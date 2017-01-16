package com.hajder.travelagency.action;

import com.hajder.travelagency.dao.ApiLocationsDAO;
import com.hajder.travelagency.exception.DAOException;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Piotr on 16.01.2017.
 * @author Piotr hajder
 */
public class ApiLocationAction extends Action {
    private String location, cid;

    private Map<String, String> locations;

    @Override
    protected void doAction() throws DAOException {
        ApiLocationsDAO dao = (ApiLocationsDAO) daoFactory.getDaoInstance(ApiLocationsDAO.class);
        try {
            if(location != null && cid != null) {
                dao.addLocation(location, cid);
            } else {
                locations = dao.getLocations();
            }
        } catch (SQLException e) {
            //TODO: tomcat logging system
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Map<String, String> getLocations() {
        return locations;
    }
}
