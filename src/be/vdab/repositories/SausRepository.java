package be.vdab.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import be.vdab.entities.Saus;

public class SausRepository {
	private static final Map<Long, Saus> SAUZEN = new ConcurrentHashMap<>();
	static {
		SAUZEN.put(12L, new Saus(12, "cocktail", "zout", "peper", "water"));
		SAUZEN.put(16L, new Saus(16, "mayonaise", "eiwit", "dooiers", "olie"));
		SAUZEN.put(18L, new Saus(18, "mosterd"));
		SAUZEN.put(19L, new Saus(19, "tartare", "yaourt"));
		SAUZEN.put(20L, new Saus(20, "vinaigrette", "azijn", "water"));
	}
	
	public List<Saus> findAll() {
		return new ArrayList<>(SAUZEN.values());
	}
	
	public List<Saus> findByIngredient(String ingredient){
		return SAUZEN.values().stream()
				.filter(saus->saus.getIngredienten()!=null)
				.filter(saus->Arrays.asList(saus.getIngredienten()).contains(ingredient))
				.collect(Collectors.toList());
	}
	
	public void remove(Long nummer) {
		SAUZEN.remove(nummer);
	}
}
