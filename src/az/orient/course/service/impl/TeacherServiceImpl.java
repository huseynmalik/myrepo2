package az.orient.course.service.impl;

import az.orient.course.dao.TeacherDao;
import az.orient.course.model.Teacher;
import az.orient.course.service.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private TeacherDao teacherDao;

    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public List<Teacher> getTeacherList() throws Exception {
        return teacherDao.getTeacherList();
    }

    @Override
    public List<Teacher> getTeacherListByLessonId(Long lessonId) throws Exception {
        return teacherDao.getTeacherListByLessonId(lessonId);
    }
}
