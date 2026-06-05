package com.ejemplo.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ejemplo.dao.DBConexion;
import com.ejemplo.models.Detalle;
import com.ejemplo.models.Empleado;
import com.ejemplo.models.EmpleadoUpdate;
import com.ejemplo.models.Genero;

public class EmpleadoServiceImpl implements EmpleadoService {
  private static final Logger LOG = Logger.getLogger("EmpleadoServiceImpl");

  @Override
  public boolean isConnectionOK() throws SQLException, Exception {

    boolean conectionOK = false;

    try (
      DBConexion dbConexion = new DBConexion("cursom", "Temp2026$$");
      Connection conn = dbConexion.getConexion();
    ) {
        if (conn != null) conectionOK = true;
    } catch (SQLException | ClassNotFoundException ex) {
        System.getLogger(EmpleadoServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
    return conectionOK;
  }

  @Override
  public List<Empleado> getEmpleadoList() {
    List<Empleado> empleados = new ArrayList<>();
    try (
      DBConexion dbConexion = new DBConexion("cursom", "Temp2026$$");
      Connection connection = dbConexion.getConexion();
    ) {
      ResultSet rs = dbConexion.getEmpleados(connection);
      while (rs.next()) {
        empleados.add(
          Empleado.builder()
            .id(rs.getInt("id"))
            .nombre(rs.getString("nombre"))
            .primerApellido(rs.getString("primerApellido"))
            .segundoApellido(rs.getString("segundoApellido"))
            .fechaAlta(rs.getDate("fechaAlta").toLocalDate())
            .genero(Genero.valueOf(rs.getString("genero")))
            .salario(rs.getBigDecimal("salario"))
            .departamentos_id(rs.getInt("departamentos_id"))
            .build()
        );
      }
    } catch (Exception ex) {
      System.getLogger(EmpleadoServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
    
    return empleados;
  }

  @Override
  public void altaEmpleado(Empleado empleado, List<String> emails, List<String> telefonos) throws SQLException {
    try (
        DBConexion dbConexion = new DBConexion("cursom", "Temp2026$$");
        Connection connection = dbConexion.getConexion();
    ) {
      dbConexion.altaNuevoEmpleado(connection, empleado, emails, telefonos);
    } catch (Exception ex) {
      System.getLogger(EmpleadoServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
  }

    @Override
    public Detalle getDetalles(int idEmpleado) {
      Detalle detalle = null;
      try (
          DBConexion dbConexion = new DBConexion("cursom", "Temp2026$$");
          Connection connection = dbConexion.getConexion();
          ResultSet rs = dbConexion.getDetallesEmpleados(connection, idEmpleado);
      ) {
        String nombreDpto = null;
        Set<String> numerosTelefono = new HashSet<>();
        Set<String> emails = new HashSet<>();
        rs.beforeFirst();
        if (rs.first()) nombreDpto = rs.getString("nombreDpto");
        rs.beforeFirst();
        while (rs.next()) {
          numerosTelefono.add(rs.getString("numeroTelefono"));
          emails.add(rs.getString("email"));
        }
        rs.close();
        // Detalle del empleado
        detalle = Detalle.builder()
          .nombreDpto(nombreDpto)
          .telefonos(numerosTelefono)
          .correos(emails)
          .build();
        LOG.log(Level.INFO, "Detalle: {0}", detalle);
      } catch (Exception ex) {
        System.getLogger(EmpleadoServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
      }
      return detalle;
    }

    @Override
    public EmpleadoUpdate getEmpleadosById(int idEmpleado) {
      EmpleadoUpdate empleadoUpdate = null;
      try (
          DBConexion dbConexion = new DBConexion("cursom", "Temp2026$$");
          Connection connection = dbConexion.getConexion();
          ResultSet rs = dbConexion.getEmpleadosById(connection, idEmpleado);
      ) {
        int idEmp = 0;
        String nombreEmpleado = null;
        String primerApellido = null;
        String segundoApellido = null;
        LocalDate fechaAlta = null;
        Genero genero = null;
        BigDecimal salario = null;
        int idDpto = 0;
        String nombreDpto = null;
        Set<String> telefonos = new HashSet<>();
        Set<String> emails = new HashSet<>();
        rs.beforeFirst();
        if (rs.first())
          nombreDpto = rs.getString("nombreDpto");
        rs.beforeFirst();
        while (rs.next()) {
          idEmp = rs.getInt("idEmpleado");
          nombreEmpleado = rs.getString("nombreEmpleado");
          primerApellido = rs.getString("primerApellido");
          segundoApellido = rs.getString("segundoApellido");
          fechaAlta = rs.getDate("fechaAlta").toLocalDate();
          genero = Genero.valueOf(rs.getString("genero"));
          salario = new BigDecimal(rs.getDouble("salario"));
          idDpto = rs.getInt("idDpto");
          nombreDpto = rs.getString("nombreDpto");
        }
        rs.beforeFirst();
        while (rs.next()) {
          telefonos.add(rs.getString("telefono"));
        }
        rs.beforeFirst();
        while (rs.next()) {
          emails.add(rs.getString("email"));
        }
        rs.close();
        // Detalle del empleado
        empleadoUpdate = EmpleadoUpdate.builder()
          .idEmp(idEmp)
          .nombre(nombreEmpleado)
          .primerApellido(primerApellido)
          .segundoApellido(segundoApellido)
          .fechaAlta(fechaAlta) 
          .genero(genero) 
          .salario(salario)
          .idDpto(idDpto)
          .nombreDpto(nombreDpto)
          .telefonos(telefonos)
          .correos(emails)
          .build();
        LOG.log(Level.INFO, "Detalle: {0}", empleadoUpdate);
      } catch (Exception ex) {
        System.getLogger(EmpleadoServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
      }

      return empleadoUpdate;
    }

}
