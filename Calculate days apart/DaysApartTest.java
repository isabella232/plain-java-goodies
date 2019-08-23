// JUnit4 test case
import org.junit.Test;
import static org.junit.Assert.*;

public class DaysApartTest {
    private DaysApart test = new DaysApart();

    @Test
    public void checkDiffInDays() {
        test.setOriginDate(2018, 5, 23);
        test.setFutureDate(2019, 3, 23);
        assertTrue(test.returnNumberOfDays() >= 0);
    }

}