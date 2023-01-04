package junit.junit5;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Date: 2023/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class AssertionsTest {

    Person person = new Person("John", "Doe");


    @Test
    public void standardAssertions() {
        assertEquals(2, 2);
        assertEquals(5, 4, "assert equals fail");
        assertTrue(2 == 2, () -> "assertion messages can be lazily evaluated--");
    }

    @Test
    public void groupedAssertions() {
        assertAll("person", () -> assertEquals("John", person.getFirstName()),
                () -> assertEquals("Doe", person.getLastName()));
    }

    @Test
    public void dependentAssertions() {
        assertAll("properties", () -> {
                    String firstName = person.getFirstName();
                    assertNotNull(firstName);
                    assertAll("first name",
                            () -> assertTrue(firstName.startsWith("J")),
                            () -> assertTrue(firstName.endsWith("n")));
                },
                () -> {
                    String lastName = person.getLastName();
                    assertNotNull(lastName);
                    assertAll("last name",
                            () -> assertTrue(lastName.startsWith("D")),
                            () -> assertTrue(lastName.endsWith("e")));
                });
    }


    @Test
    public void exceptionTesting() {

        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
        assertEquals("a message", exception.getMessage());
    }


    @Test
    void timoutNotExceeded() throws InterruptedException {
        assertTimeout(Duration.ofMinutes(1), () -> {
            Thread.sleep(2 * 1000 * 60);
        });
    }

    @Test
    void timeoutNOtExceededWithResult() {
        final String actualResult = assertTimeout(Duration.ofMinutes(1), () -> {
            return "a result";
        });
        assertEquals("a result", actualResult);
    }


    @Test
    void timeoutNotExceededWithMethod() {
        final String assertTimeout = assertTimeout(Duration.ofSeconds(5), AssertionsTest::greeting);
        assertEquals("hello world!", assertTimeout);
    }

    @Test
    void timeoutExceeded() {
        assertTimeout(Duration.ofSeconds(2), () -> {
            Thread.sleep(10000);
        });
    }


    @Test
    void timeoutExceededWithPremtiveTermination() {
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            Thread.sleep(10000);
        });
    }


    @Test
    @DisplayName("Exception Test Demo")
    public void assertThrowsException() {
        String str = null;
        assertThrows(IllegalArgumentException.class, () -> {
            Integer.valueOf(str);
        });
    }

    @Test
    public void assertThrowsException2() {
        String str = null;
        assertThrows(NullPointerException.class, () -> {
            Integer.valueOf(str);
        });
    }


    private static String greeting() {
        return "hello world!";
    }

}