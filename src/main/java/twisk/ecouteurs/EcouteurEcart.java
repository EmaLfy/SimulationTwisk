package twisk.ecouteurs;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.util.Duration;
import twisk.exceptions.TwiskException;
import twisk.mondeIG.MondeIG;

public class EcouteurEcart  implements EventHandler<ActionEvent> {

    private MondeIG m;

    public EcouteurEcart(MondeIG m) {
        this.m = m;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog textInputDialog = new TextInputDialog("3");
        textInputDialog.setHeaderText("Saisissez un nouvel\nécart pour l'activité\nsupérieur ou égal à 0");
        textInputDialog.setTitle("Changer l'écart");
        textInputDialog.showAndWait();
        String s = textInputDialog.getEditor().getText();
        try{
            m.changeEcartEtapeSelct(s);
        }
        catch (TwiskException e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur écart");
            a.setContentText("Veillez à ne pas avoir entré un écart négatif\nou une lettre.");
            a.show();
            PauseTransition p = new PauseTransition(Duration.seconds(5));
            p.setOnFinished(event -> {
                a.close();
            });
            p.play();
        }

    }
}
