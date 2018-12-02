package az.orient.course.service;

import az.orient.course.model.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getTeacherList() throws Exception;

    List<Teacher> getTeacherListByLessonId(Long lessonId) throws Exception;
}
