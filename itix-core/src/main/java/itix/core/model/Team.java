package itix.core.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TEAM", schema = "ITIX", catalog = "")
public class Team {

    private Integer home_team_id;
    private String home_team_name;
    private String home_team_gender;
    private String home_team_group;
    private Country country;
    private Manager manager;

    public Team() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEAMSEQ")
    @SequenceGenerator(name = "TEAMSEQ", sequenceName = "TEAMSEQ", allocationSize = 1)
    @Column(name = "TEAM_ID")
    public Integer getHome_team_id() {
        return home_team_id;
    }

    public void setHome_team_id(Integer home_team_id) {
        this.home_team_id = home_team_id;
    }

    @Column(name = "TEAM_NAME")
    public String getHome_team_name() {
        return home_team_name;
    }

    public void setHome_team_name(String home_team_name) {
        this.home_team_name = home_team_name;
    }

    @Column(name = "TEAM_GENDER")
    public String getHome_team_gender() {
        return home_team_gender;
    }

    public void setHome_team_gender(String home_team_gender) {
        this.home_team_gender = home_team_gender;
    }

    @Column(name = "TEAM_GROUP")
    public String getHome_team_group() {
        return home_team_group;
    }

    public void setHome_team_group(String home_team_group) {
        this.home_team_group = home_team_group;
    }

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "COUNTRY_ID", nullable = true)
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "MANAGER_ID", nullable = true)
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
