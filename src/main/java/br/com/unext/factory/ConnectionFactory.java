package br.com.unext.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/***
 * F�brica de conex�es
 * 
 * @author Gustavo
 *
 */
public class ConnectionFactory {

	/***
	 * Obt�m uma conex�o com o banco de dados
	 * @return Connection Conex�o com o banco de dados
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		//Registrar o driver do banco a ser usado
		Class.forName("oracle.jdbc.driver.OracleDriver");

		//Obter a conex�o com o banco de dados
		return DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "rm93090", "170801");
	}
}
