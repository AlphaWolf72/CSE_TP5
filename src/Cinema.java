public class Cinema {
    private static final int CLIENTS = 300; // Nombre de clients

    private SalleProjection salleProjection;
    private Billetterie billetterie;

    /**
     * Retourne la salle de projection
     * @return la salle de projection
     */
    public SalleProjection getSalleProjection() {
        return salleProjection;
    }

    /**
     * Retourne la billetterie
     * @return la billetterie
     */
    public Billetterie getBilletterie() {
        return this.billetterie;
    }

    /**
     * Méthode main
     */
    public static void main(String[] args) {

        Cinema cinema = new Cinema();

        SalleProjection salleProjection = new SalleProjection(cinema);
        Billetterie billetterie = new Billetterie(salleProjection);

        cinema.salleProjection = salleProjection;
        cinema.billetterie = billetterie;

        // Créer des clients et les démarrer dans des threads distincts
        for (int i = 1; i <= CLIENTS; i++) {
            new Client(cinema).start();
        }

        // Créer et démarrer le thread de l'employé
        Employe employe = new Employe(cinema);
        employe.setDaemon(true);
        employe.start();
    }
}
