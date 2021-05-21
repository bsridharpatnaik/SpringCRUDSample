package com.companyname.springbootcrudrest.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "leaves")
@EntityListeners(AuditingEntityListener.class)
public class LeaveDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long leaveId;
    private long days;

    @ManyToOne
    @JoinColumn(name="userId", nullable=false)
    User user;

    public long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(long leaveId) {
        this.leaveId = leaveId;
    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        this.days = days;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
