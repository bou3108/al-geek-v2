package fr.afcepf.algeek.dto.composant;


import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.composant.Boitier;
import fr.afcepf.algeek.dto.composant.ComposantOrdinateur;

public class CarteMere extends ComposantOrdinateur {

    public static final String socket_cpuKey = "cm_socket_cpu";
    public static final String form_factorKey = "cm_form_factor";
    public static final String type_ramKey = "cm_type_ram";

    public CarteMere(Produit p) {
        super(p);
    }

    public String getSocketCpu() {
        return (String) getCaracteristique(socket_cpuKey);
    }

    public void setSocketCpu(String value) {
        setCaracteristique(socket_cpuKey, value);
    }

    public String getFormFactor() {
        return (String) getCaracteristique(form_factorKey);
    }

    public void setFormFactor(String value) {
        setCaracteristique(form_factorKey, value);
    }

    public String getTypeRam() {
        return (String) getCaracteristique(type_ramKey);
    }

    public void setTypeRam(String value) {
        setCaracteristique(type_ramKey, value);
    }

    @Override
    public boolean estCompatibleAvec(ComposantOrdinateur autre) {
        if (autre instanceof Boitier) {
            return autre.estCompatibleAvec(this);
        }

        if (autre instanceof Processeur) {
            Processeur proc = (Processeur) autre;

            if (proc.isAmd() && isCptAmd())
                return true;

            if (proc.isIntel() && isCptIntel())
                return true;

            return false;
        }

        if (autre instanceof RAM) {
            RAM ram = (RAM) autre;

            if (getTypeRam().toUpperCase().contains(ram.getType().toUpperCase()))
                return true;

            return false;
        }

        return true;
    }

    private boolean isCptIntel() {
        return getSocketCpu().toUpperCase().contains("LGA1151") || getSocketCpu().toUpperCase().contains("LGA 1151");
    }

    private boolean isCptAmd() {
        return getSocketCpu().toUpperCase().contains("AM4") || getSocketCpu().toUpperCase().contains("AM 4");
    }

}