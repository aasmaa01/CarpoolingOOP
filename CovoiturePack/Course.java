package CovoiturePack;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate; 

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
    public enum StatutCourse {
        PLANIFIEE,
        EN_COURS,
        TERMINEE
    }

    private Utilisateur conducteur;
    private List<Utilisateur> passagers;
    private Itineraire itineraire;
    private Disponibilite disponibilite;
    private TypeCourse typeCourse;
    private int capacite;
    private StatutCourse statut;
    private LocalDate dateCreation;
public LocalDate getDateCreation() { return dateCreation; }

    /**
     * Constructeur de la classe Course.
     * @param conducteur Le chauffeur de la course
     * @param itineraire Le trajet de la course
     * @param disponibilite La disponibilité de la course
     * @param typeCourse Le type de course (aller, retour, aller-retour)
     * @param capacite La capacité maximale de passagers
     * @param statut Le statut initial de la course
     */
    public Course(Utilisateur conducteur,
                  Itineraire itineraire,
                  Disponibilite disponibilite,
                  TypeCourse typeCourse,
                  int capacite,
                  StatutCourse statut) {
        this.conducteur = conducteur;
        this.itineraire = itineraire;
        this.disponibilite = disponibilite;
        this.typeCourse = typeCourse;
        this.capacite = capacite;
        this.passagers = new ArrayList<>();
        this.statut = statut;
        this.dateCreation= LocalDate.now();
    }

    // --- Getters & setters ---
    public Utilisateur getConducteur() { return conducteur; }
    public void setConducteur(Utilisateur conducteur) { this.conducteur = conducteur; }

    public List<Utilisateur> getPassagers() { return passagers; }
    public void setPassagers(List<Utilisateur> passagers) { this.passagers = passagers; }

    public Itineraire getItineraire() { return itineraire; }
    public void setItineraire(Itineraire itineraire) { this.itineraire = itineraire; }

    public Disponibilite getDisponibilite() { return disponibilite; }
    public void setDisponibilite(Disponibilite disponibilite) { this.disponibilite = disponibilite; }

    public TypeCourse getTypeCourse() { return typeCourse; }
    public void setTypeCourse(TypeCourse typeCourse) { this.typeCourse = typeCourse; }

    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }

    public int getPlacesDisponibles() { return capacite - passagers.size(); }

    public StatutCourse getStatut() { return statut; }

    // --- Méthodes métiers ---

    /**
     * Vérifie si un passager est compatible avec le conducteur en fonction des préférences,
     * de l'itinéraire et de la disponibilité.
     * @param passager   Le passager à vérifier
     * @param conducteur Le conducteur de la course
     * @return true si compatible, false sinon
     */
    public boolean isCompatible(Utilisateur passager, Utilisateur conducteur) {
        // a) Itinéraire : on accepte les passagers partant du point de départ du conducteur
        // ou partant d'un point d'arrivée (trajet retour)
        String depCond = itineraire.getPointDepart();
        List<String> arrCond = itineraire.getPointsArrivee();
        String depPass = passager.getPointDepart();
        boolean itinOk = depCond.equals(depPass) || arrCond.contains(depPass);
        if (!itinOk) return false;

        // b) Préférences
        if (!conducteur.getPreference().isMatch(passager.getPreference())) {
            return false;
        }

        // c) Disponibilités
        Disponibilite dispoCond = this.disponibilite;
        Disponibilite dispoPass = passager.getDisponibilite();
        for (Disponibilite.Jour jour : dispoCond.getJoursDisponibles()) {
            LocalTime heure = dispoCond.getHeureDepart();
            if (!dispoPass.estDisponible(jour, heure)) {
                return false;
            }
        }
        return true;
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
    }

    @Override
    public String toString() {
        return "Course{" +
               "conducteur=" + conducteur.getNom() + " " + conducteur.getPrenom() +
               ", passagers=" + passagers.size() +
               ", itineraire=" + itineraire +
               ", disponibilite=" + disponibilite +
               ", typeCourse=" + typeCourse +
               ", statut=" + statut +
               '}';
    }
}
