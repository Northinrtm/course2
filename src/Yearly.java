import java.time.LocalDate;

public class Yearly implements Repeatability {
    @Override
    public LocalDate nextTime(LocalDate localDate) {
        return localDate.plusYears(1);
    }
}
