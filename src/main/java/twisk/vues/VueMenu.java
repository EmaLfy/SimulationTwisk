package twisk.vues;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import twisk.ecouteurs.*;
import twisk.mondeIG.MondeIG;
import twisk.outils.ThreadsManager;

import java.io.IOException;

public class VueMenu extends MenuBar implements Observateur{
    private MondeIG mondeIG;
    private MenuItem mItR, mItS, mItD, mItSel, mItEn, mItSo, mItDelai, mItEcart, mItJetons, mItUni, mItGauss, mItExp;
    public VueMenu(MondeIG m) {
        super();
        this.mondeIG = m;

        Menu menuF = new Menu("Fichier");
        MenuItem mItSave = new MenuItem("Sauvegarder");
        MenuItem mItLoad = new MenuItem("Charger");

        MenuItem mItQ = new MenuItem("Quitter");
        mItQ.setOnAction(e -> {
            ThreadsManager.getInstance().detruireTout();
            Platform.exit();
        });
        mItSave.setOnAction(new EcouteurSave(this.mondeIG));
        mItLoad.setOnAction(new EcouteurLoad(this.mondeIG));
        menuF.getItems().add(mItSave);
        menuF.getItems().add(mItLoad);
        menuF.getItems().add(mItQ);

        Menu menuE = new Menu("Edition");

        this.mItS = new MenuItem("Supprimer");
        this.mItS.setDisable(true);
        this.mItR = new MenuItem("Renommer");
        this.mItR.setDisable(true);
        this.mItSel = new MenuItem("Sélectionner");
        this.mItSel.setDisable(false);
        this.mItD = new MenuItem("Désélectionner");
        this.mItD.setDisable(true);


        Menu mItP = new Menu("Paramètres");

        this.mItDelai = new MenuItem("Délai");
        this.mItDelai.setOnAction(new EcouteurDelai(this.mondeIG));
        this.mItDelai.setDisable(true);

        this.mItEcart = new MenuItem("Ecart");
        this.mItEcart.setOnAction(new EcouteurEcart(this.mondeIG));
        this.mItEcart.setDisable(true);

        this.mItJetons = new MenuItem("Jetons");
        this.mItJetons.setOnAction(new EcouteurJetons(this.mondeIG));
        this.mItJetons.setDisable(true);

        mItP.getItems().addAll(mItDelai, mItEcart, mItJetons);

        this.mItSel.setOnAction(e -> this.mondeIG.selectTout());
        this.mItSel.setAccelerator(new KeyCodeCombination(KeyCode.S , KeyCombination.CONTROL_DOWN));
        this.mItD.setOnAction(e -> this.mondeIG.deselectTout());
        this.mItD.setAccelerator(new KeyCodeCombination(KeyCode.D , KeyCombination.CONTROL_DOWN));


        this.mItS.setOnAction(new EcouteurSupprimer(this.mondeIG));
        this.mItS.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));
        this.mItR.setOnAction(new EcouteurRenommer(this.mondeIG));
        this.mItR.setAccelerator(new KeyCodeCombination(KeyCode.R , KeyCombination.CONTROL_DOWN));

        menuE.getItems().add(this.mItS);
        menuE.getItems().add(this.mItR);
        menuE.getItems().add(mItP);
        menuE.getItems().add(this.mItSel);
        menuE.getItems().add(this.mItD);

        Menu menuM = new Menu("Monde");

        this.mItEn = new MenuItem("Entrée");
        this.mItEn.setOnAction(e->{this.mondeIG.setEntree();});
        this.mItEn.setDisable(true);
        this.mItSo = new MenuItem("Sortie");
        this.mItSo.setOnAction(e->{this.mondeIG.setSortie();});
        this.mItSo.setDisable(true);
        MenuItem mItCl = new MenuItem("Comportement clients");
        mItCl.setOnAction(new EcouteurClients(this.mondeIG));

        Menu mItL = new Menu("Lois");

        this.mItUni = new MenuItem("Uniforme");
        this.mItUni.setOnAction(new EcouteurUni(this.mondeIG));
        this.mItUni.setDisable(true);

        this.mItGauss = new MenuItem("Gaussienne");
        this.mItGauss.setOnAction(new EcouteurGauss(this.mondeIG));
        this.mItGauss.setDisable(true);

        this.mItExp = new MenuItem("Exponentielle");
        this.mItExp.setOnAction(new EcouteurExp(this.mondeIG));
        this.mItExp.setDisable(true);

        mItL.getItems().addAll(mItUni, mItGauss, mItExp);


        menuM.getItems().add(this.mItEn);
        menuM.getItems().add(this.mItSo);
        menuM.getItems().add(mItCl);
        menuM.getItems().add(mItL);

        this.getMenus().add(menuF);
        this.getMenus().add(menuE);
        this.getMenus().add(menuM);

        this.mItGauss.setDisable(false);
        this.mItExp.setDisable(false);

    }

    @Override
    public void reagir() {

        this.mItR.setDisable(false);
        this.mItS.setDisable(false);
        this.mItSel.setDisable(false);
        this.mItD.setDisable(false);
        this.mItSo.setDisable(false);
        this.mItEn.setDisable(false);
        this.mItDelai.setDisable(false);
        this.mItEcart.setDisable(false);
        this.mItJetons.setDisable(false);

        if (this.mondeIG.getNbEtapeSlect()!=1){
            this.mItR.setDisable(true); // renommer
            this.mItSo.setDisable(true); // sortie
            this.mItEn.setDisable(true); // entree
            this.mItDelai.setDisable(true); // delai
            this.mItEcart.setDisable(true); // ecart
            this.mItJetons.setDisable(true); // jetons

        }
        if(this.mondeIG.nbActSelect()==1 && this.mondeIG.nbGuichetSelect()==0){
            this.mItJetons.setDisable(true);
            this.mItDelai.setDisable(false); // delai
            this.mItEcart.setDisable(false); // ecart
        }
        if(this.mondeIG.nbGuichetSelect()==1 && this.mondeIG.nbActSelect()==0){
            this.mItJetons.setDisable(false);
            this.mItDelai.setDisable(true); // delai
            this.mItEcart.setDisable(true); // ecart
        }

        if(this.mondeIG.getNbEtapeSlect()==0 && !this.mondeIG.arcSelct()){
            this.mItS.setDisable(true);
            this.mItD.setDisable(true);

        }
        if (this.mondeIG.getNbEtapeSlect() == this.mondeIG.getNbEtapes()){
            this.mItSel.setDisable(true);
        }

        if(this.mondeIG.getLoi()== 0){
            this.mItUni.setDisable(true);
            this.mItGauss.setDisable(false);
            this.mItExp.setDisable(false);
        }else if(this.mondeIG.getLoi()== 1){
            this.mItUni.setDisable(false);
            this.mItGauss.setDisable(true);
            this.mItExp.setDisable(false);
        }else if(this.mondeIG.getLoi()== 2){
            this.mItUni.setDisable(false);
            this.mItGauss.setDisable(false);
            this.mItExp.setDisable(true);
        }
    }
}
