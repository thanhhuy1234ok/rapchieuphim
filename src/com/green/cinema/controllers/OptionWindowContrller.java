package com.green.cinema.controllers;

import com.green.cinema.emum.ColorTheme;
import com.green.cinema.emum.FontSize;
import com.green.cinema.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionWindowContrller extends BaseController implements Initializable {
    public OptionWindowContrller(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }


    private final String TAG = "Show";

    @FXML
    private Slider SliderFont;

    @FXML
    private ChoiceBox<ColorTheme> BoxColor;

    @FXML
    private Label lbFontSize;

    @FXML
    private Label lbColorTheme;

    @FXML
    private Button buttonApply;

    @FXML
    private Button buttonCancel;

    @FXML
    void buttonActionApply(ActionEvent event) {
        System.out.println(TAG + "buttonActionApply");
        closeStage();
    }

    @FXML
    void buttonActionCancel(ActionEvent event) {
        System.out.println(TAG + "buttonActionCancel");
        closeStage();
    }

    private void closeStage() {
        Stage stage = (Stage) buttonApply.getScene().getWindow();
        viewFactory.closeStage(stage);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initChoiceBoxTheme();
        initSliderFontSize();
    }

    private void initSliderFontSize() {
        SliderFont.setMin(0);
        SliderFont.setMax(FontSize.values().length - 1);
        SliderFont.setValue(viewFactory.getFontSize().ordinal());
        SliderFont.setMajorTickUnit(1);
        SliderFont.setMinorTickCount(0);
        SliderFont.setBlockIncrement(1);
        SliderFont.setSnapToTicks(true);
        SliderFont.setShowTickLabels(true);
        SliderFont.setShowTickMarks(true);
        SliderFont.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double aDouble) {
                int i = aDouble.intValue();
                return FontSize.values()[i].toString();
            }

            @Override
            public Double fromString(String s) {
                return null;
            }
        });

    }

    private void initChoiceBoxTheme() {
        BoxColor.setItems(FXCollections.observableArrayList(ColorTheme.values()));
        BoxColor.setValue(viewFactory.getColorTheme());

    }
}
