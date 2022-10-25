public class Creature extends Card {

    private int HP;
    private int atkPower;
    public boolean shieldStatus;
    private boolean dealt;

    public Creature(String cardName, int manaCost, int HP, int atkPower, boolean shieldStatus) {
        super(cardName, manaCost);
        this.HP = HP;
        this.atkPower = atkPower;
        this.shieldStatus = shieldStatus;
        this.dealt = false;
    }

    public void attack(Creature creature) {
        if (creature.shieldStatus) {
            creature.setShieldStatus(false);
            System.out.println(creature.getCardName() + " shielded the attack!");
        } else {
            creature.setHP(creature.getHP() - atkPower);
            System.out.println("\n" + creature.getCardName() + " lost " + atkPower + " HP!");
        }
    }

    public void attack(Enemy enemy) {
        enemy.setEnemyHealthBar(enemy.getEnemyHealthBar() - atkPower);
        System.out.println("\n" + enemy.getEnemyName() + " lost " + atkPower + " HP!");
    }

    public void attack(Player player) {
        player.setHealthBar(player.getHealthBar() - atkPower);
        System.out.println("\n" + player.getPlayerName() + " lost " + atkPower + " HP!");
    }

    public void writeInfo() {
        System.out.println(cardName);
        System.out.print("  Cost: " + manaCost);
        System.out.print(", Atk: " + atkPower);
        if (shieldStatus) {
            System.out.print(", HP: " + HP);
            System.out.print(", Shielded" + "\n");
        } else {
            System.out.print(", HP: " + HP + "\n");
        }
    }

    public void writeBattleInfo() {
        System.out.println(cardName);
        System.out.print("  Atk: " + atkPower);
        if (shieldStatus) {
            System.out.print(", HP: " + HP);
            System.out.print(", Shielded" + "\n");
        } else {
            System.out.print(", HP: " + HP + "\n");
        }
    }

    public void displayCard() {
        if (shieldStatus) {
            System.out.println("*-*-*-*-*");
        } else {
            System.out.println(" _______ ");
        }
        System.out.println("|" + cardName + "  |");
        System.out.println("| " + atkPower + "   " + HP + " |");
        if (shieldStatus) {
            System.out.println("*-*-*-*-*");
        } else {
            System.out.println(" _______ ");
        }
    }

    public int getHP() {
        return HP;
    }

    public int getAtkPower() {
        return atkPower;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setAtkPower(int atkPower) {
        this.atkPower = atkPower;
    }

    public void setShieldStatus(boolean shieldStatus) {
        this.shieldStatus = shieldStatus;
    }

    public void setDealt(boolean dealt) {
        this.dealt = dealt;
    }

}
