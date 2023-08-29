package arsw.threads;

public class CanodromoException extends Exception {
    public static final String WIN = "ganador: ";
   
    /**
     * Lanza las exepciones creadas.
     * @param Recibe un mensaje String msm
    */
    public CanodromoException(String msm){
        super(msm);
    } 
}
