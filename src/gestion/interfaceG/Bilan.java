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

/**
 *
 * @author Administrator
 */
public class Bilan extends javax.swing.JFrame {

    ResultSet rslt = null;
    BDD base_donne;
    
    public Bilan() throws ClassNotFoundException, SQLException {
        initComponents();
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
        while (rslt.next()) {
           moi_field.addItem(rslt.getString("moi"));

        }
        rslt.close();

    }
public void combox_remplissage() throws SQLException, ClassNotFoundException {
        String query="SELECT DISTINCT DATE_FORMAT(date, '%Y-%m') As moi FROM (\n" +
                "  SELECT date_vente AS date FROM vente\n" +
                "  UNION\n" +
                "  SELECT date AS date FROM depenses\n" +
                ") AS dates";
         
        rslt = base_donne.RecupererDonne(query);
        date_debut_field.removeAllItems();
        jour_fin.removeAllItems();
        while (rslt.next()) {
           jour_fin.addItem(rslt.getString("moi"));
           date_debut_field.addItem(rslt.getString("moi"));

        }
        rslt.close();

    }
    public void afficherTableBilan() throws ClassNotFoundException, SQLException {
     // Sous-requête pour calculer la somme des entrées
    String entreesQuery = "SELECT date, SUM(montant) AS total_entrees " +
                          "FROM depenses " +
                          "WHERE montant > 0 " +
                          "GROUP BY date";

    // Sous-requête pour calculer la somme des sorties
    String sortiesQuery = "SELECT date, SUM(montant) AS total_sorties " +
                          "FROM depenses " +
                          "WHERE montant < 0 " +
                          "GROUP BY date";
    String query6="SELECT DATE_FORMAT(date, '%Y-%m-%d'), SUM(sous_total) AS total_entrees, SUM(sorties) AS total_sorties, SUM(sous_total) - SUM(sorties) AS difference,\n" +
" SUM(SUM(sous_total) - SUM(sorties)) OVER (ORDER BY date) AS cumul_difference\n" +
"FROM (\n" +
"  SELECT  date_vente AS date, sous_total, 0 AS sorties FROM vente  UNION ALL\n" +
"  SELECT date, 0 AS vente, montant FROM depenses\n" +
") AS t\n" +
"GROUP BY date;";

    // Requête principale pour combiner les résultats et calculer la différence
    String query = "SELECT e.date, e.total_entrees, s.total_sorties, " +
                   "       (e.total_entrees - s.total_sorties) AS difference, " +
                   "       @cumul := @cumul + (e.total_entrees - s.total_sorties) AS cumul_diff " +
                   "FROM (" + entreesQuery + ") AS e " +
                   "JOIN (" + sortiesQuery + ") AS s " +
                   "ON e.date = s.date, (SELECT @cumul := 0) c " +
                   "ORDER BY e.date";
          
String query3=
"SELECT e.date, COALESCE(e.total_entrees, 0) AS total_entrees,"
        + "        COALESCE(s.total_sorties, 0) AS total_sorties,       "
        + " (COALESCE(e.total_entrees, 0) - COALESCE(s.total_sorties, 0)) AS difference,   "
        + "     @cumul := @cumul + (COALESCE(e.total_entrees, 0) - COALESCE(s.total_sorties, 0))"
        + " AS cumul_diff FROM (SELECT COALESCE(v.date_vente, d.date) AS date,    "
        + "    COALESCE(SUM(v.sous_total), 0) AS total_entrees FROM vente v LEFT JOIN depenses d ON v.date_vente = "
        + "d.date WHERE COALESCE(v.sous_total, 0) >= 0       OR COALESCE(d.montant, 0) >= 0 GROUP BY date) AS "
        + "e JOIN (SELECT COALESCE(v.date_vente, d.date) AS date,        COALESCE(SUM(d.montant), 0) AS total_sorties F"
        + "ROM vente v RIGHT JOIN depenses d ON v.date_vente = d.date WHERE COALESCE(v.sous_total, 0) >= 0      "
        + " OR COALESCE(d.montant, 0) >= 0 GROUP BY date) AS s ON e.date = s.date, (SELECT @cumul := 0)"
        + " c ORDER BY e.date";
String query4="SELECT e.date, COALESCE(e.total_entrees, 0) AS total_entrees,"
        + "        COALESCE(s.total_sorties, 0) AS total_sorties,    "
        + "    (COALESCE(e.total_entrees, 0) - COALESCE(s.total_sorties, 0)) AS difference"
        + ",        @cumul := @cumul + (COALESCE(e.total_entrees, 0) - COALESCE(s.total_sorties, 0)) "
        + "AS cumul_diff FROM (SELECT DISTINCT COALESCE(v.date_vente, d.date) AS date,  "
        + "      COALESCE(SUM(v.sous_total), 0) AS total_entrees FROM vente v LEFT JOIN"
        + " depenses d ON v.date_vente = d.date WHERE COALESCE(v.sous_total, 0) >= 0       "
        + "OR COALESCE(d.montant, 0) >= 0 GROUP BY date) AS e JOIN (SELECT DISTINCT COALESCE(v.date_vente, d.date) AS date,    "
        + "    COALESCE(SUM(d.montant), 0) AS total_sorties FROM vente v RIGHT JOIN depenses d ON v.date_vente = d.date "
        + "WHERE COALESCE(v.sous_total, 0) >= 0 "
        + ""
        + "      OR COALESCE(d.montant, 0) >= 0 GROUP BY date) AS s ON e.date = s.date, (SELECT @cumul := 0)"
        + " c ORDER BY e.date;";
                   rslt=base_donne.RecupererDonne(query6);
        // Défianir le modèle de tableau sur le composant table_user
        table_bilan.setModel(new ResultSetTableModel(rslt));
     
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
        date_debut_field = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jour_fin = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_bilan = new javax.swing.JTable();

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

        date_debut_field.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Date fin ou Journée");

        jour_fin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(moi_field, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(date_debut_field, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jour_fin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(moi_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(date_debut_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jour_fin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));

        table_bilan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Date ", "Entrees", "Sortie", "Cumul"
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 213, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void moi_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moi_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moi_fieldActionPerformed

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
    private javax.swing.JComboBox<String> date_debut_field;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jour_fin;
    private javax.swing.JComboBox<String> moi_field;
    private javax.swing.JTable table_bilan;
    // End of variables declaration//GEN-END:variables
}
