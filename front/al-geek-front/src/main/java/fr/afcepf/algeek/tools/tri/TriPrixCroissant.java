package fr.afcepf.algeek.tools.tri;

import fr.afcepf.algeek.dto.Produit;
import java.util.Comparator;

public class TriPrixCroissant implements Comparator<Produit> {

	@Override
	public int compare(Produit o1, Produit o2) {
		Double o1p = o1.getPrix();
		Double o2p = o2.getPrix();
		return o1p.compareTo(o2p);
		//return o1.getPrix().compareTo(o2.getPrix());
	}

}
