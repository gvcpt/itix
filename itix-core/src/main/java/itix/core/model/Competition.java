package itix.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "COMPETITION", schema = "ITIX", catalog = "")
public class Competition {

    private Long id;
    private Integer competition_id;
    private Integer season_id;
    private MatchSb match;
    private String country_name;
    private String competition_name;
    private String competition_gender;
    private String match_updated;
    private String match_available;

    public Competition() {
    }

    public Competition(Integer competitionId, Integer seasonId, String countryName, String competitionName, String competitionGender,
          String matchUpdated, String matchAvailable) {
        this.competition_id = competitionId;
        this.season_id = seasonId;
        this.country_name = countryName;
        this.competition_name = competitionName;
        this.competition_gender = competitionGender;
        this.match_updated = matchUpdated;
        this.match_available = matchAvailable;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPETITIONSEQ")
    @SequenceGenerator(name = "COMPETITIONSEQ", sequenceName = "COMPETITIONSEQ", allocationSize = 1)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "COMPETITION_ID")
    public Integer getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(Integer competitionId) {
        this.competition_id = competitionId;
    }

//    @Column(name = "SEASON_ID")
//    public Integer getSeason_id() {
//        return season_id;
//    }
//
//    public void setSeason_id(Integer seasonId) {
//        this.season_id = seasonId;
//    }

    //    @OneToOne
//    @JoinColumn(name = "MATCH_ID", nullable = true)
//    public MatchSb getMatch() {
//        return match;
//    }
//
//    public void setMatch(MatchSb match) {
//        this.match = match;
//    }
//
//    @Column(name = "COUNTRY_NAME")
//    public String getCountry_name() {
//        return country_name;
//    }
//
//    public void setCountry_name(String countryName) {
//        this.country_name = countryName;
//    }
//
    @Column(name = "COMPETITION_NAME")
    public String getCompetition_name() {
        return competition_name;
    }

    public void setCompetition_name(String competitionName) {
        this.competition_name = competitionName;
    }

    @Column(name = "COMPETITION_GENDER")
    public String getCompetition_gender() {
        return competition_gender;
    }

    public void setCompetition_gender(String competitionGender) {
        this.competition_gender = competitionGender;
    }

//    @Column(name = "MATCH_UPDATED")
//    public String getMatch_updated() {
//        return match_updated;
//    }
//
//    public void setMatch_updated(String matchUpdated) {
//        this.match_updated = matchUpdated;
//    }
//
//    @Column(name = "MATCH_AVAILABLE")
//    public String getMatch_available() {
//        return match_available;
//    }
//
//    public void setMatch_available(String matchAvailable) {
//        this.match_available = matchAvailable;
//    }

}
