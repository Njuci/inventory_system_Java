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
        
        this.setSize(955, 580);
        this.setResizable(false);
        
        
        
    }
    public void afficherTable() throws ClassNotFoundException, SQLException {
    String[] colonnes = {"id","code_produit","ref","designation","fournisseur","remise","prix_unitaire","stock","date"};
    
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

        jMenu6 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_prod = new javax.swing.JTable();
        ActualiserBtn = new javax.swing.JButton();
        sup_Btn = new javax.swing.JButton();
        modifierBtn = new javax.swing.JButton();
        Ajouterbtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        code_prod_field = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        remise_field = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        recherche_cmbx = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        ref_field = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        prix_unit_field = new javax.swing.JTextField();
        recherce_btn = new javax.swing.JButton();
        search_field = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        desi_field = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        stock_field = new javax.swing.JTextField();
        fourni_field = new javax.swing.JTextField();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        vendre_item = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        jMenu6.setText("jMenu6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.orange);
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

        jPanel1.setBackground(java.awt.Color.orange);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        label1.setText("Approvisionnement Stock");
        jPanel1.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));

        table_prod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "code_produit", "ref", "designation", "fournisseur", "remise", "prix_unitaire", "stock", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 920, 110));

        ActualiserBtn.setText("Actualiser");
        ActualiserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualiserBtnActionPerformed(evt);
            }
        });
        jPanel1.add(ActualiserBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 230, 150, 55));

        sup_Btn.setText("Supprimer");
        sup_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sup_BtnActionPerformed(evt);
            }
        });
        jPanel1.add(sup_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, 184, 55));

        modifierBtn.setText("Modifier");
        modifierBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifierBtnActionPerformed(evt);
            }
        });
        jPanel1.add(modifierBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 184, 55));

        Ajouterbtn.setText("Ajouter");
        Ajouterbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjouterbtnActionPerformed(evt);
            }
        });
        jPanel1.add(Ajouterbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 184, 55));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 950, 290);

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));

        jLabel1.setText("Code_prod");

        jLabel6.setText("Remise");

        jLabel8.setText("Rechercher par");

        recherche_cmbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "designation", "code_produit", "fournisseur", "ref", "remise", "stock", "prix_unitaire", " " }));
        recherche_cmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recherche_cmbxActionPerformed(evt);
            }
        });

        jLabel7.setText("Ref");

        ref_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ref_fieldActionPerformed(evt);
            }
        });

        jLabel4.setText("P.U");

        prix_unit_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prix_unit_fieldActionPerformed(evt);
            }
        });

        recherce_btn.setText("Lancer la recherche");
        recherce_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recherce_btnActionPerformed(evt);
            }
        });

        jLabel5.setText("Designation");

        desi_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desi_fieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Forurnisseur");

        jLabel3.setText("Stock");

        stock_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stock_fieldActionPerformed(evt);
            }
        });

        fourni_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fourni_fieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ref_field, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(desi_field, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(code_prod_field, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fourni_field, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(remise_field, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prix_unit_field, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stock_field, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(recherche_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(recherce_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(search_field, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(code_prod_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(remise_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(recherche_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(recherce_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(desi_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(ref_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fourni_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(prix_unit_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(stock_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(78, Short.MAX_VALUE))))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 290, 950, 250);

        jMenu4.setText("Menu");

        vendre_item.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        vendre_item.setText("Vendre");
        vendre_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendre_itemActionPerformed(evt);
            }
        });
        jMenu4.add(vendre_item);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Voir le Bilan ");
        jMenu4.add(jMenuItem6);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Voir les depenses");
        jMenu4.add(jMenuItem5);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Voir les vendeurs");
        jMenu4.add(jMenuItem2);

        jMenuItem1.setText("Voir ");
        jMenu4.add(jMenuItem1);

        jMenuItem4.setText("Retour ");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

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
            JOptionPane.showMessageDialog(this,"Ok le stock est ajouté");
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

    private void stock_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stock_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stock_fieldActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void vendre_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendre_itemActionPerformed
        try {
            // TODO add your handling code here:
            Cahier f=new Cahier();
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_vendre_itemActionPerformed

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
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
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
    private javax.swing.JMenuItem vendre_item;
    // End of variables declaration//GEN-END:variables

}
