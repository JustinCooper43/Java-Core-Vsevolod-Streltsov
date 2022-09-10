import com.streltsov.hard.task.model.DocumentEntity;
import com.streltsov.hard.task.service.Menu;
import com.streltsov.hard.task.util.Utility;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTest {

    private ByteArrayOutputStream output;
    private InputStream inputStream = System.in;
    private Menu menu;

    @BeforeEach
    void setUpMenu() {
        String[] args = {"--data", "contacts.txt"};
        DocumentEntity documentEntity = Utility.parseDocument(args);
        output = new ByteArrayOutputStream();
        menu = new Menu(documentEntity);
        System.setOut(new PrintStream(output));
    }

    @Test
    @DisplayName("If app runs without args should finish work and show \"Empty line of arguments!\" message")
    public void shouldFinishWorkIfAppRunsWithoutArgs() {
        String[] args = new String[0];
        DocumentEntity documentEntity = Utility.parseDocument(args);
        String warningMessage = "Empty line of arguments!\nFile is not found\n";
        Assertions.assertEquals(warningMessage, output.toString());
    }


    @Test
    @DisplayName("If input is 0 should finish work and show \"Bye!\" message")
    public void shouldFinishWorkIfInputEqualZero() {
        ByteArrayInputStream in = new ByteArrayInputStream("0".getBytes());
        System.setIn(in);
        menu.mainMenu();
        Assertions.assertEquals(UtilScript.TEST1, output.toString());
    }

    @Test
    @DisplayName("If input is invalid value should show \"Incorrect option! Try again.\" message")
    public void shouldShowWarningMessageIfInputIncorrect() {
        ByteArrayInputStream in = new ByteArrayInputStream("4\n0".getBytes());
        System.setIn(in);
        menu.mainMenu();
        Assertions.assertEquals(UtilScript.TEST2, output.toString());
    }

    @Test
    @DisplayName("Testing of ALL search strategy with not matched request")
    public void shouldShowMessageAbsentResultsIfRequestIncorrect() {
        ByteArrayInputStream in = new ByteArrayInputStream("1\nALL\nnot_match_contact\n0".getBytes());
        System.setIn(in);
        menu.mainMenu();
        Assertions.assertEquals(UtilScript.TEST3, output.toString());
    }

    @Test
    @DisplayName("Testing of ALL search strategy with matched request")
    public void shouldShowCorrectResultsIfRequestCorrect() {
        ByteArrayInputStream in = new ByteArrayInputStream("1\nALL\ngallien@evilcorp.com\n0".getBytes());
        System.setIn(in);
        menu.mainMenu();
        Assertions.assertEquals(UtilScript.TEST4, output.toString());
    }

    @Test
    @DisplayName("If input is 2 should show all records")
    public void shouldShowAllRecordsIfInputValueIsTwo() {
        ByteArrayInputStream in = new ByteArrayInputStream("2\n0".getBytes());
        System.setIn(in);
        menu.mainMenu();
        Assertions.assertEquals(UtilScript.TEST5, output.toString());
    }

    @Test
    @DisplayName("Testing of ANY search strategy with matched request")
    public void shouldPrintResultContainingOneWordFromQuery() {
        ByteArrayInputStream in = new ByteArrayInputStream("1\nANY\nDwain fsociefy@gmail.com\n0".getBytes());
        System.setIn(in);
        menu.mainMenu();
        Assertions.assertEquals(UtilScript.TEST6, output.toString());
    }

    @Test
    @DisplayName("Testing of NONE search strategy with matched name or email")
    public void shouldShowAllRecordsWhereAbsentsData() {
        ByteArrayInputStream in = new ByteArrayInputStream("1\nNONE\ngallien@evilcorp.com\n0".getBytes());
        System.setIn(in);
        menu.mainMenu();
        Assertions.assertEquals(UtilScript.TEST7, output.toString());
    }

    @AfterEach
    void cleanUpStreams() {
        System.setOut(null);
        System.setIn(inputStream);
    }
}
