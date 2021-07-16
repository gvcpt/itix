package itix.core.dao;

import itix.core.model.ScoreEntity;
import java.io.Serializable;

public interface ScoreDao {

    Serializable save(ScoreEntity score);

    ScoreEntity findById(final Integer id);

}
