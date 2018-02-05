package be.vdab.entities;

import java.time.LocalDateTime;

public class GastenboekEntry {
	private String gastentekst;
	private String gastnaam;
	private LocalDateTime datum;
	public GastenboekEntry(String gastentekst, String gastnaam, LocalDateTime datum) {
		this.gastentekst = gastentekst;
		this.gastnaam = gastnaam;
		this.datum = datum;
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
