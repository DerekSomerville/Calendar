package Customer;

public class CustomerData {
    String studentId;
    String uniModule;
    String dayOfWeek;
    int timetableDate;
    int timetableMonth;
    int timetableYear;

    public CustomerData(String studentId, String uniModule, String dayOfWeek, int timetableDate, int timetableMonth, int timetableYear) {
        this.studentId = studentId;
        this.uniModule = uniModule;
        this.dayOfWeek = dayOfWeek;
        this.timetableDate = timetableDate;
        this.timetableMonth = timetableMonth;
        this.timetableYear = timetableYear;
    }

    public String getStudentId() {
        return studentId;
    }
    public String getUniModule() {
        return uniModule;
    }
    public String getdayOfWeek() {
        return dayOfWeek;
    }
    public int getTimetableDate() {
        return timetableDate;
    }
    public int getTimetableMonth() {
        return timetableMonth;
    }
    public int getTimetableYear() {
        return timetableYear;
    }

    public void setStudentId (String studentId) {
        this.studentId = studentId;
    }
    public void setFirstName (String uniModule) {
        this.uniModule = uniModule;
    }
    public void setLastName (String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    public void setTimetableDate(int timetableDate) {
        this.timetableDate = timetableDate;
    }
    public void setTimetableMonth(int timetableMonth) {
        this.timetableMonth = timetableMonth;
    }
    public void setTimetableYear(int timetableYear) {
        this.timetableYear = timetableYear;
    }

    @Override
    public String toString() {
        return "Uni Module:'" + uniModule + '\'' +
                " Day of Week:'" + dayOfWeek + '\'' +
                " Date:" + timetableDate +
                " Month:" + timetableMonth +
                " Year:" + timetableYear;
    }
}