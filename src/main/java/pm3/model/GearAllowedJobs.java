package pm3.model;

public class GearAllowedJobs {
    protected Gear gear;
    protected Job job;
    protected boolean isAvaibility;

    public GearAllowedJobs(Gear gear, Job job, boolean isAvaibility) {
        this.gear = gear;
        this.job = job;
        this.isAvaibility = isAvaibility;
    }

    public Gear getGear() {
        return gear;
    }

    public void setGear(Gear gear) {
        this.gear = gear;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public boolean isAvaibility() {
        return isAvaibility;
    }

    public void setAvaibility(boolean avaibility) {
        isAvaibility = avaibility;
    }
}
