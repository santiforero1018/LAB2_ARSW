package arsw.threads;

/**
 * Un galgo que puede correr en un carril
 * 
 * @author rlopez
 * 
 */
public class Galgo extends Thread {
	private int paso;
	private Carril carril;
	RegistroLlegada regl;
	private boolean running;

	/*
	//  * Constructor de la clase Galgo
	 * @param carril
	 * @param name
	 * @param reg
	 */
	public Galgo(Carril carril, String name, RegistroLlegada reg) {
		super(name);
		this.carril = carril;
		paso = 0;
		this.running = true;
		this.regl = reg;
	}

	// Pone a correr al galgo
	public void corra() throws InterruptedException {

		while (paso < carril.size()) {

			Thread.sleep(100);
			synchronized (regl) {
				carril.setPasoOn(paso++);
			}

			carril.displayPasos(paso);

			if (paso == carril.size()) {
				carril.finish();
				int ubicacion = regl.getUltimaPosicionAlcanzada();
				regl.setUltimaPosicionAlcanzada(ubicacion + 1);
				System.out.println("El galgo " + this.getName() + " llego en la posicion " + ubicacion);

				if (ubicacion == 1) {
					regl.setGanador(this.getName());
				}
			}

			// valida si la bandera running es true o false, si es false, ejecuta el metodo stopGalgo
			if (!running) {
				this.stopGalgo();
			}
		}

	}

	// realiza una acción de espera sobre el hilo correspondiente al Galgo
	public void stopGalgo() throws InterruptedException {
		synchronized (regl) {
			try {
				regl.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/*
	 * Cambiar el estado del galgo (corriendo o detenido)
	 * @param stop
	*/
	public void setRunning(boolean stop) {
		this.running = stop;
	}

	@Override
	public void run() {
		try {
			corra();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
