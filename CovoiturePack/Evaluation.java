package CovoiturePack;

public class Evaluation {
    private Utilisateur evaluateur;
    private Utilisateur evalue;
    private int note;
    private String commentaire;

    public Evaluation(Utilisateur evaluateur, Utilisateur evalue, int note, String commentaire) {
        this.evaluateur = evaluateur;
        this.evalue = evalue;
        this.note = note;
        this.commentaire = commentaire;
    }

    public Utilisateur getEvaluateur() { return evaluateur; }
    public Utilisateur getEvalue() { return evalue; }
    public int getNote() { return note; }
    public String getCommentaire() { return commentaire; }
}