package trick;

import java.util.*;

public class FindHouseWithMostCandy {

    private Graph graph;
    private Set<String> visited;
    private List<String> visitedHouses;
    

    public FindHouseWithMostCandy(){
        this.graph = new Graph();
        this.visited = new HashSet<>();
        this.visitedHouses = new ArrayList<>();
        
    }

    public void run(String inputFile, String sourceHouse, String candyType,
    String outputFile){
        StdIn.setFile(inputFile);
        StdOut.setFile(outputFile);

        int numHouse = StdIn.readInt();
        for(int i = 0; i< numHouse; i++){
            String houseName = StdIn.readString();
            int numCandies = StdIn.readInt();
            graph.addVertex(houseName);

            for(int j = 0; j< numCandies; j++){
                String candyName = StdIn.readString();
                int candyQuantity = StdIn.readInt();
                graph.addCandy(houseName, candyName, candyQuantity);
            }
        }
        int numEdges = StdIn.readInt();
        for(int i = 0; i < numEdges; i++){
            String house1= StdIn.readString();
            String house2 = StdIn.readString();
            int weight = StdIn.readInt();
            graph.addEdge(house1, house2, weight);
        }
        dfs(sourceHouse);

        String bestHouse = findHouseWithMostCandy(candyType);

        StdOut.println(bestHouse);
    }
    private void dfs(String house){
        visited.add(house);
        visitedHouses.add(house);

        List<House> neighbors = graph.adj(house);
        for(House neighbor : neighbors){
            if(!visited.contains(neighbor.getName())){
                dfs(neighbor.getName());
            }
        }
    }
    private String findHouseWithMostCandy(String candyType){
        String bestHouse = null;
        int maxCandyCount = -1;

        for(String house: visitedHouses){
            HashMap<String, Integer> inventory = graph.getCandyCount(house);
            if(inventory != null){
                int candyCount = inventory.getOrDefault(candyType, 0);
                if(candyCount > maxCandyCount ){
                    maxCandyCount = candyCount;
                    bestHouse = house;
                }
            }
        }
        return bestHouse;
    }
    public static void main(String[] args) {
        if (args.length < 4) {
            StdOut.println(
                    "Too few arguments. Did you put in command line arguments? If using the debugger, add args to launch.json.");
            StdOut.println(
                    "Execute: java -cp bin trick.FindHouseWithMostCandy input1.in h1 Skittles findcandy1.out");
            return;
        }

        FindHouseWithMostCandy find = new FindHouseWithMostCandy();
        find.run(args[0], args[1], args[2], args[3]);

       
       // WRITE YOUR CODE HERE
        
    }
}
