package org.sang.service;

import org.sang.bean.Position;
import org.sang.bean.Status;
import org.sang.bean.User;
import org.sang.bean.Course;
import org.sang.dao.UserDao;
import org.sang.dao.CourseDao;
import org.sang.dao.CourseParticipantsDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 零
 *
 */
public class CourseService {
    private CourseDao meetingDao = new CourseDao();
    private UserDao employeeDao = new UserDao();
    private List<User> emps;
    private HashMap<User, Status> empssHashMap;
    public List<User> getEmps() {
        return emps;
    }

    
    public HashMap<User, Status> getEmpssHashMap() {
		return empssHashMap;
	}


	private CourseParticipantsDao meetingParticipantsDao = new CourseParticipantsDao();

    public void insert(Course meeting, String[] empids) {
        int insert = meetingDao.insert(meeting);
        meetingParticipantsDao.insert(insert, empids);
    }

    public List<Course> searchMeeting(String meetingname, String roomname, String reservername, String reservefromdate, String reservetodate, String meetingfromdate, String meetingtodate, int page, int count) {
        return meetingDao.searchMeeting(meetingname, roomname, reservername, reservefromdate, reservetodate, meetingfromdate, meetingtodate, page, count);
    }

    public int getCount(String meetingname, String roomname, String reservername, String reservefromdate, String reservetodate, String meetingfromdate, String meetingtodate) {
        return meetingDao.getCount(meetingname, roomname, reservername, reservefromdate, reservetodate, meetingfromdate, meetingtodate);
    }

    public Course getMeetingDetailsByMeetingId(int mid) {
        Course meeting = meetingDao.getMeetingById(mid);
        this.emps = employeeDao.getEmpByMeetingId(meeting.getMeetingid());
        this.empssHashMap= employeeDao.getEmpStatusByUser(this.emps, mid);
        return meeting;
    }
    public List<Course> getCanceledMeeting(int empid) {
        return meetingDao.getCanceledMeeting(empid);
    }
    
    /**
     * 获取七天会议
     * @param empid
     * @return
     */
    public List<Course> getMeeting7Days(int empid) {
        return meetingDao.getMeeting7Days(empid);
    }
    /**
     * 预定会议
     * @param empid
     * @return
     */
    public List<Course> getMyBookingMeeting(int empid) {
        return meetingDao.getMyBookingMeeting(empid);
    }
    /**
     * 参加
     * @param mid
     * @param reason
     * @return
     */
    public int attendMeeting(int mid, int userid) {
        return employeeDao.attendMeeting(mid, userid);
    }
    
    /**
     * 请假
     * @param mid
     * @param reason
     * @return
     */
    public int leaveMeeting(int mid, int userid) {
        return employeeDao.leaveMeeting(mid, userid);
    }
    /**
     * 取消会议
     * @param mid
     * @param reason
     * @return
     */
    public int cancelMeeting(int mid, String reason) {
        return meetingDao.cancelMeeting(mid, reason);
    }
    /**
     * 我的会议
     * @param empid
     * @return
     */
    public List<Course> getMyMeeting(int empid) {
        return meetingDao.getMyMeeting(empid);
    }
    /**
     * 我的参会情况
     */
    public Map<Course,Status> getMyMeetingcondition(int empid) {
        return meetingDao.getMyMeetingcondition(empid);
    }
    
    /**
     * 班级的参会情况
     */
    public Map<Map<Course, Status>,User> getClassMeetingcondition(int depid) {
        return meetingDao.getClassMeetingcondition(depid);
    }

    /**
     * 获取位置
     * @param parseInt
     * @return
     */
	public Position getclassroompostionBymid(int mid) {
		// TODO Auto-generated method stub
		return meetingDao.getclassroompostionBymid(mid);
	}
}
