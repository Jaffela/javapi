package Service;

import entite.reservation;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class reservationService implements IService<reservation> {
    private Connection cnx;
    private PreparedStatement st;
    public reservationService() {
        cnx= DataSource.getInstance().getConn();
    }
    @Override
    public void add(reservation res) {
        String sql = "INSERT INTO reservation (statut, dated, datef, service) VALUES ('pending', ?, ?, ?)";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            // Set the parameters for the prepared statement

            st.setDate(1, new Date(res.getDebut().getTime())); // Assuming getDebut() returns a java.util.Date
            st.setDate(2, new Date(res.getFin().getTime())); // Assuming getFin() returns a java.util.Date
            st.setString(3, res.getService());

            // Execute the prepared statement
            st.executeUpdate();

            // Close the statement
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(reservation res) {
        try {
            String qry = "UPDATE reservation SET statut=?, dated=?, datef=?, service=? WHERE id=?";
            PreparedStatement st = cnx.prepareStatement(qry);
            st.setString(1, res.getStatut());
            st.setDate(2, res.getDebut());
            st.setDate(3, res.getFin());
            st.setString(4, res.getService());
            st.setInt(5, res.getId());
            st.executeUpdate();
            st.close();
            System.out.println("Reservation updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(reservation res) {
        String qry = "DELETE FROM reservation WHERE id = ?";

        try {
            PreparedStatement st = cnx.prepareStatement(qry);
            st.setInt(1, res.getId());
            st.executeUpdate();
            st.close();
            System.out.println(res);

            System.out.println("Reservation deleted!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void delete1(int reservationId) {
        String qry = "DELETE FROM reservation WHERE id = ?";
        try {
            PreparedStatement st = cnx.prepareStatement(qry);
            st.setInt(1, reservationId);
            st.executeUpdate();
            st.close();
            System.out.println("Reservation with ID " + reservationId + " deleted!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//    public void update1(int reservationId) {
//        try {
//            String qry = "UPDATE reservation SET statut=?, dated=?, datef=?, service=? WHERE id=?";
//            PreparedStatement st = cnx.prepareStatement(qry);
//            // Assuming you have methods to retrieve the other reservation attributes by ID
//            st.setString(1, /* statut retrieved by ID */);
//            st.setDate(2, /* dated retrieved by ID */);
//            st.setDate(3, /* datef retrieved by ID */);
//            st.setString(4, /* service retrieved by ID */);
//            st.setInt(5, reservationId);
//            st.executeUpdate();
//            st.close();
//            System.out.println("Reservation updated!");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }




    @Override
    public List<reservation> getAll() {
        List<reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM reservation";
        try {
            st = cnx.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new reservation(rs.getInt("id"), rs.getString("statut"), rs.getDate("dated"), rs.getDate("datef"),rs.getString("service")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;    }

    @Override
    public reservation getById(int id) {
        return null;
    }


}
