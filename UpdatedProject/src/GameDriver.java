import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
public class GameDriver {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Random rand = new Random();
        // create player
        System.out.println("\nHello weary Student! What is your name?");
        boolean good = false;
        String playerName = "Player";
        while (!good) {
            try {
                System.out.print("Enter name: ");
                playerName = kb.nextLine();
                good = true;
            } catch (Exception e) {
                System.out.println("Please enter a valid name!");
                kb.next();
            }
        }
        Player player = new Player(playerName);
        System.out.println("\nI wish you luck in your journey through CSCI 1260: Intro to Computer Science II with Professor William Rochelle.\n" +
                "I hope you read the syllabus because you're going to need it...\n");
        System.out.println("Press ENTER to continue...");
        kb.nextLine();
        // create enemy
        Enemy enemy = new Enemy("Will");

        // create battlefields
        ArrayList<Creature> playerBattlefield = new ArrayList<Creature>();
        ArrayList<Creature> enemyBattlefield = new ArrayList<Creature>();

        // create player creatures
        String[] cardNames = new String[]{"Calc   ","Clue   ","Bkpk   ","IOtest ","Regex  ","Token  ","GUI    ","CSV    ","MidTerm","Search ","Sort   ","MapRoom","Final  "};
        int[] manaCosts = new int[] {1,1,2,2,3,3,4,5,5,6,7,9,10};
        int[] hps = new int[]       {2,2,3,3,4,3,5,2,8,4,7,7,10};
        int[] atkPowers = new int[] {1,1,2,2,3,4,4,7,1,8,7,9,10};
        boolean[] shields = new boolean[]{true,false,false,false,true,false,false,false,true,false,false,false,true};
        Deck playerDeck = new Deck();
        for (int i = 0; i < cardNames.length; i++) {
            Creature tempCard = new Creature(cardNames[i], manaCosts[i], hps[i], atkPowers[i], shields[i]);
            playerDeck.addCreature(tempCard);
        }
        // create enemy creatures
        String[] enemycardNames = new String[]{"Quiz   ","Quiz   ","Quiz   ","Quiz   ","Quiz   ","Quiz   ","Quiz   ","Quiz   ","Quiz   ","Quiz   ","Test   ","Test   ","Test   ","Project","Project","Project"};
        int[] enemymanaCosts = new int[] {1,1,2,2,3,3,4,5,5,6,7,9,10,10,10,10};
        int[] enemyhps = new int[]       {2,2,3,3,4,3,5,2,8,4,7,7,10,10,10,10};
        int[] enemyatkPowers = new int[] {1,1,2,2,3,4,4,7,1,8,7,9,10,10,10,10};
        boolean[] enemyshields = new boolean[]{true,false,false,false,true,false,false,false,true,false,false,false,true,true,true,false};
        Deck enemyDeck = new Deck();
        for (int i = 0; i < enemycardNames.length; i++) {
            Creature tempCard = new Creature(enemycardNames[i], enemymanaCosts[i], enemyhps[i], enemyatkPowers[i], enemyshields[i]);
            enemyDeck.addCreature(tempCard);
        }

        // create player spells
        Spell testSpell1 = new Spell("Stack Overflow", 1, 4, true, 2);
        playerDeck.addSpell(testSpell1);
        Spell testSpell2 = new Spell("Pop Quiz", 1, -2, false, 3);
        playerDeck.addSpell(testSpell2);
        Spell testSpell3 = new Spell("Grade Curve", 3, 2, false, 4);
        playerDeck.addSpell(testSpell3);
        Spell testSpell4 = new Spell("Deadline Extension", 1, 0, true, 2);
        playerDeck.addSpell(testSpell4);
        Spell testSpell5 = new Spell("Overslept", 5, -6, false, 2);
        playerDeck.addSpell(testSpell5);
        Spell testSpell6 = new Spell("Zoom Class", 2, 1, true, 3);
        playerDeck.addSpell(testSpell6);
        Spell testSpell7 = new Spell("Dropped Assignment", 7, -15, false, 1);
        playerDeck.addSpell(testSpell7);



