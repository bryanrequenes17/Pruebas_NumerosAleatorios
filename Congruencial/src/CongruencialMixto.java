

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CongruencialMixto {

//Metodo para calcular el numero primo mas cercano
    public static int nPrimo_Cercano(int num) {
        int cont = 2;
        boolean n_primo = true;

        while ((n_primo) && (cont != num)) {
            if (num % cont == 0) {
                n_primo = false;
            }
            cont++;
        }
        if (n_primo) {
            return num;
        } else {
            return nPrimo_Cercano(num - 1);
        }
    }

    public static void main(String args[]) {
        int a = 0;
        int c = 0;
        int x0 = 0;
        int m = 0;
        int k = 0;
         ArrayList<Double> numeros = new ArrayList<>();
        //calculo del multiplicador "a"
        while (k <= 0) {
            k = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el valor de 'k' para el calculo del multiplicador", "MULTIPLICADOR \na=(2^k) + 1", JOptionPane.INFORMATION_MESSAGE));
            if (k <= 0) {
                JOptionPane.showMessageDialog(null, "El valor de 'k' debe ser mayor a cero");
            }
            a = (int) Math.pow(2, k) + 1;
        }
        System.out.println("a-->" + a);

        //calculo de la semilla "X0"
        while (x0 <= 0) {
            x0 = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese x0"));
            if (x0 <= 0) {
                JOptionPane.showMessageDialog(null, "El valor de 'x0' debe ser mayor a cero");
            }
        }
        System.out.println("x0-->" + x0);

        //calculo del modulo "m"
        int d = 0;
        while (m <= a || m <= x0) {
            d = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el valor de 'd' para el calculo del modulo 'm'", "MODULO \nm=2^d", JOptionPane.INFORMATION_MESSAGE));
            m = (int) Math.pow(2, d);
            if (m <= a || m <= c || m <= x0) {
                JOptionPane.showMessageDialog(null, "El valor de 'm' debe ser mayor a los valores de a, c y x0");
            }
        }
        System.out.println("m-->" + m);

        //calculo de la constante aditiva "c"
        c = nPrimo_Cercano(m);
        JOptionPane.showMessageDialog(null, "El valor de la constante aditiva 'c' es " + c);
        System.out.println("c-->" + c);

DecimalFormat df = new DecimalFormat("0.00000");
        int n_pseudo = 0;
        int cociente = 0;
        double n_uniformes = 0.00;
        
        System.out.println("n" + "\t" + "xn" + "\t" + a + "Xn+" + c + "/" + m + "\t" + "Xn+1" + "\t" + "Numeros Uniformes" + "\n");
        int aux = x0;
        for (int i = 1; i <= m; i++) {            
            n_pseudo = ((a * x0) + c) % m; //(aXn+c) mod m
            n_uniformes = (double) n_pseudo / m; //para calcular el valor del numero uniforme (0,1)
            cociente = ((a * x0) + c) / m; //operacion -> ((a * x0)+c) {divisor}
            System.out.println(i + "\t" + x0 + "\t" + cociente + "+" + n_pseudo + "/" + m + " "+" \t" + n_pseudo + "\t" + n_pseudo + "/" + m +" ==> "+df.format(n_uniformes));
            
            numeros.add(n_uniformes);//arreglo para guardadr los numeros para hacer las pruebas
            
            x0 = n_pseudo;
            if (i > 1 && aux == n_pseudo) {
                break;
            }
        }
        Metodos_Pruebas.prueba_Promedios(numeros); //llamo al metodo para la prueba de promedios
        Metodos_Pruebas.prueba_Frecuencias(numeros);
        Metodos_Pruebas.prueba_Series(numeros);
    }

}
