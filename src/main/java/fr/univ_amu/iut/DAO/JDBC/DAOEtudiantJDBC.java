package fr.univ_amu.iut.DAO.JDBC;

import fr.univ_amu.iut.ConnexionUnique;
import fr.univ_amu.iut.DAO.DAOEtudiant;
import fr.univ_amu.iut.JDBC.ResultSetStreamer;
import fr.univ_amu.iut.JDBC.RowMappers.EtudiantMapper;
import fr.univ_amu.iut.beans.Etudiant;
import sun.security.util.ECUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class DAOEtudiantJDBC implements DAOEtudiant {
    private Connection connection;

    public DAOEtudiantJDBC() {
        connection = ConnexionUnique.getInstance().getConnection();
    }

    @Override
    public int computeNbEtudiant() {
        int result = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) N FROM ETUDIANT");
            while(resultSet.next()){
                result = resultSet.getInt('N');
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }

    @Override
    public List<Etudiant> findByAnnee(int annee) {
        return ResultSetStreamer.stream(connection, "SELECT * FROM ETUDIANT WHERE ANNEE = " + annee, new EtudiantMapper()).collect(Collectors.toList());
    }

    @Override
    public List<Etudiant> findByGroupe(int groupe) {
        return ResultSetStreamer.stream(connection, "SELECT * FROM ETUDIANT WHERE GROUPE = " + groupe, new EtudiantMapper()).collect(Collectors.toList());
    }

    @Override
    public List<Etudiant> findByNom(String nomEt) {
        return ResultSetStreamer.stream(connection, "SELECT * FROM ETUDIANT WHERE NOM_ET = " + nomEt, new EtudiantMapper()).collect(Collectors.toList());
    }

    @Override
    public boolean delete(Etudiant obj) {
        boolean result;
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM ETUDIANT WHERE NUM_ET = ";
            query += obj.getNumEt();
            ResultSet resultSet = statement.executeQuery(query);
            result = resultSet.rowDeleted();
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public List<Etudiant> findAll() {
        return ResultSetStreamer.stream(connection, "SELECT * FROM ETUDIANT", new EtudiantMapper()).collect(Collectors.toList());
    }

    @Override
    public Etudiant getById(int id) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM ETUDIANT WHERE NUM_ET =%d ",id);
            ResultSet resultSet = statement.executeQuery(query);
            Etudiant e = new EtudiantMapper().mapRow(resultSet,1);
            statement.close();
            return e;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Etudiant insert(Etudiant obj) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO ETUDIANT (NUM_ET, NOM_ET, PRENOM_ET, CP_ET, VILLE_ET, ANNEE, GROUPE) VALUES (%d, '%s', '%s', '%s', '%s', %d, %d);", obj.getNumEt(), obj.getNomEt(), obj.getPrenomEt(), obj.getCpEt(), obj.getVilleEt(), obj.getAnnee(), obj.getGroupe());
            ResultSet resultSet = statement.executeQuery(query);
            statement.close();
            if(!resultSet.rowInserted())
                return  null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public boolean update(Etudiant obj) {
        boolean result;
        try {
            Statement statement = connection.createStatement();
            String  query   = String.format("UPDATE ETUDIANT SET NUM_ET=%d, NOM_ET='%s', PRENOM_ET='%s', CP_ET='%s', VILLE_ET='%s', ANNEE=%d, GROUPE=%d WHERE NUM_ET=%d;", obj.getNumEt(), obj.getNomEt(), obj.getPrenomEt(), obj.getCpEt(), obj.getVilleEt(), obj.getAnnee(), obj.getGroupe(), obj.getNumEt());
            ResultSet resultSet = statement.executeQuery(query);
            statement.close();
            result = resultSet.rowUpdated();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
