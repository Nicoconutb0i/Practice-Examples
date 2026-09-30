package PE;

import java.time.LocalDate;

public class HealthProfile {
    // Attributes
    private String firstName;
    private String lastName;
    private String gender;
    private int birthMonth;
    private int birthDay;
    private int birthYear;
    private double height; // in inches
    private double weight; // in pounds

    // Constructor
    public HealthProfile(String firstName, String lastName, String gender, 
                         int birthMonth, int birthDay, int birthYear, 
                         double height, double weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.birthYear = birthYear;
        this.height = height;
        this.weight = weight;
    }

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public int getBirthMonth() { return birthMonth; }
    public void setBirthMonth(int birthMonth) { this.birthMonth = birthMonth; }

    public int getBirthDay() { return birthDay; }
    public void setBirthDay(int birthDay) { this.birthDay = birthDay; }

    public int getBirthYear() { return birthYear; }
    public void setBirthYear(int birthYear) { this.birthYear = birthYear; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    // Calculate age in years
    public int getAge() {
        LocalDate today = LocalDate.now();
        int age = today.getYear() - birthYear;
        
        // Adjust if the birthday hasn't occurred yet this year
        if (today.getMonthValue() < birthMonth || 
           (today.getMonthValue() == birthMonth && today.getDayOfMonth() < birthDay)) {
            age--;
        }
        return age;
    }

    // Calculate maximum heart rate (220 - age)
    public int getMaxHeartRate() {
        return 220 - getAge();
    }

    // Calculate target heart rate range (50% to 85% of max heart rate)
    public String getTargetHeartRateRange() {
        int maxHR = getMaxHeartRate();
        int minTarget = (int) (maxHR * 0.50);
        int maxTarget = (int) (maxHR * 0.85);
        return minTarget + " - " + maxTarget + " bpm";
    }

    // Calculate Body Mass Index (BMI)
    // Formula: (weight in pounds * 703) / (height in inches * height in inches)
    public double getBMI() {
        if (height <= 0) return 0.0;
        return (weight * 703) / (height * height);
    }
}
