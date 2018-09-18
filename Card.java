public class Card {
	private int value;
	private int color;

// the Card constructor which takes in a value and a color as its parameters
	public Card(int v, int c) {
		value = v;
		color = c;
	}

// a method that returns the value(int) of the card
	public int getValue() {
		return value;
	}

// a method that returns the color(int) of the card
	public int getColor() {
		return color;
	}

// show the value and the color of a specific card as a string(for example: 10 Blue)
	public String displayCard() {
		String description = "| ";
		if (value >= 0 && value <= 9) {
			description += value + " ";
		} else if (value == 10) {
			description += "Skip ";
		} else if (value == 11) {
			description += "DrawTwo ";
		} else if (value == 12) {
			description += "Reverse ";
		} else if (value == 13) {
			description += "WildDrawFour |";
			return description;
		} else if (value == 14) {
			description += "Wild |";
			return description;
		}
		switch (color) {
			case 0:
				description += "R";
				break;
			case 1:
				description += "G";
				break;
			case 2:
				description += "B";
				break;
			case 3:
				description += "Y";
				break;
		}
		description += " |";
		return description;
	}
}
