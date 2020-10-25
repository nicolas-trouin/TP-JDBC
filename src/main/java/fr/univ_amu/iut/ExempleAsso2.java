package fr.univ_amu.iut;

import fr.univ_amu.iut.beans.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ExempleAsso2{
    // Chaine de connexion
    private static final String reqTousEtudiants = "SELECT * FROM ETUDIANT";

    private static final String reqTousModules = "SELECT * FROM MODULE";

    private static final String reqToutesNotes = "SELECT * FROM NOTATION";

    public static void main(String[] args) throws SQLException {
        // Connexion a la base
        Connection conn = ConnexionUnique.getInstance().getConnection();

        System.out.println("Connecte\n");
        // Creation d'une instruction SQL
        Statement statement = conn.createStatement();
        // Execution de la requete
        System.out.println("Execution de la requete : " + reqTousEtudiants);

        ResultSet resultSetEtu = statement.executeQuery(reqTousEtudiants);
        // Affichage du resultat
        ArrayList<Etudiant> etudiants = new ArrayList<>();
        while (resultSetEtu.next()) {
            Etudiant my_etudiant = new Etudiant();
            my_etudiant.setNumEt(resultSetEtu.getInt("NUM_ET"));
            my_etudiant.setNomEt(resultSetEtu.getString("NOM_ET"));
            my_etudiant.setPrenomEt(resultSetEtu.getString("PRENOM_ET"));
            my_etudiant.setCpEt(resultSetEtu.getString("CP_ET"));
            my_etudiant.setVilleEt(resultSetEtu.getString("VILLE_ET"));
            my_etudiant.setAnnee(resultSetEtu.getInt("ANNEE"));
            my_etudiant.setGroupe(resultSetEtu.getInt("GROUPE"));
            etudiants.add(my_etudiant);
        }


        // Execution de la requete
        System.out.println("Execution de la requete : " + reqTousModules);

        ResultSet resultSetMod = statement.executeQuery(reqTousModules);
        // Affichage du resultat
        ArrayList<Module> modules = new ArrayList<>();
        while (resultSetMod.next()) {
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
            Prof my_resp = new Prof();
            my_resp.setNumProf(resultSetMod.getInt("RESP"));
            if(my_resp.getNumProf() != 0) {
                Statement stmtProf = conn.createStatement();
                String query = "SELECT * FROM PROF WHERE NUM_PROF = " + my_resp.getNumProf();
                ResultSet resultSetProf = stmtProf.executeQuery(query);
                resultSetProf.next();
                my_resp.setNomProf(resultSetProf.getString("NOM_PROF"));
            }
            my_module.setResponsable(my_resp);
            Module my_pere = new Module();
            my_pere.setCode(resultSetMod.getString("CODEPERE"));
            my_module.setPere(my_pere);
            modules.add(my_module);
        }

        // Execution de la requete
        System.out.println("Execution de la requete : " + reqToutesNotes);

        ResultSet resultSetNotes = statement.executeQuery(reqToutesNotes);
        // Affichage du resultat
        ArrayList<Notation> notes = new ArrayList<>();

        while (resultSetNotes.next()) {
            Notation my_note = new Notation();
            my_note.setMoyCC(resultSetNotes.getInt("MOY_CC"));
            my_note.setMoyTest(resultSetNotes.getInt("MOY_TEST"));


            Etudiant my_etu = new Etudiant();
            Statement stmtEtu = conn.createStatement();
            ResultSet resultSetEtuNotes = stmtEtu.executeQuery("SELECT * FROM ETUDIANT WHERE NUM_ET = " + resultSetNotes.getInt("NUM_ET"));
            resultSetEtuNotes.next();
            my_etu.setNumEt(resultSetEtuNotes.getInt("NUM_ET"));
            my_etu.setNomEt(resultSetEtuNotes.getString("NOM_ET"));
            my_etu.setPrenomEt(resultSetEtuNotes.getString("PRENOM_ET"));
            my_etu.setCpEt(resultSetEtuNotes.getString("CP_ET"));
            my_etu.setVilleEt(resultSetEtuNotes.getString("VILLE_ET"));
            my_etu.setAnnee(resultSetEtuNotes.getInt("ANNEE"));
            my_etu.setGroupe(resultSetEtuNotes.getInt("GROUPE"));
            my_note.setEtudiant(my_etu);


            Module my_module = new Module();
            Statement stmtModNotes = conn.createStatement();
            ResultSet resultSetModNotes = stmtModNotes.executeQuery("SELECT * FROM MODULE WHERE CODE = '" + resultSetNotes.getString("CODE") + "'");
            resultSetModNotes.next();
            my_module.setCode(resultSetModNotes.getString("CODE"));
            my_module.setLibelle(resultSetModNotes.getString("LIBELLE"));
            my_module.sethCoursPrev(resultSetModNotes.getInt("H_COURS_PREV"));
            my_module.sethCoursRea(resultSetModNotes.getInt("H_COURS_REA"));
            my_module.sethTpPrev(resultSetModNotes.getInt("H_TP_PREV"));
            my_module.sethTpRea(resultSetModNotes.getInt("H_TP_REA"));
            my_module.setDiscipline(resultSetModNotes.getString("DISCIPLINE"));
            my_module.setCoefTest(resultSetModNotes.getInt("COEFF_TEST"));
            my_module.setCoefCc(resultSetModNotes.getInt("COEFF_CC"));
            my_module.setCoefCc(resultSetModNotes.getInt("COEFF_CC"));
            Prof my_respo = new Prof();
            my_respo.setNumProf(resultSetModNotes.getInt("RESP"));
            if(my_respo.getNumProf() != 0) {
                Statement stmtProfNotes = conn.createStatement();
                String query = "SELECT * FROM PROF WHERE NUM_PROF = " + my_respo.getNumProf();
                ResultSet resultSetProfNotes = stmtProfNotes.executeQuery(query);
                resultSetProfNotes.next();
                my_respo.setNomProf(resultSetProfNotes.getString("NOM_PROF"));
            }
            my_module.setResponsable(my_respo);
            Module my_pere = new Module();
            my_pere.setCode(resultSetModNotes.getString("CODEPERE"));
            my_module.setPere(my_pere);
            my_note.setModule(my_module);

            notes.add(my_note);
        }

        AssociationNotation associationNotation = new AssociationNotation();

        for(Notation note : notes){
            associationNotation.creerLien(note.getModule(), note.getEtudiant(), note);
        }

        Module acsi = new Module();
        for(Module module : modules){
            if(module.getCode().equals("ACSI")){
                acsi = module;
                break;
            }
        }

        for(Lien lien : associationNotation.getLiens(acsi)){
            System.out.println("Etudiant : " + lien.getEtudiant().getPrenomEt() + " " + lien.getEtudiant().getNomEt());
            System.out.println("Notes en ACSI : " + lien.getNotation());
            System.out.println();
        }


        // Fermeture de l'instruction (liberation des ressources)
        statement.close();
        System.out.println("\nOk.\n");
    }
}
