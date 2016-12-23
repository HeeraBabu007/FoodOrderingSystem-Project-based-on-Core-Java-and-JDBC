package foodorderingsystem;
import FOSClasses.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class FoodOrderingSystem {
    public static int choice=0 ;
    public static String userid;
    public static String password ;
    public static void newCustRegister() throws ClassNotFoundException, SQLException
    { 
      NewRegistration nr=new NewRegistration();
      Scanner s1=new Scanner(System.in);
      System.out.println("*****New Customer Registration*****");
      System.out.print("Enter Contact Number: ");
      nr.contactNo=s1.nextInt();
      System.out.print("Enter Name: ");
      nr.custName=s1.next();
      System.out.print("Enter E-Mail ID: ");
      nr.emailId=s1.next();
      System.out.println();
      String insertstm="insert into customers(custname,contactno,emailid) values('"+nr.custName+"',"+nr.contactNo+",'"+nr.emailId+"');" ;
      String dispstm="select *from customers where contactno="+nr.contactNo+" ;";
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorderingsystem","root","root");
      Statement stm=con.createStatement();
      stm.executeUpdate(insertstm);
      Statement disp=con.createStatement();
      disp.executeQuery(dispstm);
      ResultSet rs = disp.getResultSet();
      while(rs.next()){
          nr.custId=rs.getInt("custid");
      }
      System.out.println("Your Customer Id is: "+nr.custId);
    }
    public static void custSearch()throws ClassNotFoundException, SQLException
    { Scanner s2=new Scanner(System.in);
      System.out.println("**********CUSTOMER SEARCH**********");
      System.out.println("1. Search Using Customer Id");
      System.out.println("2. Search Using Contact Number");
      int schoice=0 ;
      System.out.print("Enter Your Choice: ");
      schoice=s2.nextInt();
      System.out.println();
      switch(schoice)
      { case 1: searchById();break ;
        case 2: searchByCtc(); break ;
        default: System.out.println("Wrong Choice Entered!!! Please Re-Run The System.");
      }
    }
    public static void placeOrder()throws ClassNotFoundException, SQLException
    {   Scanner s3=new Scanner(System.in);
        Orders od=new Orders();
        System.out.println("**********PLACE FOOD ORDER***********");
        System.out.print("Enter Customer Id: ");
        od.custId=s3.nextInt();
        System.out.print("Enter Item Id: ");
        od.itemId=s3.nextInt();
        System.out.print("Enter Item Quantity: ");
        od.quantity=s3.nextInt();
        System.out.println();
        double billamount ;
      billamount=getBillAmount(od.itemId,od.quantity);
      String insertstm="insert into orders(custid,itemid,quantity,billamount) values("+od.custId+","+od.itemId+","+od.quantity+","+billamount+");" ;
      String dispstm="select *from orders order by postdatetime;";
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorderingsystem","root","root");
      Statement stm=con.createStatement();
      stm.executeUpdate(insertstm);
      Statement disp=con.createStatement();
      disp.executeQuery(dispstm);
      ResultSet rs = disp.getResultSet();
      while(rs.next()){
          od.orderId=rs.getInt("orderid");
      }
      System.out.println("Your Order Id is: "+od.orderId);
      System.out.println("Your Bill Amount is: "+billamount);
    }
    public static void deleteCust()throws ClassNotFoundException, SQLException
    {
            Scanner s=new Scanner(System.in);
        System.out.println("----This Task Requires Admin Login----");
        System.out.print("Enter Admin Id: ");
        userid=s.next();
        System.out.println();
        System.out.print("Enter Admin Password: ");
        password=s.next();
        System.out.println();
        if(Validate.checkAdmin(userid, password))
        {
            NewRegistration nr=new NewRegistration();
      Scanner s6=new Scanner(System.in);
      System.out.println("*****Delete A Customer Using Customer Id*****");
      System.out.print("Enter Customer Id: ");
      nr.searchid=s6.nextInt();
      System.out.println();
      String dispstm="delete from customers where custid="+nr.searchid+" ;";
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorderingsystem","root","root");
      Statement disp=con.createStatement();
      disp.executeUpdate(dispstm);
      System.out.println("Customer Deleted Successfully");
        }
        else
        { System.out.println("Invalid Credentials!! You Cannot Perform This Task.");
        }
      
    }
    public static void viewFoodMenu()throws ClassNotFoundException, SQLException
    { FoodMenu fd=new FoodMenu();
      System.out.println("***************FOOD MENU***************");
      String dispstm="select *from foodmenu;" ;
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorderingsystem","root","root");
      Statement disp=con.createStatement();
      disp.executeQuery(dispstm);
      ResultSet rs = disp.getResultSet();
      System.out.println("Item No."+"\t"+"Item Name"+"\t"+"Item Price"+"\t"+"Item Type");
      while(rs.next()){
          fd.itemId=rs.getInt("itemid");
          fd.itemName=rs.getString("itemname");
          fd.itemPrice=rs.getDouble("itemprice");
          fd.type=rs.getString("type");
          System.out.println(fd.itemId+"\t"+fd.itemName+"\t"+fd.itemPrice+"\t"+fd.type);
      }
        
    }
    public static void searchById()throws ClassNotFoundException, SQLException
    {
      NewRegistration nr=new NewRegistration();
      Scanner s4=new Scanner(System.in);
      System.out.println("*****Search Using Customer Id*****");
      System.out.print("Enter Customer Id: ");
      nr.searchid=s4.nextInt();
      System.out.println();
      String dispstm="select *from customers where custid="+nr.searchid+" ;";
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorderingsystem","root","root");
      Statement disp=con.createStatement();
      disp.executeQuery(dispstm);
      ResultSet rs = disp.getResultSet();
      System.out.println("Customer Id"+"\t"+"Name"+"\t       "+"Contact No"+"\t"+"Email Id");
      while(rs.next()){
          nr.custId=rs.getInt("custid");
          nr.custName=rs.getString("custname");
          nr.contactNo=rs.getInt("contactno");
          nr.emailId=rs.getString("emailid");
       System.out.println(nr.custId+"\t\t"+nr.custName+"\t"+nr.contactNo+"\t       "+nr.emailId);

      }
    }
    public static void searchByCtc()throws ClassNotFoundException, SQLException
    {
        NewRegistration nr=new NewRegistration();
      Scanner s5=new Scanner(System.in);
      System.out.println("*****Search Using Customer's Contact Number*****");
      System.out.print("Enter Contact Number: ");
      nr.searchctc=s5.nextInt();
      System.out.println();
      String dispstm="select *from customers where contactno="+nr.searchctc+" ;";
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorderingsystem","root","root");
      Statement disp=con.createStatement();
      disp.executeQuery(dispstm);
      ResultSet rs = disp.getResultSet();
      System.out.println("Customer Id"+"\t"+"Name"+"\t       "+"Contact No"+"\t"+"Email Id");
      while(rs.next()){
          nr.custId=rs.getInt("custid");
          nr.custName=rs.getString("custname");
          nr.contactNo=rs.getInt("contactno");
          nr.emailId=rs.getString("emailid");
       System.out.println(nr.custId+"\t\t"+nr.custName+"\t"+nr.contactNo+"\t       "+nr.emailId);

      }
    }
    public static void changeOrderStatus()throws ClassNotFoundException, SQLException
    {   Scanner s7=new Scanner(System.in);
        Orders od=new Orders();
        System.out.println("**********CHANGE ORDER STATUS**********");
        System.out.println("To set order status to COMPLETED, enter the orderid below:");
        System.out.print("Enter Order Id: ");
        od.orderId=s7.nextInt();
        System.out.println();
      String updatestm="update orders set status='Completed' where orderid="+od.orderId+";" ;
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorderingsystem","root","root");
      Statement stm=con.createStatement();
      stm.executeUpdate(updatestm);
      System.out.println("Order Status Changed Successfully");
      
    }
    public static double getBillAmount(int itemid,int qty)throws ClassNotFoundException, SQLException
    { double bill=0.0 ;
      FoodMenu fd=new FoodMenu();
      String dispstm="select *from foodmenu where itemid="+itemid+";" ;
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorderingsystem","root","root");
      Statement disp=con.createStatement();
      disp.executeQuery(dispstm);
      ResultSet rs = disp.getResultSet();
      
      while(rs.next()){
          fd.itemPrice=rs.getDouble("itemprice");
      }
      bill=fd.itemPrice*qty ;
      return bill;
    }
    public static void addItems()throws ClassNotFoundException, SQLException
    { Scanner s=new Scanner(System.in);
      System.out.println("----This Task Requires Admin Login----");
      System.out.print("Enter Admin Id: ");
        userid=s.next();
        System.out.println();
        System.out.print("Enter Admin Password: ");
        password=s.next();
        System.out.println();
      if(Validate.checkAdmin(userid, password)){
      FoodMenu fd=new FoodMenu();
      Scanner s8=new Scanner(System.in);
      System.out.println("*****Add Items To Food Menu*****");
      System.out.print("Enter Item Name: ");
      fd.itemName=s8.next();
      System.out.println();
      System.out.print("Enter Item Price: ") ;
      fd.itemPrice=s8.nextDouble();
      System.out.println();
      System.out.print("Enter Item Type(Veg or Non-Veg): ");
      fd.type=s8.next();
      System.out.println();
      String insertstm="insert into foodmenu(itemname,itemprice,type) values('"+fd.itemName+"',"+fd.itemPrice+",'"+fd.type+"');" ;
      
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorderingsystem","root","root");
      Statement stm=con.createStatement();
      stm.executeUpdate(insertstm);
      System.out.println("Food Item Added To List Successfully");
      }
    else
    { System.out.println("Invalid Credentials!! You Cannot Perform This Task.");
    }
    }
    public static void viewOrders()throws ClassNotFoundException,SQLException
    {
        Orders od=new Orders();
        FoodMenu fd=new FoodMenu();
        NewRegistration nr=new NewRegistration();
      System.out.println("*************************************************ORDERS******************************************");
      String dispstm="select *from orders;" ;
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorderingsystem","root","root");
      Statement disp=con.createStatement();
      disp.executeQuery(dispstm);
      ResultSet rs = disp.getResultSet();
      System.out.println("Order Id"+"\t"+"Customer Id"+"\t"+"Item Id"+"\t"
               +"\t"+"Quantity"+"\t"+"Status"+"\t\t"+"TotalBillAmount"+"\t\t"+"Order Date&Time");
      while(rs.next()){
          od.orderId=rs.getInt("orderid");
          nr.custId=rs.getInt("custid");
          fd.itemId=rs.getInt("itemid");
          od.quantity=rs.getInt("quantity");
          od.status=rs.getString("status");
          od.billamount=rs.getDouble("billamount");
          od.orderDT=rs.getString("postdatetime");
          System.out.println(od.orderId+"\t\t"+nr.custId+"\t\t"+fd.itemId+"\t\t"+od.quantity+"\t\t"+od.status+
                  "\t\t"+od.billamount+"\t\t"+od.orderDT);
      }
    }
    public static void viewCustomers() throws ClassNotFoundException, SQLException
    {
      NewRegistration nr=new NewRegistration();
      System.out.println("****************OUR CUSTOMERS***************");
      String dispstm="select *from customers;";
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorderingsystem","root","root");
      Statement disp=con.createStatement();
      disp.executeQuery(dispstm);
      ResultSet rs = disp.getResultSet();
      System.out.println("CustomerID"+"\t"+"Name"+"\t"+"ContactNo"+"\t"+"EmailId");
      while(rs.next()){
          nr.custId=rs.getInt("custid");
          nr.custName=rs.getString("custname");
          nr.contactNo=rs.getInt("contactno");
          nr.emailId=rs.getString("emailid");
          System.out.println(nr.custId+"\t"+nr.custName+"\t"+nr.contactNo+"\t"+nr.emailId);
      }
      
    }
    public static void systemMenu() throws ClassNotFoundException, SQLException
    {   
        for(int x=0;x>=0;x++){
        
        Scanner s=new Scanner(System.in);
        System.out.println("##############SYSTEM MENU##############");
        System.out.println("1. NEW CUSTOMER REGISTRATION");
        System.out.println("2. SEARCH FOR A CUSTOMER");
        System.out.println("3. PLACE NEW ORDER");
        System.out.println("4. DELETE A CUSTOMER (FOR ADMIN ONLY)");
        System.out.println("5. VIEW FOOD MENU");
        System.out.println("6. CHANGE ORDER STATUS");
        System.out.println("7. ADD ITEMS TO FOOD MENU (FOR ADMIN ONLY)");
        System.out.println("8. VIEW ORDERS");
        System.out.println("9. VIEW CUSTOMERS");
        System.out.println("10. ADD NEW USERS (FOR ADMIN ONLY)");
        System.out.println("11. DELETE ITEMS FROM FOOD MENU (FOR ADMIN ONLY)");
        System.out.print("Enter Your Choice: ");
        choice=s.nextInt();
        System.out.println();
        switch(choice)
        { case 1:newCustRegister(); break ;
          case 2:custSearch(); break ;
          case 3:placeOrder(); break ;
          case 4:deleteCust(); break ; //admin task
          case 5:viewFoodMenu(); break;
          case 6:changeOrderStatus(); break;
          case 7:addItems(); break; //admin task
          case 8:viewOrders(); break;
          case 9:viewCustomers(); break;
          case 10: addUsers(); break;  //admin task
          case 11: deleteItems(); break; //admin task
          default: System.out.println("Wrong Choice Entered!!! Please Re-run the System.");
        }
        System.out.println("------------------End Of This Task------------------");
        System.out.print("Do you want to go to System Menu ?(y/n) : ");
        char ch ;
        ch=s.next().charAt(0);
        System.out.println();
        if((ch=='n')||(ch=='N')) 
        {break;
        }
        
        }
    }
    public static void addUsers() throws ClassNotFoundException,SQLException 
    {    Admin ad=new Admin();
         Scanner s=new Scanner(System.in);
      System.out.println("----This Task Requires Admin Login----");
      System.out.print("Enter Admin Id: ");
        userid=s.next();
        System.out.println();
        System.out.print("Enter Admin Password: ");
        password=s.next();
        System.out.println();
      if(Validate.checkAdmin(userid, password))
      { 
      Scanner s8=new Scanner(System.in);
      System.out.println("*****Add Users To List*****");
      System.out.print("Enter User Id: ");
      ad.userId=s8.next();
      System.out.println();
      System.out.print("Enter User Name: ") ;
      ad.name=s8.next();
      System.out.println();
      System.out.print("Enter User Password: ");
      ad.password=s8.next();
      System.out.println();
      System.out.print("Enter User Contact Number: ");
      ad.contact=s8.nextInt();
      System.out.println();
      System.out.print("Do you want to register this user as ADMIN ? (y/n) : ");
      char ans ;
      ans=s8.next().charAt(0);
      System.out.println();
      if(ans=='y')
      { ad.type="admin" ;
      }
      else
      {
          ad.type="user";
      }
      String insertstm="insert into admin values('"+ad.userId+"','"+ad.password+"','"+ad.name+"',"+ad.contact+",'"+ad.type+"');" ;
      
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorderingsystem","root","root");
      Statement stm=con.createStatement();
      stm.executeUpdate(insertstm);
      System.out.println("User Added To List Successfully");
      }
      else
      { System.out.println("Invalid Credentials!! You Cannot Perform This Task.");
      } 
    }
    public static void deleteItems() throws ClassNotFoundException,SQLException 
    {
          Scanner s=new Scanner(System.in);
      System.out.println("----This Task Requires Admin Login----");
      System.out.print("Enter Admin Id: ");
        userid=s.next();
        System.out.println();
        System.out.print("Enter Admin Password: ");
        password=s.next();
        System.out.println();
      if(Validate.checkAdmin(userid, password))
      {
      FoodMenu fd=new FoodMenu();
      Scanner s8=new Scanner(System.in);
      System.out.println("*****Delete Items From Food Menu*****");
      System.out.print("Enter Item Id: ");
      fd.itemId=s8.nextInt();
      System.out.println();
      String deletestm="delete from foodmenu where itemid="+fd.itemId+";" ;
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorderingsystem","root","root");
      Statement stm=con.createStatement();
      stm.executeUpdate(deletestm);
      System.out.println("Food Item Deleted Successfully");
      }
      else
      { System.out.println("Invalid Credentials!! You Cannot Perform This Task.");
      }
        
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        Scanner s=new Scanner(System.in);
        System.out.println("**********Welcome To CHETNA'S Food Court***********");
        System.out.print("Enter Your User Id: ");
        userid=s.next();
        System.out.println();
        System.out.print("Enter Your Password: ");
        password=s.next();
        System.out.println();
        if(Validate.checkUser(userid,password))
        { systemMenu();
        }
        else
        { System.out.println("Invalid Credentials!! Please Re-Start and enter valid credentials.");
        }
        System.out.println("Creator: Chetna Negi");
        
        
      
    }
    
}
