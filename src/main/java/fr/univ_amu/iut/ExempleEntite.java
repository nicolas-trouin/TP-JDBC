package fr.univ_amu.iut;

import fr.univ_amu.iut.beans.Etudiant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ExempleEntite {
    // Chaine de connexion
    private static final String reqEtudiantsAixois =
            "SELECT NUM_ET, NOM_ET, PRENOM_ET, CP_ET, VILLE_ET, ANNEE, GROUPE  " +
                    "FROM ETUDIANT " +
                    "WHERE VILLE_ET = 'AIX-EN-PROVENCE'";

    public static void main(String[] args) throws SQLException {
        // Connexion a la base
        Connection conn = ConnexionUnique.getInstance().getConnection();

        System.out.println("Connecte\n");
        // Creation d'une instruction SQL
        Statement statement = conn.createStatement();
        // Execution de la requete
        System.out.println("Execution de la requete : " + reqEtudiantsAixois);

        ResultSet resultSet = statement.executeQuery(reqEtudiantsAixois);
        // Affichage du resultat
        ArrayList<Etudiant> etudiants = new ArrayList<>();
        while (resultSet.next()) {
            Etudiant my_etu = new Etudiant();
            my_etu.setNumEt(resultSet.getInt("NUM_ET"));
            my_etu.setNomEt(resultSet.getString("NOM_ET"));
            my_etu.setPrenomEt(resultSet.getString("PRENOM_ET"));
            my_etu.setCpEt(resultSet.getString("CP_ET"));
            my_etu.setVilleEt(resultSet.getString("VILLE_ET"));
            my_etu.setAnnee(resultSet.getInt("ANNEE"));
            my_etu.setGroupe(resultSet.getInt("GROUPE"));
            etudiants.add(my_etu);
        }

        for (int i = 0; i < etudiants.size(); i++) {
            System.out.println(etudiants.get(i).toString());
        }
        // Fermeture de l'instruction (liberation des ressources)
        statement.close();
        System.out.println("\nOk.\n");
    }
}
