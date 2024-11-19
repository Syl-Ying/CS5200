package pm3.model;

public class CharacterJob {
    protected Characters character;  // Instance of the Character class
    protected Job job;  // Instance of the Job class
    private int characterJobLevel;
    private int characterJobExperiencePoints;
    private int characterJobThreshold;

    // Constructor
    public CharacterJob(Characters character, Job job, int characterJobLevel, int characterJobExperiencePoints, int characterJobThreshold) {
        this.character = character;
        this.job = job;
        this.characterJobLevel = characterJobLevel;
        this.characterJobExperiencePoints = characterJobExperiencePoints;
        this.characterJobThreshold = characterJobThreshold;
    }
    
    public CharacterJob(Characters character, Job job, int characterJobThreshold) {
        this(character, job, 0, 0, characterJobThreshold);  // Default level and experience points
    }
    
    // Getter and Setter Methods
    public Characters getCharacter() {
        return character;
    }

    public void setCharacter(Characters character) {
        this.character = character;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public int getCharacterJobLevel() {
        return characterJobLevel;
    }

    public void setCharacterJobLevel(int characterJobLevel) {
        this.characterJobLevel = characterJobLevel;
    }

    public int getCharacterJobExperiencePoints() {
        return characterJobExperiencePoints;
    }

    public void setCharacterJobExperiencePoints(int characterJobExperiencePoints) {
        this.characterJobExperiencePoints = characterJobExperiencePoints;
    }

    public int getCharacterJobThreshold() {
        return characterJobThreshold;
    }

    public void setCharacterJobThreshold(int characterJobThreshold) {
        this.characterJobThreshold = characterJobThreshold;
    }


}

