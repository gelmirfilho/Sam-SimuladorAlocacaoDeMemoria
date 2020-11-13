import java.util.ArrayList;
import java.util.Random;

public class Main {
	public static void main(String[] args) {

		// --------------------------------------------------
		// Unitins - TOCANTINS
		// Algoritmo feito por Gelmir Elias Baumgratz Filho
		// Para a aula de Sistemas Operacionais
		// --------------------------------------------------

		// declaração das listas.
		ArrayList<Integer> partiçõesBest = new ArrayList<Integer>();
		ArrayList<Integer> processosBest = new ArrayList<Integer>();

		ArrayList<Integer> partiçõesFist = new ArrayList<Integer>();
		ArrayList<Integer> processosFist = new ArrayList<Integer>();

		ArrayList<Integer> partiçõesWorst = new ArrayList<Integer>();
		ArrayList<Integer> processosWorst = new ArrayList<Integer>();

		// declaração do gerador de numero.
		Random aleatorio = new Random();

		// criando a lista de partições com o minimo de 100 e o maximo de 500.
		int min = 100;
		int max = 500;
		for (int i = 0; i < 10; i++) {
			partiçõesBest.add((int) (Math.random() * (max - min + 1) + min));
			partiçõesFist.add(partiçõesBest.get(i));
			partiçõesWorst.add(partiçõesBest.get(i));
		}
		System.out.println(
				"As partições criadas foram: " + partiçõesWorst + " todas com tamanhos entre 100 KB e 500KB. \n");

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
		int melhorPartição = 0;
		int melhorSobra = 99999999;
		ArrayList<Integer> processosSobras = new ArrayList<Integer>();

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------");
		System.out.println("					Processamento Best-Fit");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------ \n");

		try {
			for (int j = 0; j <= 9; j++) {
				for (int i = 0; i < partiçõesBest.size(); i++) {
					if (processosBest.get(0) <= partiçõesBest.get(i)) {
						if (partiçõesBest.get(i) - processosBest.get(0) < melhorSobra) {
							melhorPartição = partiçõesBest.get(i);
							melhorSobra = partiçõesBest.get(i) - processosBest.get(0);
						}
					}
				}
				if (melhorSobra == 99999999) {
					System.err.println(
							"O processo de " + processosBest.get(0) + " KB não cabe em nenhuma partição atual \n");
					processosSobras.add(processosBest.remove(0));
				} else {
					somatorioSobras += melhorSobra;
					System.out.println("A melhor partição achada para o processo de " + processosBest.get(0)
							+ " KB, foi a partição de " + melhorPartição + " KB, pois sobrou apenas " + melhorSobra
							+ " KB, somando um total de " + somatorioSobras + " KB desperdiçados.");
					processosBest.remove(0);
					for (int i = 0; i < partiçõesBest.size(); i++) {
						if (partiçõesBest.get(i).equals(melhorPartição)) {
							partiçõesBest.remove(i);
							i = 999;
						}
					}
					melhorPartição = 0;
					melhorSobra = 99999999;
				}
				System.out.println(
						"Partições restantes " + partiçõesBest + "\n" + "Processos restantes " + processosBest + "\n");
			}
		} catch (Exception e) {
			System.err.println("erro");
		}
		if (!processosSobras.isEmpty())
			System.out.println("Os processos de " + processosSobras + " KB não cabem em nenhuma partição");
		System.out.println("O total desperciçado foi de " + somatorioSobras + " KB com uma média de "
				+ (somatorioSobras / (9 - processosSobras.size())) + " KB \n");
		double sobraTotalBest = somatorioSobras;

		// ------------------------------------------- processamento worst-fit
		// -------------------------------------------
		somatorioSobras = 0;
		melhorPartição = 0;
		melhorSobra = -99999999;
		processosSobras = new ArrayList<Integer>();

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------");
		System.out.println("					Processamento Worst-Fit");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------ \n");

		try {
			for (int j = 0; j <= 9; j++) {
				for (int i = 0; i < partiçõesWorst.size(); i++) {
					if (processosWorst.get(0) <= partiçõesWorst.get(i)) {
						if (partiçõesWorst.get(i) - processosWorst.get(0) > melhorSobra) {
							melhorPartição = partiçõesWorst.get(i);
							melhorSobra = partiçõesWorst.get(i) - processosWorst.get(0);
						}
					}
				}
				if (melhorSobra == -99999999) {
					System.err.println(
							"O processo de " + processosWorst.get(0) + " KB não cabe em nenhuma partição atual \n");
					processosSobras.add(processosWorst.remove(0));
				} else {
					somatorioSobras += melhorSobra;
					System.out.println("A melhor partição achada para o processo de " + processosWorst.get(0)
							+ " KB, foi a partição de " + melhorPartição + " KB, pois sobrou apenas " + melhorSobra
							+ " KB, somando um total de " + somatorioSobras + " KB desperdiçados.");
					processosWorst.remove(0);
					for (int i = 0; i < partiçõesWorst.size(); i++) {
						if (partiçõesWorst.get(i).equals(melhorPartição)) {
							partiçõesWorst.remove(i);
							i = 999;
						}
					}
					melhorPartição = 0;
					melhorSobra = -99999999;
				}
				System.out.println("Partições restantes " + partiçõesWorst + "\n" + "Processos restantes "
						+ processosWorst + "\n");
			}
		} catch (Exception e) {
			System.err.println("erro");
		}
		if (!processosSobras.isEmpty())
			System.out.println("Os processos de " + processosSobras + " KB não cabem em nenhuma partição");
		System.out.println("O total desperciçado foi de " + somatorioSobras + " KB com uma média de "
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
				for (int i = 0; i < partiçõesFist.size(); i++) {
					if (processosFist.get(0) <= partiçõesFist.get(i)) {
						System.out.println("O processo de " + processosFist.get(0) + " KB foi alocado na particição de "
								+ partiçõesFist.get(i) + " KB e sobrou " + (partiçõesFist.get(i) - processosFist.get(0))
								+ " KB. O total desperdiçado atual é de "
								+ (somatorioSobras += partiçõesFist.get(i) - processosFist.get(0)) + " KB");
						// somatorioSobras += partições.get(i) - processos.get(0);
						partiçõesFist.remove(i);
						processosFist.remove(0);
						i = 999;
						System.out.println("Partições restantes " + partiçõesFist + "\n" + "Processos restantes "
								+ processosFist + "\n");
					} else if (i == partiçõesFist.size() - 1) {
						i = 999;
						System.err.println("O processo de " + processosFist.get(0)
								+ " KB não cabe em nenhuma particição atual" + "\n");
						processosSobras.add(processosFist.get(0));
						processosFist.remove(0);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (!processosSobras.isEmpty())
			System.out.println("Os processos de " + processosSobras + " KB não cabem em nenhuma partição");
		System.out.println("O total desperciçado foi de " + somatorioSobras + " KB com uma média de "
				+ (somatorioSobras / (9 - processosSobras.size())) + " KB ");
		double sobraTotalFist = somatorioSobras;

		System.out.println("\n---------------------------------------------------------");
		System.out.println("\nO resultado final ficou: \n");
		System.out.println("Best-Fit resultou num desperdiçio total de " + sobraTotalBest + " MB \n");
		System.out.println("Worst-Fit resultou num desperdiçio total de " + sobraTotalWorst + " MB \n");
		System.out.println("Fist-Fit resultou num desperdiçio total de " + sobraTotalFist + " MB \n");
		System.out.println("---------------------------------------------------------\n");
	}
}
