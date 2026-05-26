package com.ejemplo.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.ejemplo.dao.DBConexion;

public class EmpleadoServiceImpl implements EmpleadoService {

  @Override
  public boolean isConnectionOK() throws SQLException, Exception {

    boolean conectionOK = false;

    try (DBConexion dbConexion = new DBConexion("cursom", "Temp2026$$");
            Connection conn = dbConexion.getConexion();
    ) {
        if (conn != null) conectionOK = true;
    } catch (SQLException | ClassNotFoundException ex) {
        System.getLogger(EmpleadoServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
      return conectionOK;
  }

}
