import java.util.*;

class unoDeck extends abstractDeck {
	private ArrayList<Card> myDeck;

	//constructor of unoDeck, creates an unoDeck of 108 uno Cards
	public unoDeck() {
		myDeck = new ArrayList<>();
		for(int i = 0; i < 4; i++) {
			for(int n = 0; n < 13; n++) {
				myDeck.add(new Card(n, i));
				myDeck.add(new Card(n, i));
			}
			for(int n = 13; n < 15; n++) {
				myDeck.add(new Card(n, -1));
			}
		}
	}

	// contructor of unodeck as well, but creates an empty unoDeck
	public unoDeck(int count) {
		myDeck = new ArrayList<>(count);
	}


	// return the number of cards in the unoDeck
	public int getSize() {
		return myDeck.size();
	}

	// add the card in the parameters of the method to the deck
	public void addCard(Card c) {
		myDeck.add(c);
	}

	// remove a card from the deck(return true if the card is successfully removed, and return false if there is no card with the same color and value as the parameters)
	public boolean delete(int s, int v) {
		for(int i = 0; i < myDeck.size(); i++) {
			if(myDeck.get(i).getColor() == s && myDeck.get(i).getValue() == v) {
				myDeck.remove(i);
				return true;
			}
		}
		return false;
	}

// 	randomly draw cards from the deck, return it and delete it from the deck
	public Card drawCard() {
		if (myDeck.size() == 0) return null;
		Random rand = new Random();
		return myDeck.remove(rand.nextInt(myDeck.size()));
	}

	// add all cards from 0 to this deck and removes them from d
	public void mergeDeck(abstractDeck d) {
		while (d.getSize() > 0) {
			addCard(d.drawCard());
		}
	}

	// 	Randomly shuffle the deck
	public void shuffle() {
		abstractDeck myDeck = new unoDeck(0);
		myDeck.mergeDeck(this);
		mergeDeck(myDeck);
	}

	// Show what cards are in a deck by going through each card and use the method displayCard to display it
	public String displayDeck() {
		String description = "";
		for (Card c : myDeck) {
			description += c.displayCard() + " ";
		}
		return description;
	}

// remove the card on top of the deck (sorry I don't have time to change the name of the method so it doesnt really make sence!)
	public Card printFirstCard() {
		return myDeck.remove(myDeck.size() - 1);
	}
}
