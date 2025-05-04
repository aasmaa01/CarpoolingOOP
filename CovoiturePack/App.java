package CovoiturePack;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import static java.util.stream.Collectors.toList;

/**
 * Auteur : Asma
 */
public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Utilisateur> utilisateurs = new ArrayList<>();
    private static final List<Course> courses = new ArrayList<>();

    public static void main(String[] args) {
        chargerDonneesInitiales();
        afficherMenuPrincipal();
    }

    private static void chargerDonneesInitiales() {
        // Utilisateurs existants
        utilisateurs.add(new Etudiant("Soltani", "Asma", "31640602", "Etudiant", "Bab Ezzouar", 2022, "Informatique", "Acad"));
        utilisateurs.add(new Etudiant("Nwel", "Nawel", "31640607", "Etudiant", "El Harrach", 2022, "Mathematiques", "Acad"));
        utilisateurs.add(new Etudiant("Amine", "Amine", "31640606", "Etudiant", "El Harrach", 2022, "Mathematiques", "Acad"));
        utilisateurs.add(new Etudiant("Salim", "Salim", "31640605", "Etudiant", "El Harrach", 2022, "Mathematiques", "Acad"));
        utilisateurs.add(new Etudiant("Younes", "Ali", "31640601", "Etudiant", "El Harrach", 2022, "Mathematiques", "Acad"));
        utilisateurs.add(new Etudiant("Ali", "Ali", "31640603", "Etudiant", "El Harrach", 2022, "Mathematiques", "Acad"));

        utilisateurs.add(new Enseignant("Amina", "Amina", "33333333", "Enseignant", "Bab Ezzouar", 2019, "Informatique"));
        utilisateurs.add(new Enseignant("Khaled", "Khaled", "33333334", "Enseignant", "El Harrach", 2018, "Mathematiques"));
        utilisateurs.add(new Enseignant("Sofia", "Sofia", "33333335", "Enseignant", "El Harrach", 2017, "Mathematiques"));

        utilisateurs.add(new ATS("Amina", "Amina", "ATS001", "ATS", "Bab Ezzouar", 2020, "Hydra"));
        utilisateurs.add(new ATS("Slimani", "Moh", "ATS002", "ATS", "Bab Ezzouar", 2021, "Service Scolarite"));

        // Course planifiée
        Itineraire itineraire = new ItineraireChauffeur("Bab Ezzouar", List.of("Hydra"));
        Disponibilite dispo = new Disponibilite(Disponibilite.TypeDisponibilite.HEBDOMADAIRE,
            List.of(Disponibilite.Jour.LUNDI, Disponibilite.Jour.MERCREDI), "07:30", "17:00");
        Course c = new Course(utilisateurs.get(1), itineraire, dispo, Course.TypeCourse.ALLER_SIMPLE, 3, Course.StatutCourse.PLANIFIEE);
        courses.add(c);
    }

    private static void afficherMenuPrincipal() {
        int choix;
        do {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Gérer les utilisateurs");
            System.out.println("2. Gérer les courses");
            System.out.println("3. Visualiser statistiques");
            System.out.println("4. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> gererUtilisateurs();
                case 2 -> gererCourses();
                case 3 -> afficherStatistiques();
                case 4 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (choix != 4);
    }

    private static void gererUtilisateurs() {
        System.out.println("\n--- Gestion Utilisateurs ---");
        System.out.println("1. Lister");
        System.out.println("2. Ajouter");
        System.out.println("3. Supprimer");
        System.out.println("4. Retour");
        int c = scanner.nextInt(); scanner.nextLine();
        switch (c) {
            case 1 -> utilisateurs.forEach(u -> System.out.println(u));
            case 2 -> ajouterUtilisateur();
            case 3 -> {
                System.out.print("Entrez le matricule à supprimer : ");
                String mat = scanner.nextLine();
                utilisateurs.removeIf(u -> u.getMatricule().equals(mat));
                System.out.println("Suppression effectuée si le matricule existait.");
            }
            case 4 -> { /* Retour */ }
            default -> System.out.println("Choix invalide");
        }
    }

    private static void ajouterUtilisateur() {
        System.out.println("Type d'utilisateur : 1=Etudiant, 2=Enseignant, 3=ATS");
        int type = scanner.nextInt(); scanner.nextLine();
        System.out.print("Nom : "); String nom = scanner.nextLine();
        System.out.print("Prenom : "); String prenom = scanner.nextLine();
        System.out.print("Matricule : "); String mat = scanner.nextLine();
        System.out.print("Statut : "); String statut = scanner.nextLine();
        System.out.print("Point de depart : "); String point = scanner.nextLine();
        switch (type) {
            case 1 -> {
                System.out.print("Annee admission : "); int annee = scanner.nextInt(); scanner.nextLine();
                System.out.print("Faculte : "); String fac = scanner.nextLine();
                System.out.print("Specialite : "); String spec = scanner.nextLine();
                utilisateurs.add(new Etudiant(nom, prenom, mat, statut, point, annee, fac, spec));
            }
            case 2 -> {
                System.out.print("Annee recrutement : "); int anneeRec = scanner.nextInt(); scanner.nextLine();
                System.out.print("Faculte : "); String facE = scanner.nextLine();
                utilisateurs.add(new Enseignant(nom, prenom, mat, statut, point, anneeRec, facE));
            }
            case 3 -> {
                System.out.print("Annee recrutement : "); int anRec = scanner.nextInt(); scanner.nextLine();
                System.out.print("Service : "); String serv = scanner.nextLine();
                utilisateurs.add(new ATS(nom, prenom, mat, statut, point, anRec, serv));
            }
            default -> System.out.println("Type invalide");
        }
        System.out.println("Utilisateur ajoute.");
    }

    private static void gererCourses() {
        System.out.println("\n--- Gestion Courses ---");
        System.out.println("1. Lister");
        System.out.println("2. Rechercher courses compatibles pour un passager");
        System.out.println("3. Ajouter passager a une course");
        System.out.println("4. Demarrer/Terminer course");
        System.out.println("5. Retour");
        int c = scanner.nextInt(); scanner.nextLine();
        switch (c) {
            case 1 -> courses.forEach(System.out::println);
            case 2 -> rechercherCoursesPassager();
            case 3 -> ajouterPassager();
            case 4 -> changerStatutCourse();
            case 5 -> { /* Retour */ }
            default -> System.out.println("Choix invalide");
        }
    }

    private static void rechercherCoursesPassager() {
        System.out.print("Matricule du passager : ");
        String mat = scanner.nextLine();
        Utilisateur passager = utilisateurs.stream()
            .filter(u -> u.getMatricule().equals(mat)).findFirst().orElse(null);
        if (passager == null) { System.out.println("Passager non trouve"); return; }
        System.out.println("Courses compatibles :");
        courses.stream()
            .filter(c -> c.isCompatible(passager, c.getConducteur()))
            .forEach(System.out::println);
    }

    private static void ajouterPassager() {
        System.out.print("ID course (index) : ");
        int idx = scanner.nextInt(); scanner.nextLine();
        if (idx < 0 || idx >= courses.size()) { System.out.println("Index invalide"); return; }
        Course course = courses.get(idx);
        System.out.print("Matricule du passager : ");
        String mat = scanner.nextLine();
        Utilisateur p = utilisateurs.stream().filter(u -> u.getMatricule().equals(mat)).findFirst().orElse(null);
        if (p == null) { System.out.println("Passager non trouve"); return; }
        try {
            if (course.addPassager(p)) System.out.println("Ajout reussi");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private static void changerStatutCourse() {
        System.out.print("ID course (index) : ");
        int idx = scanner.nextInt(); scanner.nextLine();
        if (idx < 0 || idx >= courses.size()) { System.out.println("Index invalide"); return; }
        Course course = courses.get(idx);
        System.out.println("1. Demarrer\n2. Terminer");
        int choix = scanner.nextInt(); scanner.nextLine();
        try {
            if (choix == 1) course.demarrerCourse();
            else if (choix == 2) course.terminerCourse();
            System.out.println("Statut mis a jour : " + course.getStatut());
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private static void afficherStatistiques() {
        System.out.println("\n--- Statistiques ---");
        long nbEtudiants = utilisateurs.stream().filter(u -> u instanceof Etudiant).count();
        long nbEnseignants = utilisateurs.stream().filter(u -> u instanceof Enseignant).count();
        long nbATS = utilisateurs.stream().filter(u -> u instanceof ATS).count();
        System.out.println("Etudiants : " + nbEtudiants);
        System.out.println("Enseignants : " + nbEnseignants);
        System.out.println("ATS : " + nbATS);

        // Top 3 chauffeurs par reputation
        System.out.println("\nTop 3 utilisateurs par reputation :");
        utilisateurs.stream()
            .sorted(Comparator.comparingDouble(Utilisateur::getReputation).reversed())
            .limit(3)
            .forEach(u -> System.out.println(u + " - " + String.format("%.2f", u.getReputation())));

        // Pire 3 utilisateurs
        System.out.println("\nPire 3 utilisateurs par reputation :");
        utilisateurs.stream()
            .sorted(Comparator.comparingDouble(Utilisateur::getReputation))
            .limit(3)
            .forEach(u -> System.out.println(u + " - " + String.format("%.2f", u.getReputation())));

        // Historique des courses
        System.out.println("\nHistorique des courses :");
        courses.stream()
            .filter(c -> c.getStatut() == Course.StatutCourse.TERMINEE)
            .forEach(System.out::println);

        // Etat des courses
        System.out.println("\nEtat des courses :");
        System.out.println("Planifiees : " + courses.stream().filter(c -> c.getStatut() == Course.StatutCourse.PLANIFIEE).count());
        System.out.println("En cours : " + courses.stream().filter(c -> c.getStatut() == Course.StatutCourse.EN_COURS).count());
        System.out.println("Terminees : " + courses.stream().filter(c -> c.getStatut() == Course.StatutCourse.TERMINEE).count());
    }
}
