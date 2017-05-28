package com.devchao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;

public class Test {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test" , "root", "123");
		PreparedStatement sm = connection.prepareStatement("insert into persons values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
				+ ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		int n = 1000000;
		while(true) {
			sm.setObject(1, UUID.randomUUID().hashCode());
			sm.setString(2, new Random().nextInt(100000000) + "fsd");
			sm.setString(3, new Random().nextInt(100000000) + "rr");
			sm.setString(4, new Random().nextInt(100000000) + "fggsd");
			sm.setString(5, new Random().nextInt(100000000) + "vcfsdd");
			
			for(int i = 6; i < 50; i++) {
				sm.setInt(i, new Random().nextInt(100000000));
			}
			try{
			sm.executeUpdate();
			}catch(Exception e) {
				System.out.println("repeat");
			}
			if(n-- < 0) break;
		}
		 
	}
}
