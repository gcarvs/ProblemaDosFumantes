package fumantes;

import utils.EstadoFumante;
import utils.Material;

public class Fumante implements Runnable {
	
	private String nome;
	private EstadoFumante estado;
	private Mesa mesa;
	private Material materialIniciail;
	private final int tempoFumando;
	
	/**
	 * 
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
	
	public Material getMaterialInicial() {
		return this.materialIniciail;
	}
	

	@Override
	public void run() {
		while(true) {
			try {
				mesa.fumar(this);
				Thread.sleep(this.tempoFumando);
				mesa.terminarDeFumar(this);
			} catch (InterruptedException e) {
				System.out.println("Erro ao fumar!");
				e.printStackTrace();
			}
		}
	}
}
