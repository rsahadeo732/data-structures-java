package venom;

import java.util.ArrayList;

/**
 * The Venom class represents a binary search tree of SymbioteHost objects.
 * Venom is a sentient alien symbiote with a liquid-like form that requires a
 * host to bond with for its survival. The host is granted superhuman abilities
 * and the symbiote gains a degree of autonomy. The Venom class contains methods
 * that will help venom find the most compatible host. You are Venom.
 * 
 * @author Ayla Muminovic
 * @author Shane Haughton
 * @author Elian D. Deogracia-Brito
 */
public class Venom {
    private SymbioteHost root;

    /**
     * DO NOT EDIT THIS METHOD
     * 
     * Default constructor.
     */
    public Venom() {
        root = null;
    }

    /**
     * This method is provided to you
     * Creates an array of SymbioteHost objects from a file. The file should
     * contain the number of people on the first line, followed by the name,
     * compatibility, stability, and whether they have antibodies on each
     * subsequent line.
     * 
     * @param filename the name of the file
     * @return an array of SymbioteHosts (should not contain children)
     */
    public SymbioteHost[] createSymbioteHosts(String filename) {
        // DO NOT EDIT THIS METHOD
        StdIn.setFile(filename);
        int numOfPeople = StdIn.readInt();
        SymbioteHost[] people = new SymbioteHost[numOfPeople];
        for (int i = 0; i < numOfPeople; i++) {
            StdIn.readLine();
            String name = StdIn.readLine();
            int compatibility = StdIn.readInt();
            int stability = StdIn.readInt();
            boolean hasAntibodies = StdIn.readBoolean();
            SymbioteHost newPerson = new SymbioteHost(name, compatibility, stability, hasAntibodies, null, null);
            people[i] = newPerson;
        }
        return people;
    }

    /**
     * Inserts a SymbioteHost object into the binary search tree.
     * 
     * @param symbioteHost the SymbioteHost object to insert
     */
    public void insertSymbioteHost(SymbioteHost symbioteHost) {
        // WRITE YOUR CODE HERE

        //If root is null, make new node the root
        if( root == null){
            root = symbioteHost;
            return;
        }
        //Recursion
        root = insert(root, symbioteHost);
    }
    private SymbioteHost insert(SymbioteHost current, SymbioteHost symbioteHost){
        //If current is null, insert new node
        if( current == null){
            return symbioteHost;
        }
        //Compare names
        int cmp = symbioteHost.getName().compareTo(current.getName());
        if(cmp < 0){
            current.setLeft(insert(current.getLeft(), symbioteHost));
        }else if(cmp > 0){
            current.setRight(insert(current.getRight(), symbioteHost));
        }else{
            current.setSymbioteCompatibility
            (symbioteHost.getSymbioteCompatibility());
            current.setMentalStability(symbioteHost.getMentalStability());
            current.setHasAntibodies(symbioteHost.hasAntibodies());
        }
        return current;
    }

    /**
     * Builds a binary search tree from an array of SymbioteHost objects.
     * 
     * @param filename filename to read
     */
    public void buildTree(String filename) {
        // WRITE YOUR CODE HERE
        SymbioteHost [] hosts = createSymbioteHosts(filename);
        for(SymbioteHost host : hosts){
            insertSymbioteHost(host);
        }
    }

    /**
     * Finds the most compatible host in the tree. The most compatible host
     * is the one with the highest suitability.
     * PREorder traversal is used to traverse the tree. The host with the highest suitability
     * is returned. If the tree is empty, null is returned.
     * 
     * USE the calculateSuitability method on a SymbioteHost instance to get
     * a host's suitability.
     * 
     * @return the most compatible SymbioteHost object
     */
    public SymbioteHost findMostSuitable() {
        // WRITE YOUR CODE HERE
        return findMostSuitable(root, null); // UPDATE this line, provided so code compiles
    }
    private SymbioteHost findMostSuitable(SymbioteHost current,
    SymbioteHost host){
        if( current == null){
            return host;
        }
        int currentSuitability = current.calculateSuitability();
        if(host == null || currentSuitability > host.calculateSuitability()){
            host = current;
        }
        host = findMostSuitable(current.getLeft(), host);
        host = findMostSuitable(current.getRight(), host);
        return host;
    }

    /**
     * Finds all hosts in the tree that have antibodies. INorder traversal is used to
     * traverse the tree. The hosts that have antibodies are added to an
     * ArrayList. If the tree is empty, null is returned.
     * 
     * @return an ArrayList of SymbioteHost objects that have antibodies
     */
    public ArrayList<SymbioteHost> findHostsWithAntibodies() {
        // WRITE YOUR CODE HERE
        ArrayList<SymbioteHost> hostsWithAntibodies = new ArrayList<>();
        findHostsWithAntibodies(root, hostsWithAntibodies);
        return hostsWithAntibodies; // UPDATE this line, provided so code compiles
    }
    private void findHostsWithAntibodies(SymbioteHost current,
    ArrayList<SymbioteHost> antiBodies){
        if (current == null){
            return;
        }
        findHostsWithAntibodies(current.getLeft(), antiBodies);
        if( current.hasAntibodies()){
            antiBodies.add(current);
        }
        findHostsWithAntibodies(current.getRight(), antiBodies);
    }

