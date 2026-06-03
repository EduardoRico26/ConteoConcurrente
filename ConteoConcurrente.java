import java.util.Scanner;

public class ConteoConcurrente {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el número a contar: ");
        int n = sc.nextInt();

        System.out.print("Ingrese la cantidad de hilos: ");
        int numHilos = sc.nextInt();
         // saber cuantos numeros cuenta cada hilo
        int numerosPorHilo = n / numHilos;
        int inicio = 1;

        //Crear los arreglos de los hilos
        ContadorHilo[] hilos = new ContadorHilo[numHilos];

        //Los sobrantes los toma el ultimo hilo
        for (int i = 0; i < numHilos; i++) {
            int fin = inicio + numerosPorHilo - 1;

            if (i == numHilos - 1) {
                fin = n;
            }

            hilos[i] = new ContadorHilo("Hilo-" + (i + 1), inicio, fin);
            inicio = fin + 1;
        }

        // TIMER: inicia ANTES de lanzar los hilos
        long tiempoInicio = System.nanoTime();

        for (ContadorHilo hilo : hilos) {
            hilo.start();
        }

        // Esperar a que terminen todos los hilos
        for (ContadorHilo hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // TIMER: captura al terminar todos los hilos
        long tiempoFin = System.nanoTime();
        long duracionMs = (tiempoFin - tiempoInicio) / 1_000_000;

        System.out.printf("El número %d contado con %d hilo(s) demoró %d ms%n",
                n, numHilos, duracionMs);

        System.out.println("Conteo finalizado.");
        sc.close();
    }
}