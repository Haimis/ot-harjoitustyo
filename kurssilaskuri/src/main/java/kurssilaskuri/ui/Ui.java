
package kurssilaskuri.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kurssilaskuri.logics.CoursesService;

public class Ui extends Application {
    CoursesService cs;
    
    @Override
    public void start(Stage window) throws IOException {

        this.cs = new CoursesService();    
        BorderPane layout = new BorderPane();
        Button button = new Button("Laske");
        Label header = new Label("Valitse vertailujakson ensimmäinen ja viimeinen päivämäärä");
        
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

        
        
        HBox resultBox = new HBox();
        Label resultBoxheader = new Label("Vertailun tulos: ");
        Label resultText = new Label("");
        resultBox.getChildren().addAll(resultBoxheader, resultText);
        
        //functions
        VBox functions = new VBox();
        functions.getChildren().add(header);
        functions.getChildren().add(dayPicker);
        functions.getChildren().add(button);
        functions.getChildren().add(resultBox);
        
        layout.setLeft(functions);
        
        button.setOnAction((event) -> {
            String result = cs.getAverage(Integer.parseInt(firstDate.getText()), Integer.parseInt(lastDate.getText()));
            resultText.setText(result);
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
        window.setWidth(800);
        window.setHeight(400);
        window.show();
    }

    public static void main(String[] args) {
        launch(Ui.class);
    }
  
}
