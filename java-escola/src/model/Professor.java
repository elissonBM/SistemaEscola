package model;
/**
 * Classe para armazenar os atributos e métodos para o objeto professor
 * @author Elisson Barbieri Machado
 * @since 23 de fev. de 2021
 */

import java.util.ArrayList;

public class Professor extends Funcionario{
	
	//declarando os atributos do professor 
	private int codigo;
	private ArrayList<Materia> listaMateria;
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public ArrayList<Materia> getListaMateria() {
		return listaMateria;
	}
	public void setListaMateria(ArrayList<Materia> listaMateria) {
		this.listaMateria = listaMateria;
	}
	
	
	
	
	
	

}
