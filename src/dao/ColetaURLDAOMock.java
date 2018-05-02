package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ColetaURLDAOMock implements DAO{
	
	private ArrayList<String> lista = new ArrayList<>();

	public void saveUrl(String url){
		System.out.println("URL salva: "+url);
		lista.add(url);
	}

	public boolean exists(String url) throws Exception 
	{
		if(lista.contains(url)) {
			System.out.println("-----> Já existente: "+url);
		}
		return lista.contains(url);
	}
}
