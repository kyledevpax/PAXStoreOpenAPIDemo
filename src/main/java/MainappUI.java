
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerCreateRequest;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.reseller.ResellerApi;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerCreateRequest;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerDTO;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerPageDTO;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerUpdateRequest;

import java.util.LinkedHashMap;
import java.util.List;


public class MainappUI extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Web API Demo");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Scene scene = new Scene(grid, 600, 500);
        primaryStage.setScene(scene);

        Text scenetitle = new Text("Resellers");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle,  0,  0, 2, 1);

        //Replace user name with list of Resellers
        //Accordion reselleracc = new Accordion();




        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        Merchant mer = new Merchant();
        final Reseller res = new Reseller();
        Button addResellerbtn = new Button();
        addResellerbtn.setText("Add a Reseller");
        addResellerbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //Replace with a function call
                /*LinkedHashMap<String,String> attrs = new LinkedHashMap<String,String>();
                attrs.put("code", "ABC");
                ResellerCreateRequest request=res.createResellerRequest("reseller_2","rs1@gmail.com","USA","5566887766","4078995566",null,null,null,null,attrs);
                Result<ResellerDTO> result= res.createAReseller(request);
                System.out.println(result);*/
                /*Result<ResellerPageDTO> result=res.searchforReseller(1,10,null,null,null);
                System.out.println(result.getPageInfo());
                List<ResellerPageDTO> list=result.getPageInfo().getDataSet();
                for(ResellerPageDTO page:list){
                    System.out.println(page.getName());
                }*/
                NameIdPair pairs=res.getNameandIDofResellers();
                int limit=pairs.getTopIndex();
                for(int i=0;i<=limit;i++)
                System.out.println(pairs.getId(i)+","+pairs.getName(i));
            }
        });
        grid.add(addResellerbtn, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw,  0,  2);

        Button deleteResellerbtn = new Button();
        deleteResellerbtn.setText("Delete");
        deleteResellerbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //Call function to Delete the Reseller

                System.out.println("Reseller Deleted");
            }
        });
        grid.add(deleteResellerbtn, 1, 2);

        //root.getChildren().add(addResellerbtn);
        //root.getChildren().add(deleteResellerbtn);


        primaryStage.show();
    }

}

