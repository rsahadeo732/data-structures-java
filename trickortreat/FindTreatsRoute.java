package trick;

import java.util.*;


public class FindTreatsRoute {
    public static void dfs(String house, Set<String> visited,
    List<String> visitedHouses, Graph graph){
        //mark current as visited
        visited.add(house);
        //add to visited house
        visitedHouses.add(house);
        //adjacent houses
        List<House> neighbors = graph.adj(house);
        //StdOut.println("Visiting house; " + house + "with neighbors:" + neighbors);

        //visits unvistied houses
        for(House neighbor : neighbors){
            if(!visited.contains(neighbor.getName())){
                dfs(neighbor.getName(), visited, visitedHouses, graph);
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            StdOut.println(
                    "Too few arguments. Did you put in command line arguments? If using the debugger, add args to launch.json.");
            StdOut.println(
                    "Run command: java -cp bin trick.FindTreatsRoute input1.in h1 findtreats1.out");
            return;
        }

        String inputFile = args[0];
        String sourceHouse = args[1];
        String outputFile = args[2];

        StdIn.setFile(inputFile);
        StdOut.setFile(outputFile);

        Graph graph = new Graph();

        int numHouses = StdIn.readInt();
        //StdOut.println("Number of houses: " + numHouses);

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
        int numEdges = StdIn.readInt();
        for(int i = 0; i < numEdges; i++){
            String house1 = StdIn.readString();
            String house2 = StdIn.readString();
            int weight = StdIn.readInt();
            graph.addEdge(house1, house2, weight);
        }
        Set<String> visited = new HashSet<>();
        List<String> visitedHouses = new ArrayList<>();
        dfs(sourceHouse, visited, visitedHouses, graph);
        StdOut.println(String.join(" ", visitedHouses));
        
       // WRITE YOUR CODE HERE
        
    }
}
