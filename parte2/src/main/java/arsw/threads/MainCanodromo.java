package arsw.threads;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MainCanodromo {

    private static Galgo[] galgos;

    private static Canodromo can;

    private static boolean iterar;

    private static RegistroLlegada reg = new RegistroLlegada();

    public static void main(String[] args) {
        can = new Canodromo(17, 100);
        galgos = new Galgo[can.getNumCarriles()];
        can.setVisible(true);

        // Acción del botón start
        can.setStartAction(
                new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        // como acción, se crea un nuevo hilo que cree los hilos
                        // 'galgos', los pone a correr, y luego muestra los resultados.
                        // La acción del botón se realiza en un hilo aparte para evitar
                        // bloquear la interfaz gráfica.
                        ((JButton) e.getSource()).setEnabled(false);
                        new Thread() {
                            public void run() {
                                iterar = true;
                                for (int i = 0; i < can.getNumCarriles(); i++) {
                                    // crea los hilos 'galgos'
                                    galgos[i] = new Galgo(can.getCarril(i), "" + i, reg);
                                    // inicia los hilos
                                    galgos[i].start();
                                }
                                while (iterar) {
                                    if (!(galgos[galgos.length - 1].isAlive())) {
                                        System.out.println("El ganador fue:" + reg.getGanador());
                                        iterar = false;
                                        can.winnerDialog(reg.getGanador(), reg.getUltimaPosicionAlcanzada() - 1);
                                    }
                                }
                            }
                        }.start();
                    }
                });

        can.setStopAction(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object lock = new Object();
                        iterar = false;
                        synchronized (lock) {
                            try {
                                while (!iterar) {
                                    lock.wait();
                                }
                            } catch (InterruptedException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
                        System.out.println("Carrera pausada!");
                    }

                });

        can.setContinueAction(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object inLock = new Object();
                        iterar = true;
                        synchronized (inLock) {
                            inLock.notifyAll();
                        }
                        System.out.println("Carrera reanudada!");
                    }
                });

    }

}
