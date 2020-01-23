import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@SuppressWarnings("serial")
@WebServlet("/Loginvalidation")
public class Loginvalidation extends HttpServlet 
{

	String user;
	String pwd;
	String url;
	String fqpn;
	
	

	@SuppressWarnings("resource")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	
	{
	
	
	String fs=req.getParameter("fs");
	HttpSession hs= req.getSession();
	
	if (fs.equalsIgnoreCase("2") )	
	{
		Connection con= null;
		PreparedStatement pstmt= null;
		String qry= "insert into application.user values(?,?,?,?,?)";
		PrintWriter out= resp.getWriter();
		
		
			String user = req.getParameter("un");
			String password = req.getParameter("pwd");
			String email = req.getParameter("mail");
			String gender = req.getParameter("gender");
			String contact = req.getParameter("contact");
			

	        
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306? user=root & password=dinga");
			pstmt= con.prepareStatement(qry);
	
			pstmt.setString(1,user);
			pstmt.setString(2,password);
			pstmt.setString(3,email);
			pstmt.setString(4,gender);
			pstmt.setString(5,contact);
			

			
			int i=pstmt.executeUpdate();
			
			if (i>0)
			{
				
				RequestDispatcher rd=req.getRequestDispatcher("Login.html");
				rd.include(req, resp);
				
				out.println("<html>");
	            out.println("<head>");
	            out.println("</head>");
	            out.println("<body>");
	            out.println("<h2>");
	            out.println("Registered successfully");
		       
		        out.println("</h2>");
		      
	            out.println("</body>");
	            out.println("</html>");
				
				
				
			}
			
			else 
			{
				RequestDispatcher rd=req.getRequestDispatcher("Register.html");
				rd.include(req, resp);
				out.println("Unable to register user");

				out.println("<html>");
	            out.println("<head>");
	            out.println("</head>");
	            out.println("<body>");
	            out.println("<h2>");
	            out.println("Unable to register user");
		       
		        out.println("</h2>");
		      
	            out.println("</body>");
	            out.println("</html>");
				
				
			}
	
		}
		catch (Exception e)
		{
			e.printStackTrace();
			RequestDispatcher rd=req.getRequestDispatcher("Register.html");
			rd.include(req, resp);

			out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>");
            out.println("Unable to register user");
	       
	        out.println("</h2>");
	      
            out.println("</body>");
            out.println("</html>");
			
		}
			
	} 
	
	if (fs.equalsIgnoreCase("3") )
	{
		PrintWriter out = resp.getWriter();
		String username =req.getParameter("un");
		String password =req.getParameter("pwd");
		String qry= "Select * from application.user where user='"+username+"' and password='"+password+"' ";
		
		Connection con=null;
		Statement stmt = null;
		ResultSet rs= null;
		
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306? user=root & password=dinga");
			stmt= con.createStatement();
			rs=stmt.executeQuery(qry);
			
			if (rs.next())
			{
				
				RequestDispatcher rd=req.getRequestDispatcher("order.html");
				rd.include(req, resp);
				out.println("<html>");
	            out.println("<head>");
	            out.println("</head>");
	            out.println("<body>");
	            out.println("<h2>");
	            out.println("Welcome to Ezone, Enjoy shopping ");
		       
		        out.println("</h2>");
		      
	            out.println("</body>");
	            out.println("</html>");
				
			
			}
			
			else 
			{
				RequestDispatcher rd=req.getRequestDispatcher("Login.html");
				rd.include(req, resp);
				out.println("<html>");
	            out.println("<head>");
	            out.println("</head>");
	            out.println("<body>");
	            out.println("<h2>");
	            out.println("Invalid credentials");
		       
		        out.println("</h2>");
		      
	            out.println("</body>");
	            out.println("</html>");
				
			}
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		
	}
	
