package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fumantes.Agente;
import fumantes.Fumante;
import fumantes.Mesa;
import utils.Material;

public class Main {

	public static void main(String[] args) {
		Mesa mesa = new Mesa();
		
		List<Fumante> listaFumantes = new ArrayList<Fumante>();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("           <<< Bem vindo a tabacaria da GEC! >>>");
		System.out.println("Quantas pessoas vao fumar? ");
		int numeroFumantes = sc.nextInt();
		sc.nextLine();
		
		//Cadastra todos os fumantes
		for(int count = 1; count <= numeroFumantes; count++) {
			Fumante fumante = cadastrarFumante(count, sc);
			fumante.setMesa(mesa);
			listaFumantes.add(fumante);
		}
		sc.close();
		
		System.out.println("Todos os fumantes foram cadastrados! Iniciando a distribuição dos materiais... \n");
		//Inicia a thread do agente que produz os materiais
		Thread agente = new Thread(new Agente(mesa));
		agente.start();
		
		//Inicia as threads dos fumantes
		for(Fumante fumante : listaFumantes) {
			System.out.println(fumante);
			new Thread(fumante).start();;
		}
		System.out.println("");
		
	}
	
	/**
	 * Le os dados do usuario e cria um objeto Fumante.
	 * @param numeroFumante - O numero do fumante na fila
	 * @param sc - O scanner para ler inputs do teclado
	 * @return Fumante - O fumante cadastrado
	 */
	public static Fumante cadastrarFumante(int numeroFumante, Scanner sc) {
		sc.nextLine();
		System.out.println("Digite o nome do fumante [" + numeroFumante + "]: ");
		String nomeFumante = sc.nextLine();
		
		System.out.println("Digite quantos SEGUNDOS " + nomeFumante + " leva fumando: ");
		int tempoFumando = sc.nextInt() * 1000;
		
		Material material = selecionarMaterial(sc);
		
		return new Fumante(nomeFumante, material, tempoFumando);
	}
	
	/**
	 * Exibe o menu de selecao de materiais e retorna o material selecionado
	 * 
	 * @return Material - O enum do material selecionado
	 */
	public static Material selecionarMaterial(Scanner sc) {
		exibirMenu();
		int codigoMaterial = sc.nextInt();
		while(codigoMaterial < 0 || codigoMaterial > 2) {
			System.out.println("Codigo invalido!");
			exibirMenu();
			codigoMaterial = sc.nextInt();
			//sc.nextLine();
		}
		return Material.getMaterial(codigoMaterial);
	}
	
	/**
	 * Exibe o menu de selecao de materiais
	 */
	public static void exibirMenu() {
		System.out.println("Selecione um material inicial: ");
		System.out.println("    0 - Papel");
		System.out.println("    1 - Fumo");
		System.out.println("    2 - Fosforo");
	}
	
}
