/*
 * contient les données à afficher
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author helen
 */
public class ecoleDAO extends DAO<ecole> {
    private PreparedStatement findOne;
    private PreparedStatement findAll;
    private PreparedStatement create;


    public ecoleDAO(Connection conn) throws SQLException {
        super(conn);
        findOne = this.connect.prepareStatement("SELECT ");
        findAll = this.connect.prepareStatement("SELECT * FROM ecole");
        create = this.connect.prepareStatement("INSERT INTO ecole (nom_ecole, adresse) VALUES (?, ?)");
    }

    @Override
    @SuppressWarnings("empty-statement")
    public boolean create(ecole obj) {
        try {
            //create = this.connect.getConnect().prepareStatement("INSERT INTO bulletin (appreciation, id_trimestre, id_inscription) VALUES (?, ?, ?)")
            create.setObject(1, obj.getNom());
            create.setObject(2, obj.getAdresse());

            create.executeUpdate();
            System.out.println("COUCOU : ecole créé !!");
            
        } catch (SQLException sql) {
            sql.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(ecole obj) {
        try {
            PreparedStatement delete = this.connect.prepareStatement("DELETE FROM ecole WHERE id_ecole = " + obj.getID());         
            delete.executeUpdate();
            System.out.println("ecole supprimée !");
        } catch (SQLException ex) {
            Logger.getLogger(bulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ecole PAS supprimée ! ");
            return false;
        }
        return true;
    }

    @Override
    public boolean update(ecole obj) {
        String update = "UPDATE ecole SET ";
      boolean succeed = false;
      
        if("".equals(obj.getNom())){
        } 
        else {
            update += "nom_ecole= "+"'" +obj.getNom()+"'" ;
            succeed = true;
            if(!("".equals(obj.getAdresse())))
            {
                update += " , ";     
            }
        }
        if(!("".equals(obj.getAdresse()))){
            update += "adresse = '" + obj.getAdresse() + "'"; 
            succeed = true;
        }
        
        //s'il y a quelque chose à changer
        if(succeed == true)
        {
            update += " WHERE id_ecole = " + obj.getID();
            try {
                PreparedStatement updateStm = this.connect.prepareStatement(update);         
                updateStm.executeUpdate();
                System.out.println("ecole modifié !");
            } catch (SQLException ex) {
                Logger.getLogger(detailbulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
                succeed = false;
            }  
        } 
    return succeed;
    }

    @Override
    public ecole find(int id) {
        ecole o = new ecole();
        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM ecole WHERE id_ecole = " + id);
            if (result.first())
                o = new ecole(
                        id,
                        result.getString("nom_ecole"),
                        result.getString("adresse")
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public ArrayList<ecole> findAll() {
       ArrayList<ecole> maListe = new ArrayList<>();

        int id = 0;
        String nom = "";
        String adresse = "";
        
        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM ecole");
            while(result.next())
            {
                id = result.getInt("id_ecole");
                nom = result.getString("nom_ecole");
                adresse = result.getString("adresse");
                ecole newBulletin = new ecole(id, nom, adresse);
                maListe.add(newBulletin);
            }   
        } catch (SQLException ex) {
            Logger.getLogger(ecoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maListe; 
    }

    @Override
    public ArrayList<ecole> rechercher(String parametreTable, String parametre) {
        ecole b = new ecole();
        ArrayList<ecole> bb = new ArrayList<>();
    
    try {
      
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM ecole WHERE " + parametreTable + " LIKE " + "'" + parametre + "'");
      
        while(result.next())
        {    
            b = new ecole(result.getInt("id_ecole"), result.getString("nom_ecole"),result.getString("adresse") ); 
            bb.add(b);
        }
        } catch (SQLException e) {
         e.printStackTrace();
        }
        return bb;
    }

    @Override
    public ArrayList<ecole> rechercher(String parametreTable, int parametre) {
        ecole b = new ecole();
        ArrayList<ecole> bb = new ArrayList<>();
    
    try {
      
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM ecole WHERE " + parametreTable + " LIKE " + "'" + parametre + "'");
      
        while(result.next())
        {    
            b = new ecole(result.getInt("id_ecole"), result.getString("nom_ecole"),result.getString("adresse") ); 
            bb.add(b);
        }
        } catch (SQLException e) {
         e.printStackTrace();
        }
        return bb;
    }
}