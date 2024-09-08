import java.util.ArrayList;
import java.util.List;

public class Route {

    // ATTRIBUTES
    // This array contains all the nodes that an ant has passed through in this route.
    private List<Node> nodes; // maybe this will change for a ways list
    // This represents the sum of costs for all the nodes in this route.
    private int totalCost;

    // CONSTRUCTOR
    public Route() {
        this.nodes = new ArrayList<>();
        this.totalCost = 0;
    }

    public void updatePheromones() {
        // evaporation = 0.01
        // learning = 1
        // new pheromones = ((1 - evaporation) * pheromones) + SUM(learning / totalCost) | 0
    }

    // GETTERS AND SETTERS
    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

}
