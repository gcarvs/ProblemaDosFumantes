package fumantes;

import java.util.ArrayList;
import java.util.List;

import utils.EstadoFumante;
import utils.Material;

public class Mesa {
	List<Material> materiaisDisponiveis;
	
	public Mesa() {
		materiaisDisponiveis = new ArrayList<Material>();
	}
	
	public synchronized void fumar(Fumante fumante) throws InterruptedException {
		if(materiaisDisponiveis.size() != 2) {
			System.out.println(fumante.getNome() + " aguardando*");
		}
		else if(fumante.getEstado() == EstadoFumante.FUMANDO) {
			System.out.println(fumante.getNome() + " ja estava fumando");
			wait();
		}
		else {
			if(fumante.getMaterialInicial() == Material.FOSFORO) {
				if(materiaisDisponiveis.contains(Material.FUMO) && materiaisDisponiveis.contains(Material.PAPEL)) {
					fumante.setEstado(EstadoFumante.FUMANDO);
					System.out.println(fumante.getNome() + " comecou a fumar");
				}
				else {
					wait();
				}
			}
			else if(fumante.getMaterialInicial() == Material.FUMO) {
				if(materiaisDisponiveis.contains(Material.FOSFORO) && materiaisDisponiveis.contains(Material.PAPEL)) {
					fumante.setEstado(EstadoFumante.FUMANDO);
					System.out.println(fumante.getNome() + " comecou a fumar");
				}
				else {
					wait();
				}
			}
			else if(fumante.getMaterialInicial() == Material.PAPEL) {
				if(materiaisDisponiveis.contains(Material.FOSFORO) && materiaisDisponiveis.contains(Material.FUMO)) {
					fumante.setEstado(EstadoFumante.FUMANDO);
					System.out.println(fumante.getNome() + " comecou a fumar");
				}
				else {
					wait();
				}
			}
		}
	}

	public synchronized void terminarDeFumar(Fumante fumante) {
		fumante.setEstado(EstadoFumante.AGUARDANDO);
		notifyAll();
	}
	
	public synchronized boolean distribuirMaterial(Material material1, Material material2) {
		materiaisDisponiveis.clear();
		materiaisDisponiveis.add(material1);
		materiaisDisponiveis.add(material2);
		
		return true;
	}
}
