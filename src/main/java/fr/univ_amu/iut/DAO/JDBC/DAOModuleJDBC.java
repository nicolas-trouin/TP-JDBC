package fr.univ_amu.iut.DAO.JDBC;

import fr.univ_amu.iut.ConnexionUnique;
import fr.univ_amu.iut.DAO.DAOModule;
import fr.univ_amu.iut.JDBC.ResultSetStreamer;
import fr.univ_amu.iut.JDBC.RowMappers.ModuleMapper;
import fr.univ_amu.iut.beans.Module;
import fr.univ_amu.iut.beans.Prof;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public final class DAOModuleJDBC implements DAOModule {
    private Connection connection;

    public DAOModuleJDBC() {
        connection = ConnexionUnique.getInstance().getConnection();
    }

    @Override
    public List<Module> findByLibelle(String libelle) {
        return ResultSetStreamer.stream(connection, "SELECT * FROM MODULE WHERE LIBELLE = " + libelle, new ModuleMapper()).collect(Collectors.toList());
    }

    @Override
    public List<Module> findByDiscipline(String discipline) {
        return ResultSetStreamer.stream(connection, "SELECT * FROM MODULE WHERE DISCIPLINE = " + discipline, new ModuleMapper()).collect(Collectors.toList());
    }

    @Override
    public List<Module> findByResponsable(Prof responsable) {
        return ResultSetStreamer.stream(connection, "SELECT * FROM MODULE WHERE RESPONSABLE = " + responsable, new ModuleMapper()).collect(Collectors.toList());
    }

    @Override
    public boolean delete(Module obj) {
        boolean result;
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM MODULE WHERE CODE = ";
            query += obj.getCode();
            ResultSet resultSet = statement.executeQuery(query);
            result = resultSet.rowDeleted();
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public List<Module> findAll() {
        return ResultSetStreamer.stream(connection, "SELECT * FROM MODULE", new ModuleMapper()).collect(Collectors.toList());
    }

    @Override
    public Module getById(int id) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM ETUDIANT WHERE NUM_ET =%d ",id);
            ResultSet resultSet = statement.executeQuery(query);
            Module m = new ModuleMapper().mapRow(resultSet,1);
            statement.close();
            return m;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Module insert(Module obj) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO MODULE (CODE, LIBELLE, H_COURS_PREV, H_COURS_REA, H_TP_PREV, H_TP_REA, DISCIPLINE, COEFF_TEST, COEFF_CC, RESPONSABLE, CODEPERE) VALUES (%s, '%s', '%d', '%d', '%d', %d, %s, %d, %d, %s, %s);", obj.getCode(), obj.getLibelle(), obj.gethCoursPrev(), obj.gethCoursRea(), obj.gethTpPrev(), obj.gethTpRea(), obj.getDiscipline(), obj.getCoefTest(), obj.getCoefCc(), obj.getResponsable(), obj.getPere());
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
    public boolean update(Module obj) {
        boolean result;
        try {
            Statement statement = connection.createStatement();
            String  query   = String.format("UPDATE MODULE SET CODE='%s', LIBELLE='%s', H_COURS_PREV=%d, H_COURS_REA=%d, H_TP_PREV='%d', H_TP_REA=%d, DISCIPLINE='%s', COEFF_TEST=%d, COEFF__CC=%d, RESPONSABLE='%s', CODEPERE='%s' WHERE NUM_ET=%d;", obj.getCode(), obj.getLibelle(), obj.gethCoursPrev(), obj.gethCoursRea(), obj.gethTpPrev(), obj.gethTpRea(), obj.getDiscipline(), obj.getCoefTest(), obj.getCoefCc(), obj.getResponsable(), obj.getPere());
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

