package fr.afcepf.algeek.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import fr.afcepf.algeek.dto.Client;
import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.dto.Meteo;
import fr.afcepf.algeek.service.ClientService;
import fr.afcepf.algeek.service.MeteoService;
import lombok.extern.slf4j.Slf4j;
import net.bootsfaces.render.A;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.client.RestTemplate;

@Slf4j
@ManagedBean
@ViewScoped
public class EspacePersoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private List<Commande> historique;

	@Getter @Setter
	private String msgModif;

	@Autowired
	ConnectBean connectBean;

	@Autowired
	ClientService clientService;

	@PostConstruct
	public void init(){
		historique = getCommandesPourClient(connectBean.getClient().getId());
		for (Commande commande : historique) {
			System.out.println(commande.getDateDeLaCommande());
		}

	}
	
	public void modifInfosClient() {
		modifierClient(connectBean.getClient());
		msgModif = "Modifications enregistrées";
	}

	private List<Commande> getCommandesPourClient(Long clientId) {
		return clientService.getCommandesPourClient(clientId);
	}

	private void modifierClient(Client client) {
		clientService.modifierClient(client);
	}
}
