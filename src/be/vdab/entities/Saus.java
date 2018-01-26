package be.vdab.entities;

public class Saus {
	private long nummer;
	private String naam;
	private String[] ingredienten;
	
	
	public Saus(long nummer, String naam) {
		setNummer(nummer);
		setNaam(naam);
	}
	public Saus(long nummer, String naam, String... ingredienten) {
		this(nummer, naam);
		setIngredienten(ingredienten);
	}
	public long getNummer() {
		return nummer;
	}
	public String getNaam() {
		return naam;
	}
	public String[] getIngredienten() {
		return ingredienten;
	}
	
	public void setNummer(long nummer) {
		this.nummer = nummer;
	}
	
	public static boolean isNaamValid(String naam) { 
		return naam != null && !naam.trim().isEmpty();
	}
	
	public void setNaam(String naam) {
		if (!isNaamValid(naam)) {
			throw new IllegalArgumentException();
		}
		this.naam = naam;
	}
	
	public static boolean isIngredientenValid(String[] ingredienten) {
		boolean res = true;
		for(String ingredient: ingredienten) {
			res &= isNaamValid(ingredient);
		}
		return res;
	}
	
	public void setIngredienten(String[] ingredienten) {
		if(!isIngredientenValid(ingredienten)) {
			throw new IllegalArgumentException();
		}
		this.ingredienten = ingredienten;
	}
}
