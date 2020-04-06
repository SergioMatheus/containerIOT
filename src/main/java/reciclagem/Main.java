package reciclagem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String args[]) throws InterruptedException {
		ServerSocket servidor;
		try {
			int portaServer = 8082;
			servidor = new ServerSocket(portaServer);
			System.out.println("Servidor iniciado na porta " + portaServer);
			Container container = new Container(6);
			encherContainer(container);
			while (servidor.isBound()) {
				Socket cliente = servidor.accept();
				System.out.println("Caminhão conectado no IP " + cliente.getInetAddress().getHostAddress());
				BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
				String mensagemCliente = entrada.readLine();
				System.out.println("Comando para conectar: " + mensagemCliente);
				caminhaoChegou(container, mensagemCliente);
				cliente.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void caminhaoChegou(Container container, String mensagemCliente) throws InterruptedException {
		if (mensagemCliente.equalsIgnoreCase("cheguei_container") || mensagemCliente.contains("CHEGUEI_CONTAINER")) {
			limparContainer(container);
			int sleepContainter = (int) ((Math.random() * 10000) + 1000);
			System.out.println("Tempo para o container encher novamente: " + sleepContainter);
			Thread.sleep(sleepContainter);
			encherContainer(container);
		}
	}

	private static void limparContainer(Container container) {
		new Thread(new EncherEsvaziarContainer(container, true)).start();
	}

	private static void encherContainer(Container container) {
		if (!container.containerIsFull()) {
			new Thread(new EncherEsvaziarContainer(container, false)).start();
			new Thread(new EncherEsvaziarContainer(container, false)).start();
			new Thread(new EncherEsvaziarContainer(container, false)).start();
			new Thread(new EncherEsvaziarContainer(container, false)).start();
		}
	}

}
