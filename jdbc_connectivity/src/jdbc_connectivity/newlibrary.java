package jdbc_connectivity;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;


public class newlibrary {
	private static Connection c = null;
	private static Statement stmt = null;
	
	
	
private static void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\hp840\\sqlite\\simple");
//		System.out.println("database opened.....!");
		}

    	public static void main(String []args)throws Exception {
/////////////////////////////////////////////////////////////////////////
    	Scanner sc = new Scanner(System.in);
    	date d1 = new date();
//        String s1 =d1.getdate();
//    	String s2 =d1.updatemonth(1);
//    	System.out.println(s1);
//    	System.out.println(s2);
//      table creation block
//	    try {
//		getConnection();
//			
//		String sql="alter table issued2 add RETURN_STATUS TEXT;";
//		stmt = c.createStatement();
//		stmt.executeUpdate(sql);
//		}catch(Exception e) {
//    		 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//	            System.exit(0);
//	            }
//	    System.out.println("Table emp1 created");
    		
////////////////////////////////////////////////////////////////////////////////
//   insertion block
//    	try {
//    		getConnection();
//    		String sql1 = "insert into emp5(NAME)values(?);";
//    		PreparedStatement stmt1 = c.prepareStatement(sql1);
//    		stmt1.setString(1, "josh");
// //         setString(2, "mark");
//    		stmt1.executeUpdate();
//    		stmt1.close();
//    		
//    		c.close();
//    	}catch(Exception e) {
//    		 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//	            System.exit(0);    	}
//    		System.out.println("data inserted");	
//////////////////////////////////////////////////////////////////////////////////////
//   issue block
    	System.out.println("1.for issue");
    	System.out.println("2.for return");
    	int n = sc.nextInt();
    	
    	if(n==1) {
    	try {
    		getConnection();
    		System.out.println("enter book id to issue");
    		int idb = sc.nextInt();
    		System.out.println("enter emp id");
    		int ide =sc.nextInt();
    		System.out.println("for how many month you want to issue book....?");
    		int m = sc.nextInt();
    		String sql1 = "select NAME from book5 where id ="+ idb+"; ";
    		Statement stmt = c.createStatement();
    		ResultSet r =stmt.executeQuery(sql1);
    		String sbook = r.getString("NAME");
    		String scd = d1.getdate();
    		String srd = d1.updatemonth(m);
    		System.out.println(scd);
    		System.out.println(srd);
    		Boolean isReturned = false;
    		
    		String sql = "insert into issued2(EMP_ID,BOOK_ID,NAME,ISSUE_DATE,RETURN_DATE,RETURN_STATUS)values(?,?,?,?,?,?)";
    		PreparedStatement pstmt = c.prepareStatement(sql);
    		pstmt.setInt(1, ide);
    		pstmt.setInt(2, idb);
    		pstmt.setString(3, sbook);
    		pstmt.setString(4, scd);
    		pstmt.setString(5, srd);
    		pstmt.setBoolean(6, isReturned);
    		pstmt.executeUpdate();
    		
    	     }catch(Exception e) {
    		 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0); 
    	}System.out.println("book issued");
    	}else if(n==2) {
    		try {
    			getConnection();
    			System.out.println("enter current date in dd-MM-yyyy");
    			String cd=sc.nextLine();
    	//		String inputDate = "2010-01-04 01:32:27 UTC";
    			String sss = modifyDateLayout(cd);
    			System.out.println(sss);
    			
//    			SimpleDateFormat format=new SimpleDateFormat("dd-mm-yyyy");
//    			Date ddddd=format.parse(cd);
//    			String dateString=format.format(ddddd);
//    			System.out.println("entered date:- "+dateString );
    			






 
    			
    		}catch(Exception e) {
    		 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0); 
    	}
    	}
	} 
    	private static String modifyDateLayout(String inputDate) throws ParseException{
    	    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").parse(inputDate);
    	    return new SimpleDateFormat("dd-MM-yyyy").format(date);
    	}
}
class date{
	LocalDate d = LocalDate.now();
	Date d1 = new Date();
		public  String getdate() {
			
			SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");

			return (ft.format(d1));
		}
		public String updatemonth(int x) {
			SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
			d1.setMonth(d1.getMonth()+x);
			return (ft.format(d1));
		}
}
