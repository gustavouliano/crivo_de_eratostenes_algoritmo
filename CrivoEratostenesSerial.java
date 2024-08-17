package serial;

import java.util.Arrays;
import java.util.Scanner;

public class CrivoEratostenesSerial {

    public static void crivoEratostenes(int limiteSuperior) {
        boolean[] numeros = new boolean[limiteSuperior + 1];
        Arrays.fill(numeros, true);
        numeros[0] = numeros[1] = false;

        int limiteRaizQuadrada = (int)Math.sqrt(limiteSuperior);

        long inicio_tempo = System.nanoTime();

        for (int i = 2; i <= limiteRaizQuadrada; i++) {
            if (numeros[i]) {
                for (int j = i * i; j <= limiteSuperior; j += i) {
                    numeros[j] = false;
                }
            }
        }
        
        long fim_tempo = System.nanoTime();

        int qtd = 0;
        for (int i = 2; i <= limiteSuperior; ++i) {
            if (numeros[i]) {
                qtd++;
            }
        }
        double tempoExecucao = (double)(fim_tempo - inicio_tempo) / 1_000_000;
        System.out.println("Quantidade de números primos encontrados: " + qtd);
        System.out.println("Tempo de execução: " + tempoExecucao + " ms");

    }
    
    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
        System.out.print("Limite superior: ");
    	int limiteSuperior = 1_000;
        crivoEratostenes(limiteSuperior);
//        input.close();
    }
}