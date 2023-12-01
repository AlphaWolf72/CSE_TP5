public class Employe extends Thread {
    private Cinema cinema;

    public Employe(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public void run() {
        while(true) {
            try {
                cinema.getSalleProjection().ouvrirSalle();
                Thread.sleep(3000);
                cinema.getSalleProjection().lancerProjection();
                Thread.sleep(5000);
                cinema.getSalleProjection().viderSalle();
                cinema.getSalleProjection().nettoyerSalle();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
