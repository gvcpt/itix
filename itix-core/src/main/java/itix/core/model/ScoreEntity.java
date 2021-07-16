package itix.core.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SCORE", schema = "ITIX", catalog = "")
public class ScoreEntity {

    private Integer pk;
    private String year;
    private String homeTeam;
    private String awayTeam;
    private Integer scoreHomeTeam;
    private Integer scoreAwayTeam;

    public ScoreEntity() {
    }

    public ScoreEntity(String year, String homeTeam, String awayTeam, int score_HT, int score_AT) {
        this.year = year;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.scoreHomeTeam = score_HT;
        this.scoreAwayTeam = score_AT;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCORESEQ")
    @SequenceGenerator(name = "SCORESEQ", sequenceName = "SCORESEQ", allocationSize = 1)
    @Column(name = "PK")
    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Column(name = "HOME_TEAM")
    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    @Column(name = "AWAY_TEAM")
    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Column(name = "SCORE_HT")
    public Integer getScoreHomeTeam() {
        return scoreHomeTeam;
    }

    public void setScoreHomeTeam(Integer scoreHomeTeam) {
        this.scoreHomeTeam = scoreHomeTeam;
    }

    @Column(name = "SCORE_AT")
    public Integer getScoreAwayTeam() {
        return scoreAwayTeam;
    }

    public void setScoreAwayTeam(Integer score2) {
        this.scoreAwayTeam = score2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ScoreEntity that = (ScoreEntity) o;
        return pk.equals(that.pk) &&
              scoreHomeTeam.equals(that.scoreHomeTeam) &&
              scoreAwayTeam.equals(that.scoreAwayTeam) &&
              Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, year, scoreHomeTeam, scoreAwayTeam);
    }

    public enum TeamENUM {
        E("England"),
        F("France"),
        I("Italy"),
        IR("Ireland"),
        S("Scotland"),
        W("Wales");

        private final String value;

        TeamENUM(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
