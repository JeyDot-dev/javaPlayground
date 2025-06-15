package leaveTrackingSystem;

public class VacationLeaveRequest extends LeaveRequest {
    public VacationLeaveRequest(int requestId, Employee employee,
                            String startDate, String endDate) {
        super(requestId, employee, startDate, endDate, "Vacation");
    }

}
