package it.unimi.di.sweng.esame.view;

import it.unimi.di.sweng.esame.Main;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.assertj.core.util.introspection.FieldSupport;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestIntegrazione {
    private static final boolean HEADLESS = false;
    private InputEsitoGara input;
    private DisplayView[] display;
    private Label errorMessage;
    private Stage stage;

    @BeforeAll
    public static void setupSpec() {
        if (HEADLESS) System.setProperty("testfx.headless", "true");
    }

    @Start
    public void start(Stage primaryStage) {
        Main m = new Main();
        m.start(primaryStage);

        stage = primaryStage;
        GridPane gp = (GridPane) primaryStage.getScene().getRoot();
        ObservableList<Node> view = gp.getChildren();

        input = (InputEsitoGara) view.get(0);
        display = new DisplayView[3];
        for (int i = 0; i < 3; i++) {
            display[i] = (DisplayView) view.get(i + 1);
        }
        errorMessage = FieldSupport.EXTRACTION.fieldValue("error", Label.class, input);
    }

    @Test
    public void insertTest(FxRobot robot) {
        inserisci("G001,Biathlon", "ITA;GER;FRA", robot);
        inserisci("G002,Biathlon", "NOR;SWE;AUT", robot);
        inserisci("G003,Snowboard", "USA;CAN;JPN", robot);
        inserisci("G004,Snowboard", "ITA;FRA;SUI", robot);
        inserisci("G005,Biathlon", "GER;ITA;NOR", robot);

        // Display 1: Biathlon - ordine per nazione (alfabetico), poi per tipo medaglia
        assertThat(display[0].get(0)).startsWith("AUT").contains("BRONZO").endsWith("G002");
        assertThat(display[0].get(1)).startsWith("FRA").contains("BRONZO").endsWith("G001");
        assertThat(display[0].get(2)).startsWith("GER").contains("ORO").endsWith("G005");
        assertThat(display[0].get(3)).startsWith("GER").contains("ARGENTO").endsWith("G001");
        assertThat(display[0].get(4)).startsWith("ITA").contains("ORO").endsWith("G001");

        // Display 2: Snowboard - ordine per tipo medaglia, poi per nazione (alfabetico)
        assertThat(display[1].get(0)).startsWith("ITA").contains("ORO").endsWith("G004");
        assertThat(display[1].get(1)).startsWith("USA").contains("ORO").endsWith("G003");
        assertThat(display[1].get(2)).startsWith("CAN").contains("ARGENTO").endsWith("G003");
        assertThat(display[1].get(3)).startsWith("FRA").contains("ARGENTO").endsWith("G004");
        assertThat(display[1].get(4)).startsWith("JPN").contains("BRONZO").endsWith("G003");

        // Display 3: Statistiche Top 5 - ordine per ori, argenti, bronzi, alfabetico
        assertThat(display[2].get(0)).startsWith("ITA").contains("Tot: 3").contains("Ori: 2").contains("Argenti: 1").endsWith("Bronzi: 0)");
        assertThat(display[2].get(1)).startsWith("GER").contains("Tot: 2").contains("Ori: 1").contains("Argenti: 1").endsWith("Bronzi: 0)");
        assertThat(display[2].get(2)).startsWith("NOR").contains("Tot: 2").contains("Ori: 1").contains("Argenti: 0").endsWith("Bronzi: 1)");
        assertThat(display[2].get(3)).startsWith("USA").contains("Tot: 1").contains("Ori: 1").contains("Argenti: 0").endsWith("Bronzi: 0)");
        assertThat(display[2].get(4)).startsWith("FRA").contains("Tot: 2").contains("Ori: 0").contains("Argenti: 1").endsWith("Bronzi: 1)");
    }

    @Test
    public void duplicatedError(FxRobot robot) {
        inserisci("G001,Biathlon", "ITA;GER;FRA", robot);
        inserisci("G002,Snowboard", "USA;CAN;JPN", robot);
        inserisci("G001,Sci alpino", "NOR;SWE;AUT", robot);

        verifyThat(errorMessage, hasText("gara già inserita"));
    }

    @ParameterizedTest()
    @CsvSource(delimiter = '|', textBlock = """
          G001,Calcio | ITA;GER;FRA |  disciplina non valida
          G002,Biathlon | ITA;GER  | formato medaglie non valido
          """
    )
    public void testInserisciError(String t1, String t2, String expectedError, FxRobot robot) {
        inserisci(t1, t2, robot);
        verifyThat(errorMessage, hasText(expectedError));
    }

    // TEST UTILITY FUNCTIONS
    private void inserisci(@NotNull String f1, @NotNull String f2, @NotNull FxRobot robot) {
        robot.doubleClickOn(input.text1);
        robot.doubleClickOn(input.text1);
        robot.write(f1, 0);
        robot.doubleClickOn(input.text2);
        robot.doubleClickOn(input.text2);
        robot.write(f2, 0);
        robot.clickOn(input.button);
    }
}
