package model;

public class TesteMain {

	public static void main(String[] args) 
	{
		
		boolean mockado = true; //Se esse parâmetro for TRUE, ao invés de realmente salvar
								// no banco, irá apenas "fingir", salvando só em memória.
								// Criei essa opção para poder testar na minha máquina, já que
								// não tenho acesso ao banco de dados.
								//Se marcar com FALSE, irá gravar no banco normalmente.
		
		ColetaURL coleta = new ColetaURL();
		try {
			coleta.realizarColeta("http://www.chevrolet.com.br/");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
