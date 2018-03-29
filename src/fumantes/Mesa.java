package fumantes;

import java.util.ArrayList;
import java.util.List;

import utils.EstadoFumante;
import utils.Material;

public class Mesa {
	/**
	 * Lista de materiais produzidos pelo agente
	 */
	List<Material> materiaisDisponiveis;
	
	public Mesa() {
		materiaisDisponiveis = new ArrayList<Material>();
	}
	
	/**
	 * Monta um cigarro baseado nos materiais produzidos. Se os materiais necessários nao foram produzidos,
	 * aguarda até uma nova producao.
	 * 
	 * @param fumante - O Fumante tentando fumar
	 * @throws InterruptedException - Caso haja um erro na sincronia das threads.
	 */
	public synchronized void fumar(Fumante fumante) throws InterruptedException {
		if(materiaisDisponiveis.size() != 2) {
			//System.out.println(fumante.getNome() + " aguardando");
		}
		else if(fumante.getEstado() == EstadoFumante.FUMANDO) {
			System.out.println(fumante.getNome() + " ja estava fumando");
			wait();
		}
		else {
			if(materiaisDisponiveis.containsAll(fumante.getMateriaisNecessarios())) {
				fumante.setEstado(EstadoFumante.FUMANDO);
				System.out.println(fumante.getNome() + " comecou a fumar");
				materiaisDisponiveis.clear();
			}
		}
	}
	
	/**
	 * Termina de fumar e "revive" as outras threads
	 * @param fumante - O fumante que estava fumando
	 */
	public synchronized void terminarDeFumar(Fumante fumante) {
		fumante.setEstado(EstadoFumante.AGUARDANDO);
		System.out.println(fumante.getNome() + " terminou de fumar");
		notifyAll();
	}
	
	/**
	 * Recebe os materiais produzidos pelo agente e adiciona na lista de materiais disponiveis.
	 * @param material1 - Material produzido pelo agente
	 * @param material2 - Segundo material produzido pelo agente
	 */
	public synchronized void distribuirMaterial(Material material1, Material material2) {
		materiaisDisponiveis.clear();
		System.out.println("Materiais produzidos: " + material1.getNome() + " e " + material2.getNome());
		materiaisDisponiveis.add(material1);
		materiaisDisponiveis.add(material2);
	}
}
