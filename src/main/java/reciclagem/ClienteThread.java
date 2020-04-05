package reciclagem;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ClienteThread implements Runnable {

	private String msg;

	public ClienteThread(String msg) {
		this.msg = msg;
	}

	@Override
	public void run() {
		try {
			JSONParser jsonParser = new JSONParser();

			try (FileReader reader = new FileReader("lista_dispositivos.json")) {
				Object arquivo = jsonParser.parse(reader);
				JSONObject objeto = (JSONObject) arquivo;
				JSONObject conector = (JSONObject) objeto.get("central");

				String host = (String) conector.get("ip");
				int porta = Integer.parseInt((String) conector.get("porta"));

				Socket socket = new Socket(host, porta);

				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				out.flush();
				System.out.println("Enviando comando: " + msg);
				out.println(msg);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (SocketException e) {
				System.out.println("Erro de socket");
			} catch (IOException e) {
				System.out.println("Erro de conexao");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ocorreu um erro inesperado");
		}
	}

}
