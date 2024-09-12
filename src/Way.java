public class Way {

    // ATTRIBUTES
    // These are the nodes in this way.
    private Node node1;
    private Node node2;
    // This is the cost or distance in the way.
    private double cost;
    // This represents the pheromones that are in this way.
    private double pheromones;

    // CONSTRUCTOR
    public Way(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
        this.cost = euclidianDistance();
        this.pheromones = 0.1;
    }

    // Distance between nodes with Euclidian distance.
    private double euclidianDistance() {
        return Math.sqrt(Math.pow(this.node1.getX() - this.node2.getX(), 2) + Math.pow(this.node1.getY() - this.node2.getY(), 2));
    }

    // GETTERS AND SETTERS
    public Node getNode1() {
        return this.node1;
    }

    public Node getNode2() {
        return this.node2;
    }

    public double getCost() {
        return this.cost;
    }

    public double getPheromones() {
        return this.pheromones;
    }

    public void setPheromones(double pheromones) {
        this.pheromones = pheromones;
    }
    
}
