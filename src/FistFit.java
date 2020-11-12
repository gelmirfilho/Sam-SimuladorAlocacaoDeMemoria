import java.util.ArrayList;
import java.util.Random;

public class FistFit {
	public static void main(String[] args) {

		// declaração das listas.
		ArrayList<Integer> partições = new ArrayList<Integer>();
		ArrayList<Integer> processos = new ArrayList<Integer>();

		// declaração do gerador de numero.
		Random aleatorio = new Random();

		// criando a lista de partições com o minimo de 100 e o maximo de 500.
		int min = 100;
		int max = 500;
		for (int i = 0; i < 10; i++) {
			partições.add((int) (Math.random() * (max - min + 1) + min));
		}
		System.out.println("As partições criadas foram: " + partições + "\n");

		// criando a lista de processos com o minimo de 10 e o maximo de 500.
		min = 10;
		max = 500;
		for (int i = 0; i < 10; i++) {
			processos.add((int) (Math.random() * (max - min + 1) + min));
		}
		System.out.println("Os processos criadas foram: " + processos + "\n");

		// processamento fist-fit
		double somatorioSobras = 0;
		ArrayList<Integer> processosSobras = new ArrayList<Integer>();

		System.out.println("--------------------------------------");
		System.out.println("Processamento Fist-Fit");
		System.out.println("--------------------------------------");

		try {
			for (int j = 0; j <= 10; j++) {
				for (int i = 0; i < partições.size(); i++) {
					if (processos.get(0) <= partições.get(i)) {
						System.out.println("O processo de " + processos.get(0) + " KB foi alocado na particição de "
								+ partições.get(i) + " KB e sobrou " + (partições.get(i) - processos.get(0))
								+ " KB. O total desperdiçado atual é de "
								+ (somatorioSobras += partições.get(i) - processos.get(0))+" KB");
						// somatorioSobras += partições.get(i) - processos.get(0);
						partições.remove(i);
						processos.remove(0);
						i = 999;
						System.out.println(
								"Partições restantes " + partições + "\n" + "Processos restantes " + processos + "\n");
					} else if (i == partições.size() - 1) {
						i = 999;
						System.err.println("O processo de " + processos.get(0)
								+ " KB não cabe em nenhuma particição atual" + "\n");
						processosSobras.add(processos.get(0));
						processos.remove(0);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Os processos de " + processosSobras + " KB não cabem em nenhuma partição");
		System.out.println("O total desperciçado foi de "+somatorioSobras+" KB com uma média de "+(somatorioSobras/(9-processosSobras.size()))+" KB ");
	}
}
