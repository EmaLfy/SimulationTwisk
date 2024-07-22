package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import twisk.vues.VueEtapeIG;

public class EcouteurDnDEtape implements EventHandler<MouseEvent> {

    private VueEtapeIG vueEtapeIG;

    public EcouteurDnDEtape(VueEtapeIG e) {
        this.vueEtapeIG = e;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Dragboard dragboard = this.vueEtapeIG.startDragAndDrop(TransferMode.MOVE);
        WritableImage snap = this.vueEtapeIG.snapshot(null, null);
        ImageView p = new ImageView(snap);
        dragboard.setDragView(p.getImage());
        ClipboardContent content = new ClipboardContent();
        content.putString(this.vueEtapeIG.getId());
        dragboard.setContent(content);
        mouseEvent.consume();
    }
}
