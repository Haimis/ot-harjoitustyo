
package kurssilaskuri.ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kurssilaskuri.logics.CoursesService;
import kurssilaskuri.logics.DataPoint;
import kurssilaskuri.logics.ETF;

public class Ui extends Application {
    CoursesService cs;
    
    
    @Override
    public void start(Stage window) throws IOException {

        this.cs = new CoursesService();    
        BorderPane layout = new BorderPane();
        Button button = new Button("Laske");
        Label header = new Label("Valitse vertailujakson ensimmäinen ja viimeinen päivämäärä");
        
        
        //line chart
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("time");
        yAxis.setLabel("price difference");
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        XYChart.Series compareETF = new XYChart.Series();
        lineChart.setAnimated(false);        
        
        //sliders
        Slider firstSlider = new Slider();
        firstSlider.setMin(1);
        firstSlider.setMax(31);
        firstSlider.setValue(1);

        Slider lastSlider = new Slider();
        lastSlider.setMin(1);
        lastSlider.setMax(31);
        lastSlider.setValue(31);
        
        //dayPicker layout
        HBox dayPicker = new HBox();
        Label first = new Label("alkupäivä: ");
        Label firstDate = new Label("1");
        Label last = new Label("loppupäivä: ");
        Label lastDate = new Label("31");
        dayPicker.getChildren().addAll(first, firstDate, firstSlider, last, lastDate, lastSlider);

        //checkboxs (don't work yet)
        VBox Etfs = new VBox();
        Label head = new Label("Valitse vertailtavat (Ei vielä käytössä)");
        Etfs.getChildren().add(head);
        for (ETF e : cs.getList()) {
            CheckBox cb = new CheckBox(e.getName());
            cb.setIndeterminate(false);
            Etfs.getChildren().add(cb);
        }
        
        //result
        HBox resultBox = new HBox();
        Label resultBoxheader = new Label("Vertailun tulos: ");
        Label resultText = new Label("");
        resultBox.getChildren().addAll(resultBoxheader, resultText);
        
        //functions
        VBox functions = new VBox();
        functions.getChildren().add(header);
        functions.getChildren().add(dayPicker);
        functions.getChildren().add(Etfs);
        functions.getChildren().add(button);
        functions.getChildren().add(resultBox);
        layout.setLeft(functions);
        layout.setCenter(lineChart);
        
        
        //eventHandlers
        button.setOnAction((ActionEvent event) -> {
            lineChart.getData().clear();
            compareETF.getData().clear();
            String result = cs.getAverage(Integer.parseInt(firstDate.getText()), Integer.parseInt(lastDate.getText()));
            resultText.setText(result);
            //test for graphs
            List<Double> list = cs.monthlyDifference(Integer.parseInt(firstDate.getText()), Integer.parseInt(lastDate.getText()));
            for (int i = 0; i < list.size(); i++) {
                compareETF.getData().add(new XYChart.Data(i + 1, list.get(i)));
            }
            
            lineChart.getData().add(compareETF);
            

            
        });

        firstDate.textProperty().bind(
            Bindings.format(
                "%.0f",
                firstSlider.valueProperty()
            )
        );
        
        lastDate.textProperty().bind(
            Bindings.format(
                "%.0f",
                lastSlider.valueProperty()
            )
        );

        Scene startScene = new Scene(layout);
        
        window.setTitle("Kurssilaskuri");
        window.setScene(startScene);
        window.setWidth(1200);
        window.setHeight(400);
        window.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
