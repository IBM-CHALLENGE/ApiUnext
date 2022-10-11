package br.com.unext.dao;

import java.util.ArrayList;

import br.com.unext.to.EmpresaTo;

public class EmpresaDao implements IDao<EmpresaTo>{

	@Override
	public int cadastrar(EmpresaTo model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean editar(EmpresaTo model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remover(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<EmpresaTo> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpresaTo buscarById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
