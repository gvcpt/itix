package itix.core.config;

import itix.core.model.Match;
import itix.core.model.XgTemplate;
import itix.core.service.GoalCreated;
import itix.core.service.MatchService;
import itix.core.sobj.TeamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItixApplication {

    private static final Logger logger = Logger.getLogger(ItixApplication.class);

    private static final String PROPERTIES = "/runtime.properties";
    private static final String SIX_NATIONS_COMMON_FILE_NAME = "/6NationsStatsITIX_";
    private static final String SALERNITANA = "Salernitana";

    @Autowired
    private MatchService matchService;


    public void testSimpleWrite() {
        Match m = new Match();
        m.setSeason("2021");
        m.setHomeTeam("Salernitana");
        m.setAwayTeam("Venezia");
        m.setHScore("2");
        m.setAScore("0");

        matchService.addMatch(m);

        Match savedMatch = matchService.getById(1L);
        logger.debug("retrieving saved match " + savedMatch);

    }

    public MatchService getMatchService() {
        return this.matchService;
    }


    public void storeSpiMatches(List<String> file, String league) {
        final String firstLiine = file.get(0);
        final List<String> list = Stream.of(firstLiine.split(","))
              .collect(Collectors.toList());
        final int INDEX_LEAGUE_ID = list.indexOf("league_id");
        final int INDEX_LEAGUE_NAME = list.indexOf("league");
        final int INDEX_SEASON = list.indexOf("season");
        final int INDEX_DATE = list.indexOf("date");
        final int INDEX_HOME_TEAM = list.indexOf("team1");
        final int INDEX_AWAY_TEAM = list.indexOf("team2");
        final int INDEX_HOME_XG = list.indexOf("xg1");
        final int INDEX_AWAY_XG = list.indexOf("xg2");
        final int INDEX_HOME_NS_XG = list.indexOf("nsxg1");
        final int INDEX_AWAY_NS_XG = list.indexOf("nsxg2");
        final int INDEX_HOME_SCORE = list.indexOf("score1");
        final int INDEX_AWAY_SCORE = list.indexOf("score2");

        int writeLine = 1;
        int fileSize = file.size();
        List<Match> matchList = new ArrayList<>();

        for (int i = 1; i < fileSize; i++) {
            final String line = file.get(i);
            final String[] splitLine = line.split(",", -1);
            Match m = new Match();
            m.setSeason(splitLine[INDEX_SEASON]);
            m.setMatchDate(getDate(splitLine, INDEX_DATE));
            m.setLeague(splitLine[INDEX_LEAGUE_NAME]);
            m.setLeagueId(splitLine[INDEX_LEAGUE_ID]);
            m.setHomeTeam(splitLine[INDEX_HOME_TEAM]);
            m.setAwayTeam(splitLine[INDEX_AWAY_TEAM]);
            m.setHxG(splitLine[INDEX_HOME_XG]);
            m.setAxG(splitLine[INDEX_AWAY_XG]);
            m.setNsHxG(splitLine[INDEX_HOME_NS_XG]);
            m.setNsAxG(splitLine[INDEX_AWAY_NS_XG]);
            m.setHScore(splitLine[INDEX_HOME_SCORE]);
            m.setAScore(splitLine[INDEX_AWAY_SCORE]);
            computePoints(m);
            if (m.getLeagueId().equals(ItixConstants.SERIE_A_ID)) {
                logger.debug("league id = " + ItixConstants.SERIE_A_ID + " match date " + m.getMatchDate() + " season " + m.getSeason());
                if (m.getHomeTeam().equals("Lazio") && m.getAwayTeam().equals("Atalanta")) {
                    logger.debug("Lazio - Atalanta = " + m.getHScore() + " " + m.getAScore());
                }
            }

            matchList.add(m);
            writeLine++;
        }
        matchService.addMatch(matchList);

        logger.debug(writeLine + " lignes écrites");

    }

    private void computePoints(Match m) {
        if (m.getHScore() == null || m.getHScore().isEmpty() || m.getAScore() == null || m.getAScore().isEmpty()) {
            return;
        }
        if (Double.valueOf(m.getHScore()).compareTo(Double.valueOf(m.getAScore())) > 0) {
            m.setHomePoints(3);
            m.setAwayPoints(0);
        } else if (Double.valueOf(m.getHScore()).compareTo(Double.valueOf(m.getAScore())) < 0) {
            m.setHomePoints(0);
            m.setAwayPoints(3);
        } else {
            m.setHomePoints(1);
            m.setAwayPoints(1);
        }
    }

    private Date getDate(String[] splitLine, int INDEX_DATE) {
        Date date = null;
        if (splitLine[INDEX_DATE] != null) {
            String d = splitLine[INDEX_DATE];
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(splitLine[INDEX_DATE]);
            } catch (ParseException e) {
                logger.debug("Cannot parse date " + d);
            }
        }
        return date;
    }

    /*
     * Cette méthode crée un classement globale Excel des xG pour chaque match disponible
     */
    public void createGlobalxGClassement() {
        List<Match> allMatches = matchService.getAllMatches();
        if (allMatches == null) {
            logger.debug("No match found");
            return;
        }
        logger.debug("Found " + allMatches.size() + " matches");
        Map<String, GoalCreated> goalCreatedByTeam = new HashMap<String, GoalCreated>(allMatches.size());
        for (Match m : allMatches) {
            String homeTeam = m.getHomeTeam();
            if (m.getHScore() == null) {
                continue;
            }
            String awayTeam = m.getAwayTeam();

            int goalScored = Integer.parseInt(m.getHScore());
            double xGoalScored = (m.getHxG() == null ? 0 : Double.parseDouble(m.getHxG())) + (m.getNsHxG() == null ? 0 : Double.parseDouble(m.getNsHxG()));
            int goalTaken = Integer.parseInt(m.getAScore());
            double xGoalTaken = (m.getAxG() == null ? 0 : Double.parseDouble(m.getAxG())) + (m.getNsAxG() == null ? 0 : Double.parseDouble(m.getNsAxG()));

            goalCreatedByTeam.computeIfAbsent(homeTeam, k -> new GoalCreated(0, 0, 0, 0))
                  .addGoalCreated(goalScored, xGoalScored, goalTaken, xGoalTaken);

            goalCreatedByTeam.computeIfAbsent(awayTeam, k -> new GoalCreated(0, 0, 0, 0))
                  .addGoalCreated(goalTaken, xGoalTaken, goalScored, xGoalScored);
//            logger.debug("match = " + homeTeam + " vs " + awayTeam + " recorded");
        }

        logger.debug("total size = " + goalCreatedByTeam.size());

        try {
            writeToExcel("globalClassement", goalCreatedByTeam);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
     * Cette méthode crée un classement pour chaque équipe avec le cumul des xG pour chaque équipe rencontré
     */
    public void createClassementByTeam() {

        List<String> teamList = matchService.getAllTeams();
        for (String t : teamList) {
            List<Match> allMatches = matchService.getAllMatchesByTeam(t);
            if (allMatches == null) {
                logger.debug("No match found");
                return;
            }
            logger.debug("Found " + allMatches.size() + " matches");
            Map<String, GoalCreated> goalCreatedByTeam = new HashMap<String, GoalCreated>(allMatches.size());

            for (Match m : allMatches) {
                if (m.getHScore() == null) {
                    continue;
                }
                if (t.equals(m.getHomeTeam())) {
                    int goalScored = Integer.parseInt(m.getHScore());
                    double xGoalScored =
                          (m.getHxG() == null ? 0 : Double.parseDouble(m.getHxG())) + (m.getNsHxG() == null ? 0 : Double.parseDouble(m.getNsHxG()));
                    int goalTaken = Integer.parseInt(m.getAScore());
                    double xGoalTaken =
                          (m.getAxG() == null ? 0 : Double.parseDouble(m.getAxG())) + (m.getNsAxG() == null ? 0 : Double.parseDouble(m.getNsAxG()));
                    goalCreatedByTeam.computeIfAbsent(m.getAwayTeam(), k -> new GoalCreated(0, 0, 0, 0))
                          .addGoalCreated(goalScored, xGoalScored, goalTaken, xGoalTaken);
                } else {
                    int goalScored = Integer.parseInt(m.getAScore());
                    double xGoalScored =
                          (m.getAxG() == null ? 0 : Double.parseDouble(m.getAxG())) + (m.getNsAxG() == null ? 0 : Double.parseDouble(m.getNsAxG()));
                    int goalTaken = Integer.parseInt(m.getHScore());
                    double xGoalTaken =
                          (m.getHxG() == null ? 0 : Double.parseDouble(m.getHxG())) + (m.getNsHxG() == null ? 0 : Double.parseDouble(m.getNsHxG()));
                    goalCreatedByTeam.computeIfAbsent(m.getHomeTeam(), k -> new GoalCreated(0, 0, 0, 0))
                          .addGoalCreated(goalScored, xGoalScored, goalTaken, xGoalTaken);
                }

//            logger.debug("ma//tch = " + homeTeam + " vs " + awayTeam + " recorded");
            }

            logger.debug("total output lines = " + goalCreatedByTeam.size());
//            try {
//                writeToExcelForTeams("classementByTwoTeams", t, goalCreatedByTeam);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            goalCreatedByTeam.forEach((k, v) -> writeXgTemplateMapToDb(t, k, v));
        }
    }


    /*
     * Cette méthode crée les classements des différents championnats
     */
    private List<TeamResult> createSeasonClassement(List<Match> seasonMatchList) {
        List<TeamResult> classement = new ArrayList<>();
        seasonMatchList.stream().map(Match::getHomeTeam).forEach(t -> addToClassement(t, classement));

        for (Match m : seasonMatchList) {
//            logger.debug("match " + m.getHomeTeam() + " - " + m.getAwayTeam() + " : " + m.getHScore() + " - " + m.getAScore());
            if (m.getHScore() != null && !m.getHScore().isEmpty() && m.getAScore() != null && !m.getAScore().isEmpty()) {
                addPoints(classement, m.getHomeTeam(), m.getHomePoints());
                addPoints(classement, m.getAwayTeam(), m.getAwayPoints());
                Collections.sort(classement);
//                logger.debug("new classement " + classement.toString());
            }
        }
        return classement;
    }

    private void addToClassement(String t, List<TeamResult> classement) {
        if (classement.stream().noneMatch(tr -> tr.getTeamName().equals(t))) {
            classement.add(new TeamResult(t, 0));
        }
    }

    private void addPoints(List<TeamResult> classement, String team, Integer points) {
        classement.stream()
              .filter(t -> t.getTeamName().equals(team))
              .findFirst()
              .ifPresent(tr -> tr.setTeamPoints(tr.getTeamPoints() + points));
    }

    /*
     * Cette méthode crée les classement
     */
    public void createClassementByLeagueSeason() {

        List<Match> leagueMatchList = matchService.getAllMatchesByLeagueId(ItixConstants.SERIE_A_ID).stream().sorted().collect(Collectors.toList());
        Set<String> yearList = leagueMatchList.stream()
              .map(Match::getSeason)
              .collect(Collectors.toSet());

        // create classements by seasons
        Map<String, List<TeamResult>> classementBySeasonList = new HashMap<>();
        for (String year : yearList) {
            List<Match> seasonMatchList = leagueMatchList.stream().filter(m -> year.equals(m.getSeason())).collect(Collectors.toList());

            // final classement of league for the current year. It will be the reference for the indicator performances
            List<TeamResult> classement = createSeasonClassement(seasonMatchList);
//            logger.debug("classement " + year + " " + classement.toString());
            classementBySeasonList.put(year, classement);

            // test indicator : xG+
//            testIndicator(seasonMatchList, ItixConstants.DG_CLASSEMENT);
        }


    }

    private void testIndicator(List<Match> matchList, String indicator) {
        // create classement by indicator
        Map<String, GoalCreated> goalCreatedByTeam = new HashMap<String, GoalCreated>(matchList.size());

        for (Match m : matchList) {
            if (m.getHScore() == null) {
                continue;
            }

            // TODO créer un classement par indicateur
            // TODO calculer la fonction de cout qui minimise cet indicateur (par rapport au classement final)
        }
    }

    private void writeXgTemplateMapToDb(String team, String opponentTeam, GoalCreated xgSrc) {
        XgTemplate xg = new XgTemplate();
        xg.setTeam(team);
        xg.setOpponent(opponentTeam);
        xg.setgPlus(xgSrc.getgScored());
        xg.setgMinus(xgSrc.getgTaken());
        xg.setxGPlus(xgSrc.getxGScored());
        xg.setxGPMinus(xgSrc.getxGTaken());
        xg.setDeltaXg(Double.sum(xgSrc.getxGScored(), -xgSrc.getxGTaken()));
        xg.setDeltaG(Double.sum(xgSrc.getgScored(), -xgSrc.getgTaken()));

        matchService.addExpectedGoal(xg);
    }

    private void writeToExcel(String fileName, Map<String, GoalCreated> goalCreatedByTeam) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        headerStyle.setFillPattern(FillPatternType.NO_FILL);

        // create header
        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        font.setBold(false);
        headerStyle.setFont(font);

        // list sorted by xG+ (descending)
        List<Entry<String, GoalCreated>> sortedxGPlus = goalCreatedByTeam.entrySet().stream()
              .sorted((g1, g2) -> Double.compare(g2.getValue().getxGScored(), g1.getValue().getxGScored()))
              .collect(Collectors.toList());
        Sheet sheet = createSheet(workbook, headerStyle, "xG+", "Team", "xG+", "g+");
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        createRows(workbook, sheet, sortedxGPlus, "xG+");

        // list sorted by xG-
        List<Entry<String, GoalCreated>> sortedxGMinus = goalCreatedByTeam.entrySet().stream()
              .sorted((g1, g2) -> Double.compare(g1.getValue().getxGTaken(), g2.getValue().getxGTaken()))
              .collect(Collectors.toList());
        sheet = createSheet(workbook, headerStyle, "xG-", "Team", "xG-", "g-");
        style = workbook.createCellStyle();
        style.setWrapText(true);
        createRows(workbook, sheet, sortedxGMinus, "xG-");

        // list sorted by gDiff (descending)
        List<Entry<String, GoalCreated>> sortedxGDiff = goalCreatedByTeam.entrySet().stream()
              .sorted((g1, g2) -> Double.compare((g2.getValue().getxGScored() - g2.getValue().getxGTaken()),
                    (g1.getValue().getxGScored()) - g1.getValue().getxGTaken()))
              .collect(Collectors.toList());
        sheet = createSheet(workbook, headerStyle, "xGDiff", "Team", "xG+", "xG-");
        style = workbook.createCellStyle();
        style.setWrapText(true);
        createRows(workbook, sheet, sortedxGDiff, "xGDiff");

        String path = "C:\\Users\\Utilisateur\\itix\\generatedXls\\";
        String filename = fileName + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + ".xlsx";
        String fileLocation = path + filename;

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
        logger.debug("... Creating output file under " + fileLocation + " ...");
        try {
            TimeUnit.SECONDS.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void writeToExcelForTeams(String fileName, String teamName, Map<String, GoalCreated> goalCreatedByTeam) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        headerStyle.setFillPattern(FillPatternType.NO_FILL);

        // create header
        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        font.setBold(false);
        headerStyle.setFont(font);

        // list sorted by xG+ (descending)
        List<Entry<String, GoalCreated>> sortedxGPlus = goalCreatedByTeam.entrySet().stream()
              .sorted((g1, g2) -> Double.compare(g2.getValue().getxGScored(), g1.getValue().getxGScored()))
              .collect(Collectors.toList());
        // list sorted by xG-
        List<Entry<String, GoalCreated>> sortedxGMinus = goalCreatedByTeam.entrySet().stream()
              .sorted((g1, g2) -> Double.compare(g1.getValue().getxGTaken(), g2.getValue().getxGTaken()))
              .collect(Collectors.toList());
        // list sorted by gDiff (descending)
        List<Entry<String, GoalCreated>> sortedxGDiff = goalCreatedByTeam.entrySet().stream()
              .sorted((g1, g2) -> Double.compare((g2.getValue().getxGScored() - g2.getValue().getxGTaken()),
                    (g1.getValue().getxGScored()) - g1.getValue().getxGTaken()))
              .collect(Collectors.toList());
        Sheet sheet = createTeamSheet(workbook, headerStyle, teamName, "xG+", "g+", "xG-", "g-", "DG");
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        createTeamRows(workbook, sheet, sortedxGPlus, sortedxGMinus, sortedxGDiff);

        String path = "C:\\Users\\Utilisateur\\itix\\generatedXls\\test\\";
        String filename = fileName + teamName + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + ".xlsx";
        String fileLocation = path + filename;

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
        logger.debug("... Creating output file under " + fileLocation + " ...");
        try {
            TimeUnit.SECONDS.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Sheet createSheet(XSSFWorkbook workbook, CellStyle headerStyle, String sheetName, String cell1Name, String cell2Name, String cell3Name) {
        Sheet sheet = workbook.createSheet(sheetName);
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);
        Row header = sheet.createRow(0);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue(cell1Name);
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue(cell2Name);
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue(cell3Name);
        headerCell.setCellStyle(headerStyle);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        return sheet;
    }

    private Sheet createTeamSheet(XSSFWorkbook workbook, CellStyle headerStyle, String sheetName, String cell1Name, String cell2Name, String cell3Name,
          String cell4Name, String cell5Name) {
        Sheet sheet = workbook.createSheet(sheetName);
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);
        Row header = sheet.createRow(0);

        createCell(header, 0, sheetName, headerStyle);
        createCell(header, 1, cell1Name, headerStyle);
        createCell(header, 2, cell2Name, headerStyle);
        createCell(header, 3, cell3Name, headerStyle);
        createCell(header, 4, cell4Name, headerStyle);
        createCell(header, 5, cell5Name, headerStyle);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        return sheet;
    }

    private void createCell(Row header, int pos, String cellName, CellStyle headerStyle) {
        Cell headerCell = header.createCell(pos);
        headerCell.setCellValue(cellName);
        headerCell.setCellStyle(headerStyle);
    }

    private void createRows(XSSFWorkbook workbook, Sheet sheet, List<Entry<String, GoalCreated>> aggregatedxGList, String choice) {
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        int rownum = 2;
        for (Entry<String, GoalCreated> entry : aggregatedxGList) {
            Row row = sheet.createRow(rownum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(entry.getKey());
            cell.setCellStyle(style);

            if ("xG+".equals(choice)) {
                cell = row.createCell(1);
                cell.setCellValue(entry.getValue().getxGScored());
                cell = row.createCell(2);
                cell.setCellValue(entry.getValue().getgScored());
            } else if ("xG-".equals(choice)) {
                cell = row.createCell(1);
                cell.setCellValue(entry.getValue().getxGTaken());
                cell = row.createCell(2);
                cell.setCellValue(entry.getValue().getgTaken());
            } else if ("xGDiff".equals(choice)) {
                cell = row.createCell(1);
                cell.setCellValue(entry.getValue().getxGScored());
                cell = row.createCell(2);
                cell.setCellValue(entry.getValue().getxGTaken());
            }
            cell.setCellStyle(style);
        }
    }

    private void createTeamRows(XSSFWorkbook workbook, Sheet sheet, List<Entry<String, GoalCreated>> aggregatedxGList1,
          List<Entry<String, GoalCreated>> aggregatedxGList2, List<Entry<String, GoalCreated>> aggregatedxGList3) {
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        addaggregatedData(sheet, style, aggregatedxGList1, aggregatedxGList2, aggregatedxGList3);
    }

    private void addaggregatedData(Sheet sheet, CellStyle style, List<Entry<String, GoalCreated>> aggregatedxGList1
          , List<Entry<String, GoalCreated>> aggregatedxGList2, List<Entry<String, GoalCreated>> aggregatedxGList3) {
        int rownum = 2;
        for (Entry<String, GoalCreated> entry : aggregatedxGList1) {
            Row row = sheet.createRow(rownum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(entry.getKey());
            cell.setCellStyle(style);

            // xG+
            cell = row.createCell(1);
            cell.setCellValue(entry.getValue().getxGScored());
            cell = row.createCell(2);
            cell.setCellValue(entry.getValue().getgScored());
            cell.setCellStyle(style);

            // xG-
            Entry<String, GoalCreated> entry2 = aggregatedxGList2.stream().filter(e -> e.getKey().equals(entry.getKey())).findFirst().orElse(null);
            cell = row.createCell(3);
            cell.setCellValue(entry2.getValue().getxGTaken());
            cell = row.createCell(4);
            cell.setCellValue(entry2.getValue().getgTaken());
            cell.setCellStyle(style);

            // DG
            Entry<String, GoalCreated> entry3 = aggregatedxGList2.stream().filter(e -> e.getKey().equals(entry.getKey())).findFirst().orElse(null);
            cell = row.createCell(5);
            cell.setCellValue(entry3.getValue().getxGScored() - entry3.getValue().getxGTaken());
            cell.setCellStyle(style);

        }

    }


}
