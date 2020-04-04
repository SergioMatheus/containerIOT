package reciclagem;

public class Container {

	private Integer idContainer;
	private Integer[] tipoContainer = { 0, 0, 0, 0 };

	/**
	 * 0 - PAPEL 1 - VIDRO 2 - METAL 4 - PLASTICO
	 */

	public static void main(String args[]) throws InterruptedException {
		boolean caminhao = false;
		Integer[] tipoContainer = { 0, 0, 0, 0 };
		Thread mod1 = new Thread(new EncherContainer(tipoContainer, caminhao));
		caminhao = true;
		Thread mod2 = new Thread(new EncherContainer(tipoContainer, caminhao));
		Thread mod3 = new Thread(new EncherContainer(tipoContainer, caminhao));
		Thread mod4 = new Thread(new EncherContainer(tipoContainer, caminhao));
		// TODO: VERIFICAR SE A LÓGICA PODE SER UTILIZADA ASSIM
		mod1.start();
		mod2.start();
//		mod3.start();
//		mod4.start();
//		new Thread(new ThreadCount(tipoContainer)).start();
	}
}
