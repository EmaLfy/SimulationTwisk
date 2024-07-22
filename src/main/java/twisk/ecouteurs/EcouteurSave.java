package twisk.ecouteurs;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.util.Duration;
import twisk.mondeIG.MondeIG;

import java.io.IOException;
import java.util.Optional;

public class EcouteurSave implements EventHandler<ActionEvent> {

    private MondeIG mondeIG;

    public EcouteurSave(MondeIG m) {
        this.mondeIG = m;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String delimiter;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Sauvegarder");
        dialog.setHeaderText("Sauvegarder le monde");
        dialog.setContentText("Nom du fichier :");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            delimiter = result.get();
            try {
                mondeIG.sauver(delimiter);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur sauvegarde");
                alert.setHeaderText("Erreur lors de la sauvegarde");
                alert.setContentText(e.getMessage());
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
