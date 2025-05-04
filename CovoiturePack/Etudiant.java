package CovoiturePack;
public class Etudiant extends Utilisateur {
    private int anneeAdmission;
    private String faculte;
    private String specialite;

    @Override
    public String getFullName() {
        return getNom() + " " + getPrenom();
    }

    public Etudiant(String nom, String prenom, String matricule, String statut, String pointDepart,
                    int anneeAdmission, String faculte, String specialite) {
        super(nom, prenom, matricule, statut, pointDepart);
        this.anneeAdmission = anneeAdmission;
        this.faculte = faculte;
        this.specialite = specialite;
    }

    public int getAnneeAdmission() {
        return anneeAdmission;
    }

    public String getFaculte() {
        return faculte;
    }

    public String getSpecialite() {
        return specialite;
    }
}
