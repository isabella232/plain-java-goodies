package welleydicev2;

public class Main {

    public static void main(String[] args) {

    Game start = new Game();

    start.createNewPlayer("Bob");

        start.runGame();
        System.out.println(start.rollPoint());

    }
}
