import com.pax.market.api.sdk.java.api.reseller.dto.ResellerCreateRequest;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.LinkedHashMap;

public class deleteResellerWindow {

    public static void display(final Long idToDelete) {

        final Reseller res = new Reseller();

        final Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Are You Sure?");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Text scenetitle = new Text("Are You Sure?");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle,  0,  0, 2, 1);


        Button yesbtn= new Button("Yes");
        Button nobtn = new Button("No");

        HBox hb = new HBox();
        hb.setSpacing(10);
        hb.setPadding(new Insets(0, 20, 10, 20));
        hb.getChildren().addAll(yesbtn, nobtn);

        yesbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("yes clicked");
                System.out.println("Going to delete\n"+ res.searchForSpecificReseller(idToDelete));
                res.deleteAReseller(idToDelete);
                MainappUI.resInfoTF.setText("");
                MainappUI.m_listView.setItems(FXCollections.observableArrayList(MainappUI.getResellersList()));//refreshes the list
                popupwindow.close();
            }
        });
        nobtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                popupwindow.close();
            }
        });

        grid.add(hb,0,1);

        Scene scene1= new Scene(grid, 225, 150);

        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }
}
