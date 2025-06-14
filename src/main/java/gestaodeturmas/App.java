package gestaodeturmas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import static javafx.application.Application.launch;

/**
 * JavaFX App
 */
public class App extends Application { //Veja que a aplicação é no geral associada à uma Application, invocada na main. Lembrar da organização de interface JavaFX: Application < Scene < componentes de interface desejados (botões, labels, etc).

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        //System.out.println(loadFXML("gestaoTurma"));
        scene = new Scene(loadFXML("telaPrincipal")/*, 640, 480*/); //instancia Scene associando chamada ao arquivo de interface fxml principal, chamando-a no método loadFXML
        stage.setScene(scene);

        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        //método auxiliar para poder alterar contexto de Scene na Application corrente. Simular 2 telas que se alternam na aplicação, inicia com a primary.fxml e permite alternar com secondary.fxml
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException { //método de carga de fxml, auxiliar neste exemplo, invocado no método start
       FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/"+ fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(); //por default, invoca o método start acima para gerar uma Scene na Application corrente
        return;
    }
}