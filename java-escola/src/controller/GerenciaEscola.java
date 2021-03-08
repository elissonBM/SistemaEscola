package controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import model.Aluno;
import model.Contato;
import model.Endereco;
import model.Materia;
import model.Professor;
import model.Turma;
import util.Mensagem;
import util.Rotulo;
import util.Valida;

/**
 * Classe respons�vel por controlar o sistema de gerencimento para cadastro de
 * alunos, turmas, mat�rias e professores
 * 
 * @author Diego Munhoz
 * @since 23/02/2021
 */
public class GerenciaEscola {

	// declarando as listas para armazenar os conte�dos do sistema( ALUNOS,
	// PROFESSORES )
	private ArrayList<Aluno> alunos;
	private ArrayList<Professor> professores;
	private ArrayList<Materia> materias;
	private ArrayList<Turma> turmas;

	// construtor da classe
	public GerenciaEscola() {
		alunos = new ArrayList<Aluno>();
		professores = new ArrayList<Professor>();
		materias = new ArrayList<Materia>();
		turmas = new ArrayList<Turma>();
		menuPrincipal();
	}

	// m�todo para criar um menu de op��es
	public void menuPrincipal() {

		String menu = "INFORME A OP��O DESEJADA\n" + "\n1 - CADASTRAR MAT�RIA" + "\n2 - CADASTRAR PROFESSOR"
				+ "\n3 - CADASTRAR ALUNO" + "\n4 - CADASTRAR TURMA" + "\n5 - LISTAR ALUNOS" + "\n6 - LISTAR PROFESSORES"
				+ "\n7 - LISTAR TURMAS" + "\n8 - CONSULTAR TURMA" + "\n9 - SAIR DO SISTEMA";

		while (true) {
			try {
				int opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
				processamentoPrincipal(opcao);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.opcaoInvalida, Rotulo.sistemaEscola, 0);
			}
		}

	}

	// m�todo para controlar o processamento do programa
	public void processamentoPrincipal(int opcao) {

		switch (opcao) {
		case 1: {
			cadastrarMateria();
			break;
		}
		case 2: {
			cadastrarProfessor();
			break;
		}
		case 3: {
			cadastrarAluno();
			break;
		}
		case 4: {
			cadastrarTurma();
			break;
		}
		case 5: {
			listarAlunos();
			break;
		}
		case 6: {
			listarProfessores();
			break;
		}
		case 7: {
			listarTurmas();
			break;
		}
		case 8: {
			consultarTurma();
			break;
		}
		case 9: {
			sair();
			break;
		}

		default:
			JOptionPane.showMessageDialog(null, Mensagem.opcaoInvalida, Rotulo.sistemaEscola, 0);
			break;
		}

	}

	// m�todo para cadastrar mat�ria
	public void cadastrarMateria() {
		materias.add(getMateria());
		JOptionPane.showMessageDialog(null, Mensagem.materiaCadastrada, Rotulo.cadastroMateria, 1);
	}

	// m�todo para cadastrar professor
	public void cadastrarProfessor() {
		if (materias.size() > 0) {
			professores.add(getProfessor());
			JOptionPane.showMessageDialog(null, Mensagem.professorCadastrado, Rotulo.cadastroProfessor, 1);
		} else {
			JOptionPane.showMessageDialog(null, Mensagem.erroCadastroProfessor, Rotulo.cadastroProfessor, 2);
		}

	}

	// m�todo para cadastrar aluno
	public void cadastrarAluno() {
		alunos.add(getAluno());
		JOptionPane.showMessageDialog(null, Mensagem.alunoCadastrado, Rotulo.cadastroAluno, 1);
	}

	// m�todo para cadastrar turma
	public void cadastrarTurma() {

		//verifica se existe professores e alunos cadastrados
		if (professores.size() > 0 && alunos.size() > 0) {
			turmas.add(getTurma());
			JOptionPane.showMessageDialog(null, Mensagem.turmaCadastrada, Rotulo.cadastroTurma, 1);
		} else {
			JOptionPane.showMessageDialog(null, Mensagem.erroCadastroTurma, Rotulo.cadastroTurma, 2);
		}
	}

	// m�todo para retornar um objeto do tipo Turma
	private Turma getTurma() {
		Turma turma = new Turma();

		turma.setCodigo(getCodigoTurma());
		turma.setAno(getAno());
		turma.setProfessor(getProfessorTurma());
		turma.setMateria(getMateriaTurma());
		turma.setAlunos(getAlunosTurma());
		
		return turma;
	}

	// m�todo para listar alunos cadastrados
	public void listarAlunos() {

		String mensagem = "Alunos cadastrados\n";
		boolean existe = false;

		for (Aluno aluno : alunos) {
			existe = true;
			mensagem += "\nC�digo: " + aluno.getCodigo() + " - " + aluno.getNome();
		}

		if (existe) {
			JOptionPane.showMessageDialog(null, mensagem, Rotulo.cadastroAluno, 1);
		} else {
			JOptionPane.showMessageDialog(null, Mensagem.alunosVazio, Rotulo.cadastroAluno, 2);
		}

	}

	// m�todo para listar professores cadastrados
	public void listarProfessores() {

		String mensagem = "Professores cadastrados\n";
		boolean existe = false;

		for (Professor professor : professores) {
			existe = true;
			mensagem += "\nC�digo: " + professor.getCodigo() + " - " + professor.getNome();
		}

		if (existe) {
			JOptionPane.showMessageDialog(null, mensagem, Rotulo.cadastroProfessor, 1);
		} else {
			JOptionPane.showMessageDialog(null, Mensagem.professoresVazio, Rotulo.cadastroProfessor, 2);
		}

	}

	// m�todo para listar turmas cadastrados
	public void listarTurmas() {

		String mensagem = "Turmas cadastradas\n";
		boolean existe = false;

		for (Turma turma : turmas) {
			existe = true;
			mensagem += "\nC�digo: " + turma.getCodigo() + " - " + turma.getAno() + " - "
					+ turma.getMateria().getNome();
		}

		if (existe) {
			JOptionPane.showMessageDialog(null, mensagem, Rotulo.cadastroTurma, 1);
		} else {
			JOptionPane.showMessageDialog(null, Mensagem.turmasVazio, Rotulo.cadastroTurma, 2);
		}

	}

	// m�todo para consultar uma turma espec�fica
	public void consultarTurma() {
		Turma turma = getConsultarTurma();
		String mensagem = "Detalhes da turma\n";
		mensagem += "\nC�digo: " + turma.getCodigo();
		mensagem += "\nAno: " + turma.getAno();
		mensagem += "\nProfessor: " + turma.getProfessor().getNome();
		mensagem += "\nMat�ria: " + turma.getMateria().getNome() ;
		mensagem += "\nAlunos matriculados: ";
	
		for (Aluno aluno: turma.getAlunos() ){
			mensagem += "\nC�digo: " + aluno.getCodigo() + " - " + aluno.getNome();
		}
		
		JOptionPane.showMessageDialog(null, mensagem, Rotulo.ConsultaTurma, 1);
	}

	// m�todo para sair do sistema
	public void sair() {
		int opcao = JOptionPane.showConfirmDialog(null, "Encerrar o sistema?", "Aten��o", JOptionPane.YES_OPTION,
				JOptionPane.CANCEL_OPTION);

		if (opcao == JOptionPane.YES_OPTION) {
			System.exit(0);
		}

	}

	// m�todo para retornar os atributos (valores) do aluno
	public Aluno getAluno() {
		Aluno aluno = new Aluno();
		Endereco endereco = new Endereco();
		Contato contato = new Contato();

		aluno.setCodigo(getCodigoAluno());
		aluno.setNome(getNomeAluno());
		aluno.setCpf(getCpfAluno());
		aluno.setRg(getRgAluno());

		endereco.setLogradouro(getLogradouro());
		endereco.setNome(getNomeLogradouro());
		endereco.setNumero(getNumero());
		endereco.setComplemento(getComplemento());
		endereco.setBairro(getBairro());
		endereco.setCidade(getCidade());
		endereco.setEstado(getEstado());
		endereco.setCep(getCep());

		contato.setCelular(getCelular());
		contato.setEmail(getEmail());

		aluno.setEndereco(endereco);
		aluno.setContato(contato);

		return aluno;
	}

	// m�todo para retornar os atributos (valores) do professor
	private Professor getProfessor() {// inicio do metodo
		Professor professor = new Professor();
		Endereco endereco = new Endereco();
		Contato contato = new Contato();

		professor.setCodigo(getCodigoProfessor());
		professor.setNome(getNomeProfessor());
		professor.setCpf(getCpfProfessor());
		professor.setRg(getRgProfessor());

		endereco.setLogradouro(getLogradouro());
		endereco.setNome(getNomeLogradouro());
		endereco.setNumero(getNumero());
		endereco.setComplemento(getComplemento());
		endereco.setBairro(getBairro());
		endereco.setCidade(getCidade());
		endereco.setEstado(getEstado());
		endereco.setCep(getCep());

		contato.setCelular(getCelular());
		contato.setEmail(getEmail());

		professor.setEndereco(endereco);
		professor.setContato(contato);
		professor.setListaMateria(getMaterias());

		professor.setSalario(getSalario());

		return professor;
	}// fim do metodo

	// m�todo para retornar um objeto Materia
	private Materia getMateria() {
		Materia materia = new Materia();
		materia.setCodigo(getCodigoMateria());
		materia.setNome(getNomeMateria());
		return materia;
	}

	//metodo para retornar o codigo da materia
	private int getCodigoMateria() {
		boolean execute = true;
		int codigo = 0;
		//executa at� inserir um valor valido
		while (execute) {
			try {
				codigo = Integer.parseInt(JOptionPane.showInputDialog(Mensagem.informeCodigo));

				if (Valida.isIntZero(codigo)) {
					JOptionPane.showMessageDialog(null, Mensagem.codigoVazio, Rotulo.cadastroMateria, 0);
					execute = true;
				} else {
					execute = false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroMateria, 0);
				execute = true;
			}
		}
		return codigo;
	}// fim do m�todo

	//metodo para retornar o nome da materia
	private String getNomeMateria() {
		boolean execute = true;
		String nome = "";
		while (execute) {
			nome = JOptionPane.showInputDialog(Mensagem.informeNome);
			if (Valida.isEmptyOrNull(nome)) {
				JOptionPane.showMessageDialog(null, Mensagem.nomeVazio, Rotulo.cadastroMateria, 0);
				execute = true;
			} else {
				execute = false;
			}
		}
		return nome;
	}// fim do m�todo

	//metodo para retornar o codigo do aluno
	private int getCodigoAluno() {
		boolean execute = true;
		int codigo = 0;
		while (execute) {
			try {
				codigo = Integer.parseInt(JOptionPane.showInputDialog(Mensagem.informeCodigo));

				if (Valida.isIntZero(codigo)) {
					JOptionPane.showMessageDialog(null, Mensagem.codigoVazio, Rotulo.cadastroAluno, 0);
					execute = true;
				} else {
					execute = false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroAluno, 0);
				execute = true;
			}
		}
		return codigo;
	}// fim do m�todo

	//metodo para retornar o nome da materia
	private String getNomeAluno() {
		boolean execute = true;
		String nome = "";
		while (execute) {
			nome = JOptionPane.showInputDialog(Mensagem.informeNome);
			if (Valida.isEmptyOrNull(nome)) {
				JOptionPane.showMessageDialog(null, Mensagem.nomeVazio, Rotulo.cadastroAluno, 0);
				execute = true;
			} else {
				execute = false;
			}
		}
		return nome;
	}// fim do m�todo

	//metodo para retornar o cpf do aluno
	private String getCpfAluno() {
		boolean execute = true;
		String cpf = "";
		while (execute) {
			cpf = JOptionPane.showInputDialog(Mensagem.informeCpf);
			if (Valida.isEmptyOrNull(cpf)) {
				JOptionPane.showMessageDialog(null, Mensagem.cpfVazio, Rotulo.cadastroAluno, 0);
				execute = true;
			} else {
				execute = false;
			}
		}
		return cpf;
	}// fim do m�todo

	//metodo para retornar o rg do aluno
	private String getRgAluno() {
		boolean execute = true;
		String rg = "";
		while (execute) {
			rg = JOptionPane.showInputDialog(Mensagem.informeRg);
			if (Valida.isEmptyOrNull(rg)) {
				JOptionPane.showMessageDialog(null, Mensagem.rgVazio, Rotulo.cadastroAluno, 0);
				execute = true;
			} else {
				execute = false;
			}
		}
		return rg;
	}// fim do m�todo

	//metodo para retornar o logradouro para todos os tipos de objetos que tem endere�o
	private String getLogradouro() {
		boolean execute = true;
		String logradouro = "";
		while (execute) {
			logradouro = JOptionPane.showInputDialog(Mensagem.informeLogradouro);
			if (Valida.isEmptyOrNull(logradouro)) {
				JOptionPane.showMessageDialog(null, Mensagem.logradouroVazio, Rotulo.cadastroEndereco, 0);
				execute = true;
			} else {
				execute = false;
			}
		}
		return logradouro;
	}// fim do m�todo
	
	//metodo para retornar o nome do logradouro para todos os tipos de objetos que tem endere�o
	private String getNomeLogradouro() {
		boolean execute = true;
		String nomeLogradouro = "";
		while (execute) {
			nomeLogradouro = JOptionPane.showInputDialog(Mensagem.informeNomeLogradouro);
			if (Valida.isEmptyOrNull(nomeLogradouro)) {
				JOptionPane.showMessageDialog(null, Mensagem.nomeLogradouroVazio, Rotulo.cadastroEndereco, 0);
				execute = true;
			} else {
				execute = false;
			}
		}
		return nomeLogradouro;
	}// fim do m�todo
	
	//metodo para retornar o numero para todos os tipos de objetos que tem endere�o
	private int getNumero() {
		boolean execute = true;
		int numero = 0;
		while (execute) {
			try {
				numero = Integer.parseInt(JOptionPane.showInputDialog(Mensagem.informeNumero));

				if (Valida.isIntZero(numero)) {
					JOptionPane.showMessageDialog(null, Mensagem.numeroVazio, Rotulo.cadastroEndereco, 0);
					execute = true;
				} else {
					execute = false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.numeroInvalido, Rotulo.cadastroEndereco, 0);
				execute = true;
			}

		}
		return numero;
	}// fim do m�todo

	//metodo para retornar o complemento para todos os tipos de objetos que tem endere�o
	private String getComplemento() {
		String complemento = "";
		complemento = JOptionPane.showInputDialog(Mensagem.informeComplemento);
		return complemento;
	}// fim do m�todo

	//metodo para retornar o bairro para todos os tipos de objetos que tem endere�o
	private String getBairro() {
		boolean execute = true;
		String bairro = "";
		while (execute) {
			bairro = JOptionPane.showInputDialog(Mensagem.informeBairro);
			if (Valida.isEmptyOrNull(bairro)) {
				JOptionPane.showMessageDialog(null, Mensagem.bairroVazio, Rotulo.cadastroEndereco, 0);
				execute = true;
			} else {
				execute = false;
			}
		}
		return bairro;
	}// fim do m�todo

	//metodo para retornar a cidade para todos os tipos de objetos que tem endere�o
	private String getCidade() {
		boolean execute = true;
		String cidade = "";
		while (execute) {
			cidade = JOptionPane.showInputDialog(Mensagem.informeCidade);
			if (Valida.isEmptyOrNull(cidade)) {
				JOptionPane.showMessageDialog(null, Mensagem.cidadeVazio, Rotulo.cadastroEndereco, 0);
				execute = true;
			} else {
				execute = false;
			}
		}
		return cidade;
	}// fim do m�todo

	//metodo para retornar o estado para todos os tipos de objetos que tem endere�o
	private String getEstado() {
		boolean execute = true;
		String estado = "";
		while (execute) {
			estado = JOptionPane.showInputDialog(Mensagem.informeEstado);
			if (Valida.isEmptyOrNull(estado)) {
				JOptionPane.showMessageDialog(null, Mensagem.estadoVazio, Rotulo.cadastroEndereco, 0);
				execute = true;
			} else {
				execute = false;
			}
		}
		return estado;
	}// fim do m�todo

	//metodo para retornar o CEP para todos os tipos de objetos que tem endere�o
	private String getCep() {
		boolean execute = true;
		String cep = "";
		while (execute) {
			cep = JOptionPane.showInputDialog(Mensagem.informeCep);
			if (Valida.isEmptyOrNull(cep)) {
				JOptionPane.showMessageDialog(null, Mensagem.cepVazio, Rotulo.cadastroEndereco, 0);
				execute = true;
			} else {
				execute = false;
			}
		}
		return cep;
	}// fim do m�todo

	//metodo para retornar o celular para todos os tipos de objetos que tem contato
	private String getCelular() {
		boolean execute = true;
		String celular = "";
		while (execute) {
			celular = JOptionPane.showInputDialog(Mensagem.informeCelular);
			if (Valida.isEmptyOrNull(celular)) {
				JOptionPane.showMessageDialog(null, Mensagem.celularVazio, Rotulo.cadastroContato, 0);
				execute = true;
			} else {
				execute = false;
			}
		}
		return celular;
	}// fim do m�todo

	//metodo para retornar o email para todos os tipos de objetos que tem contato
	private String getEmail() {
		boolean execute = true;
		String email = "";
		while (execute) {
			email = JOptionPane.showInputDialog(Mensagem.informeEmail);
			if (Valida.isEmptyOrNull(email)) {
				JOptionPane.showMessageDialog(null, Mensagem.emailVazio, Rotulo.cadastroContato, 0);
				execute = true;
			} else {
				execute = false;
			}
		}
		return email;
	}// fim do m�todo

	private int getCodigoProfessor() {
		boolean execute = true;
		int codigo = 0;
		while (execute) {
			try {
				codigo = Integer.parseInt(JOptionPane.showInputDialog(Mensagem.informeCodigo));

				if (Valida.isIntZero(codigo)) {
					JOptionPane.showMessageDialog(null, Mensagem.codigoVazio, Rotulo.cadastroProfessor, 0);
					execute = true;
				} else {
					execute = false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroProfessor, 0);
				execute = true;
			}
		}
		return codigo;
	}// fim do m�todo

	private String getNomeProfessor() {
		boolean execute = true;
		String nome = "";
		while (execute) {
			nome = JOptionPane.showInputDialog(Mensagem.informeNome);
			if (Valida.isEmptyOrNull(nome)) {
				JOptionPane.showMessageDialog(null, Mensagem.nomeVazio, Rotulo.cadastroProfessor, 0);
				execute = true;
			} else {
				execute = false;
			}
		}
		return nome;
	}// fim do m�todo

	private String getCpfProfessor() {
		boolean execute = true;
		String cpf = "";
		while (execute) {
			cpf = JOptionPane.showInputDialog(Mensagem.informeCpf);
			if (Valida.isEmptyOrNull(cpf)) {
				JOptionPane.showMessageDialog(null, Mensagem.cpfVazio, Rotulo.cadastroProfessor, 0);
				execute = true;
			} else {
				execute = false;
			}
		}
		return cpf;
	}// fim do m�todo

	private String getRgProfessor() {
		boolean execute = true;
		String rg = "";
		while (execute) {
			rg = JOptionPane.showInputDialog(Mensagem.informeRg);
			if (Valida.isEmptyOrNull(rg)) {
				JOptionPane.showMessageDialog(null, Mensagem.rgVazio, Rotulo.cadastroProfessor, 0);
				execute = true;
			} else {
				execute = false;
			}
		}
		return rg;
	}// fim do m�todo

	//exibe as materias cadastradas no cadastro do professor
	private ArrayList<Materia> getMaterias() {
		ArrayList<Materia> materias = new ArrayList<Materia>();
		String mensagem = "Mat�rias cadastradas\n";

		for (Materia materia : this.materias) {
			mensagem += "\nC�digo: " + materia.getCodigo() + " - " + materia.getNome();
		}

		boolean aux = true;
		mensagem += "\n" + Mensagem.informeCodigo;
		while (aux) {
			try {
				int codigo = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
				materias.add(this.materias.get(codigo - 1));

				int opcao = JOptionPane.showConfirmDialog(null, "Deseja cadastrar outra mat�ria?", "Aten��o",
						JOptionPane.YES_OPTION, JOptionPane.CANCEL_OPTION);

				if (opcao == JOptionPane.YES_OPTION) {
					aux = true;
				} else {
					aux = false;
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroProfessor, 0);
			}

		}
		return materias;
	}

	private double getSalario() {
		boolean execute = true;
		double salario = 0;
		while (execute) {
			try {
				salario = Double.parseDouble(JOptionPane.showInputDialog(Mensagem.informeSalario));

				if (Valida.isDoubleZero(salario)) {
					JOptionPane.showMessageDialog(null, Mensagem.salarioVazio, Rotulo.cadastroProfessor, 0);
					execute = true;
				} else {
					execute = false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.salarioInvalido, Rotulo.cadastroProfessor, 0);
				execute = true;
			}
		}
		return salario;
	}// fim do m�todo

	private int getCodigoTurma() {
		boolean execute = true;
		int codigo = 0;
		while (execute) {
			try {
				codigo = Integer.parseInt(JOptionPane.showInputDialog(Mensagem.informeCodigo));

				if (Valida.isIntZero(codigo)) {
					JOptionPane.showMessageDialog(null, Mensagem.codigoVazio, Rotulo.cadastroTurma, 0);
					execute = true;
				} else {
					execute = false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroTurma, 0);
				execute = true;
			}
		}
		return codigo;
	}// fim do m�todo

	//m�todo para devolver o ano
	private int getAno() {
		boolean execute = true;
		int ano = 0;
		while (execute) {
			try {
				ano = Integer.parseInt(JOptionPane.showInputDialog(Mensagem.informeAno));

				if (Valida.isIntZero(ano)) {
					JOptionPane.showMessageDialog(null, Mensagem.anoVazio, Rotulo.cadastroTurma, 0);
					execute = true;
				} else {
					execute = false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.anoInvalido, Rotulo.cadastroTurma, 0);
				execute = true;
			}
		}
		return ano;
	}// fim do m�todo

	
	private Professor getProfessorTurma() {
		Professor retorno = new Professor();
		String mensagem = "Professores cadastrados\n";

		for (Professor professor : professores) {
			mensagem += "\nC�digo: " + professor.getCodigo() + " - " + professor.getNome();
		}

		mensagem += "\n" + Mensagem.informeCodigo;
		try {
			int codigo = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
			retorno = professores.get(codigo -1);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroTurma, 0);
		}

		return retorno;
	}

	private Materia getMateriaTurma() {
		Materia retorno = new Materia();
		String mensagem = "Mat�rias cadastradas\n";

		for (Materia materia : materias) {
			mensagem += "\nC�digo: " + materia.getCodigo() + " - " + materia.getNome();
		}

		mensagem += "\n" + Mensagem.informeCodigo;
		try {
			int codigo = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
			retorno = materias.get(codigo -1);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroTurma, 0);
		}

		return retorno;
	}
	
	private ArrayList<Aluno> getAlunosTurma() {
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		String mensagem = "Alunos cadastrados\n";

		for (Aluno aluno : this.alunos) {
			mensagem += "\nC�digo: " + aluno.getCodigo() + " - " + aluno.getNome();
		}

		boolean aux = true;
		mensagem += "\n" + Mensagem.informeCodigo;
		while (aux) {
			try {
				int codigo = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
				alunos.add(this.alunos.get(codigo - 1));

				int opcao = JOptionPane.showConfirmDialog(null, "Deseja cadastrar outro aluno?", "Aten��o",
						JOptionPane.YES_OPTION, JOptionPane.CANCEL_OPTION);

				if (opcao == JOptionPane.YES_OPTION) {
					aux = true;
				} else {
					aux = false;
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroTurma, 0);
			}

		}
		return alunos;
	}
	
	private Turma getConsultarTurma() {
		Turma retorno = new Turma();
		String mensagem = "Turmas cadastradas\n";

		for (Turma turma : turmas) {
			mensagem += "\nC�digo: " + turma.getCodigo() + " - " + turma.getAno() + " - " + turma.getMateria();
		}

		mensagem += "\n" + Mensagem.informeCodigo;
		try {
			int codigo = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
			retorno = turmas.get(codigo -1);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Mensagem.codigoInvalido, Rotulo.cadastroTurma, 0);
		}

		return retorno;
	}
	
	

}// fim da classe
