package itix.core.dao;

import itix.core.model.Competition;
import itix.core.model.MatchSb;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JsonDaoImpl implements JsonDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void storeCompetition(Competition c) {
        getSession().save(c);
        getSession().flush();
    }

    @Override
    public void storeMatch(MatchSb m) {
        getSession().save(m);
        getSession().flush();
    }

    @Override
    public void flushSession() {
        getSession().flush();
    }
}
