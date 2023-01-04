package junit.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

/**
 * Date: 2023/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class RepeatTest {

    @BeforeEach
    void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        final int currentRepetition = repetitionInfo.getCurrentRepetition();
        final int totalRepetitions = repetitionInfo.getTotalRepetitions();

        final String methodName = testInfo.getTestMethod().get().getName();

        System.out.println(
                String.format("About to execute repeatition %d of %d for %s", currentRepetition, totalRepetitions, methodName));
    }

    @RepeatedTest(2)
    void repeatedTestWithRepetitionInfo(RepetitionInfo repetitionInfo) {
        assertEquals(2, repetitionInfo.getTotalRepetitions());
    }


    @RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("Repeat !")
    void customDisplayName(TestInfo testInfo) {
        assertEquals(testInfo.getDisplayName(), "Repeat ! 1/1");
    }

    @RepeatedTest(value = 1, name = RepeatedTest.LONG_DISPLAY_NAME)
    @DisplayName("Details ... ")
    public void customDisplayNameWithLongPattern(TestInfo testInfo) {
        assertEquals(testInfo.getDisplayName(), "Details ... :: repetition 1 of 1");
    }




}