package fr.afcepf.algeek.tools.tri;

import fr.afcepf.algeek.dto.Produit;
import java.util.Comparator;

public class TriPrixDecroissant implements Comparator<Produit> {

	@Override
	public int compare(Produit o1, Produit o2) {
		Double o1p = o1.getPrix();
		Double o2p = o2.getPrix();
		return o2p.compareTo(o1p);
		//return o2.getPrix().compareTo(o1.getPrix());
	}

}
