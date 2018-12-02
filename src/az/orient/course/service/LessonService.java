package az.orient.course.service;

import az.orient.course.model.Lesson;

import java.util.List;

public interface LessonService {

    List<Lesson> getLessonList() throws Exception;
}
