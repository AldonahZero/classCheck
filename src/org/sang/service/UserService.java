package org.sang.service;

import org.sang.bean.User;
import org.sang.dao.UserDao;
import org.sang.util.Md5;

import java.util.List;

/**
 * 
 * @author 零
 *
 */
public class UserService {
    private UserDao employeeDao = new UserDao();
    private User loginUser;
    public List<User> searchEmp(String employeename, String username, int status, int page, int count) {
        return employeeDao.searchEmp(employeename, username, status, page, count);
    }
    public int getCount(String employeename, String username, int status) {
        return employeeDao.getCount(employeename, username, status);
    }
    public User getLoginUser() {
        return loginUser;
    }
    public int updateEmpStatusById(int id, int status) {
        return employeeDao.updateEmpStatusById(id, status);
    }
    public List<User> getUnApproveaccount() {
        return employeeDao.getUnApproveaccount();
    }
    public String geiemail(String username)
    {
    	return employeeDao.getmail(username);
    }
    /**
     * 登陆
     * @param username
     * @param password
     * @return 状态
     */
    public int login(String username, String password) {
        int result = 3;//表示登录失败 
        User loginEmp = employeeDao.login(username, Md5.GetMD5Code(password));
        if (loginEmp == null) {
            return result;
        }else{
            this.loginUser = loginEmp;
            return loginEmp.getStatus();
        }
    }

    /**
     * 注册
     * @param employee
     * @return 1成功
     */
    public int reg(User employee) {
        return employeeDao.reg(employee);
    }
    public List<User> getEmpByDepId(int depId) {
        return employeeDao.getEmpByDepId(depId);
    }
	public int renewattendMeeting(int mid, int userid, String itclass) {
		// TODO Auto-generated method stub
		int status=0;
		if(itclass.equals("attend"))
		{
			status=1;
		}
		else if(itclass.equals("cancel"))
		{
			status=0;
		}
		else if(itclass.equals("leave"))
		{
			status=2;
		}
		return employeeDao.renewattendMeeting(mid, userid, status);
	}
}
