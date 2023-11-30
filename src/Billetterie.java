import java.util.concurrent.Semaphore;

public class Billetterie {
    private int nbBillets;

    private Semaphore semaphore;

    public Billetterie(SalleProjection salleProjection){
        this.nbBillets = salleProjection.getPlaces()*10;
        this.semaphore = new Semaphore(1); // Un seul client peut acheter un billet à la fois
    }

    public boolean vendreBillet() {
        try {
            semaphore.acquire();
            if (nbBillets > 0) {
                nbBillets--;
                return true;
            } else {
                return false; // Plus de billets disponibles
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } finally {
            semaphore.release();
        }
    }
}
