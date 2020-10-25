package fr.univ_amu.iut;

import fr.univ_amu.iut.beans.Module;
import fr.univ_amu.iut.beans.Prof;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ExempleAsso1{
    // Chaine de connexion
    private static final String reqProfs =
            "SELECT NUM_PROF, NOM_PROF, PRENOM_PROF, CP_PROF, VILLE_PROF, MAT_SPEC " +
                    "FROM PROF ";

    public static void main(String[] args) throws SQLException {
        // Connexion a la base
        Connection conn = ConnexionUnique.getInstance().getConnection();

        System.out.println("Connecte\n");
        // Creation d'une instruction SQL
        Statement statement = conn.createStatement();
        // Execution de la requete
        System.out.println("Execution de la requete : " + reqProfs);

        ResultSet resultSet = statement.executeQuery(reqProfs);
        // Affichage du resultat
        ArrayList<Prof> profs = new ArrayList<>();
        while (resultSet.next()) {
            Prof my_prof = new Prof();
            my_prof.setNumProf(resultSet.getInt("NUM_PROF"));
            my_prof.setNomProf(resultSet.getString("NOM_PROF"));
            my_prof.setPrenomProf(resultSet.getString("PRENOM_PROF"));
            my_prof.setCpProf(resultSet.getString("CP_PROF"));
            my_prof.setVilleProf(resultSet.getString("VILLE_PROF"));
            Module mat_spec = new Module();
            mat_spec.setCode(resultSet.getString("MAT_SPEC"));
            my_prof.setMatSpec(mat_spec);
            profs.add(my_prof);
        }

        for (int i = 0; i < profs.size(); i++) {
            System.out.println(profs.get(i).toString());
        }
        // Fermeture de l'instruction (liberation des ressources)
        statement.close();
        System.out.println("\nOk.\n");
    }
}
