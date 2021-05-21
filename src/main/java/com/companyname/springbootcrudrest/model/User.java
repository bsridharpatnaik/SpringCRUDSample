package com.companyname.springbootcrudrest.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "email_address", nullable = false)
	private String emailId;

	@Column(name = "created_at", nullable = false)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "created_by", nullable = false)
	@CreatedBy
	private String createdBy;

	@Column(name = "updated_at", nullable = false)
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Column(name = "updated_by", nullable = false)
	@LastModifiedBy
	private String updatedby;

	@Column(name = "leave_balance", nullable = false)
	Long leaveBalance;

	@OneToMany(mappedBy="user")
	@JsonBackReference
	Set<LeaveDetails> userLeaves;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public Long getLeaveBalance() {
		return leaveBalance;
	}

	public void setLeaveBalance(Long leaveBalance) {
		this.leaveBalance = leaveBalance;
	}

	public Set<LeaveDetails> getUserLeaves() {
		return userLeaves;
	}

	public void setUserLeaves(Set<LeaveDetails> userLeaves) {
		this.userLeaves = userLeaves;
	}
}
