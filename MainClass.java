package com.capgemini.main;

import java.sql.SQLException;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) throws ClassNotFoundException,SQLException {
		OperationClass object = new OperationClass();
		Scanner scanner=new Scanner(System.in);
		do {
			System.out.println("Enter 1 for insert a record ");
			System.out.println("Enter 2 for delete a record ");
			System.out.println("Enter 3 for update a record ");
			System.out.println("Enter 4 for Display one record ");
			System.out.println("Enter 5 for Display all record ");
			System.out.println("Enter 6 for Exit ");
			int reply=scanner.nextInt()	;
			switch(reply) {
			case 1:
				object.insert_record();
				break;
			case 2:
				object.delete_record();
				break;
			case 3:
				object.update_record();
				break;
			case 4:
				object.displayOneRecord();
				break;
			case 5:
				object.displayAllRecord();
				break;
			case 6:
				System.out.println("You have exited");
				System.exit(0);
			default:
				System.out.println("Invalid");
				
			}
			}while(true);
		
		// this is the comment to check either th ehothub is workign or not.


	}
}