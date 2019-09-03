package welleydicev1;

import java.util.*;

public class DiceyBusiness {

    private final Set<Integer> win = new HashSet<>();
    private final Set<Integer> lose = new HashSet<>();

    public DiceyBusiness() {
        this.win.add(7);
        this.win.add(11);
        this.lose.add(2);
        this.lose.add(3);
        this.lose.add(12);
    }

    public int rollDice() {

         int dice1 = 1 + ((int) (Math.random() * 6));
         int dice2 = 1 + ((int) (Math.random() * 6));

        int sum = dice1 + dice2;
        return sum;
    }

    public static void main(String[] args) {

        DiceyBusiness roll = new DiceyBusiness();

        System.out.println("> Let's play a game of CRAPS!");
        System.out.println("> First roll to win: " + roll.win);
        System.out.println("> First roll to lose: " + roll.lose);

        int firstRoll = roll.rollDice();

        Boolean gameOn = true;
        Boolean pointOn = false;

        while (gameOn) {

            if (roll.win.contains(firstRoll)) {
                System.out.println("You rolled " + firstRoll + " first!");
                System.out.println("> You win!");
                break;
            }
            if (roll.lose.contains(firstRoll)) {
                System.out.println("You rolled " + firstRoll + " first!");
                System.out.println("> You lose!");
                break;
            }
            else {
                System.out.println("POINT! You rolled " + firstRoll + ". POINT is " + firstRoll + ".");
                System.out.println("> Roll " + firstRoll + " again to win OR lose when you roll 7.");
                pointOn = true;
                break;
            }
        }

        int point = firstRoll;
        int totalRolls = 1;

        REROLL:

        while (pointOn) {

            int rollAgain = roll.rollDice();

            if (rollAgain == 7) {
                System.out.println("You rolled 7!");
                System.out.println("> You lost!");
                totalRolls++;
                break;

            } else if (rollAgain != point) {
                System.out.println("You rolled " + rollAgain + ". POINT is " + point);
                totalRolls++;
                continue REROLL;

            } else if (rollAgain == point) {
                System.out.println("You rolled " + rollAgain + "!");
                System.out.println("You win!");
                totalRolls++;
                break;
            }

        }
        System.out.println("Total rolls = " + totalRolls);

    }
}