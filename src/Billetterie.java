import java.util.concurrent.Semaphore;

public class Billetterie {
    private int nbBillets;

    private Semaphore semaphore;

    /**
     * Constructeur de la billetterie
     * @param salleProjection la salle de projection
     */
    public Billetterie(SalleProjection salleProjection){
        this.nbBillets = salleProjection.getPlaces()*10;
        this.semaphore = new Semaphore(1); // Un seul client peut acheter un billet à la fois
    }

    /**
     * Vend un billet si il en reste
     * @return true si le billet a été vendu, false sinon
     */
    public boolean vendreBillet() {
        try {
            semaphore.acquire();
            if (nbBillets > 0) {
                nbBillets--;
                System.out.println(Thread.currentThread().getName() + " à acheté un billet. Il reste " + nbBillets + " billets.");
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
