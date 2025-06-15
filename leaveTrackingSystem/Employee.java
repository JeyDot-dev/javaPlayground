package leaveTrackingSystem;

public class Employee {

    private int     employeeId;
    private int     leaveBalance = 20;
    private String  name;
    private String  department;
    private String  email;

    public Employee(int employeeId, String name, String department, String email) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.email = email;
    }
    public Employee(){
        this.employeeId = 0;
        this.leaveBalance = 0;
        this.name = "DefaultName";
        this.department = "DefaultDepartment";
        this.email = "DefaultEmail";
    }
    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(int leaveBalance) {
        if (leaveBalance >= 0) {
            this.leaveBalance = leaveBalance;
        }
        else {
            System.out.println("Leave balance cannot be negative.");
        }
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
