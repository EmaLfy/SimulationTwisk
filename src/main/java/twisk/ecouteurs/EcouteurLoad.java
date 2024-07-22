package twisk.ecouteurs;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import twisk.mondeIG.MondeIG;

import java.io.IOException;
import java.util.Optional;

public class EcouteurLoad implements EventHandler<ActionEvent> {

    private MondeIG mondeIG;

    public EcouteurLoad(MondeIG m) {
        this.mondeIG = m;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Charger le monde, attention cela écrasera le monde actuel");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichier de sauvegarde", "*.json")
        );
        fileChooser.setInitialDirectory(new java.io.File("./"));
        java.io.File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                mondeIG.load(file.getAbsolutePath());
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur chargement");
                alert.setHeaderText("Erreur lors du chargement, verifiez le nom du fichier");
                alert.setContentText("Le nom du fichier est incorrect");
                alert.show();
                PauseTransition p = new PauseTransition(Duration.seconds(5));
                p.setOnFinished(event -> {
                    alert.close();
                });
                p.play();
            }
        }
    }
}
