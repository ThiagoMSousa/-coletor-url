package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ColetaURL;
import model.ColetoresURL;
import to.ListaDeUrls;


/**
 * Servlet implementation class ListarUrlsController
 */
@WebServlet("/listar_urls.do")
public class ListarUrlsController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		
		String chave = request.getParameter("data[search]");
		    
		ColetoresURL coleta = new ColetoresURL();		
		ListaDeUrls lista;
		
		if(chave != null && chave.length() > 0)
		{
			lista = coleta.listarUrls(chave);
		} else {
			lista = coleta.listarUrls();
		}
		
		request.setAttribute("lista", lista);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ListarUrls.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}