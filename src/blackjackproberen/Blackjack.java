package blackjackproberen;

import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {

		System.out.println("Welkom bij Blackjack!!");
		
		//Maak speel deck aan
		Deck speelDeck = new Deck();
		speelDeck.maakVolDeck();
		speelDeck.schudden();
		//maak deck aan voor speler
		Deck spelerDeck = new Deck();
		
		Deck dealerDeck = new Deck();
		
		double spelerGeld = 100.00;
		
		Scanner userInput = new Scanner(System.in);
		
		//Spel loop alles in een while loop om te leren
		while(spelerGeld > 0) {
			//blijven spelen
			// neem spelers wedschap
			System.out.println("Je hebt €" + spelerGeld + ", hoeveel wil je inzetten?");
			double spelerWed = userInput.nextDouble();
			if(spelerWed > spelerGeld) {
				System.out.println("Je kunt niet meer inzetten dan je hebt!");
				break;
			}
			
			boolean eindeRonde = false;
			
			//kaarten aan speler uitdelen
			spelerDeck.draw(speelDeck);
			spelerDeck.draw(speelDeck);
			
			//kaarten aan dealer uitdelen
			dealerDeck.draw(speelDeck);
			dealerDeck.draw(speelDeck);
			
			while(true) {
				System.out.println("In je hand");
				System.out.println(spelerDeck.toString());
				System.out.println("Je deck waarde is: " + spelerDeck.kaartWaarde());
				
				//Dealer hand tonen
				System.out.println("Dealer hand: " + dealerDeck.getKaart(0).toString() + " en [afgeschermd]");
				
				//wat wil de speler doen
				System.out.println("Wil je (1)Hit of (2) Stand?");
				int response = userInput.nextInt();
				
				// als hit is
				if(response == 1) {
					spelerDeck.draw(speelDeck);
					System.out.println("Je hebt " + spelerDeck.getKaart(spelerDeck.deckGroot()-1).toString());
					
					//als > 21
					if(spelerDeck.kaartWaarde() > 21) {
						System.out.println("Bust, waarde nu: " + spelerDeck.kaartWaarde());
						spelerGeld -= spelerWed;
						eindeRonde = true;
						break;
					}
				}
				if(response==2) {
					break;
				}
			}
			
			//dealer hand tonen
			System.out.println("Dealer kaarten: " + dealerDeck.toString());
			
			//zie of de dealer meer punten heeft dan de speler
			if(dealerDeck.kaartWaarde() > spelerDeck.kaartWaarde()&& eindeRonde == false){
				System.out.println("Verslagen door dealer!");
				spelerGeld -= spelerWed;
				eindeRonde = true;
			}
			
			// als dealer meer kaarten trekt
			while((dealerDeck.kaartWaarde() < 17) && eindeRonde == false) {
				System.out.println("Dealer trekt: " + dealerDeck.getKaart(dealerDeck.deckGroot()-1).toString());
			}
			
			//Toon totaal dealer
			System.out.println("Dealer hand waarde: " + dealerDeck.kaartWaarde());
			
			//dealer is bust
			if((dealerDeck.kaartWaarde() > 21)&& eindeRonde == false) {
				System.out.println("Dealer bust(verloren), jij wint!");
				spelerGeld += spelerWed;
				eindeRonde = true;
			}
			
			//Kijk of push is
			if((spelerDeck.kaartWaarde() == dealerDeck.kaartWaarde())&& eindeRonde == false){
				System.out.println("Push");
				eindeRonde = true;

			}
			
			if((spelerDeck.kaartWaarde() > dealerDeck.kaartWaarde()) && eindeRonde == false) {
				System.out.println("Jij wint!!");
				spelerGeld += spelerWed;
				eindeRonde = true;
			}
			else if(eindeRonde == false) {
				System.out.println("Je hebt wat in de handen had verloren.");
				spelerGeld -= spelerWed;
				eindeRonde = true;
			}
			
			spelerDeck.terugZetten(speelDeck);
			dealerDeck.terugZetten(speelDeck);
			System.out.println("Geen kaart in  hand meer");
			
		}
		System.out.println("Einde spel! Je hebt geen geld meer...");
	}

}
