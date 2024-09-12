import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Ants {
    
    // ATTRIBUTES
    private Graph graph;
    private int size;
    private Node origin;
    private Node objective;
    private List<Route> routes;
    private int[] available;
    private Random random;

    // CONSTRUCTOR
    public Ants(Graph graph) {
        this.graph = graph;
        this.size = this.graph.getNodes().size();
        this.origin = graph.getNodes().get(0);
        this.objective = graph.getNodes().get(this.size - 1);
        this.available = new int[this.size];
        this.random = new Random();
    }

    // This method is used to generate n ants routes.
    public void generateRoutes(int n) {

        // List of routes for all the ants.
        this.routes = new ArrayList<>();
        
        // Cycle to generate n ant routes.
        for (int i = 0; i < n; i++) {

            // Create the route.
            Route route = createRoute(this.origin);
            System.out.println("Route: " + i);

            // Update ways pheromones.
            this.graph.updatePheromones(route);

            // Add the route to the routes list.
            this.routes.add(route);

        }
        
    }

    // This method is used to create each ant route.
    private Route createRoute(Node actualNode) {

        // Generate a new route.
        Route route = new Route();

        // Adding the origin node.
        route.getNodes().add(actualNode);

        // Reset available nodes, to not repeat nodes in the route.
        resetAvailable();

        // Cycle to iterate for all the nodes of the graph (less origin and objective).
        for (int i = 0; i < this.size - 2; i++) {

            // We'll select the next node for this route.
            Node nextNode = selectRandomNode(actualNode);

            /*
            We need to search for the way to get to the next node from the actual node,
            and adding the way and the node to the route.
            */
            route.addWay(actualNode.getWayTo(nextNode));
            route.getNodes().add(nextNode);
            
            // We'll update the actual node for the next iteration.
            actualNode = nextNode;

        }

        // Finally, adding the objective node and the way to get there.
        route.addWay(actualNode.getWayTo(objective));
        route.getNodes().add(objective);

        return route;
    }

    // This method will reset te info in available array, 1 for available, 0 for not.
    private void resetAvailable() {

        // 1 for all the nodes.
        Arrays.fill(this.available, 1);

        // 0 for origin and objective.
        this.available[0] = 0;
        this.available[this.available.length - 1] = 0;

    }

    // This method is used to choose a random node with probabilities.
    private Node selectRandomNode(Node node) {

        // We'll get the probabilities,
        double[] probabilities = node.getProbabilities();

        // This lists will contain info for available nodes and their probabilities.
        List<Node> availableNodes = new ArrayList<>();
        List<Double> availableProbabilities = new ArrayList<>();

        // Here we add available info.
        for (int i = 0; i < probabilities.length; i++) {
            if (this.available[i] != 0) {
                availableNodes.add(this.graph.getNodes().get(i));
                availableProbabilities.add(probabilities[i]);
            }
        }

        // If the available probabilities sum are not 1 we need to normalize probabilities.
        double[] normalizedProbabilities = normalizeProbabilities(availableProbabilities);

        // New random between 0 and 1.
        double rand = this.random.nextDouble();

        // Counter for probabilities.
        double count = 0;

        for (int i = 0; i < normalizedProbabilities.length; i++) {

            // Here we sum the probabilities
            count += normalizedProbabilities[i];

            // We'll select the node in the iteration when random is <= count.
            if (rand <= count){
                Node selectedNode = availableNodes.get(i);
                this.available[graph.getNodes().indexOf(selectedNode)] = 0;
                return selectedNode;
            }
        }

        return null;
    }

    // Method to normalize probabilities.
    private double[] normalizeProbabilities(List<Double> availableProbabilities) {
        
        // Here we use stream to sum the available probabilities.
        double totalProbability = availableProbabilities.stream().mapToDouble(Double::doubleValue).sum();
        
        double[] normalizedProbabilities = new double[availableProbabilities.size()];

        // Each probability is normalized when we divide it for the total.
        for (int i = 0; i < availableProbabilities.size(); i++) {
            normalizedProbabilities[i] = availableProbabilities.get(i) / totalProbability;
        }

        return normalizedProbabilities;
    }

    // Print in console.
    public void printRoutes() {
        System.out.println("Routes:");
        System.out.println("");
        for (int i = 0; i < this.routes.size(); i++) {
            System.out.println("Route " + i + ": ");
            System.out.println("");
            routes.get(i).printNodes();
            System.out.println("Total cost: " + routes.get(i).getTotalCost());
        }
        System.out.println("");
    }

    // Print the route with minimun cost obtained.
    public void printBestRoute() {

        double min = this.routes.get(0).getTotalCost();

        for (Route route : this.routes) {
            if (route.getTotalCost() < min){
                min = route.getTotalCost();
            }
        }

        System.out.println("Best route: " + min);
    }

    // GETTERS AND SETTERS
    public Graph getGraph() {
        return graph;
    }

}