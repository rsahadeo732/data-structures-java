package trick;

import java.util.*;


public class PathToMostCandy {
    public static void main(String[] args) {
        if (args.length < 4) {
            StdOut.println(
                    "Too few arguments. Did you put in command line arguments? If using the debugger, add args to launch.json.");
            StdOut.println(
                    "execute: java -cp bin trick.PathToMostCandy input1.in h1 KitKat mostcandy1.out");
            return;
        }

        //read arguments
        String inputFile = args[0];
        String sourceHouse = args[1];
        String candyType = args[2];
        String outputFile = args[3];

        StdIn.setFile(inputFile);
        StdOut.setFile(outputFile);

        Graph graph = new Graph();
        int numHouses = StdIn.readInt();

        //build the graph
        for(int i = 0; i < numHouses; i++){
            String houseName = StdIn.readString();
            int numCandies = StdIn.readInt();
            graph.addVertex(houseName);

            for(int j = 0; j < numCandies; j++){
                String candyName = StdIn.readString();
                int candyQuantity = StdIn.readInt();
                graph.addCandy(houseName, candyName, candyQuantity);
            }
        }
        //add edges to graph
        int numEdges = StdIn.readInt();
        for(int i = 0; i < numEdges; i++){
            String house1 = StdIn.readString();
            String house2 = StdIn.readString();
            int weight = StdIn.readInt();
            graph.addEdge(house1, house2, weight);
        }

        //bfs for shortest path
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        Map<String, String> edgeTo = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        queue.add(sourceHouse);
        visited.add(sourceHouse);
        distance.put(sourceHouse, 0);

        while(!queue.isEmpty()){
            String currentHouse = queue.poll();
            List<House> neighbors = graph.adj(currentHouse);
            for(House neighbor : neighbors){
                String neighborHouse = neighbor.getName();
                if(!visited.contains(neighborHouse)){
                    visited.add(neighborHouse);
                    queue.add(neighborHouse);
                    edgeTo.put(neighborHouse, currentHouse);
                    distance.put(neighborHouse, distance.get(currentHouse) + 1);
                }
            }
        }
        String targetHouse = null;
        int maxCandy = -1;

        for(String house : graph.getHouseNames()){
            HashMap<String, Integer> inventory = graph.getCandyCount(house);
            if(inventory != null){
                int candyCount = inventory.getOrDefault(candyType, 0);
                if(candyCount > maxCandy){
                    maxCandy = candyCount;
                    targetHouse = house;
                }
            }
        }
        List<String> path = new ArrayList<>();
        for(String house = targetHouse; house != null; house = edgeTo.get(house)){
            path.add(house);
        }

        Collections.reverse(path);
        StdOut.println(String.join(" ", path));

       // WRITE YOUR CODE HERE
        
    }
}
