package Pn.service;

import Pn.doa.UtilisateurDoa;
import Pn.metier.Utilisateur;
import org.mindrot.jbcrypt.BCrypt; 

public class UserService {

    private UtilisateurDoa utilisateurDAO = new UtilisateurDoa(); 

    public UserService() {
    }

    public boolean register(Utilisateur u) {
        // Vérifier si l'email existe déjà
        if (utilisateurDAO.findByEmail(u.getEmail()) != null) {
            return false; // email déjà pris
        }

        // Hacher le mot de passe avec BCrypt
        String hashedPassword = BCrypt.hashpw(u.getMotDePasse(), BCrypt.gensalt());
        u.setMotDePasse(hashedPassword);

        // Valeurs par défaut
        if (u.getRole() == null) u.setRole("USER");
        if (u.getStatut() == null) u.setStatut("ACTIF");

        // Appel au DAO pour créer l'utilisateur
        Utilisateur created = utilisateurDAO.create(u);
        return created != null;
    }

    public Utilisateur authenticate(String email, String plainPassword) {
        Utilisateur u = utilisateurDAO.findByEmail(email); 
        if (u != null && BCrypt.checkpw(plainPassword, u.getMotDePasse())) {
            return u;
        }
        return null;
    }
}