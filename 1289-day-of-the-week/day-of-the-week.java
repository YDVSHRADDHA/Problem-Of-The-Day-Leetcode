import java.time.LocalDate;
import java.time.DayOfWeek;

class Solution {
    public String dayOfTheWeek(int day, int month, int year) {
        LocalDate date = LocalDate.of(year, month, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.toString().charAt(0) + dayOfWeek.toString().substring(1).toLowerCase();
    }
}
