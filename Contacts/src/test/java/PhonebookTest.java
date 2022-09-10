import com.streltsov.medium.task.service.BookContacts;
import com.streltsov.medium.task.utils.SerializationUtils;
import com.streltsov.medium.task.view.Menu;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PhonebookTest {

    private ByteArrayOutputStream output;
    private InputStream inputStream = System.in;
    private final String pathToFile = "src/test/resources/phonebook.db";
    private BookContacts bookContactsDB;
    private Menu menu;
    private File file = new File(pathToFile);

    @BeforeEach
    void setUpMenu() {
        SerializationUtils.setPathFile(pathToFile);
        bookContactsDB = Optional.ofNullable(SerializationUtils.readObj()).orElse(new BookContacts());
        menu = new Menu();
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @Test
    @DisplayName("If input 'list' when book empty should show \"No records!\" message")
    public void shouldShowWarningMessageIfInputListWhenBookEmpty() throws IOException {
        String inputLine = UtilScript.LIST_SCRIPT;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        menu.menuActions(bookContactsDB);
        boolean isPhrasePresents = output.toString().contains("No records!");
        Assertions.assertTrue(isPhrasePresents);
    }

    @Test
    @DisplayName("If input 'count' when book empty should show \"The Phone Book has 0 records.\" message")
    public void shouldShowWarningMessageIfInputCountWhenBookEmpty() throws IOException {
        String inputLine = UtilScript.COUNT_SCRIPT;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        menu.menuActions(bookContactsDB);
        boolean isPhrasePresents = output.toString().contains("The Phone Book has 0 records.");
        Assertions.assertTrue(isPhrasePresents);
    }

    @Test
    @DisplayName("Check add person process")
    public void addPerson() throws IOException {
        String inputLine = UtilScript.ADD_PERSON_SCRIPT;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        menu.menuActions(bookContactsDB);
        boolean isPhrasePresents = output.toString().contains("A record added.");
        boolean isRecordExist = output.toString().contains("John Smith");
        Assertions.assertTrue(isPhrasePresents);
        Assertions.assertTrue(isRecordExist);
    }

    @Test
    @DisplayName("Check add organization process")
    public void addOrganization() throws IOException {

        String inputLine = UtilScript.ADD_ORGANIZATION_SCRIPT;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        menu.menuActions(bookContactsDB);
        boolean isPhrasePresents = output.toString().contains("A record added.");
        boolean isRecordExist = output.toString().contains("Pizza Shop");
        Assertions.assertTrue(isPhrasePresents);
        Assertions.assertTrue(isRecordExist);
    }

    @Test
    @DisplayName("Check count process")
    public void checkCountProcess() throws IOException {
        String inputLine = UtilScript.FILL_BOOK_SCRIPT + UtilScript.COUNT_SCRIPT;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        menu.menuActions(bookContactsDB);
        boolean isMessageExist = output.toString().contains("The Phone Book has 4 records.");
        Assertions.assertTrue(isMessageExist);
    }

    @Test
    @DisplayName("Check search process")
    public void checkSearchProcess() throws IOException {
        String inputLine = UtilScript.FILL_BOOK_SCRIPT + UtilScript.SEARCH_SCRIPT;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        menu.menuActions(bookContactsDB);
        boolean isRecordFound1 = output.toString().contains("1. Bob Dylan");
        boolean isRecordFound2 = output.toString().contains("1. Pizza Hut 2");
        boolean isRecordFound3 = output.toString().contains("2. Pizza Shop 1");
        Assertions.assertTrue(isRecordFound1);
        Assertions.assertTrue(isRecordFound2);
        Assertions.assertTrue(isRecordFound3);
    }

    @Test
    @DisplayName("Check edit organization process")
    public void editOrganizationProcess() throws IOException {
        String inputLine = UtilScript.FILL_BOOK_SCRIPT + UtilScript.EDIT_SCRIPT_ORG;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        menu.menuActions(bookContactsDB);
        boolean isRecordFound = output.toString().contains("1. Car Shop");
        Assertions.assertTrue(isRecordFound);
    }

    @Test
    @DisplayName("Check edit person process")
    public void editPersonProcess() throws IOException {
        String inputLine = UtilScript.FILL_BOOK_SCRIPT + UtilScript.EDIT_SCRIPT_PER;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        menu.menuActions(bookContactsDB);
        boolean isRecordFound = output.toString().contains("1. Gwen Stacy");
        Assertions.assertTrue(isRecordFound);
    }

    @Test
    @DisplayName("Check delete person process")
    public void deletePersonProcess() throws IOException {
        String inputLine = UtilScript.FILL_BOOK_SCRIPT + UtilScript.DELETE_SCRIPT;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        menu.menuActions(bookContactsDB);
        boolean isMessageExist = output.toString().contains("Found 0 results:");
        Assertions.assertTrue(isMessageExist);
    }

    @Test
    @DisplayName("Check delete organization process")
    public void deleteOrganizationProcess() throws IOException {
        String inputLine = UtilScript.FILL_BOOK_SCRIPT + UtilScript.DELETE_SCRIPT;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        menu.menuActions(bookContactsDB);
        boolean isMessageExist = output.toString().contains("Found 0 results:");
        Assertions.assertTrue(isMessageExist);
    }

    @Test
    @DisplayName("If input incorrect Date Of Birth should show \"Bad birth date!\" message and record default data")
    public void shouldShowWarningMessageIfInputIncorrectDOB() throws IOException {
        String inputLine = UtilScript.BAD_DOB_SCRIPT;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        menu.menuActions(bookContactsDB);
        boolean isMessageExist = output.toString().contains("Bad birth date!");
        boolean isDOBExist = output.toString().contains("Birth date: [no data]");
        Assertions.assertTrue(isMessageExist);
        Assertions.assertTrue(isDOBExist);
    }

    @Test
    @DisplayName("If input incorrect Gender should show \"Bad gender!\" message and record default data")
    public void shouldShowWarningMessageIfInputIncorrectGender() throws IOException {
        String inputLine = UtilScript.BAD_GENDER_SCRIPT;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        menu.menuActions(bookContactsDB);
        boolean isMessageExist = output.toString().contains("Bad gender!");
        boolean isDOBExist = output.toString().contains("Gender: [no data]");
        Assertions.assertTrue(isMessageExist);
        Assertions.assertTrue(isDOBExist);
    }

    @Test
    @DisplayName("If input incorrect phone number should show \"Wrong number format!\" message and record default data")
    public void shouldShowWarningMessageIfInputIncorrectNumber() throws IOException {
        String inputLine = UtilScript.BAD_PHONE_SCRIPT;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        menu.menuActions(bookContactsDB);
        boolean isMessageExist = output.toString().contains("Wrong number format!");
        boolean isDOBExist = output.toString().contains("Number: [no data]");
        Assertions.assertTrue(isMessageExist);
        Assertions.assertTrue(isDOBExist);
    }

    @Test
    @DisplayName("If input empty name should show \"Empty name!\" message and record default data")
    public void shouldShowWarningMessageIfInputEmptyName() throws IOException {
        String inputLine = UtilScript.EMPTY_NAME_SCRIPT;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        menu.menuActions(bookContactsDB);
        boolean isMessageExist = output.toString().contains("Empty name!");
        boolean isDOBExist = output.toString().contains("Name: [no data]");
        Assertions.assertTrue(isMessageExist);
        Assertions.assertTrue(isDOBExist);
    }

    @Test
    @DisplayName("If input empty surname should show \"Empty surname!\" message and record default data")
    public void shouldShowWarningMessageIfInputEmptySurname() throws IOException {
        String inputLine = UtilScript.EMPTY_SURNAME_SCRIPT;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        menu.menuActions(bookContactsDB);
        boolean isMessageExist = output.toString().contains("Empty surname!");
        boolean isDOBExist = output.toString().contains("Surname: [no data]");
        Assertions.assertTrue(isMessageExist);
        Assertions.assertTrue(isDOBExist);
    }

    @Test
    @DisplayName("If input empty address should show \"Empty address!\" message and record default data")
    public void shouldShowWarningMessageIfInputEmptyAddress() throws IOException {
        String inputLine = UtilScript.EMPTY_ADDRESS_SCRIPT;
        ByteArrayInputStream in = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(in);
        menu.menuActions(bookContactsDB);
        boolean isMessageExist = output.toString().contains("Empty address!");
        boolean isDOBExist = output.toString().contains("Address: [no data]");
        Assertions.assertTrue(isMessageExist);
        Assertions.assertTrue(isDOBExist);
    }

    @AfterEach
    void cleanUpStreams() {
        System.setOut(null);
        System.setIn(inputStream);
        file.delete();
    }
}
