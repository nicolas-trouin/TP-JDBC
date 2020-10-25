package fr.univ_amu.iut;

import fr.univ_amu.iut.beans.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ExempleAsso3 {
    // Chaine de connexion
    private static final String reqEtudiantsG1 = "SELECT * FROM ETUDIANT WHERE GROUPE = 1"; // Il serait plus judicieux de filtrer aussi par Année, mais bon ...

    private static final String reqTousEnseignt = "SELECT * FROM ENSEIGNT";

    public static void main(String[] args) throws SQLException {
        // Connexion a la base
        Connection conn = ConnexionUnique.getInstance().getConnection();

        System.out.println("Connecte\n");
        // Creation d'une instruction SQL
        Statement statement = conn.createStatement();
        // Execution de la requete
        System.out.println("Execution de la requete : " + reqTousEnseignt);

        ResultSet resultSetEnseignt = statement.executeQuery(reqTousEnseignt);
        // Affichage du resultat

        AssociationEnseignement associationEnseignement = new AssociationEnseignement();

        while (resultSetEnseignt.next()) {

            Statement stmtMod = conn.createStatement();
            ResultSet resultSetMod = stmtMod.executeQuery("SELECT * FROM MODULE WHERE CODE = '" + resultSetEnseignt.getString("CODE") + "'");
            resultSetMod.next();
            Module my_module = new Module();
            my_module.setCode(resultSetMod.getString("CODE"));
            my_module.setLibelle(resultSetMod.getString("LIBELLE"));
            my_module.sethCoursPrev(resultSetMod.getInt("H_COURS_PREV"));
            my_module.sethCoursRea(resultSetMod.getInt("H_COURS_REA"));
            my_module.sethTpPrev(resultSetMod.getInt("H_TP_PREV"));
            my_module.sethTpRea(resultSetMod.getInt("H_TP_REA"));
            my_module.setDiscipline(resultSetMod.getString("DISCIPLINE"));
            my_module.setCoefTest(resultSetMod.getInt("COEFF_TEST"));
            my_module.setCoefCc(resultSetMod.getInt("COEFF_CC"));
            my_module.setCoefCc(resultSetMod.getInt("COEFF_CC"));


            Statement stmtEtu = conn.createStatement();
            ResultSet resultSetEtu = stmtEtu.executeQuery("SELECT * FROM ETUDIANT WHERE NUM_ET = " + resultSetEnseignt.getInt("NUM_ET"));
            resultSetEtu.next();
            Etudiant my_etudiant = new Etudiant();
            my_etudiant.setNumEt(resultSetEtu.getInt("NUM_ET"));
            my_etudiant.setNomEt(resultSetEtu.getString("NOM_ET"));
            my_etudiant.setPrenomEt(resultSetEtu.getString("PRENOM_ET"));
            my_etudiant.setCpEt(resultSetEtu.getString("CP_ET"));
            my_etudiant.setVilleEt(resultSetEtu.getString("VILLE_ET"));
            my_etudiant.setAnnee(resultSetEtu.getInt("ANNEE"));
            my_etudiant.setGroupe(resultSetEtu.getInt("GROUPE"));


            Statement stmtProf = conn.createStatement();
            ResultSet resultSetProf = stmtProf.executeQuery("SELECT * FROM PROF WHERE NUM_PROF = " + resultSetEnseignt.getInt("NUM_PROF"));
            resultSetProf.next();
            Prof my_prof = new Prof();
            my_prof.setNumProf(resultSetProf.getInt("NUM_PROF"));
            my_prof.setNomProf(resultSetProf.getString("NOM_PROF"));
            my_prof.setPrenomProf(resultSetProf.getString("PRENOM_PROF"));
            my_prof.setAdrProf(resultSetProf.getString("ADR_PROF"));
            my_prof.setVilleProf(resultSetProf.getString("VILLE_PROF"));
            my_prof.setCpProf(resultSetProf.getString("CP_PROF"));

            Module my_mat_spec = new Module();
            if(resultSetProf.getString("MAT_SPEC") != null) {
                Statement stmtMatSpec = conn.createStatement();
                ResultSet resultSetMatSpec = stmtMatSpec.executeQuery("SELECT * FROM MODULE WHERE CODE = '" + resultSetProf.getString("MAT_SPEC") + "'");
                resultSetMatSpec.next();
                my_mat_spec.setCode(resultSetMatSpec.getString("CODE"));
                my_mat_spec.setLibelle(resultSetMatSpec.getString("LIBELLE"));
                my_mat_spec.sethCoursPrev(resultSetMatSpec.getInt("H_COURS_PREV"));
                my_mat_spec.sethCoursRea(resultSetMatSpec.getInt("H_COURS_REA"));
                my_mat_spec.sethTpPrev(resultSetMatSpec.getInt("H_TP_PREV"));
                my_mat_spec.sethTpRea(resultSetMatSpec.getInt("H_TP_REA"));
                my_mat_spec.setDiscipline(resultSetMatSpec.getString("DISCIPLINE"));
                my_mat_spec.setCoefTest(resultSetMatSpec.getInt("COEFF_TEST"));
                my_mat_spec.setCoefCc(resultSetMatSpec.getInt("COEFF_CC"));
                my_mat_spec.setCoefCc(resultSetMatSpec.getInt("COEFF_CC"));
            }
            my_prof.setMatSpec(my_mat_spec);


            associationEnseignement.creerEnseignement(my_module, my_etudiant, my_prof);
        }


        Set<Etudiant> etudiantsG1 = new HashSet<>();
        Statement stmtEtuG1 = conn.createStatement();
        ResultSet resultSetEtuG1 = stmtEtuG1.executeQuery(reqEtudiantsG1);
        while(resultSetEtuG1.next()){
            Etudiant my_etudiant = new Etudiant();
            my_etudiant.setNumEt(resultSetEtuG1.getInt("NUM_ET"));
            my_etudiant.setNomEt(resultSetEtuG1.getString("NOM_ET"));
            my_etudiant.setPrenomEt(resultSetEtuG1.getString("PRENOM_ET"));
            my_etudiant.setCpEt(resultSetEtuG1.getString("CP_ET"));
            my_etudiant.setVilleEt(resultSetEtuG1.getString("VILLE_ET"));
            my_etudiant.setAnnee(resultSetEtuG1.getInt("ANNEE"));
            my_etudiant.setGroupe(resultSetEtuG1.getInt("GROUPE"));
            System.out.println(my_etudiant);
            System.out.println(associationEnseignement.getEnseignements(my_etudiant));
            System.out.println();
        }


        // Fermeture de l'instruction (liberation des ressources)
        statement.close();
        System.out.println("\nOk.\n");
    }
}
