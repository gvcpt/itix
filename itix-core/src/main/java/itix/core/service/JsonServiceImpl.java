package itix.core.service;

import itix.core.dao.JsonDao;
import itix.core.model.Competition;
import itix.core.model.MatchSb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class JsonServiceImpl implements JsonService {

    @Autowired
    JsonDao jsonDao;

    public void storeCompetition(Competition c) {
        jsonDao.storeCompetition(c);
        flushSession();
    }

    public void storeMatch(MatchSb m) {
        jsonDao.storeMatch(m);
        flushSession();
    }

    public void flushSession() {
        jsonDao.flushSession();
    }

}
