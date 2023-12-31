
package gestion.interfaceG;

import gestion.stock.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import gestion.interfaceG.*;        


import java.sql.ResultSet;
import javax.swing.ImageIcon;

public class Utilisateur extends javax.swing.JFrame {

   
    ResultSet rslt=null;
    BDD base_donne;
    
    public Utilisateur() throws SQLException, ClassNotFoundException {
        base_donne=new BDD();
        initComponents();
        
         ImageIcon icon = new ImageIcon("photos/logo.jpg");
        setIconImage(icon.getImage());
        this.setBounds(400, 300, 950, 550);
        this.setResizable(false);
        base_donne.ouvrirLaConnexion();
        afficherTable();
        
        
        
        
          
    }
   public void afficherTable() throws ClassNotFoundException, SQLException {
    String[] colonnes = {"id","id_user","username","password","type"};
    
    // Récupérer le ResultSet à partir de votre méthode RecupererDonneTableFiltrePar()
    ResultSet resultSet = base_donne.RecupererDonneTableFiltrePar(colonnes, "user");
    
    
    // Créer un modèle de tableau avec le ResultSet
    ResultSetTableModel tableModel = new ResultSetTableModel(resultSet);
    
    // Définir le modèle de tableau sur le composant table_user
    table_user.setModel(new ResultSetTableModel(resultSet));
}
    public void ChampsActual(){
    id_user_field.setText("");
    username_field.setText("");
    password_field.setText("");
    
     
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_user = new javax.swing.JTable();
        Ajouterbtn = new javax.swing.JButton();
        Modifierbtn = new javax.swing.JButton();
        SupprimerBtn = new javax.swing.JButton();
        ActualiserBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        id_user_field = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        username_field = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        password_field = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        recherche_cmbx = new javax.swing.JComboBox<>();
        search_field = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        recherche_Btn = new javax.swing.JButton();
        type_field = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(java.awt.Color.orange);
        jPanel2.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Utilisateur");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(90, 10, 678, 57);

        table_user.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "id_user", "Username", "Password", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_userMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_user);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(20, 80, 911, 138);

