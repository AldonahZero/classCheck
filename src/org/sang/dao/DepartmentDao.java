package org.sang.dao;

import org.sang.bean.Date;
import org.sang.bean.Department;
import org.sang.bean.Status;
import org.sang.bean.StringBean;

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
 * 班级DAO
 * @author 零
 *
 */
public class DepartmentDao {
    public int updateDepById(String name, int id) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("UPDATE department SET departmentname=? WHERE departmentid=?");
            ps.setString(1, name);
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

    public int insert(String name) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("insert into department (departmentname) values (?);");
            ps.setString(1, name);
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

    public int deleteDepById(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("DELETE FROM department WHERE departmentid=?");
            ps.setInt(1, id);
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

    public List<Department> getAllDepartment() {
        List<Department> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("select * from department;");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Department(rs.getInt("departmentid"), rs.getString("departmentname")));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

	public static Map<Department, Map<StringBean, Status>> getTimestampattationnumber(
			Date fintime) {
		Map<Department, Map<StringBean, Status>> map=new HashMap<Department, Map<StringBean,Status>>();
		ArrayList<Department> arrayList=new ArrayList<>();
		 Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        try {
	        	//所有班级
	            con = DBUtils.getConnection();
	            ps = con.prepareStatement("select * from department;");
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	arrayList.add(new Department(rs.getInt("departmentid"), rs.getString("departmentname")));
	            }
//	            System.out.println(arrayList);
	            for (Iterator iterator = arrayList.iterator(); iterator
						.hasNext();) {
	            	Map<StringBean, Status> map2=new HashMap<StringBean, Status>();
	            	//查出此班级所有人的id
					Department department = (Department) iterator.next();
					ps = con.prepareStatement("select personid from person where departmentid =?;;");
					ps.setInt(1, department.getDepartmentid());
					rs = ps.executeQuery();
					ArrayList<Integer> personidArrayList= new ArrayList<>();
					while (rs.next()) {
						personidArrayList.add(rs.getInt("personid"));
		            }
//					System.out.println(personidArrayList);
					//拿着id 去查对应有的课程
					ps = con.prepareStatement("select courseid from courseparticipants where status=1 AND personid =?");
					ArrayList<Integer> courseArrayList=new ArrayList<>();
					for (Integer integer : personidArrayList) {
						ps.setInt(1, integer);
						rs = ps.executeQuery();
						if(rs.next())	
							courseArrayList.add(rs.getInt("courseid"));
					}
//					System.out.println(courseArrayList);
					for (Timestamp startTime = fintime.getStarttime(); startTime.getTime()<=fintime.getEndtime().getTime(); startTime=new Timestamp(startTime.getTime()+ 1 * 24 * 60 * 60 * 1000)) 
					{
						ps = con.prepareStatement("select courseid from course where starttime>? AND courseid=? AND endtime<?");
						ps.setTimestamp(1, startTime);
						for (Integer integer : courseArrayList) 
						{
							ps.setInt(2, integer);
							ps.setTimestamp(3, new Timestamp(startTime.getTime()+ 2 * 24 * 60 * 60 * 1000));
							rs = ps.executeQuery();
							Status s=new Status(0);
							while(rs.next())
							{
								s.setStatus(s.getStatus()+1);
							}
							
							String[] strings=startTime.toString().split(" ");
							String string=strings[0].replace("-","/");
							map2.put(new StringBean(string), s);
						}
					}
					
					//拿着时间段 去查每个时间段对应的人数
					
					//
					map.put(department, map2);
	            }
	        }catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return map;
		
	}
	public static void main(String[] args) {
		Map<Department, Map<StringBean, Status>> my=getTimestampattationnumber(new Date(Timestamp.valueOf("2017-12-05 15:28:22.0"), Timestamp.valueOf("2017-12-12 15:28:26.0")));
		for (Department d : my.keySet()) {
			for (StringBean string : my.get(d).keySet()) {
				System.out.println(d+" "+string+" "+my.get(d).get(string));
			}
		}
	}
}
