package Service;

import entite.cage;
import entite.reservation;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class cageService implements IService<cage>{
    private Connection cnx;
    private PreparedStatement st;
    public cageService() {
        cnx= DataSource.getInstance().getConn();
    }

    @Override
    public void add(cage cageuno) {
        String sql = "INSERT INTO cage (type, disponibilite, emplacement) VALUES ( ?, TRUE, ?)";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            // Set the parameters for the prepared statement

            st.setString(1, cageuno.getType()); // Assuming getDebut() returns a java.util.Date
            st.setString(2, cageuno.getEmplacement());

            // Execute the prepared statement
            st.executeUpdate();

            // Close the statement
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void update(cage cagedos) {
        try {
            String qry = "UPDATE reservation SET type=?, emplacement=? WHERE id=?";
            PreparedStatement st = cnx.prepareStatement(qry);
            st.setString(1, cagedos.getType());
            st.setString(2, cagedos.getEmplacement());
            st.setInt(3, cagedos.getId());
            st.executeUpdate();
            st.close();
            System.out.println("cage updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void delete2(int cageId) {
        String qry = "DELETE FROM cage WHERE id = ?";
        try {
            PreparedStatement st = cnx.prepareStatement(qry);
            st.setInt(1, cageId);
            st.executeUpdate();
            st.close();
            System.out.println("Reservation with ID " + cageId + " deleted!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(cage cage) {

    }

    @Override
    public List<cage> getAll() {
        List<cage> list = new ArrayList<>();
        String sql = "SELECT * FROM cage";
        try {
            st = cnx.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new cage(rs.getInt("id"),rs.getString("type"),rs.getBoolean("disponibilite"),rs.getString("emplacement")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public cage getById(int id) {
        return null;
    }
}
