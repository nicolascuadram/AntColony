import java.util.ArrayList;
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

    // CONSTRUCTOR
    public Ants(Graph graph) {
        this.graph = graph;
        this.size = this.graph.getNodes().size();
        this.origin = graph.getNodes().get(0);
        this.objective = graph.getNodes().get(this.size - 1);
        this.available = new int[this.size];
    }

    public void generateRoutes(int n) {

        // List of routes for all the ants.
        this.routes = new ArrayList<>();
        
        // Cycle to generate n ant routes.
        for (int i = 0; i < n; i++) {

            // Create the route.
            Route route = createRoute(this.origin);

            // Update ways pheromones.
            this.graph.updatePheromones(route);

            // Add the route to the routes list.
            this.routes.add(route);

        }
        
    }

    public void generateRoutesForCost(int n) {

        this.routes = new ArrayList<>();

        Route route;
        do {

            // Create the route.
            route = createRoute(this.origin);

            // Update ways pheromones.
            this.graph.updatePheromones(route);

            // Add the route to the routes list.
            this.routes.add(route);
            
        }
        while (route.getTotalCost() > n);

    }

    private Route createRoute(Node actualNode) {

        // Generate a new route.
        Route route = new Route();

        // Adding the origin node.
        route.getNodes().add(actualNode);

        // Reset available nodes.
        resetAvailable();

        // Cycle to iterate for all the nodes of the graph (less origin and objective).
        for (int i = 0; i < this.size - 2; i++) {

            // We'll select the next node for this route.
            Node nextNode = selectRandomNode(actualNode);
            //System.out.println(nextNode.getId());

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

    private void resetAvailable() {

        this.available[0] = 0;

        for (int i = 1; i < this.available.length - 1; i++) {
            this.available[i] = graph.getNodes().get(i).getId();
        }

        this.available[this.available.length - 1] = 0;
    }

    private Node selectRandomNode(Node node) {

        double[] probabilities = node.getProbabilities();

        List<Node> availableNodes = new ArrayList<>();
        List<Double> availableProbabilities = new ArrayList<>();

        for (int i = 0; i < probabilities.length; i++) {
            if (this.available[i] != 0) {
                availableNodes.add(this.graph.getNodes().get(i));
                availableProbabilities.add(probabilities[i]);
            }
        }

        double[] normalizedProbabilities = normalizeProbabilities(availableProbabilities);

        double random = new Random().nextDouble();
        double count = 0;

        for (int i = 0; i < normalizedProbabilities.length; i++) {

            count += normalizedProbabilities[i];

            if (random <= count){
                Node selectedNode = availableNodes.get(i);
                this.available[graph.getNodes().indexOf(selectedNode)] = 0;
                return selectedNode;
            }
        }
        System.out.println("null node, " + count + ", " + random);
        return null;
    }

    private double[] normalizeProbabilities(List<Double> availableProbabilities) {
        double totalProbability = availableProbabilities.stream().mapToDouble(Double::doubleValue).sum();
        double[] normalizedProbabilities = new double[availableProbabilities.size()];
        for (int i = 0; i < availableProbabilities.size(); i++) {
            normalizedProbabilities[i] = availableProbabilities.get(i) / totalProbability;
        }
        return normalizedProbabilities;
    }

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

    public void printBestAndLastRoutes() {
        double min = this.routes.get(0).getTotalCost();
        for (Route route : this.routes) {
            if (route.getTotalCost() < min){
                min = route.getTotalCost();
            }
        }
        System.out.println("Best route: " + min);
        System.out.println("Last Route: " + this.routes.get(this.routes.size() - 1).getTotalCost());
    }

    public void printRoutesCount() {
        System.out.println("Number of Ants Routes: " + this.routes.size());
    }

    // GETTERS AND SETTERS
    public Graph getGraph() {
        return graph;
    }

}