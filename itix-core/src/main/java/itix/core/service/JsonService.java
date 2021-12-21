package itix.core.service;

import itix.core.model.Competition;
import itix.core.model.MatchSb;

public interface JsonService {

    void storeCompetition(Competition c);

    void storeMatch(MatchSb m);

    void flushSession();

}
