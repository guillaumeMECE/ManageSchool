/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author helen
 */
public class trimestreDAO extends DAO<trimestre>{
     private PreparedStatement findOne;
    private PreparedStatement findAll;
    private PreparedStatement create;
    
    
    public trimestreDAO(Connection conn) throws SQLException {
    super(conn);

        findOne = this.connect.prepareStatement("SELECT ");
        findAll = this.connect.prepareStatement("SELECT * FROM trimestre");
        create = this.connect.prepareStatement("INSERT INTO trimestre (numero, debut, fin, id_anneScolaire) VALUES (?, ?, ?, ?)");
    }

    @Override
    @SuppressWarnings("empty-statement")
  public boolean create(trimestre obj) {
     try{
        create.setObject(1, obj.getNum());
        create.setObject(2, obj.getDebut());
        create.setObject(3, obj.getFin());
        create.setObject(4, obj.getAnneeScolaire());
        
        create.executeUpdate();
        System.out.println("COUCOU : trimestre créé !!");
      }
     catch(SQLException sql){
         sql.printStackTrace();
         //this.closeStatements();
         return false;        
      }
    //this.closeStatements();
    return true;
  }

    @Override
  public boolean delete(trimestre obj) {
    try {
            PreparedStatement delete = this.connect.prepareStatement("DELETE FROM trimestre WHERE id = " + obj.getID());         
            delete.executeUpdate();
            System.out.println("trimestre supprimée !");
        } catch (SQLException ex) {
            Logger.getLogger(trimestreDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("trimestre PAS supprimée ! ");
            return false;
        }
        return true;
  }
   
    @Override
  public boolean update(trimestre obj) {
    return false;
  }

    @Override
    public trimestre find(int id) {
        personne o = new personne();

        try {

            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM trimestre WHERE id = " + id);
            if(result.first())
                o = new trimestre(
                        id,
                        result.getInt("numero"),
                        result.getDate("debut"),
                        result.getDate("fin"),
                        result.getInt("id_anneeScolaire")
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }
    
}
