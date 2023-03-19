package br.com.jls.conexao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * The Class FabricaConexao.
 */
public class FabricaConexao {
	
	/**
	 * Gets the conexao.
	 *
	 * @return the conexao
	 */
	public static Connection getConexao() {
		try {
			Properties prop = getProperties();
			Class.forName(prop.getProperty("banco.driver"));
			final String url = prop.getProperty("banco.url");
			final String usuario = prop.getProperty("banco.usuario");
			final String senha = prop.getProperty("banco.senha");
			return DriverManager.getConnection(url, usuario, senha);
		} catch (SQLException | IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static Properties getProperties() throws IOException {
		Properties prop = new Properties();
		String caminho = "/conexao.properties";
		prop.load(FabricaConexao.class.getResourceAsStream(caminho));
		return prop;
	}	
	
	/**
	 * Teste conexao.
	 */
	public static void testeConexao() {
		try {
		Connection conexao = getConexao();		
		if (conexao != null) {
			System.out.println("Conexão obtida com sucesso!");
			conexao.close();
		}		
		} catch (Exception e){
			System.out.println(e);
		}		
	}
}

