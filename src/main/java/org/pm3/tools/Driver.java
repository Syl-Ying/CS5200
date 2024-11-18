package org.pm3.tools;

import java.sql.SQLException;
import java.util.List;

import org.pm3.dal.*;
import org.pm3.model.*;

public class Driver {
    public static void main(String[] args) {
        // Initialize the PlayerDao and CharactersDao to perform database operations
        PlayerDao playerDao = PlayerDao.getInstance();
        CharactersDao charactersDao = CharactersDao.getInstance();
        JobDao jobDao = JobDao.getInstance();
        CharacterJobDao characterJobDao = CharacterJobDao.getInstance();

        try {
            // 1. Create a new Player
            Player newPlayer = new Player("john_doe", "john.doe@example.com");
            Player createdPlayer = playerDao.create(newPlayer);
            System.out.println("Created Player: " + createdPlayer.getPlayerID());

            // 2. Create a new Character for the created Player
            Characters newCharacter = new Characters(createdPlayer, "John", "Doe", 100);
            Characters createdCharacter = charactersDao.create(newCharacter);
            System.out.println("Created Character: " + createdCharacter.getCharacterID());

            // 3. Retrieve the Character by ID
            Characters retrievedCharacter = charactersDao.getCharacterByID(createdCharacter.getCharacterID());
            System.out.println("Retrieved Character: " + retrievedCharacter.getCharacterID()+" FName: "+retrievedCharacter.getCharacterFirstName());

            // 4. Update the Character
            createdCharacter.setCharacterFirstName("Jane"); 
            createdCharacter.setCharacterLastName("Smith");
            createdCharacter.setCurrentMaxHP(120);
            Characters updatedCharacter = charactersDao.update(createdCharacter);
            System.out.println("Updated Character: " + updatedCharacter.getCharacterID()+" FName: "+updatedCharacter.getCharacterFirstName());


            
            // 5. Create a new Job
            Job newJob = new Job("Software EngineerII");
            Job createdJob = jobDao.create(newJob);
            System.out.println("Created Job: " + createdJob.getJobID() + ", " + createdJob.getJobName());
            
            // 6. Retrieve a Job by ID
            Job retrievedJob = jobDao.getJobById(createdJob.getJobID());
            System.out.println("Retrieved Job: " + retrievedJob.getJobID() + ", " + retrievedJob.getJobName());
            

            // 7. Update the Job name
            Job updatedJob = jobDao.updateJobName(retrievedJob, "Senior Software Engineer");
            System.out.println("Updated Job: " + updatedJob.getJobID() + ", " + updatedJob.getJobName());

            // 8: Assign the Job to the Character
            CharacterJob newCharacterJob = new CharacterJob(createdCharacter, createdJob, 10,50,100);
            CharacterJob createdCharacterJob = characterJobDao.create(newCharacterJob);
            System.out.println("Assigned Job to Character: CharacterJobID = " + 
                               createdCharacterJob.getJob().getJobName()+ ": "+  createdCharacterJob.getCharacter().getCharacterFirstName());
            
            // 9: Retrieve the CharacterJob by ID
            CharacterJob retrievedCharacterJob = characterJobDao.getCharacterJob(createdCharacterJob.getCharacter().getCharacterID(),createdCharacterJob.getJob().getJobID());
            if (retrievedCharacterJob != null) {
                System.out.println("CharacterID = " + retrievedCharacterJob.getCharacter().getCharacterID() +
                                   ", JobID = " + retrievedCharacterJob.getJob().getJobID());
            } else {
                System.out.println("CharacterJob not found.");
            }
            
            // Step 10: Retrieve all CharacterJobs for the created Character
            List<CharacterJob> allCharacterJobs = characterJobDao.getCharacterJobsByCharacterID(createdCharacter.getCharacterID());
            System.out.println("All Jobs for Character ID = " + createdCharacter.getCharacterID() + ":");
            for (CharacterJob characterJob : allCharacterJobs) {
                System.out.println(" JobName = " + characterJob.getJob().getJobName() +
                                   ", Level = " + characterJob.getCharacterJobLevel());
            }
         //  11: Delete the CharacterJob
            characterJobDao.delete(createdCharacterJob.getCharacter().getCharacterID(),createdCharacterJob.getJob().getJobID());
            System.out.println("Deleted Character Job Pair = "+ createdCharacterJob.getCharacter().getCharacterFirstName() + createdCharacterJob.getJob().getJobName());

            // 12. Delete the Player after character operations are completed
            playerDao.delete(createdPlayer);
            System.out.println("Deleted Player with ID: " + createdPlayer.getPlayerID());
            
            
            // 13. Delete the Job (optional)
             jobDao.delete(updatedJob);
             System.out.println("Deleted Job with ID: " + updatedJob.getJobID());



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

