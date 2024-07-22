package twisk.vues;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

public class VueGuichet extends VueEtapeIG {

    public VueGuichet(MondeIG monde, EtapeIG etape) {
        super(monde, etape);
        HBox hBox = new HBox();
        for (int i = 0; i < 10; i++) {
            HBox hBox2 = new HBox();
            hBox2.setMinSize(25 ,25);
            hBox2.setStyle("-fx-border-color: #07fb00; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px; -fx-background-color: lightgrey");
            hBox.getChildren().add(hBox2);
        }
        this.getChildren().add(hBox);


    }

    @Override
    public void reagir() {

    }
}
