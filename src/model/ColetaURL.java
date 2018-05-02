package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.ColetaURLDAOMock;
import dao.ColetaUrlDAO;
import dao.DAO;
import to.ColetaUrlTO;

import java.util.ArrayList;

public class ColetaURL {
	
	// Atributos
	private int id;
	private String url = "";
	private ArrayList<String> listaDeURL;
	private DAO dao;
	
	
	// Construtor
	public ColetaURL()
	{
		setUrl("http://www.alertschool.com.br/");
		//setUrl("https://www.ibm.com/br/employment/br-pt");
	}
		
	public ColetaURL( String url )
	{
		setUrl(url);
	}
		
	// Modificador
	public void setUrl( String url )
	{
		this.url = url;
	}
	
	// de Acesso
	public String getUrl()
	{
		return this.url;
	}
	
	// Outros M�todos
	// M�todo que faz a captura das URL's e salva no ArrayList
	
	public String getLinks( String url )
	{
		
		return getLinks( url );
	}
	
	public ArrayList<String> getPage(String url) throws IOException
	{	
		String inputLine;
		String html = "";
        String start = "href=\"http";
        //String start = "\"http";
		//String start = "href=\"";
        String end = "\"";
        
        try
        {
        	/* Cria o objeto pagina do tipo URL */
    		URL pagina = new URL( url);
    		
    		/*  cria o Objeto do tipo URLConnection, e passa o objeto URL */
    		URLConnection con = pagina.openConnection();
    		
    		// Faz a leitura
    		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    		
        	// Faz a leitura do c�digo/p�gina html em um loop, e salva na variavel "inputLine"
        	while ((inputLine = in.readLine()) != null)
    		{      
                html += inputLine;
    		}        
        } catch (MalformedURLException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
		
		Pattern pattern = Pattern.compile(start + ".*?" + end);
        Matcher matcher = pattern.matcher(html);
        
        listaDeURL = new ArrayList<String>();
        

        while (matcher.find()) 
        {
        	//str.replace(0, str.indexOf(" ") + 1, "")
        	String link = matcher.group().substring(6, matcher.group().length()-1);
        	listaDeURL.add( link );
        	//System.out.println( matcher.group() );
        	
        }
        return listaDeURL;
	}
	
	public String verificaArrayList()
	{
		String msg = "";
		for (int i = 0; i < listaDeURL.size(); i++) 
		{
			//msg+= "Posicao %d- %s\n", i, listaDeURL.get(i);
			msg+= "\nPosicao " + i + "i: " + listaDeURL.get(i);
		}
		return msg;
	}
	
	public void realizarColeta(String url) throws Exception {
		ArrayList<String> links = getPage(url);
		for(String link : links) {
			if(!dao.exists(link)) {
				dao.saveUrl(link);
				realizarColeta(link);
			}
		}
	}
	
	public void criar()
	{
		ColetaUrlDAO dao = new ColetaUrlDAO();
		ColetaUrlTO to = getTO();
		dao.incluir(to);
		this.id = to.getId();
	}
	
	public ColetaUrlTO getTO()
	{
		ColetaUrlTO to = new ColetaUrlTO();
		to.setId(id);
		to.setUrl(url);
		return to;
	}
	
	public void atualizar()
	{
		ColetaUrlDAO dao = new ColetaUrlDAO();
		ColetaUrlTO to = getTO();
		dao.atualizar(to);
	}
	
	public void excluir()
	{
		ColetaUrlDAO dao = new ColetaUrlDAO();
		ColetaUrlTO to = new ColetaUrlTO();
		to.setId(id);
		dao.excluir(to);
	}
	
	public void carregar()
	{
		ColetaUrlDAO dao = new ColetaUrlDAO();
		ColetaUrlTO to = dao.carregar(id);
		url = to.getUrl();
	}
	
	
}
