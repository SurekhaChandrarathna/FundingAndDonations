package model;

import model.FundsAdmin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FundsAPI")
public class FundsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	FundsAdmin fundsobj = new FundsAdmin();
	
    
    public FundsAPI() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		
		String output = fundsobj.insertFundRequests(request.getParameter("UserEmail"),
				request.getParameter("ProjectID"),
				request.getParameter("BankCardNumber"));
				response.getWriter().write(output); 
		
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
