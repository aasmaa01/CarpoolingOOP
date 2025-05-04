package CovoiturePack;

import java.util.ArrayList;
import java.util.List;

public class Administration {
    private List<Utilisateur> utilisateurs;
    private List<Course> courses;

    public Administration() {
        this.utilisateurs = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public void ajouterUtilisateur(Utilisateur u) {
        utilisateurs.add(u);
    }

    public void ajouterCourse(Course c) {
        courses.add(c);
    }

    public void afficherCoursesEnCours() {
        System.out.println("===== Courses en cours =====");
        for (Course c : courses) {
            if (c.getStatut() == Course.StatutCourse.EN_COURS) {
                System.out.println("Course en cours par " + c.getConducteur().getNom());
            }
        }
    }

    public void afficherHistorique() {
        System.out.println("===== Historique des courses =====");
        for (Course c : courses) {
            if (c.getStatut() == Course.StatutCourse.TERMINEE) {
                System.out.println("Course terminée par " + c.getConducteur().getNom());
            }
        }
    }

   
}