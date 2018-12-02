package az.orient.course.service.impl;

import az.orient.course.dao.LessonDao;
import az.orient.course.model.Lesson;
import az.orient.course.service.LessonService;

import java.util.List;

public class LessonServiceImpl implements LessonService {

    private LessonDao lessonDao;

    public LessonServiceImpl(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    public List<Lesson> getLessonList() throws Exception {
        return lessonDao.getLessonList();
    }
}
