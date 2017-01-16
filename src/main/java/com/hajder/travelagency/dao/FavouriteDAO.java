package com.hajder.travelagency.dao;

import com.hajder.travelagency.entity.FavouritePublicTransport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.hajder.travelagency.dao.DAOUtil.setValues;

/**
 * Created by Piotr on 16.01.2017.
 * @author Piotr Hajder
 */
public class FavouriteDAO extends DAO {
    private static final String SQL_ADD_FAVOURITE_TRANSPORT =
            "INSERT INTO favourite_transport (user_id, start_point, end_point, fav_time, avoid, point_mode) " +
            " VALUES (?,?,?,?,?,?)";
    private static final String SQL_GET_FAVOURITES =
            "SELECT start_point, end_point, fav_time, avoid, point_mode " +
            " FROM favourite_transport " +
            " WHERE user_id = ?";

    public boolean saveFavourite(FavouritePublicTransport fpt, long userId){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = daoFactory.getConnection();
            stmt = conn.prepareStatement(SQL_ADD_FAVOURITE_TRANSPORT);
            setValues(stmt, userId,
                    fpt.getStartPoint(), fpt.getEndPoint(),
                    fpt.getHour(),
                    fpt.isAvoidTransfer(), fpt.isMode());
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

    public List<FavouritePublicTransport> getFavouriteTransports(long userId) {
        List<FavouritePublicTransport> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = daoFactory.getConnection();
            stmt = conn.prepareStatement(SQL_GET_FAVOURITES);
            setValues(stmt, userId);
            rs = stmt.executeQuery();

            while(rs.next()) {
                FavouritePublicTransport obj = new FavouritePublicTransport();
                obj.setStartPoint(rs.getString(1));
                obj.setEndPoint(rs.getString(2));
                obj.setHour(rs.getString(3));
                obj.setAvoidTransfer(rs.getBoolean(4));
                obj.setMode(rs.getBoolean(5));
                result.add(obj);
            }
        } catch (SQLException e) {
            //TODO: tomcat logging system
        } finally {
            try { if(rs != null) rs.close(); } catch (SQLException ignored) {}
            try { if(stmt != null) stmt.close(); } catch (SQLException ignored){}
            try { if(conn != null) conn.close(); } catch (SQLException ignored){}
        }
        return result;
    }
}
