public class Client extends Thread {
    private String nom;

    private Cinema cinema;

    public Client(String nom, Cinema cinema) {
        this.cinema = cinema;
        this.nom = nom;
    }

    private boolean acheterBillet() {
        if(cinema.getBilletterie().vendreBillet()) {
            System.out.println(nom + " a acheté un billet.");
            return true;
        } else {
            System.out.println(nom + " n'a pas pu acheter de billet.");
            return false;
        }
    }

    private void entrerSalle() {
        if(cinema.getSalleProjection().getNbClients() < cinema.getSalleProjection().getPlaces() && cinema.getSalleProjection().getEtatSalle() == SalleProjection.EtatSalle.OPEN) {
            cinema.getSalleProjection().addClient();
            System.out.println(nom + " est entré dans la salle.");
        } else {
            System.out.println(nom + " n'a pas pu entrer dans la salle.");
        }
    }

    private void regarderFilm() {
        // Simulation du visionnage du film
        while (cinema.getSalleProjection().getEtatSalle() == SalleProjection.EtatSalle.PROJECTING) {
            // Attendre la fin du film
        }
    }

    private void quitterSalle() {
        if(cinema.getSalleProjection().getEtatSalle() == SalleProjection.EtatSalle.EXITING) {
            cinema.getSalleProjection().delClient();
            System.out.println(nom + " est sorti de la salle.");
        }
    }

    @Override
    public void run() {
        if (acheterBillet()) {
            entrerSalle();
            regarderFilm();
            quitterSalle();
        } else {
            System.out.println(nom + " rentre chez lui. Plus de billets disponibles.");
        }
        cinema.delClient();
    }
}
