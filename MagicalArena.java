import java.util.Scanner;

public class MagicalArena {

    public void fight(Player playerA, Player playerB) {
        Dice dice = new Dice();
        System.out.println("\nBattle Start!");
        System.out.println(playerA);
        System.out.println(playerB);

        while (playerA.isAlive() && playerB.isAlive()) {
            if (playerA.getHealth() <= playerB.getHealth()) {
                performTurn(playerA, playerB, dice);
            } else {
                performTurn(playerB, playerA, dice);
            }
        }

        System.out.println("\nBattle Over!");
        if (playerA.isAlive()) {
            System.out.println("Player A Wins!");
        } else {
            System.out.println("Player B Wins!");
        }
    }

    private void performTurn(Player attacker, Player defender, Dice dice) {
        int attackRoll = dice.roll();
        int defendRoll = dice.roll();

        int attackDamage = attacker.getAttack() * attackRoll;
        int defendValue = defender.getStrength() * defendRoll;

        int damageDealt = Math.max(0, attackDamage - defendValue);
        defender.reduceHealth(damageDealt);

        System.out.println("\nTurn Summary:");
        System.out.println(attacker + " attacks " + defender);
        System.out.printf("Attack Roll: %d, Damage: %d | Defend Roll: %d, Defended: %d | Defender's Health Left: %d\n",
                attackRoll, attackDamage, defendRoll, defendValue, defender.getHealth());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input for Player A
        System.out.println("Enter Player A's attributes:");
        System.out.print("Health: ");
        int healthA = scanner.nextInt();
        System.out.print("Strength: ");
        int strengthA = scanner.nextInt();
        System.out.print("Attack: ");
        int attackA = scanner.nextInt();

        Player playerA = new Player(healthA, strengthA, attackA);

        // Input for Player B
        System.out.println("\nEnter Player B's attributes:");
        System.out.print("Health: ");
        int healthB = scanner.nextInt();
        System.out.print("Strength: ");
        int strengthB = scanner.nextInt();
        System.out.print("Attack: ");
        int attackB = scanner.nextInt();

        Player playerB = new Player(healthB, strengthB, attackB);

        // Start the game
        MagicalArena arena = new MagicalArena();
        arena.fight(playerA, playerB);
    }
}
