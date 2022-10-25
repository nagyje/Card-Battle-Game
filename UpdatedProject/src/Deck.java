import java.util.ArrayList;
public class Deck {
    private ArrayList<Creature> creatures;
    private ArrayList<Spell> spells;
    private ArrayList<Boolean> dealt;
    private int deckSize;
    private int numCreatures;
    private int numSpells;

    public Deck() {
        this.deckSize = 20;
        this.creatures = new ArrayList<Creature>(13);
        this.spells = new ArrayList<Spell>(7);
        this.dealt = new ArrayList<Boolean>(20);
        for (int i = 0; i < deckSize; i++) {
            dealt.add(false);
        }
        this.numCreatures = 0;
        this.numSpells = 0;
    }

    /**public Deck(int deckSize) {
        this.deckSize = deckSize;
        this.creatures = new ArrayList<Creature>(deckSize/2);
        this.creatures = new ArrayList<Creature>(deckSize/2);
        this.dealt = new ArrayList<Boolean>();
        for (int i = 0; i < deckSize; i++) {
            dealt.add(false);
        }
        this.numCards = 0;
    }

    public Deck(Deck prevDeck) {
        this.creatures = prevDeck.getCards();
        this.dealt = prevDeck.getDealt();
        this.deckSize = prevDeck.getSize();
        this.numCards = prevDeck.getNum();
    }**/

    public void addCreature(Creature newCard) {
        creatures.add(newCard);
        numCreatures++;
    }

    public void addSpell(Spell newCard) {
        spells.add(newCard);
        numSpells++;
    }

    public Creature dealCreature(int i) {
        dealt.set(i, true);
        return creatures.get(i);
    }

    public Spell dealSpell(int i) {
        dealt.set(i, true);
        return spells.get(i);
    }

    public Creature getCreature(int i) {
        return creatures.get(i);
    }

    public Spell getSpell(int i) {
        return spells.get(i);
    }

    public void returnToDeck(int i) {
        dealt.set(i, false);
    }

    public void displayCreatures() {
        for (int i = 0; i < numCreatures; i++) {
            creatures.get(i).writeInfo();
            if (dealt.get(i)) {
                System.out.println("\nThis card has been dealt.\n");
            } else {
                System.out.println("\nThis card has not been dealt.\n");
            }
        }
    }


    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public ArrayList<Boolean> getDealt() {
        return dealt;
    }

    public int getSize() {
        return deckSize;
    }

    public int getNumCreatures() {
        return numCreatures;
    }

    public int getNumSpells() {
        return numSpells;
    }
}