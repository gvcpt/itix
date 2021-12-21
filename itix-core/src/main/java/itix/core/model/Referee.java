package itix.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "REFEREE", schema = "ITIX", catalog = "")
public class Referee {

    private Integer id;
    private String name;

    public Referee() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REFEREESEQ")
    @SequenceGenerator(name = "REFEREESEQ", sequenceName = "REFEREESEQ", allocationSize = 1)
    @Column(name = "REFEREE_ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "REFEREE_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
