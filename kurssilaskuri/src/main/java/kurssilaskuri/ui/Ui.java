
package kurssilaskuri.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kurssilaskuri.logics.CoursesService;
import kurssilaskuri.model.Etf;

public class Ui extends Application {
    private CoursesService cs;
    
    @Override
    public void start(Stage window) throws IOException {

        this.cs = new CoursesService();    
        BorderPane layout = new BorderPane();
        Button button = new Button("Laske");
        Text infoBox = new Text(cs.infoBox());
        infoBox.wrappingWidthProperty().set(600);
        
        //sliders
        Slider firstSlider = generateSlider(1);
        Slider lastSlider = generateSlider(5);
        Slider firstSlider2 = generateSlider(6);
        Slider lastSlider2 =  generateSlider(7);
        
        //dayPickers
        HBox dayPicker = new HBox();
        Label first = new Label("alkupäivä: ");
        Label firstDate = new Label("1");
        Label last = new Label("loppupäivä: ");
        Label lastDate = new Label("5");
        dayPicker.getChildren().addAll(new Label("1. vertailujakso "), first, firstDate, firstSlider, last, lastDate, lastSlider);
        
        HBox dayPicker2 = new HBox();
        Label first2 = new Label("alkupäivä: ");
        Label firstDate2 = new Label("6");
        Label last2 = new Label("loppupäivä: ");
        Label lastDate2 = new Label("7");
        dayPicker2.getChildren().addAll(new Label("2. vertailujakso "), first2, firstDate2, firstSlider2, last2, lastDate2, lastSlider2);
        
        //checkboxs
        VBox etfs = new VBox();
        Label head = new Label("Valitse vertailtavat rahastot");
        List<String> checked = new ArrayList<>();
        etfs.getChildren().add(head);
        for (Etf e : cs.getList()) {
            CheckBox cb = new CheckBox(e.getName());
            cb.setIndeterminate(false);
            etfs.getChildren().add(cb);
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    if (cb.isSelected()) {
                        checked.add(cb.getText());
                    } else {
                        checked.remove(cb.getText());
                    }
                }
            };
            cb.setOnAction(event);
        }
        
        //result
        HBox resultBox = new HBox();
        Label resultBoxheader = new Label("Vertailun tulos: ");
        Label resultText = new Label("");
        resultBox.getChildren().addAll(resultBoxheader, resultText);
        
        //functions
        VBox functions = new VBox();
        functions.getChildren().add(infoBox);
        functions.getChildren().add(dayPicker);
        functions.getChildren().add(dayPicker2);
        functions.getChildren().add(etfs);
        functions.getChildren().add(button);
        functions.getChildren().add(resultBox);
        layout.setLeft(functions);
        
        
        //eventHandlers
        button.setOnAction((ActionEvent event) -> {
            String result = cs.generateComparison(checked, Integer.parseInt(firstDate.getText()), Integer.parseInt(lastDate.getText()), 
                    Integer.parseInt(firstDate2.getText()), Integer.parseInt(lastDate2.getText()));
            resultText.setText(result);
            
        });
        
        firstDate.textProperty().bind(
            Bindings.format(
                "%.0f",
                firstSlider.valueProperty()
            )
        );
        
        firstDate2.textProperty().bind(
            Bindings.format(
                "%.0f",
                firstSlider2.valueProperty()
            )
        );
        
        lastDate.textProperty().bind(
            Bindings.format(
                "%.0f",
                lastSlider.valueProperty()
            )
        );
        lastDate2.textProperty().bind(
            Bindings.format(
                "%.0f",
                lastSlider2.valueProperty()
            )
        );
    
        Scene startScene = new Scene(layout);
        
        window.setTitle("Kurssilaskuri");
        window.setScene(startScene);
        window.setWidth(600);
        window.setHeight(700);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public Slider generateSlider(int def) {
        Slider slider = new Slider();
        slider.setMin(1);
        slider.setMax(31);
        slider.setValue(def);
        
        return slider;   
    }

}
