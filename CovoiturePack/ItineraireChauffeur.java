package CovoiturePack;
import java.util.List;


public class ItineraireChauffeur extends Itineraire {

    private String pointDepart;

    private List<String> pointsArrivee;


    public ItineraireChauffeur(String pointDepart, List<String> pointsArrivee) {

        this.pointDepart = pointDepart;

        this.pointsArrivee = pointsArrivee;

    }


    @Override

    public String getPointDepart() {

        return pointDepart;

    }


    @Override

    public List<String> getPointsArrivee() {

        return pointsArrivee;

    }


    @Override

    public String toString() {

        return "De " + pointDepart + " à " + String.join(", ", pointsArrivee);

    }

}