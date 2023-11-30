public class Employe extends Thread {
    private Cinema cinema;

    public Employe(Cinema cinema) {
        this.cinema = cinema;
    }

    private void ouvrirSalle() throws InterruptedException {
        // Simulation de l'ouverture de la salle (état OPEN)
        cinema.getSalleProjection().setEtatSalle(SalleProjection.EtatSalle.OPEN);
        sleep(3000);
    }

    private void lancerProjection() throws InterruptedException {
        // Simulation de la projection (état PROJECTING)
        cinema.getSalleProjection().setEtatSalle(SalleProjection.EtatSalle.PROJECTING);
        sleep(5000);
    }

    private void viderSalle() {
        // Simulation de la sortie des spectateurs (état EXITING)
        cinema.getSalleProjection().setEtatSalle(SalleProjection.EtatSalle.EXITING);
    }

    private void nettoyerSalle() throws InterruptedException {
        // Simulation du nettoyage de la salle (état CLEANING)
        while (cinema.getSalleProjection().getNbClients() > 0) {
            // Attendre que tous les clients soient sortis
        }
        cinema.getSalleProjection().setEtatSalle(SalleProjection.EtatSalle.CLEANING);
        sleep(2000);
    }

    @Override
    public void run() {
        while (cinema.getCurrentClient() > 0) {
            try {
                ouvrirSalle();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                lancerProjection();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            viderSalle();
            try {
                nettoyerSalle();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
