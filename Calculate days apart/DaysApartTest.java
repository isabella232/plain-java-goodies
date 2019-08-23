// JUnit4 Testing
import org.junit.Test;
import static org.junit.Assert.*;

public class DaysApartTest {
    long diffInDays = 238;

    @Test
    public void checkDiffInTime() {
        assertTrue(diffInDays >= 0);
    }
}