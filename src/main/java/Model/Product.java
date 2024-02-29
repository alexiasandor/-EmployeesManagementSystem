package Model;

public class Product {
    private int id;
    private String nume;
    private int pret;
    private int cantitate;

    public Product(int id, String nume, int pret, int cantitate) {
        this.id = id;
        this.nume = nume;
        this.pret = pret;
        this.cantitate = cantitate;
    }

    public Product(){
        this.id = 0;
        this.nume = "";
        this.pret = 0;
        this.cantitate = 0;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    /**
     *
     * @return
     */
    public String[] getFieldTable() {
        String[]fields = new String[4];
        fields[0]= String.valueOf(id);
        fields[1]=nume;
        fields[2]=String.valueOf(pret);
        fields[3]=String.valueOf(cantitate);

        return fields;
    }
}
