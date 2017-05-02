

import java.sql.*;
import java.util.Scanner;
import java.lang.String;

public class JDBCclass1 {
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
      while(true){
	      sql = "SELECT AvailableSeats FROM Airport_To_Airport_Capacity WHERE From_Airpoty LIKE '";
	      
	      //get values for the from airport
	      System.out.println("From Airport: ");
	      temp = input.nextLine();
	      if (temp.compareTo("/Q") == 0)
	    	  break;	  
	      sql = sql + temp + "' AND To_airpot LIKE '";
	      
	      //get values for to airport
	      System.out.println("To Airport: ");
	      temp = input.nextLine();
	      if (temp.compareTo("/Q") == 0)
	    	  break;
	      sql = sql + temp + "' AND SeatClass LIKE '";
	      
	      //get values for seat class
	      System.out.println("Seat Class: ");
	      temp = input.nextLine();
	      if (temp.compareTo("/Q") == 0)
	    	  break;
	      sql = sql + temp + "';";
	      
	      //System.out.println(sql);
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      //STEP 5: Extract data from result set
	      while(rs.next()){
	          //Retrieve by column name
	    	  String output = rs.getString("AvailableSeats");
	    	  //System.out.println("-----"+output);
	          if(output != null)
	        	  System.out.println("Available Seats: " + output);
	          else
	        	  System.out.println("No available seats or no flight found");
	      }
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