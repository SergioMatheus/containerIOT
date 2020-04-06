package reciclagem;

public class EncherEsvaziarContainer implements Runnable {

	private Container container;
	private Integer contadorContainer;

	public EncherEsvaziarContainer(Container container, boolean caminhao) {
		this.container = container;
		this.container.setCaminhao(caminhao);
		this.contadorContainer = 0;
	}

	@Override
	public void run() {
		if (!this.container.isCaminhao()) {
			while (contadorContainer < 100) {
				contadorContainer += (int) (Math.random() * 10);
				System.out.println(
						Thread.currentThread().getName() + " - " + (contadorContainer > 100 ? 100 : contadorContainer));
				try {
					Thread.sleep((int) (Math.random() * 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			setLixoContainer();
			contadorContainer = 0;
			if (containerCheio()) {
				new Thread(new ClienteThread("CHEIO " + container.getIdContainer())).start();
			}
		} else {
			System.out.println("Esvaziando Container");
			this.container.setTipoContainer(new Integer[] { 0, 0, 0, 0 });
			this.container.setCaminhao(false);
		}
		imprimirContainers();
	}

	private boolean containerCheio() {
		for (int i = 0; i < container.getTipoContainer().length; i++) {
			if (container.getTipoContainer()[i] == 0) {
				return false;
			}
		}
		return true;
	}

	private void setLixoContainer() {
		for (int i = 0; i <= this.container.getTipoContainer().length - 1; i++) {
			if (this.container.getTipoContainer()[i] == 0) {
				this.container.getTipoContainer()[i] = 1;
				String msg = "CHEIO " + conteverTipo(i) + " " + container.getIdContainer();
				// new Thread(new ClienteThread(msg)).start();
				System.out.println(msg);
				break;
			}
		}
	}

	private String conteverTipo(int i) {
		String resp = "";
		switch (i) {
		case 0:
			resp = "PAPEL";
			break;
		case 1:
			resp = "VIDRO";
			break;
		case 2:
			resp = "METAL";
			break;
		case 3:
			resp = "PLASTICO";
			break;
		}
		return resp;
	}

	private void imprimirContainers() {
		System.out.println("__");
		System.out.println(this.container.getTipoContainer()[0] + " " + this.container.getTipoContainer()[1] + " "
				+ this.container.getTipoContainer()[2] + " " + this.container.getTipoContainer()[3]);
		System.out.println("__");
	}
}