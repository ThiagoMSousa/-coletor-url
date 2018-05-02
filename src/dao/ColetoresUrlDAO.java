package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factory.ConnectionFactory;
import to.ColetaUrlTO;
import to.ListaDeUrls;

public class ColetoresUrlDAO {
	
	public ListaDeUrls listarUrls(){
		ColetaUrlTO to;
		ArrayList<ColetaUrlTO> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, endereco FROM url";
		
		try( Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);)
		{
			try( ResultSet rs = stm.executeQuery(); )
			{
				while( rs.next() )
				{
					to = new ColetaUrlTO();
					to.setId( rs.getInt("id") );
					to.setUrl( rs.getString("endereco") );
					lista.add(to);
				}
			}
			catch( SQLException e )
			{
				e.printStackTrace();
			}
		}
		catch( SQLException e1 )
		{
			System.out.println( e1.getStackTrace() );
		}
		ListaDeUrls listaDeUrls = new ListaDeUrls();
		listaDeUrls.setUrls(lista);
		return listaDeUrls;
	}
	
	public ListaDeUrls listarUrls( String chave ){
		ColetaUrlTO to;
		ArrayList<ColetaUrlTO> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, endereco FROM url";
		
		try( Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);)
		{
			stm.setString(1, "%" + chave.toUpperCase() + "%");;
			try( ResultSet rs = stm.executeQuery(); )
			{
				while( rs.next() )
				{
					to = new ColetaUrlTO();
					to.setId( rs.getInt("id") );
					to.setUrl( rs.getString("endereco") );
					lista.add(to);
				}
			}
			catch( SQLException e )
			{
				e.printStackTrace();
			}
		}
		catch( SQLException e1 )
		{
			System.out.println( e1.getStackTrace() );
		}
		ListaDeUrls listaDeUrls = new ListaDeUrls();
		listaDeUrls.setUrls(lista);
		return listaDeUrls;
	}

}
