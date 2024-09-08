import java.util.ArrayList;
import java.util.List;

public class Node {

    // ATTRIBUTES
    // Identifier for the Node.
    private int id;
    // Coordinate X
    private int x;
    // Coordinate Y
    private int y;
    // List of ways from this Node to others.
    private List<Way> ways;

    // CONSTRUCTOR
    public Node(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.ways = new ArrayList<>();
    }

    // Method to obtain the probability that an ant has for choosing each path.
    public double[] getProbabilities() {

        // We initialize an array for ways probabilities, and the summation.
        double[] probabilities = new double[this.ways.size()];
        double totalProbability = 0;

        /*
        First, we'll calculate the probability for each way, the probability is:
        (visibility * pheromones) / SUM(visibility * pheromones)
        The SUM includes all the probabilities ways for this node, so in this first cycle,
        we'll calculate (visibility * pheromones) and get the SUM.
        */
        for (int i = 0; i < this.ways.size(); i++) {
            probabilities[i] = calcProbability(this.ways.get(i));
            totalProbability = totalProbability + probabilities[i];
        }

        /*
        In this second cycle we already have (visibility * pheromones) and the SUM,
        so we'll divide all the probabilities for the SUM.
        */
        for (int i = 0; i < this.ways.size(); i++) {
            probabilities[i] = (probabilities[i] / totalProbability);
        }

        return probabilities;
    }

    /*
    Method to calculate (visibility * pheromones), visibility is (1 / cost), so we'll
    simplify the operation doing (pheromones / cost).
    */
    private double calcProbability(Way way) {
        return (way.getPheromones() / way.getCost());
    }

    // GETTERS AND SETTERS
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Way> getWays() {
        return this.ways;
    }

}
