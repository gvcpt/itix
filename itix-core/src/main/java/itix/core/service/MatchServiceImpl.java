package itix.core.service;

import itix.core.dao.MatchDao;
import itix.core.model.Match;
import itix.core.model.XgTemplate;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MatchServiceImpl implements MatchService {

    private static final Logger logger = Logger.getLogger(MatchServiceImpl.class);

    @Autowired
    MatchDao matchDao;

    @Override
    public Match getById(Long matchId) {
        return matchDao.findById(matchId);
    }

    @Override
    public List<Match> getAllMatches() {
        return matchDao.findAllMatches();
    }

    @Override
    public List<String> getAllTeams() {
        List<String> teamList = matchDao.findAllTeams();
        List<String> teamListWithoutSpecialChars = new ArrayList<>(teamList.size());
        for (String s : teamList) {
            String newTeam = s.replaceAll("[-+.^:,'`]", " ");
            teamListWithoutSpecialChars.add(newTeam);
        }
        return teamListWithoutSpecialChars;
    }

    @Override
    public List<Match> getAllMatchesByTeam(String team) {
        return matchDao.findAllMatchesByTeam(team);
    }

    @Override
    public List<Match> getAllMatchesByLeagueId(String leagueId) {
        return matchDao.findAllMatchesByLeagueId(leagueId);
    }

    @Override
    public List<Match> getAllMatches(String leagueId, String season) {
        return matchDao.findAllMatches(leagueId, season);
    }

    @Override
    @Transactional(readOnly = false)
    public void addMatch(Match m) {
        Long id = (Long) matchDao.save(m);
        logger.debug("Id of new Match " + id);
    }

    @Override
    @Transactional(readOnly = false)
    public void addMatch(List<Match> m) {
        matchDao.save(m);
    }


    @Override
    @Transactional(readOnly = false)
    public void addExpectedGoal(XgTemplate xg) {
        matchDao.save(xg);
    }
}
