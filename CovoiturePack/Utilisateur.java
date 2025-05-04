package CovoiturePack;
//Imene 
public abstract class Utilisateur {
    private String nom;
    private String prenom;
    private String matricule;
    private String statut;
    private String pointDepart;

    private int totalNotes = 0;
    private int nombreEvaluations = 0;

    public Utilisateur(String nom, String prenom, String matricule, String statut, String pointDepart) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.statut = statut;
        this.pointDepart = pointDepart;
    }

    public void ajouterEvaluation(int note) {
        totalNotes += note;
        nombreEvaluations++;
    }

    public double getReputation() {
        if (nombreEvaluations == 0) return 0;
        return (double) totalNotes / nombreEvaluations;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getStatut() {
        return statut;
    }

    public String getPointDepart() {
        return pointDepart;
    }

    public String getMatricule() {
        return matricule;
    }

    @Override
    public String toString() {
        return nom + " " + prenom + " (" + statut + ") - Réputation : " + getReputation();
    }

    protected abstract String getFullName();
}
