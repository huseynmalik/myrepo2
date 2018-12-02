package az.orient.course.service;

import az.orient.course.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getStudentList() throws Exception;

    boolean addStudent(Student student) throws Exception;

    Student getStudentById(Long studentId) throws Exception;

    boolean updateStudent(Student studentU, long studentId) throws Exception;

    boolean deleteStudent(Long studentId) throws Exception;

    boolean checkStudent(String name) throws Exception;


    List<Student> searchStudentData(String keyword) throws Exception;


}
