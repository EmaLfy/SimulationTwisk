package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import twisk.mondeIG.MondeIG;

import java.util.Optional;

public class EcouteurRenommer implements EventHandler<ActionEvent> {

    private MondeIG m;

    public EcouteurRenommer(MondeIG m) {
        this.m = m;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String delimiter;
        TextInputDialog textInputDialog = new TextInputDialog("Nouveau nom");
        textInputDialog.setHeaderText("Saisissez une nouveau\npour la selection");
        textInputDialog.setTitle("Renommer la selection");

        Optional<String> result = textInputDialog.showAndWait();
        if (result.isPresent()) {
            delimiter=result.get();
            m.renommeEtapeSelct(delimiter);

        }
    }
}
