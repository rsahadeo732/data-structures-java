package trick;

import java.util.*;

public class CanAvoidCurfew {

    private Graph graph;
    private Set<String> visited;
    private HashMap<String, Integer> distance;
    private HashMap<String, String> predecessors;

    public CanAvoidCurfew(){
        this.graph = new Graph();
        this.visited = new HashSet<>();
        this.distance = new HashMap<>();
        this.predecessors = new HashMap<>();
    }

    public void run(String inputFile, String sourceHouse,
    String desHouse, int curfew, String outputFile){
        StdIn.setFile(inputFile);
        StdOut.setFile(outputFile);

        int numHouses = StdIn.readInt();
        for(int i = 0; i< numHouses; i++){
            String houseName = StdIn.readString();
            int numCandies = StdIn.readInt();
            graph.addVertex(houseName);

            for(int j = 0; j< numCandies; j++){
                String candyName = StdIn.readString();
                int candyQuantity = StdIn.readInt();
                graph.addCandy(houseName, candyName, candyQuantity);
            }
        }
        int numedges = StdIn.readInt();
        for(int i = 0; i< numedges; i++){
            String house1 = StdIn.readString();
            String house2 = StdIn.readString();
            int weight = StdIn.readInt();
            graph.addEdge(house1, house2, weight);
        }
        dijkstra(sourceHouse);

        int distanceDestination = distance.getOrDefault(desHouse, Integer.MAX_VALUE);

        boolean canAvoidCurfew = distanceDestination <= curfew;

        StdOut.println(canAvoidCurfew + " " + distanceDestination);
    }

    private void dijkstra(String source){
        for(String house: graph.getHouseNames()){
            distance.put(house, Integer.MAX_VALUE);
            predecessors.put(house, null);
        }
        distance.put(source, 0);

        PriorityQueue<String> pq = new
        PriorityQueue<>(Comparator.comparingInt(distance::get));
        pq.add(source);

        while(!pq.isEmpty()){
            String currentHouse = pq.poll();
            int currentDistance = distance.get(currentHouse);

            if(currentDistance > distance.get(currentHouse)){
                continue;
            }
            for(House neighbor: graph.adj(currentHouse)){
                String neighborName = neighbor.getName();
                int newDistance = currentDistance + neighbor.getWeight();

                if(newDistance<distance.get(neighborName)){
                    distance.put(neighborName, newDistance);
                    predecessors.put(neighborName, currentHouse);
                    pq.add(neighborName);
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 5) {
            StdOut.println(
                    "Too few arguments. Did you put in command line arguments? If using the debugger, add args to launch.json.");
            StdOut.println(
                    "Execute: java -cp bin trick.CanAvoidCurfew input1.in h1 h8 100 shortestpaths1.out");
            return;
        }

        CanAvoidCurfew canAvoidCurfew = new CanAvoidCurfew();
        canAvoidCurfew.run(args[0], args[1], args[2], Integer.parseInt(args[3]), args[4]);
        

       // WRITE YOUR CODE HERE
        
    }
}
