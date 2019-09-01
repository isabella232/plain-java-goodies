import java.util.ArrayList;
import java.util.List;

public class Location {

    private final List<String> locations = new ArrayList<>();

    public Location () {
        this.locations.add("Upstairs");
        this.locations.add("Coffee area");
        this.locations.add("Outside of classroom");
        this.locations.add("Outside of building");
        this.locations.add("Classroom");
    }

    public String returnRandomLocation() {
        int arraySize = locations.size();
        int index = ((int) (Math.random()* arraySize));
        String location = locations.get(index);
        return location;
    }

    public static void main(String[] args) {

        Location generate = new Location();
        System.out.println(generate.returnRandomLocation());

    }
}