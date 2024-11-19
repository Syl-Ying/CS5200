package org.pm3.model;

public class WeaponAllowedJobs {

    protected Weapon weapon;
    protected Job job;
    boolean isAvailability;

    public WeaponAllowedJobs(Weapon weapon, Job job, boolean isAvailability) {
        this.weapon = weapon;
        this.job = job;
        this.isAvailability = isAvailability;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public boolean isAvailability() {
        return isAvailability;
    }

    public void setAvailability(boolean availability) {
        isAvailability = availability;
    }
}
