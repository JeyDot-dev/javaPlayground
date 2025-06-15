package leaveTrackingSystem;

public class LeaveRequest {
    private int         requestId;
    private Employee    employee;
    private String      startDate;
    private String      endDate;
    private String      status; // "Pending", "Approved", "Denied"
    private String      reason;


    public LeaveRequest(){
        this.requestId = 0;
        this.employee = new Employee();
        this.startDate = "01-01-1970";
        this.endDate = "02-01-1970";
        this.status = "Denied";
        this.reason = "DefaultReason";
    }

    public LeaveRequest(int requestId, Employee employee,
                        String startDate, String endDate,
                        String reason) {
        this.requestId = requestId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "Pending";
        this.reason = reason;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
