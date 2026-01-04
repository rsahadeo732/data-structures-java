package trick;

public class House {
    private String name;
    private int weight;
    private House next;

    //constructor to initialze house
    public House(String name, int weight){

        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException
            ("house name cannot be null or empty");
        }
        if(weight < 0){
            throw new IllegalArgumentException("Edge weight cannot be negative");
        }
        this.name = name;
        this.weight = weight;
        this.next = null;
    }

    //getter for house
    public String getName(){
        return name;
    }
    //getter for weifht
    public int getWeight(){
        return weight;
    }
    //getter for next house
    public House getNext(){
        return next;
    }
    //setter for next house
    public void setNext(House next){
        this.next = next;
    }
    @Override
    public String toString(){
        return "House{name = '" + name + "' , weight=" + weight +"}";
    }
    
}
