package az.orient.course.dao.impl;

import az.orient.course.dao.DbHelper;
import az.orient.course.dao.ScheduleDao;
import az.orient.course.model.*;
import az.orient.course.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDaoImpl implements ScheduleDao {

    @Override
    public List<Schedule> getScheduleList() throws Exception {
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,SC.ID,S.ID STUDENT_ID,S.NAME STUDENT_NAME,S.SURNAME STUDENT_SURNAME,\r\n"
                + "T.ID TEACHER_ID,T.NAME TEACHER_NAME,T.SURNAME TEACHER_SURNAME,L.ID LESSON_ID,L.LESSON_NAME FROM SCHEDULE SC\r\n"
                + "INNER JOIN STUDENT S ON SC.S_ID = S.ID\r\n"
                + "INNER JOIN TEACHER T ON SC.T_ID = T.ID\r\n"
                + "INNER JOIN LESSON L ON SC.L_ID = L.ID\r\n"
                + "WHERE SC.STATUS = 1 AND S.STATUS = 1 AND T.STATUS = 1 order by SC.ID ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Schedule schedule = new Schedule();
                    schedule.setR(rs.getLong("r"));
                    schedule.setId(rs.getLong("ID"));
                    Student student = new Student();
                    student.setId(rs.getLong("STUDENT_ID"));
                    student.setName(rs.getString("STUDENT_NAME"));
                    student.setSurname(rs.getString("STUDENT_SURNAME"));
                    schedule.setStudent(student);
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("TEACHER_ID"));
                    teacher.setName(rs.getString("TEACHER_NAME"));
                    teacher.setSurname(rs.getString("TEACHER_SURNAME"));
                    schedule.setTeacher(teacher);
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("LESSON_ID"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    schedule.setLesson(lesson);
                    scheduleList.add(schedule);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return scheduleList;
    }

    @Override
    public boolean addSchedule(Schedule schedule) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO SCHEDULE(ID,S_ID,T_ID,L_ID)\n"
                + "VALUES(SCHEDULE_SEQ.NEXTVAL,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, schedule.getStudent().getId());
                ps.setLong(2, schedule.getTeacher().getId());
                ps.setLong(3, schedule.getLesson().getId());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            c.commit();
            JdbcUtility.close(c, ps, null);
        }

        return result;
    }

    @Override
    public Schedule getScheduleById(long scheduleId) throws Exception {
        Schedule schedule = new Schedule();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID,S_ID,T_ID,L_ID FROM SCHEDULE\n" +
                "WHERE STATUS = 1 AND ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, scheduleId);
                rs = ps.executeQuery();
                while (rs.next()) {

                    schedule.setId(rs.getLong("ID"));
                    Student student = new Student();
                    student.setId(rs.getLong("S_ID"));
                    schedule.setStudent(student);
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("T_ID"));
                    schedule.setTeacher(teacher);
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("L_ID"));
                    schedule.setLesson(lesson);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return schedule;
    }

    @Override
    public boolean updateSchedule(Schedule schedule, long scheduleId) throws Exception {
        boolean result = false;
        String sql = "UPDATE SCHEDULE SET S_ID = ?,T_ID = ?,L_ID = ?\n"
                + "WHERE ID = ?";
        try (Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setLong(1, schedule.getStudent().getId());
            ps.setLong(2, schedule.getTeacher().getId());
            ps.setLong(3, schedule.getLesson().getId());
            ps.setLong(4, scheduleId);
            ps.execute();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Schedule> searchScheduleData(String keyword) throws Exception {
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        Connection c = null;
        PreparedStatement ps = null; // order by SC.ID
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,SC.ID,S.ID STUDENT_ID,S.NAME STUDENT_NAME,S.SURNAME STUDENT_SURNAME,\r\n"
                + "T.ID TEACHER_ID,T.NAME TEACHER_NAME,T.SURNAME TEACHER_SURNAME,L.ID LESSON_ID,L.LESSON_NAME FROM SCHEDULE SC\r\n"
                + "INNER JOIN STUDENT S ON SC.S_ID = S.ID\r\n"
                + "INNER JOIN TEACHER T ON SC.T_ID = T.ID\r\n"
                + "INNER JOIN LESSON L ON SC.L_ID = L.ID\r\n"
                + "WHERE SC.STATUS = 1 AND S.STATUS = 1 AND T.STATUS = 1 AND " +
                " (LOWER(S.NAME) LIKE LOWER(?) OR LOWER(S.SURNAME) LIKE LOWER(?) OR LOWER(T.NAME) LIKE LOWER(?)" +
                " OR LOWER(T.SURNAME) LIKE LOWER(?) OR LOWER(L.LESSON_NAME) LIKE LOWER(?)) order by SC.ID";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1,"%"+keyword+"%");
                ps.setString(2,"%"+keyword+"%");
                ps.setString(3,"%"+keyword+"%");
                ps.setString(4,"%"+keyword+"%");
                ps.setString(5,"%"+keyword+"%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Schedule schedule = new Schedule();
                    schedule.setR(rs.getLong("r"));
                    schedule.setId(rs.getLong("ID"));
                    Student student = new Student();
                    student.setId(rs.getLong("STUDENT_ID"));
                    student.setName(rs.getString("STUDENT_NAME"));
                    student.setSurname(rs.getString("STUDENT_SURNAME"));
                    schedule.setStudent(student);
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("TEACHER_ID"));
                    teacher.setName(rs.getString("TEACHER_NAME"));
                    teacher.setSurname(rs.getString("TEACHER_SURNAME"));
                    schedule.setTeacher(teacher);
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("LESSON_ID"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    schedule.setLesson(lesson);
                    scheduleList.add(schedule);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return scheduleList;
    }

    @Override
    public List<Schedule> advancedSearchSchedule(AdvancedSearch advancedSearch) throws Exception {
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,SC.ID,S.ID STUDENT_ID,S.NAME STUDENT_NAME,S.SURNAME STUDENT_SURNAME,\r\n"
                + "T.ID TEACHER_ID,T.NAME TEACHER_NAME,T.SURNAME TEACHER_SURNAME,L.ID LESSON_ID,L.LESSON_NAME FROM SCHEDULE SC\r\n"
                + "INNER JOIN STUDENT S ON SC.S_ID = S.ID\r\n"
                + "INNER JOIN TEACHER T ON SC.T_ID = T.ID\r\n"
                + "INNER JOIN LESSON L ON SC.L_ID = L.ID\r\n"
                + "WHERE SC.STATUS = 1 AND S.STATUS = 1 AND T.STATUS = 1  ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {

                if (advancedSearch.getLessonId() != 0)
                    sql+=" AND  L.ID = "+advancedSearch.getLessonId();
                if (advancedSearch.getTeacherId() != 0)
                    sql+=" AND T.ID = "+advancedSearch.getTeacherId();
                if (advancedSearch.getBeginDate() != null && !advancedSearch.getBeginDate().isEmpty())
                    sql+=" AND S.DOB >= TO_DATE('"+new java.sql.Date(df.parse(advancedSearch.getBeginDate()).getTime())+"','YYYY-MM-DD')";
                if (advancedSearch.getEndDate() != null && !advancedSearch.getEndDate().isEmpty())
                    sql+=" AND S.DOB < TO_DATE('"+new java.sql.Date(df.parse(advancedSearch.getEndDate()).getTime())+"','YYYY-MM-DD')";


                ps = c.prepareStatement(sql+" order by SC.ID");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Schedule schedule = new Schedule();
                    schedule.setR(rs.getLong("r"));
                    schedule.setId(rs.getLong("ID"));
                    Student student = new Student();
                    student.setId(rs.getLong("STUDENT_ID"));
                    student.setName(rs.getString("STUDENT_NAME"));
                    student.setSurname(rs.getString("STUDENT_SURNAME"));
                    schedule.setStudent(student);
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("TEACHER_ID"));
                    teacher.setName(rs.getString("TEACHER_NAME"));
                    teacher.setSurname(rs.getString("TEACHER_SURNAME"));
                    schedule.setTeacher(teacher);
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("LESSON_ID"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    schedule.setLesson(lesson);
                    scheduleList.add(schedule);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return scheduleList;
    }
}
