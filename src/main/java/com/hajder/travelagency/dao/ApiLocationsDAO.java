package com.hajder.travelagency.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static com.hajder.travelagency.dao.DAOUtil.setValues;

/**
 * Created by Piotr on 16.01.2017.
 * @author Piotr Hajder
 */
public class ApiLocationsDAO extends DAO {
    private static final String SQL_ADD_LOCATION =
            "INSERT INTO api_locations (location, cid)" +
            " VALUES (?, ?)";
    private static final String SQL_GET_LOCATIONS =
            "SELECT location, cid " +
            " FROM api_locations";

    public boolean addLocation(String location, String cid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = daoFactory.getConnection();
            stmt = conn.prepareStatement(SQL_ADD_LOCATION);
            setValues(stmt, location, cid);
            int val = stmt.executeUpdate();
            return val == 1;
        } catch (SQLException e) {
            //TODO: tomcat logging exception
        } finally {
            try { if(stmt != null) stmt.close(); } catch (SQLException ignored){}
            try { if(conn != null) conn.close(); } catch (SQLException ignored){}
        }
        return false;
    }

    public Map<String, String> getLocations() {
        Map<String, String> result = new HashMap<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = daoFactory.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL_GET_LOCATIONS);
            while (rs.next()) {
                result.put(rs.getString(1),//location
                        rs.getString(2));//cid
            }
        } catch (SQLException e) {
            //TODO: tomcat logging exception
        } finally {
            try { if(rs != null) rs.close(); } catch (SQLException ignored){}
            try { if(stmt != null) stmt.close(); } catch (SQLException ignored){}
            try { if(conn != null) conn.close(); } catch (SQLException ignored){}
        }
        return result;
    }
}
