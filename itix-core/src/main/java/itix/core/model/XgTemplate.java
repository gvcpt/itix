package itix.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "XGTEMPLATE", schema = "ITIX", catalog = "")
public class XgTemplate {

    private Long id;
    private String team;
    private String opponent;
    private Double xGPlus;
    private int gPlus;
    private Double xGPMinus;
    private int gMinus;
    private Double deltaXg;
    private Double deltaG;

    public XgTemplate() {
    }

    public XgTemplate(String team, String opponent, Double xGPlus, int gPlus, Double xGPMinus, int gMinus, Double deltaXg, Double deltaG) {
        this.team = team;
        this.opponent = opponent;
        this.xGPlus = xGPlus;
        this.gPlus = gPlus;
        this.xGPMinus = xGPMinus;
        this.gMinus = gMinus;
        this.deltaXg = deltaXg;
        this.deltaG = deltaG;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XGSEQ")
    @SequenceGenerator(name = "XGSEQ", sequenceName = "XGSEQ", allocationSize = 1)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "TEAM")
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Column(name = "OPPONENT")
    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    @Column(name = "XG_PLUS")
    public Double getxGPlus() {
        return xGPlus;
    }

    public void setxGPlus(Double xGPlus) {
        this.xGPlus = xGPlus;
    }

    @Column(name = "G_PLUS")
    public int getgPlus() {
        return gPlus;
    }

    public void setgPlus(int gPlus) {
        this.gPlus = gPlus;
    }

    @Column(name = "XG_MINUS")
    public Double getxGPMinus() {
        return xGPMinus;
    }

    public void setxGPMinus(Double xGPMinus) {
        this.xGPMinus = xGPMinus;
    }

    @Column(name = "G_MINUS")
    public int getgMinus() {
        return gMinus;
    }

    public void setgMinus(int gMinus) {
        this.gMinus = gMinus;
    }

    @Column(name = "DELTA_XG")
    public Double getDeltaXg() {
        return deltaXg;
    }

    public void setDeltaXg(Double deltaXg) {
        this.deltaXg = deltaXg;
    }

    @Column(name = "DELTA_G")
    public Double getDeltaG() {
        return deltaG;
    }

    public void setDeltaG(Double deltaG) {
        this.deltaG = deltaG;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("xgTemplate{");
        sb.append("id=").append(id);
        sb.append(", team='").append(team).append('\'');
        sb.append(", opponent='").append(opponent).append('\'');
        sb.append(", xGPlus=").append(xGPlus);
        sb.append(", gPlus=").append(gPlus);
        sb.append(", xGPMinus=").append(xGPMinus);
        sb.append(", gMinus=").append(gMinus);
        sb.append(", deltaXg=").append(deltaXg);
        sb.append(", deltaG=").append(deltaG);
        sb.append('}');
        return sb.toString();
    }
}
