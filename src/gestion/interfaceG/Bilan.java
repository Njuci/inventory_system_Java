/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestion.interfaceG;
import com.itextpdf.text.Image;
import org.apache.logging.log4j.LogManager;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.draw.LineSeparator;
import gestion.stock.ResultSetTableModel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.itextpdf.text.PageSize;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.adobe.acrobat.Viewer;
import com.itextpdf.text.BadElementException;
import static gestion.interfaceG.depenses.previewPDF;
import java.io.IOException;
/**
 *
 * @author Administrator
 */
public class Bilan extends javax.swing.JFrame {

    ResultSet rslt = null;
    BDD base_donne;
    
    public Bilan() throws ClassNotFoundException, SQLException {
        initComponents();
         ImageIcon icon = new ImageIcon("photos/logo.jpg");
        setIconImage(icon.getImage());
        base_donne=new BDD();
        afficherTableBilan();
        combox_mois_remplissage();
        this.setResizable(false);
    }
    public void combox_mois_remplissage() throws SQLException, ClassNotFoundException {
        String query="SELECT DISTINCT DATE_FORMAT(date, '%Y-%m') As moi FROM (\n" +
                "  SELECT date_vente AS date FROM vente\n" +
                "  UNION\n" +
                "  SELECT date AS date FROM depenses\n" +
                ") AS dates";
         
        rslt = base_donne.RecupererDonne(query);
        moi_field.removeAllItems();
        debut_date.removeAllItems();
        fin_date.removeAllItems();
        jour.removeAllItems();
        while (rslt.next()) {
           moi_field.addItem(rslt.getString("moi"));

        }
         String query1="SELECT DISTINCT DATE_FORMAT(date, '%Y-%m-%d') As moi FROM (\n" +
                "  SELECT date_vente AS date FROM vente\n" +
                "  UNION\n" +
                "  SELECT date AS date FROM depenses\n" +
                ") AS dates ORDER BY(date)";
         
        rslt = base_donne.RecupererDonne(query1);
         while (rslt.next()) {
           jour.addItem(rslt.getString("moi"));
           debut_date.addItem(rslt.getString("moi"));
           fin_date.addItem(rslt.getString("moi"));

        }
        
        rslt.close();

    }
public void combox_remplissage() throws SQLException, ClassNotFoundException {
        String query="SELECT DISTINCT DATE_FORMAT(date, '%Y-%m-%d') As moi FROM (\n" +
                "  SELECT date_vente AS date FROM vente\n" +
                "  UNION\n" +
                "  SELECT date AS date FROM depenses\n" +
                ") AS dates";
         
        rslt = base_donne.RecupererDonne(query);
        debut_date.removeAllItems();
        jour.removeAllItems();
        while (rslt.next()) {
           jour.addItem(rslt.getString("moi"));
           debut_date.addItem(rslt.getString("moi"));

        }
        rslt.close();

    }
    public void afficherTableBilan() throws ClassNotFoundException, SQLException {
     

    String query6="SELECT DATE_FORMAT(date, '%Y-%m-%d')AS Date, SUM(sous_total) AS Ventes, SUM(sorties) AS Depenses, SUM(sous_total) - SUM(sorties) AS Difference,\n" +
                    " SUM(SUM(sous_total) - SUM(sorties)) OVER (ORDER BY date) AS Cumul\n" +
                    "FROM (\n" +
"  SELECT  date_vente AS date, tp.sous_total, 0 AS sorties FROM vente As tp JOIN produit As td  WHERE td.devise = '$' and tp.code_produit=td.code_produit  UNION ALL\n" +
"  SELECT date, 0 AS vente, montant FROM depenses  WHERE  devise ='$' \n" +
") AS t\n" +
"GROUP BY date";

                    rslt=base_donne.RecupererDonne(query6);
        // Défianir le modèle de tableau sur le composant table_user
        table_bilan.setModel(new ResultSetTableModel(rslt));
        
    String query7="SELECT DATE_FORMAT(date, '%Y-%m-%d')AS Date, SUM(sous_total) AS Ventes, SUM(sorties) AS Depenses, SUM(sous_total) - SUM(sorties) AS Difference,\n" +
                    " SUM(SUM(sous_total) - SUM(sorties)) OVER (ORDER BY date) AS Cumul\n" +
                    "FROM (\n" +
"  SELECT  date_vente AS date, tp.sous_total, 0 AS sorties FROM vente As tp JOIN produit As td  WHERE td.devise = 'fc' and tp.code_produit=td.code_produit  UNION ALL\n" +
"  SELECT date, 0 AS vente, montant FROM depenses  WHERE  devise ='fc' \n" +
") AS t\n" +
"GROUP BY date";

                    rslt=base_donne.RecupererDonne(query7);
        // Défianir le modèle de tableau sur le composant table_user
        table_bilan_fc.setModel(new ResultSetTableModel(rslt));
        combox_mois_remplissage() ;
     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        moi_field = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        debut_date = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jour = new javax.swing.JComboBox<>();
        recher_par_moi = new javax.swing.JButton();
        interval_search = new javax.swing.JButton();
        fin_date = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        search_journey = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_bilan = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_bilan_fc = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        imprimer = new javax.swing.JButton();
        voir_lesbilans = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(java.awt.Color.orange);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bilan");

        jLabel2.setText("Par moi");

        moi_field.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        moi_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moi_fieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Date debut");

        debut_date.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        debut_date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debut_dateActionPerformed(evt);
            }
        });

        jLabel4.setText("Date fin");

        jour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jourActionPerformed(evt);
            }
        });

        recher_par_moi.setText("Bilan par moi");
        recher_par_moi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recher_par_moiActionPerformed(evt);
            }
        });

        interval_search.setText("Rechercher Par intervalle");
        interval_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                interval_searchActionPerformed(evt);
            }
        });

        fin_date.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        fin_date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fin_dateActionPerformed(evt);
            }
        });

        jLabel7.setText("Journée");

        search_journey.setText("Rechercher par Journeé");
        search_journey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_journeyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(moi_field, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(recher_par_moi)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(debut_date, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(fin_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(interval_search)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(search_journey)
                        .addGap(67, 67, 67))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(380, 380, 380)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(moi_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(debut_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fin_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recher_par_moi)
                    .addComponent(interval_search)
                    .addComponent(search_journey))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));

        table_bilan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Date ", "Ventes", "Depenses", "Cumul"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table_bilan);

        table_bilan_fc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Date ", "Ventes", "Depenses", "Cumul"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table_bilan_fc);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Bilan en Dollars Americains");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Bilan en Francs Congolais");

        imprimer.setText("Imprimer");
        imprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimerActionPerformed(evt);
            }
        });

        voir_lesbilans.setText("Voir les bilans Imprimés");
        voir_lesbilans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voir_lesbilansActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1))
                    .addComponent(voir_lesbilans))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(imprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(voir_lesbilans, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(111, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void moi_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moi_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moi_fieldActionPerformed

    private void debut_dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debut_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_debut_dateActionPerformed

    private void jourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jourActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jourActionPerformed

    private void fin_dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fin_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fin_dateActionPerformed

    private void recher_par_moiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recher_par_moiActionPerformed
        try {                                               
            // TODO add your handling code here:
            String query6="SELECT DATE_FORMAT(date, '%Y-%m-%d')AS Date, SUM(sous_total) AS Ventes, SUM(sorties) AS Depenses, SUM(sous_total) - SUM(sorties) AS Difference,\n" +
                    " SUM(SUM(sous_total) - SUM(sorties)) OVER (ORDER BY date) AS Cumul\n" +
                    "FROM (\n" +
                    "  SELECT  date_vente AS date, tp.sous_total, 0 AS sorties FROM vente As tp JOIN produit As td  WHERE td.devise = '$' and tp.code_produit=td.code_produit  UNION ALL\n" +
                    "  SELECT date, 0 AS vente, montant FROM depenses  WHERE  devise ='$'\n" +
                    ") AS t\n" +" WHERE date like '%"+ moi_field.getSelectedItem().toString()+"%'\n "+
                    "GROUP BY date";
            
                rslt=base_donne.RecupererDonne(query6);
           
            // Défianir le modèle de tableau sur le composant table_user
            table_bilan.setModel(new ResultSetTableModel(rslt));
            
            String query7="SELECT DATE_FORMAT(date, '%Y-%m-%d')AS Date, SUM(sous_total) AS Ventes, SUM(sorties) AS Depenses, SUM(sous_total) - SUM(sorties) AS Difference,\n" +
                    " SUM(SUM(sous_total) - SUM(sorties)) OVER (ORDER BY date) AS Cumul\n" +
                    "FROM (\n" +
                    "  SELECT  date_vente AS date, tp.sous_total, 0 AS sorties FROM vente As tp JOIN produit As td  WHERE td.devise = 'fc' and tp.code_produit=td.code_produit  UNION ALL\n" +
                    "  SELECT date, 0 AS vente, montant FROM depenses  WHERE  devise ='fc'\n" +
                    ") AS t\n" +" WHERE date like '%"+ moi_field.getSelectedItem().toString()+"%'\n "+
                    "GROUP BY date";
            
            rslt=base_donne.RecupererDonne(query7);
            // Défianir le modèle de tableau sur le composant table_user
            table_bilan_fc.setModel(new ResultSetTableModel(rslt));
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Bilan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bilan.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    
        
        
    }//GEN-LAST:event_recher_par_moiActionPerformed

    private void interval_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_interval_searchActionPerformed
        // TODO add your handling code here:
        try {                                               
            // TODO add your handling code here:
            String query6="SELECT DATE_FORMAT(date, '%Y-%m-%d')AS Date, SUM(sous_total) AS Ventes, SUM(sorties) AS Depenses, SUM(sous_total) - SUM(sorties) AS Difference,\n" +
                    " SUM(SUM(sous_total) - SUM(sorties)) OVER (ORDER BY date) AS Cumul\n" +
                    "FROM (\n" +
                    "  SELECT  date_vente AS date, tp.sous_total, 0 AS sorties FROM vente As tp JOIN produit As td  WHERE td.devise = '$' and tp.code_produit=td.code_produit  UNION ALL\n" +
                    "  SELECT date, 0 AS vente, montant FROM depenses  WHERE  devise ='$'  \n" +
                    ") AS t\n" +" where  date BETWEEN '"+ debut_date.getSelectedItem().toString() +"' and '"+fin_date.getSelectedItem().toString()+"'\n" +
                    "GROUP BY date";
            
                rslt=base_donne.RecupererDonne(query6);
           
            // Défianir le modèle de tableau sur le composant table_user
            table_bilan.setModel(new ResultSetTableModel(rslt));
            
            String query7="SELECT DATE_FORMAT(date, '%Y-%m-%d')AS Date, SUM(sous_total) AS Ventes, SUM(sorties) AS Depenses, SUM(sous_total) - SUM(sorties) AS Difference,\n" +
                    " SUM(SUM(sous_total) - SUM(sorties)) OVER (ORDER BY date) AS Cumul\n" +
                    "FROM (\n" +
                    "  SELECT  date_vente AS date, tp.sous_total, 0 AS sorties FROM vente As tp JOIN produit As td  WHERE td.devise = 'fc' and tp.code_produit=td.code_produit  UNION ALL\n" +
                    "  SELECT date, 0 AS vente, montant FROM depenses  WHERE  devise ='fc'  \n" +
                    ") AS t\n" +" where  date BETWEEN '"+ debut_date.getSelectedItem().toString() +"' and '"+fin_date.getSelectedItem().toString()+"'\n" +
                    "GROUP BY date";
            
            rslt=base_donne.RecupererDonne(query7);
            // Défianir le modèle de tableau sur le composant table_user
            table_bilan_fc.setModel(new ResultSetTableModel(rslt));
            System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Bilan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bilan.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        
        
    }//GEN-LAST:event_interval_searchActionPerformed

    private void search_journeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_journeyActionPerformed
        // TODO add your handling code here:
         try {                                               
            // TODO add your handling code here:
            String query6="SELECT DATE_FORMAT(date, '%Y-%m-%d')AS Date, SUM(sous_total) AS Ventes, SUM(sorties) AS Depenses, SUM(sous_total) - SUM(sorties) AS Difference,\n" +
                    " SUM(SUM(sous_total) - SUM(sorties)) OVER (ORDER BY date) AS Cumul\n" +
                    "FROM (\n" +
                    "  SELECT  date_vente AS date, tp.sous_total, 0 AS sorties FROM vente As tp JOIN produit As td  WHERE td.devise = '$' and tp.code_produit=td.code_produit  UNION ALL\n" +
                    "  SELECT date, 0 AS vente, montant FROM depenses  WHERE  devise ='$'\n" +
                    ") AS t\n" +" WHERE date like '%"+ jour.getSelectedItem().toString()+"%'\n "+
                    "GROUP BY date";
            
                rslt=base_donne.RecupererDonne(query6);
           
            // Défianir le modèle de tableau sur le composant table_user
            table_bilan.setModel(new ResultSetTableModel(rslt));
            
            String query7="SELECT DATE_FORMAT(date, '%Y-%m-%d')AS Date, SUM(sous_total) AS Ventes, SUM(sorties) AS Depenses, SUM(sous_total) - SUM(sorties) AS Difference,\n" +
                    " SUM(SUM(sous_total) - SUM(sorties)) OVER (ORDER BY date) AS Cumul\n" +
                    "FROM (\n" +
                    "  SELECT  date_vente AS date, tp.sous_total, 0 AS sorties FROM vente As tp JOIN produit As td  WHERE td.devise = 'fc' and tp.code_produit=td.code_produit  UNION ALL\n" +
                    "  SELECT date, 0 AS vente, montant FROM depenses  WHERE  devise ='fc'\n" +
                    ") AS t\n" +" WHERE date like '%"+ jour.getSelectedItem().toString()+"%'\n "+
                    "GROUP BY date";
            
            rslt=base_donne.RecupererDonne(query7);
            // Défianir le modèle de tableau sur le composant table_user
            table_bilan_fc.setModel(new ResultSetTableModel(rslt));
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Bilan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bilan.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    
    }//GEN-LAST:event_search_journeyActionPerformed

     public static void previewPDF(String filename) throws Exception {
         
 lecteurPDF lecteur = new lecteurPDF(filename);
 //créer le JFrame
 JFrame f = new JFrame("Lecteur PDF");
 f.setSize(1024,768);
 f.setLocationRelativeTo(null);
 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 f.setVisible(true);
 f.getContentPane().add(lecteur);
    }
    private void imprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimerActionPerformed
        // TODO add your handling code here:
         SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyHHmmss");
         SimpleDateFormat v = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                String nom="pdf/bilan/table_bilan"+s.format(date).toString()+".pdf";
                
        try {                                         
            Document document = new Document(PageSize.A4);
            
            try {
                System.out.println(nom);
                try {
                    PdfWriter.getInstance(document, new FileOutputStream(nom));
                    
                } catch (DocumentException ex) {
                    Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
            }
            document.open();
             Image image=null;
             try {
                 image = Image.getInstance("photos/logo.jpg");
             } catch (BadElementException ex) {
                 Logger.getLogger(Bilan.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(Bilan.class.getName()).log(Level.SEVERE, null, ex);
             }
              image.scaleAbsolute(60f, 60f);
              image.setAbsolutePosition(0f, document.getPageSize().getHeight() - image.getScaledHeight());
            
            // Ajoutez l'image au document
            document.add(image);
            image.setAbsolutePosition(document.getPageSize().getWidth() - image.getScaledWidth(), document.getPageSize().getHeight() - image.getScaledHeight());
             document.add(image);
                 Paragraph paragraph = new Paragraph("Bilan prélevé en $ en date "+v.format(date).toString()+"\n"+"\n");
            paragraph.setAlignment(Element.ALIGN_CENTER);
             document.add(paragraph);
             Paragraph espace = new Paragraph("\n");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(espace);
            document.add(espace);
            
            
            PdfPTable pdfTable = new PdfPTable(table_bilan.getColumnCount());
            
// Ajouter les en-têtes de colonne
        for (int i = 0; i < table_bilan.getColumnCount(); i++) {
            pdfTable.addCell(table_bilan.getColumnName(i));
            }

// Ajouter les données de la JTable
        for (int rows = 0; rows < table_bilan.getRowCount(); rows++) {
        for (int cols = 0; cols < table_bilan.getColumnCount(); cols++) {
        pdfTable.addCell(table_bilan.getModel().getValueAt(rows, cols).toString());
        }
        }

        document.add(pdfTable);
        document.add(espace);
        document.add(espace);
        
        

        Paragraph p=new Paragraph("Bilan prélevé en fc en date "+v.format(date).toString()+"\n"+"\n");
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);
        document.add(espace);
        document.add(espace);
        pdfTable = new PdfPTable(table_bilan_fc.getColumnCount());
          for (int i = 0; i < table_bilan_fc.getColumnCount(); i++) {
            pdfTable.addCell(table_bilan_fc.getColumnName(i));
            }

// Ajouter les données de la JTable
        for (int rows = 0; rows < table_bilan_fc.getRowCount(); rows++) {
        for (int cols = 0; cols < table_bilan_fc.getColumnCount(); cols++) {
        pdfTable.addCell(table_bilan_fc.getModel().getValueAt(rows, cols).toString());
        }
        }

        document.add(pdfTable);
        
        


document.close();
            try {
                previewPDF(nom);
            } catch (Exception ex) {
                Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (DocumentException ex) {
            Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imprimerActionPerformed

    private void voir_lesbilansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voir_lesbilansActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        File currentDir = new File(System.getProperty("user.dir"));
File subDir = new File(currentDir, "pdf/bilan");

        chooser.setCurrentDirectory(subDir);
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String selectedFile="";
                try {
                    selectedFile = chooser.getSelectedFile().getCanonicalPath();
                } catch (IOException ex) {
                    Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(selectedFile);
                previewPDF(selectedFile);
            } catch (Exception ex) {
                Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
            }
    }   
    }//GEN-LAST:event_voir_lesbilansActionPerformed

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
            java.util.logging.Logger.getLogger(Bilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Bilan().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Bilan.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Bilan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> debut_date;
    private javax.swing.JComboBox<String> fin_date;
    private javax.swing.JButton imprimer;
    private javax.swing.JButton interval_search;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jour;
    private javax.swing.JComboBox<String> moi_field;
    private javax.swing.JButton recher_par_moi;
    private javax.swing.JButton search_journey;
    private javax.swing.JTable table_bilan;
    private javax.swing.JTable table_bilan_fc;
    private javax.swing.JButton voir_lesbilans;
    // End of variables declaration//GEN-END:variables
}
