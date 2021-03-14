/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dataservices.dao.ClienteDAO;
import dataservices.dao.FuncionarioDAO;
import domain.Cliente;
import domain.Funcionario;
import domain.Produto;
import domain.Venda;
import domain.tb_produto_tb_venda;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import resources.VendaTableModel;

/**
 *
 * @author felipe_baumgartel
 */
public class CadVenda extends javax.swing.JFrame {

    VendaDAO dao = new VendaDAO();
    
    public CadVenda() {
        initComponents();
        desabilitaComponetes();
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        atualizarTabela();
        carregarCliente();
        carregarFuncionario();
        cbCliente.setSelectedIndex(-1);
        cbFunc.setSelectedIndex(-1);
        txCd.setEnabled(false);
        this.setTitle("Cadstro de Venda");
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
        btFechar = new javax.swing.JButton();
        bt_Finalizar = new javax.swing.JButton();
        bt_Add = new javax.swing.JButton();
        bt_Cancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbFunc = new javax.swing.JComboBox();
        cbCliente = new javax.swing.JComboBox();
        txData = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txCd = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbProduto = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        txQtd = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 204));

        btFechar.setBackground(new java.awt.Color(102, 102, 255));
        btFechar.setText("Fechar");
        btFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFecharActionPerformed(evt);
            }
        });
        jPanel1.add(btFechar);

        bt_Finalizar.setBackground(new java.awt.Color(102, 102, 255));
        bt_Finalizar.setText("Finalizar Compra");
        bt_Finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_FinalizarActionPerformed(evt);
            }
        });
        jPanel1.add(bt_Finalizar);

        bt_Add.setBackground(new java.awt.Color(102, 102, 255));
        bt_Add.setText("Adicionar Produto");
        bt_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_AddActionPerformed(evt);
            }
        });
        jPanel1.add(bt_Add);

        bt_Cancelar.setBackground(new java.awt.Color(102, 102, 255));
        bt_Cancelar.setText("Cancelar Último Produto");
        jPanel1.add(bt_Cancelar);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));

        jLabel2.setText("Data");

        jLabel3.setText("Funcionário");

        jLabel4.setText("Cliente");

        try {
            txData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        txCd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txCdActionPerformed(evt);
            }
        });

        jLabel16.setText("Codigo");

        jLabel5.setText("Produto");

        jLabel6.setText("Quantidade");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(txCd, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbFunc, 0, 150, Short.MAX_VALUE)
                            .addComponent(cbCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txData)
                            .addComponent(cbProduto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txQtd))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txCd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btFecharActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() > 1) {

            Venda a = vtm.getValueAT(jTable1.getSelectedRow());
            recuperaVenda(a);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void txCdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txCdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txCdActionPerformed

    private void bt_FinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_FinalizarActionPerformed
        dao2.Save(cadastrar());
        JOptionPane.showMessageDialog(null, "Valor Total = "+dao2.total);
    }//GEN-LAST:event_bt_FinalizarActionPerformed

    private void bt_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_AddActionPerformed
        if(dao2.list(daoPed.idPedido()).isEmpty()){
            dao2.Save (cadastrar());
            dao2.Save1(adicionar());
        }else{
            dao2.Save1(adicionar());
        }
        atualizarProdutos();
    }//GEN-LAST:event_bt_AddActionPerformed
    private tb_produto_tb_venda adicionar(){
        int qntd = Integer.parseInt(txQtd.getText());
        Produto prod = (Produto) cbProduto.getSelectedItem();
        
        return new tb_produto_tb_venda (qntd, prod);
    }
    
    private Venda cadastrar(){
        int id = (daoPed.idPedido()+1);
        Cliente cliente = (Cliente) cbCliente.getSelectedItem();
        Funcionario func = (Funcionario) cbFunc.getSelectedItem();
        LocalDate Dt_Pedido = LocalDate.parse(txData.getText());
        int quantidade = Integer.parseInt(txQtd.getText());
        
        return new Venda(id, , Dt_Pedido, cliente, func);
    }
    
    public void carregarClientes(){
        for(Clientes cliente : daoCli.list()){
            cb_Cliente.addItem(cliente);
        }
    }
    
    public void carregarFuncionario(){
        for(Funcionarios func : daoFunc.list()){
            cb_Func.addItem(func);
        }
    }
    
    public void carregarProduto(){
        for(Produtos prod : dao.list()){
            cb_Prod.addItem(prod);
        }
    }
    
    public TableModel carregarProdutos(){
        List<Pedido_Produto> lista = dao2.list(daoPed.idPedido());
        ptm = new Ped_ProdTableModel(lista);
        return ptm;
    }
    
    public void atualizarProdutos(){
        jTable1.setModel(carregarProdutos());
    }
    
    Ped_ProdTableModel ptm;
    PedidosDAO daoPed = new PedidosDAO();
    PedidoProdutoDAO dao2 = new PedidoProdutoDAO();
    ProdutoDAO dao = new ProdutoDAO();
    FuncionarioDAO daoFunc = new FuncionarioDAO();
    ClienteDAO daoCli = new ClienteDAO();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFechar;
    private javax.swing.JButton bt_Add;
    private javax.swing.JButton bt_Cancelar;
    private javax.swing.JButton bt_Finalizar;
    private javax.swing.JComboBox cbCliente;
    private javax.swing.JComboBox cbFunc;
    private javax.swing.JComboBox cbProduto;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txCd;
    private javax.swing.JFormattedTextField txData;
    private javax.swing.JTextField txQtd;
    // End of variables declaration//GEN-END:variables
}