    /**
     * Finds all hosts in the tree that have a suitability between the given
     * range. The range is inclusive. Level order traversal is used to traverse the tree. The
     * hosts that fall within the range are added to an ArrayList. If the tree
     * is empty, null is returned.
     * 
     * @param minSuitability the minimum suitability
     * @param maxSuitability the maximum suitability
     * @return an ArrayList of SymbioteHost objects that fall within the range
     */
    public ArrayList<SymbioteHost> findHostsWithinSuitabilityRange(int minSuitability, int maxSuitability) {
        ArrayList<SymbioteHost> suitableHosts = new ArrayList<>();
        if(root == null){
            return suitableHosts;
        }

        Queue<SymbioteHost> queue = new Queue<>();
        queue.enqueue(root);
        while(!queue.isEmpty()){
            SymbioteHost current = queue.dequeue();
            int suitability = current.calculateSuitability();
            if(suitability >= minSuitability && suitability <= maxSuitability){
                suitableHosts.add(current);
            }
            if(current.getLeft() != null){
                queue.enqueue(current.getLeft());
            }
            if(current.getRight() != null){
                queue.enqueue(current.getRight());
            }
        }
        return suitableHosts; // UPDATE this line, provided so code compiles
    }

    /**
     * Deletes a node from the binary search tree with the given name.
     * If the node is not found, nothing happens.
     * 
     * @param name the name of the SymbioteHost object to delete
     */
    public void deleteSymbioteHost(String name){
        root = deleteSymbioteHost(root, name);
    }
    private SymbioteHost deleteSymbioteHost(SymbioteHost current, String name){
        //if current is null, nothing to delete
        if(current == null){
            return null;
        }
        int cmp = name.compareTo(current.getName());
        if(cmp < 0){
            //Recusion on left subtree
            current.setLeft(deleteSymbioteHost(current.getLeft(), name));
        }else if(cmp > 0){
            //Recursion on right subtree
            current.setRight(deleteSymbioteHost(current.getRight(), name));
        }else{
            //Found node to delete, address 3 cases
            current.setMentalStability(current.getMentalStability() + 1);
            current.setSymbioteCompatibility
            (current.getSymbioteCompatibility() + 1);

            //1:  no children
            if(current.getLeft() == null && current.getRight() == null ){
                return null;
            }
            //2:  one child
            if(current.getLeft() == null){
                return current.getRight();
            }else if( current.getRight() == null){
                return current.getLeft();
            }
            //3 : 2 kids
            SymbioteHost successor = findMin(current.getRight());
            //Update current nodes data
            current.setName(successor.getName());
            current.setSymbioteCompatibility
            (successor.getSymbioteCompatibility());
            current.setMentalStability(successor.getMentalStability());
            current.setHasAntibodies(successor.hasAntibodies());
            //Remove the successor node recursively from right subtree
            current.setRight(deleteSymbioteHost(current.getRight(),
            successor.getName()));

        }
        return current;
        
    }
    private SymbioteHost findMin(SymbioteHost node){
        while(node.getLeft() != null){
            node = node.getLeft();
        }
        return node;
    }

    /**
     * Challenge - worth zero points
     *
     * Heroes have arrived to defeat you! You must clean up the tree to
     * optimize your chances of survival. You must remove hosts with a
     * suitability between 0 and 100 and hosts that have antibodies.
     * 
     * Cleans up the tree by removing nodes with a suitability of 0 to 100
     * and nodes that have antibodies (IN THAT ORDER).
     */
    public void cleanupTree() {
        // WRITE YOUR CODE HERE
        ArrayList<SymbioteHost> lowSuitHosts =
        findHostsWithinSuitabilityRange(0, 100);
        for(SymbioteHost host : lowSuitHosts){
            deleteSymbioteHost(host.getName());
        }
        ArrayList<SymbioteHost> antiBodyHosts = findHostsWithAntibodies();
        for(SymbioteHost host : antiBodyHosts){
            deleteSymbioteHost(host.getName());
        }
    }


    /**
     * Gets the root of the tree.
     * 
     * @return the root of the tree
     */
    public SymbioteHost getRoot() {
        return root;
    }

    /**
     * Prints out the tree.
     */
    public void printTree() {
        if (root == null) {
            return;
        }

        // Modify no. of '\t' based on depth of node
        printTree(root, 0, false, false);
    }

    private void printTree(SymbioteHost root, int depth, boolean isRight, boolean isLeft) {
        System.out.print("\t".repeat(depth));

        if (isRight) {
            System.out.print("|-R- ");
        } else if (isLeft) {
            System.out.print("|-L- ");
        } else {
            System.out.print("+--- ");
        }

        if (root == null) {
            System.out.println("null");
            return;
        }

        System.out.println(root);

        if (root.getLeft() == null && root.getRight() == null) {
            return;
        }

        printTree(root.getLeft(), depth + 1, false, true);
        printTree(root.getRight(), depth + 1, true, false);
    }
}
