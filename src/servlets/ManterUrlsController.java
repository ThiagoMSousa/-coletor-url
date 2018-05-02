package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ColetaURL;
import to.ColetaUrlTO;
import to.ListaDeUrls;

/**
 * Servlet implementation class ManterUrlsController
 */
@WebServlet("/ManterUrl.do")
public class ManterUrlsController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		String pAcao 	  = request.getParameter("acao");
		String pId   	  = request.getParameter("id");
		String pEndereco  = request.getParameter("endereco");
		
		ColetaURL coleta = new ColetaURL(pEndereco);
		RequestDispatcher view = null;
		
		if( pAcao.equals("Criar"))
		{	
			//coleta.getUrl();
			System.out.println( coleta.getPage(pEndereco) );
			
			coleta.criar();
			ArrayList<ColetaUrlTO> lista = new ArrayList<>();
			lista.add(coleta.getTO());
			
			ListaDeUrls listaDeUrls = new ListaDeUrls();
			listaDeUrls.setUrls(lista);
			request.setAttribute("lista", listaDeUrls);
			view = request.getRequestDispatcher("ListarUrls.jsp");
			
		}
		else if( pAcao.equals("Excluir"))
		{
			coleta.excluir();
			view = request.getRequestDispatcher("ListarUrls.jsp");
		}
		else if (pAcao.equals("Alterar"))
		{
			coleta.atualizar();
			request.setAttribute("url", coleta.getTO());
			view = request.getRequestDispatcher("VisualizarUrl.jsp");
		}
		else if (pAcao.equals("Visualizar"))
		{
			coleta.carregar();
			request.setAttribute("url", coleta.getTO());
			view = request.getRequestDispatcher("VisualizarUrl.jsp");
		}
		else if (pAcao.equals("Editar"))
		{
			coleta.carregar();
			request.setAttribute("url", coleta.getTO());
			view = request.getRequestDispatcher("AlterarUrl.jsp");
		}
		view.forward(request, response);
	}
}
