/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gestaodeturmas.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 *
 * @author -
 */
public class telaPrincipalController implements Initializable {
    public telaPrincipalController(){

    }
    @FXML
    private Button registrarTurmaButton;
    @FXML
    private Button carregarButton;
    @FXML
    private Button exportarButton;

    @FXML
    public void TelaCadastro() throws IOException {

        registrarTurmaButton.getScene().getWindow().setWidth(1300);
        registrarTurmaButton.getScene().getWindow().setHeight(846);
        App.setRoot("gestaoTurma");

    }
    public void Exportar(){
        
    }
    public void Carregar(){
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
}
