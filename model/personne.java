package model;

public abstract class personne {
    protected int id = 0;
    protected String nom = "";
    protected String prenom = "";
    protected String type_ = "";
    protected String userP = "";
    protected String mdp = "";
    protected int classe = "";

    public personne() {
    }

    public personne(int id, String name, String firstname, String type, String user, String motdp) {
        this.nom = name;
        this.prenom = firstname;
        this.id = id;
        this.type_ = type;
        this.userP = user;
        this.mdp = motdp;
    }


    public String getName() {
        return nom;
    }

    public String getFirstname() {
        return prenom;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type_;
    }

    public String getUser() {
        return userP;
    }

    public String mdp() {
        return mdp;
    }

    public int getClasse() {
        return classe;
    }

    public void setName(String name) {
        this.nom = name;
    }

    public void setFirstname(String firstname) {
        this.prenom = firstname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type_ = type;
    }

    public void setUser(String user) {
        this.userP = user;
    }

    public void setMDP(String mdp) {
        this.mdp = mdp;
    }
}
