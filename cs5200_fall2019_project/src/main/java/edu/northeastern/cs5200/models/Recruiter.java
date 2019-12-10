//JJ
package edu.northeastern.cs5200.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity

public class Recruiter extends User{
	private Boolean verified;
	private String jobTitle;
	private String company;
	
	public Recruiter() {}
	

	//For jobs OneToMany
	@OneToMany(mappedBy = "thisRecruiterJobs", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Job> jobsCreatedByRecruiter;

	public void jobsCreatedByRecruiter(Job job) {
		this.jobsCreatedByRecruiter.add(job);
		if (job.getThisRecruiterJobs() != this)
			job.setThisRecruiterJobs(this);
	}
	public void removeJob(Job job) {
		this.jobsCreatedByRecruiter.remove(job);
		job.setThisRecruiterJobs(null);
	}
	

	public List<Job> getJobsCreatedByRecruiter(){
		return jobsCreatedByRecruiter;
	}
	
	public void setJobsCreatedByRecruiter(List<Job> jobsCreatedByRecruiter) {
		this.jobsCreatedByRecruiter = jobsCreatedByRecruiter;
	}


	//For notification OneToMany	
	@OneToMany (mappedBy = "thisRecruiterNotifications")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Notification> notificationsForRecruiter;

	public void notificationsForRecruiter(Notification nots) {
		this.notificationsForRecruiter.add(nots);
		if (nots.getThisRecruiterNotifications() != this)
			nots.setThisRecruiterNotifications(this);
	}

	public List<Notification> getNotificationsForRecruiter(){
		return notificationsForRecruiter;
	}
	
	public void setNotificationsForRecruiter(List<Notification> notificationsForRecruiter) {
		this.notificationsForRecruiter = notificationsForRecruiter;
	}


	//

	public Recruiter(String firstName, String lastName, String password, String email, String userDtype, Boolean verified,
			String jobTitle, String company) {
		super(firstName, lastName, password, email, userDtype);
		this.verified = verified;
		this.jobTitle = jobTitle;
		this.company = company;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}


}
