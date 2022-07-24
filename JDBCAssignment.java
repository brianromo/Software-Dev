/************************************************************
 * Brian Banfield
 * 7/17/2022
 * MySQL Workbench. 
 ***********************************************************/

package javadb;
import java.sql.*;

public class JDBCAssignment {

	public static void main(String[] args) {
			Connection connection = null;
			
			try {
			//     ResultSet resultSet = null;
			     try {
			          Class.forName("com.mysql.cj.jdbc.Driver");
			    } catch (ClassNotFoundException e) {
			        e.printStackTrace();
			    }
			    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wordOccurences","root","softwaredevelopment");
			} catch (SQLException e) {
			    e.printStackTrace();
			}
			try {
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Word");
						    while(resultSet.next())
						    {
						     System.out.print(resultSet.getString(1) + ": ");					     
						     System.out.print(resultSet.getString("firstWord") + " ");				
						     System.out.println(resultSet.getString("lastWord"));
						    }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
