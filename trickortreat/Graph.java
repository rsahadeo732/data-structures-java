package trick;

import java.util.*;


public class Graph {
    private HashMap<String, House> adjacencyList;
    private HashMap<String, HashMap<String, Integer>> candyInventory;

    //constructor
    public Graph(){
        adjacencyList = new HashMap<>();
        candyInventory = new HashMap<>();
    }

    public Set<String> getHouseNames(){
        return adjacencyList.keySet();
    }


    public HashMap<String, Integer> getCandyCount(String houseName){
        return candyInventory.get(houseName);
    }

    //add house to graph
    public void addVertex(String houseName){
        if(!adjacencyList.containsKey(houseName)){
            adjacencyList.put(houseName, new House(houseName, 0));
            candyInventory.put(houseName, new HashMap<>());
        }
    }

    //add edge
    public void addEdge(String house1, String house2, int weight){
        if(house1.equals(house2)){
            throw new IllegalArgumentException("Cannot create an edge " +
            "between house and itself");
        }
        addVertex(house1);
        addVertex(house2);

        House edge1 = new House(house2, weight);
        House current1 = adjacencyList.get(house1);

        while(current1.getNext() != null){
            current1 = current1.getNext();
        }
        current1.setNext(edge1);

        House edge2 = new House(house1, weight);
        House current2 = adjacencyList.get(house2);
        while(current2.getNext() != null){
            current2 = current2.getNext();
        }
        current2.setNext(edge2);
    }

    public void addCandy(String houseName, String candyName, int count){
        if(!candyInventory.containsKey(houseName)){
            System.out.println("Error: House " + houseName + "does not exist!");
            return;
        }
        HashMap<String, Integer> inventory = candyInventory.get(houseName);
        inventory.put(candyName, inventory.getOrDefault(candyName, 0) + count);
    }

    public List<House> adj(String houseName){
        List<House> neighbors = new ArrayList<>();
        House current = adjacencyList.get(houseName).getNext();
        while(current != null){
            neighbors.add(current);
            current = current.getNext();
        }
        return neighbors;
    }

    //display graph
    public void displayGraphAndCandy() {
        for(String house : candyInventory.keySet()){
            HashMap<String, Integer> inventory = candyInventory.get(house);

            List<String> candyList = new ArrayList<>();
            for(Map.Entry<String, Integer> entry : inventory.entrySet()){
                candyList.add(entry.getKey() + " " + entry.getValue());
            }

            StdOut.println(house + " "+ String.join(" ", candyList));
        }
        for(String house : adjacencyList.keySet()){
            House current = adjacencyList.get(house).getNext();

            List<String> adjacencyLine = new ArrayList<>();
            while(current != null){
                adjacencyLine.add(current.getName()+ " "+ current.getWeight());
                current = current.getNext();
            }
            if(!adjacencyLine.isEmpty()){
                StdOut.print(house + " ");
                StdOut.println(String.join(" ", adjacencyLine));
            }
        }
    }
}

