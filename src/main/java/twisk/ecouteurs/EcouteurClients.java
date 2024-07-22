package twisk.ecouteurs;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.util.Duration;
import twisk.exceptions.TwiskException;
import twisk.mondeIG.MondeIG;

public class EcouteurClients implements EventHandler<ActionEvent> {

    private MondeIG mondeIG;

    public EcouteurClients(MondeIG m) {
        this.mondeIG = m;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog textInputDialog = new TextInputDialog("3");
        textInputDialog.setHeaderText("Saisissez un nouvel\nécart pour l'activité\nsupérieur ou égal à 0");
        textInputDialog.setTitle("Changer l'écart");
        textInputDialog.showAndWait();
        String s = textInputDialog.getEditor().getText();
        try{
            mondeIG.setNbClients(s);
        }
        catch (TwiskException e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.getDialogPane().setMinWidth(300);
            a.getDialogPane().setMinHeight(200);
            a.setTitle("Erreur nombre de clients");
            a.setHeaderText("Erreur lors de la saisie du nombre de clients");
            a.setContentText("Veillez à ne pas avoir entré un nombre de clients\nnégatifs, une lettre ou un nombre de clients > 50.");
            a.show();
            PauseTransition p = new PauseTransition(Duration.seconds(5));
            p.setOnFinished(event -> {
                a.close();
            });
            p.play();
        }

    }
}
