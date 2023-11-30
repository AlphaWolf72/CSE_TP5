import java.util.concurrent.Semaphore;

public class SalleProjection {

    private Cinema cinema;
    private static final int PLACES = 100; // Nombre de places dans la salle
    public enum EtatSalle { CLOSED, OPEN, PROJECTING, EXITING, CLEANING }

    private EtatSalle etatSalle = EtatSalle.CLOSED;

    private Semaphore semaphore;

    private int nbClients = 0;

    public int getNbClients() {
        return nbClients;
    }

    public void addClient() {
        nbClients++;
        cinema.addClient();
    }

    public void delClient() {
        nbClients--;
    }

    public int getPlaces() {
        return PLACES;
    }

    public EtatSalle getEtatSalle() {
        return etatSalle;
    }

    public void setEtatSalle(EtatSalle etatSalle) {
        this.etatSalle = etatSalle;
    }

    public SalleProjection(Cinema cinema) {
        this.cinema = cinema;
        this.semaphore = new Semaphore(1);
    }
}
