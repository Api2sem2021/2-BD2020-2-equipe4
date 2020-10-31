/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yourcad;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
/*import javafx.scene.control.TextField;*/
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mateu
 */
public class Form_CadContaController implements Initializable {

    @FXML
    private BorderPane mainPane;
    @FXML
    private AnchorPane ancPane_TelaInicio;
    @FXML
    private MenuBar menuBar_TelaInicial;
    @FXML
    private MenuItem menuItem_CadCliente;
    @FXML
    private MenuItem menuItem_CadConta;
    @FXML
    private MenuItem menuItem_CadConcessionaria;
    @FXML
    private MenuItem menuItem_PesqCliente;
    @FXML
    private MenuItem menuItem_PesqConta;
    @FXML
    private MenuItem menuItem_PesqConcessionaria;
    @FXML
    private Pane panel_contas;
    @FXML
    private TextField txt_pesqInstalacao;
    @FXML
    private Button btn_pesqInstalacao;
    @FXML
    private TextField txt_apelido;
    @FXML
    private TextField txt_tipoInstalacao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
        // INICIO MENU BAR //
    // FUNÇÃO PARA ABRIR TELA A PARTIR DE MENU BAR 
    @FXML
    public void gotoCliente(ActionEvent event) throws IOException{
        PesqClienteController.alterClienteId = 0;
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Form_CadCliente.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void gotoConta(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Form_CadConta.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void gotoConcessionaria(ActionEvent event) throws IOException {
        PesqConcessionariaController.alterConcessionariaId = 0;
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Form_CadConcessionaria.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void gotoPesqCliente(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("PesqCliente.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    
    @FXML
    private void gotoPesqConta(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("PesqConta.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    
    @FXML
    private void gotoPesqConcessionaria(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("PesqConcessionaria.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    
    // FIM MENU BAR //
    static String contaInstalacaoId;
    static String contaInstalacaoNum;
    static String clienteId;
    @FXML
    private void pesquisarInstalacao(ActionEvent event) throws Exception {
        
        String numInstalacao = txt_pesqInstalacao.getText();
        
        Connection conn = null;
        ResultSet resultadoBanco = null;
        conn = DBConexao.abrirConexao();
        Statement stm = conn.createStatement();
                
        String sql0;
        sql0 = "SELECT * FROM instalacao WHERE instalacao_numero = " + numInstalacao +";";
        resultadoBanco = stm.executeQuery(sql0);
        String inst_apelido = null;
        String inst_tipo = null;
        String inst_id = null;
        String inst_num = null;
        String inst_cliente = null;

                while(resultadoBanco.next())
                { 
                    inst_apelido = resultadoBanco.getString("instalacao_apelido"); 
                    inst_tipo = resultadoBanco.getString("instalacao_tipo"); 
                    inst_id = resultadoBanco.getString("instalacao_id");
                    inst_num = resultadoBanco.getString("instalacao_numero");
                    inst_cliente = resultadoBanco.getString("cliente_id");

                }
                txt_apelido.setText(inst_apelido);
                txt_tipoInstalacao.setText(inst_tipo);
                
        if("Agua e Esgoto".equals(inst_tipo))
        {
           contaInstalacaoId = inst_id;
           contaInstalacaoNum = inst_num;
           clienteId = inst_cliente;
           panel_contas.getChildren().clear();
           Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("Form_cadContaAgua.fxml"));
           panel_contas.getChildren().add(newLoadedPane); 
        }
        // ***** Se for de energia deleta instalacao de energia
        if("Energia".equals(inst_tipo))
        {
            contaInstalacaoId = inst_id;
            contaInstalacaoNum = inst_num;
            clienteId = inst_cliente;
            panel_contas.getChildren().clear();
            Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("Form_cadContaEnergia.fxml"));
            panel_contas.getChildren().add(newLoadedPane);
        }
    }

   
    
}
