package az.orient.course.dao;

import az.orient.course.model.Login;

public interface LoginDao {

    Login login(String username, String password) throws Exception;

    Login getLoginByEmail(String email) throws Exception;

    Login getLoginByToken(String token) throws Exception;

    boolean changePassword(String password , String token) throws Exception;
}
