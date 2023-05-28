package jdbc.study.college.pcps.model.jdbc;

import java.util.List;

import jdbc.study.college.pcps.model.jdbc.dao.StudentDao;

public class TestJdbcConnection {
	public static void main(String[] args) {
		StudentDao stdDao=new StudentDao();
		stdDao.updateStudent();
		//stdDao.insertStudentDetailsUsingPreparedStatement();
		//List<Student> studentList=stdDao.getAllStudentDetails();
		//for(Student std:studentList) {
		//	System.out.println(std.getName());
		//	System.out.println(std.getRegdNo());
			
		//}
	}
}
