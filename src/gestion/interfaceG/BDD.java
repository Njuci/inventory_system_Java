/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.interfaceG;

/**
 *
 * @author Administrator
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
public class BDD {

    private static String hote="127.0.0.1";
    private static String database="gest_stock";
    private static String userName="root";
    private static String pwd="";
    String Sql;
    private String URL="jdbc:mysql://"+hote+":3306/";

    public BDD() {
    }

    private Connection connexion=null;
    public Connection ouvrirLaConnexion()  throws SQLException, ClassNotFoundException {
       
        connexion= DriverManager.getConnection(this.URL+database,userName,pwd);
        return connexion;
    }




    public String createFacture() throws SQLException {
        PreparedStatement statement = connexion.prepareStatement("INSERT INTO facture (id) VALUES (DEFAULT)", Statement.RETURN_GENERATED_KEYS);
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        long id = resultSet.getLong(1);
        String formattedId = String.format("fac%05d", id);
        String id_str= String.valueOf(id);
        String [] contenu= {formattedId};
        String [] colone={"fac_num"};
        this.updateDonneTable("facture",colone ,contenu , "id='"+id_str+"'");
        return formattedId;
    }



    public Connection ConnectDB(){
        try{
        
            connexion=DriverManager.getConnection(this.URL+database,userName,pwd);
            System.out.println("mec");

        }catch (Exception e){
            System.err.println(e.getMessage());

        }
        return connexion;
    }
    public ResultSet RecupererDonne(String query) throws SQLException, ClassNotFoundException {
        ouvrirLaConnexion();
        ResultSet rslt=null;

        System.out.println(query);

        try {
            Statement access;
                  System.out.println(query);
            access = connexion.createStatement();
            rslt=access.executeQuery(query);


            System.out.println(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return rslt;
    }
    public String UpdateDonne(String query){
   ConnectDB();
   String rslt= "";   
   Statement st;
   try{
       
   st=connexion.createStatement();
   st.executeUpdate(query);
   rslt=query;
   System.out.println(query);   
   }catch(SQLException e){
   System.err.println(e.getMessage());
   rslt=e.toString();}
    return rslt;
   }

    public ResultSet RecupererDonneTableFiltre(String table,String param) throws ClassNotFoundException{
     String   Sql="SELECT *FROM "+ table+" WHERE " +param;

        try {
            return this.RecupererDonne(Sql);
        } catch (SQLException ex) {
            Logger.getLogger(BDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ResultSet RecupererDonneTableFiltrePar(String[] nomC ,String table) throws ClassNotFoundException{
       Sql="SELECT ";
    for (int i = 0; i <= nomC.length - 1; i++) {
        Sql += nomC[i];
        if (i < nomC.length -1){
        Sql += ",";
        }
        }
    Sql += " FROM " + table;

       System.out.println(Sql);
       try {
           return this.RecupererDonne(Sql);
       } catch (SQLException ex) {
           Logger.getLogger(BDD.class.getName()).log(Level.SEVERE, null, ex);
       }
        return null;
   
   }   
    public ResultSet RecupererDonneTableFiltreParEtat(String[] nomC ,String table,String param) throws SQLException, ClassNotFoundException{
       Sql="SELECT ";
    for (int i = 0; i <= nomC.length - 1; i++) {
        Sql += nomC[i];
        if (i < nomC.length -1){
        Sql += ",";
        }
        }
    Sql += " FROM " + table +" WHERE "+param;

       System.out.println(Sql);
       return this.RecupererDonne(Sql);
   }
      
public String Inserer(String table,String[] contenuTable ){
       Sql="INSERT INTO "+table +" VALUES(";
    for (int i = 0; i <= contenuTable.length - 1; i++) {
        Sql += "'" +contenuTable[i]+"'";
        if (i < contenuTable.length -1){
        Sql += ",";
        }
        }
       Sql += ")";

       System.out.println(Sql);
       return this.UpdateDonne(Sql);
   }
      
public String InsererParColns(String table,String[] ncol,String[] contenuTable ){
       Sql="INSERT INTO "+table +"(";
       for (int i = 0; i <= ncol.length - 1; i++) {
        Sql += ncol[i];
        if (i < ncol.length -1){
        Sql += ",";
        }
        }      
      Sql += ") VALUES(";
    for (int i = 0; i <= contenuTable.length - 1; i++) {
        Sql += "'" +contenuTable[i]+"'";
        if (i < contenuTable.length -1){
        Sql += ",";
        }
        }
       Sql += ")";

       System.out.println(Sql);
       return this.UpdateDonne(Sql);
   }
public String updateDonneTable(String table,String[] ncol,String[] contenuTable,String param ){
    Sql = "UPDATE " +table+" SET ";
    for (int i = 0; i <= ncol.length - 1; i++) {
        Sql += ncol[i] +"='"+contenuTable[i]+"'";
        System.out.println(Sql);
        if (i < ncol.length -1){
        Sql += ",";
        }}
    Sql += "WHERE " + param;
     System.out.println(Sql);
    return this.UpdateDonne(Sql);
}
   public String Delete(String table,String param){
      
       Sql="DELETE FROM "+ table +" WHERE "+ param;
         System.out.println(Sql);
    return this.UpdateDonne(Sql);}

   
   


}