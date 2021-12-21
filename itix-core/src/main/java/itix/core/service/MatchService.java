package itix.core.service;

import itix.core.model.Match;
import java.util.List;

public interface MatchService {

    Match getById(Long matchId);

    List<Match> getAllMatches();

    List<Match> getAllMatches(String leagueId);

    List<Match> getAllMatches(String leagueId, String season);

    void addMatch(Match m);

    void addMatch(List<Match> m);

}
