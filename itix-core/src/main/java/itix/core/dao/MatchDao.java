package itix.core.dao;

import itix.core.model.Match;
import itix.core.model.XgTemplate;
import java.io.Serializable;
import java.util.List;

public interface MatchDao {

    Serializable save(Match match);

    void save(List<Match> matchList);

    void save(XgTemplate xg);

    Match findById(Long id);

    List<Match> findAllMatches();

    List<String> findAllTeams();

    List<Match> findAllMatchesByTeam(String leagueId);

    List<Match> findAllMatchesByLeagueId(String leagueId);

    List<Match> findAllMatches(String leagueId, String year);

}
