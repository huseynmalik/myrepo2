package az.orient.course.dao.impl;

import az.orient.course.dao.DbHelper;
import az.orient.course.dao.StudentDao;
import az.orient.course.model.Student;
import az.orient.course.util.JdbcUtility;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> getStudentList() throws Exception {
        List<Student> studentList = new ArrayList<Student>();
        Connection c = null; // db ile elaqe yaratmaq uchun
        CallableStatement cs = null;
        ResultSet rs = null; // neticeni ozunde saxlayan
        String sql = "{? = call QRUP32.MAIN_PACK.GET_STUDENT_LIST}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Student student = new Student();
                    student.setR(rs.getLong("r"));
                    student.setId(rs.getLong("ID"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setAddress(rs.getString("ADDRESS"));
                    student.setPhone(rs.getString("PHONE"));
                    student.setDob(rs.getDate("DOB"));
                    studentList.add(student);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, cs, rs);
        }

        return studentList;
    }

    @Override
    public boolean addStudent(Student student) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call QRUP32.MAIN_PACK.ADD_STUDENT(?,?,?,?,?,?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, student.getName());
                cs.setString(2, student.getSurname());
                cs.setDate(3, new java.sql.Date(student.getDob().getTime()));
                cs.setString(4, student.getAddress());
                cs.setString(5, student.getPhone());
                cs.setString(6, student.getEmail());
                cs.execute();
                result = true;
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.commit();
            JdbcUtility.close(c, cs, null);
        }
        return result;
    }

    @Override
    public Student getStudentById(Long studentId) throws Exception {
        Student student = new Student();
        Connection c = null; // baza ile elaqe yaratmaq uchun
        CallableStatement cs = null; // sql-i run etmeye hazirlamaq uchun
        ResultSet rs = null; // sql-den qayidan neticeni ozunde saxlamaq uchun
        String sql = "{call QRUP32.MAIN_PACK.GET_STUDENT_BY_ID(?,?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setLong(1, studentId);
                cs.registerOutParameter(2,OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(2);
                if (rs.next()) {
                    student.setId(rs.getLong("ID"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setAddress(rs.getString("ADDRESS"));
                    student.setPhone(rs.getString("PHONE"));
                    student.setDob(rs.getDate("DOB"));
                } else {
                    student = null;
                }
            } else {
                System.out.println("Connection is null!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, cs, rs);
        }
        return student;
    }

    @Override
    public boolean updateStudent(Student studentU, long studentId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE STUDENT SET NAME = ?,SURNAME = ?,ADDRESS = ?,DOB = ?,PHONE = ?"
                + " WHERE ID = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, studentU.getName());
                ps.setString(2, studentU.getSurname());
                ps.setString(3, studentU.getAddress());
                ps.setDate(4, new java.sql.Date(studentU.getDob().getTime()));
                ps.setString(5, studentU.getPhone());
                ps.setLong(6, studentId);
                ps.executeUpdate();
                result = true;
            } else {
                System.out.println("Connection is null!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.commit();
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean deleteStudent(Long studentId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE STUDENT SET STATUS = 0"
                + " WHERE ID = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, studentId);
                ps.executeUpdate();
                result = true;
            } else {
                System.out.println("Connection is null!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.commit();
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean checkStudent(String name) throws Exception {
        boolean result = false;
        Connection c = null; // db ile elaqe yaratmaq uchun
        PreparedStatement ps = null; // sql i run etmek ucun hazirlayir
        ResultSet rs = null; // neticeni ozunde saxlayan
        String sql = "SELECT * FROM STUDENT "
                + "WHERE STATUS = 1 AND NAME = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1,name);
                rs = ps.executeQuery();
                if (rs.next()) {
                  result = true;
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return result;
    }

    @Override
    public List<Student> searchStudentData(String keyword) throws Exception {
        List<Student> studentList = new ArrayList<Student>();
        Connection c = null; // db ile elaqe yaratmaq uchun
        PreparedStatement ps = null; // sql i run etmek ucun hazirlayir
        ResultSet rs = null; // neticeni ozunde saxlayan
        String sql = "SELECT ROWNUM r,ID,NAME,SURNAME,DOB,ADDRESS,NVL(PHONE,' ') PHONE FROM STUDENT "
                + "WHERE STATUS = 1 AND (LOWER(NAME) LIKE LOWER(?) OR LOWER(SURNAME) LIKE LOWER(?) " +
                " OR LOWER(ADDRESS) LIKE LOWER(?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1,"%"+keyword+"%");
                ps.setString(2,"%"+keyword+"%");
                ps.setString(3,"%"+keyword+"%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Student student = new Student();
                    student.setR(rs.getLong("r"));
                    student.setId(rs.getLong("ID"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setAddress(rs.getString("ADDRESS"));
                    student.setPhone(rs.getString("PHONE"));
                    student.setDob(rs.getDate("DOB"));
                    studentList.add(student);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return studentList;
    }
}
