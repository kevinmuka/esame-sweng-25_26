package it.unimi.di.sweng.esame.view;

import it.unimi.di.sweng.esame.presenter.InputPresenter;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public class InputEsitoGara extends Region implements InputView {

    final TextField text1 = new TextField();
    final TextField text2 = new TextField();
    final Button button = new Button("Inserisci");


    @NotNull
    private final Label error;


    public InputEsitoGara() {
        setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5.0), Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, new CornerRadii(5.0), new BorderWidths(2))));
        error = new Label("");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));

        grid.add(text1, 0, 0);
        grid.add(text2, 1, 0);
        grid.add(button, 2, 0);
        grid.add(error, 0, 1);

        this.getChildren().add(grid);
    }

    public void addHandlers(@NotNull InputPresenter presenter) {
        button.setOnAction(eh -> presenter.action1(text1.getText(), text2.getText()));
    }

    @Override
    public void showError(@NotNull String s) {
        error.setText(s);
        setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(5.0), Insets.EMPTY)));
    }

    @Override
    public void showSuccess() {
        error.setText("");
        text1.setText("");
        text2.setText("");
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5.0), Insets.EMPTY)));
    }
}

