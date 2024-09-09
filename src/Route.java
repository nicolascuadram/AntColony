import java.util.ArrayList;
import java.util.List;

public class Route {

    // ATTRIBUTES
    // This array contains all the ways that an ant has passed through in this route.
    private List<Way> ways;
    // This represents the sum of costs for all the nodes in this route.
    private int totalCost;

    // CONSTRUCTOR
    public Route() {
        this.ways = new ArrayList<>();
        this.totalCost = 0;
    }

    public void updatePheromones() {
        // evaporation = 0.01
        // learning = 1
        // new pheromones = ((1 - evaporation) * pheromones) + SUM(learning / totalCost) | 0
    }

    // GETTERS AND SETTERS
    public List<Way> getWays() {
        return ways;
    }

    public void setWays(List<Way> ways) {
        this.ways = ways;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

}
