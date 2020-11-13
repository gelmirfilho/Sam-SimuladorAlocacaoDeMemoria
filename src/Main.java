import java.util.ArrayList;
import java.util.Random;

public class Main {
	public static void main(String[] args) {

		// --------------------------------------------------
		// Unitins - TOCANTINS
		// Algoritmo feito por Gelmir Elias Baumgratz Filho
		// Para a aula de Sistemas Operacionais
		// --------------------------------------------------

		// declara��o das listas.
		ArrayList<Integer> parti��esBest = new ArrayList<Integer>();
		ArrayList<Integer> processosBest = new ArrayList<Integer>();

		ArrayList<Integer> parti��esFist = new ArrayList<Integer>();
		ArrayList<Integer> processosFist = new ArrayList<Integer>();

		ArrayList<Integer> parti��esWorst = new ArrayList<Integer>();
		ArrayList<Integer> processosWorst = new ArrayList<Integer>();

		// declara��o do gerador de numero.
		Random aleatorio = new Random();

		// criando a lista de parti��es com o minimo de 100 e o maximo de 500.
		int min = 100;
		int max = 500;
		for (int i = 0; i < 10; i++) {
			parti��esBest.add((int) (Math.random() * (max - min + 1) + min));
			parti��esFist.add(parti��esBest.get(i));
			parti��esWorst.add(parti��esBest.get(i));
		}
		System.out.println(
				"As parti��es criadas foram: " + parti��esWorst + " todas com tamanhos entre 100 KB e 500KB. \n");

		// criando a lista de processos com o minimo de 10 e o maximo de 500.
		min = 10;
		max = 500;
		for (int i = 0; i < 10; i++) {
			processosBest.add((int) (Math.random() * (max - min + 1) + min));
			processosFist.add(processosBest.get(i));
			processosWorst.add(processosBest.get(i));
		}
		System.out.println(
				"Os processos criadas foram: " + processosWorst + " todos com tamanhos entre 10KB e 500KB. \n");

		// ------------------------------------------- processamento best-fit
		// -------------------------------------------
		double somatorioSobras = 0;
		int melhorParti��o = 0;
		int melhorSobra = 99999999;
		ArrayList<Integer> processosSobras = new ArrayList<Integer>();

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------");
		System.out.println("					Processamento Best-Fit");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------ \n");

		try {
			for (int j = 0; j <= 9; j++) {
				for (int i = 0; i < parti��esBest.size(); i++) {
					if (processosBest.get(0) <= parti��esBest.get(i)) {
						if (parti��esBest.get(i) - processosBest.get(0) < melhorSobra) {
							melhorParti��o = parti��esBest.get(i);
							melhorSobra = parti��esBest.get(i) - processosBest.get(0);
						}
					}
				}
				if (melhorSobra == 99999999) {
					System.err.println(
							"O processo de " + processosBest.get(0) + " KB n�o cabe em nenhuma parti��o atual \n");
					processosSobras.add(processosBest.remove(0));
				} else {
					somatorioSobras += melhorSobra;
					System.out.println("A melhor parti��o achada para o processo de " + processosBest.get(0)
							+ " KB, foi a parti��o de " + melhorParti��o + " KB, pois sobrou apenas " + melhorSobra
							+ " KB, somando um total de " + somatorioSobras + " KB desperdi�ados.");
					processosBest.remove(0);
					for (int i = 0; i < parti��esBest.size(); i++) {
						if (parti��esBest.get(i).equals(melhorParti��o)) {
							parti��esBest.remove(i);
							i = 999;
						}
					}
					melhorParti��o = 0;
					melhorSobra = 99999999;
				}
				System.out.println(
						"Parti��es restantes " + parti��esBest + "\n" + "Processos restantes " + processosBest + "\n");
			}
		} catch (Exception e) {
			System.err.println("erro");
		}
		if (!processosSobras.isEmpty())
			System.out.println("Os processos de " + processosSobras + " KB n�o cabem em nenhuma parti��o");
		System.out.println("O total desperci�ado foi de " + somatorioSobras + " KB com uma m�dia de "
				+ (somatorioSobras / (9 - processosSobras.size())) + " KB \n");
		double sobraTotalBest = somatorioSobras;

		// ------------------------------------------- processamento worst-fit
		// -------------------------------------------
		somatorioSobras = 0;
		melhorParti��o = 0;
		melhorSobra = -99999999;
		processosSobras = new ArrayList<Integer>();

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------");
		System.out.println("					Processamento Worst-Fit");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------ \n");

		try {
			for (int j = 0; j <= 9; j++) {
				for (int i = 0; i < parti��esWorst.size(); i++) {
					if (processosWorst.get(0) <= parti��esWorst.get(i)) {
						if (parti��esWorst.get(i) - processosWorst.get(0) > melhorSobra) {
							melhorParti��o = parti��esWorst.get(i);
							melhorSobra = parti��esWorst.get(i) - processosWorst.get(0);
						}
					}
				}
				if (melhorSobra == -99999999) {
					System.err.println(
							"O processo de " + processosWorst.get(0) + " KB n�o cabe em nenhuma parti��o atual \n");
					processosSobras.add(processosWorst.remove(0));
				} else {
					somatorioSobras += melhorSobra;
					System.out.println("A melhor parti��o achada para o processo de " + processosWorst.get(0)
							+ " KB, foi a parti��o de " + melhorParti��o + " KB, pois sobrou apenas " + melhorSobra
							+ " KB, somando um total de " + somatorioSobras + " KB desperdi�ados.");
					processosWorst.remove(0);
					for (int i = 0; i < parti��esWorst.size(); i++) {
						if (parti��esWorst.get(i).equals(melhorParti��o)) {
							parti��esWorst.remove(i);
							i = 999;
						}
					}
					melhorParti��o = 0;
					melhorSobra = -99999999;
				}
				System.out.println("Parti��es restantes " + parti��esWorst + "\n" + "Processos restantes "
						+ processosWorst + "\n");
			}
		} catch (Exception e) {
			System.err.println("erro");
		}
		if (!processosSobras.isEmpty())
			System.out.println("Os processos de " + processosSobras + " KB n�o cabem em nenhuma parti��o");
		System.out.println("O total desperci�ado foi de " + somatorioSobras + " KB com uma m�dia de "
				+ (somatorioSobras / (9 - processosSobras.size())) + " KB \n");
		double sobraTotalWorst = somatorioSobras;

		// ------------------------------------------- processamento fist-fit
		// -------------------------------------------
		somatorioSobras = 0;
		processosSobras = new ArrayList<Integer>();

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------");
		System.out.println("					Processamento Fist-Fit");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------ \n");

		try {
			for (int j = 0; j <= 10; j++) {
				for (int i = 0; i < parti��esFist.size(); i++) {
					if (processosFist.get(0) <= parti��esFist.get(i)) {
						System.out.println("O processo de " + processosFist.get(0) + " KB foi alocado na partici��o de "
								+ parti��esFist.get(i) + " KB e sobrou " + (parti��esFist.get(i) - processosFist.get(0))
								+ " KB. O total desperdi�ado atual � de "
								+ (somatorioSobras += parti��esFist.get(i) - processosFist.get(0)) + " KB");
						// somatorioSobras += parti��es.get(i) - processos.get(0);
						parti��esFist.remove(i);
						processosFist.remove(0);
						i = 999;
						System.out.println("Parti��es restantes " + parti��esFist + "\n" + "Processos restantes "
								+ processosFist + "\n");
					} else if (i == parti��esFist.size() - 1) {
						i = 999;
						System.err.println("O processo de " + processosFist.get(0)
								+ " KB n�o cabe em nenhuma partici��o atual" + "\n");
						processosSobras.add(processosFist.get(0));
						processosFist.remove(0);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (!processosSobras.isEmpty())
			System.out.println("Os processos de " + processosSobras + " KB n�o cabem em nenhuma parti��o");
		System.out.println("O total desperci�ado foi de " + somatorioSobras + " KB com uma m�dia de "
				+ (somatorioSobras / (9 - processosSobras.size())) + " KB ");
		double sobraTotalFist = somatorioSobras;

		System.out.println("\n---------------------------------------------------------");
		System.out.println("\nO resultado final ficou: \n");
		System.out.println("Best-Fit resultou num desperdi�io total de " + sobraTotalBest + " MB \n");
		System.out.println("Worst-Fit resultou num desperdi�io total de " + sobraTotalWorst + " MB \n");
		System.out.println("Fist-Fit resultou num desperdi�io total de " + sobraTotalFist + " MB \n");
		System.out.println("---------------------------------------------------------\n");
	}
}
