package br.edu.idp.tech.poo;


import java.util.Random;


public class GeradorNumAleatorio {

    public static int gerarInt(int faixa) {
        Random random = new Random();
        int numero = random.nextInt(faixa);
        return numero;
    }
}
