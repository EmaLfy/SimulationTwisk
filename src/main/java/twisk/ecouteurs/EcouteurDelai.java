package twisk.ecouteurs;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.util.Duration;
import twisk.exceptions.TwiskException;
import twisk.mondeIG.MondeIG;

public class EcouteurDelai implements EventHandler<ActionEvent> {

    private MondeIG m;

    public EcouteurDelai(MondeIG m) {
        this.m = m;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog textInputDialog = new TextInputDialog("3");
        textInputDialog.setHeaderText("Saisissez un nouveau\ndélai pour l'activité\nsupérieur ou égal à 0");
        textInputDialog.setTitle("Changer le délai");
        textInputDialog.showAndWait();
        String s = textInputDialog.getEditor().getText();
        try{
            m.changeDelaiEtapeSelct(s);
        }
        catch (TwiskException e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur Delai");
            a.setContentText("Veillez à ne pas avoir entré un délai négatif\nou une lettre.");
            a.show();
            PauseTransition p = new PauseTransition(Duration.seconds(5));
            p.setOnFinished(event -> {
                a.close();
            });
            p.play();
        }

    }
}
