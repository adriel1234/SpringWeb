package com.web.SpringWeb;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.web.SpringWeb.entidades.Cliente;
import com.web.SpringWeb.entidades.Pedido;
import com.web.SpringWeb.servicos.Configuracao;


public class teste {

	public static void main(String[] args) {
		/*
		    * João é um comerciante que vende laranjas Ele precisa fazer uma entrega de
		    * algumas caixas cada laranja ele vende por 0,50 centavos, cada caixa tem 50
		    * laranjas
		    * 
		    * Faça um programa que peça a quantidade de caixas para o joão e de o total a
		    * receber
		    * 
		    * Perguntar se o pagamento é a vista, caso seja a vista e valor for maior que
		    * R$ 100 reais dar um desconto de 10% 
		    
		    *
		    * Obs: aceitar parcelas no máximo até 12 meses, e mostrar o valor de cada
		    * parcela já com acrescimo
		    * 
		    * 
		    * O lucro do comerciante será de 45% em suas vendas
		    * 
		    * Dar um relatório final sobre a operação acima
		    */
		List<Pedido> pedidos = new ArrayList<Pedido>();
		Configuracao config = new Configuracao();
		Scanner s = new Scanner(System.in);

		while (true) {
			System.out.println("===========================================");
			System.out.println("Seja muito bem vindo joão ao seu software");
			System.out.println("===========================================");
			System.out.println(("Digite: \n1 para continuar \n0 para sair\n"));
			int sair = s.nextInt();
			if (sair == 0)break;

			Pedido pedido = new Pedido();
			pedido.setCliente(new Cliente());
			System.out.println("Digite o nome do cliente que quer comprar as caixas:\n");

			pedido.getCliente().setNome(s.next());
			System.out.println("Digite a quantidade de caixas que o " + pedido.getCliente().getNome() + " deseja?\n");
			
			int quantidadeCaixas = s.nextInt();
			
			pedido.setQuantidadeDeCaixas(quantidadeCaixas);

			System.out.println("[" + pedido.getQuantidadeDeCaixas() + "]");

			pedido.setTotalDeLaranjas(pedido.getQuantidadeDeCaixas(), config.getValorLaranja(), config.getQuantidadeDeLaranjaPorCaixa());
			
			System.out.println("Você deseja pagar a vista ou parcelado ?\n A - À vista\n P - Parcelado\n");

			String tipoPagamento = s.next();

			boolean aVista = tipoPagamento.toUpperCase().equals("A");
			if (aVista) {
				System.out.println("Você selecionou o pagamento a vista");
				
				pedido.alterarValorParaPagamentoAVista(config.getValorParaDesconto(), quantidadeCaixas, config.getQuantidadeCaixasPromocao(), config.getPorcentagemDesconto());
			} else {
				System.out.println("Você selecionou o pagamento a parcelado, Digite a quantidade de parcelas\n");
				pedido.setParcelas(s.nextInt());
				if (pedido.getParcelas() > config.getMaximoParcelas()) {
					System.out.println("Quantidade de parcelas inválida, iremos assumir em " + config.getMaximoParcelas() + " vezes");
					pedido.setParcelas(config.getMaximoParcelas());
				}

				if (pedido.getParcelas() == 1) {
					System.out.println("Você selecionou o pagamento a vista");
					pedido.alterarValorParaPagamentoAVista(config.getValorParaDesconto(),pedido.getQuantidadeDeCaixas(), config.getQuantidadeCaixasPromocao(), config.getPorcentagemDesconto());	
					
				} else{
					pedido.acrescentaJuros(config.getPorcentagemAcrescimo());
				}
			}
			
			

			pedido.calculaLucroReceber(config.getPorcentagemLucro());

			pedidos.add(pedido);

			System.out.println("===========================================");
			System.out.println("Muito bem seu João parabéns pela venda");
			System.out.println("O seu lucro é de: R$ " + pedido.getLucroAReceber());
			System.out.println("O valor total a cobrar do cliente é de: R$ " + pedido.getValorTotalAlterado());
			System.out.println("O cliente escolheu o tipo de pagamento: " + (aVista ? "À vista" : "Parcelado em " + pedido.getParcelas() + " vezes"));
			if (aVista) {
				System.out.println("Para o pagamento a vista demos o desconto dê: R$ " + (pedido.valorTotalComDesconto()));
			} else {
				System.out.println("Para o pagamento a parcelado colocamos um acrescimo dê: R$ " + pedido.valorDoJuros());
				System.out.println("O valor da parcela será de: R$ " + pedido.valorParcela());
			}
			System.out.println("===========================================");
		}

		for (int i = 0; i < pedidos.size(); i++) {
			Pedido pedido = pedidos.get(i);
			System.out.println("===========================================");
			System.out.println("Cliente: " + pedido.getCliente().getNome());
			System.out.println("Quantidade de caixas: " + pedido.getQuantidadeDeCaixas());
			System.out.println("Valor total a pagar: " + pedido.getValorTotal());
			System.out.println("===========================================");
		}

	}

}
