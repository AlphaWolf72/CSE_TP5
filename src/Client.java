public class Client extends Thread {

    private Cinema cinema;

    public Client(Cinema cinema) {
        this.cinema = cinema;
    }



    @Override
    public void run() {
        if (cinema.getBilletterie().vendreBillet()) {
            cinema.getSalleProjection().entrerSalle();
            cinema.getSalleProjection().quitterSalle();
        } else {
            System.out.println(Thread.currentThread().getName() + " rentre chez lui. Plus de billets disponibles.");
        }
    }
}
