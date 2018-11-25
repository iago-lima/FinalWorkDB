package br.ufc.quixada.entity;

import java.sql.Date;

public class Funcionario {
	private String cpf, pnome, minicial, unome, endereco ; 
	private Date dataNasc; 
	private char sexo;
	private double salario;
	
	public Funcionario(String cpf, String pnome, String minicial, String unome, String endereco, Date dataNasc,
			char sexo, double salario) {
		super();
		this.cpf = cpf;
		this.pnome = pnome;
		this.minicial = minicial;
		this.unome = unome;
		this.endereco = endereco;
		this.dataNasc = dataNasc;
		this.sexo = sexo;
		this.salario = salario;
	}
	public Funcionario() {

	}
	public String toString() {
		return "Funcionario [cpf= " + cpf + ", pnome=" + pnome + ", minicial=" + minicial + ", unome=" + unome
				+ ", endereco=" + endereco + ", dataNasc=" + dataNasc + ", sexo=" + sexo + ", salario=" + salario + "]";
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getPnome() {
		return pnome;
	}
	public void setPnome(String pnome) {
		this.pnome = pnome;
	}
	public String getMinicial() {
		return minicial;
	}
	public void setMinicial(String minicial) {
		this.minicial = minicial;
	}
	public String getUnome() {
		return unome;
	}
	public void setUnome(String unome) {
		this.unome = unome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}

}
