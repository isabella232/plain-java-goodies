import java.util.ArrayList;
import java.util.List;

public class Location {

    public int randomNumber(int minimum, int maximum) {
        int i = (int) (Math.random()*(maximum-minimum) + minimum);
        return i;
    }

    public static void main(String[] args) {

        Location generate = new Location();

        List<String> location = new ArrayList<>();

        location.add("Upstairs");
        location.add("Coffee area");
        location.add("Outside of classroom");
        location.add("Outside of building");
        location.add("Classroom");

        System.out.println(location.get(generate.randomNumber(0, 5)));
    }
}
