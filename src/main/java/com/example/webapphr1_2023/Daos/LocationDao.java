package com.example.webapphr1_2023.Daos;

import com.example.webapphr1_2023.Beans.Employee;
import com.example.webapphr1_2023.Beans.Job;
import com.example.webapphr1_2023.Beans.Location;

import java.sql.*;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;

public class LocationDao extends DaoBase{
    public ArrayList<Location> obtenerListaLocations() {
        ArrayList<Location> listaLocations = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM locations");) {

            while (rs.next()) {
                Location location = new Location();
                location.setLocationId(rs.getInt(1));
                location.setStreet_address(rs.getString(2));
                location.setPostal_code(rs.getString(3));
                location.setCity(rs.getString(4));
                location.setState_province(rs.getString(5));
                location.setCountry_id(rs.getString(6));

                listaLocations.add(location);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaLocations;

    }

    public void crearLocations(int locationId, String street_address, String postal_code, String city, String  state_province, String  country_id) {

        String sql = "INSERT INTO jobs (location_id,street_address,postal_code,city,state_province,country_id) VALUES (?,?,?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, locationId);
            pstmt.setString(2, street_address);
            pstmt.setString(3, postal_code);
            pstmt.setString(4, city);
            pstmt.setString(5, state_province);
            pstmt.setString(6, country_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Location obtenerLocations(int locationId) {

        Location location = null;

        String sql = "SELECT * FROM jobs WHERE location_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, locationId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    location = new Location();
                    location.setLocationId(rs.getInt(1));
                    location.setStreet_address(rs.getString(2));
                    location.setPostal_code(rs.getString(3));
                    location.setCity(rs.getString(4));
                    location.setState_province(rs.getString(5));
                    location.setCountry_id(rs.getString(6));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return location;
    }

    public void guardarLocation(Location location) {

        String sql = "INSERT INTO employees (street_address,postal_code,city,state_province,country_id) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setLocationData(location, pstmt);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void actualizarLocations(Location location) {

        String sql = "UPDATE locations SET street_address = ?, postal_code = ?, city = ?, state_province = ?, country_id = ? "
                + "WHERE location_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setLocationData(location, pstmt);
            pstmt.setInt(11, location.getLocationId());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void borrarLocations(int locationId) {

        String sql = "DELETE FROM jobs WHERE job_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, locationId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setLocationData(Location location, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, location.getStreet_address());
        pstmt.setString(2, location.getPostal_code());
        pstmt.setString(3, location.getCity());
        pstmt.setString(4, location.getState_province());
        pstmt.setString(5, location.getCountry_id());

    }
}
