package itix.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SEASON", schema = "ITIX", catalog = "")
public class Season {

    private Integer season_id;
    private String season_name;

    public Season(Integer season_id, String season_name) {
        this.season_id = season_id;
        this.season_name = season_name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEASONSEQ")
    @SequenceGenerator(name = "SEASONSEQ", sequenceName = "SEASONSEQ", allocationSize = 1)
    @Column(name = "SEASON_ID")
    public Integer getSeason_id() {
        return season_id;
    }

    public void setSeason_id(Integer season_id) {
        this.season_id = season_id;
    }

    @Column(name = "SEASON_NAME")
    public String getSeason_name() {
        return season_name;
    }

    public void setSeason_name(String season_name) {
        this.season_name = season_name;
    }
}
