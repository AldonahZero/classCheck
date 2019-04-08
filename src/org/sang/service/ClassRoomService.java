package org.sang.service;

import org.sang.bean.ClassRoom;
import org.sang.dao.ClassRoomDao;

import java.util.List;

/**
 * 
 * @author 零
 *
 */
public class ClassRoomService {
    private ClassRoomDao meetingRoomDao = new ClassRoomDao();
    public int insert(ClassRoom meetingRoom) {
        return meetingRoomDao.insert(meetingRoom);
    }
    public List<ClassRoom> getAllMeetingRoom(){
        return meetingRoomDao.getAllMeetingRoom();
    }
    public ClassRoom getMeetingRoomById(int id) {
        return meetingRoomDao.getMeetingRoomById(id);
    }
    public int update(ClassRoom meetingRoom) {
        return meetingRoomDao.update(meetingRoom);
    }
    public List<ClassRoom> getClassRooms(int page,int count)
    {
    	return meetingRoomDao.getMeetingRoomBypages(page, count);
    }
}
