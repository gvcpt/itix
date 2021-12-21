package itix.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "STAGE", schema = "ITIX", catalog = "")
public class Stage {

    private Integer id;
    private String name;

    public Stage() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STAGESEQ")
    @SequenceGenerator(name = "STAGESEQ", sequenceName = "STAGESEQ", allocationSize = 1)
    @Column(name = "STAGE_ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "STAGE_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
