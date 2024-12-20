package org.pm3.tools;

import java.sql.SQLException;
import java.util.List;
import org.pm3.dal.*;
import org.pm3.model.*;

public class Driver {

    public static void main(String[] args) throws SQLException {
        // Initialize DAOs to perform database operations
        PlayerDao playerDao = PlayerDao.getInstance();
        CharactersDao charactersDao = CharactersDao.getInstance();
        JobDao jobDao = JobDao.getInstance();
        CharacterJobDao characterJobDao = CharacterJobDao.getInstance();
        InventoryDao inventoryDao = InventoryDao.getInstance();
        WeaponAllowedJobsDao weaponAllowedJobsDao = WeaponAllowedJobsDao.getInstance();
        GearAllowedJobsDao gearAllowedJobsDao = GearAllowedJobsDao.getInstance();
        SlotTypeDao slotTypeDao = SlotTypeDao.getInstance();
        CurrencyDao currencyDao = CurrencyDao.getInstance();
        CharacterCurrencyDao characterCurrencyDao = CharacterCurrencyDao.getInstance();
        CharacterAttributeDao characterAttributeDao = CharacterAttributeDao.getInstance();
        CharacterAttributeValuesDao characterAttributeValuesDao = CharacterAttributeValuesDao.getInstance();
        GearWeaponAttributeBonusDao gearWeaponAttributeBonusDao = GearWeaponAttributeBonusDao.getInstance();
        ConsumableAttributeBonusDao consumableAttributeBonusDao = ConsumableAttributeBonusDao.getInstance();
        ItemDao itemDao = ItemDao.getInstance();

        try {
            // 1. Create a new Player
            Player newPlayer = new Player("john_doe", "john.doe@example.com");
            Player createdPlayer = playerDao.create(newPlayer);
            Player player2 = new Player("jane_doe", "janedoe@gmail.com");
            player2 = playerDao.create(player2);
            System.out.println("Created Player: " + createdPlayer.getPlayerID());

            // 2. Create a new Character for the created Player
            Characters newCharacter = new Characters(createdPlayer, "John", "Doe", 100);
            Characters createdCharacter = charactersDao.create(newCharacter);
            Characters character2 = new Characters(player2, "Jane", "Doe", 200);
            character2 = charactersDao.create(character2);
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
            Job job2 = new Job("Software EngineerI");
            job2 = jobDao.create(job2);

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


            // 1. Create a new SlotType
            SlotType newSlotType = new SlotType(0, "Helmet");
            SlotType slotType1 = slotTypeDao.create(newSlotType);
            System.out.println("Created SlotType: ID = " + slotType1.getSlotID() + ", Name = " + slotType1.getName());

            // 2. Retrieve the SlotType by ID
            SlotType retrievedSlotType = slotTypeDao.getByID(slotType1.getSlotID());
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

            // CharacterAttribute
            // Create CharacterAttribute
            CharacterAttribute newAttribute = new CharacterAttribute("Strength");
            CharacterAttribute createdAttribute = characterAttributeDao.create(newAttribute);
            System.out.println("Created CharacterAttribute: " + createdAttribute.getAttributeName());
            // Retrieve CharacterAttribute by AttributeID
            CharacterAttribute retrievedAttribute = characterAttributeDao.getByAttributeId(createdAttribute.getAttributeID());
            System.out.println("Retrieved CharacterAttribute: " + retrievedAttribute.getAttributeName());

            // CharacterAttributeValues
            // Create CharacterAttribute
            CharacterAttributeValues newValue1 = new CharacterAttributeValues(1, createdAttribute.getAttributeID(), 100);
            newValue1 = characterAttributeValuesDao.create(newValue1);
            System.out.println("Created CharacterAttributeValues01: " + newValue1.getAttributeValue());
            CharacterAttributeValues newValue2 = new CharacterAttributeValues(2, createdAttribute.getAttributeID(), 200);
            newValue2 = characterAttributeValuesDao.create(newValue2);
            System.out.println("Created CharacterAttributeValues02: " + newValue2.getAttributeValue());
            // Retrieve CharacterAttributeValues by CharacterID and AttributeID
            CharacterAttributeValues retrievedValue = characterAttributeValuesDao.getByCharAttrById(newValue1.getCharacterID(),
                newValue1.getAttributeID());
            System.out.println("Retrieved CharacterAttributeValues01: " + retrievedValue.getAttributeValue());

            //Create Slottype
            SlotType newSlotTypeforItem = new SlotType(1, "Waist");
            SlotType slotType2 = slotTypeDao.create(newSlotTypeforItem);

            //Create Items
            Item item1 = new Item("weapon1",50,newSlotTypeforItem,5000,true,"weapon");
            Item item2 = new Item("weapon2",50,newSlotTypeforItem,5000,false,"weapon");
            Item newItem1 = ItemDao.getInstance().create(item1);
            Item newItem2 = ItemDao.getInstance().create(item2);
            System.out.print("the new item id is: " + newItem1.getItemID()+". the new item name is: " +newItem1.getItemName() + ". new item max size is " + newItem1.getItemMaxStackSize()+". new item slottype is: " + newItem1.getSlotType().getSlotID() + ". vendor price is: " + newItem1.getItemVendorPrice() + " whether or not it is able to sell: " + newItem1.isAbleToSell()+" .item catrgory is: " + newItem1.getItemCategory());
            Item returnItem1 = ItemDao.getInstance().getItembyId(item1.getItemID());
            System.out.println();
            System.out.print("the return item id is: " + returnItem1.getItemID()+". the new return item name is: " +returnItem1.getItemName() + ". new item max size is " + returnItem1.getItemMaxStackSize()+". new returnItem1 slottype is: " + returnItem1.getSlotType().getSlotID() + ". vendor price is: " + returnItem1.getItemVendorPrice() + " whether or not it is able to sell: " + returnItem1.isAbleToSell()+" .item catrgory is: " + returnItem1.getItemCategory() +"\n");
            Weapon weapon1 = new Weapon("weapon3",30,slotType2,50,true,"weapon",5,10,2,3,4);
            Weapon weaponReturn = WeaponDao.getInstance().create(weapon1);
            Weapon weapon2 = new Weapon("weapon4",20,slotType2,500,false,"weapon",7,11,4,5,7);
            weapon2 = WeaponDao.getInstance().create(weapon2);
            //        System.out.print("the return weapon id is: " + weaponReturn.getItemID()+". the new return weapon name is: " +weaponReturn.getItemName() + ". new weapon max size is " + weaponReturn.getItemMaxStackSize()+". new weapon slottype is: " + weaponReturn.getSlotType().getSlotID() + ". vendor price is: " + weaponReturn.getItemVendorPrice() + " whether or not it is able to sell: " + weaponReturn.isAbleToSell()+" .item catrgory is: " + weaponReturn.getItemCategory());
            //        System.out.println("The weapon itemLel is" + weaponReturn.getWeaponItemLevel()+"the weapon required level is" + weaponReturn.getWeaponRequiredLevel() + "the weapon damage done is " + weaponReturn.getWeaponDamageDone() +"the weapon auto attack is" + weaponReturn.getWeaponAutoAttack()+"weapon " );
            System.out.println(weaponReturn.toString());
            Weapon weaponbyId = WeaponDao.getInstance().getItembyId(18);
            System.out.println(weaponbyId);

            Gear gear1 = new Gear("gear1",30,slotType2,60,false,"gear",10,2,3,4);
            //create a new gear
            System.out.println(GearDao.getInstance().create(gear1));
            //test get gear by gear id
            System.out.println(GearDao.getInstance().getGearById(19));
            Consumable consumable1 = new Consumable("consumable1",30, slotType2,60,false,"Consumable",2,10);
            //create a new consumables
            System.out.println(ConsumableDao.getInstance().create(consumable1));
            //get consumable by id
            System.out.println(ConsumableDao.getInstance().getConsumableById(20));

            // GearWeaponAttributeBonus
            // Create a new GearWeaponAttributeBonus
            GearWeaponAttributeBonus newBonus1 = new GearWeaponAttributeBonus(1, 2, 50);
            newBonus1 = gearWeaponAttributeBonusDao.create(newBonus1);
            GearWeaponAttributeBonus newBonus2 = new GearWeaponAttributeBonus(2, 1, 60);
            newBonus2 = gearWeaponAttributeBonusDao.create(newBonus2);
            System.out.println("Created GearWeaponAttributeBonus: Bonus Value " + newBonus1.getBonusValue());
            // Retrieve the GearWeaponAttributeBonus by itemID and attributeID
            GearWeaponAttributeBonus retrievedBonus = gearWeaponAttributeBonusDao.getGWAByItemIdAndAttributeID(newBonus1.getItemID(), newBonus1.getAttributeID());
            System.out.println("Retrieved GearWeaponAttributeBonus: Bonus Value " + retrievedBonus.getBonusValue());
            // Update the bonus value of GearWeaponAttributeBonus
            GearWeaponAttributeBonus updatedBonus = gearWeaponAttributeBonusDao.update(retrievedBonus, 999);
            System.out.println("Updated GearWeaponAttributeBonus: Bonus Value " + updatedBonus.getBonusValue());

            // ConsumableAttributeBonus
            // Create a new ConsumableAttributeBonus
            ConsumableAttributeBonus newBonus = new ConsumableAttributeBonus(6, 1, 15.5, 670);
            newBonus = consumableAttributeBonusDao.create(newBonus);
            System.out.println("Created ConsumableAttributeBonus: Bonus Percentage " + newBonus.getBonusPercentage() +
                ", Bonus Cap " + newBonus.getBonusCap() );
            // Retrieve the ConsumableAttributeBonus by bonusID
            ConsumableAttributeBonus retrievedConsumableAttributeBonus = consumableAttributeBonusDao.getConsuAttriByBonusId(newBonus.getBonusID());
            System.out.println("Retrieved ConsumableAttributeBonus: Bonus Percentage " + retrievedConsumableAttributeBonus.getBonusPercentage() +
                ", Bonus Cap " + retrievedConsumableAttributeBonus.getBonusCap());
            // Update the bonus value of ConsumableAttributeBonus
            ConsumableAttributeBonus updatedConsumableAttributeBonus = consumableAttributeBonusDao.update(retrievedConsumableAttributeBonus, 19.9, 199);
            System.out.println("Updated ConsumableAttributeBonus: Bonus Percentage " + updatedConsumableAttributeBonus.getBonusPercentage() +
                ", Bonus Cap " + updatedConsumableAttributeBonus.getBonusCap());

            // Create a new Inventory
            Inventory inventory1 = new Inventory(character2, newItem1, 10, 1);
            inventory1 = inventoryDao.create(inventory1);
            Inventory inventory2 = new Inventory(character2, newItem2, 20, 2);
            inventory2 = inventoryDao.create(inventory2);

            // Read the Inventory by ID
            Inventory retrievedInventory = inventoryDao.getInventoryByCharacterAndItem(character2, newItem1);
            System.out.format("Reading Inventory: [itemID=%d, characterID=%d, inventorySlotNumber=%d, currentItemQuantity=%d]\n",
                retrievedInventory.getItem().getItemID(), retrievedInventory.getCharacter().getCharacterID(),
                retrievedInventory.getInventorySlotNumber(), retrievedInventory.getCurrentItemQuantity());

            // Update the Inventory
            Inventory updatedInventory = inventoryDao.updateInventory(inventory1, 2);
            System.out.format("Updated Inventory: [itemID=%d, characterID=%d, inventorySlotNumber=%d, currentItemQuantity=%d]\n",
                updatedInventory.getItem().getItemID(), updatedInventory.getCharacter().getCharacterID(),
                updatedInventory.getInventorySlotNumber(), updatedInventory.getCurrentItemQuantity());

            // Retrieve all Inventories for the created Character
            List<Inventory> allInventories = inventoryDao.getInventoryByCharacter(createdCharacter);
            for (Inventory inventory : allInventories) {
                System.out.format("Looping inventory: [itemID=%d, characterID=%d, inventorySlotNumber=%d, currentItemQuantity=%d]\n",
                    inventory.getItem().getItemID(), inventory.getCharacter().getCharacterID(),
                    inventory.getInventorySlotNumber(), inventory.getCurrentItemQuantity());
            }


            // Create a new WeaponAllowedJobs
            WeaponAllowedJobs weaponAllowedJobs1 = new WeaponAllowedJobs(weaponReturn, createdJob, true);
            weaponAllowedJobs1 = weaponAllowedJobsDao.create(weaponAllowedJobs1);
            WeaponAllowedJobs weaponAllowedJobs2 = new WeaponAllowedJobs(weaponReturn, job2, true);
            weaponAllowedJobs2 = weaponAllowedJobsDao.create(weaponAllowedJobs2);

            // Read the WeaponAllowedJobs by WeaponID and JobID
            WeaponAllowedJobs retrievedWeaponAllowedJobs = weaponAllowedJobsDao.getWeaponAllowedJobsByWeaponIDAndJobID(weaponReturn.getItemID(), createdJob.getJobID());
            System.out.format("Reading WeaponAllowedJobs: [weaponID=%d, jobID=%d, isAvaibility=%b]\n",
                retrievedWeaponAllowedJobs.getWeapon().getItemID(), retrievedWeaponAllowedJobs.getJob().getJobID(),
                retrievedWeaponAllowedJobs.isAvailability());

            // Create a new GearAllowedJobs
            GearAllowedJobs gearAllowedJobs = new GearAllowedJobs(gear1, createdJob, true);
            gearAllowedJobs = gearAllowedJobsDao.create(gearAllowedJobs);

            // Read the GearAllowedJobs by GearID and JobID
            GearAllowedJobs retrievedGearAllowedJobs = gearAllowedJobsDao.getGearAllowedJobsByGearIDAndJobID(gear1.getItemID(), createdJob.getJobID());
            System.out.format("Reading GearAllowedJobs: [gearID=%d, jobID=%d, isAvaibility=%b]\n",
                retrievedGearAllowedJobs.getGear().getItemID(), retrievedGearAllowedJobs.getJob().getJobID(),
                retrievedGearAllowedJobs.isAvaibility());


            // 1. Retrieve the SlotType for "mainHand"
            String mainHandSlotName = "mainHand";
            SlotType mainHandSlot = slotTypeDao.getAll().stream()
                .filter(slot -> mainHandSlotName.equals(slot.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("SlotType 'mainHand' not found"));

            // 2. Create a mainHand Item
            Item mainHandItem = new Item("mainHandItem", 1, mainHandSlot, 1000, true, "Weapon");
            Item createdMainHandItem = itemDao.create(mainHandItem);

            System.out.println("Created Item for mainHand:");
            System.out.println("Item ID: " + createdMainHandItem.getItemID());
            System.out.println("Item Name: " + createdMainHandItem.getItemName());
            System.out.println("Slot Type: " + createdMainHandItem.getSlotType().getName());

            // 3. Retrieve the Character
            Characters existingCharacter = charactersDao.getCharacterByID(createdCharacter.getCharacterID());

            // 4. Equip the mainHand Item
            EquippedItem mainHandEquippedItem = new EquippedItem(existingCharacter, mainHandSlot, createdMainHandItem);
            EquippedItem createdEquippedItem = EquippedItemDao.getInstance().create(mainHandEquippedItem);

            System.out.println("Equipped Item successfully:");
            System.out.println("Character ID: " + createdEquippedItem.getCharacter().getCharacterID());
            System.out.println("Slot Type: " + createdEquippedItem.getSlotType().getName());
            System.out.println("Item ID: " + createdEquippedItem.getItem().getItemID());
            System.out.println("Item Name: " + createdEquippedItem.getItem().getItemName());

            // 5. Verify the mainHand Item is equipped by the Character
            EquippedItem retrievedEquippedItem = EquippedItemDao.getInstance().getEquippedItemByCharacterIDAndSlotID(
                existingCharacter.getCharacterID(),
                mainHandSlot.getSlotID()
            );
            if (retrievedEquippedItem != null) {
                System.out.println("Verified EquippedItem for mainHand:");
                System.out.println("Character ID: " + retrievedEquippedItem.getCharacter().getCharacterID());
                System.out.println("Slot Type: " + retrievedEquippedItem.getSlotType().getName());
                System.out.println("Item Name: " + retrievedEquippedItem.getItem().getItemName());}

            // 6. Ensure that the mainHand slot always has at least one equipped item.
            //    For testing the delete functionality, add another EquippedItem to the "head" slot: Retrieve the SlotType for "head".
            String headSlotName = "head";
            SlotType headSlot = slotTypeDao.getAll().stream()
                .filter(slot -> headSlotName.equals(slot.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("SlotType 'head' not found"));

            // 7. Create a head Item
            Item headItem = new Item("headItem", 1, headSlot, 500, true, "Armor");
            Item createdHeadItem = itemDao.create(headItem);

            System.out.println("Created Item for head:");
            System.out.println("Item ID: " + createdHeadItem.getItemID());
            System.out.println("Item Name: " + createdHeadItem.getItemName());
            System.out.println("Slot Type: " + createdHeadItem.getSlotType().getName());

            // 8. Equip the head Item
            EquippedItem headEquippedItem = new EquippedItem(existingCharacter, headSlot, createdHeadItem);
            EquippedItem createdHeadEquippedItem = EquippedItemDao.getInstance().create(headEquippedItem);

            System.out.println("Equipped Item successfully:");
            System.out.println("Character ID: " + createdHeadEquippedItem.getCharacter().getCharacterID());
            System.out.println("Slot Type: " + createdHeadEquippedItem.getSlotType().getName());
            System.out.println("Item ID: " + createdHeadEquippedItem.getItem().getItemID());
            System.out.println("Item Name: " + createdHeadEquippedItem.getItem().getItemName());

            // 9. Verify the head Item is equipped by the Character
            EquippedItem retrievedHeadEquippedItem = EquippedItemDao.getInstance().getEquippedItemByCharacterIDAndSlotID(
                existingCharacter.getCharacterID(),
                headSlot.getSlotID()
            );
            if (retrievedHeadEquippedItem != null) {
                System.out.println("Verified EquippedItem for head:");
                System.out.println("Character ID: " + retrievedHeadEquippedItem.getCharacter().getCharacterID());
                System.out.println("Slot Type: " + retrievedHeadEquippedItem.getSlotType().getName());
                System.out.println("Item Name: " + retrievedHeadEquippedItem.getItem().getItemName());
            }

            // 1. Retrieve all equipped items for the given character
            List<EquippedItem> equippedItems = EquippedItemDao.getInstance().getByCharacterID(existingCharacter.getCharacterID());

            // 2. Print the equipped items
            System.out.println("Equipped Items for Character ID: " + existingCharacter.getCharacterID());
            for (EquippedItem equippedItem : equippedItems) {
                System.out.println("Slot Type: " + equippedItem.getSlotType().getName() +
                    ", Item Name: " + equippedItem.getItem().getItemName() +
                    ", Item ID: " + equippedItem.getItem().getItemID());
            }

            // 1. Retrieve an existing EquippedItem to update
            if (!equippedItems.isEmpty()) {
                EquippedItem equippedItemToUpdate = equippedItems.get(0); // Choose the first equipped item
                System.out.println("Equipped Item before update:");
                System.out.println("Slot Type: " + equippedItemToUpdate.getSlotType().getName() +
                    ", Item Name: " + equippedItemToUpdate.getItem().getItemName() +
                    ", Item ID: " + equippedItemToUpdate.getItem().getItemID());

                // 2. Create a new Item to update the EquippedItem with
                Item newItem = new Item("Updated Sword", 50, equippedItemToUpdate.getSlotType(), 2000, true, "Weapon");
                Item createdNewItem = itemDao.create(newItem);

                // 3. Update the EquippedItem with the new Item
                equippedItemToUpdate.setItem(createdNewItem); // Set the new Item in the EquippedItem
                EquippedItem updatedEquippedItem = EquippedItemDao.getInstance().updateItem(equippedItemToUpdate);

                // 4. Print the updated EquippedItem
                System.out.println("Equipped Item after update:");
                System.out.println("Slot Type: " + updatedEquippedItem.getSlotType().getName() +
                    ", Item Name: " + updatedEquippedItem.getItem().getItemName() +
                    ", Item ID: " + updatedEquippedItem.getItem().getItemID());
            } else {
                System.out.println("No Equipped Items found for the character to test update.");
            }

            // Delete
            System.out.println("============= Delete ================");
            // Delete the head EquippedItem
            EquippedItemDao.getInstance().delete(retrievedHeadEquippedItem);
            System.out.println("Deleted EquippedItem for head:");
            System.out.println("Character ID: " + retrievedHeadEquippedItem.getCharacter().getCharacterID());
            System.out.println("Slot Type: " + retrievedHeadEquippedItem.getSlotType().getName());
            System.out.println("Item ID: " + retrievedHeadEquippedItem.getItem().getItemID());
            System.out.println("Item Name: " + retrievedHeadEquippedItem.getItem().getItemName());

            // Delete the CharacterJob
            characterJobDao.delete(createdCharacterJob.getCharacter().getCharacterID(),createdCharacterJob.getJob().getJobID());
            System.out.println("Deleted Character Job Pair = "+ createdCharacterJob.getCharacter().getCharacterFirstName() + createdCharacterJob.getJob().getJobName());

            // Delete CharacterAttribute by AttributeID
            characterAttributeDao.delete(createdAttribute);
            System.out.println("Deleted CharacterAttribute with ID: " + createdAttribute.getAttributeID());

            // Delete CharacterAttributeValues by CharacterID and AttributeID
            characterAttributeValuesDao.delete(newValue1);
            System.out.println("Deleted CharacterAttributeValues with CharacterID: " + newValue1.getCharacterID() +
                " and AttributeID: " + newValue1.getAttributeID());

            // Delete the Player after character operations are completed
            playerDao.delete(createdPlayer);
            System.out.println("Deleted Player with ID: " + createdPlayer.getPlayerID());

            // Delete the Job (optional)
            jobDao.delete(updatedJob);
            System.out.println("Deleted Job with ID: " + updatedJob.getJobID());

            // Delete the SlotType
            slotTypeDao.delete(updatedSlotType.getSlotID());
            System.out.println("Deleted SlotType with ID: " + updatedSlotType.getSlotID());

            // Delete Currency
            currencyDao.delete(updatedCurrency.getCurrencyID());
            System.out.println("Deleted Currency with ID: " + updatedCurrency.getCurrencyID());

            // delete the WeaponAllowedJobs
            weaponAllowedJobsDao.delete(weaponAllowedJobs1);
            if (weaponAllowedJobsDao.getWeaponAllowedJobsByWeaponIDAndJobID(weapon2.getItemID(), createdJob.getJobID()) == null) {
                System.out.println("Deleted WeaponAllowedJobs: [weaponID=" + weapon2.getItemID() + ", jobID=" + createdJob.getJobID() + "]");
            } else {
                System.out.println("Failed to delete WeaponAllowedJobs: [weaponID=" + weapon2.getItemID() + ", jobID=" + createdJob.getJobID() + "]");
            }

            // delete the GearAllowedJobs
            gearAllowedJobsDao.delete(gearAllowedJobs);
            if (gearAllowedJobsDao.getGearAllowedJobsByGearIDAndJobID(gear1.getItemID(), createdJob.getJobID()) == null) {
                System.out.println("Deleted GearAllowedJobs: [gearID=" + gear1.getItemID() + ", jobID=" + createdJob.getJobID() + "]");
            } else {
                System.out.println("Failed to delete GearAllowedJobs: [gearID=" + gear1.getItemID() + ", jobID=" + createdJob.getJobID() + "]");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


