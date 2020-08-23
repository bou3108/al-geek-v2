package fr.afcepf.algeek.web;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import fr.afcepf.algeek.dto.Categorie;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;


@ManagedBean
@ApplicationScoped
public class MenuCategoriesBean implements Serializable {

	private static final long serialVersionUID = 3325278823042584862L;

	private MenuModel model;

	@PostConstruct
	public void init() {
		model = new DefaultMenuModel();
		
		Categorie root = getRootCategorie();
		
		MenuElement submenu = construireRecursif(root);
		model.getElements().add(submenu);
		
		DefaultMenuItem configItem = new DefaultMenuItem("Montage sur mesure", "", "configurateur.xhtml");
		model.getElements().add(configItem);
		
		DefaultMenuItem achatGuideItem = new DefaultMenuItem("Achat guidé", "", "achatguide.xhtml");
		model.getElements().add(achatGuideItem);
	}

	// TODO: Remplace l'appel à getRootCategorie de CategorieService par un appel REST à product-manager
	private Categorie getRootCategorie() {
		return null;
	}

	private MenuElement construireRecursif(Categorie categorie) {
		if (categorie.getEnfants().size() == 0) {
			DefaultMenuItem item = new DefaultMenuItem(categorie.getNomAffiche());
			
			if (categorie.getTypeProduit() != null) {
				item.setParam("typeProduitId", categorie.getTypeProduit().getId());
				item.setCommand("#{catalogueBean.afficher(" + categorie.getTypeProduit().getId() + ")}");
			}
			
			return item;			
		}
		
		DefaultSubMenu sub = new DefaultSubMenu(categorie.getNomAffiche());
		
		for (Categorie c : categorie.getEnfants()) {
			sub.getElements().add(construireRecursif(c));			
		}
		
		return sub;
	}

	// ====================================================================
	// Getters/Setters

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

}