        // make hand for player and deal 5 cards
        ArrayList<Creature> handCreatures = new ArrayList<Creature>();
        ArrayList<Spell> handSpells = new ArrayList<Spell>();
        boolean cont = true;
        int roll1;
        int spellsDealt = 0;
        int creaturesDealt = 0;
        for (int i = 0; i < 5; i++) {       // deal 5 cards
            roll1 = rand.nextInt(100)+1;
            if (roll1 >= 65) {              // if spell roll
                handSpells.add(playerDeck.getSpell(spellsDealt));
                spellsDealt++;
            } else if (roll1 < 65) {        // if creature roll
                handCreatures.add(playerDeck.getCreature(creaturesDealt));
                creaturesDealt++;
            }
        }



        /** MAIN LOOP */
        System.out.println("\nAn angry professor approaches...\n");
        System.out.println("Press ENTER to continue...");
        kb.nextLine();
        boolean status = true;
        int manaMax = 1;
        int manaPool = 1;
        int counter = 2;
        int userChoice = 0;
        int enemyCounter = 0;
        while (player.getHealthBar() > 0 && enemy.getEnemyHealthBar() > 0) {
            // if board not empty print it and ask player to attack
            if (playerBattlefield.size() > 0) {
                for (Creature f: playerBattlefield) {
                    printBattlefield(enemy, enemyBattlefield);
                    printBattlefield(player, playerBattlefield);
                    // print enemy battlefield
                    System.out.println("\nEnemy Targets");
                    System.out.println("1) " + enemy.getEnemyName() + ", " + enemy.getEnemyHealthBar() + " HP");
                    for (Creature b: enemyBattlefield) {
                        System.out.print(counter + ") ");
                        b.writeBattleInfo();

                        counter++;
                    }
                    System.out.println("0) None");
                    good = false;
                    while (!good) {
                        try {
                            System.out.print("\nChoose a unit for " + f.getCardName() + "\nto attack: ");
                            userChoice = kb.nextInt();
                            if (!(0 <= userChoice && userChoice <= counter)) {
                                continue;
                            }
                            good = true;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid choice!");
                            kb.next();
                        }
                    }
                    counter = 2;
                    if (userChoice <= 0) {
                        System.out.println("Attack Passed");
                    } else if (userChoice == 1) {
                        f.attack(enemy);
                    } else if (1 < userChoice) {
                        f.attack(enemyBattlefield.get(userChoice-2));
                    }
                    // check for deaths
                    if (player.getHealthBar() <= 0 || enemy.getEnemyHealthBar() <= 0) {
                        break;
                    }
                    for (int y = 0; y < enemyBattlefield.size(); y++) {
                        if (enemyBattlefield.get(y).getHP() <= 0) {
                            System.out.println("Killed " + enemyBattlefield.get(y).getCardName());
                            enemyBattlefield.remove(enemyBattlefield.get(y));
                        }
                    }
                }
            }
            if (player.getHealthBar() <= 0 || enemy.getEnemyHealthBar() <= 0) {
                break;
            }
            // print board again
            printBattlefield(enemy, enemyBattlefield);
            printBattlefield(player, playerBattlefield);
            // draw card
            roll1 = rand.nextInt(100)+1;
            if (spellsDealt >= 7 && creaturesDealt >= 13) {
                System.out.println("No Cards Remaining in Deck!");
            } else if (spellsDealt >= 7 && creaturesDealt <= 13) {
                handCreatures.add(playerDeck.getCreature(creaturesDealt));
                creaturesDealt++;
            } else if (spellsDealt <= 7 && creaturesDealt >= 13) {
                handSpells.add(playerDeck.getSpell(spellsDealt));
                spellsDealt++;
            } else {
                if (roll1 >= 65) {              // if spell roll
                    handSpells.add(playerDeck.getSpell(spellsDealt));
                    spellsDealt++;
                } else if (roll1 < 65) {        // if creature roll
                    handCreatures.add(playerDeck.getCreature(creaturesDealt));
                    creaturesDealt++;
                }
            }

            // display hand and ask player to play something LOOP THIS FOR MANA OR TURN END
            boolean end = false;
            int playChoice = 0;
            while (!end) {
                printBattlefield(enemy, enemyBattlefield);
                printBattlefield(player, playerBattlefield);
                displayHand(handCreatures,handSpells);
                System.out.print("\nMana Pool: " + manaPool);
                System.out.print("\n1) Play Creature\n2) Play Spell\n0) End Turn\n\nChoice: ");
                good = false;
                while (!good) {
                    try {
                        userChoice = kb.nextInt();
                        if (!(0 <= userChoice && userChoice <= 2)) {
                            System.out.println("Please enter a valid choice!");
                            continue;
                        }
                        good = true;
                    } catch (Exception e) {
                        System.out.println("Please enter a valid choice!");
                        kb.next();
                    }
                }
                if (userChoice == 0) {
                    System.out.println("Turn Ended\n");
                    end = true;
                } else if (userChoice == 1) {
                    good = false;
                    while (!good) {
                        try {
                            System.out.print("Enter creature number: ");
                            playChoice = kb.nextInt();
                            if (!(1 <= playChoice && playChoice <= handCreatures.size())) {
                                System.out.println("Please enter a valid choice!");
                                continue;
                            }
                            good = true;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid choice!");
                            kb.next();
                        }
                    }
                    if (manaPool >= handCreatures.get(playChoice-1).getManaCost()) {
                        manaPool = manaPool - handCreatures.get(playChoice-1).getManaCost();
                        playerBattlefield.add(handCreatures.get(playChoice-1));
                        handCreatures.remove(playChoice-1);
                    } else {
                        System.out.println("Not enough mana to play that!");
                        continue;
                    }
                } else if (userChoice == 2) {
                    good = false;
                    while (!good) {
                        try {
                            System.out.print("Enter spell number: ");
                            playChoice = kb.nextInt();
                            if (!(1 <= playChoice && playChoice <= handSpells.size())) {
                                continue;
                            }
                            good = true;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid choice!");
                            kb.next();
                        }
                    }
                    if (manaPool >= handSpells.get(playChoice-1).getManaCost()) {
                        manaPool = manaPool - handSpells.get(playChoice-1).getManaCost();
                        handSpells.get(playChoice-1).use(playerBattlefield,enemyBattlefield,player,enemy);
                        handSpells.remove(playChoice-1);
                    } else {
                        System.out.println("Not enough mana to play that!");
                        continue;
                    }
                }

            }
            manaMax++;
            manaPool = manaMax;
            // enemy attacks
            System.out.println("\nENEMY TURN\n");
            for (Creature q: enemyBattlefield) {
                if (playerBattlefield.size() > 0) {
                    q.attack(playerBattlefield.get(0));
                    System.out.println("Will attacked " + playerBattlefield.get(0).getCardName());
                    if (playerBattlefield.get(0).getHP() <= 0) {
                        System.out.println("Will killed " + playerBattlefield.get(0).getCardName());
                        playerBattlefield.remove(0);
                    }
                } else {
                    q.attack(player);
                }
            }

            // check for deaths then print board again
            if (player.getHealthBar() <= 0 || enemy.getEnemyHealthBar() <= 0) {
                break;
            }
            // enemy plays 2 cards
            if (enemyCounter < 16) {
                enemyBattlefield.add(enemyDeck.getCreature(enemyCounter));
                System.out.println("Will played " + enemyDeck.getCreature(enemyCounter).getCardName() );
                enemyCounter++;
                enemyBattlefield.add(enemyDeck.getCreature(enemyCounter));
                System.out.println("Will played " + enemyDeck.getCreature(enemyCounter).getCardName());
                enemyCounter++;
            }
            System.out.println("\n\n");
        }

