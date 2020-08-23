package fr.afcepf.algeek.tools.tri;

import fr.afcepf.algeek.dto.Produit;
import java.util.Comparator;

public class TriMarqueCroissant implements Comparator<Produit> {

	@Override
	public int compare(Produit o1, Produit o2) {
		return o1.getMarque().getNom().compareToIgnoreCase(o2.getMarque().getNom());
	}

}
