import com.pax.market.api.sdk.java.api.reseller.dto.ResellerCreateRequest;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.*;

import java.util.LinkedHashMap;


public class addResellerWindow {


    public static void display() {

        final Reseller res = new Reseller();
        //============delete=============
        final LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("abc", "xyz");
        //===========delete=============

        final Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Reseller Information");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Text scenetitle = new Text("Reseller Information:");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle,  0,  0, 2, 1);

        Label resNameL = new Label("Name: ");
        grid.add(resNameL, 0, 1);
        final TextField resNameTF = new TextField();
        grid.add(resNameTF, 1,1);

        Label resEmailL = new Label("Email: ");
        grid.add(resEmailL, 0, 2);
        final TextField resEmailTF = new TextField();
        grid.add(resEmailTF, 1,2);

        Label resCountryL = new Label("Country: ");
        grid.add(resCountryL, 0, 3);
        final TextField resCountryTF = new TextField();
        grid.add(resCountryTF, 1,3);

        Label resContactL = new Label("Contact: ");
        grid.add(resContactL, 0, 4);
        final TextField resContactTF = new TextField();
        grid.add(resContactTF, 1,4);

        Label resPhoneL = new Label("Phone: ");
        grid.add(resPhoneL, 0, 5);
        final TextField resPhoneTF = new TextField();
        grid.add(resPhoneTF, 1,5);

        Label resPostCodeL = new Label("Postal Code: ");
        grid.add(resPostCodeL, 0, 6);
        final TextField resPostCodeTF = new TextField();
        grid.add(resPostCodeTF, 1,6);

        Label resAddressL = new Label("Address: ");
        grid.add(resAddressL, 0, 7);
        final TextField resAddressTF = new TextField();
        grid.add(resAddressTF, 1,7);

        Label resCompanyL = new Label("Company: ");
        grid.add(resCompanyL, 0, 8);
        final TextField resCompanyTF = new TextField();
        grid.add(resCompanyTF, 1,8);

        Button btn= new Button("Add Reseller");
        grid.add(btn, 1, 9);
        btn.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            public void handle(ActionEvent event) {
                ResellerCreateRequest request = res.createResellerRequest(resNameTF.getText(), resEmailTF.getText(), resCountryTF.getText(), resContactTF.getText(), resPhoneTF.getText(), resPostCodeTF.getText(), resAddressTF.getText(), resCompanyTF.getText(), null, map);
                res.createAReseller(request);
                popupwindow.close();
            }
        });


        Scene scene1= new Scene(grid, 300, 400);

        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }

}