        if (player.getHealthBar() > 0) {
            System.out.println("The time has come, you feel your phone receive a notification from the Pulse app " +
                    "saying that the final project has been graded.\nHesitantly, you open the notification, " +
                    "sweat starts to bead on your forehead as you slowly swipe to reveal your grade...\n97/100. You can rest. You have won");
        } else if (player.getHealthBar() <= 0) {
            System.out.println("\n\n\nYou studied hard, but William Rochelle has won this battle and you did not " +
                    "end the class with a passing grade.\nHowever, for a large fee (which you don't get financial aid for) " +
                    "you can retake this class in the summer for a better grade\nor you can wait and retake it again the " +
                    "following semester and not graduate in 4 years!");

        }
    }

    public void battlePhase(Player player, Enemy enemy) {

    }

    public static void printBattlefield(Player player, ArrayList<Creature> array) {
        for (Creature f: array) {
            if (f.shieldStatus) {
                System.out.print("*-*-*-*-*");
            } else {
                System.out.print(" ------- ");
            }
            System.out.print("   ");
        }
        System.out.print("\n");
        for (Creature f: array) {
            System.out.print("|" + f.cardName + "|");
            System.out.print("   ");
        }
        System.out.print("\n");
        for (Creature f: array) {
            if (f.getAtkPower() >= 10 && f.getHP() >= 10) {
                System.out.print("| " + f.getAtkPower() + " " + f.getHP() + " |");
                System.out.print("   ");
            } else if (f.getAtkPower() >= 10) {
                System.out.print("| " + f.getAtkPower() + "  " + f.getHP() + " |");
                System.out.print("   ");
            } else if (f.getHP() >= 10) {
                System.out.print("| " + f.getAtkPower() + "  " + f.getHP() + " |");
                System.out.print("   ");
            } else {
                System.out.print("| " + f.getAtkPower() + "   " + f.getHP() + " |");
                System.out.print("   ");
            }
        }
        System.out.print("\n");
        for (Creature f: array) {
            if (f.shieldStatus) {
                System.out.print("*-*-*-*-*");
            } else {
                System.out.print(" ------- ");
            }
            System.out.print("   ");
        }
        System.out.print("\n");
        System.out.println(player.getPlayerName() + "'s Board        " + player.getHealthBar() + " HP");
        System.out.println("--------------------------------------------------------------------------------------");
    }

    public static void printBattlefield(Enemy enemy, ArrayList<Creature> array) {
        System.out.println("\n\n--------------------------------------------------------------------------------------");
        System.out.println(enemy.getEnemyName() + "'s Board        " + enemy.getEnemyHealthBar() + " HP");
        for (Creature f: array) {
            if (f.shieldStatus) {
                System.out.print("*-*-*-*-*");
            } else {
                System.out.print(" ------- ");
            }
            System.out.print("   ");
        }
        System.out.print("\n");
        for (Creature f: array) {
            System.out.print("|" + f.cardName + "|");
            System.out.print("   ");
        }
        System.out.print("\n");
        for (Creature f: array) {
            if (f.getAtkPower() >= 10 && f.getHP() >= 10) {
                System.out.print("| " + f.getAtkPower() + " " + f.getHP() + " |");
                System.out.print("   ");
            } else if (f.getAtkPower() >= 10) {
                System.out.print("| " + f.getAtkPower() + "  " + f.getHP() + " |");
                System.out.print("   ");
            } else if (f.getHP() >= 10) {
                System.out.print("| " + f.getAtkPower() + "  " + f.getHP() + " |");
                System.out.print("   ");
            } else {
                System.out.print("| " + f.getAtkPower() + "   " + f.getHP() + " |");
                System.out.print("   ");
            }
        }
        System.out.print("\n");
        for (Creature f: array) {
            if (f.shieldStatus) {
                System.out.print("*-*-*-*-*");
            } else {
                System.out.print(" ------- ");
            }
            System.out.print("   ");
        }
        System.out.print("\n");
        for (Creature f: array) {
            System.out.print("~~~~~~~~~~~~");
        }
        System.out.print("\n");
    }

    public static void displayHand(ArrayList<Creature> creatures, ArrayList<Spell> spells) {
        System.out.println("\nCURRENT HAND\n");
        int count = 0;
        System.out.println("Creatures");
        System.out.println("------------");
        for (Creature f: creatures) {
            count++;
            System.out.print(count + ") ");
            f.writeInfo();
        }
        count = 0;
        System.out.println("\nSpells");
        System.out.println("------------");
        for (Spell f: spells) {
            count++;
            System.out.print(count + ") ");
            f.writeInfo();
        }
    }
}

