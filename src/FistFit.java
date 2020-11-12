import java.util.ArrayList;
import java.util.Random;

public class FistFit {
	public static void main(String[] args) {

		// declara��o das listas.
		ArrayList<Integer> parti��es = new ArrayList<Integer>();
		ArrayList<Integer> processos = new ArrayList<Integer>();

		// declara��o do gerador de numero.
		Random aleatorio = new Random();

		// criando a lista de parti��es com o minimo de 100 e o maximo de 500.
		int min = 100;
		int max = 500;
		for (int i = 0; i < 10; i++) {
			parti��es.add((int) (Math.random() * (max - min + 1) + min));
		}
		System.out.println("As parti��es criadas foram: " + parti��es + "\n");

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
				for (int i = 0; i < parti��es.size(); i++) {
					if (processos.get(0) <= parti��es.get(i)) {
						System.out.println("O processo de " + processos.get(0) + " KB foi alocado na partici��o de "
								+ parti��es.get(i) + " KB e sobrou " + (parti��es.get(i) - processos.get(0))
								+ " KB. O total desperdi�ado atual � de "
								+ (somatorioSobras += parti��es.get(i) - processos.get(0))+" KB");
						// somatorioSobras += parti��es.get(i) - processos.get(0);
						parti��es.remove(i);
						processos.remove(0);
						i = 999;
						System.out.println(
								"Parti��es restantes " + parti��es + "\n" + "Processos restantes " + processos + "\n");
					} else if (i == parti��es.size() - 1) {
						i = 999;
						System.err.println("O processo de " + processos.get(0)
								+ " KB n�o cabe em nenhuma partici��o atual" + "\n");
						processosSobras.add(processos.get(0));
						processos.remove(0);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Os processos de " + processosSobras + " KB n�o cabem em nenhuma parti��o");
		System.out.println("O total desperci�ado foi de "+somatorioSobras+" KB com uma m�dia de "+(somatorioSobras/(9-processosSobras.size()))+" KB ");
	}
}
