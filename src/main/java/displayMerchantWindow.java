import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class displayMerchantWindow {

    static NameIdPairs mercNIDP;
    static NameIdPairs termNIDP;
    static TextArea mercInfoTF = new TextArea();
    static Long selectedID;
    static ListView<String> merc_listView;

    public static void display(Long resID) {

        final Stage mercWindow = new Stage();
        Reseller res = new Reseller();
        final Merchant merc = new Merchant();
        final Terminal term = new Terminal();
        final String resName = res.searchForSpecificReseller(resID).getData().getName();


        mercWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Scene scene = new Scene(grid, 600, 500);
        mercWindow.setScene(scene);

        Text scenetitle = new Text(resName + "'s Merchants");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle,  0,  0, 2, 1);

        HBox hbox = new HBox();
        hbox.setSpacing(10);

        mercNIDP = merc.getNameandIDofMerchants(res.searchForSpecificReseller(resID).getData().getName());
        ArrayList<String> list = new ArrayList<String>();

        for (int i =0; i<= mercNIDP.getTopIndex();i++){
            list.add(mercNIDP.getName(i));
        }

        // create a list of items.
        merc_listView = new ListView<String>(FXCollections.observableArrayList(list));
        merc_listView.prefWidth(200);
        merc_listView.setMaxWidth(250);
        merc_listView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {

                    public void changed(
                            ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {
                        // change the label text value to the newly selected
                        // item
                        for(int i=0; i<=mercNIDP.getTopIndex();i++){
                            if(mercNIDP.getName(i).equals(newValue)){
                                selectedID = new Long (mercNIDP.getId(i));
                            }

                        }
                        //System.out.println(newValue);//change to a call
                        mercInfoTF.setText(Helper.printMerchantResultString(merc.searchForSpecificMerchant(selectedID)));
                    }
                });

        merc_listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        System.out.println("Double clicked");// start terminal window
                        //displayMerchantWindow.display(selectedID);
                    }
                }
            }
        });
        grid.add(merc_listView, 0, 1);

        final Button addMerchantbtn = new Button();
        addMerchantbtn.setText("Add a Merchant");
        addMerchantbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                addMerchantWindow.display(resName);

            }
        });

        final Button deleteMerchantbtn = new Button();
        deleteMerchantbtn.setText("Delete");
        deleteMerchantbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //deleteResellerWindow.display(selectedID);

            }
        });

        final Button activateMerchantbtn = new Button();
        activateMerchantbtn.setText("Activate");
        activateMerchantbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //res.activateAReseller(selectedID);
                refreshmerc();
            }
        });

        final Button deactivateMerchantbtn = new Button();
        deactivateMerchantbtn.setText("Deactivate");
        deactivateMerchantbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //res.disableAReseller(selectedID);
                refreshmerc();
            }
        });

        final Button resrefmerchbtn = new Button();
        resrefmerchbtn.setText("Refresh");
        resrefmerchbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                refreshmerc();
            }
        });

        //buttons for Reseller page
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 0, 20, 20));

        addMerchantbtn.setMaxWidth(Double.MAX_VALUE);
        deleteMerchantbtn.setMaxWidth(Double.MAX_VALUE);
        deactivateMerchantbtn.setMaxWidth(Double.MAX_VALUE);
        activateMerchantbtn.setMaxWidth(Double.MAX_VALUE);
        resrefmerchbtn.setMaxWidth(Double.MAX_VALUE);


        Label mercInfoL = new Label("Information");
        mercInfoTF.setMinWidth(200);
        mercInfoTF.setMinHeight(200);
        mercInfoTF.setPrefWidth(200);
        mercInfoTF.setPrefHeight(200);

        VBox vbButtons = new VBox();
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(0, 20, 10, 20));
        vbButtons.getChildren().addAll(addMerchantbtn, deleteMerchantbtn, activateMerchantbtn, deactivateMerchantbtn, resrefmerchbtn, mercInfoL, mercInfoTF);

        grid.add(vbButtons, 2,1,2,2);

        //mercWindow.show();

        mercWindow.setScene(scene);
        mercWindow.showAndWait();

    }
    //refreshes merchant list
    public static ArrayList<String> getMerchantList(String resName){
        Merchant merc = new Merchant();
        NameIdPairs pairs = merc.getNameandIDofMerchants(resName);

        ArrayList<String> list = new ArrayList<String>();
        for (int i =0; i<= pairs.getTopIndex();i++){
            list.add(pairs.getName(i));
        }
        return list;
    }

    public static void refreshmerc(){
        Merchant merc = new Merchant();
        mercInfoTF.setText("");
        mercInfoTF.setText(Helper.printMerchantResultString(merc.searchForSpecificMerchant(selectedID)));
    }
}
