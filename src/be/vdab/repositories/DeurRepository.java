package be.vdab.repositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

import be.vdab.entities.Deur;

public class DeurRepository implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Map<Long, Deur> DEUREN = new ConcurrentHashMap<>();
	public static int aantalDeuren = 8;
	public DeurRepository(){
		makeDeurRepository();
	}

	public void openDoor(Long deurnummer) {
		DEUREN.get(deurnummer).setOpened(true);
	}

	public List<Deur> findAll() {
		return new ArrayList<>(DEUREN.values());
	}
	
	public Map<Long, Deur> getDeuren(){
		return DEUREN;
	}
	
	public void makeDeurRepository(){
		for(int i = 0; i<aantalDeuren; i++) {
			DEUREN.put((long) i, new Deur(i));
		}
		int randomDoor = ThreadLocalRandom.current().nextInt(DEUREN.size());
		DEUREN.get((long)randomDoor).setContainsFry(true);
	}
}