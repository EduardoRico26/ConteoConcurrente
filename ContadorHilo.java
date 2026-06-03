class ContadorHilo extends Thread {
    private int inicio;
    private int fin;

    public ContadorHilo(String nombre, int inicio, int fin) {
        super(nombre);
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    public void run() {
        System.out.println(getName() + " cuenta:");

        for (int i = inicio; i <= fin; i++) {
            System.out.println(getName() + ": " + i);

            try {
                Thread.sleep(0); // Pruebas para numeros pequeños,ponemos demora entre un numero y otro para ver la recurrencia
            } catch (InterruptedException e) {
                System.out.println(getName() + " fue interrumpido.");
            }
        }
    }
}