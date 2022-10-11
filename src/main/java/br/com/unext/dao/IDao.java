package br.com.unext.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.exceptions.ErroOperacaoException;

public interface IDao<Model> {

	/**
	 * Cadastra um item no banco de dados
	 * @param model Model que vai ser cadastrado
	 * @return Código do item gerado
	 */
	public int cadastrar(Model model) throws SQLException, ErroOperacaoException;
	
	/**
	 * Edita um item no banco de dados
	 * @param model Model que vai ser editado
	 * @return Booleano se a operação teve sucesso 
	 */
	public boolean editar(Model model) throws SQLException, ErroOperacaoException;
	
	/**
	 * Remove um item no banco de dados
	 * @param id Id do item a ser removido
	 * @return Booleano se a operação teve sucesso
	 */
	public boolean remover(int id) throws SQLException, ErroOperacaoException;
	
	/**
	 * Lista todos os itens de um mesmo model
	 * @return Lista com os itens
	 */
	public ArrayList<Model> listar() throws SQLException;
	
	/**
	 * Busca um item especifico de um model
	 * @param id Id do item
	 * @return Item do model
	 */
	public Model buscarById(int id) throws SQLException, ErroOperacaoException;
}
