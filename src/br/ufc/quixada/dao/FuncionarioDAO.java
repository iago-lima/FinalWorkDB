package br.ufc.quixada.dao;

import java.util.List;

import br.ufc.quixada.entity.Funcionario;

public interface FuncionarioDAO {
	public void save(Funcionario funcionario);
	public void update(String cpf, Funcionario funcionario);
	public void delete(String cpf);
	public Funcionario find(String cpf);
	public List<Funcionario> listAll();
	public List<Funcionario> findByNome(String nome);
	public double totalSalario();
	public int qtdFuncionarios();
}
