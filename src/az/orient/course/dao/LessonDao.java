package az.orient.course.dao;

import az.orient.course.model.Lesson;

import java.util.List;

public interface LessonDao {

    List<Lesson> getLessonList() throws Exception;
}
