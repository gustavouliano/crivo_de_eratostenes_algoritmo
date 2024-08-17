package concorrente;

import java.util.Arrays;
import java.util.Scanner;

public class CrivoEratostenesConcorrente {

    static class MarcarMultiplos implements Runnable {
        private final boolean[] numeros;
        private final int primo;
        private final int limiteSuperior;

        public MarcarMultiplos(boolean[] numeros, int primo, int limiteSuperior) {
            this.numeros = numeros;
            this.primo = primo;
            this.limiteSuperior = limiteSuperior;
        }

        @Override
        public void run() {
            for (int j = primo * primo; j <= limiteSuperior; j += primo) {
                numeros[j] = false; 
            }
        }
    }

    public static void crivoEratostenes(int limiteSuperior) throws InterruptedException {
        boolean[] numeros = new boolean[limiteSuperior + 1];
        Arrays.fill(numeros, true);
        numeros[0] = numeros[1] = false;

        int limiteRaizQuadrada = (int)Math.sqrt(limiteSuperior);
        Thread[] threads = new Thread[limiteRaizQuadrada];

        long inicio_tempo = System.nanoTime();
        
        for (int i = 2; i <= limiteRaizQuadrada; ++i) {
            if (numeros[i]) {
                threads[i - 2] = new Thread(new MarcarMultiplos(numeros, i, limiteSuperior));
                threads[i - 2].start();
            }
        }

        // Aguarda todas as threads terminarem
        for (Thread thread : threads) {
            if (thread != null) {
                thread.join();
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
        System.out.println("Tempo de execução concorrente: " + tempoExecucao + " ms");
    }

    public static void main(String[] args) throws InterruptedException {
//        Scanner input = new Scanner(System.in);
//        System.out.print("Limite superior: ");
    	int limiteSuperior = 100_000_000;
        crivoEratostenes(limiteSuperior);
//        input.close();
    }
}
