package utils;

public enum Material {
	PAPEL(0),
	FUMO(1),
	FOSFORO(2);
	
	private final int codigo;
	
	private Material(int codigo) {
		this.codigo = codigo;
	}
	
	public static Material getMaterial(int codigo) {
		switch(codigo) {
			case 0:
				return PAPEL;
			case 1:
				return FUMO;
			case 2:
				return FOSFORO;
			default:
				return null;
		}
	}
	
	public String getNome() {
		switch(codigo) {
		case 0:
			return "Papel";
		case 1:
			return "Fumo";
		case 2:
			return "Fosforo";
		default:
			return null;
	}
	}
}
