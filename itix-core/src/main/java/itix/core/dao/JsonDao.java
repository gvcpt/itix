package itix.core.dao;

import itix.core.model.Competition;
import itix.core.model.MatchSb;

public interface JsonDao {

    void storeCompetition(Competition c);

    void storeMatch(MatchSb m);

    void flushSession();

}
