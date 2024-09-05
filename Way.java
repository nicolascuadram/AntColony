public class Way {

    // ATTRIBUTES
    // This node represents the destination node for this way.
    private Node node;
    // This is the cost or distance to get to the node.
    private int cost;
    // This represents the pheromones that are in this way.
    private double pheromones;

    // CONSTRUCTOR
    public Way() {
        this.cost = 0; // This can change to get automatically the euclidian distance.
        this.pheromones = 0.1;
    }

    // GETTERS AND SETTERS
    public Node getNode() {
        return this.node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getPheromones() {
        return this.pheromones;
    }

    public void setPheromones(double pheromones) {
        this.pheromones = pheromones;
    }
    
}
