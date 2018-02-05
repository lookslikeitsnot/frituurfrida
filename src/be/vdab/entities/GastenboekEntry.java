package be.vdab.entities;

import java.time.LocalDateTime;

public class GastenboekEntry {
	private int nummer;
	private String gastentekst;
	private String gastnaam;
	private LocalDateTime datum;
	public GastenboekEntry(int nummer,String gastentekst, String gastnaam, LocalDateTime datum) {
		this.nummer = nummer;
		this.gastentekst = gastentekst;
		this.gastnaam = gastnaam;
		this.datum = datum;
	}
	public int getNummer() {
		return nummer;
	}
	public String getGastentekst() {
		return gastentekst;
	}
	public String getGastnaam() {
		return gastnaam;
	}
	public LocalDateTime getDatum() {
		return datum;
	}
}
