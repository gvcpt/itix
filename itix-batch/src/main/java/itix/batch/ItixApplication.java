package itix.batch;

import itix.core.model.Match;
import itix.core.model.ScoreEntity;
import itix.core.service.GoalCreated;
import itix.core.service.MatchService;
import itix.core.service.ScoreService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
    MatchService matchService;

    @Autowired
    ScoreService scoreService;


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


    public void footballWriteDb(List<String> file, String league) {
        final String firstLiine = file.get(0);
        final List<String> list = Stream.of(firstLiine.split(","))
              .collect(Collectors.toList());
        final int INDEX_LEAGUE = list.indexOf("league_id");
        final int INDEX_SEASON = list.indexOf("season");
        final int INDEX_HOME_TEAM = list.indexOf("team1");
        final int INDEX_AWAY_TEAM = list.indexOf("team2");
        final int INDEX_HOME_XG = list.indexOf("xg1");
        final int INDEX_AWAY_XG = list.indexOf("xg2");
        final int INDEX_HOME_NS_XG = list.indexOf("nsxg1");
        final int INDEX_AWAY_NS_XG = list.indexOf("nsxg2");
        final int INDEX_HOME_SCORE = list.indexOf("score1");
        final int INDEX_AWAY_SCORE = list.indexOf("score2");

        int writeLine = 0;
        List<Match> matchList = new ArrayList<>();
        for (String line : file) {
            final String[] splitLine = line.split(",", -1);
            if (league.equals(splitLine[INDEX_LEAGUE])
//                  && (SALERNITANA.equals(splitLine[INDEX_HOME_TEAM]) || SALERNITANA.equals(splitLine[INDEX_AWAY_TEAM]))
            ) {
                Match m = new Match();
                m.setSeason(splitLine[INDEX_SEASON]);
                m.setHomeTeam(splitLine[INDEX_HOME_TEAM]);
                m.setAwayTeam(splitLine[INDEX_AWAY_TEAM]);
                m.setHxG(splitLine[INDEX_HOME_XG]);
                m.setAxG(splitLine[INDEX_AWAY_XG]);
                m.setNsHxG(splitLine[INDEX_HOME_NS_XG]);
                m.setNsAxG(splitLine[INDEX_AWAY_NS_XG]);
                m.setHScore(splitLine[INDEX_HOME_SCORE]);
                m.setAScore(splitLine[INDEX_AWAY_SCORE]);

//                matchService.addMatch(m);
                matchList.add(m);
                writeLine++;
            }
        }
        matchService.addMatch(matchList);

        logger.debug(writeLine + " lignes écrites");

    }

    public void createxGClassement() {
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
            writeToExcel(goalCreatedByTeam);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToExcel(Map<String, GoalCreated> goalCreatedByTeam) throws IOException {
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

        String path = "C:\\Users\\Utilisateur\\itix\\";
        String filename = "xClass" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + ".xlsx";
        String fileLocation = path + filename;

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
        logger.debug("Output file created under " + fileLocation);
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


    public void rugbyWriteToDb(List<String> file) {

        // fill SCORE table
        for (String line : file) {
            for (int i = 1; i < 6; i++) {
                final ScoreEntity scoreLine = new ScoreEntity();
                final String[] splitLine = line.split(",");
                scoreLine.setYear(splitLine[0]);
                final String[] resSplit = splitLine[i].split(":");
                final String[] resSplitTeam = resSplit[0].split("_");
                final String[] resSplitScore = resSplit[1].split("-");
                if (resSplitTeam[1].equals("H")) {
                    scoreLine.setHomeTeam("ITALY");
                    scoreLine.setScoreHomeTeam(Integer.valueOf(resSplitScore[0]));
                    String team = resSplitTeam[0];
                    scoreLine.setAwayTeam(ScoreEntity.TeamENUM.valueOf(team).getValue());

                } else {
                    scoreLine.setAwayTeam("ITALY");
                    scoreLine.setScoreHomeTeam(Integer.valueOf(resSplitScore[0]));
                    String team = resSplitTeam[0];
                    scoreLine.setHomeTeam(ScoreEntity.TeamENUM.valueOf(team).getValue());
                }
                scoreLine.setScoreAwayTeam(Integer.valueOf(resSplitScore[1]));
                scoreService.addScore(scoreLine);
            }
        }

    }


}
