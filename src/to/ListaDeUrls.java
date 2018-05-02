package to;

import java.util.ArrayList;

public class ListaDeUrls 
{
	// Atríbutos
	ArrayList<ColetaUrlTO> urls;
	
	public ListaDeUrls()
	{
		this.urls = new ArrayList<ColetaUrlTO>();
	}
	
	public ArrayList<ColetaUrlTO> getUrls(){
		return urls;
	}
	
	public void setUrls( ArrayList<ColetaUrlTO> urls )
	{
		this.urls = urls;
	}

}
