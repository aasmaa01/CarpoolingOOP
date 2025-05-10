package CovoiturePack;

//ATS.java
public class ATS extends Utilisateur {
    private int anneeRecrutement;
    private String service;

    //i add pref and dispo
    public ATS(String nom, String prenom, String matricule, String statut, String pointDepart,
               Preference pref, Disponibilite dispos,
               int anneeRecrutement, String service) {
        super(nom, prenom, matricule, statut, pointDepart, pref, dispos);
        this.anneeRecrutement = anneeRecrutement;
        this.service = service;
    }

    public int getAnneeRecrutement() {
        return anneeRecrutement;
    }

    public String getService() {
        return service;
    }

	@Override
public String getFullName() {
    return getNom() + " " + getPrenom();
}
}