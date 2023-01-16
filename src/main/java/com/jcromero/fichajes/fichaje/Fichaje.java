package com.jcromero.fichajes.fichaje;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Fichaje {
    @Id @GeneratedValue
    private Long id;
    private Long businessId;
    private Instant date;
    private String employeeId;
    private RecordType recordType;
    private String serviceId;
    private FichajeType type;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Fichaje other = (Fichaje) obj;
        return Objects.equals(id, other.id);
    }
    
    @Override
    public String toString() {
        return "Fichaje [id=" + id + ", businessId=" + businessId + ", date=" + date + ", employeeId=" + employeeId
                + ", recordType=" + recordType + ", serviceId=" + serviceId + ", type=" + type + "]";
    }
}
