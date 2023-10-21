/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.interfaceG;

import gestion.stock.ResultSetTableModel;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Administrator
 */
public class Cahier extends javax.swing.JFrame {

    ResultSet rslt = null;
    BDD base_donne;

    public Cahier() throws SQLException, ClassNotFoundException {
        base_donne = new BDD();

        initComponents();
        base_donne.ouvrirLaConnexion();
        // TODO add your handling code here:
        combox_remplissage();
        afficherTableProd();
        afficherTableVente();

        this.setVisible(true);
        this.setResizable(false);
    }

    public void bill_print() {

       
            bill.setText("                            \t\tThe Njuci'son                      \tDate : "+ date_field.getText()+"\n");
            bill.setText(bill.getText() + "        \t     \t  17/ Athnee1, \n");
            bill.setText(bill.getText() + "        \t  \t  Bukavu, DRC, \n");
            bill.setText(bill.getText() + "        \t   \t+243 971595494, \n");
            bill.setText(bill.getText() + " \n");
           bill.setText(bill.getText() +search_field.getSelectedItem().toString()+ " \n");
            bill.setText(bill.getText() + " \n");
            bill.setText(bill.getText() + "-------------------------------------------------------------------------------------------------------\n");
            bill.setText(bill.getText() + " Code \tref \tPrice \tQty \tSous-total \n");
            bill.setText(bill.getText() + "-------------------------------------------------------------------------------------------------------\n");

            for (int i = 0; i < table_vente.getRowCount(); i++) {

                String code_produit = table_vente.getValueAt(i, 0).toString();
                String reference = table_vente.getValueAt(i, 1).toString();
                String prc = table_vente.getValueAt(i, 2).toString();
                String stv = table_vente.getValueAt(i, 3).toString();
                String subtol = table_vente.getValueAt(i, 4).toString();

                bill.setText(bill.getText() + code_produit + "\t" + reference + "\t" + prc + "\t" + stv + "\t" + subtol + "\n");

            }
            bill.setText(bill.getText() + "-------------------------------------------------------------------------------------------------------\n");
            bill.setText(bill.getText() + "SubTotal :\t" + total_facture.getText() + "\n");
            bill.setText(bill.getText() + "Cash :\t" + "0" + "\n");
            bill.setText(bill.getText() + "Ballance :\t" + total_facture.getText() + "\n");
            bill.setText(bill.getText() + "===========================================================\n");
             bill.setText(bill.getText() + "                     Merci pour votre fidélité...!" + "\n");
            bill.setText(bill.getText() + "----------------------------------------------------------------\n");
            bill.setText(bill.getText() + "                     Software by Njuci " + "\n");

            

        
    }

    public void afficherTableProd() throws ClassNotFoundException, SQLException {
        String[] colonnes = {"id", "code_produit", "ref", "designation", "fournisseur", "remise", "prix_unitaire", "devise", "stock", "unite_mesure"};

        // Récupérer le ResultSet à partir de votre méthode RecupererDonneTableFiltrePar()
        ResultSet resultSet = base_donne.RecupererDonneTableFiltreParEtat(colonnes, "produit", "stock >= 1");

        // Créer un modèle de tableau avec le ResultSet
        ResultSetTableModel tableModel = new ResultSetTableModel(resultSet);

        // Définir le modèle de tableau sur le composant table_user
        table_prod.setModel(new ResultSetTableModel(resultSet));
        date_set();
    }

    public void combox_remplissage() throws SQLException, ClassNotFoundException {
        rslt = base_donne.RecupererDonne("SELECT fac_num FROM facture order by -id");
        search_field.removeAllItems();
        while (rslt.next()) {

            search_field.addItem(rslt.getString("fac_num"));

        }
        rslt.close();

    }  

