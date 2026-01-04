package trick;

public class Candy {
    private String name;
    private int count;

    public Candy (String name, int count){
        this.name = name;
        this.count = count;
    }

    //getter for candy
    public String getName(){
        return name;
    }
    //getter for candy county
    public int getCount(){
        return count;
    }
    @Override
    public String toString(){
        return "Candy{name= '" + name + "', county=" +count+ "}";
    }
    
}
