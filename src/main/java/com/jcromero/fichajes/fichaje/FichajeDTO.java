package com.jcromero.fichajes.fichaje;

import java.time.Instant;

public class FichajeDTO {
    private Long businessId;
    private Instant date;
    private String employeeId;
    private RecordType recordType;
    private String serviceId;
    private FichajeType type;
    
    public Long getBusinessId() {
        return businessId;
    }
    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }
    public Instant getDate() {
        return date;
    }
    public void setDate(Instant date) {
        this.date = date;
    }
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public RecordType getRecordType() {
        return recordType;
    }
    public void setRecordType(RecordType recordType) {
        this.recordType = recordType;
    }
    public String getServiceId() {
        return serviceId;
    }
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
    public FichajeType getType() {
        return type;
    }
    public void setType(FichajeType type) {
        this.type = type;
    }    
}
