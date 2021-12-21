package itix.core.dao;

import itix.core.model.Match;
import java.io.Serializable;
import java.util.List;

public interface MatchDao {

    Serializable save(Match match);

    void save(List<Match> matchList);

    Match findById(Long id);

    List<Match> findAllMatches();

    List<Match> findAllMatches(String leagueId);

    List<Match> findAllMatches(String leagueId, String year);

}
