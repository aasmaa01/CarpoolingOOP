package CovoiturePack;
public class Enseignant extends Utilisateur {
    private int anneeRecrutement;
    private String faculte;

    public Enseignant(String nom, String prenom, String matricule, String statut, String pointDepart,
                      int anneeRecrutement, String faculte) {
        super(nom, prenom, matricule, statut, pointDepart);
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