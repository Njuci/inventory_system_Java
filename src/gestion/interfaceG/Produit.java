/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.interfaceG;

import gestion.stock.ResultSetTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class Produit extends javax.swing.JFrame {
    ResultSet rslt=null;
    BDD base_donne;
    public Produit() throws SQLException, ClassNotFoundException {
        
        base_donne=new BDD();
        
        
        initComponents();
       base_donne.ouvrirLaConnexion();
            // TODO add your handling code here:

            afficherTable();
        
        this.setSize(1000, 600);
        this.setResizable(false);
        
        
        
    }
    public void afficherTable() throws ClassNotFoundException, SQLException {
    String[] colonnes = {"id","code_produit","ref","designation","fournisseur","remise","prix_unitaire","stock"};
    
    // Récupérer le ResultSet à partir de votre méthode RecupererDonneTableFiltrePar()
    ResultSet resultSet = base_donne.RecupererDonneTableFiltrePar(colonnes, "produit");
    
    
    // Créer un modèle de tableau avec le ResultSet
    ResultSetTableModel tableModel = new ResultSetTableModel(resultSet);
    
    // Définir le modèle de tableau sur le composant table_user
    table_prod.setModel(new ResultSetTableModel(resultSet));
}
   
public void ChampsActual(){
     code_prod_field.setText("");
     fourni_field.setText("");
    desi_field.setText("");
    ref_field.setText("");
    remise_field.setText("");
    prix_unit_field.setText("");
    stock_field.setText("");
    
    
    
     
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        sup_Btn = new javax.swing.JButton();
        modifierBtn = new javax.swing.JButton();
        ActualiserBtn = new javax.swing.JButton();
        Ajouterbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        code_prod_field = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        prix_unit_field = new javax.swing.JTextField();
        fourni_field = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ref_field = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        search_field = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        remise_field = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        desi_field = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        recherche_cmbx = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_prod = new javax.swing.JTable();
        stock_field = new javax.swing.JTextField();
        recherce_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(800, 570));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        label1.setText("Approvisionnement Stock");
        getContentPane().add(label1);
        label1.setBounds(83, 16, 726, 78);

        sup_Btn.setText("Supprimer");
        sup_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sup_BtnActionPerformed(evt);
            }
        });
        getContentPane().add(sup_Btn);
        sup_Btn.setBounds(451, 237, 184, 55);

        modifierBtn.setText("Modifier");
        modifierBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifierBtnActionPerformed(evt);
            }
        });
        getContentPane().add(modifierBtn);
        modifierBtn.setBounds(249, 237, 184, 55);

        ActualiserBtn.setText("Actualiser");
        ActualiserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualiserBtnActionPerformed(evt);
            }
        });
        getContentPane().add(ActualiserBtn);
        ActualiserBtn.setBounds(670, 240, 150, 55);

        Ajouterbtn.setText("Ajouter");
        Ajouterbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjouterbtnActionPerformed(evt);
            }
        });
        getContentPane().add(Ajouterbtn);
        Ajouterbtn.setBounds(15, 237, 184, 55);

        jLabel1.setText("Code_prod");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(15, 337, 94, 34);
        getContentPane().add(code_prod_field);
        code_prod_field.setBounds(115, 341, 121, 26);

        jLabel2.setText("Forurnisseur");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 470, 94, 34);

        prix_unit_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prix_unit_fieldActionPerformed(evt);
            }
        });
        getContentPane().add(prix_unit_field);
        prix_unit_field.setBounds(360, 380, 121, 26);

        fourni_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fourni_fieldActionPerformed(evt);
            }
        });
        getContentPane().add(fourni_field);
        fourni_field.setBounds(110, 470, 121, 26);

        jLabel3.setText("Stock");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(250, 420, 94, 34);

        ref_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ref_fieldActionPerformed(evt);
            }
        });
        getContentPane().add(ref_field);
        ref_field.setBounds(110, 380, 121, 26);

        jLabel4.setText("P.U");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(250, 380, 94, 34);
        getContentPane().add(search_field);
        search_field.setBounds(720, 400, 121, 26);

        jLabel5.setText("Designation");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(10, 420, 94, 34);
        getContentPane().add(remise_field);
        remise_field.setBounds(354, 341, 121, 26);

        jLabel6.setText("Remise");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(254, 337, 94, 34);

        jLabel7.setText("Ref");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(20, 380, 94, 34);

        desi_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desi_fieldActionPerformed(evt);
            }
        });
        getContentPane().add(desi_field);
        desi_field.setBounds(110, 420, 121, 26);

        jLabel8.setText("Rechercher par");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(530, 330, 110, 40);

        recherche_cmbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "designation", "code_produit", "fournisseur", "ref", "remise", "stock", "prix_unitaire", " " }));
        recherche_cmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recherche_cmbxActionPerformed(evt);
            }
        });
        getContentPane().add(recherche_cmbx);
        recherche_cmbx.setBounds(750, 340, 124, 26);

        table_prod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "code_produit", "ref", "designation", "fournisseur", "remise", "prix_unitaire", "stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_prod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_prodMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_prod);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 120, 870, 100);
        getContentPane().add(stock_field);
        stock_field.setBounds(360, 420, 121, 26);

        recherce_btn.setText("Lancer la recherche");
        recherce_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recherce_btnActionPerformed(evt);
            }
        });
        getContentPane().add(recherce_btn);
        recherce_btn.setBounds(520, 380, 180, 55);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ActualiserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualiserBtnActionPerformed
        try {
            // TODO add your handling code here:
            afficherTable();
            ChampsActual();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ActualiserBtnActionPerformed

    private void prix_unit_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prix_unit_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prix_unit_fieldActionPerformed

    private void fourni_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fourni_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fourni_fieldActionPerformed

    private void desi_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desi_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_desi_fieldActionPerformed

    private void modifierBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifierBtnActionPerformed
        // TODO add your handling code here:
          if(code_prod_field.getText().equals("") || desi_field.getText().equals("") 
                || ref_field.getText().equals("")|| fourni_field.getText().equals("") ||
                prix_unit_field.getText().equals("") || stock_field.getText().equals("")
                || remise_field.getText().equals("")){
            JOptionPane.showMessageDialog(this,"svp Veuillez Renseignez toute les champs");
           }    
        else{
            String[] colonnes={"code_produit","designation","ref","fournisseur","prix_unitaire","stock","remise"};
            
            String[] donne={code_prod_field.getText(),desi_field.getText(),
                ref_field.getText(),fourni_field.getText(),prix_unit_field.getText(),stock_field.getText(),remise_field.getText()
               };
            String id= String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(),0));
            base_donne.updateDonneTable("produit", colonnes,donne,"id='"+id+"'");
           
            ChampsActual();
            try {
                
                afficherTable();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this,"Ok Stock est modifié");
        }
    }//GEN-LAST:event_modifierBtnActionPerformed

    private void recherche_cmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recherche_cmbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_recherche_cmbxActionPerformed

    private void AjouterbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjouterbtnActionPerformed
        
        
        
        
        if(code_prod_field.getText().equals("") || desi_field.getText().equals("") 
                || ref_field.getText().equals("")|| fourni_field.getText().equals("") ||
                prix_unit_field.getText().equals("") || stock_field.getText().equals("")
                || remise_field.getText().equals("")){
            JOptionPane.showMessageDialog(this,"svp Veuillez Renseignez toute les champs");
           }    
        else{
            String[] colonnes={"code_produit","designation","ref","fournisseur","prix_unitaire","stock","remise"};
            
            String[] donne={code_prod_field.getText(),desi_field.getText(),
                ref_field.getText(),fourni_field.getText(),prix_unit_field.getText(),stock_field.getText(),remise_field.getText()
               };
            base_donne.InsererParColns("produit", colonnes,donne);
            
            ChampsActual();
            try {
                
                afficherTable();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this,"Ok l'utilisateur est ajouté");
        }
    }//GEN-LAST:event_AjouterbtnActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       
    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowActivated

    private void ref_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ref_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ref_fieldActionPerformed

    private void sup_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sup_BtnActionPerformed
        // TODO add your handling code here:
         String id= String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(),0));
        if(JOptionPane.showConfirmDialog(this,"êtes-vous Sûr de supprimer","warning",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
            base_donne.Delete("produit","id ='"+id+"'");
                
        
        }else{
        return;
        }
        try {
                
                
                afficherTable();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }

        
        
       
    }//GEN-LAST:event_sup_BtnActionPerformed

    private void recherce_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recherce_btnActionPerformed
        // TODO add your handling code here:
        
         if (search_field.getText().equals("")){
        JOptionPane.showMessageDialog(this,"veuillez entrez quelque hose");    
        }else{
            if (recherche_cmbx.getSelectedItem().equals("code_produit")){
                try {
                  rslt= base_donne.RecupererDonneTableFiltre("produit","code_produit LIKE'%"+search_field.getText()+"%'");
                   table_prod.setModel(new ResultSetTableModel(rslt));
                  
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
            }else if(recherche_cmbx.getSelectedItem().equals("ref")){
                try {
                  rslt= base_donne.RecupererDonneTableFiltre("produit","ref LIKE'%"+search_field.getText()+"%'");
                   table_prod.setModel(new ResultSetTableModel(rslt));
                  
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }}else if(recherche_cmbx.getSelectedItem().equals("designation")){
                try {
                  rslt= base_donne.RecupererDonneTableFiltre("produit","designation LIKE'%"+search_field.getText()+"%'");
                   table_prod.setModel(new ResultSetTableModel(rslt));
                  
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }}else if(recherche_cmbx.getSelectedItem().equals("fournisseur")){
                try {
                  rslt= base_donne.RecupererDonneTableFiltre("produit","fournisseur LIKE'%"+search_field.getText()+"%'");
                   table_prod.setModel(new ResultSetTableModel(rslt));
                  
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }}else if(recherche_cmbx.getSelectedItem().equals("remise")){
                try {
                  rslt= base_donne.RecupererDonneTableFiltre("produit","remise LIKE'%"+search_field.getText()+"%'");
                   table_prod.setModel(new ResultSetTableModel(rslt));
                  
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }}else if(recherche_cmbx.getSelectedItem().equals("prix_unitaire")){
                try {
                  rslt= base_donne.RecupererDonneTableFiltre("produit","prix_unitaire LIKE'%"+search_field.getText()+"%'");
                   table_prod.setModel(new ResultSetTableModel(rslt));
                  
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }}else if(recherche_cmbx.getSelectedItem().equals("stock")){
                try {
                  rslt= base_donne.RecupererDonneTableFiltre("produit","stock LIKE'%"+search_field.getText()+"%'");
                   table_prod.setModel(new ResultSetTableModel(rslt));
                  
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }}
            
        
        
        }
        
        
       
    }//GEN-LAST:event_recherce_btnActionPerformed

    private void table_prodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_prodMouseClicked
        // TODO add your handling code here:
         code_prod_field.setText(String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(),1)));
     fourni_field.setText(String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(),4)));
    desi_field.setText(String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(),3)));
    ref_field.setText(String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(),2)));
    remise_field.setText(String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(),5)));
    prix_unit_field.setText(String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(),6)));
    stock_field.setText(String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(),7)));
        
        
    }//GEN-LAST:event_table_prodMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Produit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Produit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Produit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Produit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Produit().setVisible(true);
                   
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ActualiserBtn;
    private javax.swing.JButton Ajouterbtn;
    private javax.swing.JTextField code_prod_field;
    private javax.swing.JTextField desi_field;
    private javax.swing.JTextField fourni_field;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Label label1;
    private javax.swing.JButton modifierBtn;
    private javax.swing.JTextField prix_unit_field;
    private javax.swing.JButton recherce_btn;
    private javax.swing.JComboBox<String> recherche_cmbx;
    private javax.swing.JTextField ref_field;
    private javax.swing.JTextField remise_field;
    private javax.swing.JTextField search_field;
    private javax.swing.JTextField stock_field;
    private javax.swing.JButton sup_Btn;
    private javax.swing.JTable table_prod;
    // End of variables declaration//GEN-END:variables

}