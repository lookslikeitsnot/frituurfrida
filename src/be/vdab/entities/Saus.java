package be.vdab.entities;

import java.util.List;

public class Saus {
	private int nummer;
	private String naam;
	private List<String> ingredienten;
	
	
	public Saus(int nummer, String naam) {
		setNummer(nummer);
		setNaam(naam);
	}
	public Saus(int nummer, String naam, List<String> ingredienten) {
		this(nummer, naam);
		setIngredienten(ingredienten);
	}
	public long getNummer() {
		return nummer;
	}
	public String getNaam() {
		return naam;
	}
	public List<String> getIngredienten() {
		return ingredienten;
	}
	
	public void setNummer(int nummer) {
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
	
	public static boolean isIngredientenValid(List<String> ingredienten) {
		boolean res = true;
		for(String ingredient: ingredienten) {
			res &= isNaamValid(ingredient);
		}
		return res;
	}
	
	public void setIngredienten(List<String> ingredienten) {
		if(!isIngredientenValid(ingredienten)) {
			throw new IllegalArgumentException();
		}
		this.ingredienten = ingredienten;
	}
}
