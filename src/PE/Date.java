package PE;

public class Date {
    private int month;
    private int day;
    private int year;

    // Array tracking days per month (Index 1 = Jan, Index 2 = Feb, etc.)
    private static final int[] DAYS_PER_MONTH = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public Date(int month, int day, int year) {
        // Assume year is correct per prompt instructions, but keep it positive
        this.year = (year >= 0) ? year : 2000; 
        
        // Validate month
        if (month >= 1 && month <= 12) {
            this.month = month;
        } else {
            System.out.println("Invalid month (" + month + "). Setting to default 1.");
            this.month = 1;
        }

        // Validate day
        if (isValidDay(day)) {
            this.day = day;
        } else {
            System.out.println("Invalid day (" + day + ") for current month/year. Setting to default 1.");
            this.day = 1;
        }

        // Output confirmation using implicit toString call via string concatenation
        System.out.println("Date object constructor for date " + this);
    }

    // Helper method to check if a day is valid given current month and year
    private boolean isValidDay(int testDay) {
        if (testDay <= 0) return false;

        // Check for leap year when it's February 29th
        if (this.month == 2 && testDay == 29 && isLeapYear(this.year)) {
            return true;
        }

        return testDay <= DAYS_PER_MONTH[this.month];
    }

    // Helper method to determine if a year is a leap year
    private boolean isLeapYear(int testYear) {
        return (testYear % 400 == 0) || (testYear % 4 == 0 && testYear % 100 != 0);
    }

    // Increments the date by one day
    public void nextDay() {
        if (isValidDay(day + 1)) {
            day++;
        } else {
            // Roll over to the next month
            day = 1;
            if (month < 12) {
                month++;
            } else {
                // Roll over to the next year
                month = 1;
                year++;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", month, day, year);
    }
}
