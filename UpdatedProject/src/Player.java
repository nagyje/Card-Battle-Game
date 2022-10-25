public class Player
{
    static String playerName;
    protected int healthBar;
    protected Deck playerDeck;

    public Player(String playerName)
    {
        this.playerName = playerName;   //player should only be able to set their name.
        healthBar = 20;
    }

    public void setHealthBar(int healthBar) {
        this.healthBar = healthBar;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHealthBar() {
        return healthBar;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Deck getPlayerDeck() {
        return playerDeck;
    }

    public void setPlayerDeck(Deck playerDeck) {
        this.playerDeck = playerDeck;
    }

    public void playerInfo () {
        System.out.println("***************");
        System.out.println("PLAYER INFO");
        System.out.println(playerName);
        System.out.println("HP: " + healthBar);
    }
}