	if (fs.equalsIgnoreCase("4") )	
	{
		
		
		 String product = req.getParameter("product");
		 String product2 = req.getParameter("product2");
		    String quantity = req.getParameter("qty");
		    String quantity2 = req.getParameter("qty2");
		
			PrintWriter out = resp.getWriter(); 
			
			hs.setAttribute("product", product);
			hs.setAttribute("product2", product2);
			hs.setAttribute("quantity", quantity);
			hs.setAttribute("quantity2", quantity2);

		        Connection con=null;
				Statement stmt = null;
				String qry= "select price from application.products where Product_name ='"+product+"'";
				String qry2= "select Object_price from application.addins where Object_name ='"+product2+"'";
				ResultSet rs= null;
			
	   
				try
				{
			
					Class.forName("com.mysql.cj.jdbc.Driver");
					con= DriverManager.getConnection("jdbc:mysql://localhost:3306? user=root & password=dinga");
					stmt= con.createStatement();
					rs=stmt.executeQuery(qry);
					
					
					
					while (rs.next())
					{
						int price  = rs.getInt("price");
			            
			            int g= price*Integer.parseInt(quantity); 
			           
			            
			          
						
						rs=stmt.executeQuery(qry2);
						
						while (rs.next())	
						{
							 int price2= rs.getInt("Object_price");
								int v= price2*Integer.parseInt(quantity2);
								
								
							RequestDispatcher ra=req.getRequestDispatcher("price.html");
							ra.include(req, resp);
							
							
			            out.println("<html>");
			            out.println("<head>");
			            out.println("<title>Price</title>");
			            out.println("</head>");
			            out.println("<body>");
			            out.println("<h1>");
			            out.println("<label> Your order details</label> ");
			            out.println("</h1>");
			            out.println("<h2>");
			            out.println("The product selected by you is: " + product ); 
			            out.println("<br>"); 
				        out.println(" Quantity : " + quantity );
				        out.println("<br>");  
				        out.println(" Price per piece: " + price );
				        out.println("<br>");
				        out.println(" Total Price of "+ product + " is: "  + g );
				        out.println("<br>");
				        out.println("<br>");	
						out.println("You also have selected: " + product2 ); 
				        out.println("<br>"); 
					    out.println(" Quantity : " + quantity2 );
					    out.println("<br>");
					    out.println(" Price per piece: " + price2 );
					    out.println("<br>");
					    out.println(" Total Price of" + product2 + "is:" + v );
					    out.println("<br>");
					    out.println("<br>");
					    out.println(" Amount to be paid for " + product + " and " +product2 + " is: " +(g+v)  );
					    out.println("<br>");
					    out.println("<br>");
					    /*out.println("\n <button onclick=\"window.location.href='confirmation.html'\">confirm</button>");*/ 
					   
				        out.println("<h2>");
				        out.println("</body>");
				        out.println("</html>");
				        
				        
					    
					}
					}
			
				}
	            
				
				catch (Exception e)
				{
					e.printStackTrace();	
				}
	}
	
	
	if ( fs.equalsIgnoreCase("5"))	
	{
		
		
		String product=(String)hs.getAttribute("product");
		String product2=(String)hs.getAttribute("product2");
		String quantity=(String)hs.getAttribute("quantity");
		String quantity2=(String)hs.getAttribute("quantity2");
		 PrintWriter out = resp.getWriter(); 
			
	   
				try
				{				
							RequestDispatcher ra=req.getRequestDispatcher("confirmation.html");
							ra.include(req, resp);
							
							
				            out.println("<html>");
				            out.println("<head>");
				            out.println("<title>Price</title>");
				            out.println("</head>");
				            out.println("<body>");
				            out.println("<h2>");
				            out.println("<label> Your order details</label> ");
				            out.println("</h2>");
				            out.println("<h2>");
				            out.println("your order has been placed for: " + product); 
				            out.println("<br>"); 
					        out.println(" of Quantity : " + quantity );
					        out.println("<br>");
					        out.println("<br>");
					        out.println("<br>");	
							out.println("Also your second order has been placed for:" + product2 ); 
					        out.println("<br>"); 
						    out.println(" of Quantity : " + quantity2 );
						    out.println("<br>");
						    out.println("<br>");
						    out.println("<br>");
						    out.println("<br>");
						    out.println("\n <button onclick=\"window.location.href='Thankyou.html'\">Leave</button>"); 
					        out.println("</body>");
					        out.println("</html>");
				}
	            
				
				
				catch (Exception e)
				{
					e.printStackTrace();
				
					
				}
	}
 }
	
}
 