    public void afficherTableVente() throws ClassNotFoundException, SQLException {

        String[] colonnes = {"td.code_produit", "td.ref", "td.prix_vente", "td.stock_sortie", "td.sous_total","tp.devise"};

        ResultSet resultSet = base_donne.RecupererDonneTableFiltreParEtat(colonnes, "vente As td  JOIN produit As tp", "td.num_facture ='" + search_field.getSelectedItem().toString() + "' and td.code_produit =tp.code_produit");
        String query = "SELECT  DATE_FORMAT(date, '%Y-%m-%d')As date FROM facture WHERE fac_num ='" + search_field.getSelectedItem().toString() + "'";

        rslt = base_donne.RecupererDonne(query);
        rslt.next();
        date_venrte_field.setText(rslt.getString("date"));
        // Créer un modèle de tableau avec le ResultSet
        ResultSetTableModel tableModel = new ResultSetTableModel(resultSet);

        table_vente.setModel(new ResultSetTableModel(resultSet));
        total_facture();
    }
//

    public void update_stock() throws ClassNotFoundException, SQLException {

        rslt = base_donne.RecupererDonneTableFiltre("produit", "code_produit='" + code_prod_field1.getText() + "'");
        int stock_dispo = 0;
        int stock_voulu = Integer.parseInt(stock_field.getText());
        while (rslt.next()) {
            stock_dispo = rslt.getInt("stock");
        }
        int nouveau_stock;
        nouveau_stock = stock_dispo - stock_voulu;
        String stock_update = String.valueOf(nouveau_stock);
        String[] colonnes = {"stock"};
        String[] donne = {stock_update};
        String condition = "code_produit='" + code_prod_field1.getText() + "'";
        base_donne.updateDonneTable("produit", colonnes, donne, condition);
        afficherTableProd();
    }

    public void update_stock2(int stock_reab, String vente_id) throws ClassNotFoundException, SQLException {

        rslt = base_donne.RecupererDonneTableFiltre("produit", "code_produit='" + vente_id + "'");
        int stock_dispo = 0;

        while (rslt.next()) {
            stock_dispo = rslt.getInt("stock");
        }
        int nouveau_stock;
        nouveau_stock = stock_dispo + stock_reab;
        String stock_update = String.valueOf(nouveau_stock);
        String[] colonnes = {"stock"};
        String[] donne = {stock_update};
        String condition = "code_produit='" + vente_id + "'";
        base_donne.updateDonneTable("produit", colonnes, donne, condition);
        afficherTableProd();
    }
    //

    public void total_vente() {
        int qt = Integer.parseInt(stock_field.getText());
        int pv = Integer.parseInt(nouveau_prix_unit_field1.getText());
        int total = qt * pv;
        String total_st = String.valueOf(total);
        sous_total_facture.setText(total_st);

    }

    public void date_set() {
        Date d = new Date();
        SimpleDateFormat dat1 = new SimpleDateFormat("EEEE-dd-MMM-yyy");
        SimpleDateFormat dat2 = new SimpleDateFormat("HH:mm");
        date_field.setText(dat1.format(d));
        heure_field.setText(dat2.format(d));

    }

    public void total_facture() throws SQLException, ClassNotFoundException {

        rslt = base_donne.RecupererDonne("SELECT SUM(td.sous_total) AS total FROM vente As td  INNER JOIN produit As tp ON td.code_produit=tp.code_produit  WHERE td.num_facture ='" + search_field.getSelectedItem().toString() + "' and tp.devise='$'");
        rslt.next();
        total_facture.setText(rslt.getString("total"));
        rslt.close();
       
        rslt = base_donne.RecupererDonne("SELECT SUM(td.sous_total) AS total FROM vente As td  INNER JOIN produit As tp ON td.code_produit=tp.code_produit  WHERE td.num_facture ='" + search_field.getSelectedItem().toString() + "' and tp.devise='fc'");
        rslt.next();
        total_facture_fc.setText(rslt.getString("total"));
        rslt.close();
       

    }

