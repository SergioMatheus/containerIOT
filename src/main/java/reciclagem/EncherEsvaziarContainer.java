package reciclagem;

public class EncherEsvaziarContainer implements Runnable {

	private Container container;
	private Integer contadorContainer;

	public EncherEsvaziarContainer(Container container, boolean caminhao) {
		this.container = container;
		this.container.setCaminhao(caminhao);
		this.contadorContainer = 0;
	}

	public void run() {
		if (!this.container.isCaminhao()) {
			while (contadorContainer < 100) {
				contadorContainer += (int) (Math.random() * 40);
				System.out.println(Thread.currentThread().getName() + " - " + contadorContainer);
				try {
					Thread.sleep((int) (Math.random() * 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i <= this.container.getTipoContainer().length - 1; i++) {
				if (this.container.getTipoContainer()[i] == 0) {
					this.container.getTipoContainer()[i] = 1;
					// TODO: ENVIAR PACOTE PARA CENTRAL
					System.out.println("Container " + i + " cheio.");
					break;
				}
			}
		} else {
			System.out.println("Esvaziando Container");
			this.container.setTipoContainer(new Integer[] { 0, 0, 0, 0 });
		}
		imprimirContainers();
	}

	private void imprimirContainers() {
		System.out.println("__");
		System.out.println(this.container.getTipoContainer()[0] + " " + this.container.getTipoContainer()[1] + " "
				+ this.container.getTipoContainer()[2] + " " + this.container.getTipoContainer()[3]);
		System.out.println("__");
	}
}