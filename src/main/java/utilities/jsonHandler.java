package utilities;

import com.google.gson.Gson;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;
import model.staticModels.Lista;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class jsonHandler {
    List<Disciplina> dList = Lista.listaDisciplina;
    List<Professor> pList = Lista.listaProfessor;
    List<Aluno> aList = Lista.listaAlunos;
    List<Turma> aTurma = Lista.listaTurma;

    public void exportJson(){
        Gson gson = new Gson();
        jsonHandler l = new jsonHandler();
        try(FileWriter w = new FileWriter("dados.json")){
            gson.toJson(l,w);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void importarJson(File arquivo) {
        if (arquivo != null) {
            Gson gson = new Gson();
            try (FileReader reader = new FileReader(arquivo)) {
                TempJsonList tL = gson.fromJson(reader, TempJsonList.class);
                tL.passToMainList();
            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo JSON: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("Erro ao converter JSON para objeto: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Nenhum arquivo JSON selecionado.");
        }
    }
    public static File selecaoDeArquivo(){
        try {
            Stage stage = new Stage();
            stage.setTitle("Seleção de Arquivo");
            FileChooser fc = new FileChooser();
            fc.setTitle("Importação de Arquivos");
            FileChooser.ExtensionFilter filterJson = new FileChooser.ExtensionFilter("Arquivos JSON ou XML", "*.json", "*.xml");
            fc.getExtensionFilters().add(filterJson);
            File arquivo = fc.showOpenDialog(stage);
            return arquivo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
