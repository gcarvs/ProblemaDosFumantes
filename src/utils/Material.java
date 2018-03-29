package utils;

/**
 * Enum que representa os materiais necessários para construir o cigarro
 * @author gcarvs
 *
 */
public enum Material {
	PAPEL(0),
	FUMO(1),
	FOSFORO(2);
	
	private final int codigo;
	
	private Material(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Retorna um material apartir de seu codigo
	 * @param codigo - O codigo do material
	 * @return Material - o material selecionado ou null se o codigo for invalido
	 */
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
	
	/**
	 * Retorna o nome do material atual
	 * @return String - nome do material
	 */
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
