package az.orient.course.service;

import az.orient.course.model.Login;

public interface LoginService {

    Login login(String username, String password) throws Exception;

    Login getLoginByEmail(String email) throws Exception;

    Login getLoginByToken(String token) throws Exception;

    boolean changePassword(String password, String token) throws Exception;
}
