package com.franz.sud.java.game.cartridge.castlevania;

import com.franz.sud.java.game.cartridge.Cartridge;
import com.franz.sud.java.game.cartridge.castlevania.elements.GameMap;
import com.franz.sud.java.game.cartridge.castlevania.elements.GameProgress;
import com.franz.sud.java.game.cartridge.castlevania.elements.item.AttributedItem;
import com.franz.sud.java.game.cartridge.castlevania.elements.item.ConsumableItem;
import com.franz.sud.java.game.cartridge.castlevania.elements.item.EquipmentType;
import com.franz.sud.java.game.cartridge.castlevania.elements.item.EquippableItem;
import com.franz.sud.java.game.cartridge.castlevania.elements.skill.*;
import com.franz.sud.java.game.cartridge.castlevania.elements.unit.Enemy;
import com.franz.sud.java.game.cartridge.castlevania.elements.unit.Hero;
import com.franz.sud.java.game.cartridge.castlevania.elements.unit.SkilledEnemy;
import com.franz.sud.java.game.cartridge.castlevania.service.BattleService;
import com.franz.sud.java.game.cartridge.castlevania.service.InventoryService;
import com.franz.sud.java.game.cartridge.castlevania.service.MapMovementService;
import com.franz.sud.java.game.cartridge.castlevania.service.RoomService;
import com.franz.sud.java.game.misc.Direction;
import com.franz.sud.java.game.misc.IO;
import com.franz.sud.java.game.platform.components.Point;
import com.franz.sud.java.game.platform.components.Room;

import java.util.ArrayList;
import java.util.HashMap;

public class Castlevania implements Cartridge {
    private final static HashMap<String, String> input = new HashMap<>();

    private boolean gameOver;
    private Enemy finalBoss;
    private Hero hero;
    private RoomService roomService = new RoomService();
    private BattleService battleService = new BattleService();
    private InventoryService inventoryService = new InventoryService();
    private MapMovementService mapService = new MapMovementService();
    private GameProgress progress = new GameProgress();

    public Castlevania() {
        gameOver = false;
    }

//    private void consumableItemDrop() {
//        Random rand = new Random();
//        int c = rand.nextInt(3);
//        if (c > 0) {
//            switch (c) {
//                case 1:
//                    break;
//                case 2:
//                    break;
//                case 3:
//                    break;
//            }
//        }
//    }

    @Override
    public boolean isFinished() {
        return !finalBoss.isAlive();
    }

    @Override
    public String getGameName() {
        return "Castlevania";
    }

    @Override
    public void start() {
        while (!gameOver) {
            checkProgress();
            checkRoomForEnemy();
            checkRoomForItem();
            mainMenu();
        }
    }

    private void checkProgress() {
        Room progressBossRoom = progress.getProgressBossRoom();
        if (roomService.enemyIsAlive(progressBossRoom)) return;

        progress.incrementProgress();

        ArrayList<Room> newOpenRooms = progress.getCurrentProgressRooms();
        mapService.setOpenRooms(newOpenRooms);
    }

    private void mainMenu() {
        input.clear();
        input.put("q", "Quit");
        input.put("i", "Inventory");
        input.put("m", "Map");

        switch (IO.userInput(input)) {
            case "i":
                inventoryService.OpenInventoryMenu();
                break;
            case "m":
                mapService.mapMenu();
                break;
            case "q":
                gameOver = true;
                break;
        }
    }

    private void checkRoomForItem() {
        Room currRoom = hero.getCurrentLocation();
        if (!roomService.roomContainsItem(currRoom)) return;

        AttributedItem item = roomService.getRoomItem(currRoom);

        System.out.println("You found an item, pick it up?");
        switch (IO.pickupItem()) {
            case "y":
                inventoryService.addItemToInventory(item);
                break;
            case "n":
                break;
        }
    }

    private void checkRoomForEnemy() {
        Room currRoom = hero.getCurrentLocation();
        if (!roomService.roomContainsEnemy(currRoom)) return;

        Enemy e = roomService.getRoomEnemy(currRoom);
        int winner = battleService.simulateBattle(e);

        if (winner > 0) {

        }
    }

