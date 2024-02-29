package Model;

public class Client {
    private int id;
    private String nume;
    private String adresa;
    private String email;
    private int varsta;

    public Client(int id, String nume, String adresa, String email, int varsta ) {
        this.id=id;
        this.nume=nume;
        this.adresa=adresa;
        this.email=email;
        this.varsta=varsta;
    }
    public Client(){
        super();
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getEmail() {
        return email;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    /**
     *
     * @return
     */
    public String[] getFieldTable() {
        String[]fields = new String[5];
        fields[0]= String.valueOf(id);
        fields[1]=nume;
        fields[2]=adresa;
        fields[3]=email;
        fields[4]=String.valueOf(varsta);
        return fields;
    }
}
