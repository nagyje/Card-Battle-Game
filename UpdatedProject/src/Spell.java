import java.util.ArrayList;
import java.util.Scanner;
public class Spell extends Card {
    Scanner kb = new Scanner(System.in);
    private int hpChange;
    private boolean shield;
    private int targets;
    private boolean dealt;

    public Spell(String cardName, int manaCost, int hpChange, boolean shield, int targets) {
        super(cardName, manaCost);
        this.hpChange = hpChange;
        this.shield = shield;
        this.targets = targets;
        this.dealt = false;
    }

    public void use(ArrayList<Creature> playerBF, ArrayList<Creature> enemyBF, Player player, Enemy enemy) {
        for (int i = 0; i < targets; i++) {
            if (targets > i) {
                System.out.println("\nTarget Selection #" + (i+1));
            }
            System.out.print("1) Enemy Creature\n2) Friendly Creature\n3) " + enemy.getEnemyName() + "\n4) " + player.getPlayerName() + "\n\nChoose a target: ");
            boolean good = false;
            int choice = 0;
            while (!good) {
                try {
                    choice = kb.nextInt();
                    if (!(0 <= choice && choice <= 4)) {
                        System.out.println("Please enter a valid choice!");
                        continue;
                    }
                    good = true;
                } catch (Exception e) {
                    System.out.println("Please enter a valid choice!");
                    kb.next();
                }
            }
            int userChoice = 0;
            switch (choice) {
                case 1:
                    for (int j = 0; j < enemyBF.size(); j++) {
                        System.out.println((j+1) + ") " + enemyBF.get(j).getCardName() + " - " + enemyBF.get(j).getHP() + " HP");
                    }
                    good = false;
                    while (!good) {
                        try {
                            System.out.print("\nChoose an enemy creature: ");
                            userChoice = kb.nextInt();
                            if (!(1 <= userChoice && userChoice <= enemyBF.size())) {
                                System.out.println("Please enter a valid choice!");
                                continue;
                            }
                            good = true;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid choice!");
                            kb.next();
                        }
                    }
                    applySpell(enemyBF.get(userChoice - 1));
                    kb.nextLine();
                    if (enemyBF.get(userChoice - 1).getHP() <= 0) {
                        System.out.println("Killed " + enemyBF.get(userChoice - 1).getCardName());
                        enemyBF.remove(userChoice - 1);
                    }
                    break;
                case 2:
                    for (int j = 0; j < playerBF.size(); j++) {
                        System.out.println((j+1) + ") " + playerBF.get(j).getCardName() + " - " + playerBF.get(j).getHP() + " HP");
                    }
                    good = false;
                    while (!good) {
                        try {
                            System.out.print("Choose a friendly creature: ");
                            userChoice = kb.nextInt();
                            if (!(0 <= userChoice && userChoice <= playerBF.size())) {
                                System.out.println("Please enter a valid choice!");
                                continue;
                            }
                            good = true;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid choice!");
                            kb.next();
                        }
                    }
                    applySpell(playerBF.get(userChoice - 1));
                    kb.nextLine();
                    if (playerBF.get(userChoice - 1).getHP() <= 0) {
                        System.out.println("Killed " + playerBF.get(userChoice - 1).getCardName());
                        playerBF.remove(userChoice - 1);
                    }
                    break;
                case 3:
                    applySpell(enemy);
                    break;
                case 4:
                    applySpell(player);
                    break;
                default:
                    break;
            }

        }


    }

    public void applySpell(Creature creature) {
        if (shield) {
            creature.setShieldStatus(shield);
            System.out.println("Shielded " + creature.getCardName() + "!");
        }
        if (!creature.shieldStatus) {
            creature.setHP(creature.getHP() + hpChange);
            if (hpChange > 0) {
                System.out.println("\n" + creature.getCardName() + " gained " + hpChange + " HP!");
            } else if (hpChange < 0) {
                System.out.println("\n" + creature.getCardName() + " lost " + hpChange + " HP!");
            }
        } else {
            if (hpChange > 0) {
                creature.setHP(creature.getHP() + hpChange);
                System.out.println("\n" + creature.getCardName() + " gained " + hpChange + " HP!");
            } else if (hpChange < 0) {
                creature.setShieldStatus(false);
                System.out.println("\n" + creature.getCardName() + " shielded the attack!");
            }
        }
    }

    public void applySpell(Player player) {
        player.setHealthBar(player.getHealthBar() + hpChange);
        if (hpChange > 0) {
            System.out.println("\n" + player.getPlayerName() + " gained " + hpChange + " HP!");
        } else if (hpChange < 0) {
            System.out.println("\n" + player.getPlayerName() + " lost " + hpChange + " HP!");
        }
    }

    public void applySpell(Enemy enemy) {
        enemy.setEnemyHealthBar(enemy.getEnemyHealthBar() + hpChange);
        if (hpChange > 0) {
            System.out.println("\n" + enemy.getEnemyName() + " gained " + hpChange + " HP!");
        } else if (hpChange < 0) {
            System.out.println("\n" + enemy.getEnemyName() + " lost " + hpChange + " HP!");
        }
    }

    public void writeInfo() {
        System.out.println(cardName);
        System.out.print("  Cost: " + manaCost);
        System.out.print(", " + hpChange + " HP change");
        if (shield) {
            System.out.print(", Shields Target");
        }
        System.out.print(", Targets: " + targets + "\n");
    }

    public void setDealt(boolean dealt) {
        this.dealt = dealt;
    }
}
