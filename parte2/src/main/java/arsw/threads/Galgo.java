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

	public Galgo(Carril carril, String name, RegistroLlegada reg) {
		super(name);
		this.carril = carril;
		paso = 0;
		this.running = true;
		this.regl = reg;
	}

	public void corra() throws InterruptedException {
		while (paso < carril.size()) {
			Thread.sleep(100);
			synchronized (regl) {
				carril.setPasoOn(paso++);
			}

			carril.displayPasos(paso);

			if (!running) {
				this.stopGalgo();
			}

			if (paso == carril.size()) {
				carril.finish();
				int ubicacion = regl.getUltimaPosicionAlcanzada();
				regl.setUltimaPosicionAlcanzada(ubicacion + 1);
				System.out.println("El galgo " + this.getName() + " llego en la posicion " + ubicacion);
				
				if (ubicacion == 1) {
					synchronized(regl){
						regl.setGanador(this.getName());
					}
				}
			}
		}

	}

	public void stopGalgo() throws InterruptedException {
		synchronized (regl) {
			try {
				regl.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

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
