package az.orient.course.service.impl;

import az.orient.course.dao.LoginDao;
import az.orient.course.model.Login;
import az.orient.course.service.LoginService;

public class LoginServiceImpl implements LoginService {

   private LoginDao loginDao;

    public LoginServiceImpl(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    @Override
    public Login login(String username, String password) throws Exception {
        return loginDao.login(username,password);
    }

    @Override
    public Login getLoginByEmail(String email) throws Exception {
        return loginDao.getLoginByEmail(email);
    }

    @Override
    public Login getLoginByToken(String token) throws Exception {
        return loginDao.getLoginByToken(token);
    }

    @Override
    public boolean changePassword(String password, String token) throws Exception {
        return loginDao.changePassword(password,token);
    }
}
