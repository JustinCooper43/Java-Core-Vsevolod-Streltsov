
import com.streltsov.easy.task.Game;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameTest {

    private ByteArrayOutputStream output;
    private final InputStream inputStream = System.in;
    private Game game;

    @BeforeEach
    void setUpMenu() {
        output = new ByteArrayOutputStream();
        game = new Game();
        System.setOut(new PrintStream(output));
    }

    @Test
    @DisplayName("If input is invalid value should show \"Bad parameters!\" message")
    public void shouldShowWarningMessageIfInputIncorrect() {
        String exitCommand = "exit";
        String incorrectCommand = "incorrectCommand";
        String inputLine = incorrectCommand + "\n" + exitCommand;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        game.start();
        String[] resultOutput = output.toString().split("\n");
        String actualResult = resultOutput[resultOutput.length - 2];
        String expectedResult = "Bad parameters!";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("If coordinates is more than 3 app should show \"Coordinates should be from 1 to 3!\" message")
    public void shouldShowWarningMessageIfCoordMoreThanThree() {
        String exitCommand = "exit";
        String startCommand = "start user user";
        String incorrectCoord = "4 4";
        String step2 = "1 1";
        String step3 = "2 2";
        String step4 = "1 2";
        String step5 = "2 1";
        String step6 = "1 3";

        String stringBuilder = startCommand + "\n" +
                incorrectCoord + "\n" +
                step2 + "\n" +
                step3 + "\n" +
                step4 + "\n" +
                step5 + "\n" +
                step6 + "\n" +
                exitCommand;

        ByteArrayInputStream in = new ByteArrayInputStream(stringBuilder.getBytes());
        System.setIn(in);
        game.start();
        boolean isPhrasePresents = output.toString().contains("Coordinates should be from 1 to 3!");
        Assertions.assertTrue(isPhrasePresents);
    }

    @Test
    @DisplayName("If coordinates is less than 1 app should show \"Coordinates should be from 1 to 3!\" message")
    public void shouldShowWarningMessageIfCoordLessThanOne() {
        String exitCommand = "exit";
        String startCommand = "start user user";
        String incorrectCoord = "0 0";
        String step2 = "1 1";
        String step3 = "2 2";
        String step4 = "1 2";
        String step5 = "2 1";
        String step6 = "1 3";

        String stringBuilder = startCommand + "\n" +
                incorrectCoord + "\n" +
                step2 + "\n" +
                step3 + "\n" +
                step4 + "\n" +
                step5 + "\n" +
                step6 + "\n" +
                exitCommand;

        ByteArrayInputStream in = new ByteArrayInputStream(stringBuilder.getBytes());
        System.setIn(in);
        game.start();
        boolean isPhrasePresents = output.toString().contains("Coordinates should be from 1 to 3!");
        Assertions.assertTrue(isPhrasePresents);
    }

    @Test
    @DisplayName("If coordinates is not number app should show \"Coordinates should be from 1 to 3!\" message")
    public void shouldShowWarningMessageIfCoordIsNotNumber() {
        String exitCommand = "exit";
        String startCommand = "start user user";
        String incorrectCoord = "abc";
        String step2 = "1 1";
        String step3 = "2 2";
        String step4 = "1 2";
        String step5 = "2 1";
        String step6 = "1 3";

        String stringBuilder = startCommand + "\n" +
                incorrectCoord + "\n" +
                step2 + "\n" +
                step3 + "\n" +
                step4 + "\n" +
                step5 + "\n" +
                step6 + "\n" +
                exitCommand;

        ByteArrayInputStream in = new ByteArrayInputStream(stringBuilder.getBytes());
        System.setIn(in);
        game.start();
        boolean isPhrasePresents = output.toString().contains("You should enter numbers!");
        Assertions.assertTrue(isPhrasePresents);
    }

    @Test
    @DisplayName("Testing of HARD and HARD mode")
    public void shouldFinishWithDrawResultHARD() {
        String exitCommand = "exit";
        String startCommand = "start hard hard";
        String inputLine = startCommand + "\n" + exitCommand;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        game.start();
        String[] resultOutput = output.toString().split("\n");
        String actualResult = resultOutput[resultOutput.length - 2];
        String expectedResult = "Draw";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Testing of MEDIUM and MEDIUM mode")
    public void shouldFinishWithWinOfResultMEDIUM() {
        String exitCommand = "exit";
        String startCommand = "start medium medium";
        String inputLine = startCommand + "\n" + exitCommand;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        game.start();
        String[] resultOutput = output.toString().split("\n");
        String actualResult = resultOutput[resultOutput.length - 2];
        String expectedResult = "Draw";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Testing of EASY and EASY mode")
    public void shouldFinishWithWinOfResultEASY() {
        String exitCommand = "exit";
        String startCommand = "start easy easy";
        String inputLine = startCommand + "\n" + exitCommand;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        game.start();
        String[] resultOutput = output.toString().split("\n");
        String actualResult = resultOutput[resultOutput.length - 2];
        String expectedResult = "O wins";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Testing of USER and USER mode")
    public void shouldFinishWithDrawUsers() {
        String exitCommand = "exit";
        String startCommand = "start user user";
        String step1 = "1 1";
        String step2 = "2 2";
        String step3 = "1 2";
        String step4 = "1 3";
        String step5 = "3 1";
        String step6 = "2 1";
        String step7 = "2 3";
        String step8 = "3 2";
        String step9 = "3 3";

        String stringBuilder = startCommand + "\n" +
                step1 + "\n" +
                step2 + "\n" +
                step3 + "\n" +
                step4 + "\n" +
                step5 + "\n" +
                step6 + "\n" +
                step7 + "\n" +
                step8 + "\n" +
                step9 + "\n" +
                exitCommand;

        ByteArrayInputStream in = new ByteArrayInputStream(stringBuilder.getBytes());
        System.setIn(in);
        game.start();
        String[] resultOutput = output.toString().split("\n");
        String actualResult = resultOutput[resultOutput.length - 2];
        String expectedResult = "Draw";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @AfterEach
    void cleanUpStreams() {
        System.setOut(null);
        System.setIn(inputStream);
    }
}
