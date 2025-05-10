package CovoiturePack;
//Imene 
public abstract class Utilisateur {
    private String nom;
    private String prenom;
    private String matricule;
    private String statut;
    private String pointDepart;
    //Zedt hado -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private Preference preference;
    private Disponibilite disponibilite;

    private int totalNotes = 0;
    private int nombreEvaluations = 0;

    public Utilisateur(String nom, String prenom, String matricule, String statut, String pointDepart, Preference preference, Disponibilite disponibilite) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.statut = statut;
        this.pointDepart = pointDepart;
        this.preference = preference;
        this.disponibilite = disponibilite;
    }

    public void ajouterEvaluation(int note) {
        totalNotes += note;
        nombreEvaluations++;
    }
        // Getter & Setter
    public Preference getPreference() { return preference; }
    public void setPreference(Preference preference) { this.preference = preference; }

    public Disponibilite getDisponibilite() { return disponibilite; }
    public void setDisponibilite(Disponibilite disponibilite) { this.disponibilite = disponibilite; }

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
        return nom + " " + prenom + " (" + statut + ") - Réputation : " + getReputation()
             + "\n" + preference + "\n" + disponibilite;
    }

    protected abstract String getFullName();
}