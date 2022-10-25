import java.util.Random;
public class Enemy
{
    public  String enemyName;
    public  int healthBar;
    public  int atkStrength;
    public  int defStrength;

    Random ranNum = new Random();

    public Enemy(String enemyName)
    {
        this.enemyName = enemyName;
        this.healthBar = 20;
        this.atkStrength = ranNum.nextInt(100);

    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public void setEnemyHealthBar(int enemyHealthBar) {
        this.healthBar = enemyHealthBar;
    }

    public int getEnemyHealthBar() {
        return healthBar;
    }

    public String getEnemyName() {
        return enemyName;
    }
    public int getEnemyDmg()
    {
        return atkStrength;
    }
    public void displayEnemy()
    {
        System.out.println("-----Enemy-----");
        System.out.println("Enemy Name: " + enemyName);
        System.out.println("Enemy Health: " + healthBar);
        System.out.println("---------------\n");
    }
}