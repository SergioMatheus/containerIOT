package reciclagem;

public class EncherContainer implements Runnable {
	private Integer contadorContainer;
	private Integer[] tipoContainer;
	private boolean caminhao;

	public EncherContainer(Integer[] tipoContainer, boolean caminhao) {
		super();
		this.tipoContainer = tipoContainer;
		this.contadorContainer = 0;
		this.caminhao = caminhao;
	}

	@Override
	public void run() {
		if (!caminhao) {
			while (contadorContainer < 100) {
				contadorContainer += (int) (Math.random() * 80);
				System.out.println(Thread.currentThread() + " - " + contadorContainer);
				try {
					Thread.sleep((int) (Math.random() * 3000) + 500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i <= tipoContainer.length - 1; i++) {
				if (tipoContainer[i] == 0) {
					tipoContainer[i] = 1;
					// TODO: ENVIAR PACOTE PARA CENTRAL
					System.out.println("Container " + i + " cheio.");
					break;
				}
			}
		} else {
			System.out.println("Esvaziando Container");
			this.tipoContainer = new Integer[] { 0, 0, 0, 0 };
		}
		imprimirContainers();
	}

	private void imprimirContainers() {
		System.out.println("_______________");
		System.out.println(tipoContainer[0] + " " + tipoContainer[1] + " " + tipoContainer[2] + " " + tipoContainer[3]);
		System.out.println("_______________");
	}

}
