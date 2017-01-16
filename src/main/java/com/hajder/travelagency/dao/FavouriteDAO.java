package com.hajder.travelagency.dao;

import com.hajder.travelagency.entity.FavouritePublicTransport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.hajder.travelagency.dao.DAOUtil.setValues;

/**
 * Created by Piotr on 16.01.2017.
 * @author Piotr Hajder
 */
public class FavouriteDAO extends DAO {
    private static final String SQL_ADD_FAVOURITE_TRANSPORT =
            "INSERT INTO favourite_transport (user_id, start_point, end_point, hour, avoid, point_mode) " +
            " VALUES (?,?,?,?,?,?)";

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
}
