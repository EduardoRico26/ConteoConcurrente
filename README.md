# Conteo Concurrente con Hilos en Java y Go

## Información General

**Asignatura:** Arquitecturas de Software (ARSW)

**Docente:** Rodrigo Humberto Gualtero Martínez

**Estudiante:** Eduardo Rico Duarte

**Fecha:** 03/06/2026

---

## Descripción

Este repositorio contiene el desarrollo de un ejercicio práctico de concurrencia cuyo objetivo es implementar un programa capaz de realizar el conteo de números desde 1 hasta un valor n, distribuyendo el trabajo entre múltiples hilos de ejecución.

El ejercicio fue desarrollado en dos lenguajes de programación:

- Java
- Go (Golang)

Cada implementación busca aplicar los conceptos fundamentales de concurrencia vistos en clase, permitiendo comprender cómo diferentes hilos o goroutines pueden ejecutar tareas simultáneamente y cómo influye la cantidad de hilos en el desempeño del programa.

---

## Objetivos

### Objetivo General

Desarrollar un programa concurrente que realice el conteo desde 1 hasta un número dado utilizando múltiples hilos de ejecución.

### Objetivos Específicos

- Comprender el funcionamiento de los hilos de ejecución.
- Aplicar conceptos de concurrencia en Java y Go.
- Distribuir una tarea entre múltiples hilos.
- Medir el tiempo de ejecución del programa.
- Analizar el impacto de la cantidad de hilos sobre el rendimiento.

---

# Estructura del Repositorio

El desarrollo se realizó en el repositorio ConteoConcurrente en GitHub 

## Ramas Utilizadas

| Rama | Descripción |
|--------|--------|
| Taller | Implementación del ejercicio en Java |
| Taller-go | Implementación del ejercicio en Go |



---

# Fundamento Teórico

La concurrencia es la capacidad de un sistema para manejar múltiples operaciones o transacciones de manera simultánea.

Su objetivo es aprovechar mejor los recursos computacionales disponibles, permitiendo que varias tareas avancen de manera concurrente.

## Estrategias de Concurrencia

### Memoria Compartida

Permite que múltiples hilos accedan a una misma región de memoria para intercambiar información.

Entre sus ventajas se encuentra una comunicación rápida entre hilos al no requerir intercambio explícito de mensajes.

Sin embargo, requiere mecanismos de sincronización para evitar inconsistencias en los datos, condiciones de carrera y otros problemas asociados al acceso concurrente.

Java implementa principalmente este modelo mediante Threads, monitores, semáforos y otros mecanismos de sincronización.

### Paso de Mensajes

En este modelo cada proceso mantiene su propio espacio de memoria y la comunicación se realiza mediante el envío y recepción de mensajes.

Este enfoque reduce problemas asociados a la memoria compartida y facilita la construcción de sistemas distribuidos y escalables.

Go adopta esta filosofía mediante goroutines y canales (channels), permitiendo una concurrencia más segura y sencilla de implementar.

## Hilos y Goroutines

En Java la concurrencia se implementa mediante objetos de tipo Thread.

Cada hilo representa una unidad independiente de ejecución que puede ejecutarse concurrentemente con otros hilos.

En Go la concurrencia se implementa mediante goroutines.

Las goroutines son más ligeras que los threads tradicionales y permiten crear miles de tareas concurrentes con una menor sobrecarga de recursos.

---

# Desarrollo del Ejercicio

El programa solicita al usuario:

1. El número máximo a contar.
2. La cantidad de hilos a utilizar.

Posteriormente:

1. Se divide el rango de números entre los hilos disponibles.
2. Cada hilo recibe un subconjunto del rango.
3. Los hilos son ejecutados concurrentemente.
4. Se mide el tiempo total de ejecución.
5. Se presenta el tiempo requerido para completar el conteo.



# Resultados Experimentales

Todas las pruebas fueron realizadas utilizando:

n = 500000


---

## Resultados Java

| Número de Hilos | Tiempo (ms) |
|----------------|------------|
| 10 | 27128 |
| 50 | 27373 |
| 60 | 27234 |
| 100 | 27355 |
| 200 | 27507 |
| 300 | 28174 |
| 400 | 27557 |
| 1000 | 27321 |
| 2000 | 28218 |
| 5000 | 28452 |
| 6000 | 28159 |
| 7000 | 28365 |
| 9000 | 28760 |
| 10000 | 29123 |

---

## Resultados Go

| Número de Hilos | Tiempo (ms) |
|----------------|------------|
| 50 | 16188 |
| 60 | 13395 |
| 100 | 10409 |
| 300 | 10035 |
| 500 | 9465 |
| 600 | 10003 |
| 900 | 10125 |
| 1000 | 9654 |
| 1200 | 10795 |
| 3000 | 12423 |
| 5000 | 9553 |
| 6000 | 14069 |
| 7000 | 14563 |
| 9000 | 17098 |
| 10000 | 10108 |
| 12000 | 21083 |

---

# Análisis de Resultados

A continuación se presentan las gráficas obtenidas a partir de los resultados experimentales.

## Java

![Gráfica Java](Imagenes/graf1.png)


---

## Go

![Gráfica Go](Imagenes/graf2.png)


---

## Discusión de Resultados

Inicialmente podría suponerse que aumentar la cantidad de hilos reduciría continuamente el tiempo de ejecución debido a que el trabajo se distribuye entre más unidades de procesamiento.

Sin embargo, los resultados experimentales muestran que esta relación no es lineal.

Aunque en algunos casos el tiempo disminuye al incrementar la cantidad de hilos, después de cierto punto la tendencia general es un aumento en el tiempo total de ejecución.

