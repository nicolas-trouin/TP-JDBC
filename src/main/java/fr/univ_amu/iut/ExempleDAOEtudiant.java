package fr.univ_amu.iut;

import fr.univ_amu.iut.DAO.JDBC.DAOEtudiantJDBC;
import fr.univ_amu.iut.beans.Etudiant;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExempleDAOEtudiant {
    public static void main(String[] args) throws SQLException {
        DAOEtudiantJDBC daoEtudiantJDBC = new DAOEtudiantJDBC();
        List<Etudiant> etudiantList = new ArrayList<>();
        etudiantList = daoEtudiantJDBC.findByAnnee(2);

        for (int i = 0; i < etudiantList.size(); i++) {
            System.out.println(etudiantList.get(i).toString());
        }
    }
}
