package org.sang.dao;

import org.sang.bean.Status;
import org.sang.bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 零
 *
 */
public class UserDao {

    public int updateEmpStatusById(int id, int status) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("UPDATE person set status=? WHERE personid=?");
            ps.setInt(1, status);
            ps.setInt(2, id);
            return ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return -1;
    }

    public List<User> getUnApproveaccount() {
        List<User> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT * FROM person WHERE status=0 AND role >1");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("personid"), rs.getString("personname"), rs.getString("username"), rs.getString("phone"), rs.getString("email"), rs.getInt("status"), rs.getInt("departmentid"), rs.getString("password"), rs.getInt("role")));
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return list;
    }

    public List<User> getEmpByDepId(int depId) {
        List<User> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT * FROM person WHERE status=1 AND departmentid=?");
            ps.setInt(1,depId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("personid"), rs.getString("personname"), rs.getString("username"), rs.getString("phone"), rs.getString("email"), rs.getInt("status"), rs.getInt("departmentid"), rs.getString("password"), rs.getInt("role")));
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return list;
    }

    /**
     * 得到某个课程的所有参加者
     * @param mid
     * @return
     */
    public List<User> getEmpByMeetingId(int mid) {
        List<User> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT * FROM person WHERE personid IN(SELECT personid FROM courseparticipants WHERE courseid=?)");
            ps.setInt(1,mid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("personid"), rs.getString("personname"), rs.getString("username"), rs.getString("phone"), rs.getString("email"), rs.getInt("status"), rs.getInt("departmentid"), rs.getString("password"), rs.getInt("role")));
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return list;
    }

    /**
     * 请假会议
     * @param mid
     * @param reason
     * @return
     */
    public int leaveMeeting(int mid, int userid) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("UPDATE courseparticipants  SET status=2 WHERE courseid=? AND personid=?");
            ps.setInt(1, mid);
            ps.setInt(2, userid);
            return ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return -1;
    }
    
    /**
     * 更新课程
     * @param mid
     * @param reason
     * @return
     */
    public int renewattendMeeting(int mid, int userid,int status) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("UPDATE courseparticipants  SET status=? WHERE courseid=? AND personid=?");
            ps.setInt(1, status);
            ps.setInt(2, mid);
            ps.setInt(3, userid);
            return ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return -1;
    }
    
    /**
     * 参加课程
     * @param mid
     * @param reason
     * @return
     */
    public int attendMeeting(int mid, int userid) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("UPDATE courseparticipants  SET status=1 WHERE courseid=? AND personid=?");
    
            ps.setInt(1, mid);
            ps.setInt(2, userid);
            return ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return -1;
    }
    
    
    /**
     * 得到某个课程的所有参加者的状态
     * @param mid
     * @return
     */
    public HashMap<User, Status> getEmpStatusByUser(List<User> Users,int mid) {
    	HashMap<User, Status> map= new HashMap<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            for (Iterator iterator = Users.iterator(); iterator.hasNext();) {
				User user = (User) iterator.next();
				ps = con.prepareStatement("SELECT status FROM courseparticipants WHERE courseid=? AND personid=?");
	            ps.setInt(1,mid);
	            ps.setInt(2,user.getEmployeeid());
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                map.put(user,new Status(rs.getInt("status")));
	            }
			}
            
            
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return map;
    }
    
    
    public int getCount(String employeename, String username, int status) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sb = new StringBuffer("SELECT count(*) FROM person WHERE status=? AND role >1");
        if (employeename != null && !"".equals(employeename)) {
            sb.append(" and personname=?");
        }
        if (username != null && !"".equals(username)) {
            sb.append(" and username=?");
        }
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement(sb.toString());
            ps.setInt(1, status);
            int index = 2;
            if (employeename != null && !"".equals(employeename)) {
                ps.setString(index++, employeename);
            }
            if (username != null && !"".equals(username)) {
                ps.setString(index++, username);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return -1;
    }

    public List<User> searchEmp(String employeename, String username, int status, int page, int count) {
        List<User> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sb = new StringBuffer("SELECT * FROM person WHERE status=? AND role >1");
        if (employeename != null && !"".equals(employeename)) {
            sb.append(" and personname=?");
        }
        if (username != null && !"".equals(username)) {
            sb.append(" and username=?");
        }
        sb.append(" limit ?,?");
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement(sb.toString());
            ps.setInt(1, status);
            int index = 2;
            if (employeename != null && !"".equals(employeename)) {
                ps.setString(index++, employeename);
            }
            if (username != null && !"".equals(username)) {
                ps.setString(index++, username);
            }
            ps.setInt(index++, (page - 1) * count);
            ps.setInt(index++, count);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("personid"), rs.getString("personname"), rs.getString("username"), rs.getString("phone"), rs.getString("email"), rs.getInt("status"), rs.getInt("departmentid"), rs.getString("password"), rs.getInt("role")));
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return list;
    }

    /**
     * 登陆
     * @param username
     * @param password
     * @return 用户bean对象
     */
    public User login(String username, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT * FROM person WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("personid"), rs.getString("personname"), rs.getString("username"), rs.getString("phone"), rs.getString("email"), rs.getInt("status"), rs.getInt("departmentid"), rs.getString("password"), rs.getInt("role"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 注册
     * @param person
     * @return 1成功
     */
    public int reg(User employee) {
        if (isUsernameExists(employee.getUsername())) {
            return -1;
        }
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("INSERT INTO person(personname,username,phone,email,status,departmentid,password,role) VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, employee.getEmployeename());
            ps.setString(2, employee.getUsername());
            ps.setString(3, employee.getPhone());
            ps.setString(4, employee.getEmail());
            ps.setInt(5, 0);
            ps.setInt(6, employee.getDepartmentid());
            ps.setString(7, employee.getPassword());
            ps.setInt(8, 2);
            return ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return 0;
    }

    public boolean isUsernameExists(String username) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT * from person WHERE username=?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return false;
    }

	public static String getmail(String username) {
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT email from person WHERE username=?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("email");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
		return null;
	}
	
	public static void main(String[] args) {
//		System.out.println(getmail("0001"));
	}
}