    public boolean getStock() throws ClassNotFoundException, SQLException {

        rslt = base_donne.RecupererDonneTableFiltre("produit", "code_produit='" + code_prod_field1.getText() + "'");
        int stock_dispo = 0;
        int stock_voulu = Integer.parseInt(stock_field.getText());
        while (rslt.next()) {
            stock_dispo = rslt.getInt("stock");

        }
        boolean getStock = true;
        if (stock_dispo <= stock_voulu || stock_voulu == 0) {
            getStock = false;

        }
        return getStock;
    }

    public void ApayeApre() {
        if (credit_field.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "svp Veuillez Renseignez  le champs Crefit");

        } else {
            int credit = Integer.parseInt(credit_field.getText());
            int total = Integer.parseInt(total_facture.getText());
            int reste = total - credit;
            String reste_st = String.valueOf(reste);
            cash_field.setText(reste_st);
        }

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
        jScrollPane1 = new javax.swing.JScrollPane();
        table_prod = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        date_field = new javax.swing.JLabel();
        heure_field = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lancer = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        credit_field = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_vente = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        total_facture = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        imprimer = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        cash_field = new javax.swing.JTextField();
        search_field = new javax.swing.JComboBox<>();
        supprimer = new javax.swing.JButton();
        date_venrte_field = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        bill = new javax.swing.JTextArea();
        previsual_facture = new javax.swing.JButton();
        total_facture_fc = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        recherche_cmbx = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        recherche_prod = new javax.swing.JButton();
        search_field_comb = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        code_prod_field1 = new javax.swing.JTextField();
        ref_field = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        nouveau_prix_unit_field1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        fourni_field = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        remise_field = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        sous_total_facture = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        valider_vente = new javax.swing.JButton();
        stock_field = new javax.swing.JTextField();
        g = new javax.swing.JLabel();
        um_field = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        devise_field = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(java.awt.Color.orange);

        table_prod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Code_produit", "ref", "Designation", "Fournisseur", "Remise", "Prix", "Devise", "Stock", "unite de Mesure", "Devise"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true
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
        jScrollPane1.setViewportView(table_prod);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Cahier");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        date_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        heure_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(date_field, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(heure_field, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(date_field, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(heure_field, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));

        jLabel2.setText("Num Fac");

        jLabel3.setText("Crédit ");

