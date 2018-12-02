package az.orient.course.dao.impl;

import az.orient.course.dao.DbHelper;
import az.orient.course.dao.LessonDao;
import az.orient.course.model.Lesson;
import az.orient.course.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LessonDaoImpl implements LessonDao {
    @Override
    public List<Lesson> getLessonList() throws Exception {
        List<Lesson> lessonList = new ArrayList<Lesson>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID,LESSON_NAME,LESSON_TIME,LESSON_PRICE FROM LESSON\n"
                + "WHERE STATUS = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("ID"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    lesson.setLessonPrice(rs.getDouble("LESSON_PRICE"));
                    lesson.setLessonTime(rs.getInt("LESSON_TIME"));
                    lessonList.add(lesson);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return lessonList;
    }
}
