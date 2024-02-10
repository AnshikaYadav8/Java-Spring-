package com.capgemini.main;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class OperationClass {
	Scanner scanner =new Scanner(System.in);
	ConnectionClass connectionobject = new ConnectionClass();
	Connection connection=null;
	int id;
public void insert_record()throws ClassNotFoundException,SQLException {
	System.out.println("Enter Student UID: ");
	String studentUID=scanner.next();
	System.out.println("Enter Student Name: ");
	String studentName=scanner.next();
	System.out.println("Enter Student Course: ");
	String studentCourse=scanner.next();
	System.out.println("Enter Student Batch: ");
	String studentBatch=scanner.next();
	if(connection==null) {
		System.out.println("w");
		connection = connectionobject.getConnection();
		String insertQuery ="Insert into Student values(?,?,?,?,?)";
		String searchQuery = "select max(idstudent) from student";
		PreparedStatement psmt1 =connection.prepareStatement(searchQuery);
		ResultSet rs = psmt1.executeQuery();
		System.out.println(psmt1);
		if(rs.next()) {
			

				id = rs.getInt(1);
				
				id+=1;
				

		}
		else {
			System.out.println(id);
		}
		PreparedStatement psmt =connection.prepareStatement(insertQuery);
		psmt.setInt(1, id);
		psmt.setString(2, studentUID);
		psmt.setString(3, studentName);
		psmt.setString(4, studentCourse);
		psmt.setString(5, studentBatch);
		int status = psmt.executeUpdate();
		if(status >0) {
			System.out.println("Record insert sucessfully"+status);
		}
		else {
			System.out.println("Record not insereted");
		}
		psmt.close();
		connection.close();
	
	}
	
}
public void delete_record() throws ClassNotFoundException, SQLException {
    System.out.println("Enter Student UID to delete: ");
    String studentUID = scanner.next();

    if (connection == null) {
        connection = connectionobject.getConnection();
        String deleteQuery = "DELETE FROM Student WHERE studentUID = ?";
        PreparedStatement psmt = connection.prepareStatement(deleteQuery);
        psmt.setString(1, studentUID);

        int status = psmt.executeUpdate();
        if (status > 0) {
            System.out.println("Record deleted successfully");
        } else {
            System.out.println("Record not found or could not be deleted");
        }

        psmt.close();
        connection.close();
    }
}
public void displayOneRecord()throws ClassNotFoundException,SQLException{
	 System.out.println("Enter Student UID to Display the Record: ");
	 String studentUID=scanner.next();
	 if(connection==null) {
		 connection = connectionobject.getConnection();
		 String displayQuery = "select * FROM student WHERE StudentUID=?";
		 PreparedStatement psmt = connection.prepareStatement(displayQuery);
		 psmt.setString(1,studentUID);
		 System.out.println("a");
		 ResultSet rs =psmt.executeQuery();
		 System.out.println("b");
		 if(rs.next()) {

			 System.out.println("Student UID: "+rs.getString(2));
			 System.out.println("Student Name: "+rs.getString(3));
			 
			 System.out.println("Student Course: "+rs.getString(4));
			 System.out.println("Student Branch: "+rs.getString(5));
			 
		 }
		 else {
			 System.out.println("Record Not Found !");
		 }
		 psmt.close();
	     connection.close();
	 }
}
public void displayAllRecord()throws ClassNotFoundException,SQLException{
	
	 if(connection==null) {
		 connection = connectionobject.getConnection();
		 String displayQuery = "select * FROM student";
		 PreparedStatement psmt = connection.prepareStatement(displayQuery);
		
		 ResultSet rs =psmt.executeQuery();
		 
		 while(rs.next()) {

			 System.out.println("Student UID: "+rs.getString(2));
			 System.out.println("Student Name: "+rs.getString(3));
			 System.out.println("Student Course: "+rs.getString(4));
			 System.out.println("Student Branch: "+rs.getString(5));
			 
		 }
		 
		 psmt.close();
	     connection.close();
	 }
}
public void update_record() throws ClassNotFoundException, SQLException {
    System.out.println("Enter Student UID to update: ");
    String studentUID = scanner.next();

    if (connection == null) {
        connection = connectionobject.getConnection();
        String selectQuery = "SELECT * FROM Student WHERE studentUID = ?";
        String updateQuery = "UPDATE Student SET studentName = ?, studentCourse = ?, studentBatch = ? WHERE studentUID = ?";

        PreparedStatement psmtSelect = connection.prepareStatement(selectQuery);
        psmtSelect.setString(1, studentUID);

        ResultSet rs = psmtSelect.executeQuery();
        if (rs.next()) {
            
            System.out.println("Enter new Student Name: ");
            String newStudentName = scanner.next();
            System.out.println("Enter new Student Course: ");
            String newStudentCourse = scanner.next();
            System.out.println("Enter new Student Batch: ");
            String newStudentBatch = scanner.next();
            

            
            PreparedStatement psmtUpdate = connection.prepareStatement(updateQuery);
            psmtUpdate.setString(1, newStudentName);
            psmtUpdate.setString(2, newStudentCourse);
            psmtUpdate.setString(3, newStudentBatch);
            psmtUpdate.setString(4, studentUID);

            int status = psmtUpdate.executeUpdate();
            if (status > 0) {
                System.out.println("Record updated successfully");
            } else {
                System.out.println("Record update failed");
            }

            psmtUpdate.close();
        } else {
            System.out.println("Record not found");
        }

        rs.close();
        psmtSelect.close();
        connection.close();
    }
}

}