    @Override
    public void init() {
        // Skill setup
        HealSkill lesserHeal = new HealSkill("Lesser Heal", 30);
        HealSkill heal = new HealSkill("Heal" , 60);
        DamageSkill lightningBolt = new DamageSkill("Lightning Bolt", 15);
        DamageSkill fireBolt = new DamageSkill("Fire Bolt", 30);
        SoulSteal soulSteal = new SoulSteal();
        ChaosStrike chaosStrike = new ChaosStrike();
        StatBoostSkill minorBuff = new StatBoostSkill.Builder("Minor Buff")
                .criticalChance(5)
                .evasion(5)
                .lifesteal(5)
                .duration(3)
                .build();
        StatBoostSkill greaterBuff = new StatBoostSkill.Builder("Improved Buff")
                .criticalChance(10)
                .evasion(10)
                .lifesteal(10)
                .duration(5)
                .build();

        // Hero of the game
        hero = new Hero.Builder("Alucard")
                .health(115)
                .damage(100)
                .lifesteal(20)
                .criticalChance(5)
                .evasion(0)
                .skill(soulSteal)
                .build();

        // All other units
        finalBoss = new SkilledEnemy.Builder("Vlad the Impaler")
                .damage(65)
                .health(700)
                .evasion(5)
                .criticalChance(25)
                .lifesteal(25)
                .skill(chaosStrike)
                .build();

        SkilledEnemy warlock = new SkilledEnemy.Builder("Warlock")
                .damage(25)
                .health(200)
                .evasion(10)
                .criticalChance(10)
                .lifesteal(25)
                .skill(heal)
                .build();
        SkilledEnemy werewolf = new SkilledEnemy.Builder("Werewolf")
                .damage(40)
                .health(250)
                .evasion(10)
                .criticalChance(10)
                .lifesteal(25)
                .skill(fireBolt)
                .build();
        SkilledEnemy minotaur = new SkilledEnemy.Builder("Minotaur")
                .damage(1)
                .health(350)
                .evasion(5)
                .criticalChance(25)
                .lifesteal(5)
                .skill(greaterBuff)
                .build();
        SkilledEnemy medusa = new SkilledEnemy.Builder("Medusa")
                .damage(15)
                .health(110)
                .evasion(5)
                .criticalChance(10)
                .skill(lesserHeal)
                .build();
        SkilledEnemy casper = new SkilledEnemy.Builder("Casper")
                .damage(20)
                .health(140)
                .evasion(10)
                .criticalChance(5)
                .skill(minorBuff)
                .build();
        SkilledEnemy lilith = new SkilledEnemy.Builder("Lilith")
                .damage(30)
                .health(170)
                .criticalChance(10)
                .lifesteal(10)
                .skill(lightningBolt)
                .build();
        SkilledEnemy general = new SkilledEnemy.Builder("General Milling")
                .damage(35)
                .health(250)
                .evasion(10)
                .criticalChance(25)
                .lifesteal(10)
                .skill(greaterBuff)
                .build();
        SkilledEnemy priestess = new SkilledEnemy.Builder("Priestess")
                .damage(40)
                .health(400)
                .evasion(25)
                .criticalChance(10)
                .lifesteal(10)
                .skill(chaosStrike)
                .build();
        SkilledEnemy death = new SkilledEnemy.Builder("Grim Reaper")
                .damage(50)
                .health(440)
                .evasion(10)
                .criticalChance(25)
                .lifesteal(10)
                .skill(chaosStrike)
                .build();
        Enemy banshee = new Enemy.Builder("Banshee")
                .damage(8)
                .health(80)
                .evasion(5)
                .build();
        Enemy imp = new Enemy.Builder("Imp")
                .damage(12)
                .health(100)
                .criticalChance(5)
                .build();
        Enemy vampire = new Enemy.Builder("Vampire")
                .damage(15)
                .health(120)
                .lifesteal(10)
                .build();

        // Equippable Item setup
        EquippableItem commonSword = new EquippableItem.Builder("Common Sword")
                .evasion(5)
                .damage(10)
                .equipmentType(EquipmentType.WEAPON)
                .build();
        EquippableItem rareSword = new EquippableItem.Builder("Rare Sword")
                .damage(50)
                .health(50)
                .criticalChance(15)
                .evasion(5)
                .lifesteal(5)
                .equipmentType(EquipmentType.WEAPON)
                .build();
        EquippableItem rapier = new EquippableItem.Builder("Rapier")
                .damage(150)
                .criticalChance(25)
                .lifesteal(25)
                .evasion(10)
                .equipmentType(EquipmentType.WEAPON)
                .build();
        EquippableItem chainMail = new EquippableItem.Builder("Chain Mail")
                .health(30)
                .equipmentType(EquipmentType.ARMOR)
                .build();
        EquippableItem breastPlate = new EquippableItem.Builder("Breast Plate")
                .health(80)
                .damage(15)
                .evasion(5)
                .lifesteal(5)
                .equipmentType(EquipmentType.ARMOR)
                .build();
        EquippableItem kevlar = new EquippableItem.Builder("Kevlar")
                .health(250)
                .evasion(15)
                .criticalChance(10)
                .lifesteal(5)
                .damage(50)
                .equipmentType(EquipmentType.ARMOR)
                .build();
        EquippableItem talismanEvasion = new EquippableItem.Builder("Talisman of Evasion")
                .evasion(40)
                .equipmentType(EquipmentType.AMULET)
                .build();
        EquippableItem redMoon = new EquippableItem.Builder("Red Moon")
                .health(40)
                .damage(5)
                .evasion(5)
                .criticalChance(5)
                .lifesteal(10)
                .equipmentType(EquipmentType.AMULET)
                .build();
        EquippableItem talisman = new EquippableItem.Builder("Vampire's Talisman")
                .health(10)
                .lifesteal(25)
                .equipmentType(EquipmentType.AMULET)
                .build();

        // Consumable item setup
        ConsumableItem lowTierEvasionBoost = new ConsumableItem.Builder("Lesser Evasion Potion")
                .tier(1)
                .evasion(2)
                .build();
        ConsumableItem lowTierLifestealBoost = new ConsumableItem.Builder("Lesser Lifesteal Potion")
                .tier(1)
                .lifesteal(2)
                .build();
        ConsumableItem lowTiereCriticalBoost = new ConsumableItem.Builder("Lesser Critical Chance Potion")
                .tier(1)
                .criticalChance(2)
                .build();
        ConsumableItem lowTierDamageBoost = new ConsumableItem.Builder("Lesser Damage Boost")
                .tier(1)
                .damage(5)
                .build();
        ConsumableItem lowTierHealthBoost = new ConsumableItem.Builder("Lesser Health Boost")
                .tier(1)
                .health(20)
                .build();
        ConsumableItem midTierEvasionBoost = new ConsumableItem.Builder("Evasion Potion")
                .tier(2)
                .evasion(5)
                .build();
        ConsumableItem midTierLifestealBoost = new ConsumableItem.Builder("Lifesteal Potion")
                .tier(2)
                .lifesteal(5)
                .build();
        ConsumableItem midTiereCriticalBoost = new ConsumableItem.Builder("Critical Chance Potion")
                .tier(2)
                .criticalChance(5)
                .build();
        ConsumableItem midTierDamageBoost = new ConsumableItem.Builder("Damage Boost")
                .tier(2)
                .damage(12)
                .build();
        ConsumableItem midTierHealthBoost = new ConsumableItem.Builder("Health Boost")
                .tier(2)
                .health(50)
                .build();
        ConsumableItem highTierEvasionBoost = new ConsumableItem.Builder("Higher Evasion Potion")
                .tier(3)
                .evasion(10)
                .build();
        ConsumableItem highTierLifestealBoost = new ConsumableItem.Builder("Higher Lifesteal Potion")
                .tier(3)
                .lifesteal(10)
                .build();
        ConsumableItem highTiereCriticalBoost = new ConsumableItem.Builder("Higher Critical Chance Potion")
                .tier(3)
                .criticalChance(10)
                .build();
        ConsumableItem highTierDamageBoost = new ConsumableItem.Builder("Higher Damage Boost")
                .tier(3)
                .damage(30)
                .build();
        ConsumableItem highTierHealthBoost = new ConsumableItem.Builder("Higher Health Boost")
                .tier(3)
                .health(99)
                .build();

        // first map rooms setup
        Room hallwayOne = new Room("Hallway One", new Point(0, -1));
        Room hallwayTwo = new Room("Hallway Two", new Point(0, 0));
        Room hallwayThree = new Room("Hallway Three", new Point(0, 1));
        Room livingRoom = new Room("Living Room", new Point(-1, 0));
        Room servantQuarters = new Room("Servant's Quarters", new Point(-2, 0));
        Room diningHall = new Room("Dining Hall", new Point(1, 0));
        Room kitchen = new Room("Kitchen", new Point(2, 0));
        Room masterBedroom = new Room("Master's Bedroom", new Point(0, 2));

        // second map rooms setup
        Room hallwayOne_2 = hallwayOne.clone();
        Room hallwayTwo_2 = hallwayTwo.clone();
        Room hallwayThree_2 = hallwayThree.clone();
        Room livingRoom_2 = livingRoom.clone();
        Room servantQuarters_2 = servantQuarters.clone();
        Room diningHall_2 = diningHall.clone();
        Room kitchen_2 = kitchen.clone();
        Room masterBedroom_2 = masterBedroom.clone();

        // Setting the adjacent rooms for each rooms
        hallwayOne.setAdjacentRoom(Direction.NORTH, hallwayTwo);
        hallwayOne_2.setAdjacentRoom(Direction.NORTH, hallwayTwo_2);

        hallwayTwo.setAdjacentRoom(Direction.WEST, livingRoom);
        hallwayTwo.setAdjacentRoom(Direction.EAST, diningHall);
        hallwayTwo.setAdjacentRoom(Direction.NORTH, hallwayThree);

        hallwayTwo_2.setAdjacentRoom(Direction.WEST, livingRoom_2);
        hallwayTwo_2.setAdjacentRoom(Direction.EAST, diningHall_2);
        hallwayTwo_2.setAdjacentRoom(Direction.NORTH, hallwayThree_2);

        hallwayThree_2.setAdjacentRoom(Direction.NORTH, masterBedroom_2);
        hallwayThree.setAdjacentRoom(Direction.NORTH, masterBedroom);

        livingRoom.setAdjacentRoom(Direction.WEST, servantQuarters);
        livingRoom_2.setAdjacentRoom(Direction.WEST, servantQuarters_2);

        diningHall.setAdjacentRoom(Direction.EAST, kitchen);
        diningHall_2.setAdjacentRoom(Direction.EAST, kitchen_2);

        // Sets up the items for each room
        roomService.addItem(hallwayTwo, commonSword);
        roomService.addItem(livingRoom, redMoon);
        roomService.addItem(kitchen, chainMail);
        roomService.addItem(masterBedroom, rareSword);
        roomService.addItem(kitchen_2, kevlar);
        roomService.addItem(livingRoom_2, talisman);
        roomService.addItem(hallwayThree_2, talismanEvasion);
        roomService.addItem(servantQuarters_2, rapier);

        //Sets up the enemy for each room
        roomService.addEnemy(hallwayOne, minotaur);
        roomService.addEnemy(livingRoom, banshee);
        roomService.addEnemy(servantQuarters, medusa);
        roomService.addEnemy(diningHall, imp);
        roomService.addEnemy(kitchen, casper);
        roomService.addEnemy(hallwayThree, vampire);
        roomService.addEnemy(masterBedroom, lilith);
        roomService.addEnemy(hallwayThree_2, warlock);
        roomService.addEnemy(livingRoom_2, werewolf);
        roomService.addEnemy(diningHall_2, minotaur);
        roomService.addEnemy(kitchen_2, priestess);
        roomService.addEnemy(servantQuarters_2, death);
        roomService.addEnemy(hallwayOne_2, finalBoss);

        // Sets the game services
        battleService.setHero(hero);
        inventoryService.setHero(hero);
        GameMap map = new GameMap(hero);
        mapService.setMap(map);

        // Setup rooms opened of each progress
        ArrayList<Room> firstProg = new ArrayList<>();
        firstProg.add(hallwayOne);
        firstProg.add(hallwayTwo);
        firstProg.add(livingRoom);
        firstProg.add(servantQuarters);

        ArrayList<Room> secondProg = (ArrayList<Room>) firstProg.clone();
        secondProg.add(diningHall);
        secondProg.add(kitchen);

        ArrayList<Room> thirdProg = (ArrayList<Room>) secondProg.clone();
        thirdProg.add(hallwayThree);
        thirdProg.add(masterBedroom);

        ArrayList<Room> fourthProg = (ArrayList<Room>) thirdProg.clone();
        fourthProg.add(masterBedroom_2);
        fourthProg.add(hallwayThree_2);
        fourthProg.add(hallwayTwo_2);
        fourthProg.add(livingRoom_2);
        fourthProg.add(servantQuarters_2);

        ArrayList<Room> fifthProg = (ArrayList<Room>) fourthProg.clone();
        fifthProg.add(diningHall_2);
        fifthProg.add(kitchen_2);

        ArrayList<Room> sixthProg = (ArrayList<Room>) fifthProg.clone();
        sixthProg.add(diningHall_2);
        sixthProg.add(kitchen_2);

        progress.addProgressRooms(firstProg);
        progress.addProgressRooms(secondProg);
        progress.addProgressRooms(thirdProg);
        progress.addProgressRooms(fourthProg);
        progress.addProgressRooms(fifthProg);
        progress.addProgressRooms(sixthProg);

        //Initialized open room
        map.setOpenRooms(progress.getCurrentProgressRooms());

        //Set hero's current location
        hero.setCurrentLocation(hallwayOne);
    }
}
