package fumantes;

import java.util.Random;

import utils.Material;

public class Agente implements Runnable{
	private Mesa mesa;
	private static final int TEMPO_PRODUCAO = 5000;
	
	public Agente() { }
	
	public Agente(Mesa mesa) {
		this.mesa = mesa;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(TEMPO_PRODUCAO);
				Material material1 = sortearMaterial();
				Material material2 = sortearMaterial();
				
				//Previne que o agente sorteie dois materiais iguais
				while(material1 == material2) {
					material2 = sortearMaterial();
				}
				this.mesa.distribuirMaterial(material1, material2);
			} catch (InterruptedException e) {
				System.out.println("Falha na producao!");
				e.printStackTrace();
			}
		}
	}
	
	private Material sortearMaterial() {
		Random random = new Random();
		int codigo = random.nextInt(3);
		
		return Material.getMaterial(codigo);
	}
}
