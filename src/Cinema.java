public class Cinema {
    private static final int CLIENTS = 1200; // Nombre de clients

    private SalleProjection salleProjection;
    private Billetterie billetterie;

    private int currentClient;

    Cinema() {
        this.currentClient = 0;
    }

    public SalleProjection getSalleProjection() {
        return salleProjection;
    }

    public Billetterie getBilletterie() {
        return this.billetterie;
    }

    public int getCurrentClient() {
        return currentClient;
    }

    public void addClient() {
        this.currentClient++;
    }

    public void delClient() {
        this.currentClient--;
    }


    public static void main(String[] args) {

        Cinema cinema = new Cinema();

        SalleProjection salleProjection = new SalleProjection(cinema);
        Billetterie billetterie = new Billetterie(salleProjection);

        cinema.salleProjection = salleProjection;
        cinema.billetterie = billetterie;

        // Créer des clients et les démarrer dans des threads distincts
        for (int i = 1; i <= CLIENTS; i++) {
            cinema.addClient();
            new Client("Client " + i, cinema).start();
        }

        // Créer et démarrer le thread de l'employé
        new Employe(cinema).start();
    }
}