### Coste de Creación de Hilos

Cada hilo requiere recursos del sistema para ser creado y administrado.

Cuando se crean miles de hilos, el costo de creación y administración puede superar el beneficio obtenido por la división del trabajo.

### Cambios de Contexto

El procesador dispone de un número limitado de núcleos físicos.

Cuando la cantidad de hilos excede ampliamente el número de núcleos disponibles, el sistema operativo debe alternar constantemente entre ellos.

Este proceso se conoce como cambio de contexto (Context Switching) y genera una sobrecarga adicional.

### Planificación del Sistema Operativo

El scheduler del sistema operativo debe gestionar la ejecución de todos los hilos activos.

A medida que aumenta la cantidad de hilos, también aumenta el trabajo del scheduler, reduciendo la eficiencia general del sistema.

### Operaciones de Entrada y Salida

La impresión de información en consola es una operación relativamente lenta.

Por esta razón, parte importante del tiempo medido corresponde a operaciones de entrada y salida y no únicamente al proceso de conteo.

---

## Relación con la Teoría Vista en Clase

Los resultados obtenidos permiten observar uno de los conceptos fundamentales de la concurrencia: la existencia de una cantidad óptima de hilos para una tarea determinada.

Aunque es posible crear miles de hilos, esto no implica necesariamente una mejora en el rendimiento.

Después de cierto punto, la sobrecarga asociada a la administración de hilos, cambios de contexto y planificación comienza a superar los beneficios de la paralelización.

Este comportamiento explica por qué los tiempos observados no disminuyen indefinidamente y, en muchos casos, terminan aumentando.

Los resultados también muestran una diferencia importante entre Java y Go.

Las goroutines de Go son considerablemente más ligeras que los threads tradicionales de Java, permitiendo manejar una gran cantidad de tareas concurrentes con una menor sobrecarga.

Esto coincide con los beneficios descritos en la teoría revisada durante el curso.

---

## Comparación General

Los resultados obtenidos muestran que Go presentó tiempos de ejecución inferiores a Java en la mayoría de las pruebas realizadas.

Esto puede atribuirse al diseño de las goroutines y al modelo de concurrencia implementado por el runtime de Go.

Sin embargo, ambos lenguajes evidencian el mismo comportamiento general: después de cierto número de hilos la sobrecarga de administración comienza a afectar negativamente el rendimiento.

---

# Conclusiones

- Fue posible implementar exitosamente un programa concurrente para realizar conteos utilizando múltiples hilos de ejecución.
- El ejercicio permitió comprender conceptos fundamentales de concurrencia como creación de hilos, sincronización y ejecución concurrente.
- Los resultados demostraron que aumentar la cantidad de hilos no garantiza una reducción del tiempo de ejecución.
- La existencia de cambios de contexto y sobrecarga administrativa puede generar tiempos mayores cuando se utilizan demasiados hilos.
- Go presentó mejores tiempos de ejecución debido a la eficiencia de las goroutines y su modelo de concurrencia.
- La concurrencia constituye una herramienta poderosa para mejorar el aprovechamiento de los recursos computacionales, pero requiere un uso adecuado para obtener beneficios reales.

---

# Anexos

A continuación se presentan las evidencias de ejecución del programa.

## Resultados Java

## Evidencia 1

![Evidencia 1](Imagenes/Ev1.png)

---

## Evidencia 2

![Evidencia 2](Imagenes/Ev2.png)

---

## Evidencia 3

![Evidencia 3](Imagenes/Ev3.png)

---

## Evidencia 4

![Evidencia 4](Imagenes/Ev4.png)

---

## Evidencia 5

![Evidencia 5](Imagenes/Ev5.png)

---
## Evidencia 6

![Evidencia 6](Imagenes/Ev6.png)

---
## Resultados Go

## Evidencia 7

![Evidencia 7](Imagenes/Ev7.png)

---
## Evidencia 8

![Evidencia 8](Imagenes/Ev8.png)

---
## Evidencia 9

![Evidencia 9](Imagenes/Ev9.png)

---
## Evidencia 10

![Evidencia 10](Imagenes/Ev10.png)

---
## Evidencia 11

![Evidencia 11](Imagenes/Ev11.png)

---
## Evidencia 12

![Evidencia 12](Imagenes/Ev12.png)

---
## Evidencia 13

![Evidencia 13](Imagenes/Ev13.png)

---
## Evidencia 14

![Evidencia 14](Imagenes/Ev14.png)

---
## Evidencia 15

![Evidencia 15](Imagenes/Ev15.png)

---
## Evidencia 16

![Evidencia 16](Imagenes/Ev16.png)

---
# Bibliografía

1. Benavides Navarro, L. D., & Gualtero Martínez, R. H. (2024). *Concurrencia e hilos en Java y Go* [Diapositivas de clase]. 

2. OpenAI. (2026). *ChatGPT* (versión GPT-5.5) [Modelo de lenguaje]. https://chatgpt.com/ (Se utilizó principalmente como herramienta de apoyo para construir el código en Go) 

3. Oracle. (s.f.). *The Java™ Tutorials: Concurrency*. Oracle Corporation. Recuperado el 3 de junio de 2026, de https://docs.oracle.com/javase/tutorial/essential/concurrency/

4. The Go Authors. (s.f.). *Effective Go: Concurrency*. Google. Recuperado el 3 de junio de 2026, de https://go.dev/doc/effective_go

5. Silberschatz, A., Galvin, P. B., & Gagne, G. (2018). *Operating System Concepts* (10th ed.). John Wiley & Sons.

6. Tanenbaum, A. S., & Bos, H. (2015). *Modern Operating Systems* (4th ed.). Pearson.


