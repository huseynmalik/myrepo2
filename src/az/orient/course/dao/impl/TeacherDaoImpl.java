package az.orient.course.dao.impl;

import az.orient.course.dao.DbHelper;
import az.orient.course.dao.TeacherDao;
import az.orient.course.model.Student;
import az.orient.course.model.Teacher;
import az.orient.course.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {

    @Override
    public List<Teacher> getTeacherList() throws Exception {
        List<Teacher> teacherList = new ArrayList<Teacher>();
        Connection c = null; // db ile elaqe yaratmaq uchun
        PreparedStatement ps = null; // sql i run etmek ucun hazirlayir
        ResultSet rs = null; // neticeni ozunde saxlayan
        String sql = "SELECT ROWNUM r,ID,NAME,SURNAME,DOB,ADDRESS,NVL(PHONE,' ') PHONE FROM TEACHER "
                + "WHERE STATUS = 1 ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setR(rs.getLong("r"));
                    teacher.setId(rs.getLong("ID"));
                    teacher.setName(rs.getString("NAME"));
                    teacher.setSurname(rs.getString("SURNAME"));
                    teacher.setAddress(rs.getString("ADDRESS"));
                    teacher.setPhone(rs.getString("PHONE"));
                    teacher.setDob(rs.getDate("DOB"));
                    teacherList.add(teacher);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return teacherList;
    }

    @Override
    public List<Teacher> getTeacherListByLessonId(Long lessonId) throws Exception {
        List<Teacher> teacherList = new ArrayList<Teacher>();
        Connection c = null; // db ile elaqe yaratmaq uchun
        PreparedStatement ps = null; // sql i run etmek ucun hazirlayir
        ResultSet rs = null; // neticeni ozunde saxlayan
        String sql = "SELECT DISTINCT T.ID,T.NAME,T.SURNAME FROM SCHEDULE SC\n" +
                "INNER JOIN TEACHER T ON SC.T_ID = T.ID\n" +
                "INNER JOIN LESSON L ON SC.L_ID = L.ID\n" +
                "WHERE SC.STATUS = 1 AND T.STATUS = 1 AND SC.L_ID = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1,lessonId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("ID"));
                    teacher.setName(rs.getString("NAME"));
                    teacher.setSurname(rs.getString("SURNAME"));
                    teacherList.add(teacher);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return teacherList;
    }
}
