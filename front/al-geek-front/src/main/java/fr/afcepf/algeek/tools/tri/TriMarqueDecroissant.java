package fr.afcepf.algeek.tools.tri;

import fr.afcepf.algeek.dto.Produit;
import java.util.Comparator;

public class TriMarqueDecroissant implements Comparator<Produit> {

	@Override
	public int compare(Produit o1, Produit o2) {
		return o2.getMarque().getNom().compareToIgnoreCase(o1.getMarque().getNom());
	}

}
