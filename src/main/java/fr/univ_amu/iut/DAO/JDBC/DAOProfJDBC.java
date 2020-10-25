package fr.univ_amu.iut.DAO.JDBC;

import fr.univ_amu.iut.ConnexionUnique;
import fr.univ_amu.iut.DAO.DAOProf;
import fr.univ_amu.iut.JDBC.ResultSetStreamer;
import fr.univ_amu.iut.JDBC.RowMappers.ProfMapper;
import fr.univ_amu.iut.beans.Module;
import fr.univ_amu.iut.beans.Prof;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public final class DAOProfJDBC implements DAOProf {
    private Connection connection;

    public DAOProfJDBC() {
        connection = ConnexionUnique.getInstance().getConnection();
    }

    @Override
    public int computeNbProf() {
        int result = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) N FROM PROF");
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
    public List<Prof> findByMatSpec(Module matSpec) {
        return ResultSetStreamer.stream(connection, "SELECT * FROM PROF WHERE MAT_SPEC = " + matSpec, new ProfMapper()).collect(Collectors.toList());
    }

    @Override
    public List<Prof> findByNom(String nomEt) {
        return ResultSetStreamer.stream(connection, "SELECT * FROM PROF WHERE NOM_ET = " + nomEt, new ProfMapper()).collect(Collectors.toList());
    }

    @Override
    public boolean delete(Prof obj) {
        boolean result;
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM PROF WHERE NUM_PROF = ";
            query += obj.getNumProf();
            ResultSet resultSet = statement.executeQuery(query);
            result = resultSet.rowDeleted();
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public List<Prof> findAll() {
        return ResultSetStreamer.stream(connection, "SELECT * FROM PROF", new ProfMapper()).collect(Collectors.toList());
    }

    @Override
    public Prof getById(int id) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM PROF WHERE NUM_PROF =%d ",id);
            ResultSet resultSet = statement.executeQuery(query);
            Prof p = new ProfMapper().mapRow(resultSet,1);
            statement.close();
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Prof insert(Prof obj) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO PROF (NUM_PROF, NOM_PROF, PRENOM_PROF, CP_PROF, VILLE_PROF, ADR_PROF, MAT_SPEC) VALUES (%d, '%s', '%s', '%s', '%s', %s, %s);", obj.getNumProf(), obj.getNomProf(), obj.getPrenomProf(), obj.getCpProf(), obj.getVilleProf(), obj.getAdrProf(), obj.getMatSpec());
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
    public boolean update(Prof obj) {
        boolean result;
        try {
            Statement statement = connection.createStatement();
            String  query   = String.format("UPDATE PROF SET NUM_PROF=%d, NOM_PROF='%s', PRENOM_PROF='%s', CP_PROF='%s', VILLE_PROF='%s', ADR_PROF=%d, MAT_SPEC=%d WHERE NUM_PROF=%d;", obj.getNumProf(), obj.getNomProf(), obj.getPrenomProf(), obj.getCpProf(), obj.getVilleProf(), obj.getAdrProf(), obj.getMatSpec());
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

