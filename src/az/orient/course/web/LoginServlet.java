package az.orient.course.web;

import az.orient.course.dao.LoginDao;
import az.orient.course.dao.impl.LoginDaoImpl;
import az.orient.course.model.Login;
import az.orient.course.service.LoginService;
import az.orient.course.service.impl.LoginServiceImpl;
import az.orient.course.util.Methods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet" , urlPatterns = "/ls")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       processRequest(request,response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = null;
        LoginDao loginDao = new LoginDaoImpl();
        LoginService loginService = new LoginServiceImpl(loginDao);
        String action = null;
        try {
            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }

            if (action.equalsIgnoreCase("login")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                    Login login = loginService.login(username,Methods.generatePasswordWithSha256(password));
                    if (login != null) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("login",login);
                        address = "main.jsp";
                    } else {
                        request.setAttribute("invalid","Username or password is invalid!");
                        address = "login.jsp";
                    }
                } else {
                    request.setAttribute("invalid","Username or password is empty!");
                    address = "login.jsp";
                }
            } else if (action.equalsIgnoreCase("forgotPassword")) {
                address = "forgotPassword.jsp";
            }  else if (action.equalsIgnoreCase("verifyPassword")) {
                String email = request.getParameter("email");
                Login user = loginService.getLoginByEmail(email);
                String emailSubject = "Pls confirm this url";
                String emailTxt = "http://localhost:8073/course/cs?action=changePasswordView&token="+user.getToken();
                boolean isSend = Methods.sendMail(email,emailSubject,emailTxt);
                if (isSend) {
                    request.setAttribute("success","Link has been successfully sended!");
                } else {
                    request.setAttribute("invalid","Problem!Link has not been successfully sended!");
                }
                address = "forgotPassword.jsp";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (address != null)
        request.getRequestDispatcher(address).forward(request,response);
        //  response.sendRedirect(address);
    }

}
