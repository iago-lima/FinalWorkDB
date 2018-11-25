package br.ufc.quixada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufc.quixada.dao.FuncionarioDAO;
import br.ufc.quixada.entity.Funcionario;

public class FuncionarioJDBCDAO implements FuncionarioDAO {

	@Override
	public void save(Funcionario funcionario){
		Connection conect = null;
		try {
			conect = ConnectionFactory.getConnection();	
			String insertsql = "insert into funcionario values (?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conect.prepareStatement(insertsql);
			ps.setString(1, funcionario.getPnome());
			ps.setString(2, funcionario.getMinicial());
			ps.setString(3, funcionario.getUnome());
			ps.setString(4, funcionario.getCpf());
			ps.setDate(5, funcionario.getDataNasc());
			ps.setString(6, funcionario.getEndereco());
			ps.setString(7, String.valueOf(funcionario.getSexo()));
			ps.setDouble(8, funcionario.getSalario());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null,"Usuário inserido com sucesso");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Funcionário não inserido");
		}finally {
			try {
				if( conect != null) {
					conect.close();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Não foi possível fechar a conexão");
			}
		}
	}
	@Override
	public void update(String cpf, Funcionario funcionario) {
		Connection conect = null;
		try {
			conect = ConnectionFactory.getConnection();	
			
			String insertsql = "update Funcionario set pnome = ?, minicial  = ?, unome = ?, datanasc = ?, sexo = ?, salario = ?, endereco = ? where cpf = ?";
			
			PreparedStatement ps = conect.prepareStatement(insertsql);
			
			ps.setString(1, funcionario.getPnome());
			ps.setString(2, funcionario.getMinicial());
			ps.setString(3, funcionario.getUnome());
			ps.setDate(4, funcionario.getDataNasc());
			ps.setString(5, String.valueOf(funcionario.getSexo()));
			ps.setDouble(6, funcionario.getSalario());
			ps.setString(7, funcionario.getEndereco());
			ps.setString(8, funcionario.getCpf());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Atualização realizada com sucesso");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível realizar a atualização");

		}finally {
			try {
				if( conect != null) {
					conect.close();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Não foi possível fechar a conexão");
			}
		}
		 
	}

	@Override
	public void delete(String cpf) {
		Connection conect = null;
		try {
			conect = ConnectionFactory.getConnection();
			String insertsql = "delete from Funcionario where cpf = ?";
			PreparedStatement ps = conect.prepareStatement(insertsql);
			ps.setString(1, cpf);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Remoção realizada com sucesso");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível deletar o funcionário");
		}finally {
			try {
				if(conect != null) {
					conect.close();
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Não foi possível fechar a conexão");

			}
		}

	}

	@Override
	public Funcionario find(String cpf) {
		Connection conect = null;
		Funcionario funcionario = new Funcionario();
		
		try {
			conect = ConnectionFactory.getConnection();	
			
			String insertsql = "select * from Funcionario where cpf = ?";
			
			PreparedStatement ps = conect.prepareStatement(insertsql);
			
			ps.setString(1, cpf);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				funcionario.setPnome(rs.getString("pnome"));
				funcionario.setMinicial(rs.getString("minicial"));
				funcionario.setUnome(rs.getString("unome"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setDataNasc(rs.getDate("datanasc"));
				funcionario.setEndereco(rs.getString("endereco"));
				funcionario.setSexo(rs.getString("sexo").charAt(0));
				funcionario.setSalario(rs.getDouble("salario"));
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Usuário não encontrado");

		}finally {
			try {
				if (conect != null) {
					conect.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return funcionario;
	}

	@Override
	public List<Funcionario> listAll() {
		List<Funcionario> funcs = new ArrayList<Funcionario>();;
		Connection conect = null;
		
		try {
			conect = ConnectionFactory.getConnection();
			String insertsql = "select * from funcionario order by pnome , minicial, unome";
			
			PreparedStatement ps = conect.prepareStatement(insertsql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Funcionario funcAux = new Funcionario();
				funcAux.setPnome(rs.getString("pnome"));
				funcAux.setMinicial(rs.getString("minicial"));
				funcAux.setUnome(rs.getString("unome"));
				funcAux.setCpf(rs.getString("cpf"));
				funcAux.setDataNasc(rs.getDate("datanasc"));
				funcAux.setEndereco(rs.getString("endereco"));
				funcAux.setSexo(rs.getString("sexo").charAt(0));
				funcAux.setSalario(rs.getDouble("salario"));
				funcs.add(funcAux);
				
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível realizar a busca");
		}finally {
			try {
				if(conect != null) {
					conect.close();
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Não foi possível fechar a conexão");
			}
		}
		return funcs;
	}

	@Override
	public List<Funcionario> findByNome(String nome) {
		List<Funcionario> funcs = new ArrayList<Funcionario>();;
		Connection conect = null;
		
		try {
			conect = ConnectionFactory.getConnection();
			String insertsql = "select * from funcionario where pnome = ? order by pnome";
			PreparedStatement ps = conect.prepareStatement(insertsql);
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Funcionario funcAux = new Funcionario();
				funcAux.setPnome(rs.getString("pnome"));
				funcAux.setMinicial(rs.getString("minicial"));
				funcAux.setUnome(rs.getString("unome"));
				funcAux.setCpf(rs.getString("cpf"));
				funcAux.setDataNasc(rs.getDate("datanasc"));
				funcAux.setEndereco(rs.getString("endereco"));
				funcAux.setSexo(rs.getString("sexo").charAt(0));
				funcAux.setSalario(rs.getDouble("salario"));
				funcs.add(funcAux);	
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível realizar a busca");
		}finally {
			try {
				if(conect != null) {
					conect.close();
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Não foi possível fechar a conexão");
			}
		}
		return funcs;
	}

	@Override
	public double totalSalario() {
		double salarioTotal = 0.0;
		Connection conect = null;
		
		try {
			conect = ConnectionFactory.getConnection();
			String insertsql = "select SUM(salario) as salarioTotal from funcionario \n";
			PreparedStatement ps = conect.prepareStatement(insertsql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				salarioTotal = rs.getDouble("salarioTotal");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível realizar a consulta");
		}finally {
			try {
				if(conect != null) {
					conect.close();
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Não foi possível fechar a conexão");
			}
		}
		
		return salarioTotal;
	}

	@Override
	public int qtdFuncionarios() {
		int numFunc = 0;
		Connection conect = null;
		
		try {
			conect = ConnectionFactory.getConnection();
			String insertsql = "SELECT COUNT(*) as numFunc FROM funcionario";
			PreparedStatement ps = conect.prepareStatement(insertsql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				numFunc = rs.getInt("numFunc");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível realizar a consulta");
		}finally {
			try {
				if(conect != null) {
					conect.close();
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Não foi possível fechar a conexão");
			}
		}
		
		return numFunc;
	}

}
