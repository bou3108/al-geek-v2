package fr.afcepf.algeek.tools.tri;

import fr.afcepf.algeek.dto.Produit;
import java.util.Comparator;

public class TriNomAlphabetique implements Comparator<Produit> {

	@Override
	public int compare(Produit o1, Produit o2) {
		return o1.getNom().compareTo(o2.getNom());
	}

	
}
