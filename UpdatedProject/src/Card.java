public class Card {
    protected String cardName;

    protected int manaCost;

    public Card(String cardName, int manaCost) {
        this.cardName = cardName;
        this.manaCost = manaCost;
    }

    public void play(Enemy enemy, Player player) {

    }
    public void writeInfo() {
        System.out.println("****************************");
        System.out.println(cardName.toString());
    }

    public int getManaCost() {
        return manaCost;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

}
