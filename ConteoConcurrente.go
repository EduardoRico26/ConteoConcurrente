package main

import (
	"fmt"
	"sync"
	"time"
)

//Equivalente a "class ContadorHilo"

type ContadorHilo struct {
	nombre string
	inicio int
	fin    int
}

func (c *ContadorHilo) contar(wg *sync.WaitGroup) {
	defer wg.Done() // Avisa al WaitGroup cuando termina (equivale a join())

	fmt.Printf("%s cuenta:\n", c.nombre)

	for i := c.inicio; i <= c.fin; i++ {
		fmt.Printf("%s: %d\n", c.nombre, i)
		time.Sleep(1 * time.Millisecond)
	}
}

// Equivalente a "class ConteoConcurrente" (main)

func main() {
	var n, numHilos int

	fmt.Print("Ingrese el número a contar: ")
	fmt.Scan(&n)

	fmt.Print("Ingrese la cantidad de hilos: ")
	fmt.Scan(&numHilos)

	numerosPorHilo := n / numHilos
	inicio := 1

	hilos := make([]*ContadorHilo, numHilos)

	for i := 0; i < numHilos; i++ {
		fin := inicio + numerosPorHilo - 1

		// El último hilo toma los números sobrantes
		if i == numHilos-1 {
			fin = n
		}

		hilos[i] = &ContadorHilo{
			nombre: fmt.Sprintf("Hilo-%d", i+1),
			inicio: inicio,
			fin:    fin,
		}
		inicio = fin + 1
	}

	var wg sync.WaitGroup

	// TIMER: inicia ANTES de lanzar las goroutines
	tiempoInicio := time.Now()

	for _, hilo := range hilos {
		wg.Add(1)
		go hilo.contar(&wg) // goroutine = equivalente a hilo.start()
	}

	wg.Wait() // Espera a que todas las goroutines terminen (equivalente a join())

	// TIMER: captura al terminar todas las goroutines
	duracion := time.Since(tiempoInicio)

	fmt.Println("\n--- Resumen ---")
	fmt.Printf("El número %d contado con %d hilo(s) demoró %d ms\n",
		n, numHilos, duracion.Milliseconds())

	fmt.Println("Conteo finalizado.")
}
