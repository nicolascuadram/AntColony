import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    // ATTRIBUTES
    private List<Node> nodes;

    // CONSTRUCTOR
    public Graph () {
        this.nodes = loadGraphWithFile();
        generateWays();
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

                    // Divide the line info in parts.
                    String[] parts = line.split(" ");

                    // Parse line parts to integer.
                    int id = Integer.parseInt(parts[0]);
                    int x = Integer.parseInt(parts[1]);
                    int y = Integer.parseInt(parts[2]);

                    // Here we add the node.
                    nodes.add(new Node(id, x, y));

                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return nodes;
    }

    private void generateWays() {

        for (int i = 0; i < this.nodes.size(); i++) {

            // Here we get the node where the way will start.
            Node from = this.nodes.get(i);
    
            for (int j = i + 1; j < this.nodes.size(); j++) {

                // Here we get the node where the path will end.
                Node to = this.nodes.get(j);
    
                // Calculate the Euclidean distance between nodes.
                int distance = (int) Math.sqrt(Math.pow(from.getX() - to.getX(), 2) + Math.pow(from.getY() - to.getY(), 2));
    
                // We create the way and add it to the "from" node.
                from.getWays().add(new Way(to, distance));
    
                // We create the reverse path and add it to the "to" node.
                to.getWays().add(new Way(from, distance));

            }
        }
    }

    // GETTERS AND SETTERS
    public List<Node> getNodes() {
        return this.nodes;
    }

}
