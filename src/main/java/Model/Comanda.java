package Model;

public class Comanda {
    private int id;
    private int idClient;
    private int idProdus;
    private int cantitate;

    public Comanda(int id, int idClient, int idProdus, int cantitate) {
        this.id = id;
        this.idClient = idClient;
        this.idProdus = idProdus;
        this.cantitate = cantitate;
    }
    public Comanda() {
        this.id = 0;
        this.idClient = 0;
        this.idProdus = 0;
        this.cantitate = 0;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "id=" + id +
                ", idClient=" + idClient +
                ", idProdus=" + idProdus +
                ", cantitate=" + cantitate +
                '}';
    }

    /**
     *
     * @return
     */
    public String[] getFieldTable() {
        String[]fields = new String[4];
        fields[0]= String.valueOf(id);
        fields[1]=String.valueOf(idClient);
        fields[2]=String.valueOf(idProdus);
        fields[3]=String.valueOf(cantitate);

        return fields;
    }
}
