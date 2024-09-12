import java.util.ArrayList;
import java.util.List;

public class Route {

    // ATTRIBUTES
    // This array contains all the nodes that an ant has passed through in this route.
    private List<Node> nodes;
    // This array contains all the ways that an ant has passed through in this route.
    private List<Way> ways;
    // This represents the sum of costs for all the nodes in this route.
    private double totalCost;

    // CONSTRUCTOR
    public Route() {
        this.nodes = new ArrayList<>();
        this.ways = new ArrayList<>();
        this.totalCost = 0;
    }

    public void addWay(Way way) {
        this.ways.add(way);
        this.totalCost += way.getCost();
    }

    public void printNodes() {
        System.out.println("Nodes:");
        for (Node node : this.nodes) {
            System.out.println("ID: " + node.getId());
        }
        System.out.println("");
    }

    // GETTERS AND SETTERS
    public List<Node> getNodes() {
        return this.nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
    
    public List<Way> getWays() {
        return this.ways;
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
