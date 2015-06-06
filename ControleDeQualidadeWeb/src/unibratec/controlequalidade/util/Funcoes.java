package unibratec.controlequalidade.util;

import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class Funcoes {
	
	//Metodo que retorna um boolean se uma data entre outras definidas pelos parametros.
	public static boolean procurarEntreDatas (Date date, Date dateStart, Date dateEnd) {
		if (date != null && dateStart != null && dateEnd != null) {
			if (date.after(dateStart) && date.before(dateEnd)) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}

	// Método que retorna o resultado da subtração de duas datas
	public static long subtrairDiasDataCalendar(Calendar menorData, Calendar maiorData){

		Date primeiraDataDate = menorData.getTime();     
		Date segundaDataDate = maiorData.getTime();     
		
		long resultado = segundaDataDate.getTime() - primeiraDataDate.getTime();     
		
		System.out.println("Diferenca em dias: " + (resultado/1000/60/60/24));     

		return resultado/1000/60/60/24;
	}
	
	//Método que gera o nome do lote.
	public static String geraNomeLote(){
		String nomeLote = null;
		Calendar c = Calendar.getInstance();
		nomeLote = "LT" + c.get(Calendar.YEAR) +  getNomeMesDeInt(c.get(Calendar.MONTH)) + c.get(Calendar.DAY_OF_MONTH) + 
				"-" + "T" +  String.valueOf(c.getTimeInMillis()).substring(7);
		return nomeLote; 		
	}

	//Método que "traduz" o número do mês na abreviação do mesmo.
	private static String getNomeMesDeInt(int mes){
		String[] meses = {"JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEC"};
		return meses[mes];
	}
		
}
