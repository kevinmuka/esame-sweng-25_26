package it.unimi.di.sweng.esame;


import it.unimi.di.sweng.esame.model.Discipline;
import it.unimi.di.sweng.esame.model.Model;
import it.unimi.di.sweng.esame.presenter.*;
import it.unimi.di.sweng.esame.view.DisplayView;
import it.unimi.di.sweng.esame.view.InputEsitoGara;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int NUM_LINES = 5;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Medagliere Olimpiadi Invernali");

        InputEsitoGara input = new InputEsitoGara();

        DisplayView display1 = new DisplayView(NUM_LINES, "Biathlon");
        DisplayView display2 = new DisplayView(NUM_LINES, "Snowboard");
        DisplayView display3 = new DisplayView(NUM_LINES, "Statistiche Top 5 Nazioni");

        GridPane gridPane = new GridPane();
        gridPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.add(input, 0, 3);
        GridPane.setColumnSpan(input, GridPane.REMAINING);

        gridPane.add(display1, 1, 0);
        gridPane.add(display2, 2, 0);

        gridPane.add(display3, 0, 2);
        GridPane.setColumnSpan(display3, GridPane.REMAINING);

        Model model = new Model();
        input.addHandlers(new MyInputPresenter(model, input));
        model.addObserver(new MyOutputPresenter<>(display1,
                new SportSelector(Discipline.BIATHLON),
                new SportByNationOrdering(),
                MedalEntry::toString));
        model.addObserver(new MyOutputPresenter<>(display2,
                new SportSelector(Discipline.SNOWBOARD),
                new SportByMedalOrdering(),
                MedalEntry::toString));
        model.addObserver(new MyOutputPresenter<>(display3,
                new StatsSelector(),
                new NationStatsOrdering(),
                NationStats::toString));

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