        lancer.setText("Lancer");
        lancer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lancerActionPerformed(evt);
            }
        });

        jLabel4.setText("Cash");

        jButton2.setText("Creer une nouvelle facture");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        table_vente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Code_produit", "Reference", "Prix_vente", "Stock Vendu", "Sous-Total", "Devise"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table_vente);

        jLabel22.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel22.setText("Total $");

        total_facture.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        total_facture.setText("0");

        jButton5.setText("Annuler");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        imprimer.setText("Imprimer la facture");
        imprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimerActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel24.setText("Total Fc");

        search_field.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        search_field.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                search_fieldItemStateChanged(evt);
            }
        });
        search_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_fieldActionPerformed(evt);
            }
        });
        search_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_fieldKeyReleased(evt);
            }
        });

        supprimer.setText("Supprimer vente");
        supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimerActionPerformed(evt);
            }
        });

        date_venrte_field.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N

        bill.setColumns(20);
        bill.setRows(5);
        jScrollPane3.setViewportView(bill);

        previsual_facture.setText("Prévisualiser la facture");
        previsual_facture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previsual_factureActionPerformed(evt);
            }
        });

        total_facture_fc.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        total_facture_fc.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(lancer)
                        .addGap(80, 80, 80)
                        .addComponent(jButton2)
                        .addGap(40, 40, 40)
                        .addComponent(supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(imprimer)
                                .addGap(337, 337, 337)
                                .addComponent(jButton5))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(total_facture, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(previsual_facture))
                                        .addGap(62, 62, 62)
                                        .addComponent(total_facture_fc, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(28, 28, 28)
                                        .addComponent(cash_field, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(95, 95, 95)
                                        .addComponent(jLabel3)
                                        .addGap(79, 79, 79)
                                        .addComponent(credit_field, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(date_venrte_field, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(162, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lancer)
                    .addComponent(jButton2)
                    .addComponent(search_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supprimer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(total_facture, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(total_facture_fc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(date_venrte_field, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(credit_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cash_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(previsual_facture, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48))))
        );

        jPanel4.setBackground(new java.awt.Color(51, 204, 255));

        jLabel12.setText("Rechercher");

        recherche_cmbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "code_produit", "ref", "fournisseur", "designation", "remise  ", "prix_unitaire", "stock" }));
        recherche_cmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recherche_cmbxActionPerformed(evt);
            }
        });

        jLabel13.setText("code_prod");

        recherche_prod.setText("Lancer");
        recherche_prod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recherche_prodActionPerformed(evt);
            }
        });

        search_field_comb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_field_combActionPerformed(evt);
            }
        });

        jLabel14.setText("Reference");

        ref_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ref_fieldActionPerformed(evt);
            }
        });

        jLabel15.setText("N Prix_unit");

        jLabel16.setText("Fournisseur");

        jLabel17.setText("Remise");

        jLabel19.setText("Stock Vendu");

        sous_total_facture.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        sous_total_facture.setText("0");

        jLabel21.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel21.setText("Total");

        valider_vente.setText("Valider");
        valider_vente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valider_venteActionPerformed(evt);
            }
        });

        stock_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                stock_fieldKeyReleased(evt);
            }
        });

        g.setText("Unité Mesure");

        jLabel5.setText("Devise");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nouveau_prix_unit_field1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stock_field, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valider_vente, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel17)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(remise_field, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fourni_field, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel13)
                                                .addComponent(jLabel14))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                            .addComponent(recherche_prod)
                                            .addGap(28, 28, 28)))
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(recherche_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(search_field_comb)
                                        .addComponent(code_prod_field1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(ref_field)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(sous_total_facture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(g, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(um_field))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(devise_field)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(recherche_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recherche_prod)
                    .addComponent(search_field_comb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(code_prod_field1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(ref_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(fourni_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(remise_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nouveau_prix_unit_field1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(devise_field))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(stock_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(g)
                    .addComponent(um_field))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sous_total_facture, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(valider_vente, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jMenu1.setText("Menu");

        jMenuItem3.setText("Voir le bilan");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem5.setText("tableau de Bord");
        jMenu1.add(jMenuItem5);

        jMenuItem6.setText("Voir les depenses");
        jMenu1.add(jMenuItem6);

        jMenuItem7.setText("Login");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Quitter");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void search_field_combActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_field_combActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_field_combActionPerformed

    private void ref_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ref_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ref_fieldActionPerformed

    private void lancerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lancerActionPerformed
        try {
            // TODO add your handling code here:
            afficherTableVente();
            total_facture();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cahier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Cahier.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_lancerActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void imprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimerActionPerformed
        try {
            // TODO add your handling code here:
            /*Map params = new HashMap();
            JRDataSource dataSource = new JRTableModelDataSource(table_vente.getModel());
            params.put("item_invoice", dataSource);// Compiler le rapport Jasper
            JasperReport report=null;
            String reportPath = "C:/Users/Administrator/Documents/NetBeansProjects/Gestion Stock/src/gestion/interfaceG/facture.jrxml";
            String log4jConfPath = "C:/Users/Administrator/Documents/NetBeansProjects/Gestion Stock/src/gestion/interfaceG/log4j.properties";
            PropertyConfigurator.configure(log4jConfPath);
            try {
            report = JasperCompileManager.compileReport(reportPath);
            } catch (JRException ex) {
            Logger.getLogger(Cahier.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Remplir le rapport Jasper avec les données et les paramètres
            JasperPrint print=null;
            try {
            print = JasperFillManager.fillReport(report, params, new JREmptyDataSource());
            } catch (JRException ex) {
            Logger.getLogger(Cahier.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Afficher le rapport Jasper dans une fenêtre
            JasperViewer.viewReport(print); // true == Exit on Close
            */
            bill_print();
            bill.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Cahier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imprimerActionPerformed

    private void table_prodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_prodMouseClicked
        // TODO add your handling code here:

        code_prod_field1.setText(String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(), 1)));
        fourni_field.setText(String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(), 4)));

        ref_field.setText(String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(), 2)));
        remise_field.setText(String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(), 5)));
        nouveau_prix_unit_field1.setText(String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(), 6)));
        um_field.setText(String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(), 9)));
        devise_field.setText(String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(), 7)));
        stock_field.setText(String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(), 8)));


    }//GEN-LAST:event_table_prodMouseClicked

    private void valider_venteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valider_venteActionPerformed
        try {
            // TODO add your handling code here:
            if (code_prod_field1.getText().equals("") || fourni_field.getText().equals("") || ref_field.getText().equals("")
                    || remise_field.getText().equals("") || nouveau_prix_unit_field1.getText().equals("") || stock_field.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "svp Veuillez Renseignez toute les champs");
            } else {
                try {
                    if (getStock() == true) {
                        total_vente();
                        String[] colonnes = {"code_produit", "num_facture", "ref", "stock_sortie", "prix_vente", "sous_total", "date_vente"};
                        rslt = base_donne.RecupererDonne("SELECT date FROM facture WHERE fac_num ='" + search_field.getSelectedItem().toString() + "'");
                        String date = null;
                        while (rslt.next()) {
                            date = rslt.getString("date");

                        }

                        String[] donne = {code_prod_field1.getText(), search_field.getSelectedItem().toString(),
                            ref_field.getText(), stock_field.getText(), nouveau_prix_unit_field1.getText(), sous_total_facture.getText(), date};
                        System.out.println(donne + " " + colonnes);

                        base_donne.InsererParColns("vente", colonnes, donne);
                        afficherTableVente();
                        total_facture();
                        update_stock();
                        afficherTableVente();
                        total_facture();

                    } else {
                        JOptionPane.showMessageDialog(this, "Quantité invalide");
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Cahier.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Cahier.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            afficherTableProd();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cahier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Cahier.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_valider_venteActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            String s = base_donne.createFacture();
            try {
                combox_remplissage();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Cahier.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Cahier.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void recherche_cmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recherche_cmbxActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_recherche_cmbxActionPerformed

    private void recherche_prodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recherche_prodActionPerformed
        // TODO add your handling code here:
        //
        if (search_field_comb.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "veuillez entrez quelque hose");
        } else {
            if (recherche_cmbx.getSelectedItem().equals("code_produit")) {
                try {
                    rslt = base_donne.RecupererDonneTableFiltre("produit", "code_produit LIKE'%" + search_field_comb.getText() + "%'");
                    table_prod.setModel(new ResultSetTableModel(rslt));

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (recherche_cmbx.getSelectedItem().equals("ref")) {
                try {
                    rslt = base_donne.RecupererDonneTableFiltre("produit", "ref LIKE'%" + search_field_comb.getText() + "%'");
                    table_prod.setModel(new ResultSetTableModel(rslt));

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (recherche_cmbx.getSelectedItem().equals("designation")) {
                try {
                    rslt = base_donne.RecupererDonneTableFiltre("produit", "designation LIKE'%" + search_field_comb.getText() + "%'");
                    table_prod.setModel(new ResultSetTableModel(rslt));

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (recherche_cmbx.getSelectedItem().equals("fournisseur")) {
                try {
                    rslt = base_donne.RecupererDonneTableFiltre("produit", "fournisseur LIKE'%" + search_field_comb.getText() + "%'");
                    table_prod.setModel(new ResultSetTableModel(rslt));

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (recherche_cmbx.getSelectedItem().equals("remise")) {
                try {
                    rslt = base_donne.RecupererDonneTableFiltre("produit", "remise LIKE'%" + search_field_comb.getText() + "%'");
                    table_prod.setModel(new ResultSetTableModel(rslt));

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (recherche_cmbx.getSelectedItem().equals("prix_unitaire")) {
                try {
                    rslt = base_donne.RecupererDonneTableFiltre("produit", "prix_unitaire LIKE'%" + search_field_comb.getText() + "%'");
                    table_prod.setModel(new ResultSetTableModel(rslt));

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (recherche_cmbx.getSelectedItem().equals("stock")) {
                try {
                    rslt = base_donne.RecupererDonneTableFiltre("produit", "stock LIKE'%" + search_field_comb.getText() + "%'");
                    table_prod.setModel(new ResultSetTableModel(rslt));

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }


    }//GEN-LAST:event_recherche_prodActionPerformed

    private void supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimerActionPerformed
        // TODO add your handling code here:
        String id = String.valueOf(table_vente.getValueAt(table_vente.getSelectedRow(), 0));
        int stock_voulu = (int) table_vente.getValueAt(table_vente.getSelectedRow(), 5);
        String code_produit = String.valueOf(table_vente.getValueAt(table_vente.getSelectedRow(), 2));
        int id_int = Integer.parseInt(id);
        if (JOptionPane.showConfirmDialog(this, "êtes-vous Sûr de supprimer", "warning", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            try {
                update_stock2(stock_voulu, code_produit);
                base_donne.Delete("vente", " id ='" + id + "'");
                afficherTableProd();
                afficherTableVente();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Cahier.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Cahier.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            total_facture();
        } catch (SQLException ex) {
            Logger.getLogger(Cahier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cahier.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(id);


    }//GEN-LAST:event_supprimerActionPerformed

    private void stock_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stock_fieldKeyReleased
        // TODO add your handling code here:


    }//GEN-LAST:event_stock_fieldKeyReleased

    private void search_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_fieldKeyReleased
        try {
            // TODO add your handling code here:
            afficherTableVente();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cahier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Cahier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_search_fieldKeyReleased

    private void previsual_factureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previsual_factureActionPerformed
        // TODO add your handling code here:
        bill_print();
    }//GEN-LAST:event_previsual_factureActionPerformed

    private void search_fieldItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_search_fieldItemStateChanged
       
    }//GEN-LAST:event_search_fieldItemStateChanged

    private void search_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_fieldActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

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
            java.util.logging.Logger.getLogger(Cahier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cahier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cahier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cahier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Cahier().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Cahier.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Cahier.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea bill;
    private javax.swing.JTextField cash_field;
    private javax.swing.JTextField code_prod_field1;
    private javax.swing.JTextField credit_field;
    private javax.swing.JLabel date_field;
    private javax.swing.JLabel date_venrte_field;
    private javax.swing.JLabel devise_field;
    private javax.swing.JTextField fourni_field;
    private javax.swing.JLabel g;
    private javax.swing.JLabel heure_field;
    private javax.swing.JButton imprimer;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton lancer;
    private javax.swing.JTextField nouveau_prix_unit_field1;
    private javax.swing.JButton previsual_facture;
    private javax.swing.JComboBox<String> recherche_cmbx;
    private javax.swing.JButton recherche_prod;
    private javax.swing.JTextField ref_field;
    private javax.swing.JTextField remise_field;
    private javax.swing.JComboBox<String> search_field;
    private javax.swing.JTextField search_field_comb;
    private javax.swing.JLabel sous_total_facture;
    private javax.swing.JTextField stock_field;
    private javax.swing.JButton supprimer;
    private javax.swing.JTable table_prod;
    private javax.swing.JTable table_vente;
    private javax.swing.JLabel total_facture;
    private javax.swing.JLabel total_facture_fc;
    private javax.swing.JLabel um_field;
    private javax.swing.JButton valider_vente;
    // End of variables declaration//GEN-END:variables
}
