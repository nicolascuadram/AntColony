import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    // ATTRIBUTES
    private List<Node> nodes;
    private List<Way> ways;

    // CONSTRUCTOR
    public Graph () {
        this.nodes = loadGraphWithFile();
        this.ways = generateWays();
    }
    
    private List<Node> loadGraphWithFile() {

        // Array to save nodes.
        List<Node> nodes = new ArrayList<>();

        // Read the file.
        try (BufferedReader br = new BufferedReader(new FileReader("../tsp/a280.tsp"))) {

            // Variable for each line.
            String line;
            // Variable to find lines with nodes info.
            boolean nodeLine = false;

            // We'll review each line of the file.
            while ((line = br.readLine()) != null) {

                // Remove spaces.
                line = line.trim();

                // If we find the "NODE_COORD_SECTION" line we'll begin nodes creation.
                if (line.equals("NODE_COORD_SECTION")) {
                    nodeLine = true;
                    continue;
                }

                // If we find the "EOF" line we'll finish the nodes creation.
                if (line.equals("EOF")) {
                    break;
                }

                // If nodeLine is true we are in the "NODE_COORD_SECTION", so we'll create nodes.
                if (nodeLine) {

                    // Divide the line info in parts with the regex "\\s+" (means one or more white spaces).
                    String[] parts = line.split("\\s+");

                    try {

                        // Parse line parts to integer.
                        int id = Integer.parseInt(parts[0]);
                        int x = Integer.parseInt(parts[1]);
                        int y = Integer.parseInt(parts[2]);

                        // Here we add the node.
                        nodes.add(new Node(id, x, y));

                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return nodes;
    }

    private List<Way> generateWays() {

        List<Way> ways = new ArrayList<>();

        for (int i = 0; i < this.nodes.size(); i++) {

            // Here we get the node where the way will start.
            Node from = this.nodes.get(i);
    
            for (int j = i + 1; j < this.nodes.size(); j++) {

                // Here we get the node where the path will end.
                Node to = this.nodes.get(j);
        
                // We create the way and add it to the nodes.
                Way way = new Way(from, to);
                from.getWays().add(way);
                to.getWays().add(way);

                // Also we add the way to the graph.
                ways.add(way);

            }

        }

        return ways;
    }

    public void updatePheromones(double evaporationRate, Route route) {
        // evaporationRate = 0.01
        for (Way way : this.ways) {

            double evaporation = ((1 - evaporationRate) * way.getPheromones());
            double pheromonesContribution = 0;

            if (route.getWays().contains(way)) {
                pheromonesContribution = (1 / route.getTotalCost());
            }
            
            way.setPheromones(evaporation + pheromonesContribution);
        }
    }

    public void printNodes() {
        for (Node node : this.nodes) {
            System.out.println("ID: " + node.getId() + ", X: " + node.getX() + ", Y: " + node.getY());
        }
    }

    // GETTERS AND SETTERS
    public List<Node> getNodes() {
        return this.nodes;
    }

    public List<Way> getWays() {
        return this.ways;
    }

}
