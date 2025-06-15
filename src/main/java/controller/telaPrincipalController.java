/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gestaodeturmas.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import utilities.*;
/**
 *
 * @author -
 */
public class telaPrincipalController implements Initializable {
    public telaPrincipalController(){

    }
    @FXML private Button registrarTurmaButton;
    @FXML private Button carregarButton;
    @FXML private Button exportarButton;

    @FXML public void TelaCadastro() throws IOException {
        registrarTurmaButton.getScene().getWindow().setWidth(1300);
        registrarTurmaButton.getScene().getWindow().setHeight(846);
        App.setRoot("gestaoTurma");

    }
    @FXML public void exportar(){
        jsonHandler jH = new jsonHandler();
        xmlHandler xmlH = new xmlHandler();
        File caminho = jsonHandler.selecaoDeCaminho();
        try{
            jH.exportJson(caminho);
            xmlH.exportarXml(caminho);
            gestaoTurmaController.showAlert("Aviso",
                    "Exportação de arquivos",
                    "Arquivos exportados com sucesso",
                    Alert.AlertType.CONFIRMATION);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML public void carregar(){
        jsonHandler jH = new jsonHandler();
        xmlHandler xH = new xmlHandler();
        try{
            File arquivo = jsonHandler.selecaoDeArquivo();
            if(arquivo.toString().contains(".json")){
                jH.importarJson(arquivo);
            }else if(arquivo.toString().contains(".xml")){
                xH.importarXml(arquivo);
            }
            gestaoTurmaController.showAlert("Aviso",
                    "Importação de arquivos",
                    "Arquivos importados com sucesso",
                    Alert.AlertType.CONFIRMATION);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
}
