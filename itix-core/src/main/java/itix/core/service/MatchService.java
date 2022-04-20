package itix.core.service;

import itix.core.model.Match;
import itix.core.model.XgTemplate;
import java.util.List;

public interface MatchService {

    Match getById(Long matchId);

    List<Match> getAllMatches();

    List<String> getAllTeams();

    List<Match> getAllMatchesByTeam(String team);

    List<Match> getAllMatchesByLeagueId(String leagueId);

    List<Match> getAllMatches(String leagueId, String season);

    void addMatch(Match m);

    void addMatch(List<Match> m);

    public void addExpectedGoal(XgTemplate xg);

}
