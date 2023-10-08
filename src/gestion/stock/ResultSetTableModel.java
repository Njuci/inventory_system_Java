package gestion.stock;
import javax.swing.table.AbstractTableModel;
import java.sql.*;
public class ResultSetTableModel extends AbstractTableModel {
    private ResultSet rslt=null;
    public ResultSetTableModel (ResultSet rslt){
    this.rslt=rslt;
    fireTableDataChanged();
    }
    public int getColumnCount(){
    try{
        if (rslt==null){
            return 0;
        }else{
            return rslt.getMetaData().getColumnCount();}
    
    }catch( SQLException e){
    System.out.println(e.getMessage());
    return 0;}
    }
    public int getRowCount() {
    try {
        if (rslt == null) {
            return 0;
        } else {
            rslt.last();
            return rslt.getRow();
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return 0;
    }
}

    public Object getValueAt( int ligne,int colonne) {
    if (ligne < 0 || ligne > getRowCount() || colonne < 0 || colonne > getColumnCount()) {
        return null;
    }
    try {
        if (rslt == null) {
            return null;
        } else {
                rslt.absolute(ligne+1);
                return rslt.getObject(colonne+1);}
                     
            
        
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return null;
    }
}
     
         @Override
     public String getColumnName(int colonne){
     try{
         return rslt.getMetaData().getColumnName(colonne + 1);
     
     }catch( SQLException e){
    System.out.println(e.getMessage()); 
    }
     return super.getColumnName(colonne);
     }}             
