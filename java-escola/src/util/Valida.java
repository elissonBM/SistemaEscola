package util;

/**
 * Classe responsavel por validar os dados inputados pelo usuario
 * @author Elisson Barbieri Machado
 * @since 23 de fev. de 2021
 */
public class Valida {

	//verificando se o valor digitado nullo ou vazio
	public static boolean isEmptyOrNull(String string) {
		return (string == null || string.trim().equals(""));
	}

	//verificando se o valor int informado é zero
	public static boolean isIntZero(int args) {
		return(args == 0);
	}
	
	//verificando se o valor double informado é zero
	public static boolean isDoubleZero(double args) {
		return(args == 0);
	}
}
