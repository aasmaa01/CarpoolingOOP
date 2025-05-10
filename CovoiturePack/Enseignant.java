package CovoiturePack;
public class Enseignant extends Utilisateur {
    private int anneeRecrutement;
    private String faculte;

    //i add pref and dispo 
    public Enseignant(String nom, String prenom, String matricule, String statut, String pointDepart,
                      Preference pref, Disponibilite dispos,
                      int anneeRecrutement, String faculte) {
        super(nom, prenom, matricule, statut, pointDepart, pref, dispos);
        this.anneeRecrutement = anneeRecrutement;
        this.faculte = faculte;
    }


    @Override
    public String getFullName() {
        return getPrenom() + " " + getNom();
    }

    public int getAnneeRecrutement() {
        return anneeRecrutement;
    }

    public String getFaculte() {
        return faculte;
    }
}