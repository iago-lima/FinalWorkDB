package br.ufc.quixada;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import br.ufc.quixada.dao.FuncionarioDAO;
import br.ufc.quixada.entity.Funcionario;

public class Main {

	public static void main(String[] args) throws ParseException {
		FuncionarioDAO baseFuncionarios = new FuncionarioJDBCDAO();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		
		String menu = "Escolha uma opção: \n 1 - Inserir funcionário \n "
				+ "2 - Atualizar funcionário \n "
				+ "3 - Buscar funcionário por CPF \n "
				+ "4 - Remover funcionário por CPF \n "
				+ "5 - Listar todos os funcionários \n "
				+ "6 - Listar todos os funcionários por nome\n "
				+ "7 - Total salarial pago pela empresa \n "
				+ "8 - Número total de funcionários \n "
				+ "9 - Sair";
		
		int opcao = 0; 
		while(true) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
			Funcionario func;
			
			if(opcao == 1) {
				func = new Funcionario();
				Funcionario funcAux = new Funcionario();
				
				func.setCpf(JOptionPane.showInputDialog("Digite o CPF do funcionário"));
				
				if(func.getCpf().length() == 0) {
					JOptionPane.showMessageDialog(null, "CPF não pode ser vazio");
					break;
				}
				funcAux = baseFuncionarios.find(func.getCpf());
				
				if(funcAux.getCpf() != null) {
					JOptionPane.showMessageDialog(null, "Funcionário já cadastrado");
					break;
				}
				
				func.setPnome(JOptionPane.showInputDialog("Digite o primeiro nome do funcionário"));
				func.setMinicial(JOptionPane.showInputDialog("Digite a inicial do segundo nome do funcionário"));
				func.setUnome(JOptionPane.showInputDialog("Digite o último nome do funcionário"));
								
				String sdata = JOptionPane.showInputDialog("Digite a data de nascimento do funcionário");
				java.util.Date date = sdf1.parse(sdata);
				java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
				func.setDataNasc(sqlDate);
				
				func.setEndereco(JOptionPane.showInputDialog("Digite o endereço do funcionário"));
				func.setSexo((JOptionPane.showInputDialog("Digite o sexo do funcionário").charAt(0)));
				func.setSalario(Double.parseDouble(JOptionPane.showInputDialog("Digite o salário")));
				
				baseFuncionarios.save(func);
				
			}else if (opcao == 2) {
				func = new Funcionario();
				
				String cpf = JOptionPane.showInputDialog("Digite o CPF do funcionário a ser atualizado");
				
				if(cpf.length() == 0) {
					JOptionPane.showMessageDialog(null, "CPF não pode ser vazio");
					break;
				}
				
				func = baseFuncionarios.find(cpf);
				
				if(func.getCpf() == null) {
					JOptionPane.showMessageDialog(null, "Usuário não encontrado");
				
				}else {
					func.setPnome(JOptionPane.showInputDialog("Digite o primeiro nome do funcionário", func.getPnome()));
					func.setMinicial(JOptionPane.showInputDialog("Digite a inicial do segundo nome do funcionário", func.getMinicial()));
					func.setUnome(JOptionPane.showInputDialog("Digite o último nome do funcionário", func.getUnome()));
					
					String sdata = JOptionPane.showInputDialog("Digite a data de nascimento do funcionário", func.getDataNasc());
					java.util.Date date = sdf1.parse(sdata);
					java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
					
					func.setDataNasc(sqlDate);
					
					func.setEndereco(JOptionPane.showInputDialog("Digite o endereço do funcionário", func.getEndereco()));
					func.setSexo((JOptionPane.showInputDialog("Digite o sexo do funcionário", func.getSexo()).charAt(0)));
					func.setSalario(Double.parseDouble(JOptionPane.showInputDialog("Digite o salário", func.getSalario())));
					
					baseFuncionarios.update(cpf, func);
					//JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
				}
			
			}else if (opcao == 3) {
				func = new Funcionario();
				String cpf = JOptionPane.showInputDialog("Digite o CPF do funcionário a ser buscado");
				
				if(cpf.length() == 0) {
					JOptionPane.showMessageDialog(null, "CPF não pode ser vazio");
					break;
				}
				
				func = baseFuncionarios.find(cpf);
				if(func.getCpf() == null) {
					JOptionPane.showMessageDialog(null, "Funcionário não encontrado");
				}else {	
					JOptionPane.showMessageDialog(null, func.toString());
				}
				
			}else if(opcao == 4) {
				Funcionario funcAux = new Funcionario();
				String cpf = JOptionPane.showInputDialog("Digite o CPF do funcionário a ser deletado");
				
				if(cpf.length() == 0) {
					JOptionPane.showMessageDialog(null, "CPF não pode ser vazio");
					break;
				}
				funcAux = baseFuncionarios.find(cpf);
				if(funcAux.getCpf() == null) {
					JOptionPane.showMessageDialog(null, "Usuário não encontrado");
				}else {
					baseFuncionarios.delete(cpf);
				}
				
			}else if(opcao == 5) {
				StringBuilder listagem = new StringBuilder();
				
				for(Funcionario funcAux : baseFuncionarios.listAll()) {
					listagem.append(funcAux).append("\n");
				}
				JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum funcionário encontrado" : listagem);
			}else if(opcao == 6) {
				String nome = JOptionPane.showInputDialog("Digite o nome a ser buscado"); 
				StringBuilder listagem = new StringBuilder();
				
				for(Funcionario funcAux : baseFuncionarios.findByNome(nome)) {
					listagem.append(funcAux).append("\n");
				}
				JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum funcionário encontrado" : listagem);
			}else if (opcao == 7) {
				JOptionPane.showMessageDialog(null, baseFuncionarios.totalSalario());
			}else if (opcao == 8) {
				JOptionPane.showMessageDialog(null, baseFuncionarios.qtdFuncionarios());
			}
			else if (opcao == 9) {
				System.exit(0);
			}
			
			
			
			else {
				JOptionPane.showMessageDialog(null, "Opção inválida, tente novamente");
			}
		}
	}

}
