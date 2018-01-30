package be.vdab.entities;

import java.io.Serializable;

public class Deur implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nummer;
	private boolean opened;
	private boolean containsFry;
	
	public Deur(int number) {
		this.nummer = number;
		this.containsFry = false;
		this.opened = false;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public boolean isContainsFry() {
		return containsFry;
	}

	public void setContainsFry(boolean containsFry) {
		this.containsFry = containsFry;
	}

	public int getNummer() {
		return nummer;
	}

	public void setNummer(int number) {
		this.nummer = number;
	}
	
	public String getImage() {
		return this.opened ? this.containsFry ? "gevonden" : "deuropen" : "deurtoe";
	}
}
	
