package CovoiturePack;
import java.util.Collections;
import java.util.List;


public class ItinerairePassager extends Itineraire {

    private String pointDepart;

    private String pointArrivee;


    public ItinerairePassager(String pointDepart, String pointArrivee) {

        this.pointDepart = pointDepart;

        this.pointArrivee = pointArrivee;

    }


    @Override

    public String getPointDepart() {

        return pointDepart;

    }


    @Override

    public List<String> getPointsArrivee() {

        return Collections.singletonList(pointArrivee); 

    }


    @Override

    public String toString() {

        return "De " + pointDepart + " à " + pointArrivee;

    }

}