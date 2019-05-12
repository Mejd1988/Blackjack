package blackjackproberen;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	
	private ArrayList<Kaarten> kaarten;
	
	
	public Deck() {
		this.kaarten = new ArrayList<Kaarten>();
	}
	
	public void maakVolDeck() {
		//Kaarten maken
		for(Kleur kaartKleur : Kleur.values()) {
			for(Waarde kaartWaarde : Waarde.values()) {
				//nieuwe kaart toevoegen
				this.kaarten.add(new Kaarten(kaartKleur,kaartWaarde));
			}
		}
	}
	
	public void schudden() {
	 ArrayList<Kaarten> tijdelijkDeck = new ArrayList<Kaarten>();
	 //gebruik random voor het schudden
	 Random random = new Random();
	 int randomKaartIndex = 0;
	 int origineelGroot = this.kaarten.size();
	 for(int i = 0; i < origineelGroot; i++) {
		 //Maak random kaarten met rand.nextInt ((max - min) + 1) + min;
		 randomKaartIndex = random.nextInt((this.kaarten.size()-1 - 0) + 1) + 0;
		 tijdelijkDeck.add(this.kaarten.get(randomKaartIndex));
		 //verwijder uit origineel deck
		 this.kaarten.remove(randomKaartIndex);
	 }
	 this.kaarten = tijdelijkDeck;
	}
	
	public String toString() {
		String kaartLijstTonen = "";
		int i = 0;
		
		for(Kaarten aKaarten : this.kaarten) {
			kaartLijstTonen += "\n" + aKaarten.toString ();
			i ++;
		}
		return kaartLijstTonen;
	}
	
	public void verwijderKaart(int i) {
		this.kaarten.remove(i);
	}
	
	public Kaarten getKaart(int i) {
		return this.kaarten.get(i);
	}
	
	public void voegKaart (Kaarten voegKaart) {
		this.kaarten.add(voegKaart);		
	}
	
	//methode draw van het deck
	public void draw(Deck komtVan) {
		this.kaarten.add(komtVan.getKaart(0));
		komtVan.verwijderKaart(0);
	}
	
	public int deckGroot() {
		return this.kaarten.size();
	}
	
	public void terugZetten(Deck terugNaar) {
		int thisDeckGroot = this.kaarten.size();
		
		//zet kaart naar terugNaar deck
		for(int i = 0; i < thisDeckGroot; i++) {
			terugNaar.voegKaart(this.getKaart(i));
		}
		
		for(int i = 0; i < thisDeckGroot; i++) {
			this.verwijderKaart(0);
		}
		
		
		
	}
	
	public int kaartWaarde() {
		int totaalWaarde = 0;
		int aas = 0;
		
		// chek waarde van iedere kaart
		for(Kaarten aKaarten : this.kaarten) {
			switch(aKaarten.getWaarde()) {
			case TWEE: totaalWaarde += 2; break;
			case DRIE: totaalWaarde += 3; break;
			case VIER: totaalWaarde += 4; break;
			case VIJF: totaalWaarde += 5; break;
			case ZES: totaalWaarde += 6; break;
			case ZEVEN: totaalWaarde += 7; break;
			case ACHT: totaalWaarde += 8; break;
			case NEGEN: totaalWaarde += 9; break;
			case TIEN: totaalWaarde += 10; break;
			case BOER: totaalWaarde += 10; break;
			case VROUW: totaalWaarde += 10; break;
			case KONING: totaalWaarde += 10; break;
			case AAS: totaalWaarde += 1; break;
			
			}
		}
		
		for(int i = 0; i < aas; i++) {
			if(totaalWaarde > 10) {
					totaalWaarde += 1;
			}
			else {
				totaalWaarde += 11;
			}
		}
		
		return totaalWaarde;
			
	}
}
