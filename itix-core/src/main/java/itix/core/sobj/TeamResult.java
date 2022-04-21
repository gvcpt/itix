package itix.core.sobj;

public class TeamResult implements Comparable<TeamResult> {

    private String teamName;
    private Integer teamPoints;

    public TeamResult() {
    }

    public TeamResult(String teamName, Integer teamPoints) {
        this.teamName = teamName;
        this.teamPoints = teamPoints;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTeamPoints() {
        return teamPoints;
    }

    public void setTeamPoints(Integer teamPoints) {
        this.teamPoints = teamPoints;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("").append(teamName);
        sb.append(" ").append(teamPoints);
        sb.append('\n');
        return sb.toString();
    }


    @Override
    public int compareTo(TeamResult o) {
        // reverse order by default
        return Integer.compare(o.teamPoints, this.teamPoints);
    }
}
