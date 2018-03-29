package fumantes;

import java.util.Random;

import utils.Material;

/**
 * Agente responsavel por produzir os materiais.
 * 
 * @author gcarvs
 *
 */
public class Agente implements Runnable{
	private Mesa mesa;
	private int tempoProducao;
	
	public Agente() { 
		this.tempoProducao = 5000;
	}
	
	public Agente(Mesa mesa, int tempoProducao) {
		this.mesa = mesa;
		this.tempoProducao = tempoProducao * 1000;
	}
	
	public void setTempoProducao(int tempo) {
		this.tempoProducao = tempo * 1000;
	}
	
	@Override
	/**
	 * Este método é executado quando a thread inicia. Ele produz os materiais e disponibiliza na mesa.
	 */
	public void run() {
		while(true) {
			try {
				//Produz dois materiais no tempo determinado
				Thread.sleep(tempoProducao);
				Material material1 = sortearMaterial();
				Material material2 = sortearMaterial();
				
				//Previne que o agente sorteie dois materiais iguais
				while(material1 == material2) {
					material2 = sortearMaterial();
				}
				//Envia os materiais para a mesa disponibilizar aos fumantes
				this.mesa.distribuirMaterial(material1, material2);
			} catch (InterruptedException e) {
				System.out.println("Falha na producao!");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Sorteia um material.
	 * 
	 * @return Material - O material sorteado
	 */
	private Material sortearMaterial() {
		Random random = new Random();
		int codigo = random.nextInt(3);
		
		return Material.getMaterial(codigo);
	}
}
