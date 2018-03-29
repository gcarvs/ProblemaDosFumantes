package fumantes;

import java.util.ArrayList;
import java.util.List;

import utils.EstadoFumante;
import utils.Material;

/**
 * Fumante que consome os materiais.
 * @author gcarvs
 *
 */
public class Fumante implements Runnable {
	
	private String nome;
	private EstadoFumante estado;
	private Mesa mesa;
	private Material materialIniciail;
	private List<Material> materiaisNecessarios;
	private final int tempoFumando;
	
	/**
	 * @param nome - O nome do fumante
	 * @param mesa - A mesa que o fumante está participando
	 * @param material - O material inicial que o fumante possui
	 * @param tempoFumando - Quanto tempo ele leva fumando
	 */
	public Fumante(String nome, Mesa mesa, Material material, int tempoFumando) {
		this.nome = nome;
		this.estado = EstadoFumante.AGUARDANDO;
		this.mesa = mesa;
		this.materialIniciail = material;
		this.tempoFumando = tempoFumando;
		
		this.definirMateriaisNecessarion();
	}
	
	/**
	 * @param nome - O nome do fumante
	 * @param material - O material inicial que o fumante possui
	 * @param tempoFumando - Quanto tempo ele leva fumando
	 */
	public Fumante(String nome, Material material, int tempoFumando) {
		this.nome = nome;
		this.estado = EstadoFumante.AGUARDANDO;
		this.materialIniciail = material;
		this.tempoFumando = tempoFumando;
		
		this.definirMateriaisNecessarion();
	}
	
	/**
	 * Define quais materiais este fumante precisa para completar o cigarro.
	 */
	private void definirMateriaisNecessarion() {
		this.materiaisNecessarios = new ArrayList<Material>();
		
		if(this.getMaterialInicial() == Material.FOSFORO) {
			materiaisNecessarios.add(Material.FUMO);
			materiaisNecessarios.add(Material.PAPEL);
		}
		else if(this.getMaterialInicial() == Material.FUMO) {
			materiaisNecessarios.add(Material.FOSFORO);
			materiaisNecessarios.add(Material.PAPEL);
		}
		else if(this.getMaterialInicial() == Material.PAPEL) {
			materiaisNecessarios.add(Material.FUMO);
			materiaisNecessarios.add(Material.FOSFORO);
		}
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public EstadoFumante getEstado() {
		return this.estado;
	}
	
	public void setEstado(EstadoFumante estado) {
		this.estado = estado;
	}
	
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	
	public Material getMaterialInicial() {
		return this.materialIniciail;
	}
	
	public List<Material> getMateriaisNecessarios(){
		return this.materiaisNecessarios;
	}

	@Override
	/**
	 * Este metodo e executado quando a thread inicia. O fumante tenta fumar utilizando os materiais da mesa.
	 * Se ele consegue, ele espera o seu tempo de fumo e notifica o termino, senao ele continua tentando.
	 */
	public void run() {
		while(true) {
			try {
				//Tenta fumar
				mesa.fumar(this);
				
				//Se está fumando, aguarda o tempo necessario e notifica o termino do fumo.
				if(this.getEstado() == EstadoFumante.FUMANDO) {
					Thread.sleep(this.tempoFumando);
					mesa.terminarDeFumar(this);
				}
			} catch (InterruptedException e) {
				System.out.println("Erro ao fumar!");
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public String toString() {
		return this.getNome() + " comecou com " + this.getMaterialInicial().getNome() 
				+ " e leva " + this.tempoFumando/1000 + " segundos fumando";
	}
}
