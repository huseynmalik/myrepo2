/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.orient.course.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Ideas.az
 */
public class JdbcUtility {
    public static void close(Connection c,PreparedStatement ps,ResultSet rs) throws Exception {
		if (c != null)
			c.close();
		if (ps != null)
			ps.close();
		if (rs != null)
			rs.close();
	}

	public static void close(Connection c, CallableStatement cs, ResultSet rs) throws Exception {
		if (c != null)
			c.close();
		if (cs != null)
			cs.close();
		if (rs != null)
			rs.close();
	}
}
