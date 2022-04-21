package itix.core.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MATCH", schema = "ITIX", catalog = "")
public class Match implements Comparable<Match> {

    private Long id;
    private String season;
    private Date matchDate;
    private String league;
    private String leagueId;
    private String homeTeam;
    private String awayTeam;
    private String HScore;
    private String AScore;
    private String HxG;
    private String AxG;
    private String nsHxG;
    private String nsAxG;
    private Integer homePoints;
    private Integer awayPoints;

    public Match() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATCHSEQ")
    @SequenceGenerator(name = "MATCHSEQ", sequenceName = "MATCHSEQ", allocationSize = 1)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "SEASON")
    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    @Column(name = "LEAGUE")
    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    @Column(name = "LEAGUE_ID")
    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
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

    @Column(name = "H_SCORE")
    public String getHScore() {
        return HScore;
    }

    public void setHScore(String hScore) {
        this.HScore = hScore;
    }

    @Column(name = "A_SCORE")
    public String getAScore() {
        return this.AScore;
    }

    public void setAScore(String aScore) {
        this.AScore = aScore;
    }

    @Column(name = "H_xG")
    public String getHxG() {
        return HxG;
    }

    public void setHxG(String hxG) {
        this.HxG = hxG;
    }

    @Column(name = "A_xG")
    public String getAxG() {
        return AxG;
    }

    public void setAxG(String axG) {
        this.AxG = axG;
    }

    @Column(name = "nsH_xG")
    public String getNsHxG() {
        return nsHxG;
    }

    public void setNsHxG(String nsHxG) {
        this.nsHxG = nsHxG;
    }

    @Column(name = "nsA_xG")
    public String getNsAxG() {
        return nsAxG;
    }

    public void setNsAxG(String nsAxG) {
        this.nsAxG = nsAxG;
    }

    @Column(name = "h_points")
    public Integer getHomePoints() {
        return homePoints;
    }

    public void setHomePoints(Integer homePoints) {
        this.homePoints = homePoints;
    }

    @Column(name = "a_points")
    public Integer getAwayPoints() {
        return awayPoints;
    }

    public void setAwayPoints(Integer awayPoints) {
        this.awayPoints = awayPoints;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Match{");
        sb.append("id=").append(id);
        sb.append(", season='").append(season).append('\'');
        sb.append(", matchDate=").append(matchDate);
        sb.append(", league='").append(league).append('\'');
        sb.append(", leagueId='").append(leagueId).append('\'');
        sb.append(", homeTeam='").append(homeTeam).append('\'');
        sb.append(", awayTeam='").append(awayTeam).append('\'');
        sb.append(", HScore='").append(HScore).append('\'');
        sb.append(", AScore='").append(AScore).append('\'');
        sb.append(", HxG='").append(HxG).append('\'');
        sb.append(", AxG='").append(AxG).append('\'');
        sb.append(", nsHxG='").append(nsHxG).append('\'');
        sb.append(", nsAxG='").append(nsAxG).append('\'');
        sb.append(", homePoints='").append(homePoints).append('\'');
        sb.append(", awayPoints='").append(awayPoints).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Match o) {
        return this.matchDate.compareTo(o.matchDate);
    }
}
