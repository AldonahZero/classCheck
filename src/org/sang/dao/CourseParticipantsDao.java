package org.sang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 与会
 * @author 零
 *
 */
public class CourseParticipantsDao {
    public void insert(int meetingid, String[] empids) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("insert into courseparticipants (courseid,personid) values (?,?);");
            for (String empid : empids) {
                ps.setInt(1, meetingid);
                ps.setInt(2, Integer.parseInt(empid));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
    }
}
