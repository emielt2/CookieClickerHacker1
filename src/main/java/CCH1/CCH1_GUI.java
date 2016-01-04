package CCH1;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;

public class CCH1_GUI extends Application {
    static int counter=0;
    @Override
    public void start(Stage primaryStage) {
        System.out.println("STARTED");
        primaryStage.setTitle("CookieClickerHacker1.0");
        GridPane grid1 = new GridPane();
        grid1.setAlignment(Pos.TOP_LEFT);
        grid1.setHgap(10);
        grid1.setVgap(10);
        grid1.setPadding(new Insets(25, 25, 25, 25));
//--buttonGo
        Button buttonGo = new Button("Open session URL");//buttonGo
        HBox hbButtonGo = new HBox(40);//buttonGo
        hbButtonGo.setAlignment(Pos.TOP_LEFT);//buttonGo
        hbButtonGo.getChildren().add(buttonGo);//buttonGo
        grid1.add(hbButtonGo, 0, 5);//buttonGo
///---
        //---
        Button buttonShow = new Button("Close session");
        HBox hbButtonShow = new HBox(40);
        hbButtonShow.setAlignment(Pos.TOP_LEFT);
        hbButtonShow.getChildren().add(buttonShow);
        grid1.add(hbButtonShow, 0, 6);
        ///---
        //---

        Button buttonClicking = new Button("CLICKCLICK");
        HBox hbButtonClicking = new HBox(40);
        hbButtonClicking.setAlignment(Pos.TOP_LEFT);
        hbButtonClicking.getChildren().add(buttonClicking);
        grid1.add(hbButtonClicking, 1, 5);

        ///---
        //---

        Button buttonBestBuyProd = new Button("BestBuyProd");
        HBox hbButtonBestBuyProd = new HBox(40);
        hbButtonBestBuyProd.setAlignment(Pos.TOP_LEFT);
        hbButtonBestBuyProd.getChildren().add(buttonBestBuyProd);
        grid1.add(hbButtonBestBuyProd, 1, 7);

        ///---
        //---

        Button buttonBuyNewItem = new Button("BuyNewItem");
        HBox hbButtonBuyNewItem = new HBox(40);
        hbButtonBuyNewItem.setAlignment(Pos.TOP_LEFT);
        hbButtonBuyNewItem.getChildren().add(buttonBuyNewItem);
        grid1.add(hbButtonBuyNewItem, 1, 8);

        ///---
//--checkbox
        final CheckBox cb1 = new CheckBox("Keep Looping");
        cb1.setSelected(true);
        grid1.add(cb1,1,9);

        //grid1.add(cb1);
///-----

        Separator sepHor1 = new Separator();
        Separator sepHor2 = new Separator();
        Separator sepVer = new Separator();//Vertical separator
        sepVer.setOrientation(Orientation.VERTICAL);//Vertical separator
        final Text actiontarget = new Text();
        grid1.add(actiontarget, 1, 6);
        Text scenetitle = new Text("Welcome Citizen47281");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid1.add(scenetitle, 0, 1/*, 1, 1*/);

        final Text scenetitle2 = new Text("Last results:");
        scenetitle2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid1.add(scenetitle2, 0, 7/*, 10, 10*/);

        final TextField urlInputField = new TextField("http://orteil.dashnet.org/cookieclicker/");
        final TextField toInputField = new TextField();
        final SeleniumDAO browser1 = new SeleniumDAO(urlInputField.getText());
        try {
            browser1.startSeleniumConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //butonGo extra
        buttonGo.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.BLUE);
                actiontarget.setText("Processing... " + counter ++);
                try {
                    System.out.println("Go clicked");
                    browser1.startSeleniumConnection();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });

        buttonShow.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {

                try {
                    System.out.println("Show clicked");
                    browser1.stopSeleniumConnection();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        buttonClicking.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {

                try {
                    System.out.println("CLICKING clicked");
                    while(cb1.isSelected()&&browser1.trigIsSelected()){
                        browser1.multiClickCookie();//                    id=bigCookie
                        System.out.println(cb1.isSelected());
                        browser1.bestBuyProd();
                        browser1.buyNextItem();
                        Thread.sleep(1000);

                    }
                    System.out.println("CLICKING ENDED");
                } catch (Exception e1) {

                    e1.printStackTrace();
                }
            }
        });

        buttonBestBuyProd.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {

                try {
                    System.out.println("buybestbutton clicked");
                    browser1.bestBuyProd();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        buttonBuyNewItem.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {

                try {
                    System.out.println("buy new item clicked");
                    browser1.buyNextItem();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        String[][] staffArray =
                {
                        {"Uid", "FROM", "TO","Google","Soap"},
                        {"a", "b", "c","x","y"},
                        {"d", "e", "f","x","y"}
                };

        System.out.println("Check E1");
        System.out.println("Check E2");

        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(staffArray));
        data.remove(0);//remove titles from data

        TableView<String[]> table = new TableView<>();
        for (int i = 0; i < staffArray[0].length; i++) {
            TableColumn tc = new TableColumn(staffArray[0][i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setPrefWidth(90);
            table.getColumns().add(tc);
        }
        table.setItems(data);


//--SETTING CONTENT OF GRIDS
       GridPane grid2 = new GridPane();
        Label userName = new Label("FROM valuta:");
        grid1.add(userName, 0, 3);
        grid1.add(urlInputField, 1, 3);
        //String input1=urlInputField.getText();
        grid1.add(toInputField, 1, 4);

        grid1.add(table,2,0,1,70);

        primaryStage.setScene(new Scene(grid1, 900, 500));
        //todo add stuff to scene ofzo  https://community.oracle.com/thread/2587213?start=0&tstart=0
        primaryStage.getScene().fillProperty();
        primaryStage.alwaysOnTopProperty();
        primaryStage.show();
    }
///------

    public static void main(String[] args) {
        launch(args);
    }
}
