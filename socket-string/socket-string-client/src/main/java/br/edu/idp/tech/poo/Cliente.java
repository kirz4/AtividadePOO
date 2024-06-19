package br.edu.idp.tech.poo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {

    public void iniciar(String endereco, int porta) {
        ObjectOutputStream saida;
        ObjectInputStream entrada;
        Socket conexao;
        Scanner ler = new Scanner(System.in);
        String mensagem = "";
        try {
            conexao = new Socket(endereco, porta);
            System.out.println("J.R.R. Tolkien que criou Eru e Melkor");
            System.out.println("Conectado ao servidor " + endereco + ", na porta: " + porta);
            System.out.println("Digite: FIM para encerrar a conexão");

            // Conexões de saída e entrada
            saida = new ObjectOutputStream(conexao.getOutputStream());
            saida.flush();
            entrada = new ObjectInputStream(conexao.getInputStream());

            // Lendo a mensagem enviada pelo servidor
            mensagem = (String) entrada.readObject();
            System.out.println("Servidor>> " + mensagem);

            do {
                System.out.print("Cliente::: ");
                mensagem = ler.nextLine();
                saida.writeObject(mensagem);
                saida.flush();

                // Lendo a mensagem enviada pelo servidor
                String resposta = (String) entrada.readObject();
                System.out.println("A sua mensagem foi enviada para Servidor e ele respondeu:\n>> " + resposta);
                System.out.println("(a mensagem original foi \"" + mensagem + "\")");

            } while (!mensagem.equals("FIM"));

            saida.close();
            entrada.close();
            conexao.close();

        } catch (Exception e) {
            System.err.println("Erro: " + e.toString());
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Uso: java Cliente <endereco-IP> <porta>");
            System.exit(1);
        }

        Cliente c = new Cliente();
        c.iniciar(args[0], Integer.parseInt(args[1]));
    }
}
