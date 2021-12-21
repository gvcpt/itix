package itix.core.dao;

import itix.core.model.Match;
import itix.core.service.MatchServiceImpl;
import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MatchDaoImpl implements MatchDao {

    private static final Logger logger = Logger.getLogger(MatchDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Serializable save(Match match) {
        return getSession().save(match);
    }

    @Override
    public void save(List<Match> matchList) {
        for (Match m : matchList) {
            getSession().save(m);
        }
    }

    @Override
    public Match findById(final Long id) {
        return getSession().get(Match.class, id);
    }

    @Override
    public List<Match> findAllMatches() {
        Query query = getSession().createQuery("select m from " + Match.class.getSimpleName() + " m");
        return query.list();
    }

    @Override
    public List<Match> findAllMatches(String leagueId) {
        Query query = getSession().createQuery("select m from " + Match.class.getSimpleName() + " m " + " where m.league = " + leagueId);
        return query.list();
    }

    @Override
    public List<Match> findAllMatches(String leagueId, String season) {
        Query query = getSession().createQuery("select m from " + Match.class.getSimpleName() + " m " +
              " where m.league = " + leagueId +
              " and m.season = " + season);
        return query.list();
    }

}
