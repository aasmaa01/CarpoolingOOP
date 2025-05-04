package CovoiturePack;

//Course.java
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Course pour gérer les trajets dans un système de covoiturage.
 * Gère l'affectation des passagers et chauffeurs ainsi que le cycle de vie d'une course.
 * @author Asma
 */
public class Course {
    public enum TypeCourse {

        ALLER_SIMPLE,
        RETOUR_SIMPLE,
        ALLER_RETOUR
    
    }
    public enum StatutCourse { PLANIFIEE, EN_COURS, TERMINEE }

    private Utilisateur conducteur;
    private List<Utilisateur> passagers;
    private Itineraire itineraire;
    private Disponibilite disponibilite;
    private TypeCourse typeCourse;
    private int capacite;
    private StatutCourse statut;

    /**
     * Constructeur de la classe Course.
     * @param conducteur Le chauffeur de la course
     * @param itineraire Le trajet de la course
     * @param disponibilite La disponibilité de la course
     * @param typeCourse Le type de course (aller, retour, aller-retour)
     * @param capacite La capacité maximale de passagers
     * @param statut Le statut initial de la course
     */
    public Course(Utilisateur conducteur, Itineraire itineraire, Disponibilite disponibilite, 
                  TypeCourse typeCourse, int capacite, StatutCourse statut) {
        this.conducteur = conducteur;
        this.itineraire = itineraire;
        this.disponibilite = disponibilite;
        this.typeCourse = typeCourse;
        this.capacite = capacite;
        this.passagers = new ArrayList<>();
        this.statut = statut;
    }

    public Utilisateur getConducteur() {
        return conducteur;
    }

    public void setConducteur(Utilisateur conducteur) {
        this.conducteur = conducteur;
    }

    public List<Utilisateur> getPassagers() {
        return passagers;
    }

    public void setPassagers(List<Utilisateur> passagers) {
        this.passagers = passagers;
    }

    public Itineraire getItineraire() {
        return itineraire;
    }

    public void setItineraire(Itineraire itineraire) {
        this.itineraire = itineraire;
    }

    public Disponibilite getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Disponibilite disponibilite) {
        this.disponibilite = disponibilite;
    }

    public TypeCourse getTypeCourse() {
        return typeCourse;
    }

    public void setTypeCourse(TypeCourse typeCourse) {
        this.typeCourse = typeCourse;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getPlacesDisponibles() {
        return capacite - passagers.size();
    }

    public StatutCourse getStatut() {
        return statut;
    }

    /**
     * Vérifie si un passager est compatible avec le conducteur en fonction des préférences,
     * de l'itinéraire et de la disponibilité.
     * @param passager Le passager à vérifier
     * @param conducteur Le conducteur de la course
     * @return true si compatible, false sinon
     */
    public boolean isCompatible(Utilisateur passager, Utilisateur conducteur) {
        // Vérifier les préférences (par exemple, sexe, musique, bagages)
        // Note : La classe Preference n'a pas de méthode pour comparer directement les préférences
        // On suppose que Preference a une méthode isMatch() pour vérifier la compatibilité
        // À implémenter selon la logique définie par Imene
        // Placeholder for future preference compatibility logic
        // Placeholder for future preference compatibility logic

        // Vérifier les itinéraires
        Itineraire passagerItineraire = new ItinerairePassager(passager.getPointDepart(), "Destination"); // Placeholder
        boolean itineraireCompatible = itineraire.getPointDepart().equals(passagerItineraire.getPointDepart()) &&
                                       itineraire.getPointsArrivee().containsAll(passagerItineraire.getPointsArrivee());

        // Vérifier la disponibilité
        Disponibilite passagerDispo = new Disponibilite(Disponibilite.TypeDisponibilite.JOURNALIER, 
                                                        List.of(Disponibilite.Jour.LUNDI), 
                                                        "08:00", "18:00"); // Placeholder
        boolean dispoCompatible = disponibilite.getJoursDisponibles().stream()
            .anyMatch(jour -> passagerDispo.estDisponible(jour, disponibilite.getHeureDepart()));

        return itineraireCompatible && dispoCompatible; // Ajouter la vérification des préférences une fois implémentée
    }

    /**
     * Ajoute un passager à la course après vérification de la compatibilité et de la capacité.
     * @param passager Le passager à ajouter
     * @return true si l'ajout est réussi, false sinon
     */
    public boolean addPassager(Utilisateur passager) {
        if (getPlacesDisponibles() <= 0) {
            throw new IllegalStateException("Capacité de la course dépassée");
        }
        if (!isCompatible(passager, this.conducteur)) {
            throw new IllegalArgumentException("Passager incompatible avec le conducteur");
        }
        return passagers.add(passager);
    }

    public boolean removePassager(Utilisateur passager) {
        return passagers.remove(passager);
    }

    /**
     * Démarre la course en mettant à jour le statut.
     */
    public void demarrerCourse() {
        if (statut != StatutCourse.PLANIFIEE) {
            throw new IllegalStateException("La course ne peut pas démarrer");
        }
        statut = StatutCourse.EN_COURS;
    }

    /**
     * Termine la course, collecte les évaluations et met à jour la réputation.
     */
    public void terminerCourse() {
        if (statut != StatutCourse.EN_COURS) {
            throw new IllegalStateException("La course n'est pas en cours");
        }
        statut = StatutCourse.TERMINEE;
        // Post-trip: déclencher la collecte d'évaluations (à implémenter par Raghad)
        // EvaluationService.collecterEvaluations(this);
        // Mise à jour automatique de la réputation (à implémenter par Raghad)
        // ReputationService.mettreAJourReputation(conducteur);
        // for (Utilisateur p : passagers) {
        //     ReputationService.mettreAJourReputation(p);
        // }
    }

    @Override
    public String toString() {
        return "Course{" +
               "conducteur=" + conducteur.getNom() + " " + conducteur.getPrenom() +
               ", passagers=" + passagers.size() +
               ", itineraire=" + itineraire +
               ", disponibilite=" + disponibilite +
               ", typeCourse=" + typeCourse +
               '}';
    }
}