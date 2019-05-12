package blackjackproberen;

public class Kaarten {
	
	private Kleur kleur;
	private Waarde waarde;
	
	public Kaarten(Kleur kleur, Waarde waarde) {
		
		this.waarde = waarde;
		this.kleur = kleur;
	
	}
	
	public String toString() {
		return this.kleur.toString() + "-" + this.waarde.toString();
		
	}

	public Waarde getWaarde() {
		return this.waarde;
	}
}
