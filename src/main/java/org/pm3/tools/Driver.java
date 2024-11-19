package org.pm3.tools;

import org.pm3.dal.*;
import org.pm3.model.*;
import java.sql.SQLException;
import java.util.List;

public class Driver {
	public static void main(String[] args) { 
        // Initialize DAOs
        PlayerDao playerDao = PlayerDao.getInstance();
        CharactersDao charactersDao = CharactersDao.getInstance();
        JobDao jobDao = JobDao.getInstance();
        CharacterJobDao characterJobDao = CharacterJobDao.getInstance();
        SlotTypeDao slotTypeDao = SlotTypeDao.getInstance();
        CurrencyDao currencyDao = CurrencyDao.getInstance();
        CharacterCurrencyDao characterCurrencyDao = CharacterCurrencyDao.getInstance();
        CharacterAttributeDao characterAttributeDao = CharacterAttributeDao.getInstance();
        CharacterAttributeValuesDao characterAttributeValuesDao = CharacterAttributeValuesDao.getInstance();
        GearWeaponAttributeBonusDao gearWeaponAttributeBonusDao = GearWeaponAttributeBonusDao.getInstance();
        ConsumableAttributeBonusDao consumableAttributeBonusDao = ConsumableAttributeBonusDao.getInstance();

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
            
            // 11: Delete the CharacterJob
            characterJobDao.delete(createdCharacterJob.getCharacter().getCharacterID(),createdCharacterJob.getJob().getJobID());
            System.out.println("Deleted Character Job Pair = "+ createdCharacterJob.getCharacter().getCharacterFirstName() + createdCharacterJob.getJob().getJobName());

            // 12. Delete the Player after character operations are completed
            playerDao.delete(createdPlayer);
            System.out.println("Deleted Player with ID: " + createdPlayer.getPlayerID());
            
            // 13. Delete the Job (optional)
            jobDao.delete(updatedJob);
            System.out.println("Deleted Job with ID: " + updatedJob.getJobID());

            // 1. Create a new SlotType
            SlotType newSlotType = new SlotType(0, "Helmet");
            SlotType createdSlotType = slotTypeDao.create(newSlotType);
            System.out.println("Created SlotType: ID = " + createdSlotType.getSlotID() + ", Name = " + createdSlotType.getName());

            // 2. Retrieve the SlotType by ID
            SlotType retrievedSlotType = slotTypeDao.getByID(createdSlotType.getSlotID());
            if (retrievedSlotType != null) {
                System.out.println("Retrieved SlotType: ID = " + retrievedSlotType.getSlotID() + ", Name = " + retrievedSlotType.getName());
            } else {
                System.out.println("SlotType not found.");
            }

            // 3. Update the SlotType
            retrievedSlotType.setName("Updated Helmet");
            SlotType updatedSlotType = slotTypeDao.update(retrievedSlotType);
            System.out.println("Updated SlotType: ID = " + updatedSlotType.getSlotID() + ", Name = " + updatedSlotType.getName());

            // 4. Retrieve all SlotTypes
            List<SlotType> allSlotTypes = slotTypeDao.getAll();
            System.out.println("All SlotTypes:");
            for (SlotType slotType : allSlotTypes) {
                System.out.println("ID = " + slotType.getSlotID() + ", Name = " + slotType.getName());
            }

            // 5. Delete the SlotType
            slotTypeDao.delete(updatedSlotType.getSlotID());
            System.out.println("Deleted SlotType with ID: " + updatedSlotType.getSlotID());

            // 1. Create Currency
            Currency newCurrency = new Currency(0, "Gold", 1000.0, 500.0);
            Currency createdCurrency = currencyDao.create(newCurrency);
            System.out.println("Created Currency: " + createdCurrency.getCurrencyID());

            // 2. Retrieve Currency by Id
            Currency retrievedCurrency = currencyDao.getByID(createdCurrency.getCurrencyID());
            System.out.println("Retrieved Currency: " + retrievedCurrency.getCurrencyName());

            // 3. Update Currency
            retrievedCurrency.setCurrencyName("Updated Gold");
            Currency updatedCurrency = currencyDao.update(retrievedCurrency);
            System.out.println("Updated Currency: " + updatedCurrency.getCurrencyName());

            // 4. Retrieve all Currency
            List<Currency> allCurrencies = currencyDao.getAll();
            System.out.println("All Currencies:");
            for (Currency currency : allCurrencies) {
                System.out.println(currency.getCurrencyName());
            }

            // 5. Delete Currency
            currencyDao.delete(updatedCurrency.getCurrencyID());
            System.out.println("Deleted Currency with ID: " + updatedCurrency.getCurrencyID());
        
	     	// Test CharacterAttributeDao
	        CharacterAttribute newAttribute = new CharacterAttribute("Strength");
	        CharacterAttribute createdAttribute = characterAttributeDao.create(newAttribute);
	        System.out.println("Created CharacterAttribute: " + createdAttribute.getAttributeName());
	
	        CharacterAttribute retrievedAttribute = characterAttributeDao.getByAttributeId(createdAttribute.getAttributeID());
	        System.out.println("Retrieved CharacterAttribute: " + retrievedAttribute.getAttributeName());
	
	        // Test CharacterAttributeValuesDao
	        CharacterAttributeValues newValue = new CharacterAttributeValues(1, createdAttribute.getAttributeID(), 100);
	        newValue = characterAttributeValuesDao.create(newValue);
	        System.out.println("Created CharacterAttributeValues: " + newValue.getAttributeValue());
	
	        CharacterAttributeValues retrievedValue = characterAttributeValuesDao.getByCharAttrById(newValue.getCharacterID(), newValue.getAttributeID());
	        System.out.println("Retrieved CharacterAttributeValues: " + retrievedValue.getAttributeValue());
		} catch (SQLException e) {
            e.printStackTrace();
        } 
	}
}


