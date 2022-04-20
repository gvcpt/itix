package itix.core.dao;

import itix.core.model.Match;
import itix.core.model.XgTemplate;
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
    public void save(XgTemplate xg) {
        getSession().save(xg);
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
    public List<String> findAllTeams() {
        Query queryHomeTeam = getSession().createQuery("select distinct m.homeTeam from " + Match.class.getSimpleName() + " m");
        List<String> allTeams = queryHomeTeam.list();
        return allTeams;
    }

    @Override
    public List<Match> findAllMatchesByTeam(String team) {
        Query query = getSession().createQuery("select m from " + Match.class.getSimpleName() + " m " +
              " where m.homeTeam = \'" + team + "\' or m.awayTeam = \'" + team + "\'");
        return query.list();
    }

    @Override
    public List<Match> findAllMatchesByLeagueId(String leagueId) {
        Query query = getSession().createQuery("select m from " + Match.class.getSimpleName() + " m " + " where m.leagueId = " + leagueId);
        return query.list();
    }

    @Override
    public List<Match> findAllMatches(String leagueId, String season) {
        Query query = getSession().createQuery("select m from " + Match.class.getSimpleName() + " m " +
                    " where m.leagueId = " + leagueId
              // + " and m.season = " + season
        );
        return query.list();
    }

}
