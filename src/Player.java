/**
 * Created by ross on 27/09/16.
 */
public class Player {
    public String name;
    public double average;
    public int wins;
    public int losses;

    public int sets;
    public int legs;
    public int points;

    public Player(String name)
    {
        this.name = name;
        sets = 0;
        legs = 0;
        points = 501;
    }

    public String getName() {
        return name;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getSets() {
        return sets;
    }

    public boolean updatePoints(int score)
    {
        points = points - score;
        if(points == 0)
        {
            updateLegs();
            return true;
        }
        return false;
    }

    public void updateLegs()
    {
        legs++;
        if(legs == 3)
        {
            this.updateSets();
            legs = 0;
        }
    }

    public void updateSets()
    {
        sets++;
    }

}
