package CovoiturePack;

import java.util.List;

public abstract class Itineraire {

    public abstract String getPointDepart();
    
    public abstract List<String> getPointsArrivee();
    
    public abstract String toString();
    

}