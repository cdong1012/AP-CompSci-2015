import java.util.*;
import java.io.Console;

	class unoGame {
		public static void main(String[] args) {
			Console console = System.console();

			// Get number of players of the game
			int players = 0;
			while (players == 0) {
				System.out.print("How many players (2-4)? ");
				try {
					players = Integer.parseInt(console.readLine());
				} catch (Exception e) {
					players = 0;
				}
				if (players < 2 || players > 4) {
					System.out.println("Please enter a valid number of players");
					players = 0;
				}
			}

			// Create draw deck and player hands
			unoDeck orgDeck = new unoDeck();
			orgDeck.shuffle();
			ArrayList<unoDeck> playerHands = new ArrayList<>();
			for (int i = 0; i < players; i++) {
				playerHands.add(new unoDeck(0));
			}
			unoDeck usedCard = new unoDeck(0);

			// Draw player hands
			for (unoDeck p : playerHands) {
				drawHand(7, p, orgDeck);
			}

			// Put first card
			drawHand(1, usedCard, orgDeck);

			// making playCard method to let a player type the card he/she wants to play and put that card to the usedCard deck

			int player = 0;
			boolean clockwise = true;
			while (true) {
				if(usedCard.getSize() > 0) {
					Card c = usedCard.printFirstCard();
					if(c.getValue() == 10) {
					  System.out.println("Skip Card");
						player += (clockwise ? 1 : -1);
						player %= players;
				  } else if(c.getValue() == 11) {
						System.out.println("Draw Two Card");
						drawHand(2, playerHands.get(player), orgDeck);
					} else if (c.getValue() == 12) {
						System.out.println("Reverse Card");
						clockwise = !clockwise;
						player += (clockwise ? 2 : -2) + players;
						player %= players;
					} else if(c.getValue() == 13) {
						System.out.println("Wild Draw Four Card");
						System.out.println("Color to play(Red is 0, Green is 1, Blue is 2, and Yellow is 3): " + c.getColor());
						drawHand(4, playerHands.get(player), orgDeck);
					} else if (c.getValue() == 14) {
						System.out.println("Wild Card");
						System.out.println("Color to play(Red is 0, Green is 1, Blue is 2, and Yellow is 3): " + c.getColor());
					}
					usedCard.addCard(c);
				}

				System.out.println("Player " + (player + 1));
				System.out.println("Deck: " + playerHands.get(player).displayDeck());
				playCard(playerHands.get(player), usedCard, orgDeck);

				// automatically say "UNO!" when any of the player's deck has only 1 card left
				if(playerHands.get(player).getSize() == 1) {
					System.out.println("Player " + player + "has 1 card left");
					System.out.println("Uno!!!!");
					System.out.println('\n');
				}

				if(playerHands.get(player).getSize() == 0) {
					System.out.println("Game Over");
					System.out.println("The winner is player" + player);
					break;
				}

				player += (clockwise ? 1 : -1) + players;
				player %= players;
			}
		}



		public static void drawHand(int cards, abstractDeck to, abstractDeck from) {
			for(int i = 1;i <= cards;i++) {
 				to.addCard(from.drawCard());
			}
		}

		// let the player choose a card in the deck he/she wants to play (same value, same color, Wild, or WildDrawFour)
		public static void playCard(abstractDeck d, abstractDeck disCard, abstractDeck orgDeck) {
			Console console = System.console();
			int value = -1;
			int color = -1;
			Card c = disCard.printFirstCard();

			while(value == -1 || color == -1) {
				System.out.println("Top Card: " + c.displayCard());
				String line = console.readLine("The card that is in your deck you want to play (Type 'No Card' if you do not have a valid card): ");
				String parts[] = line.split(" ");
				String valueCard = "";
				String colorCard = "";
				if (parts.length == 2) {
					valueCard = parts[0];
					colorCard = parts[1];
				} else if (parts.length == 1) {
					valueCard = parts[0];
				}

				if(valueCard.equalsIgnoreCase("No") && colorCard.equalsIgnoreCase("Card")) {
					drawHand(1, d, orgDeck);
					System.out.println("Deck: " + d.displayDeck());
				} else if(valueCard.equalsIgnoreCase("1")) {
					value = 1;
				} else if(valueCard.equalsIgnoreCase("2")) {
					value = 2;
				} else if(valueCard.equalsIgnoreCase("3")) {
					value = 3;
				} else if(valueCard.equalsIgnoreCase("4")) {
					value = 4;
				} else if(valueCard.equalsIgnoreCase("5")) {
					value = 5;
				} else if(valueCard.equalsIgnoreCase("6")) {
					value = 6;
				} else if(valueCard.equalsIgnoreCase("7")) {
					value = 7;
				} else if(valueCard.equalsIgnoreCase("8")) {
					value = 8;
				} else if(valueCard.equalsIgnoreCase("9")) {
					value = 9;
				} else if(valueCard.equalsIgnoreCase("Skip")) {
					value = 10;
				} else if(valueCard.equalsIgnoreCase("DrawTwo")) {
					value = 11;
				} else if(valueCard.equalsIgnoreCase("Reverse")) {
					value = 12;
				} else if(valueCard.equalsIgnoreCase("WildDrawFour")) {
					value = 13;
				} else if(valueCard.equalsIgnoreCase("Wild")) {
					value = 14;
				} else if(valueCard.equalsIgnoreCase("0")) {
					value = 0;
				} else {
					value = -1;
				}

				if(colorCard.equalsIgnoreCase("Red")) {
					color = 0;
				} else if(colorCard.equalsIgnoreCase("Green")) {
					color = 1;
				} else if(colorCard.equalsIgnoreCase("Blue")) {
					color = 2;
				} else if(colorCard.equalsIgnoreCase("Yellow")) {
					color = 3;
				} else {
					color = -1;
				}

				if (d.delete(color, value)) {
					if (value == 13 || value == 14) {
						Console colorTest = System.console();
						color = -1;
						while (color == -1) {
							String theColor = console.readLine("The color of the card you want the next player to play: ");
							if(theColor.equalsIgnoreCase("Red")) {
								color = 0;
							} else if(theColor.equalsIgnoreCase("Green")) {
								color = 1;
							} else if(theColor.equalsIgnoreCase("Blue")) {
								color = 2;
							} else if(theColor.equalsIgnoreCase("Yellow")) {
								color = 3;
							} else {
								color = -1;
							}
						}
						disCard.addCard(c);
						disCard.addCard(new Card(value, color));
					} else {
						// Check to see if the card is valid
						if (c.getValue() != value && c.getColor() != color) {
							d.addCard(new Card(value, color));
							value = -1;
						} else {
							disCard.addCard(new Card(value, color));
						}
					}
				} else {
					value = -1;
				}
			}
		}
	}
