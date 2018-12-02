package az.orient.course.web;

import az.orient.course.dao.*;
import az.orient.course.dao.impl.*;
import az.orient.course.model.*;
import az.orient.course.service.*;
import az.orient.course.service.impl.*;
import az.orient.course.util.Methods;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "ControllerServlet",urlPatterns = "/cs")
public class ControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        String action = null;
        String address = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        StudentDao studentDao = new StudentDaoImpl();
        StudentService studentService = new StudentServiceImpl(studentDao);
        /*****************************************************/
        TeacherDao teacherDao = new TeacherDaoImpl();
        TeacherService teacherService = new TeacherServiceImpl(teacherDao);
        /***************************************************/
        ScheduleDao scheduleDao = new ScheduleDaoImpl();
        ScheduleService scheduleService = new ScheduleServiceImpl(scheduleDao);
        /************************************************************/
        LessonDao lessonDao = new LessonDaoImpl();
        LessonService lessonService = new LessonServiceImpl(lessonDao);
        LoginDao loginDao = new LoginDaoImpl();
        LoginService loginService = new LoginServiceImpl(loginDao);
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }

        try {

            if (action.equalsIgnoreCase("register")) {
                FileWriter fw = new FileWriter("D://qrup32Web.txt",true);
                BufferedWriter bw = new BufferedWriter(fw);
                String studentName = request.getParameter("studentName");
                bw.write("Student Name: "+studentName);
                String studentSurname = request.getParameter("studentSurname");
                bw.newLine();
                bw.write("Student Surname: "+studentSurname);
                String gender = request.getParameter("gender");
                bw.newLine();
                bw.write("Gender: "+gender);
                bw.newLine();
                String[] foreignLanguages = request.getParameterValues("foreignLanguages");
                for (String lang: foreignLanguages) {
                    bw.write(lang+" ");
                }
                bw.newLine();
                bw.write("********************************************");
                bw.newLine();
                bw.close();
                pw.println("<h1>Fayla melumat ugurla yaazildi!</h1>");
            } else if (action.equalsIgnoreCase("getStudentList")) {
                HttpSession session = request.getSession(false);
                if (session.getAttribute("login") != null) {
                    List<Student> studentList = studentService.getStudentList();
                    request.setAttribute("studentList",studentList);
                    address = "/WEB-INF/pages/studentList1.jsp";
                } else {
                    address = "login.jsp";
                }

            } else if (action.equalsIgnoreCase("getTeacherList")) {
                HttpSession session = request.getSession(false);
                if (session.getAttribute("login") != null) {
                    List<Teacher> teacherList = teacherService.getTeacherList();
                    request.setAttribute("teacherList", teacherList);
                    address = "/WEB-INF/pages/teacherList1.jsp";
                } else {
                    address = "login.jsp";
                }
            } else if (action.equalsIgnoreCase("getScheduleList")) {
                HttpSession session = request.getSession(false);
                if (session.getAttribute("login") != null) {
                    List<Schedule> scheduleList = scheduleService.getScheduleList();
                    List<Lesson> lessonList = lessonService.getLessonList();
                    List<Teacher> teacherList = teacherService.getTeacherList();
                    request.setAttribute("lessonList", lessonList);
                    request.setAttribute("teacherList", teacherList);
                    request.setAttribute("scheduleList", scheduleList);
                    address = "/WEB-INF/pages/scheduleList.jsp";
                } else {
                    address = "login.jsp";
                }
            } else if (action.equalsIgnoreCase("addStudent")) {
                String studentName = request.getParameter("name");
                boolean isExist = studentService.checkStudent(studentName);
                response.setContentType("text/html");
                if (isExist) {
                    pw.write("exist");
                } else {
                    Student student = new Student();
                    student.setName(studentName);
                    student.setSurname(request.getParameter("surname"));
                    student.setPhone(request.getParameter("phone"));
                    student.setAddress(request.getParameter("address"));
                    student.setEmail(request.getParameter("email"));
                    student.setDob(df.parse(request.getParameter("dob")));
                    boolean isAdded =  studentDao.addStudent(student);

                    if (isAdded) {
                        pw.write("success");
                    } else {
                        pw.write("fail");
                    }
                }
            } else if (action.equalsIgnoreCase("editStudent")) {
                Long studentId = Long.parseLong(request.getParameter("studentId"));
                Student student = studentService.getStudentById(studentId);
                request.setAttribute("student",student);
                address = "/WEB-INF/pages/editStudent.jsp";
            } else if (action.equalsIgnoreCase("updateStudent")) {
                Student student = new Student();
                student.setName(request.getParameter("name"));
                student.setSurname(request.getParameter("surname"));
                student.setPhone(request.getParameter("phone"));
                student.setAddress(request.getParameter("address"));
                student.setEmail(request.getParameter("email"));
                student.setDob(df.parse(request.getParameter("dob")));
                Long studentId = Long.parseLong(request.getParameter("studentId"));
                boolean isUpdated =  studentDao.updateStudent(student,studentId);
                response.setContentType("text/html");
                if (isUpdated) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("deleteStudent")) {
               Long studentId = Long.parseLong(request.getParameter("studentId"));
                boolean isDeleted =  studentService.deleteStudent(studentId);
                response.setContentType("text/html");
                if (isDeleted) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("newSchedule")) {
                List<Student> studentList = studentService.getStudentList();
                List<Teacher> teacherList = teacherService.getTeacherList();
                List<Lesson> lessonList = lessonService.getLessonList();
                request.setAttribute("studentList",studentList);
                request.setAttribute("teacherList",teacherList);
                request.setAttribute("lessonList",lessonList);
                address = "/WEB-INF/pages/newSchedule.jsp";
            } else if (action.equalsIgnoreCase("addSchedule")) {
                Long studentId = Long.parseLong(request.getParameter("studentCombo"));
                Long teacherId = Long.parseLong(request.getParameter("teacherCombo"));
                Long lessonId = Long.parseLong(request.getParameter("lessonCombo"));
                Student student = new Student();
                student.setId(studentId);
                Teacher teacher = new Teacher();
                teacher.setId(teacherId);
                Lesson lesson = new Lesson();
                lesson.setId(lessonId);
                Schedule schedule = new Schedule();
                schedule.setStudent(student);
                schedule.setTeacher(teacher);
                schedule.setLesson(lesson);
                boolean isAdded = scheduleService.addSchedule(schedule);
                response.setContentType("text/html");
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("editSchedule")) {
                Long scheduleId = Long.parseLong(request.getParameter("scheduleId"));
                Schedule schedule = scheduleService.getScheduleById(scheduleId);
                List<Student> studentList = studentService.getStudentList();
                List<Teacher> teacherList = teacherService.getTeacherList();
                List<Lesson> lessonList = lessonService.getLessonList();
                request.setAttribute("studentList",studentList);
                request.setAttribute("teacherList",teacherList);
                request.setAttribute("lessonList",lessonList);
                request.setAttribute("schedule",schedule);
                address = "/WEB-INF/pages/editSchedule.jsp";
            } else if (action.equalsIgnoreCase("searchStudentData")) {
                String keyword = request.getParameter("keyword");
                List<Student> studentList = studentService.searchStudentData(keyword);
                request.setAttribute("studentList",studentList);
                address = "/WEB-INF/pages/studentList1.jsp";
            } else if (action.equalsIgnoreCase("searchScheduleData")) {
                String keyword = request.getParameter("keyword");
                List<Schedule> scheduleList = scheduleService.searchScheduleData(keyword);
                request.setAttribute("scheduleList",scheduleList);
                address = "/WEB-INF/pages/scheduleList.jsp";
            } else if (action.equalsIgnoreCase("getTeacherComboByLessonId")) {
                Long lessonId = Long.parseLong(request.getParameter("lessonId"));
                List<Teacher> teacherList = teacherService.getTeacherListByLessonId(lessonId);
                request.setAttribute("teacherList",teacherList);
                address = "/WEB-INF/pages/teacherCombo.jsp";
            } else if (action.equalsIgnoreCase("advancedSearchSchedule")) {
                Long lessonId = Long.parseLong(request.getParameter("lessonId"));
                Long teacherId = Long.parseLong(request.getParameter("teacherId"));
                String beginDate = request.getParameter("beginDate");
                String endDate = request.getParameter("endDate");
                AdvancedSearch advancedSearch = new AdvancedSearch();
                advancedSearch.setLessonId(lessonId);
                advancedSearch.setTeacherId(teacherId);
                advancedSearch.setBeginDate(beginDate);
                advancedSearch.setEndDate(endDate);
                List<Schedule> scheduleList = scheduleService.advancedSearchSchedule(advancedSearch);
                request.setAttribute("scheduleList",scheduleList);
                address = "/WEB-INF/pages/scheduleData.jsp";
            } else if (action.equalsIgnoreCase("changePasswordView")) {
                String token = request.getParameter("token");
                Login login = loginService.getLoginByToken(token);
                System.out.println("Login: "+login);
                if (login != null) {
                    System.out.println("111111111");
                    request.setAttribute("token",token);
                    address = "changePassword.jsp";
                } else {
                    address = "error.jsp";
                }
            } else if (action.equalsIgnoreCase("changePassword")) {
                String password = request.getParameter("password");
                String token = request.getParameter("token");
                boolean isChange = loginService.changePassword(Methods.generatePasswordWithSha256(password),token);
                if (isChange) {
                    request.setAttribute("success","Password has been successfully changed!");
                } else {
                    request.setAttribute("invalid","Problem!Password has not been successfully changed!");
                }
                address = "login.jsp";
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (address != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }
    }
}
