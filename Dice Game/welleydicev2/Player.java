package welleydicev2;

public class Player {
    String name;
    private int sum;

    public Player(String name) {
        this.name = name;
    }

    public int rollDice() {
        Dice diceSet = new Dice();
        sum = diceSet.dice1 + diceSet.dice2;
        System.out.println("> " + Player.this.name + " rolls " + diceSet.dice1 + " and " + diceSet.dice2 + " (" + sum + ")");
        return sum;
    }

}
