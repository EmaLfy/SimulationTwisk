package twisk.ecouteurs;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.util.Duration;
import twisk.exceptions.JetonsException;
import twisk.exceptions.TwiskException;
import twisk.mondeIG.MondeIG;

public class EcouteurJetons implements EventHandler<ActionEvent> {
    private MondeIG m;

    public EcouteurJetons(MondeIG m) {
        this.m = m;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog textInputDialog = new TextInputDialog("3");
        textInputDialog.setHeaderText("Saisissez le nombre de jetons\nsupérieur ou égal à 0");
        textInputDialog.setTitle("Definir le nombre de jetons");
        textInputDialog.showAndWait();
        String s = textInputDialog.getEditor().getText();
        try{
            m.setJetonsGuichetSelct(s);
        }
        catch (JetonsException e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur Jetons");
            a.setContentText("Veillez à ne pas avoir entré un nombre de jetons négatifs\nou une lettre.");
            a.show();
            PauseTransition p = new PauseTransition(Duration.seconds(5));
            p.setOnFinished(event -> {
                a.close();
            });
            p.play();
        }

    }

}
