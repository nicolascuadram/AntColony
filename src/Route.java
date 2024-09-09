import java.util.ArrayList;
import java.util.List;

public class Route {

    // ATTRIBUTES
    // This array contains all the ways that an ant has passed through in this route.
    private List<Way> ways;
    // This represents the sum of costs for all the nodes in this route.
    private double totalCost;

    // CONSTRUCTOR
    public Route() {
        this.ways = new ArrayList<>();
        this.totalCost = 0;
    }

    public void addWay(Way way) {
        this.ways.add(way);
        this.totalCost = this.totalCost + way.getCost();
    }

    // GETTERS AND SETTERS
    public List<Way> getWays() {
        return ways;
    }

    public void setWays(List<Way> ways) {
        this.ways = ways;
    }

    public double getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

}
