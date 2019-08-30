

import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerDTO;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerPageDTO;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
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
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import com.pax.market.api.sdk.java.api.merchant.dto.MerchantCreateRequest;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantUpdateRequest;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerCreateRequest;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerUpdateRequest;
import com.pax.market.api.sdk.java.api.terminal.TerminalApi;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalCreateRequest;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalUpdateRequest;
import com.pax.market.api.sdk.java.api.terminalApk.TerminalApkApi;
import com.pax.market.api.sdk.java.api.terminalApk.dto.CreateTerminalApkRequest;
import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;

import static com.pax.market.api.sdk.java.api.terminal.TerminalApi.*;

public class MainappUI extends Application{

    Reseller res = new Reseller();
    NameIdPairs resNIDP;
    NameIdPairs mercNIDP;
    NameIdPairs termNIDP;

    Merchant merc = new Merchant();
    Terminal term = new Terminal();
    TerminalAPK termApk = new TerminalAPK();
    static TextArea resInfoTF = new TextArea();
    static Long selectedID = new Long(-1);


    static ListView<String> m_listView;


	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {

        //============delete=============
        final LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("abc", "xyz");
        //===========delete=============

        primaryStage.setTitle("Web API Demo");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        final Scene scene = new Scene(grid, 600, 500);
        primaryStage.setScene(scene);

        Text scenetitle = new Text("Resellers");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle,  0,  0, 2, 1);

        //Replace user name with list of Resellers

        HBox hbox = new HBox();
        hbox.setSpacing(10);

        resNIDP = res.getNameandIDofResellers();
        ArrayList<String> list = new ArrayList<String>();

        for (int i =0; i<= resNIDP.getTopIndex();i++){
            list.add(resNIDP.getName(i));
        }

        // create a list of items.
        m_listView = new ListView<String>(FXCollections.observableArrayList(list));
        m_listView.prefWidth(200);
        m_listView.setMaxWidth(250);
        m_listView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {

                    public void changed(
                            ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {
                        // change the label text value to the newly selected
                        // item
                        for(int i=0; i<=resNIDP.getTopIndex();i++){
                            if(resNIDP.getName(i).equals(newValue)){
                                selectedID = new Long (resNIDP.getId(i));
                            }
                        }
                        //System.out.println(newValue);//change to a call
                        resInfoTF.setText(Helper.printResellerResultString(res.searchForSpecificReseller(selectedID)));
                    }
                });

        m_listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        System.out.println("Double clicked");// start merchant window
                        displayMerchantWindow.display(selectedID);
                    }
                }
            }
        });
        grid.add(m_listView, 0, 1);

        Merchant mer = new Merchant();
        Reseller test = new Reseller();
        final Button addResellerbtn = new Button();
        addResellerbtn.setText("Add a Reseller");
        addResellerbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                addResellerWindow.display(primaryStage);

            }
        });

        final Button deleteResellerbtn = new Button();
        deleteResellerbtn.setText("Delete");
        deleteResellerbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                deleteResellerWindow.display(selectedID);
            }
        });

        final Button activateResellerbtn = new Button();
        activateResellerbtn.setText("Activate");
        activateResellerbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                res.activateAReseller(selectedID);
                refresh();
            }
        });

        final Button deactivateResellerbtn = new Button();
        deactivateResellerbtn.setText("Deactivate");
        deactivateResellerbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                res.disableAReseller(selectedID);
                refresh();
            }
        });

        final Button resrefreshbtn = new Button();
        resrefreshbtn.setText("Refresh");
        resrefreshbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                m_listView.setItems(FXCollections.observableArrayList(getResellersList()));//refreshes the list
                refresh();
            }
        });
        //grid.setGridLinesVisible(true);
        //grid.add(addResellerbtn, 1, 0);

        //buttons for Reseller page
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 0, 20, 20));

        addResellerbtn.setMaxWidth(Double.MAX_VALUE);
        deleteResellerbtn.setMaxWidth(Double.MAX_VALUE);
        deactivateResellerbtn.setMaxWidth(Double.MAX_VALUE);
        activateResellerbtn.setMaxWidth(Double.MAX_VALUE);
        resrefreshbtn.setMaxWidth(Double.MAX_VALUE);


        Label resInfoL = new Label("Information");
        resInfoTF.setMinWidth(200);
        resInfoTF.setMinHeight(200);
        resInfoTF.setPrefWidth(200);
        resInfoTF.setPrefHeight(200);

        VBox vbButtons = new VBox();
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(0, 20, 10, 20));
        vbButtons.getChildren().addAll(addResellerbtn, deleteResellerbtn, activateResellerbtn, deactivateResellerbtn, resrefreshbtn, resInfoL, resInfoTF);

        grid.add(vbButtons, 2,1,2,2);

        primaryStage.show();
    }

    //Used to refresh the Reseller list
    public static ArrayList<String> getResellersList(){
        Reseller res=new Reseller();
        NameIdPairs pairs=res.getNameandIDofResellers();

        ArrayList<String> list = new ArrayList<String>();
        for (int i =0; i<= pairs.getTopIndex();i++){
            list.add(pairs.getName(i));
        }
        return list;
    }

    public static void refresh(){
	    Reseller res = new Reseller();
	    resInfoTF.setText("");
        resInfoTF.setText(Helper.printResellerResultString(res.searchForSpecificReseller(selectedID)));
    }
}


