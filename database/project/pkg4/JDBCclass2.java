package database.project.pkg4;

import java.sql.*;
import java.util.Scanner;
import java.lang.String;

public class JDBCclass2 {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306/nxm4075"; //<-- replace your database name with xxx

   //  Database credentials
   static final String USER = "nxm4075";	//<-- replace your username with xxx
   static final String PASS = "xozVfb#q";	//<-- replace your mysql password with xxx
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
	  Scanner input = new Scanner(System.in);
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      //System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql, temp;
      int count;
      
      // get count for the passenger 
      sql = "SELECT * FROM Passenger;";
      ResultSet rs = stmt.executeQuery(sql);
      count = rs.last() ? rs.getRow() : 0;
      System.out.println("Count: " + count);
      
      while(true){
    	  count = count + 1;
    	  sql = "LOCK TABLES Passenger WRITE;";
    	  rs = stmt.executeQuery(sql);
	      sql = "INSERT INTO Passenger VALUES (" + Integer.toString(count) + ",'";
	      
	      //get values for the passenger name
	      System.out.println("Passenger Name: ");
	      temp = input.nextLine();
	      if (temp.compareTo("/Q") == 0)
	    	  break;	  
	      sql = sql + temp + "','";
	      
	      //get values for the passenger phone
	      System.out.println("Passenger Phone: ");
	      temp = input.nextLine();
	      if (temp.compareTo("/Q") == 0)
	    	  break;
	      sql = sql + temp + "');";
	      
	      //add values to passenger table
	      //System.out.println(sql);
	      int tempInt = stmt.executeUpdate(sql);
	      
	      //unlock tables
    	  sql = "UNLOCK TABLES;";
    	  rs = stmt.executeQuery(sql);
    	  
    	  //lock table to write
    	  sql = "LOCK TABLES Reservation WRITE;";
    	  rs = stmt.executeQuery(sql);
    	  
    	  //set up to write in reservation
    	  sql = "INSERT INTO `Reservation` VALUES (" + Integer.toString(count) + ",'";
    	  
	      //get values for flight number
	      System.out.println("Flight Number: ");
	      temp = input.nextLine();
	      if (temp.compareTo("/Q") == 0)
	    	  break;
	      sql = sql + temp + "','";
	      
	      //get values for flight date
	      System.out.println("Flight Date (EX. 2015-01-30): ");
	      temp = input.nextLine();
	      if (temp.compareTo("/Q") == 0)
	    	  break;
	      sql = sql + temp + "','";
	      
	      //get values for from airport
	      System.out.println("From Airpot: ");
	      temp = input.nextLine();
	      if (temp.compareTo("/Q") == 0)
	    	  break;
	      sql = sql + temp + "','";
	      
	      //get values for to airport
	      System.out.println("To Airpot: ");
	      temp = input.nextLine();
	      if (temp.compareTo("/Q") == 0)
	    	  break;
	      sql = sql + temp + "','";
	      
	      //get values for from airport
	      System.out.println("Seat Class: ");
	      temp = input.nextLine();
	      if (temp.compareTo("/Q") == 0)
	    	  break;
	      sql = sql + temp + "','2015-10-08',NULL);";
	      
	      //System.out.println(sql);
	      
	      //execute the query
	      tempInt = stmt.executeUpdate(sql);
	      
      }
      
      //STEP 6: Clean-up environment
      stmt.close();
      conn.close();
      input.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end FirstExample