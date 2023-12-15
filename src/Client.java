public class Client extends Thread {

    private Cinema cinema;

    /**
     * Constructeur du client
     * @param cinema le cinéma
     */
    public Client(Cinema cinema) {
        this.cinema = cinema;
    }

    /**
     * Méthode run
     * Le client achète un billet et entre dans la salle de projection
     * S'il n'y a plus de billets, il rentre chez lui
     * S'il y a des billets, il entre dans la salle de projection, regarde le film et quitte la salle
     * S'il ne peux pas entrer dans la salle, il attend la prochaine séance
     * Une vois qu'il a vu le film, il rentre chez lui
     */
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
