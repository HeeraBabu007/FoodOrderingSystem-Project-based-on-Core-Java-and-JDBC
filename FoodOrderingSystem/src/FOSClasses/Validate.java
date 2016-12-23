
package FOSClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Validate {
    
       public static boolean checkUser(String userid,String pass) 
      {
      boolean st =false;
      try{

	 //loading drivers for mysql
         Class.forName("com.mysql.cj.jdbc.Driver");

 	 //creating connection with the database 
         Connection con=DriverManager.getConnection
                        ("jdbc:mysql://localhost:3306/foodorderingsystem","root","root");
         PreparedStatement ps =con.prepareStatement
                             ("select * from admin where userid=? and password=? ;");
         ps.setString(1, userid);
         ps.setString(2, pass);
         ResultSet rs =ps.executeQuery();
         st = rs.next();
        
      }catch(Exception e)
      {
          e.printStackTrace();
      }
         return st;                 
  }
       
     public static boolean checkAdmin(String userid,String pass) 
      {
      boolean st =false;
      try{

	 //loading drivers for mysql
         Class.forName("com.mysql.cj.jdbc.Driver");

 	 //creating connection with the database 
         Connection con=DriverManager.getConnection
                        ("jdbc:mysql://localhost:3306/foodorderingsystem","root","root");
         PreparedStatement ps =con.prepareStatement
                             ("select * from admin where userid=? and password=? and type='admin' ;");
         ps.setString(1, userid);
         ps.setString(2, pass);
         ResultSet rs =ps.executeQuery();
         st = rs.next();
        
      }catch(Exception e)
      {
          e.printStackTrace();
      }
         return st;                 
  }
}
