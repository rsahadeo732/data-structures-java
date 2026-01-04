package trick;

import java.util.*;

public class UnifiedGraphTest {
    public static void main(String[] args){
        testGraphStructure();
        testAddEdge();
        testCandyInventory();
    }

    public static void testGraphStructure(){
        System.out.println("=== Test: Graph Structure ===");

        Graph graph = new Graph();
        graph.addVertex("h1");
        graph.addVertex("h2");
        graph.addVertex("h3");

        graph.displayGraphAndCandy();

        System.out.println();
    }

    //test adding edges
    public static void testAddEdge(){
        System.out.println("=== Test: Add Edges ===");

        Graph graph = new Graph();
        graph.addEdge("h1", "h2", 5);
        graph.addEdge("h1", "h3", 10);
        graph.addEdge("h2", "h4", 2);

        graph.displayGraphAndCandy();
        System.out.println();
    }

    //test Candy inventory
    public static void testCandyInventory(){
        System.out.println("=== Test: Candy Inventory ===");

        Graph graph = new Graph();
        graph.addVertex("h1");
        graph.addVertex("h2");

        graph.addCandy("h1", "KitKat", 10);
        graph.addCandy("h1", "Skittles", 5);
        graph.addCandy("h2", "M&Ms", 20);

        graph.displayGraphAndCandy();
        System.out.println();
    }
    
}
