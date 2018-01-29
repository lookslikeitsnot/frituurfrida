package be.vdab.entities;

public class Deur {
	private int number;
	private boolean opened;
	private boolean containsFry;
	
	public Deur(int number, boolean containsFry) {
		this.number = number;
		this.containsFry = containsFry;
		opened = false;
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
