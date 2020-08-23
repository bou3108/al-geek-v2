package fr.afcepf.algeek.dto.composant;

import fr.afcepf.algeek.dto.Produit;

public class OrdinateurPortable extends ComposantOrdinateur {

    public static final String proc_typeKey = "ordi_port_proc_type";
    public static final String proc_caracKey = "ordi_port_proc_carac";
    public static final String proc_freqKey = "ordi_port_proc_freq";
    public static final String ram_tailleKey = "ordi_port_ram_taille";

    public OrdinateurPortable(Produit p) {
        super(p);
    }

    public String getProcType() {
        return (String) getCaracteristique(proc_typeKey);
    }

    public void setProcType(String value) {
        setCaracteristique(proc_typeKey, value);
    }

    public String getProcCarac() {
        return (String) getCaracteristique(proc_caracKey);
    }

    public void setProcCarac(String value) {
        setCaracteristique(proc_caracKey, value);
    }

    public Double getProcFreq() {
        return (Double) getCaracteristique(proc_freqKey);
    }

    public void setProcFreq(Double value) {
        setCaracteristique(proc_freqKey, value);
    }

    public Double getRamTaille() {
        return (Double) getCaracteristique(ram_tailleKey);
    }

    public void setRamTaille(Double value) {
        setCaracteristique(ram_tailleKey, value);
    }

    @Override
    public boolean estCompatibleAvec(ComposantOrdinateur autre) {
        return true;
    }

}