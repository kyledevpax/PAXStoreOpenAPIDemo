import com.pax.market.api.sdk.java.api.merchant.dto.MerchantCreateRequest;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerCreateRequest;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.LinkedHashMap;

public class addMerchantWindow {



    public static void display(final String resName) {

        final Reseller res = new Reseller();
        final Merchant merc = new Merchant();
        //============delete=============
        final LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("abc", "xyz");
        //===========delete=============

        final Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Merchant Information");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Text scenetitle = new Text("Merchant Information:");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle,  0,  0, 2, 1);

        Label mercNameL = new Label("Name: ");
        grid.add(mercNameL, 0, 1);
        final TextField mercNameTF = new TextField();
        grid.add(mercNameTF, 1,1);

        Label mercEmailL = new Label("Email: ");
        grid.add(mercEmailL, 0, 2);
        final TextField mercEmailTF = new TextField();
        grid.add(mercEmailTF, 1,2);

        /*Label mercResellerL = new Label("Reseller: ");
        grid.add(mercResellerL,0,3);
        final TextField mercResellerTF = new TextField();
        grid.add(mercResellerTF,1,3);*/

        final Label mercContactL = new Label("Contact: ");
        grid.add(mercContactL, 0, 3);
        final TextField mercContactTF = new TextField();
        grid.add(mercContactTF, 1,3);

        Label mercCountryL = new Label("Country: ");
        grid.add(mercCountryL, 0, 4);
        final TextField mercCountryTF = new TextField();
        grid.add(mercCountryTF, 1,4);

        Label mercPhoneL = new Label("Phone: ");
        grid.add(mercPhoneL, 0, 5);
        final TextField mercPhoneTF = new TextField();
        grid.add(mercPhoneTF, 1,5);

        Label mercPostCodeL = new Label("Postal Code: ");
        grid.add(mercPostCodeL, 0, 6);
        final TextField mercPostCodeTF = new TextField();
        grid.add(mercPostCodeTF, 1,6);

        Label mercAddressL = new Label("Address: ");
        grid.add(mercAddressL, 0, 7);
        final TextField mercAddressTF = new TextField();
        grid.add(mercAddressTF, 1,7);

        Label mercDescriptionL = new Label("Description: ");
        grid.add(mercDescriptionL,0,8);
        final TextField mercDescriptionTF = new TextField();
        grid.add(mercDescriptionTF,1,8);

        /*Label mercStatusL = new Label("Status: ");
        grid.add(mercAddressL,0,10);
        final TextField mercStatusTF = new TextField();
        grid.add(mercStatusTF,1,10);*/

        HBox btnbox = new HBox();
        btnbox.setPadding(new Insets(10));
        Button btn= new Button("Add Merchant");
        btnbox.getChildren().addAll(btn);
        grid.add(btnbox, 1, 9);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                MerchantCreateRequest request = merc.CreateMerchantRequest(mercNameTF.getText(), mercEmailTF.getText(),resName, mercContactTF.getText(), mercCountryTF.getText(), mercPhoneTF.getText(), mercPostCodeTF.getText(), mercAddressTF.getText(), mercDescriptionTF.getText(), true,  null, null);
                merc.createAMerchant(request);
                displayMerchantWindow.merc_listView.setItems(FXCollections.observableArrayList(displayMerchantWindow.getMerchantList(resName)));//refreshes the list
                popupwindow.close();
            }
        });


        Scene scene1= new Scene(grid, 300, 425);

        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }
}
