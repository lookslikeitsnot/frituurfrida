package be.vdab.spellen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SausRaden implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private String teVinden;
	private String gevonden;
	private int pogingen;
	private List<Character> geprobeerdeLetters;
	

	public SausRaden(String teVinden) {
		this.teVinden = teVinden;
		pogingen = 0;
		geprobeerdeLetters = new ArrayList<>();
	}

	public String getTeVinden() {
		return teVinden;
	}

	public int getPogingen() {
		return pogingen;
	}

	public List<Character> getGeprobeerdeLetters() {
		return geprobeerdeLetters;
	}

	public void setGeprobeerdeLetters(List<Character> geprobeerdeLetters) {
		this.geprobeerdeLetters = geprobeerdeLetters;
	}

	public void setPogingen(int pogingen) {
		this.pogingen = pogingen;
	}

	public String getGevonden() {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < teVinden.length(); i++) {
			if (geprobeerdeLetters.contains(teVinden.charAt(i))) {
				str.append(teVinden.charAt(i));
			} else {
				str.append('.');
			}
		}
		gevonden = str.toString();
		return gevonden;
	}
}
