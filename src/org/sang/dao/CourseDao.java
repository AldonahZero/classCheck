package org.sang.dao;

import org.sang.bean.Course;
import org.sang.bean.Position;
import org.sang.bean.Status;
import org.sang.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 课程DAO
 * @author 零
 *
 */
public class CourseDao {
    public int getCount(String meetingname, String roomname, String reservername, String reservefromdate, String reservetodate, String meetingfromdate, String meetingtodate) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sb = new StringBuffer("SELECT count(*) FROM course m,person e,classroom mr WHERE m.`roomid`=mr.`roomid` AND m.`reservationistid`=e.`personid`");
        if (meetingname != null && !"".equals(meetingname)) {
            sb.append(" and coursename=?");
        }
        if (roomname != null && !"".equals(roomname)) {
            sb.append(" and coursename=?");
        }
        if (reservername != null && !"".equals(reservername)) {
            sb.append(" and coursename=?");
        }
        if (reservefromdate != null && !"".equals(reservefromdate) && reservetodate != null && !"".equals(reservetodate)) {
            sb.append(" and reservationtime > ? and reservationtime<?");
        }
        if (meetingfromdate != null && !"".equals(meetingfromdate) && meetingtodate != null && !"".equals(meetingtodate)) {
            sb.append(" and starttime>? and endtime<?");
        }
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement(sb.toString());
            int index = 1;
            if (meetingname != null && !"".equals(meetingname)) {
                ps.setString(index++, meetingname);
            }
            if (roomname != null && !"".equals(roomname)) {
                ps.setString(index++, roomname);
            }
            if (reservername != null && !"".equals(reservername)) {
                ps.setString(index++, reservername);
            }
            if (reservefromdate != null && !"".equals(reservefromdate) && reservetodate != null && !"".equals(reservetodate)) {
                ps.setTimestamp(index++, Timestamp.valueOf(reservefromdate));
                ps.setTimestamp(index++, Timestamp.valueOf(reservetodate));
            }
            if (meetingfromdate != null && !"".equals(meetingfromdate) && meetingtodate != null && !"".equals(meetingtodate)) {
                ps.setTimestamp(index++, Timestamp.valueOf(meetingfromdate));
                ps.setTimestamp(index++, Timestamp.valueOf(meetingtodate));
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

    public List<Course> searchMeeting(String meetingname, String roomname, String reservername, String reservefromdate, String reservetodate, String meetingfromdate, String meetingtodate, int page, int count) {
        List<Course> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sb = new StringBuffer("SELECT m.*,e.`personname` AS personname,mr.`roomname` AS roomname FROM course m,person e,classroom mr WHERE m.`roomid`=mr.`roomid` AND m.`reservationistid`=e.`personid`");
        if (meetingname != null && !"".equals(meetingname)) {
            sb.append(" and coursename=?");
        }
        if (roomname != null && !"".equals(roomname)) {
            sb.append(" and roomname=?");
        }
        if (reservername != null && !"".equals(reservername)) {
            sb.append(" and personname=?");
        }
        if (reservefromdate != null && !"".equals(reservefromdate) && reservetodate != null && !"".equals(reservetodate)) {
            sb.append(" and reservationtime > ? and reservationtime<?");
        }
        if (meetingfromdate != null && !"".equals(meetingfromdate) && meetingtodate != null && !"".equals(meetingtodate)) {
            sb.append(" and starttime>? and endtime<?");
        }
        sb.append(" limit ?,?");
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement(sb.toString());
            int index = 1;
            if (meetingname != null && !"".equals(meetingname)) {
                ps.setString(index++, meetingname);
            }
            if (roomname != null && !"".equals(roomname)) {
                ps.setString(index++, roomname);
            }
            if (reservername != null && !"".equals(reservername)) {
                ps.setString(index++, reservername);
            }
            if (reservefromdate != null && !"".equals(reservefromdate) && reservetodate != null && !"".equals(reservetodate)) {
                ps.setTimestamp(index++, Timestamp.valueOf(reservefromdate));
                ps.setTimestamp(index++, Timestamp.valueOf(reservetodate));
            }
            if (meetingfromdate != null && !"".equals(meetingfromdate) && meetingtodate != null && !"".equals(meetingtodate)) {
                ps.setTimestamp(index++, Timestamp.valueOf(meetingfromdate));
                ps.setTimestamp(index++, Timestamp.valueOf(meetingtodate));
            }
            ps.setInt(index++, (page - 1) * count);
            ps.setInt(index++, count);
            rs = ps.executeQuery();
            while (rs.next()) {
                int meetingid = rs.getInt("courseid");
                String meetingname1 = rs.getString("coursename");
                int roomid = rs.getInt("roomid");
                int reservationistid = rs.getInt("reservationistid");
                int numberofparticipants = rs.getInt("numberofparticipants");
                Timestamp starttime = rs.getTimestamp("starttime");
                Timestamp endtime = rs.getTimestamp("endtime");
                Timestamp reservationtime = rs.getTimestamp("reservationtime");
                Timestamp canceledtime = rs.getTimestamp("canceledtime");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                String employeename = rs.getString("personname");
                String roomname1 = rs.getString("roomname");
                Course meeting = new Course(meetingid, meetingname1, roomid, reservationistid, numberofparticipants, starttime, endtime, reservationtime, canceledtime, description, status);
                meeting.setEmpname(employeename);
                meeting.setRoomname(roomname1);
                list.add(meeting);
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
        return list;
    }

    public List<Course> getMeeting7Days(int empid) {
        List<Course> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT m.*,mr.`roomname` AS roomname FROM course m,classroom mr WHERE courseid IN(SELECT courseid FROM courseparticipants WHERE personid=?) AND m.`roomid`=mr.`roomid` AND starttime<? AND starttime>?");
            ps.setInt(1, empid);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000));
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            rs = ps.executeQuery();
            while (rs.next()) {
                int meetingid = rs.getInt("courseid");
                String meetingname1 = rs.getString("coursename");
                int roomid = rs.getInt("roomid");
                int reservationistid = rs.getInt("reservationistid");
                int numberofparticipants = rs.getInt("numberofparticipants");
                Timestamp starttime = rs.getTimestamp("starttime");
                Timestamp endtime = rs.getTimestamp("endtime");
                Timestamp reservationtime = rs.getTimestamp("reservationtime");
                Timestamp canceledtime = rs.getTimestamp("canceledtime");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                String roomname1 = rs.getString("roomname");
                Course meeting = new Course(meetingid, meetingname1, roomid, reservationistid, numberofparticipants, starttime, endtime, reservationtime, canceledtime, description, status);
                meeting.setRoomname(roomname1);
//                System.out.println(meeting+"00");
                list.add(meeting);
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
        return list;
    }

    public List<Course> getMyBookingMeeting(int empid) {
        List<Course> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT m.*,mr.`roomname` AS roomname FROM course m,classroom mr WHERE m.`reservationistid`=? AND m.status=0 AND m.`roomid`=mr.`roomid`");
            ps.setInt(1, empid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int meetingid = rs.getInt("courseid");
                String meetingname1 = rs.getString("coursename");
                int roomid = rs.getInt("roomid");
                int reservationistid = rs.getInt("reservationistid");
                int numberofparticipants = rs.getInt("numberofparticipants");
                Timestamp starttime = rs.getTimestamp("starttime");
                Timestamp endtime = rs.getTimestamp("endtime");
                Timestamp reservationtime = rs.getTimestamp("reservationtime");
                Timestamp canceledtime = rs.getTimestamp("canceledtime");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                String roomname1 = rs.getString("roomname");
                Course meeting = new Course(meetingid, meetingname1, roomid, reservationistid, numberofparticipants, starttime, endtime, reservationtime, canceledtime, description, status);
                meeting.setRoomname(roomname1);
                list.add(meeting);
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
        return list;
    }

    public List<Course> getCanceledMeeting(int empid) {
        List<Course> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT m.*,mr.`roomname` AS roomname FROM course m,classroom mr WHERE courseid IN(SELECT courseid FROM courseparticipants WHERE personid=?) AND m.`roomid`=mr.`roomid` AND m.status=1");
            ps.setInt(1, empid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int meetingid = rs.getInt("courseid");
                String meetingname1 = rs.getString("coursename");
                int roomid = rs.getInt("roomid");
                int reservationistid = rs.getInt("reservationistid");
                int numberofparticipants = rs.getInt("numberofparticipants");
                Timestamp starttime = rs.getTimestamp("starttime");
                Timestamp endtime = rs.getTimestamp("endtime");
                Timestamp reservationtime = rs.getTimestamp("reservationtime");
                Timestamp canceledtime = rs.getTimestamp("canceledtime");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                String roomname1 = rs.getString("roomname");
                String canceledreason = rs.getString("canceledreason");
                Course meeting = new Course(meetingid, meetingname1, roomid, reservationistid, numberofparticipants, starttime, endtime, reservationtime, canceledtime, description, status);
                meeting.setCanceledreason(canceledreason);
                meeting.setRoomname(roomname1);
                list.add(meeting);
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
        return list;
    }

    /**
     * 个人参会情况
     * @param empid
     * @return
     */
    public Map<Course, Status> getMyMeetingcondition(int empid) {
    	Map<Course, Status> map=new HashMap<>();
    	List<Course> list = getMyMeeting(empid);
    	 Connection con = null;
         PreparedStatement ps = null;
         ResultSet rs = null;
         for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Course course = (Course) iterator.next();
			try{
				con = DBUtils.getConnection();
	        	 ps = con.prepareStatement("SELECT status from courseparticipants where courseid=? AND personid=?");
	             ps.setInt(1, course.getMeetingid());
	             ps.setInt(2, empid);
	             rs = ps.executeQuery();
	             while (rs.next()) {
	            	 int status=rs.getInt("status");
//	            	 System.out.println(status);
	                 map.put(course,new Status(status));
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
		}
         
         return map;
	}
    
    /**
     * 班级参会情况
     * @param empid
     * @return
     */

    public Map<Map<Course, Status>,User> getClassMeetingcondition(int depid) {
    	Map<Map<Course, Status>,User> map=new HashMap<Map<Course, Status>,User>();
    	//根据班级id拿到所有人
    	List<User> list = new ArrayList<>();
    	 Connection con = null;
         PreparedStatement ps = null;
         ResultSet rs = null;
         try {
             con = DBUtils.getConnection();
             ps = con.prepareStatement("SELECT * FROM person WHERE status=1 AND departmentid=?");
             ps.setInt(1, depid);
             rs = ps.executeQuery();
             while (rs.next()) {
            	 User user=new User(rs.getInt("personid"), rs.getString("personname"), rs.getString("username"), rs.getString("phone"), rs.getString("email"), rs.getInt("status"), rs.getInt("departmentid"), rs.getString("password"), rs.getInt("role"));
//            	 System.out.println(user);
                 list.add(user);
             }
             for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				User user = (User) iterator.next();
				Map<Course, Status> map2=getMyMeetingcondition(user.getEmployeeid());
				map.put(map2, user);
				
			}
             return map;
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (SQLException e) {
             e.printStackTrace();
         } 
         return null;
	}
    public List<Course> getMyMeeting(int empid) {
        List<Course> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT m.*,e.`personname`,mr.`roomname` FROM course m,person e,classroom mr WHERE m.`courseid` IN(SELECT mp.`courseid` FROM courseparticipants mp WHERE mp.`personid`=?) AND m.`roomid`=mr.`roomid` AND m.`reservationistid`=e.`personid` and m.status=0 AND m.starttime>?");
            ps.setInt(1, empid);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            rs = ps.executeQuery();
            while (rs.next()) {
                int meetingid = rs.getInt("courseid");
                String meetingname1 = rs.getString("coursename");
                int roomid = rs.getInt("roomid");
                int reservationistid = rs.getInt("reservationistid");
                int numberofparticipants = rs.getInt("numberofparticipants");
                Timestamp starttime = rs.getTimestamp("starttime");
                Timestamp endtime = rs.getTimestamp("endtime");
                Timestamp reservationtime = rs.getTimestamp("reservationtime");
                Timestamp canceledtime = rs.getTimestamp("canceledtime");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                String roomname1 = rs.getString("roomname");
                String employeename = rs.getString("personname");
                String canceledreason = rs.getString("canceledreason");
                Course meeting = new Course(meetingid, meetingname1, roomid, reservationistid, numberofparticipants, starttime, endtime, reservationtime, canceledtime, description, status);
                meeting.setCanceledreason(canceledreason);
                meeting.setRoomname(roomname1);
                meeting.setEmpname(employeename);
                list.add(meeting);
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
        return list;
    }

    /**
     * 添加一条课程记录
     *
     * @param course
     * @return 返回值为插入记录的id
     */
    public int insert(Course meeting) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("insert into course (coursename,roomid,reservationistid,numberofparticipants,starttime,endtime,reservationtime,description,status) values (?,?,?,?,?,?,?,?,?);", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, meeting.getMeetingname());
            ps.setInt(2, meeting.getRoomid());
            ps.setInt(3, meeting.getReservationistid());
            ps.setInt(4, meeting.getNumberofparticipants());
            ps.setTimestamp(5, meeting.getStarttime());
            ps.setTimestamp(6, meeting.getEndtime());
            ps.setTimestamp(7, meeting.getReservationtime());
            ps.setString(8, meeting.getDescription());
            ps.setInt(9, meeting.getStatus());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
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

    public Course getMeetingById(int mid) {
        Course meeting = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("select * from course WHERE courseid=?;");
            ps.setInt(1, mid);
            rs = ps.executeQuery();
            if (rs.next()) {
                int meetingid = rs.getInt("courseid");
                String meetingname1 = rs.getString("coursename");
                int roomid = rs.getInt("roomid");
                int reservationistid = rs.getInt("reservationistid");
                int numberofparticipants = rs.getInt("numberofparticipants");
                Timestamp starttime = rs.getTimestamp("starttime");
                Timestamp endtime = rs.getTimestamp("endtime");
                Timestamp reservationtime = rs.getTimestamp("reservationtime");
                Timestamp canceledtime = rs.getTimestamp("canceledtime");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                meeting = new Course(meetingid, meetingname1, roomid, reservationistid, numberofparticipants, starttime, endtime, reservationtime, canceledtime, description, status);
                return meeting;
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

    
    /**
     * 取消课程
     * @param mid
     * @param reason
     * @return
     */
    public int cancelMeeting(int mid, String reason) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("UPDATE course  SET status=1,canceledtime=?,canceledreason=? WHERE courseid=?;");
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.setString(2, reason);
            ps.setInt(3, mid);
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
     * //拿到对应mid的roomid再取出经纬度
     * @param mid
     * @return
     */
	public Position getclassroompostionBymid(int mid) {
		// TODO Auto-generated method stub
		 Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs=null;
	        try {
	            con = DBUtils.getConnection();
	            ps = con.prepareStatement("select roomid from course where courseid=?;");
	            ps.setInt(1, mid);
	            rs = ps.executeQuery();
	            int roomid=0;
	            if (rs.next()) {
	            	roomid=rs.getInt("roomid");
	            }
	          
	           ps = con.prepareStatement("select longitude,latitude from classroom where roomid=?;");
	           ps.setInt(1, roomid);
	           rs = ps.executeQuery();
	           if (rs.next())
	           return new Position(rs.getDouble("longitude"), rs.getDouble("latitude"));
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DBUtils.close(ps);
	            DBUtils.close(con);
	        }
	        return null;
	}

    
    public static void main(String[] args) {
//        Course meeting = new CourseDao().getMeetingById(25);
//        System.out.println(meeting.toString());
//    	System.out.println(getclassroompostionBymid(25));
    }

  
	
}
