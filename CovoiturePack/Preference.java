package CovoiturePack;
public class Preference {

    public enum SexePreference {

        GARÇON,

        FILLE,

        INDIFFÉRENT

    }


    public enum MusiquePreference {

        AVEC_MUSIQUE,

        SANS_MUSIQUE,

        INDIFFÉRENT

    }


    public enum BagagePreference {

        AVEC_BAGAGE,

        SANS_BAGAGE,

        INDIFFÉRENT

    }


    private SexePreference sexePreference;

    private MusiquePreference musiquePreference;

    private BagagePreference bagagePreference;


    public Preference(SexePreference sexePreference, MusiquePreference musiquePreference, BagagePreference bagagePreference) {

        this.sexePreference = sexePreference;

        this.musiquePreference = musiquePreference;

        this.bagagePreference = bagagePreference;

    }


    public SexePreference getSexePreference() {

        return sexePreference;

    }


    public void setSexePreference(SexePreference sexePreference) {

        this.sexePreference = sexePreference;

    }


    public MusiquePreference getMusiquePreference() {

        return musiquePreference;

    }


    public void setMusiquePreference(MusiquePreference musiquePreference) {

        this.musiquePreference = musiquePreference;

    }


    public BagagePreference getBagagePreference() {

        return bagagePreference;

    }


    public void setBagagePreference(BagagePreference bagagePreference) {

        this.bagagePreference = bagagePreference;

    }


    @Override

    public String toString() {

        return "Préférences : Sexe = " + sexePreference +

               ", Musique = " + musiquePreference +

               ", Bagages = " + bagagePreference;

    }

}