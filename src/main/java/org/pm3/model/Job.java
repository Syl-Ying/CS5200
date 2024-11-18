package org.pm3.model;


public class Job {
    private int jobID;
    private String jobName;

    // Constructor to initialize a Job object with jobID and jobName
    public Job(int jobID, String jobName) {
        this.jobID = jobID;
        this.jobName = jobName;
    }

    // Constructor for creating a Job without the jobID (e.g., before inserting into DB)
    public Job(String jobName) {
        this.jobName = jobName;
    }

    public Job(int jobID) {
    	this.jobID = jobID;
	}

	// Getter for jobID
    public int getJobID() {
        return jobID;
    }

    // Setter for jobID
    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    // Getter for jobName
    public String getJobName() {
        return jobName;
    }

    // Setter for jobName
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }


}
