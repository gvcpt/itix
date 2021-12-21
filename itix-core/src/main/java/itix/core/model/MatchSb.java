package itix.core.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MATCH_SB", schema = "ITIX", catalog = "")
public class MatchSb {

    //    private Long id;
    private Integer match_id;
    private String match_date;
    private String kick_off;
    private Competition competition;
    private Season season;
    private Team home_team;
    private Team away_team;
    private Integer home_score;
    private Integer away_score;
    private String match_status;
    private String match_status_360;
    private String last_updated;
    private String last_updated_360;
    private Integer match_week;
    private Stage competition_stage;
    private Referee referee;

    public MatchSb() {
    }

    public MatchSb(Integer match_id, String match_date, String kick_off, Competition competition) {
//        this.id = id;
        this.match_id = match_id;
        this.match_date = match_date;
        this.kick_off = kick_off;
        this.competition = competition;
    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATCHSBSEQ")
//    @SequenceGenerator(name = "MATCHSBSEQ", sequenceName = "MATCHSBSEQ", allocationSize = 1)
//    @Column(name = "id")
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    @Id
    @Column(name = "MATCH_ID")
    public Integer getMatch_id() {
        return match_id;
    }

    public void setMatch_id(Integer match_id) {
        this.match_id = match_id;
    }

    @Column(name = "MATCH_DATE")
    public String getMatch_date() {
        return match_date;
    }

    public void setMatch_date(String match_date) {
        this.match_date = match_date;
    }

    @Column(name = "MATCH_KO")
    public String getKick_off() {
        return kick_off;
    }

    public void setKick_off(String kick_off) {
        this.kick_off = kick_off;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "COMPETITION_ID", nullable = true)
    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "SEASON_ID", nullable = true)
    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "HOME_TEAM_ID", nullable = true)
    public Team getHome_team() {
        return home_team;
    }

    public void setHome_team(Team home_team) {
        this.home_team = home_team;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "AWAY_TEAM_ID", nullable = true)
    public Team getAway_team() {
        return away_team;
    }

    public void setAway_team(Team away_team) {
        this.away_team = away_team;
    }

    @Column(name = "HOME_SCORE")
    public Integer getHome_score() {
        return home_score;
    }

    public void setHome_score(Integer home_score) {
        this.home_score = home_score;
    }

    @Column(name = "AWAY_SCORE")
    public Integer getAway_score() {
        return away_score;
    }

    public void setAway_score(Integer away_score) {
        this.away_score = away_score;
    }

    @Column(name = "MATCH_STATUS")
    public String getMatch_status() {
        return match_status;
    }

    public void setMatch_status(String match_status) {
        this.match_status = match_status;
    }

    @Column(name = "MATCH_STATUS_360")
    public String getMatch_status_360() {
        return match_status_360;
    }

    public void setMatch_status_360(String match_status_360) {
        this.match_status_360 = match_status_360;
    }

    @Column(name = "LAST_UPDATE")
    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    @Column(name = "LAST_UPDATE_360")
    public String getLast_updated_360() {
        return last_updated_360;
    }

    public void setLast_updated_360(String last_updated_360) {
        this.last_updated_360 = last_updated_360;
    }

    @Column(name = "MATCH_WEEK")
    public Integer getMatch_week() {
        return match_week;
    }

    public void setMatch_week(Integer match_week) {
        this.match_week = match_week;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "STAGE_ID", nullable = true)
    public Stage getCompetition_stage() {
        return competition_stage;
    }

    public void setCompetition_stage(Stage competition_stage) {
        this.competition_stage = competition_stage;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "REFEREE_ID", nullable = true)
    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }
}
