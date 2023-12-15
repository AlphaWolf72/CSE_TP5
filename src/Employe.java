public class Employe extends Thread {
    private Cinema cinema;

    /**
     * Constructeur de l'employé
     * @param cinema le cinéma
     */
    public Employe(Cinema cinema) {
        this.cinema = cinema;
    }

    /**
     * Méthode run
     * L'employé ouvre la salle, lance la projection, vide la salle et nettoie la salle
     */
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
