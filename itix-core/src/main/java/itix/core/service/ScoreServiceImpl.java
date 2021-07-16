package itix.core.service;

import itix.core.dao.ScoreDao;
import itix.core.model.ScoreEntity;
import java.io.Serializable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ScoreServiceImpl implements ScoreService {

    private static final Logger logger = Logger.getLogger(ScoreServiceImpl.class);

    @Autowired
    ScoreDao scoreDao;

    @Override
    @Transactional(readOnly = false)
    public void addScore(ScoreEntity s) {
        Serializable id = scoreDao.save(s);
        logger.debug("Id of new ScoreEntity " + id);
        scoreDao.findById(100);
    }


}
