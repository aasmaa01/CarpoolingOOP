package CovoiturePack;
public class PlageHoraire {
    private String jour;
    private String heureDebut;
    private String heureFin;

    public PlageHoraire(String jour, String heureDebut, String heureFin) {
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public String getJour() { return jour; }
    public String getHeureDebut() { return heureDebut; }
    public String getHeureFin() { return heureFin; }
}