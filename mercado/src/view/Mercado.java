package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import helper.utils;
import model.Produto;

public class Mercado {
	private static Scanner teclado = new Scanner(System.in);
	private static ArrayList<Produto> produtos;
	
	//Map vai separar o produto que foi selecionado e o integer vai salvar a quantidade.
	private static Map<Produto, Integer> carrinho;

	public static void main(String[] args) {
		produtos = new ArrayList<Produto>();
		//HashMap é uma classe que implementa a interface Map
		carrinho = new HashMap<Produto, Integer>();
		Mercado.menu();
	}
	
	private static void menu() {
		System.out.println("============================");
		System.out.println("==========bem-vindo=========");
		System.out.println("============================");
		
		System.out.println("Selecione uma das Opçoes: ");
		System.out.println("1 - Cadastrar Produto");
		System.out.println("2 - Listar Produtos");
		System.out.println("3 - Comprar Produto");
		System.out.println("4 - Visualizar Carrinho");
		System.out.println("5 - Sair do sistema");
		
		int opcao = 0;
		try {
			opcao = Integer.parseInt(Mercado.teclado.nextLine());
		}catch(InputMismatchException e) {
			Mercado.menu();
		}catch(NumberFormatException f) {
			Mercado.menu();
		}
		
		switch (opcao) {
			case 1:
				Mercado.cadastrarProduto();
				break;
			case 2: 
				Mercado.listarProdutos();
				break;
			case 3:
				Mercado.comprarProdutos();
				break;
			case 4:
				Mercado.visualizarProdutos();
				break;
			case 5:
				System.out.println("Volte Sempre!");
				utils.pausar(2);
				System.exit(0);
			default:
				System.out.println("Opção inválida!");
				utils.pausar(2);
				Mercado.menu();
				break;
		}
	}
	private static void cadastrarProduto() {
		System.out.println("=======================================");
		System.out.println("=========Cadastro do Produto===========");
		System.out.println("Informe o nome do produto: ");
		String nome = Mercado.teclado.nextLine();
		
		System.out.println("Informe o preço do produto: ");
		Double preco = Mercado.teclado.nextDouble();
		
		Produto produto = new Produto(nome, preco);
		
		Mercado.produtos.add(produto);
		
		System.out.println("Produto " + produto.getNome()+" foi cadastrado com sucesso!");
		utils.pausar(2);
		Mercado.menu();
	}
	
	
	private static void listarProdutos() {
		if (Mercado.produtos.size()>0) {
			System.out.println("Listagem de produtos: ");
			
			for(Produto p : Mercado.produtos) {
				System.out.println("====================");
				System.out.println(p);
				System.out.println("");
			}
		}else {
			System.out.println("Não tem produtos cadastrados.");
		}
		utils.pausar(2);
		Mercado.menu();
	}
	
	
	private static void comprarProdutos() {
		if(Mercado.produtos.size()> 0) {
			System.out.println("Informe o código do produto que deseja comprar: ");
			System.out.println();
			System.out.println("=============Produtos Disponiveis==============");
			for(Produto p : Mercado.produtos) {
				System.out.println(p);
				System.out.println("============================================");
			}
			int codigo = Integer.parseInt(Mercado.teclado.nextLine());
			boolean tem = false;
			
			for(Produto p : Mercado.produtos) {
				if(p.GetCodigo()==codigo) {
					int quant = 0;
					try {
						quant = Mercado.carrinho.get(p);
						//se ja tiver produto no carrinho, atualizar a quantidade//
						Mercado.carrinho.put(p, quant+1);
					}catch(NullPointerException e) {
						//Primeiro produto no carrinho
						Mercado.carrinho.put(p, 1);
					}
					System.out.println("O produto "+p.getNome()+" foi adicionado ao carrinho");
					tem = true;
				}
				if (tem) {
					System.out.println("Deseja adcionar outros produtos?");
					System.out.println("Informe 1 para sim ou 0 para não");
					int op = Integer.parseInt(Mercado.teclado.nextLine());
					
					if (op == 1) {
						Mercado.comprarProdutos();
					}else {
						System.out.println("Por favor, aguarde o fechamento do pedido.");
						utils.pausar(2);
						Mercado.fecharPedido();
					}
				}else {
					System.out.println("Ainda não existe produto cadastrado com o codigo "+ codigo);
					utils.pausar(2);
					Mercado.menu();
				}
			}
			
		}else {
			System.out.println("Não existem produtos cadastrados no sistema.");
			utils.pausar(2);
			Mercado.menu();
		}
	}
	
	
	private static void visualizarProdutos() {
		if(Mercado.carrinho.size()> 0) {
			System.out.println("Produtos no carrinho: ");
			
			for(Produto p : Mercado.carrinho.keySet()) {
				System.out.println("Produto: "+p+ "\nQuantidade: " + Mercado.carrinho.get(p));
			}
		}else {
			System.out.println("ainda nao tem produtos no carrinho");
		}
		utils.pausar(2);
		Mercado.menu();
	}

	private static void fecharPedido() {
		Double valorTotal = 0.0;
		System.out.println("Produtos do carrinho:");
		System.out.println("---------------------");
		for (Produto p : Mercado.carrinho.keySet()) {
			int quant = Mercado.carrinho.get(p);
			valorTotal += p.getPreco()* quant;
			System.out.println(p);
			System.out.println("Quantidade: " + quant);
			System.out.println("--------------------");
		}
		System.out.println("Sua Fatura é: " + utils.doubleParaString(valorTotal));
		Mercado.carrinho.clear();
		System.out.println("Obrigado pela preferência!");
		utils.pausar(2);
		Mercado.menu();
		
	}
}
