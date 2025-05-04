package CovoiturePack;

import java.time.LocalTime;
import java.util.List;

/**
 * Classe Disponibilite pour gérer les disponibilités des utilisateurs (conducteurs/passagers).
 * @author Imene
 */
public class Disponibilite {
    

    public enum TypeDisponibilite {
        JOURNALIER,    
        HEBDOMADAIRE,   
        QUOTIDIEN       
    }

    public enum Jour {
        LUNDI, MARDI, MERCREDI, JEUDI, VENDREDI, SAMEDI, DIMANCHE
    }

    private TypeDisponibilite type;
    private List<Jour> joursDisponibles; 
    private LocalTime heureDepart;            
    private LocalTime heureRetour;            

    public Disponibilite(TypeDisponibilite type, List<Jour> joursDisponibles, String heureDepart, String heureRetour) {
        this.type = type;
        this.joursDisponibles = joursDisponibles;
        this.heureDepart = LocalTime.parse(heureDepart); // Convertir String en LocalTime
        this.heureRetour = LocalTime.parse(heureRetour); // Convertir String en LocalTime
    }

    public TypeDisponibilite getType() {
        return type;
    }

    public void setType(TypeDisponibilite type) {
        this.type = type;
    }

    public List<Jour> getJoursDisponibles() {
        return joursDisponibles;
    }

    public void setJoursDisponibles(List<Jour> joursDisponibles) {
        this.joursDisponibles = joursDisponibles;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = LocalTime.parse(heureDepart);
    }

    public LocalTime getHeureRetour() {
        return heureRetour;
    }

    public void setHeureRetour(String heureRetour) {
        this.heureRetour = LocalTime.parse(heureRetour);
    }

    public boolean estDisponible(Jour jour, LocalTime heure) {
        return joursDisponibles.contains(jour) &&
               (heure.isAfter(heureDepart) || heure.equals(heureDepart)) &&
               (heure.isBefore(heureRetour) || heure.equals(heureRetour));
    }

    @Override
    public String toString() {
        return "Disponibilité : " + type + ", Jours = " + joursDisponibles +
               ", Départ à " + heureDepart + ", Retour à " + heureRetour;
    }
}