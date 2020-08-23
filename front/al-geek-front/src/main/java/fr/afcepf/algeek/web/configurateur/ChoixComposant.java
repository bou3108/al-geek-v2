package fr.afcepf.algeek.web.configurateur;

import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.TypeProduit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChoixComposant {
	
	private TypeProduit typeProduit;
	
	private boolean obligatoire;
	
	private Produit produit;
	
	private String message;
	
	public ChoixComposant(TypeProduit type) {
		typeProduit = type;
	}
}
