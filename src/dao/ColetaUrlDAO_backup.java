package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ColetaURL;
import to.ColetaUrlTO;
import to.ListaDeUrls;
import factory.ConnectionFactory;

public class ColetaUrlDAO_backup extends ConnectionFactory implements DAO
{	
	public void save( ArrayList<ColetaURL> listaDeUrl ) throws Exception 
	{
		Connection conn = getConnection();
		PreparedStatement stmt = null;		
		
		ArrayList lista = listaDeUrl;
		
		for (int i = 0; i < listaDeUrl.size(); i++) {
			try 
			{			
				//texto += "\n" + (i+1) + "�" + (String) lista.get(i);
				stmt = conn.prepareStatement("INSERT INTO url (endereco) VALUES(?)");
				String endereco = (String)lista.get(i);
				stmt.setString(1, endereco);
				stmt.execute();	
			}	
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				stmt.close();
			}
		}
	}

	@Override
	public void saveUrl(String url) throws Exception {
		Connection conn = getConnection();
		PreparedStatement stmt = null;	
		try {			
			//texto += "\n" + (i+1) + "�" + (String) lista.get(i);
			stmt = conn.prepareStatement("INSERT INTO url (endereco) VALUES(?)");
			stmt.setString(1, url);
			stmt.execute();	
			System.out.println("url salva: "+url);
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			stmt.close();
		}
	
	}

	@Override
	public boolean exists(String url) throws Exception 
	{
		Connection conn = getConnection();
		PreparedStatement stmt = null;		
		boolean existeRegistro = false;
		try{			
			//texto += "\n" + (i+1) + "�" + (String) lista.get(i);
			stmt = conn.prepareStatement("select * from url where endereco = ?");
			stmt.setString(1, url);
			ResultSet rs = stmt.executeQuery();
			existeRegistro =  rs.next();
			if(existeRegistro) {
				System.out.println("-----url já salva: "+url);
			}
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			stmt.close();
		}
		
		return existeRegistro;
	}
	




}
