package twisk.vues;

import javafx.scene.layout.HBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

public class VueActiviteIG extends VueEtapeIG{

    public VueActiviteIG(MondeIG monde, EtapeIG etape) {
        super(monde, etape);
        HBox hBox= new HBox();
        hBox.setMinHeight(TailleComposants.getInstance().hBoxHeight());
        hBox.setStyle("-fx-border-color: #0059FF; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px; -fx-background-color: lightgrey");
        this.getChildren().add(hBox);

    }


    @Override
    public void reagir() {

    }
}
