import java.util.concurrent.Semaphore;

public class SalleProjection {

    private Cinema cinema;
    private static final int PLACES = 100; // Nombre de places dans la salle
    public enum EtatSalle { CLOSED, OPEN, PROJECTING, EXITING, CLEANING }

    private EtatSalle etatSalle = EtatSalle.CLOSED;

    private Semaphore semaphore = new Semaphore(1);

    private int nbClients = 0;

    /**
     * Retourne le nombre de clients dans la salle
     * @return
     */
    public int getNbClients() {
        return nbClients;
    }

    /**
     * Ajoute un client dans la salle
     */
    public void addClient() {
        nbClients++;
        //System.out.println("Il y a " + nbClients + " clients dans la salle.");
    }

    /**
     * Retire un client de la salle
     */
    public void delClient() {
        nbClients--;
        //System.out.println("Il y a " + nbClients + " clients dans la salle.");
    }

    /**
     * Retourne le nombre de places dans la salle
     * @return
     */
    public int getPlaces() {
        return PLACES;
    }

    /**
     * Retourne l'état de la salle
     * @return
     */
    public EtatSalle getEtatSalle() {
        return etatSalle;
    }

    /**
     * Modifie l'état de la salle
     * @param etatSalle
     */
    public void setEtatSalle(EtatSalle etatSalle) {
        this.etatSalle = etatSalle;
    }

    /**
     * Constructeur de la salle de projection
     * @param cinema le cinéma
     */
    public SalleProjection(Cinema cinema) {
        this.cinema = cinema;
        this.semaphore = new Semaphore(1);
    }

    /**
     * Un client entre dans la salle
     */
    synchronized void entrerSalle(){
        while (cinema.getSalleProjection().getNbClients() >= cinema.getSalleProjection().getPlaces() || cinema.getSalleProjection().getEtatSalle() != SalleProjection.EtatSalle.OPEN){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        cinema.getSalleProjection().addClient();
        System.out.println(Thread.currentThread().getName() + " est entré dans la salle.");}

    /**
     * Un client sort de la salle
     */
    synchronized void quitterSalle(){
        while (cinema.getSalleProjection().getEtatSalle() != SalleProjection.EtatSalle.EXITING){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        cinema.getSalleProjection().delClient();
        System.out.println(Thread.currentThread().getName() + " est sorti de la salle.");
        if(cinema.getSalleProjection().getNbClients() == 0){
            notifyAll();
        }
    }

    /**
     * L'employé ouvre la salle
     */
    synchronized void ouvrirSalle() throws InterruptedException {
        // Simulation de l'ouverture de la salle (état OPEN)
        cinema.getSalleProjection().setEtatSalle(SalleProjection.EtatSalle.OPEN);
        System.out.println("L'employe ouvre la salle");
        notifyAll();
    }

    /**
     * L'employé lance la projection
     */
    synchronized void lancerProjection() throws InterruptedException {
        // Simulation de la projection (état PROJECTING)
        System.out.println("Il y a " + cinema.getSalleProjection().getNbClients() + " clients dans la salle.");
        cinema.getSalleProjection().setEtatSalle(SalleProjection.EtatSalle.PROJECTING);
        System.out.println("L'employe lance la séance");
    }

    /**
     * Les spectateurs quittent la salle
     */
    synchronized void viderSalle() {
        // Simulation de la sortie des spectateurs (état EXITING)
        cinema.getSalleProjection().setEtatSalle(SalleProjection.EtatSalle.EXITING);
        System.out.println("Les clients quittent la salle");
        notifyAll();
    }

    /**
     * L'employé nettoie la salle
     */
    synchronized void nettoyerSalle() throws InterruptedException {
        while (cinema.getSalleProjection().getNbClients() != 0){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        cinema.getSalleProjection().setEtatSalle(SalleProjection.EtatSalle.CLEANING);
        System.out.println("L'employe nettoie la salle");
    }
}
