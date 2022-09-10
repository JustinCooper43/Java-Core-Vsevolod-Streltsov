import com.streltsov.medium.task.service.BookContacts;
import com.streltsov.medium.task.utils.SerializationUtils;
import com.streltsov.medium.task.view.Menu;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SerializationTest {

    private ByteArrayOutputStream output;
    private final InputStream inputStream = System.in;
    private final String pathToFile = "src/test/resources/phonebook.db";
    private BookContacts bookContactsDB;
    private Menu menu1;
    private Menu menu2;
    private final File file = new File(pathToFile);


    @Test
    @Order(1)
    @DisplayName("")
    public void shouldSaveAddedDataToBook() throws IOException {
        SerializationUtils.setPathFile(pathToFile);
        bookContactsDB = Optional.ofNullable(SerializationUtils.readObj()).orElse(new BookContacts());
        menu1 = new Menu();
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        String inputLine1 = UtilScript.FILL_BOOK_SCRIPT + UtilScript.COUNT_SCRIPT;
        ByteArrayInputStream in1 = new ByteArrayInputStream(inputLine1.getBytes());
        System.setIn(in1);
        menu1.menuActions(bookContactsDB);
        boolean isMessageExist = output.toString().contains("The Phone Book has 4 records.");
        Assertions.assertTrue(isMessageExist);
        System.setIn(inputStream);
    }

    @Test
    @Order(2)
    @DisplayName("")
    public void shouldShowAddedDataToBookAfterFinish () throws IOException {
        SerializationUtils.setPathFile(pathToFile);
        bookContactsDB = Optional.ofNullable(SerializationUtils.readObj()).orElse(new BookContacts());
        menu2 = new Menu();
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        String inputLine2 = UtilScript.COUNT_SCRIPT;
        ByteArrayInputStream in2 = new ByteArrayInputStream(inputLine2.getBytes());
        System.setIn(in2);
        menu2.menuActions(bookContactsDB);

        boolean isMessageExist = output.toString().contains("The Phone Book has 4 records.");
        Assertions.assertTrue(isMessageExist);
        System.setOut(null);
        System.setIn(inputStream);
        file.delete();
    }
}
