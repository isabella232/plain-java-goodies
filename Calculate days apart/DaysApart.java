import java.util.Calendar;
import static java.lang.System.out;

public class DaysApart {
    private static int aDayInMilliseconds = 1000 * 60 * 60 * 24;
    private long originDateInMillis, futureDateInMillis;


    public void setOriginDate(int yearStart, int monthStart, int dateStart) {
        Calendar originDate = Calendar.getInstance();
        originDate.set(yearStart, monthStart, dateStart);
        originDateInMillis = originDate.getTimeInMillis();
        out.println(String.format("Origin date: %tc", originDate));
    }

    public void setFutureDate(int yearEnd, int monthEnd, int dateEnd) {
        Calendar futureDate = Calendar.getInstance();
        futureDate.set(yearEnd, monthEnd, dateEnd);
        futureDateInMillis = futureDate.getTimeInMillis();
        out.println(String.format("Future date: %tc", futureDate));
    }

    public long returnNumberOfDays() {
        long diffInMillis = futureDateInMillis - originDateInMillis;
        long diffInDays = diffInMillis / aDayInMilliseconds;

            if (futureDateInMillis < originDateInMillis) {
                 out.println("Error: Date input reversed.");
            } else { out.println(" ");}

        return diffInDays;
    }

    public static void main(String[] args) {

        DaysApart count = new DaysApart();

        count.setOriginDate(2019,7,14); // January = 0
        count.setFutureDate(2020, 5, 30);

        out.println("Days apart: " + count.returnNumberOfDays());
    }
}
