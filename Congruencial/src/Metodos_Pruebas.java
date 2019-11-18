
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author bryan
 */
public class Metodos_Pruebas {

    public static void prueba_Promedios(ArrayList<Double> n_pseudoaleatorios) {
        System.out.println("\nPRUEBA DE PROMEDIOS");
        double suma = 0.00;
        int N = n_pseudoaleatorios.size();
        for (int i = 0; i < N; i++) {
            suma = suma + n_pseudoaleatorios.get(i);
        }
        double media = suma / N;
        //Zo=((media-0.5)*raiz N) / (raiz 1/12)
        double Z0 = ((media - 0.5) * Math.sqrt(N)) / Math.sqrt(0.083333);  //ESTADISTICO
        System.out.println("Media x --> " + media);
        System.out.println("Zo --> " + Z0);
        if (Math.abs(Z0) < 1.96) { //nivel se significancia del 95%
            System.out.println("NO se puede rechaza la hipotesis de que los numeros pseudoaleatorios tienen un nivel esperado de aceptacion de 0.5");
        } else {
            System.out.println("SE rechaza  la hipotesis de que los numeros pseudoaleatorios tienen un nivel esperado de aceptacion de 0.5");
        }
        System.out.println("---------------------------------------------------");
    }

    public static void prueba_Frecuencias(ArrayList<Double> n_pseudoaleatorios) {
        System.out.println("\nPRUEBA DE FRECUENCIAS");
        int n = 5;   // # de subintervalos
        int N = n_pseudoaleatorios.size();
        int f_esperada = N / n;
        int f_observada[] = new int[n];
        double valores[] = new double[n]; //los valores de .2  .4 .....
        double estadistico = 0.00; //valor del estadistico X
        System.out.println("FE\tFO");
        for (int i = 0; i < n; i++) {
            int aux = 0;
            for (int j = 0; j < N; j++) {
                valores[i] = (double) (i + 1) / n; //.2 .4 ...
                if (i == 0) {
                    if (n_pseudoaleatorios.get(j) < valores[i]) {
                        aux++;
                        f_observada[i] = aux;
                    }
                } else {
                    if ((n_pseudoaleatorios.get(j) < valores[i]) && (n_pseudoaleatorios.get(j) >= valores[i - 1])) {
                        aux++;
                        f_observada[i] = aux;
                    }
                }
            }
            System.out.println(f_esperada + "\t" + f_observada[i] + "  ->  " + valores[i]);
            estadistico = estadistico + Math.pow((f_observada[i] - f_esperada), 2); // (F0-FE)^2
        }
        double X = (double) estadistico / f_esperada;  //SUM((F0-FE)^2)/FE

        System.out.println("n--> " + n);
        System.out.println("(Xo)^2 --> " + X);
        //α nivel de significancia   n-1 grados de libertad    NC=1-α
        //con un vlaor de  α de 0.05; y n=5 el valor de chi cuadrado seria --> X α,n-1 == 9,49
        double chi_cuadrado = 9.49;
        if (X < chi_cuadrado) {
            System.out.println("NO se puede rechaza la hipotesis de que los numeros pseudoaleatorios provienen de una distribucion uniforme");
        } else {
            System.out.println("SE rechazar la hipotesis de que los numeros pseudoaleatorios provienen de una distribucion uniforme");
        }
    }
    public static void prueba_Series(ArrayList<Double> n_pseudoaleatorios) {
        System.out.println("\n PRUEBA DE SERIES");
          int n = 25;   // # de subintervalos
        int N = n_pseudoaleatorios.size();
        int f_esperada = (N-1) / n;
        int t = (int) Math.sqrt(n);
        int f_observada[][] = new int[t][t];
        double valores[] = {0.2,0.4,0.6,0.8,1}; //los valores de .2  .4 .....
        double estadistico = 0.00; //valor del estadistico X

        for (int i = 0; i < N; i++) {
            double num = n_pseudoaleatorios.get(i);
            double num2 = n_pseudoaleatorios.get(i+1);
            int aux = 0;
            for (int j = 0; j < t; j++) {
                
                 if (i == 0) {
                    if (num < valores[i]) {
                        for (int k = 0; k <t; k++) {
                             if (k == 0) {
                    if (num2 < valores[i]) {
                        aux++;
                        f_observada[j][k] = aux;
                    }
                } else {
                    if ((num2 < valores[i]) && (num2 >= valores[i - 1])) {
                        aux++;
                        f_observada[j][k] = aux;
                    }
                }
                        }
                    }
                } else {
                    if ((num < valores[i]) && (num >= valores[i - 1])) {
                        for (int k = 0; k < t; k++) {
                             if (i == 0) {
                    if (num2 < valores[i]) {
                        aux++;
                        f_observada[j][k] = aux;
                    }
                } else {
                    if ((num2 < valores[i]) && (num2 >= valores[i - 1])) {
                        aux++;
                        f_observada[j][k] = aux;
                    }
                }
                        }
                    }
                }
               
            }
        }
        System.out.println("FE\tFO");
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < t; j++) {
                System.out.println(f_esperada+'\t'+f_observada[i][j]);   
                estadistico = estadistico + Math.pow((f_observada[i][j] - f_esperada), 2);
            }
            
        }
        double X = (double) estadistico / f_esperada;  //SUM((F0-FE)^2)/FE

        System.out.println("n--> " + n);
        System.out.println("(Xo)^2 --> " + X);
        //α nivel de significancia   n-1 grados de libertad    NC=1-α
        //con un vlaor de  α de 0.05; y n=5 el valor de chi cuadrado seria --> X α,n-1 == 9,49
        double chi_cuadrado = 36.4;
        if (X < chi_cuadrado) {
            System.out.println("NO se puede rechaza la hipotesis de que los numeros pseudoaleatorios provienen de una distribucion uniforme");
        } else {
            System.out.println("SE rechazar la hipotesis de que los numeros pseudoaleatorios provienen de una distribucion uniforme");
        }
    }
}
