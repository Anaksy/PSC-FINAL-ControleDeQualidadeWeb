package unibratec.controlequalidade.entidades;

public enum StatusUsuarioEnum {
	
	ATIVO(1), INATIVO(0);
	
	private final int valor; 
	
	StatusUsuarioEnum(int valorEnum){
		this.valor = valorEnum; 
	}
	
	public int getValue(){
		return valor;
	}
}