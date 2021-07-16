package itix.core.dao;

import itix.core.model.Match;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MatchDaoImpl implements MatchDao {

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

}
