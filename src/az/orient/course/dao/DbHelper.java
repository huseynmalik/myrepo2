package az.orient.course.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Locale;

public class DbHelper {

    public static Connection getConnection() throws Exception {
        Locale.setDefault(Locale.ENGLISH);
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Qrup32Course");
        Connection c = dataSource.getConnection();
        return c;
    }

}
