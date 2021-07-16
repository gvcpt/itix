package itix.core.dao;

import itix.core.model.ScoreEntity;
import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreDaoImpl implements ScoreDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Serializable save(ScoreEntity score) {
        return getSession().save(score);
    }

    @Override
    public ScoreEntity findById(final Integer id) {
        return getSession().get(ScoreEntity.class, id);
    }

}
