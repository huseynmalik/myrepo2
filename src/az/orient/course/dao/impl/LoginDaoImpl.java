package az.orient.course.dao.impl;

import az.orient.course.dao.DbHelper;
import az.orient.course.dao.LoginDao;
import az.orient.course.model.Login;
import az.orient.course.model.Role;
import az.orient.course.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class LoginDaoImpl implements LoginDao {
    @Override
    public Login login(String username, String password) throws Exception {
        Login login = new Login();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT L.ID,L.USERNAME, R.ROLE_NAME ,L.NAME,L.SURNAME FROM LOGIN L \n" +
                "INNER JOIN ROLE R ON L.ROLE_ID = R.ID\n" +
                "WHERE L.STATUS = 1 AND L.USERNAME = ? AND L.PASSWORD = ?";
        try {
           c = DbHelper.getConnection();
           if (c != null) {
               ps = c.prepareStatement(sql);
               ps.setString(1,username);
               ps.setString(2,password);
               rs = ps.executeQuery();
               if (rs.next()) {
                   login.setId(rs.getLong("ID"));
                   login.setUsername(rs.getString("USERNAME"));
                   login.setName(rs.getString("NAME"));
                   login.setSurname(rs.getString("SURNAME"));
                   Role role = new Role();
                  // role.setId(rs.getLong(""));
                   role.setRoleName(rs.getString("ROLE_NAME"));
                   login.setRole(role);
               } else {
                   login = null;
               }
           }
        } catch (Exception ex) {
            ex.printStackTrace();
            login = null;
        } finally {
            JdbcUtility.close(c,ps,rs);
        }

        return login;
    }

    @Override
    public Login getLoginByEmail(String email) throws Exception {
        Login login = new Login();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql1 = "UPDATE LOGIN SET TOKEN = ? WHERE USERNAME = ?";
        String sql2 = "SELECT L.ID,L.USERNAME, R.ROLE_NAME ,L.NAME,L.SURNAME,L.TOKEN FROM LOGIN L \n" +
                "INNER JOIN ROLE R ON L.ROLE_ID = R.ID\n" +
                "WHERE L.STATUS = 1 AND L.USERNAME = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql1);
                ps.setString(1, UUID.randomUUID().toString());
                ps.setString(2,email);
                ps.executeUpdate();
                ps = c.prepareStatement(sql2);
                ps.setString(1,email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    login.setId(rs.getLong("ID"));
                    login.setUsername(rs.getString("USERNAME"));
                    login.setName(rs.getString("NAME"));
                    login.setSurname(rs.getString("SURNAME"));
                    login.setToken(rs.getString("TOKEN"));
                    Role role = new Role();
                    // role.setId(rs.getLong(""));
                    role.setRoleName(rs.getString("ROLE_NAME"));
                    login.setRole(role);
                } else {
                    login = null;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            login = null;
        } finally {
            JdbcUtility.close(c,ps,rs);
        }

        return login;
    }

    @Override
    public Login getLoginByToken(String token) throws Exception {
        Login login = new Login();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT L.ID,L.USERNAME, R.ROLE_NAME ,L.NAME,L.SURNAME FROM LOGIN L \n" +
                "INNER JOIN ROLE R ON L.ROLE_ID = R.ID\n" +
                "WHERE L.STATUS = 1 AND L.TOKEN = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1,token);
                rs = ps.executeQuery();
                if (rs.next()) {
                    login.setId(rs.getLong("ID"));
                    login.setUsername(rs.getString("USERNAME"));
                    login.setName(rs.getString("NAME"));
                    login.setSurname(rs.getString("SURNAME"));
                    Role role = new Role();
                    // role.setId(rs.getLong(""));
                    role.setRoleName(rs.getString("ROLE_NAME"));
                    login.setRole(role);
                } else {
                    login = null;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            login = null;
        } finally {
            JdbcUtility.close(c,ps,rs);
        }

        return login;
    }

    @Override
    public boolean changePassword(String password, String token) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql1 = "UPDATE LOGIN SET PASSWORD = ? WHERE TOKEN = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql1);
                ps.setString(1, password);
                ps.setString(2,token);
                ps.executeUpdate();
                result = true;

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            c.commit();
            JdbcUtility.close(c,ps,rs);
        }

        return result;
    }
}
