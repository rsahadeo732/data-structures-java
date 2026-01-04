package trick;


public class NeighborhoodMap {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println(
                    "Too few arguments. Did you put in command line arguments? If using the debugger, add args to launch.json.");
                    System.out.println(
                    "Execute: java -cp bin trick.NeighborhoodMap <neighborhoodmap INput file> <neighborhoodmap OUTput file>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];
        StdIn.setFile(inputFile);
        StdOut.setFile(outputFile);

        Graph graph = new Graph();
        int numHouses = StdIn.readInt();

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

        for(int i = 0; i< numEdges; i++){
            String house1 = StdIn.readString();
            String house2 = StdIn.readString();
            int weight = StdIn.readInt();

            graph.addEdge(house1, house2, weight);
        }
        graph.displayGraphAndCandy();
        
    }
}
