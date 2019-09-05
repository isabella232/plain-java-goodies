package welleydicev2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {

    private final Set<Integer> win = new HashSet<>();
    private final Set<Integer> lose = new HashSet<>();
    private final List<Object> players = new ArrayList<>();
    private int totalRolls, firstRoll, rollAgain;
    private boolean pointOn;
    private Player person;

    public Game() {
        this.win.add(7);
        this.win.add(11);
        this.lose.add(2);
        this.lose.add(3);
        this.lose.add(12);
    }

    public void createNewPlayer(String playerName) {
        players.add(person = new Player(playerName));
    }

    public int runGame() {
        System.out.println("-----------------------------------------------");
        System.out.println("Welcome to a game of CRAPS!");
        System.out.println("Natural win: " + this.win);
        System.out.println("Crap rolls: " + this.lose);
        System.out.println("-----------------------------------------------");

        for (Object player : this.players) {
            person = (Player) player;

            System.out.println("\n> " + person.name + " has entered the game.");

            firstRoll = person.rollDice();

            if (this.win.contains(firstRoll)) {
                System.out.println(person.name + " wins on the first roll!");

            }
            else if (this.lose.contains(firstRoll)) {
                Object loser = player;
                System.out.println(person.name + " lost on the first roll! " + person.name + " is eliminated.");
            }
            else
                System.out.println("\n> POINT is " + firstRoll + ".\n");
        }
        return firstRoll;
    }

    public String rollPoint() {
        if (!this.win.contains(firstRoll) && !this.lose.contains(firstRoll)) {
            pointOn = true;
        } else
            pointOn = false;

        int point = firstRoll;
        totalRolls = 1;

        REROLL:

        while(pointOn) {
            rollAgain = person.rollDice();

            if (rollAgain == 7) {
                System.out.println(person.name + " lost!");
                totalRolls++;
                break;

            } else if (rollAgain != point) {
                System.out.println("> POINT is " + point);
                totalRolls++;
                continue REROLL;

            } else if (rollAgain == point) {
                System.out.println(person.name + " wins!");
                totalRolls++;
                break;
            }
        }
        return "\n> Total rolls: " + totalRolls;
    }
}
