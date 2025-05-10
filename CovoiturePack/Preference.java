package CovoiturePack;
public class Preference {
    public enum SexePreference { GARCON, FILLE, INDIFFERENT }
    public enum MusiquePreference { AVEC_MUSIQUE, SANS_MUSIQUE, INDIFFERENT }
    public enum BagagePreference { AVEC_BAGAGE, SANS_BAGAGE, INDIFFERENT }

    private SexePreference sexe;
    private MusiquePreference musique;
    private BagagePreference bagage;

    public Preference(SexePreference sexe, MusiquePreference musique, BagagePreference bagage) {
        this.sexe = sexe;
        this.musique = musique;
        this.bagage = bagage;
    }

    // getters/setters omitted for brevity

    public boolean isMatch(Preference autre) {
        return match(this.sexe, autre.sexe)
            && match(this.musique, autre.musique)
            && match(this.bagage, autre.bagage);
    }

    private <T extends Enum<T>> boolean match(T a, T b) {
        // "INDIFFERENT" value matches always
        if (a.name().equals("INDIFFERENT") || b.name().equals("INDIFFERENT")) return true;
        return a.equals(b);
    }

    @Override
    public String toString() {
        return "Préférences : Sexe = " + sexe
             + ", Musique = " + musique
             + ", Bagages = " + bagage;
    }
}
