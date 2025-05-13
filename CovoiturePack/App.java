package CovoiturePack;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Auteur : Asma
 */
public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Utilisateur> utilisateurs = new ArrayList<>();
    private static final List<Course> courses = new ArrayList<>();
    private static final List<Evaluation> evaluations = new ArrayList<>();
    public static void main(String[] args) {
        chargerDonneesInitiales();
        afficherMenuPrincipal();
    }

    private static void chargerDonneesInitiales() {
        // Préférences et disponibilités par défaut
        Preference prefIndiff = new Preference(
            Preference.SexePreference.INDIFFERENT,
            Preference.MusiquePreference.INDIFFERENT,
            Preference.BagagePreference.INDIFFERENT);
        List<Disponibilite.Jour> allDays = Arrays.asList(Disponibilite.Jour.values());
        Disponibilite dispoAll = new Disponibilite(
            Disponibilite.TypeDisponibilite.QUOTIDIEN,
            allDays,
            "00:00", "23:59");

        // Utilisateurs 
        utilisateurs.add(new Etudiant("Soltani", "Asma", "31640602", "Etudiant", "Birkhadem",
            prefIndiff, dispoAll, 2022, "Informatique", "Acad"));
        utilisateurs.add(new Etudiant("Nwel",   "Nawel", "31640607", "Etudiant", "El Harrach",
            prefIndiff, dispoAll, 2022, "Mathematiques", "Acad"));
        utilisateurs.add(new Etudiant("Amine",  "Amine",  "31640606", "Etudiant", "El Harrach",
            prefIndiff, dispoAll, 2022, "Mathematiques", "Acad"));
        utilisateurs.add(new Etudiant("Salim",  "Salim",  "31640605", "Etudiant", "Alger",
            prefIndiff, dispoAll, 2022, "Mathematiques", "Acad"));
        utilisateurs.add(new Etudiant("Younes", "Ali",    "31640601", "Etudiant", "El Harrach",
            prefIndiff, dispoAll, 2022, "Informatique", "Acad"));
        utilisateurs.add(new Etudiant("Ali",    "Ali",    "31640603", "Etudiant", "Alger",
            prefIndiff, dispoAll, 2022, "Informatique", "Acad"));

        utilisateurs.add(new Enseignant("Amina",  "Amina",  "33333333", "Enseignant", "Bab Ezzouar",
            prefIndiff, dispoAll, 2019, "Informatique"));
        utilisateurs.add(new Enseignant("Khaled", "Khaled", "33333334", "Enseignant", "El Harrach",
            prefIndiff, dispoAll, 2018, "Mathematiques"));
        utilisateurs.add(new Enseignant("Sofia",  "Sofia",  "33333335", "Enseignant", "Sidi Abdellah",
            prefIndiff, dispoAll, 2017, "Mathematiques"));

        utilisateurs.add(new ATS("Amina",  "Amina",  "ATS001", "ATS", "Sidi Abdellah",
            prefIndiff, dispoAll, 2020, "Hydra"));
        utilisateurs.add(new ATS("Slimani","Moh",    "ATS002", "ATS", "Bab Ezzouar",
            prefIndiff, dispoAll, 2021, "Service Scolarite"));

            
        // Création de 2 courses planifiées
        Itineraire itin1 = new ItineraireChauffeur("Birkhadem", List.of("Hydra"));
        Disponibilite d1 = new Disponibilite(
            Disponibilite.TypeDisponibilite.HEBDOMADAIRE,
            List.of(Disponibilite.Jour.LUNDI, Disponibilite.Jour.MERCREDI),
            "07:30", "17:00");
        Course c1 = new Course(
            utilisateurs.get(0), // Asma
            itin1,
            d1,
            Course.TypeCourse.ALLER_SIMPLE,
            4,
            Course.StatutCourse.PLANIFIEE);
            courses.add(c1);

            for (int i = 1; i <= 3; i++) {
            Utilisateur p = utilisateurs.get(i);
            if (c1.isCompatible(p, c1.getConducteur())) {
                c1.addPassager(p);
            } else {
            System.out.println("[Init] Passager " 
                + p.getFullName() 
                + " non compatible pour la course, ignoré.");
        }
    }

        Itineraire itin2 = new ItineraireChauffeur("El Harrach", List.of("Alger"));
        Disponibilite d2 = new Disponibilite(
            Disponibilite.TypeDisponibilite.QUOTIDIEN,
            allDays,
            "08:00", "18:00");
        Course c2 = new Course(
            utilisateurs.get(1), // Khaled
            itin2,
            d2,
            Course.TypeCourse.RETOUR_SIMPLE,
            4, // capacité max 4
            Course.StatutCourse.TERMINEE
        );
        courses.add(c2);

        for (Utilisateur p : List.of(utilisateurs.get(4), utilisateurs.get(5), utilisateurs.get(6))) {
            if (c2.isCompatible(p, c2.getConducteur())) {
                c2.addPassager(p);
            } else {
                System.out.println("[Init] Passager " 
                    + p.getFullName() 
                    + " non compatible pour c2, ignoré.");
        }
        }

        //  2 courses terminées
        Course finished1 = new Course(
            utilisateurs.get(3), // Salim
            itin1,
            d1,
            Course.TypeCourse.ALLER_SIMPLE,
            2,
            Course.StatutCourse.PLANIFIEE);
        finished1.demarrerCourse();
        finished1.terminerCourse();
        courses.add(finished1);

        Course finished2 = new Course(
            utilisateurs.get(4), // Younes
            itin2,
            d2,
            Course.TypeCourse.ALLER_RETOUR,
            4,
            Course.StatutCourse.PLANIFIEE);
        finished2.demarrerCourse();
        finished2.terminerCourse();
        courses.add(finished2);
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
            choix = scanner.nextInt(); scanner.nextLine();

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
            case 1 -> utilisateurs.forEach(System.out::println);
            case 2 -> ajouterUtilisateur();
            case 3 -> {
                System.out.print("Entrez le matricule à supprimer : ");
                String mat = scanner.nextLine();
                utilisateurs.removeIf(u -> u.getMatricule().equals(mat));
                System.out.println("Suppression effectuée si le matricule existait.");
            }
            case 4 -> {/* retour */}
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
        // Préférences et dispos par défaut
        Preference pref = new Preference(
            Preference.SexePreference.INDIFFERENT,
            Preference.MusiquePreference.INDIFFERENT,
            Preference.BagagePreference.INDIFFERENT);
        Disponibilite dispo = new Disponibilite(
            Disponibilite.TypeDisponibilite.QUOTIDIEN,
            Arrays.asList(Disponibilite.Jour.values()),
            "00:00", "23:59");
        switch (type) {
            case 1 -> {
                System.out.print("Annee admission : "); int annee = scanner.nextInt(); scanner.nextLine();
                System.out.print("Faculte : "); String fac = scanner.nextLine();
                System.out.print("Specialite : "); String spec = scanner.nextLine();
                utilisateurs.add(new Etudiant(nom, prenom, mat, statut, point, pref, dispo,
                    annee, fac, spec));
            }
            case 2 -> {
                System.out.print("Annee recrutement : "); int anRec = scanner.nextInt(); scanner.nextLine();
                System.out.print("Faculte : "); String facE = scanner.nextLine();
                utilisateurs.add(new Enseignant(nom, prenom, mat, statut, point, pref, dispo,
                    anRec, facE));
            }
            case 3 -> {
                System.out.print("Annee recrutement : "); int anRec2 = scanner.nextInt(); scanner.nextLine();
                System.out.print("Service : "); String serv = scanner.nextLine();
                utilisateurs.add(new ATS(nom, prenom, mat, statut, point, pref, dispo,
                    anRec2, serv));
            }
            default -> System.out.println("Type invalide");
        }
        System.out.println("Utilisateur ajouté.");
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
            case 5 -> { 
                // retour
                
            }
            default -> System.out.println("Choix invalide");
        }
    }

    private static void rechercherCoursesPassager() {
        System.out.print("Matricule du passager : ");
        String mat = scanner.nextLine();
        Utilisateur passager = utilisateurs.stream()
            .filter(u -> u.getMatricule().equals(mat)).findFirst().orElse(null);
        if (passager == null) { System.out.println("Passager non trouve"); return; }
        System.out.println("Courses compatibles :");
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
        Utilisateur p = utilisateurs.stream()
            .filter(u -> u.getMatricule().equals(mat)).findFirst().orElse(null);
        if (p == null) { System.out.println("Passager non trouve"); return; }
        try {
            if (course.addPassager(p)) System.out.println("Ajout reussi");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private static void changerStatutCourse() {
        System.out.print("ID course (index) : ");
        int idx = scanner.nextInt(); scanner.nextLine();
        if (idx < 0 || idx >= courses.size()) { System.out.println("Index invalide"); return; }
        Course course = courses.get(idx);
        System.out.println("1. Démarrer\n2. Terminer");
        int choix = scanner.nextInt(); scanner.nextLine();
        try {
            if (choix == 1) course.demarrerCourse();
            else if (choix == 2) {
                course.terminerCourse();
                System.out.println("Statut mis à jour : " + course.getStatut());
                saisirEvaluations(course);
            }
            System.out.println("Statut mis à jour : " + course.getStatut());
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
    private static void saisirEvaluations(Course course) {
        System.out.println("\n--- Évaluations de la course ---");
        // Passagers notent le conducteur
        for (Utilisateur passager : course.getPassagers()) {
            System.out.print(passager.getFullName() + ", notez le conducteur (0-5) : ");
            int note = scanner.nextInt(); scanner.nextLine();
            System.out.print("Commentaire : ");
            String com = scanner.nextLine();
            evaluations.add(new Evaluation(passager, course.getConducteur(), note, com));
            course.getConducteur().ajouterEvaluation(note);
        }
        // Conducteur note les passagers
        Utilisateur conducteur = course.getConducteur();
        for (Utilisateur passager : course.getPassagers()) {
            System.out.print(conducteur.getFullName() + ", notez le passager " + passager.getFullName() + " (0-5) : ");
            int note = scanner.nextInt(); scanner.nextLine();
            System.out.print("Commentaire : ");
            String com = scanner.nextLine();
            evaluations.add(new Evaluation(conducteur, passager, note, com));
            passager.ajouterEvaluation(note);
        }
        System.out.println("Évaluations enregistrées.\n");
    }


        private static void bannirUtilisateurs() {
        List<Utilisateur> toBan = utilisateurs.stream()
            .filter(u -> u.getReputation() < 2.0)
            .collect(Collectors.toList());
        utilisateurs.removeAll(toBan);
        System.out.println("\n--- Utilisateurs bannis (réputation < 2.0) ---");
        toBan.forEach(u -> System.out.println(u.getFullName() + " (" + String.format("%.2f", u.getReputation()) + ")"));
        System.out.println();
    }

    
    private static void afficherStatistiques() {
        int choix;
        do {
            System.out.println("\n--- Statistiques ---");
            System.out.println("1. Statistiques générales");
            System.out.println("2. Utilisateurs actifs ce mois");
            System.out.println("3. Top/Pire 10 par réputation");
            System.out.println("4. Historique et état des courses");
            System.out.println("5. Afficher planning");
            System.out.println("6. Liste des courses en cours");
            System.out.println("7. Bannir utilisateurs (réputation < 2.0)");
            System.out.println("8. Retour");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt(); scanner.nextLine();
            switch (choix) {
                case 1 -> statistiquesGenerales();
                case 2 -> afficherActifsMois();
                case 3 -> topPireDix();
                case 4 -> historiqueEtEtat();
                case 5 -> afficherPlanning();
                case 6 -> coursesEnCours();
                case 7 -> bannirUtilisateurs();
                case 8 -> { break; }
                default -> System.out.println("Choix invalide");
            }
        } while (choix != 8);
    }


    private static void statistiquesGenerales() {
        long nbEt = utilisateurs.stream().filter(u -> u instanceof Etudiant).count();
        long nbEns = utilisateurs.stream().filter(u -> u instanceof Enseignant).count();
        long nbATS = utilisateurs.stream().filter(u -> u instanceof ATS).count();
        System.out.println("Etudiants : " + nbEt + ", Enseignants : " + nbEns + ", ATS : " + nbATS);
        // Catégorie qui propose le plus de courses
        Map<String, Long> byCat = courses.stream()
            .map(Course::getConducteur)
            .collect(Collectors.groupingBy(u -> {
                if (u instanceof Etudiant) return "Etudiant";
                if (u instanceof Enseignant) return "Enseignant";
                return "ATS";
            }, Collectors.counting()));
        byCat.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .ifPresent(e -> System.out.println("Catégorie proposant le plus de courses : " + e.getKey() + " (" + e.getValue() + ")"));
        // Faculté la plus active
        Map<String, Long> byFac = courses.stream()
            .map(Course::getConducteur)
            .filter(u -> u instanceof Etudiant || u instanceof Enseignant)
            .map(u -> (u instanceof Etudiant) ? ((Etudiant) u).getFaculte() : ((Enseignant) u).getFaculte())
            .collect(Collectors.groupingBy(f -> f, Collectors.counting()));
        byFac.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .ifPresent(e -> System.out.println("Faculté la plus active : " + e.getKey() + " (" + e.getValue() + " courses)"));
    }

    private static void afficherActifsMois() {
    System.out.println("\n--- Utilisateurs actifs ce mois ---");
    Month moisCourant = LocalDate.now().getMonth();
    Set<Utilisateur> actifs = courses.stream()
        .filter(c -> c.getDateCreation().getMonth() == moisCourant)
        .flatMap(c -> Stream.concat(
            Stream.of(c.getConducteur()),
            c.getPassagers().stream()))
        .collect(Collectors.toSet());
    if (actifs.isEmpty()) {
        System.out.println("Aucun utilisateur actif ce mois.");
    } else {
        actifs.forEach(u -> System.out.println(u.getFullName()));
    }
}


    private static void topPireDix() {
        System.out.println("\n--- Top 10 utilisateurs par réputation ---");
        utilisateurs.stream()
            .sorted(Comparator.comparingDouble(Utilisateur::getReputation).reversed())
            .limit(10)
            .forEach(u -> System.out.println(u.getFullName() + " (" + String.format("%.2f", u.getReputation()) + ")"));
        System.out.println("\n--- Pire 10 utilisateurs par réputation ---");
        utilisateurs.stream()
            .sorted(Comparator.comparingDouble(Utilisateur::getReputation))
            .limit(10)
            .forEach(u -> System.out.println(u.getFullName() + " (" + String.format("%.2f", u.getReputation()) + ")"));
    }

    private static void historiqueEtEtat() {
        System.out.println("\n--- Historique des courses terminées :");
        courses.stream().filter(c -> c.getStatut() == Course.StatutCourse.TERMINEE)
            .forEach(System.out::println);
        long nbPlanifiees = courses.stream()
            .filter(c -> c.getStatut() == Course.StatutCourse.PLANIFIEE)
            .count();
        System.out.println("État : planifiées = " + nbPlanifiees);
    }

    private static void coursesEnCours() {
        System.out.println("\n--- Courses en cours ---");
        courses.stream()
            .filter(c -> c.getStatut() == Course.StatutCourse.EN_COURS)
            .forEach(System.out::println);
    }



    private static void afficherPlanning() {
        System.out.println("\n--- Planning des courses ---");
        Map<Disponibilite.Jour, List<Course>> parJour = courses.stream()
            .flatMap(c -> c.getDisponibilite().getJoursDisponibles().stream()
                .map(j -> Map.entry(j, c)))
            .collect(Collectors.groupingBy(Map.Entry::getKey,
                Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

        for (Disponibilite.Jour jour : parJour.keySet()) {
            System.out.println(jour + ":");
            for (Course c : parJour.get(jour)) {
                LocalTime hd = c.getDisponibilite().getHeureDepart();
                LocalTime hr = c.getDisponibilite().getHeureRetour();
                System.out.println("  - [" + hd + "-" + hr + "] " + c);
            }
        }
    }
}