        Ajouterbtn.setText("Ajouter");
        Ajouterbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjouterbtnActionPerformed(evt);
            }
        });
        jPanel2.add(Ajouterbtn);
        Ajouterbtn.setBounds(20, 220, 200, 50);

        Modifierbtn.setText("Modifier");
        Modifierbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifierbtnActionPerformed(evt);
            }
        });
        jPanel2.add(Modifierbtn);
        Modifierbtn.setBounds(230, 220, 200, 50);

        SupprimerBtn.setText("Supprimer");
        SupprimerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupprimerBtnActionPerformed(evt);
            }
        });
        jPanel2.add(SupprimerBtn);
        SupprimerBtn.setBounds(470, 220, 200, 50);

        ActualiserBtn.setText("Actualiser");
        ActualiserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualiserBtnActionPerformed(evt);
            }
        });
        jPanel2.add(ActualiserBtn);
        ActualiserBtn.setBounds(710, 220, 200, 50);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 280));

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setLayout(null);

        jLabel3.setText("Id_user");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(30, 20, 100, 30);

        id_user_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_user_fieldActionPerformed(evt);
            }
        });
        jPanel1.add(id_user_field);
        id_user_field.setBounds(130, 20, 84, 26);

        jLabel4.setText("Username");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(30, 70, 80, 30);
        jPanel1.add(username_field);
        username_field.setBounds(130, 70, 84, 26);

        jLabel5.setText("Password");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 120, 100, 30);

        password_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                password_fieldActionPerformed(evt);
            }
        });
        jPanel1.add(password_field);
        password_field.setBounds(130, 110, 84, 26);

        jLabel2.setText("Type");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 160, 100, 30);

        recherche_cmbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id_user", "username", "type" }));
        jPanel1.add(recherche_cmbx);
        recherche_cmbx.setBounds(760, 30, 150, 26);

        search_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_fieldActionPerformed(evt);
            }
        });
        jPanel1.add(search_field);
        search_field.setBounds(770, 110, 150, 26);

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Rechercher par");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(570, 30, 150, 30);

        recherche_Btn.setText("Lancer la recherche");
        recherche_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recherche_BtnActionPerformed(evt);
            }
        });
        jPanel1.add(recherche_Btn);
        recherche_Btn.setBounds(560, 90, 180, 50);

        type_field.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "directeur", "agent" }));
        type_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                type_fieldActionPerformed(evt);
            }
        });
        jPanel1.add(type_field);
        type_field.setBounds(130, 160, 90, 26);

        jButton1.setText("Retour");
        jButton1.setMaximumSize(new java.awt.Dimension(169, 29));
        jButton1.setMinimumSize(new java.awt.Dimension(169, 29));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(300, 140, 170, 50);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 950, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void recherche_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recherche_BtnActionPerformed
        // TODO add your handling code here:
        if (search_field.getText().equals("")){
        JOptionPane.showMessageDialog(this,"veuillez entrez quelque hose");    
        }else{
            if (recherche_cmbx.getSelectedItem().equals("id_user")){
                try {
                  rslt= base_donne.RecupererDonneTableFiltre("user","id_user LIKE'%"+search_field.getText()+"%'");
                   table_user.setModel(new ResultSetTableModel(rslt));
                  
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
            }else if(recherche_cmbx.getSelectedItem().equals("username")){
                try {
                  rslt= base_donne.RecupererDonneTableFiltre("user","username LIKE'%"+search_field.getText()+"%'");
                   table_user.setModel(new ResultSetTableModel(rslt));
                  
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }}else if(recherche_cmbx.getSelectedItem().equals("type")){
                try {
                  rslt= base_donne.RecupererDonneTableFiltre("user","type LIKE'%"+search_field.getText()+"%'");
                   table_user.setModel(new ResultSetTableModel(rslt));
                  
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }}
            
        
        
        }
        
        
        
        
    }//GEN-LAST:event_recherche_BtnActionPerformed

    private void SupprimerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupprimerBtnActionPerformed
        // TODO add your handling code here:
        String id= String.valueOf(table_user.getValueAt(table_user.getSelectedRow(),0));
        if(JOptionPane.showConfirmDialog(this,"êtes-vous Sûr de supprimer","warning",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
            base_donne.Delete("user","id ='"+id+"'");
                
        
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

        
        
        
        
    }//GEN-LAST:event_SupprimerBtnActionPerformed

    private void ModifierbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifierbtnActionPerformed
        // TODO add your handling code here:
        if(id_user_field.getText().equals("") || username_field.getText().equals("") || password_field.getText().equals("")){
            JOptionPane.showMessageDialog(this,"svp Veuillez Renseignez toute les champs");
           }    
        else{
            String[] colonnes={"id_user","username","password","type"};
            
            String[] donne={id_user_field.getText(),username_field.getText(),
                password_field.getText(),
                type_field.getSelectedItem().toString()};
            String id= String.valueOf(table_user.getValueAt(table_user.getSelectedRow(),0));
            base_donne.updateDonneTable("user", colonnes,donne,"id='"+id+"'");
            
            
            
            
            
            ChampsActual();
            try {
                
                
                afficherTable();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this,"Ok l'utilisateur est modifié");
        }
        
    }//GEN-LAST:event_ModifierbtnActionPerformed

    private void AjouterbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjouterbtnActionPerformed
        // TODO add your handling code here:
        if(id_user_field.getText().equals("") || username_field.getText().equals("") || password_field.getText().equals("")){
            JOptionPane.showMessageDialog(this,"svp Veuillez Renseignez toute les champs");
           }    
        else{
            String[] colonnes={"id_user","username","password","type"};
            
            String[] donne={id_user_field.getText(),username_field.getText(),
                password_field.getText(),
                type_field.getSelectedItem().toString()};
            base_donne.InsererParColns("user", colonnes,donne);
            
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

    private void search_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_fieldActionPerformed

    private void ActualiserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualiserBtnActionPerformed
        // TODO add your handling code here:
           ChampsActual();
            try {
                
                afficherTable();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        
    }//GEN-LAST:event_ActualiserBtnActionPerformed

    private void id_user_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_user_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_user_fieldActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
        
        
    }//GEN-LAST:event_formWindowOpened

    private void table_userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_userMouseClicked
        // TODO add your handling code here:
        id_user_field.setText(String.valueOf(table_user.getValueAt(table_user.getSelectedRow(),1)));
        username_field.setText(String.valueOf(table_user.getValueAt(table_user.getSelectedRow(),2)));
        password_field.setText(String.valueOf(table_user.getValueAt(table_user.getSelectedRow(),3)));
        type_field.setSelectedItem(String.valueOf(table_user.getValueAt(table_user.getSelectedRow(),1)));
    
    
        
        
    }//GEN-LAST:event_table_userMouseClicked

    private void password_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_password_fieldActionPerformed

    private void type_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_type_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_type_fieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DIG n= new DIG();n.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Utilisateur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Utilisateur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Utilisateur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Utilisateur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Utilisateur().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ActualiserBtn;
    private javax.swing.JButton Ajouterbtn;
    private javax.swing.JButton Modifierbtn;
    private javax.swing.JButton SupprimerBtn;
    private javax.swing.JTextField id_user_field;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField password_field;
    private javax.swing.JButton recherche_Btn;
    private javax.swing.JComboBox<String> recherche_cmbx;
    private javax.swing.JTextField search_field;
    private javax.swing.JTable table_user;
    private javax.swing.JComboBox<String> type_field;
    private javax.swing.JTextField username_field;
    // End of variables declaration//GEN-END:variables
}
