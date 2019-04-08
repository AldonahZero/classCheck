package org.sang.dao;

import org.sang.bean.ClassRoom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 教室DAO
 * @author 零
 *
 */
public class ClassRoomDao {
    public List<ClassRoom> getAllMeetingRoom() {
        List<ClassRoom> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("select * from classroom;");
            rs = ps.executeQuery();
            while (rs.next()) {
                ClassRoom meetingRoom = new ClassRoom(rs.getInt("roomid"), rs.getInt("roomnum"), rs.getString("roomname"), rs.getInt("capacity"), rs.getInt("status"), rs.getString("description"));
                list.add(meetingRoom);
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
    
    
    public List<ClassRoom> getMeetingRoomBypages(int page, int count) {
        List<ClassRoom> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("select * from classroom limit ?,?;");
            ps.setInt(1, (page - 1) * count);
            ps.setInt(2, count);
            rs = ps.executeQuery();
            while (rs.next()) {
                ClassRoom meetingRoom = new ClassRoom(rs.getInt("roomid"), rs.getInt("roomnum"), rs.getString("roomname"), rs.getInt("capacity"), rs.getInt("status"), rs.getString("description"));
                list.add(meetingRoom);
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
    
    
    public ClassRoom getMeetingRoomById(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("select * from classroom WHERE roomid=?;");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ClassRoom meetingRoom = new ClassRoom(rs.getInt("roomid"), rs.getInt("roomnum"), rs.getString("roomname"), rs.getInt("capacity"), rs.getInt("status"), rs.getString("description"));
                return meetingRoom;
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

    public int insert(ClassRoom meetingRoom) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("INSERT INTO classroom(roomnum,roomname,capacity,status,description) VALUES (?,?,?,?,?)");
            ps.setInt(1, meetingRoom.getRoomnum());
            ps.setString(2, meetingRoom.getRoomname());
            ps.setInt(3, meetingRoom.getCapacity());
            ps.setInt(4, meetingRoom.getStatus());
            ps.setString(5, meetingRoom.getDescription());
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

    public int update(ClassRoom meetingRoom) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("UPDATE classroom set roomnum=?,roomname=?,capacity=?,status=?,description=? WHERE roomid=?");
            ps.setInt(1, meetingRoom.getRoomnum());
            ps.setString(2, meetingRoom.getRoomname());
            ps.setInt(3, meetingRoom.getCapacity());
            ps.setInt(4, meetingRoom.getStatus());
            ps.setString(5, meetingRoom.getDescription());
            ps.setInt(6, meetingRoom.getRoomid());
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

    public static void main(String[] args) {
//        ClassRoom meetingRoomById = new ClassRoomDao().getMeetingRoomById(6);
//        System.out.println(meetingRoomById);
    }
}
