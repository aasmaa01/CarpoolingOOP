package CovoiturePack;

//ATS.java
public class ATS extends Utilisateur {
    private int anneeRecrutement;
    private String service;

    public ATS(String nom, String prenom, String matricule, String statut, String pointDepart,
               int anneeRecrutement, String service) {
        super(nom, prenom, matricule, statut, pointDepart);
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