package trick;

import java.util.Map.Entry;
import java.util.*;


public class ShortestPath {

    private Graph graph;
    private Map<String, Integer> distance;
    private Map<String, String> predecessor;
    private Set<String> done;
    private Set<String> fringe;

    public ShortestPath(){
        this.graph = new Graph();
        this.distance = new HashMap<>();
        this.predecessor = new HashMap<>();
        this.done = new HashSet<>();
        this.fringe = new HashSet<>();
    }

    public void findShortestPaths(String source){
        for(String house : graph.getHouseNames()){
            distance.put(house, Integer.MAX_VALUE);
            predecessor.put(house, null);
            fringe.add(house);
        }
        distance.put(source, 0);
        while(!fringe.isEmpty()){
            String m = getMinDistanceHouse();

            fringe.remove(m);
            done.add(m);

            for(House neighbor: graph.adj(m)){
                String neighborHouse = neighbor.getName();
                if(!done.contains(neighborHouse)){
                    int newDist = distance.get(m) + neighbor.getWeight();
                    if(newDist < distance.get(neighborHouse)){
                        distance.put(neighborHouse, newDist);
                        predecessor.put(neighborHouse, m);
                    }
                }
            }
        }

        for(String house: graph.getHouseNames()){
            String pred = predecessor.get(house) == null ?
            "null" : predecessor.get(house);
            StdOut.println(house + " " + pred);
        }
    }

    private String getMinDistanceHouse(){
        String minHouse = null;
        int minDistance = Integer.MAX_VALUE;
        for(String house: fringe){
            if(distance.get(house) < minDistance){
                minDistance = distance.get(house);
                minHouse = house;
            }
        }
        return minHouse;
    }

    public Integer getDistance(String houseName){
        return distance.get(houseName);
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            StdOut.println(
                    "Too few arguments. Did you put in command line arguments? If using the debugger, add args to launch.json.");
            StdOut.println(
                    "Execute: java -cp bin trick.ShortestPath input1.in h1 shortestpaths1.out");
            return;
        }

        String inputFile = args[0];
        String sourceHouse = args[1];
        String outputFile = args[2];
        StdIn.setFile(inputFile);
        StdOut.setFile(outputFile);

        ShortestPath sp = new ShortestPath();
        int numHouses = StdIn.readInt();
        for(int i = 0; i<numHouses; i++){
            String houseName = StdIn.readString();
            int numCandies = StdIn.readInt();
            sp.graph.addVertex(houseName);

            for(int j = 0; j < numCandies; j++){
                String candyName = StdIn.readString();
                int candyQuantity = StdIn.readInt();
                sp.graph.addCandy(houseName, candyName, candyQuantity);
            }
        }
        int numEdges = StdIn.readInt();
        for(int i = 0; i<numEdges; i++){
            String house1 = StdIn.readString();
            String house2 = StdIn.readString();
            int weight = StdIn.readInt();
            sp.graph.addEdge(house1, house2, weight);
        }
        sp.findShortestPaths(sourceHouse);
        
       // WRITE YOUR CODE HERE
    }

}








