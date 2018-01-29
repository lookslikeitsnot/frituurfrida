package be.vdab.repositories;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

import be.vdab.entities.Deur;

public class DeurRepository {
	private static final Map<Long, Deur> DEUREN = new ConcurrentHashMap<>();
	public static int aantalDeuren = 7;
	static {
		for(int i = 0; i<aantalDeuren; i++) {
			DEUREN.put((long) i, new Deur(0, false));
		}
		int randomDoor = ThreadLocalRandom.current().nextInt(DEUREN.size());
		DEUREN.get((long)randomDoor).setContainsFry(true);
	}

	public void openDoor(Long deurnummer) {
		DEUREN.get(deurnummer).setOpened(true);
	}
	
	public boolean containsFry(Long deurnummer) {
		return DEUREN.get(deurnummer).isContainsFry();
	}
	
}