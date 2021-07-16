package itix.core.service;

public class GoalCreated {

    int gScored;
    double xGScored;
    int gTaken;
    double xGTaken;

    public GoalCreated(int gScored, double xGScored, int gTaken, double xGTaken) {
        this.gScored = gScored;
        this.xGScored = xGScored;
        this.gTaken = gTaken;
        this.xGTaken = xGTaken;
    }

    public void addGoalCreated(int gScored, double xGScored, int gTaken, double xGTaken) {
        this.gScored += Math.round(gScored * 100.0) / 100.0;
        this.xGScored += Math.round(xGScored * 100.0) / 100.0;
        this.gTaken += Math.round(gTaken * 100.0) / 100.0;
        this.xGTaken += Math.round(xGTaken * 100.0) / 100.0;
    }

    public int getgScored() {
        return gScored;
    }

    public void setgScored(int gScored) {
        this.gScored = gScored;
    }

    public double getxGScored() {
        return xGScored;
    }

    public void setxGScored(double xGScored) {
        this.xGScored = xGScored;
    }

    public int getgTaken() {
        return gTaken;
    }

    public void setgTaken(int gTaken) {
        this.gTaken = gTaken;
    }

    public double getxGTaken() {
        return xGTaken;
    }

    public void setxGTaken(double xGTaken) {
        this.xGTaken = xGTaken;
    }

}
