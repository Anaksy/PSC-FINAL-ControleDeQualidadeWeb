package unibratec.controlequalidade.util;

import java.util.Calendar;
import java.util.Date;

public abstract class Funcoes {

	// Método que retorna o resultado da subtração de duas datas
	public static long subtrairDiasDataCalendar(Calendar primeiraDataCalendar, Calendar seundaDataCalendar){

		Date primeiraDataDate = primeiraDataCalendar.getTime();     
		Date segundaDataDate = seundaDataCalendar.getTime();     
		
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
