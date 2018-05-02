package model;

import dao.ColetaUrlDAO;
import dao.ColetoresUrlDAO;
import to.ListaDeUrls;

public class ColetoresURL {
	
	public ListaDeUrls listarUrls()
	{
		ListaDeUrls lista;
		ColetoresUrlDAO dao = new ColetoresUrlDAO();
		lista = dao.listarUrls();
		return lista;
	}
	
	public ListaDeUrls listarUrls( String chave )
	{
		ColetoresUrlDAO dao = new ColetoresUrlDAO();
		ListaDeUrls lista = dao.listarUrls( chave );
		return lista;
	}

}
