import java.util.Random;
import java.util.ArrayList;

	abstract class abstractDeck {
 		public abstract int getSize();
 		public abstract String displayDeck();
 		public abstract void addCard(Card c);
 		public abstract boolean delete(int s, int v);
 		public abstract Card drawCard();
 		public abstract void mergeDeck(abstractDeck d);
		public abstract Card printFirstCard();
 		public abstract void shuffle();
	}
