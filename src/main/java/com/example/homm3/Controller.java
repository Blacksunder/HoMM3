package com.example.homm3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller {

    // Добавление существ Замка (Castle)
      OffenseCreatures Pikeman = new OffenseCreatures("Pikeman",4,5,10,1,3,1,0,0,1,0,0,"","",1,false);
      OffenseCreatures Halberdier = new OffenseCreatures("Halberdier",6,5,10,2,3,1,0,0,1,0,0,"","",1,false);
      ArcheryCreatures Archer = new ArcheryCreatures("Archer",6,3,10,2,3,1,0,0,2,0,0,"","",1,false);
      ArcheryCreatures Marksman = new ArcheryCreatures("Marksman",6,3,10,2,3,2,0,0,2,0,0,"","",1,false);
      OffenseCreatures Griffin = new OffenseCreatures("Griffin",8,8,25,3,6,1,0,0,3,0,0,"","",1,false);
      OffenseCreatures RoyalGriffin = new OffenseCreatures("RoyalGriffin",9,9,25,3,6,1,0,0,3,0,0,"","",1,false);
      OffenseCreatures Swordsman = new OffenseCreatures("Swordsman",10,12,35,6,9,1,0,0,4,0,0,"","",1,false);
      OffenseCreatures Crusader = new OffenseCreatures("Crusader",12,12,35,7,10,2,0,0,4,0,0,"","",1,false);
      ArcheryCreatures Monc = new ArcheryCreatures("Monc",12,7,30,10,12,1,0,0,5,0,0,"","",1,false);
      ArcheryCreatures Zealot = new ArcheryCreatures("Zealot",12,10,30,10,12,1,0,0,5,0,0,"","",1,false);
      OffenseCreatures Cavalier = new OffenseCreatures("Cavalier",15,15,100,15,25,1,0,0,6,0,0,"","",1,false);
      OffenseCreatures Champion = new OffenseCreatures("Champion",16,16,100,20,25,1,0,0,6,0,0,"","",1,false);
      OffenseCreatures Angel = new OffenseCreatures("Angel",20,20,200,50,50, 1,0,0,7,0,0,"Devil","ArchDevil",1.5f,false);
      OffenseCreatures Archangel = new OffenseCreatures("Archangel",30,30,250,50,50,1,0,0,7,0,0,"Devil","ArchDevil",1.5f,false);

      Creatures[] CastleCreatures = new Creatures[] {Pikeman,Halberdier,Archer,Marksman,Griffin,RoyalGriffin,Swordsman,Crusader,Monc,Zealot,Cavalier,Champion,Angel,Archangel};

      // Добавление существ Оплота (Rampart)
      OffenseCreatures Centaur = new OffenseCreatures("Centaur",5,3,8,2,3,1,0,0,1,0,0,"","",1,false);
      OffenseCreatures CentaurCaptain = new OffenseCreatures("CentaurCaptain",6,3,10,2,3,1,0,0,1,0,0,"","",1,false);
      OffenseCreatures Dwarf = new OffenseCreatures("Dwarf",6,7,20,2,3,1,0,0,1,0,0,"","",1,false);
      OffenseCreatures BattleDwarf = new OffenseCreatures("BattleDworf",7,7,20,2,4,1,0,0,2,0,0,"","",1,false);
      ArcheryCreatures WoodElf = new ArcheryCreatures("WoodElf",9,5,15,3,5,1,0,0,3,0,0,"","",1,false);
      ArcheryCreatures GrandElf = new ArcheryCreatures("GrandElf",9,5,15,3,5,2,0,0,3,0,0,"","",1,false);
      OffenseCreatures Pegasus = new OffenseCreatures("Pegasus",9,8,30,5,9,1,0,0,4,0,0,"","",1,false);
      OffenseCreatures SilverPegasus = new OffenseCreatures("SilverPegasus",9,10,30,5,9,1,0,0,4,0,0,"","",1,false);
      OffenseCreatures DendroidGuard = new OffenseCreatures("DendroidGuard",9,12,55,10,14,1,0,0,5,0,0,"","",1,false);
      OffenseCreatures DendroidSoldier = new OffenseCreatures("DendroidSoldier",9,12,65,10,14,1,0,0,5,0,0,"","",1,false);
      OffenseCreatures Unicorn = new OffenseCreatures("Unicorn",15,14,90,18,22,1,0,0,6,0,0,"","",1,false);
      OffenseCreatures WarUnicorn = new OffenseCreatures("WarUnicorn",15,14,110,18,22,1,0,0,6,0,0,"","",1,false);
      OffenseCreatures GreenDragon = new OffenseCreatures("GreenDragon",18,18,180,40,50,1,0,0,7,0,0,"","",1,false);
      OffenseCreatures GoldDragon = new OffenseCreatures("GoldDragon",27,27,250,40,50,1,0,0,7,0,0,"","",1,false);

      Creatures [] RampartCreatures = new Creatures[] {Centaur,CentaurCaptain,Dwarf,BattleDwarf,WoodElf,GrandElf,Pegasus,SilverPegasus,DendroidGuard,DendroidSoldier,Unicorn,WarUnicorn,GreenDragon,GoldDragon};

      // Добавление существ Башни (Tower)
      OffenseCreatures Gremlin = new OffenseCreatures("Gremlin",3,3,4,1,2,1,0,0,1,0,0,"","",1,false);
      ArcheryCreatures MasterGremlin = new ArcheryCreatures("MasterGremlin",4,4,4,1,2,1,0,0,1,0,0,"","",1,false);
      OffenseCreatures StoneGargoyle = new OffenseCreatures("StoneGargoyle",6,6,16,2,3,1,0,0,2,0,0,"","",1,false);
      OffenseCreatures ObsidianGargoyle = new OffenseCreatures("ObsidianGargoyle",7,7,16,2,3,1,0,0,2,0,0,"","",1,false);
      OffenseCreatures StoneGolem = new OffenseCreatures("StoneGolem",7,10,30,4,5,1,0,0,3,0,0,"","",1,true);
      OffenseCreatures IronGolem = new OffenseCreatures("IronGolem",9,10,35,4,5,1,0,0,3,0,0,"","",1,true);
      ArcheryCreatures Mage = new ArcheryCreatures("Mage",11,8,25,7,9,1,0,0,4,0,0,"","",1,false);
      ArcheryCreatures ArchMage = new ArcheryCreatures("ArchMage",12,9,30,7,9,1,0,0,4,0,0,"","",1,false);
      OffenseCreatures Genie = new OffenseCreatures("Genie",12,12,40,13,16,1,0,0,5,0,0,"Efreet","EfreetSultan",1.5f,false);
      OffenseCreatures MasterGenie = new OffenseCreatures("MasterGenie",12,12,40,13,16,1,0,0,5,0,0,"Efreet","EfreetSultan",1.5f,false);
      OffenseCreatures Naga = new OffenseCreatures("Naga",16,13,110,20,20,1,0,0,6,0,0,"","",1,false);
      OffenseCreatures NagaQueen = new OffenseCreatures("NagaQueen",16,13,110,30,30,1,0,0,6,0,0,"","",1,false);
      OffenseCreatures Giant = new OffenseCreatures("Giant",19,16,150,40,60,1,0,0,7,0,0,"","",1,true);
      ArcheryCreatures Titan = new ArcheryCreatures("Titan",24,24,300,40,60,1,0,0,7,0,0,"BlackDragon","",1.5f,true);

      Creatures[] TowerCreatures = new Creatures[]{Gremlin,MasterGremlin,StoneGargoyle,ObsidianGargoyle,StoneGolem,IronGolem,Mage,ArchMage,Genie,MasterGenie,Naga,NagaQueen,Giant,Titan};

      // Добавление существ Инферно (Inferno)
      OffenseCreatures Imp = new OffenseCreatures("Imp",2,3,4,1,2,1,0,0,1,0,0,"","",1,false);
      OffenseCreatures Familiar = new OffenseCreatures("Familiar",4,4,4,1,2,1,0,0,1,0,0,"","",1,false);
      ArcheryCreatures Gog = new ArcheryCreatures("Gog",6,4,13,2,4,1,0,0,2,0,0,"","",1,false);
      ArcheryCreatures Magog = new ArcheryCreatures("Magog",7,4,13,2,4,1,0,0,2,0,0,"","",1,false);
      OffenseCreatures HellHound = new OffenseCreatures("HellHound",10,6,25,2,7,1,0,0,3,0,0,"","",1,false);
      OffenseCreatures Cerberus = new OffenseCreatures("Cerberus",10,8,25,2,7,1,0,0,3,0,0,"","",1,false);
      OffenseCreatures Demon = new OffenseCreatures("Demon",10,10,35,7,9,1,0,0,4,0,0,"","",1,false);
      OffenseCreatures HornedDemon = new OffenseCreatures("HornedDemon",10,10,40,7,9,1,0,0,4,0,0,"","",1,false);
      OffenseCreatures PitFiend = new OffenseCreatures("PitFiend",13,13,45,13,17,1,0,0,5,0,0,"","",1,false);
      OffenseCreatures PitLord = new OffenseCreatures("PitLord",13,13,45,13,17,1,0,0,5,0,0,"","",1,false);
      OffenseCreatures Efreet = new OffenseCreatures("Efreet",16,12,90,16,24,1,0,0,6,0,0,"Genie","MasterGenie",1.5f,false);
      OffenseCreatures EfreetSultan = new OffenseCreatures("EfreetSultan",16,14,90,16,24,1,0,0,6,0,0,"Genie","MasterGenie",1.5f,false);
      OffenseCreatures Devil = new OffenseCreatures("Devil",19,21,160,30,40,1,0,0,7,0,0,"Angel","Archangel",1.5f,false);
      OffenseCreatures ArchDevil = new OffenseCreatures("ArchDevil",26,28,200,30,40,1,0,0,7,0,0,"Angel","Archangel",1.5f,false);

      Creatures[] InfernoCreatures = new Creatures[] {Imp,Familiar,Gog,Magog,HellHound,Cerberus,Demon,HornedDemon,PitFiend,PitLord,Efreet,EfreetSultan,Devil,ArchDevil};

      // Добавление существ Некрополиса (Necropolis)
      OffenseCreatures Skeleton = new OffenseCreatures("Skeleton",5,4,6,1,3,1,0,0,1,0,0,"","",1,true);
      OffenseCreatures SkeletonWarrior = new OffenseCreatures("SkeletonWarrior",6,6,6,1,3,1,0,0,1,0,0,"","",1,true);
      OffenseCreatures WalkingDead = new OffenseCreatures("WalkingDead",5,5,15,2,3,1,0,0,2,0,0,"","",1,true);
      OffenseCreatures Zombie = new OffenseCreatures("Zombie",5,5,20,2,3,1,0,0,2,0,0,"","",1,true);
      OffenseCreatures Wight = new OffenseCreatures("Wight",7,7,18,3,5,1,0,0,3,0,0,"","",1,true);
      OffenseCreatures Wraith = new OffenseCreatures("Wraith",7,7,18,3,5,1,0,0,3,0,0,"","",1,true);
      OffenseCreatures Vampire = new OffenseCreatures("Vampire",10,9,30,5,8,1,0,0,4,0,0,"","",1,true);
      OffenseCreatures VampireLord = new OffenseCreatures("VampireLord",10,10,40,5,8,1,0,0,4,0,0,"","",1,true);
      ArcheryCreatures Lich = new ArcheryCreatures("Lich",13,10,30,11,13,1,0,0,5,0,0,"","",1,true);
      ArcheryCreatures PowerLich = new ArcheryCreatures("PowerLich",13,10,40,11,15,1,0,0,5,0,0,"","",1,true);
      OffenseCreatures BlackNight = new OffenseCreatures("BlackNight",16,16,120,15,30,1,0,0,6,0,0,"","",1,true);
      OffenseCreatures DreadNight = new OffenseCreatures("DreadNight",18,18,120,15,30,1,0,0,6,0,0,"","",1,true);
      OffenseCreatures BoneDragon = new OffenseCreatures("BoneDragon",17,15,150,25,50,1,0,0,7,0,0,"","",1,true);
      OffenseCreatures GhostDragon = new OffenseCreatures("GhostDragon",19,17,200,25,50,1,0,0,7,0,0,"","",1,true);

      Creatures[] NecropolisCreatures = new Creatures[]{Skeleton,SkeletonWarrior,WalkingDead,Zombie,Wight,Wraith,Vampire,VampireLord,Lich,PowerLich,BlackNight,DreadNight,BoneDragon,GhostDragon};

      // Добавление существ Темницы (Dungeon)
      OffenseCreatures Troglodyte = new OffenseCreatures("Troglodyte",4,3,5,1,3,1,0,0,1,0,0,"","",1,false);
      OffenseCreatures InfernalTroglodyte = new OffenseCreatures("InfernalTroglodyte",5,4,6,1,3,1,0,0,1,0,0,"","",1,false);
      OffenseCreatures Harpy = new OffenseCreatures("Harpy",6,5,14,1,4,1,0,0,2,0,0,"","",1,false);
      OffenseCreatures HarpyHag = new OffenseCreatures("HarpyHag",6,6,14,1,4,1,0,0,2,0,0,"","",1,false);
      ArcheryCreatures Beholder = new ArcheryCreatures("Beholder",9,7,22,3,5,1,0,0,3,0,0,"","",1,false);
      ArcheryCreatures EvilEye = new ArcheryCreatures("EvilEye",10,8,22,3,5,1,0,0,3,0,0,"","",1,false);
      ArcheryCreatures Medusa = new ArcheryCreatures("Medusa",9,9,25,6,8,1,0,0,4,0,0,"","",1,false);
      ArcheryCreatures MedusaQueen = new ArcheryCreatures("MedusaQueen",10,10,30,6,8,1,0,0,4,0,0,"","",1,false);
      OffenseCreatures Minotaur = new OffenseCreatures("Minotaur",14,12,50,12,20,1,0,0,5,0,0,"","",1,false);
      OffenseCreatures MinotaurKing = new OffenseCreatures("MinotaurKing",15,15,50,12,2,1,0,0,5,0,0,"","",1,false);
      OffenseCreatures Manticore = new OffenseCreatures("Manticore",15,13,80,14,20,1,0,0,6,0,0,"","",1,false);
      OffenseCreatures Scorpicore = new OffenseCreatures("Scorpicore",16,14,80,14,20,1,0,0,6,0,0,"","",1,false);
      OffenseCreatures RedDragon = new OffenseCreatures("RedDragon",19,19,180,40,50,1,0,0,7,0,0,"","",1,false);
      OffenseCreatures BlackDragon = new OffenseCreatures("BlackDragon",25,25,300,40,50,1,0,0,7,0,0,"Titan","",1.5f,false);

      Creatures[] DungeonCreatures = new Creatures[]{Troglodyte,InfernalTroglodyte,Harpy,HarpyHag,Beholder,EvilEye,Medusa,MedusaQueen,Minotaur,MinotaurKing,Manticore,Scorpicore,RedDragon,BlackDragon};

      // Добавление существ Цитадели (Stronghold)
      OffenseCreatures Goblin = new OffenseCreatures("Goblin",4,2,5,1,2,1,0,0,1,0,0,"","",1,false);
      OffenseCreatures Hobgoblin = new OffenseCreatures("Hobgoblin",5,3,5,1,2,1,0,0,1,0,0,"","",1,false);
      OffenseCreatures WolfRider = new OffenseCreatures("WolfRider",7,5,10,2,4,1,0,0,2,0,0,"","",1,false);
      OffenseCreatures WolfRaider = new OffenseCreatures("WolfRaider",8,5,10,3,4,2,0,0,2,0,0,"","",1,false);
      ArcheryCreatures Orc = new ArcheryCreatures("Orc",8,4,15,2,5,1,0,0,3,0,0,"","",1,false);
      ArcheryCreatures OrcChieftain = new ArcheryCreatures("OrcChieftain",8,4,20,2,5,1,0,0,3,0,0,"","",1,false);
      OffenseCreatures Ogre = new OffenseCreatures("Ogre",13,7,40,6,12,1,0,0,4,0,0,"","",1,false);
      OffenseCreatures OgreMage = new OffenseCreatures("OgreMage",13,7,60,6,12,1,0,0,4,0,0,"","",1,false);
      OffenseCreatures Roc = new OffenseCreatures("Roc",13,11,60,11,15,1,0,0,5,0,0,"","",1,false);
      OffenseCreatures Thunderbird = new OffenseCreatures("Thunderbird",13,11,60,11,15,1,0,0,5,0,10,"","",1,false);
      ArcheryCreatures Cyclop = new ArcheryCreatures("Cyclop",15,12,70,16,20,1,0,0,6,0,10,"","",1,false);
      ArcheryCreatures CyclopsKing = new ArcheryCreatures("CyclopsKing",17,13,70,16,20,1,0,0,6,0,0,"","",1,false);
      OffenseCreatures Behemoth = new OffenseCreatures("Behemoth",17,17,175, 30, 50, 1,0.3f,0,7,0,0,"","",1,false);
      OffenseCreatures AncientBehemoth = new OffenseCreatures("AncientBehemoth",19,19,300,30,50,1,0.8f,0,7,0,0,"","",1,false);

      Creatures[] StrongholdCreatures = new Creatures[]{Goblin,Hobgoblin,WolfRider,WolfRaider,Orc,OrcChieftain,Ogre,OgreMage,Roc,Thunderbird,Cyclop,CyclopsKing,Behemoth,AncientBehemoth};

      // Добавление существ Крепости (Fortress)
      OffenseCreatures Gnoll = new OffenseCreatures("Gnoll",3,5,6,2,3,1,0,0,1,0,0,"","",1,false);
      OffenseCreatures GnollMarauder = new OffenseCreatures("GnollMarauder",4,6,6,2,3,1,0,0,1,0,0,"","",1,false);
      ArcheryCreatures Lizardman = new ArcheryCreatures("Lizardman",5,6,14,2,3,1,0,0,2,0,0,"","",1,false);
      ArcheryCreatures LizardWarrior = new ArcheryCreatures("LizardWarrior",6,8,15,2,5,1,0,0,2,0,0,"","",1,false);
      OffenseCreatures SerpentFly = new OffenseCreatures("SerpentFly",7,9,20,2,5,1,0,0,3,0,0,"","",1,false);
      OffenseCreatures DragonFly = new OffenseCreatures("DragonFly",8,10,20,2,5,1,0,0,3,0,0,"","",1,false);
      OffenseCreatures Basilisk = new OffenseCreatures("Basilisk",11,11,35,6,10,1,0,0,4,0,0,"","",1,false);
      OffenseCreatures GreaterBasilisk = new OffenseCreatures("GreaterBasilisk",12,12,40,6,10,1,0,0,4,0,0,"","",1,false);
      OffenseCreatures Gorgon = new OffenseCreatures("Gorgon",10,14,70,12,16,1,0,0,5,0,0,"","",1,false);
      OffenseCreatures MightyGorgon = new OffenseCreatures("MightyGorgon",11,16,70,12,16,1,0,0,5,0.1f,0,"","",1,false);
      OffenseCreatures Wyvern = new OffenseCreatures("Wyvern",14,14,70,14,18, 1, 0,0,6,0,0,"","",1,false);
      OffenseCreatures WyvernMonarch = new OffenseCreatures("WyvernMonarch",14,14,70,18,22,1,0,0,6,0,0,"","",1,false);
      OffenseCreatures Hydra = new OffenseCreatures("Hydra",16,18,160,25,45,1,0,0,7,0,0,"","",1,false);
      OffenseCreatures ChaosHydra = new OffenseCreatures("ChaosHydra",18,20,250,25,45,1,0,0,7,0,0,"","",1,false);

      Creatures[] FortressCreatures = new Creatures[] {Gnoll,GnollMarauder,Lizardman,LizardWarrior,SerpentFly,DragonFly,Basilisk,GreaterBasilisk,Gorgon,MightyGorgon,Wyvern,WyvernMonarch,Hydra,ChaosHydra};

      // Добавление существ Сопряжения (Conflux)
      OffenseCreatures Pixie = new OffenseCreatures("Pixie",2,2,3,1,2,1,0,0,1,0,0,"","",1,false);
      OffenseCreatures Sprite = new OffenseCreatures("Sprite",2,2,3,1,3,1,0,0,1,0,0,"","",1,false);
      OffenseCreatures AirElemental = new OffenseCreatures("AirElemental",9,9,25,2,8,1,0,0,2,0,0,"EarthElemental","MagmaElemental",2,true);
      ArcheryCreatures StormElemental = new ArcheryCreatures("StormElemental",9,9,25,2,8,1,0,0,2,0,0,"EarthElemental","MagmaElemental",2,true);
      OffenseCreatures WaterElemental = new OffenseCreatures("WaterElemental",8,10,30,3,7,1,0,0,3,0,0,"FireElemental","EnergyElemental",2,true);
      ArcheryCreatures IceElemental = new ArcheryCreatures("IceElemental",8,10,30,3,7,1,0,0,3,0,0,"FireElemental","EnergyElemental",2,true);
      OffenseCreatures FireElemental = new OffenseCreatures("FireElemental",10,8,35,4,6,1,0,0,4,0,0,"WaterElemental","IceElemental",2,true);
      OffenseCreatures EnergyElemental = new OffenseCreatures("EnergyElemental",12,8,35,4,6,1,0,0,4,0,0,"WaterElemental","IceElemental",2,true);
      OffenseCreatures EarthElemental = new OffenseCreatures("EarthElemental",10,10,40,4,8,1,0,0,5,0,0,"AirElemental","StormElemental",2,true);
      OffenseCreatures MagmaElemental = new OffenseCreatures("MagmaElemental",11,11,40,6,11,1,0,0,5,0,0,"AirElemental","StormElemental",2,true);
      PsychicElemental PsychicElemental = new PsychicElemental("PsychicElemental",15,13,75,10,20,1,0,0,6,0,0,"","",1,true);
      MagicElemental MagicElemental = new MagicElemental("MagicElemental",16,13,80,15,25,1,0,0,6,0,0,"","",1,true);
      OffenseCreatures Firebird = new OffenseCreatures("Firebird",18,18,150,30,40,1,0,0,7,0,0,"","",1,false);
      OffenseCreatures Phoenix = new OffenseCreatures("Phoenix",21,18,200,30,40,1,0,0,7,0,0,"","",1,false);

      Creatures[] ConfluxCreatures = new Creatures[] {Pixie,Sprite,AirElemental,StormElemental,WaterElemental,IceElemental,FireElemental,EnergyElemental,EarthElemental,MagmaElemental,PsychicElemental,MagicElemental,Firebird,Phoenix};

      // Добавление существ Причала (Cove)
      OffenseCreatures Nymph = new OffenseCreatures("Nymph",5,2,4,1,2,1,0,0,1,0,0,"","",1,false);
      OffenseCreatures Oceanid = new OffenseCreatures("Oceanid",6,2,4,1,3,1,0,0,1,0,0,"","",1,false);
      OffenseCreatures Sailor = new OffenseCreatures("Sailor",7,4,15,2,4,1,0,0,2,0,0,"","",1,false);
      OffenseCreatures Thug = new OffenseCreatures("Thug",8,6,15,3,4,1,0,0,2,0,0,"","",1,false);
      ArcheryCreatures Pirate = new ArcheryCreatures("Pirate",8,6,15,3,7,1,0,0,3,0,0,"","",1,false);
      ArcheryCreatures Corsair = new ArcheryCreatures("Corsair",10,8,15,3,7,1,0,0,3,0,0,"","",1,false);
      ArcheryCreatures SeaWolfs = new ArcheryCreatures("SeaWolfs",12,11,16,3,7,1,0,0,3,0.03f,0,"","",1,false);
      OffenseCreatures OceanSpirits = new OffenseCreatures("OceanSpirits",10,8,30,6,9,1,0,0,4,0,0,"","",1,false);
      Assids Assid = new Assids("Assid",11,8,30,6,10,1,0,0,4,0,0,"","",1,false);
      ArcheryCreatures SeaPriestess = new ArcheryCreatures("SeaPriestess",12,7,35,10,14,1,0,0,5,0,0,"","",1,false);
      ArcheryCreatures Charmer = new ArcheryCreatures("Charmer",12,9,35,10,16,1,0,0,5,0,0,"","",1,false);
      OffenseCreatures Nix = new OffenseCreatures("Nix",13,16,90,18,20,1,0,0.3f,6,0,0,"","",1,false);
      OffenseCreatures WarriorNix = new OffenseCreatures("WarriorNix",14,17,100,18,22,1,0,0.6f,6,0,0,"","",1,false);
      OffenseCreatures SeaSnake = new OffenseCreatures("SeaSnake",22,13,180,30,55,1,0,0,7,0,0,"","",1,false);
      OffenseCreatures Aspid = new OffenseCreatures("Aspid",29,20,300,30,55,1,0,0,7,0,0,"","",1,false);

      Creatures[] CoveCreatures = new Creatures[] {Nymph,Oceanid,Sailor,Thug,Pirate,Corsair,SeaWolfs,OceanSpirits,Assid,SeaPriestess,Charmer,Nix,WarriorNix,SeaSnake,Aspid};

      // Добавление существ Фабрики (Factory)
      ArcheryCreatures Hobbit = new ArcheryCreatures("Hobbit",4,2,3,1,3,1,0,0,1,0,0,"","",1,false);
      ArcheryCreatures GrenadierHobbit = new ArcheryCreatures("GrenadierHobbit",5,2,3,2,3,1,0.2f,0,1,0,0,"","",1,false);
      OffenseCreatures Mechanic = new OffenseCreatures("Mechanic",6,5,14,2,4,1,0,0,2,0,0,"","",1,false);
      OffenseCreatures Engineer = new OffenseCreatures("Engineer",7,5,16,2,5,1,0,0,2,0,0,"","",1,false);
      OffenseCreatures Armadillo = new OffenseCreatures("Armadillo",5,10,25,3,5,1,0,0,3,0,0,"","",1,false);
      OffenseCreatures ChiefArmadillo = new OffenseCreatures("ChiefArmadillo",5,11,25,3,5,1,0,0,3,0,0,"","",1,false);
      OffenseCreatures Automaton = new OffenseCreatures("Automaton",12,10,30,7,7,1,0,0,4,0,0,"","",1,true);
      OffenseCreatures AutomatonGuard = new OffenseCreatures("AutomatonGuard",12,10,30,9,9,1,0,0,4,0,0,"","",1,true);
      OffenseCreatures SoundWorm = new OffenseCreatures("SoundWorm",13,12,50,12,16,1,0,0,5,0,0,"","",1,false);
      OffenseCreatures OlgoyHorkoy = new OffenseCreatures("OlgoyHorkoy",15,12,60,12,16,1,0,0,5,0,0,"","",1,false);
      ArcheryCreatures Gunner = new ArcheryCreatures("Gunner",17,12,45,14,24,1,0,0,6,0,0,"","",1,false);
      ArcheryCreatures BountyHunter = new ArcheryCreatures("BountyHunter",18,14,45,14,24,1,0,0,6,0,0,"","",1,false);
      OffenseCreatures Couatl = new OffenseCreatures("Couatl",17,17,160,25,45,1,0,0,7,0,0,"","",1,false);
      OffenseCreatures CrimsonCouatl = new OffenseCreatures("CrimsonCouatl",21,21,200,25,45,1,0,0,7,0,0,"","",1,false);
      OffenseCreatures Dreadnought = new OffenseCreatures("Dreadnought",18,20,200,40,50,1,0,0,7,0,0,"","",1,true);
      OffenseCreatures Juggernaut = new OffenseCreatures("Juggernaut",23,23,300,40,50,1,0,0,7,0,0,"","",1,true);

      Creatures[] FactoryCreatures = new Creatures[] {Hobbit,GrenadierHobbit,Mechanic,Engineer,Armadillo,ChiefArmadillo,Automaton,AutomatonGuard,SoundWorm,OlgoyHorkoy,Gunner,BountyHunter,Couatl,CrimsonCouatl,Dreadnought,Juggernaut};

      // Добавление нейтральных существ
      OffenseCreatures Peasant = new OffenseCreatures("Peasant",1,1,1,1,1,1,0,0,1,0,0,"","",1,false);
      OffenseCreatures Rogue = new OffenseCreatures("Rogue",8,3,10,2,4,1,0,0,2,0,0,"","",1,false);
      OffenseCreatures Boar = new OffenseCreatures("Boar",6,5,15,2,3,1,0,0,2,0,0,"","",1,false);
      OffenseCreatures Nomad = new OffenseCreatures("Nomad",9,8,30,2,6,1,0,0,3,0,0,"","",1,false);
      OffenseCreatures Mummy = new OffenseCreatures("Mummy",7,7,30,3,5,1,0,0,3,0,0,"","",1,true);
      ArcheryCreatures Sharpshooter = new ArcheryCreatures("Sharpshooter",12,10,15,8,10,1,0,0,4,0,0,"","",1,false);
      OffenseCreatures GoldGolem = new OffenseCreatures("GoldGolem",11,12,50,8,10,1,0,0,5,0,0,"","",1,true);
      OffenseCreatures Troll = new OffenseCreatures("Troll",14,7,40,10,15,1,0,0,5,0,0,"","",1,false);
      OffenseCreatures DiamondGolem = new OffenseCreatures("DiamondGolem",13,12,60,10,14,1,0,0,6,0,0,"","",1,true);
      OffenseCreatures Enchanter = new OffenseCreatures("Enchanter",17,12,30,14,14,1,0,0,6,0,0,"","",1,false);
      OffenseCreatures FaerieDragon = new OffenseCreatures("FaerieDragon",20,20,500,20,30,1,0,0,7,0,0,"","",1,false);
      OffenseCreatures RustDragon = new OffenseCreatures("RustDragon",30,30,750,50,50,1,0,0,7,0,0,"","",1,false);
      OffenseCreatures CrystalDragon = new OffenseCreatures("CrystalDragon",40,40,800,60,75,1,0,0,7,0,0,"","",1,false);
      OffenseCreatures AzureDragon = new OffenseCreatures("AzureDragon",50,50,1000,70,80,1,0,0,7,0,0,"","",1,false);

      Creatures[] NeutralCreatures = new Creatures[] {Peasant,Rogue,Boar,Nomad,Mummy,Sharpshooter,GoldGolem,Troll,DiamondGolem,Enchanter,FaerieDragon,RustDragon,CrystalDragon,AzureDragon};

      Creatures[] creatures = new Creatures[] {Pikeman,Halberdier,Archer,Marksman,Griffin,RoyalGriffin,Swordsman,Crusader,Monc,Zealot,Cavalier,Champion,Angel,Archangel,
      Centaur,CentaurCaptain,Dwarf,BattleDwarf,WoodElf,GrandElf,Pegasus,SilverPegasus,DendroidGuard,DendroidSoldier,Unicorn,WarUnicorn,GreenDragon,GoldDragon,
      Gremlin,MasterGremlin,StoneGargoyle,ObsidianGargoyle,StoneGolem,IronGolem,Mage,ArchMage,Genie,MasterGenie,Naga,NagaQueen,Giant,Titan,
      Imp,Familiar,Gog,Magog,HellHound,Cerberus,Demon,HornedDemon,PitFiend,PitLord,Efreet,EfreetSultan,Devil,ArchDevil,
      Skeleton,SkeletonWarrior,WalkingDead,Zombie,Wight,Wraith,Vampire,VampireLord,Lich,PowerLich,BlackNight,DreadNight,BoneDragon,GhostDragon,
      Troglodyte,InfernalTroglodyte,Harpy,HarpyHag,Beholder,EvilEye,Medusa,MedusaQueen,Minotaur,MinotaurKing,Manticore,Scorpicore,RedDragon,BlackDragon,
      Goblin,Hobgoblin,WolfRider,WolfRaider,Orc,OrcChieftain,Ogre,OgreMage,Roc,Thunderbird,Cyclop,CyclopsKing,Behemoth,AncientBehemoth,
      Gnoll,GnollMarauder,Lizardman,LizardWarrior,SerpentFly,DragonFly,Basilisk,GreaterBasilisk,Gorgon,MightyGorgon,Wyvern,WyvernMonarch,Hydra,ChaosHydra,
      Pixie,Sprite,AirElemental,StormElemental,WaterElemental,IceElemental,FireElemental,EnergyElemental,EarthElemental,MagmaElemental,PsychicElemental,MagicElemental,Firebird,Phoenix,
      Nymph,Oceanid,Sailor,Thug,Pirate,Corsair,SeaWolfs,OceanSpirits,Assid,SeaPriestess,Charmer,Nix,WarriorNix,SeaSnake,Aspid,
      Hobbit,GrenadierHobbit,Mechanic,Engineer,Armadillo,ChiefArmadillo,Automaton,AutomatonGuard,SoundWorm,OlgoyHorkoy,Gunner,BountyHunter,Couatl,CrimsonCouatl,Dreadnought,Juggernaut,
      Peasant,Rogue,Boar,Nomad,Mummy,Sharpshooter,GoldGolem,Troll,DiamondGolem,Enchanter,FaerieDragon,RustDragon,CrystalDragon,AzureDragon};

      ObservableList<String> SetFraction = FXCollections.observableArrayList("Castle","Rampart","Tower","Inferno","Necropolis","Dungeon","Stronghold","Fortress","Conflux","Cove","Factory","Neutral");
      ObservableList<String> SetSkills = FXCollections.observableArrayList("None","Basic","Advanced","Expert");
      ObservableList<String> SetSpec = FXCollections.observableArrayList("Other","Offense","Armorer","Archery","CreaturesNotFix","CreaturesFix");
      ObservableList<String> Castle = FXCollections.observableArrayList(Pikeman.Name,Halberdier.Name,Archer.Name, Marksman.Name, Griffin.Name, RoyalGriffin.Name, Swordsman.Name, Crusader.Name, Monc.Name, Zealot.Name, Cavalier.Name,Champion.Name, Angel.Name, Archangel.Name);
      ObservableList<String> Rampart = FXCollections.observableArrayList(Centaur.Name,CentaurCaptain.Name, WoodElf.Name, Dwarf.Name, BattleDwarf.Name, GrandElf.Name, Pegasus.Name, SilverPegasus.Name, DendroidGuard.Name, DendroidSoldier.Name, Unicorn.Name,WarUnicorn.Name, GreenDragon.Name, GoldDragon.Name);
      ObservableList<String> Tower = FXCollections.observableArrayList(Gremlin.Name, MasterGremlin.Name, StoneGargoyle.Name, ObsidianGargoyle.Name, StoneGolem.Name, IronGolem.Name, Mage.Name, ArchMage.Name, Genie.Name, MasterGenie.Name, Naga.Name, NagaQueen.Name, Giant.Name, Titan.Name);
      ObservableList<String> Inferno = FXCollections.observableArrayList(Imp.Name, Familiar.Name, Gog.Name, Magog.Name, HellHound.Name, Cerberus.Name, Demon.Name, HornedDemon.Name, PitFiend.Name, PitLord.Name, Efreet.Name, EfreetSultan.Name, Devil.Name, ArchDevil.Name);
      ObservableList<String> Necropolis = FXCollections.observableArrayList(Skeleton.Name, SkeletonWarrior.Name, WalkingDead.Name, Zombie.Name, Wight.Name, Wraith.Name, Vampire.Name, VampireLord.Name, Lich.Name, PowerLich.Name, BlackNight.Name, DreadNight.Name, BoneDragon.Name, GhostDragon.Name);
      ObservableList<String> Dungeon = FXCollections.observableArrayList(Troglodyte.Name, InfernalTroglodyte.Name, Harpy.Name, HarpyHag.Name, Beholder.Name, EvilEye.Name, Medusa.Name, MedusaQueen.Name, Minotaur.Name, MinotaurKing.Name, Manticore.Name, Scorpicore.Name, RedDragon.Name, BlackDragon.Name);
      ObservableList<String> Stronghold = FXCollections.observableArrayList(Goblin.Name, Hobgoblin.Name, WolfRider.Name, WolfRaider.Name, Orc.Name, OrcChieftain.Name, Ogre.Name, OgreMage.Name, Roc.Name, Thunderbird.Name, Cyclop.Name, CyclopsKing.Name, Behemoth.Name, AncientBehemoth.Name);
      ObservableList<String> Fortress = FXCollections.observableArrayList(Gnoll.Name, GnollMarauder.Name, Lizardman.Name, LizardWarrior.Name, SerpentFly.Name, DragonFly.Name, Basilisk.Name, GreaterBasilisk.Name, Gorgon.Name, MightyGorgon.Name, Wyvern.Name, WyvernMonarch.Name, Hydra.Name, ChaosHydra.Name);
      ObservableList<String> Conflux = FXCollections.observableArrayList(Pixie.Name, Sprite.Name, AirElemental.Name, StormElemental.Name, WaterElemental.Name, IceElemental.Name, FireElemental.Name, EnergyElemental.Name, EarthElemental.Name, MagmaElemental.Name, PsychicElemental.Name, MagicElemental.Name, Firebird.Name, Phoenix.Name);
      ObservableList<String> Cove = FXCollections.observableArrayList(Nymph.Name, Oceanid.Name, Sailor.Name, Thug.Name, Pirate.Name, Corsair.Name, SeaWolfs.Name, OceanSpirits.Name, Assid.Name, SeaPriestess.Name, Charmer.Name, Nix.Name, WarriorNix.Name, SeaSnake.Name, Aspid.Name);
      ObservableList<String> Factory = FXCollections.observableArrayList(Hobbit.Name, GrenadierHobbit.Name, Mechanic.Name, Engineer.Name, Armadillo.Name, ChiefArmadillo.Name, Automaton.Name, AutomatonGuard.Name, SoundWorm.Name, OlgoyHorkoy.Name, Gunner.Name, BountyHunter.Name, Couatl.Name, CrimsonCouatl.Name, Dreadnought.Name, Juggernaut.Name);
      ObservableList<String> Neutral = FXCollections.observableArrayList(Peasant.Name, Rogue.Name, Boar.Name, Nomad.Name, Mummy.Name, Sharpshooter.Name, GoldGolem.Name, Troll.Name, DiamondGolem.Name, Enchanter.Name, FaerieDragon.Name, RustDragon.Name, CrystalDragon.Name, AzureDragon.Name);





      @FXML
    private TextField FirstHeroeFixAtt;

    @FXML
    private TextField FirstHeroeFixDef;

    @FXML
    private TextField SecondHeroeFixAtt;

    @FXML
    private  TextField SecondHeroeFixDef;

    @FXML
    private TextField Amount;

    @FXML
    private CheckBox AttackNeutral;

    @FXML
    private Button Calculate;

    @FXML
    private CheckBox DefenseNeutral;

    @FXML
    private ChoiceBox<String> FirstHeroeArchery;

    @FXML
    private ChoiceBox<String> FirstHeroeArmory;

    @FXML
    private ChoiceBox<String> FirstHeroeOffense;

    @FXML
    private ChoiceBox<String> FirstHeroeSpec;

    @FXML
    private TextArea ResultArea;

    @FXML
    private ChoiceBox<String> SecondHeroeArchery;

    @FXML
    private ChoiceBox<String> SecondHeroeArmory;

    @FXML
    private ChoiceBox<String> SecondHeroeOffense;

    @FXML
    private ChoiceBox<String> SecondHeroeSpec;

    @FXML
    private ChoiceBox<String> SetAttackCreature;

    @FXML
    private ChoiceBox<String> SetAttackFraction;

    @FXML
    private CheckBox SetAttackNativeLand;

    @FXML
    private ChoiceBox<String> SetDefenseCreature;

    @FXML
    private ChoiceBox<String> SetDefenseFraction;

    @FXML
    private CheckBox SetDefenseNativeLand;

    @FXML
    private TextField SetFirstHeroeAttack;

    @FXML
    private TextField SetFirstHeroeDefense;

    @FXML
    private TextField SetFirstHeroeExtraArchery;

    @FXML
    private TextField SetFirstHeroeLvl;

    @FXML
    private TextField SetSecondHeroeAttack;

    @FXML
    private TextField SetSecondHeroeDefense;

    @FXML
    private TextField SetSecondHeroeExtraArchery;

    @FXML
    private TextField SetSecondHeroeLvl;

    @FXML
    private Label FirstHeroeExtraArchery;

    @FXML
    private Label SecondHeroeExtraArchery;

    @FXML
    private Label FirstHeroeFixAttack;

    @FXML
    private Label FirstHeroeFixDefense;

    @FXML
    private Label SecondHeroeFixAttack;

    @FXML
    private Label SecondHeroeFixDefense;

    @FXML
    void Calculate(ActionEvent event) {

    }



    Heroe heroeAttack = new Heroe(
              0,
              0,
              0.0f,
              0,
              0.1f,
              0,
              false,
              false,
              false,
              false,
              0,
              0,
              false,
              1,
              false,
            false);

      Heroe heroeDefense = new Heroe(
              0,
              0,
              0,
              0,
              0,
              0,
              false,
              false,
              false,
              false,
              0,
              0,
              false,
              1,
              false,
              false);

    @FXML
    void initialize() {

        AttackNeutral.setSelected(false);
        DefenseNeutral.setSelected(false);
        SetAttackNativeLand.setSelected(false);
        SetDefenseNativeLand.setSelected(false);
        SetFirstHeroeAttack.setText("0");
        SetFirstHeroeDefense.setText("0");
        SetFirstHeroeLvl.setText("1");
        SetFirstHeroeExtraArchery.setText("0");
        SetFirstHeroeExtraArchery.setVisible(false);
        SetSecondHeroeAttack.setText("0");
        SetSecondHeroeDefense.setText("0");
        SetSecondHeroeLvl.setText("1");
        SetSecondHeroeExtraArchery.setText("0");
        SetSecondHeroeExtraArchery.setVisible(false);
        Amount.setText("1");
        FirstHeroeFixAtt.setText("0");
        FirstHeroeFixAtt.setVisible(false);
        FirstHeroeFixDef.setText("0");
        FirstHeroeFixDef.setVisible(false);
        SecondHeroeFixAtt.setText("0");
        SecondHeroeFixAtt.setVisible(false);
        SecondHeroeFixDef.setText("0");
        SecondHeroeFixDef.setVisible(false);
        FirstHeroeFixAttack.setVisible(false);
        FirstHeroeFixDefense.setVisible(false);
        SecondHeroeFixAttack.setVisible(false);
        SecondHeroeFixDefense.setVisible(false);
        FirstHeroeExtraArchery.setVisible(false);
        SecondHeroeExtraArchery.setVisible(false);


        AttackNeutral.selectedProperty().addListener((observable, oldValue, newValue) ->{
            heroeAttack.IsNeutral = newValue;
        } );

        DefenseNeutral.selectedProperty().addListener((observable, oldValue, newValue) ->{
            heroeDefense.IsNeutral = newValue;
        } );

        SetAttackNativeLand.selectedProperty().addListener((observable, oldValue, newValue) ->{
            heroeAttack.NativeLand = newValue;
        } );

        SetDefenseNativeLand.selectedProperty().addListener((observable, oldValue, newValue) ->{
            heroeDefense.NativeLand = newValue;
        } );

        SetFirstHeroeAttack.textProperty().addListener((observable, oldValue, newValue) -> {
    heroeAttack.Attack = Integer.parseInt(SetFirstHeroeAttack.getText());
    });
        SetFirstHeroeDefense.textProperty().addListener((observable, oldValue, newValue) -> {
    heroeAttack.Defense = Integer.parseInt(newValue);
    });
        SetFirstHeroeLvl.textProperty().addListener((observable, oldValue, newValue) -> {
    heroeAttack.lvl = Integer.parseInt(newValue);
    });
        SetFirstHeroeExtraArchery.textProperty().addListener((observable, oldValue, newValue) -> {
    heroeAttack.ExtraArchery = Integer.parseInt(newValue);
    });
        FirstHeroeFixAtt.textProperty().addListener((observable, oldValue, newValue) -> {
    heroeAttack.FixAtt = Integer.parseInt(newValue);
    });
        FirstHeroeFixDef.textProperty().addListener((observable, oldValue, newValue) -> {
    heroeAttack.FixDef = Integer.parseInt(newValue);
    });

        SetSecondHeroeAttack.textProperty().addListener((observable, oldValue, newValue) -> {
    heroeDefense.Attack = Integer.parseInt(newValue);
    });
        SetSecondHeroeDefense.textProperty().addListener((observable, oldValue, newValue) -> {
    heroeDefense.Defense = Integer.parseInt(newValue);
    });
        SetSecondHeroeLvl.textProperty().addListener((observable, oldValue, newValue) -> {
    heroeDefense.lvl = Integer.parseInt(newValue);
    });
        SetSecondHeroeExtraArchery.textProperty().addListener((observable, oldValue, newValue) -> {
    heroeDefense.ExtraArchery = Integer.parseInt(newValue);
    });
        SecondHeroeFixAtt.textProperty().addListener((observable, oldValue, newValue) -> {
    heroeDefense.FixAtt = Integer.parseInt(newValue);
    });
        SecondHeroeFixDef.textProperty().addListener((observable, oldValue, newValue) -> {
    heroeDefense.FixDef = Integer.parseInt(newValue);
    });


        SetAttackFraction.setItems(SetFraction);
//        SetAttackFraction.setValue("Neutral");
        SetAttackCreature.setItems(Neutral);
//        SetAttackCreature.setValue(Neutral.getFirst());

        SetDefenseFraction.setItems(SetFraction);
//        SetDefenseFraction.setValue("Neutral");
        SetDefenseCreature.setItems(Neutral);
//        SetDefenseCreature.setValue(Neutral.getFirst());

        FirstHeroeOffense.setItems(SetSkills);
        FirstHeroeOffense.setValue("None");
        FirstHeroeArmory.setItems(SetSkills);
        FirstHeroeArmory.setValue("None");
        FirstHeroeArchery.setItems(SetSkills);
        FirstHeroeArchery.setValue("None");

        SecondHeroeOffense.setItems(SetSkills);
        SecondHeroeOffense.setValue("None");
        SecondHeroeArmory.setItems(SetSkills);
        SecondHeroeArmory.setValue("None");
        SecondHeroeArchery.setItems(SetSkills);
        SecondHeroeArchery.setValue("None");

        FirstHeroeSpec.setItems(SetSpec);
        FirstHeroeSpec.setValue("Other");

        SecondHeroeSpec.setItems(SetSpec);
        SecondHeroeSpec.setValue("Other");

        FirstHeroeOffense.getSelectionModel().selectedItemProperty().addListener((v,OldValue,NewValue) -> {
            if (NewValue.equals("None")){
            heroeAttack.offense = 0;
        } else if (NewValue.equals("Basic")) {
            heroeAttack.offense = 0.1f;
        } else if (NewValue.equals("Advanced")) {
            heroeAttack.offense = 0.2f;
        } else if (NewValue.equals("Expert")) {
            heroeAttack.offense = 0.3f;
        }
        });

        FirstHeroeArmory.getSelectionModel().selectedItemProperty().addListener((v,OldValue,NewValue) -> {
            if (NewValue.equals("None")){
            heroeAttack.armorer = 0;
        } else if (NewValue.equals("Basic")) {
            heroeAttack.armorer = 0.05f;
        } else if (NewValue.equals("Advanced")) {
            heroeAttack.armorer = 0.1f;
        } else if (NewValue.equals("Expert")) {
            heroeAttack.armorer = 0.15f;
        }
        });

        FirstHeroeArchery.getSelectionModel().selectedItemProperty().addListener((v,OldValue,NewValue) -> {
            if (NewValue.equals("None")){
            SetFirstHeroeExtraArchery.setVisible(false);
            FirstHeroeExtraArchery.setVisible(false);
            heroeAttack.archery = 0;
        } else if (NewValue.equals("Basic")) {
            SetFirstHeroeExtraArchery.setVisible(true);
            FirstHeroeExtraArchery.setVisible(true);
            heroeAttack.archery = 0.1f;
        } else if (NewValue.equals("Advanced")) {
            SetFirstHeroeExtraArchery.setVisible(true);
            FirstHeroeExtraArchery.setVisible(true);
            heroeAttack.archery = 0.25f;
        } else if (NewValue.equals("Expert")) {
            SetFirstHeroeExtraArchery.setVisible(true);
            FirstHeroeExtraArchery.setVisible(true);
            heroeAttack.archery = 0.5f;
        }
        });



        SecondHeroeOffense.getSelectionModel().selectedItemProperty().addListener((v,OldValue,NewValue) -> {
            if (NewValue.equals("None")){
            heroeDefense.offense = 0;
        } else if (NewValue.equals("Basic")) {
            heroeDefense.offense = 0.1f;
        } else if (NewValue.equals("Advanced")) {
            heroeDefense.offense = 0.2f;
        } else if (NewValue.equals("Expert")) {
            heroeDefense.offense = 0.3f;
        }
        });

        SecondHeroeArmory.getSelectionModel().selectedItemProperty().addListener((v,OldValue,NewValue) -> {
            if (NewValue.equals("None")){
            heroeDefense.armorer = 0;
        } else if (NewValue.equals("Basic")) {
            heroeDefense.armorer = 0.05f;
        } else if (NewValue.equals("Advanced")) {
            heroeDefense.armorer = 0.1f;
        } else if (NewValue.equals("Expert")) {
            heroeDefense.armorer = 0.15f;
        }
        });

        SecondHeroeArchery.getSelectionModel().selectedItemProperty().addListener((v,OldValue,NewValue) -> {
            if (NewValue.equals("None")){
                SetSecondHeroeExtraArchery.setVisible(false);
                SecondHeroeExtraArchery.setVisible(false);
            heroeDefense.archery = 0;
        } else if (NewValue.equals("Basic")) {
                SetSecondHeroeExtraArchery.setVisible(true);
                SecondHeroeExtraArchery.setVisible(true);
            heroeDefense.archery = 0.1f;
        } else if (NewValue.equals("Advanced")) {
                SetSecondHeroeExtraArchery.setVisible(true);
                SecondHeroeExtraArchery.setVisible(true);
            heroeDefense.archery = 0.25f;
        } else if (NewValue.equals("Expert")) {
                SetSecondHeroeExtraArchery.setVisible(true);
                SecondHeroeExtraArchery.setVisible(true);
            heroeDefense.archery = 0.5f;
        }
        });

        FirstHeroeSpec.getSelectionModel().selectedItemProperty().addListener((v,OldValue,NewValue) -> {
            if (NewValue.equals("Other")){
            heroeAttack.SpecOffense = false;
            heroeAttack.SpecArchery = false;
            heroeAttack.SpecDefense = false;
            heroeAttack.SpecCreatureFix = false;
            heroeAttack.SpecCreatureNotFix = false;
            FirstHeroeFixAttack.setVisible(false);
            FirstHeroeFixDefense.setVisible(false);
            FirstHeroeFixAtt.setVisible(false);
            FirstHeroeFixDef.setVisible(false);
        } else if (NewValue.equals("Offense")) {
            heroeAttack.SpecOffense = true;
            heroeAttack.SpecArchery = false;
            heroeAttack.SpecDefense = false;
            heroeAttack.SpecCreatureFix = false;
            heroeAttack.SpecCreatureNotFix = false;
            FirstHeroeFixAttack.setVisible(false);
            FirstHeroeFixDefense.setVisible(false);
            FirstHeroeFixAtt.setVisible(false);
            FirstHeroeFixDef.setVisible(false);
        } else if (NewValue.equals("Armorer")) {
            heroeAttack.SpecOffense = false;
            heroeAttack.SpecArchery = false;
            heroeAttack.SpecDefense = true;
            heroeAttack.SpecCreatureFix = false;
            heroeAttack.SpecCreatureNotFix = false;
            FirstHeroeFixAttack.setVisible(false);
            FirstHeroeFixDefense.setVisible(false);
            FirstHeroeFixAtt.setVisible(false);
            FirstHeroeFixDef.setVisible(false);
        } else if (NewValue.equals("Archery")) {
            heroeAttack.SpecOffense = false;
            heroeAttack.SpecArchery = true;
            heroeAttack.SpecDefense = false;
            heroeAttack.SpecCreatureFix = false;
            heroeAttack.SpecCreatureNotFix = false;
            FirstHeroeFixAttack.setVisible(false);
            FirstHeroeFixDefense.setVisible(false);
            FirstHeroeFixAtt.setVisible(false);
            FirstHeroeFixDef.setVisible(false);
        } else if (NewValue.equals("CreaturesNotFix")) {
            heroeAttack.SpecOffense = false;
            heroeAttack.SpecArchery = false;
            heroeAttack.SpecDefense = false;
            heroeAttack.SpecCreatureFix = false;
            heroeAttack.SpecCreatureNotFix = true;
            FirstHeroeFixAttack.setVisible(false);
            FirstHeroeFixDefense.setVisible(false);
            FirstHeroeFixAtt.setVisible(false);
            FirstHeroeFixDef.setVisible(false);
        } else if (NewValue.equals("CreaturesFix")) {
            heroeAttack.SpecOffense = false;
            heroeAttack.SpecArchery = false;
            heroeAttack.SpecDefense = false;
            heroeAttack.SpecCreatureFix = true;
            heroeAttack.SpecCreatureNotFix = false;
            FirstHeroeFixAttack.setVisible(true);
            FirstHeroeFixDefense.setVisible(true);
            FirstHeroeFixAtt.setVisible(true);
            FirstHeroeFixDef.setVisible(true);
            heroeAttack.FixAtt = Integer.parseInt(FirstHeroeFixAtt.getText());
            heroeAttack.FixDef = Integer.parseInt(FirstHeroeFixDef.getText());
        }
        });

        SecondHeroeSpec.getSelectionModel().selectedItemProperty().addListener((v,OldValue,NewValue) -> {
            if (NewValue.equals("Other")){
            heroeDefense.SpecOffense = false;
            heroeDefense.SpecArchery = false;
            heroeDefense.SpecDefense = false;
            heroeDefense.SpecCreatureFix = false;
            heroeDefense.SpecCreatureNotFix = false;
            SecondHeroeFixAttack.setVisible(false);
            SecondHeroeFixDefense.setVisible(false);
            SecondHeroeFixAtt.setVisible(false);
            SecondHeroeFixDef.setVisible(false);
        } else if (NewValue.equals("Offense")) {
            heroeDefense.SpecOffense = true;
            heroeDefense.SpecArchery = false;
            heroeDefense.SpecDefense = false;
            heroeDefense.SpecCreatureFix = false;
            heroeDefense.SpecCreatureNotFix = false;
            SecondHeroeFixAttack.setVisible(false);
            SecondHeroeFixDefense.setVisible(false);
            SecondHeroeFixAtt.setVisible(false);
            SecondHeroeFixDef.setVisible(false);
        } else if (NewValue.equals("Armorer")) {
            heroeDefense.SpecOffense = false;
            heroeDefense.SpecArchery = false;
            heroeDefense.SpecDefense = true;
            heroeDefense.SpecCreatureFix = false;
            heroeDefense.SpecCreatureNotFix = false;
            SecondHeroeFixAttack.setVisible(false);
            SecondHeroeFixDefense.setVisible(false);
            SecondHeroeFixAtt.setVisible(false);
            SecondHeroeFixDef.setVisible(false);
        } else if (NewValue.equals("Archery")) {
            heroeDefense.SpecOffense = false;
            heroeDefense.SpecArchery = true;
            heroeDefense.SpecDefense = false;
            heroeDefense.SpecCreatureFix = false;
            heroeDefense.SpecCreatureNotFix = false;
            SecondHeroeFixAttack.setVisible(false);
            SecondHeroeFixDefense.setVisible(false);
            SecondHeroeFixAtt.setVisible(false);
            SecondHeroeFixDef.setVisible(false);
        } else if (NewValue.equals("CreaturesNotFix")) {
            heroeDefense.SpecOffense = false;
            heroeDefense.SpecArchery = false;
            heroeDefense.SpecDefense = false;
            heroeDefense.SpecCreatureFix = false;
            heroeDefense.SpecCreatureNotFix = true;
            SecondHeroeFixAttack.setVisible(false);
            SecondHeroeFixDefense.setVisible(false);
            SecondHeroeFixAtt.setVisible(false);
            SecondHeroeFixDef.setVisible(false);
        } else if (NewValue.equals("CreaturesFix")) {
            heroeDefense.SpecOffense = false;
            heroeDefense.SpecArchery = false;
            heroeDefense.SpecDefense = false;
            heroeDefense.SpecCreatureFix = true;
            heroeDefense.SpecCreatureNotFix = false;
            SecondHeroeFixAttack.setVisible(true);
            SecondHeroeFixDefense.setVisible(true);
            SecondHeroeFixAtt.setVisible(true);
            SecondHeroeFixDef.setVisible(true);
            heroeDefense.FixAtt = Integer.parseInt(SecondHeroeFixAtt.getText());
            heroeDefense.FixDef = Integer.parseInt(SecondHeroeFixDef.getText());
        }
        });



        AtomicInteger Attacking = new AtomicInteger();
        SetAttackFraction.getSelectionModel().selectedItemProperty().addListener((V,OldValue,NewValue) -> {
            if (NewValue.equals("Castle")) {
                SetAttackCreature.setItems(Castle);
            } else if (NewValue.equals("Rampart")) {
                SetAttackCreature.setItems(Rampart);
            } else if (NewValue.equals("Tower")) {
                SetAttackCreature.setItems(Tower);
            } else if (NewValue.equals("Inferno")) {
                SetAttackCreature.setItems(Inferno);
            } else if (NewValue.equals("Necropolis")) {
                SetAttackCreature.setItems(Necropolis);
            } else if (NewValue.equals("Dungeon")) {
                SetAttackCreature.setItems(Dungeon);
            } else if (NewValue.equals("Stronghold")) {
                SetAttackCreature.setItems(Stronghold);
            } else if (NewValue.equals("Fortress")) {
                SetAttackCreature.setItems(Fortress);
            } else if (NewValue.equals("Conflux")) {
                SetAttackCreature.setItems(Conflux);
            } else if (NewValue.equals("Cove")) {
                SetAttackCreature.setItems(Cove);
            } else if (NewValue.equals("Factory")) {
                SetAttackCreature.setItems(Factory);
            } else if (NewValue.equals("Neutral")) {
                SetAttackCreature.setItems(Neutral);
            }
            SetAttackCreature.getSelectionModel().selectedItemProperty().addListener((v,Oldvalue,Newvalue) -> {
                        for (int i = 0; i < creatures.length; i++) {
                            if (Objects.equals(Newvalue, creatures[i].Name)) {
                                Attacking.set(i);
                                break;
                            }
                        }
                });
        });

        AtomicInteger Defensing = new AtomicInteger();
        SetDefenseFraction.getSelectionModel().selectedItemProperty().addListener((V,OldValue,NewValue) -> {
            if (NewValue.equals("Castle")) {
                SetDefenseCreature.setItems(Castle);
            } else if (NewValue.equals("Rampart")) {
                SetDefenseCreature.setItems(Rampart);
            } else if (NewValue.equals("Tower")) {
                SetDefenseCreature.setItems(Tower);
            } else if (NewValue.equals("Inferno")) {
                SetDefenseCreature.setItems(Inferno);
            } else if (NewValue.equals("Necropolis")) {
                SetDefenseCreature.setItems(Necropolis);
            } else if (NewValue.equals("Dungeon")) {
                SetDefenseCreature.setItems(Dungeon);
            } else if (NewValue.equals("Stronghold")) {
                SetDefenseCreature.setItems(Stronghold);
            } else if (NewValue.equals("Fortress")) {
                SetDefenseCreature.setItems(Fortress);
            } else if (NewValue.equals("Conflux")) {
                SetDefenseCreature.setItems(Conflux);
            } else if (NewValue.equals("Cove")) {
                SetDefenseCreature.setItems(Cove);
            } else if (NewValue.equals("Factory")) {
                SetDefenseCreature.setItems(Factory);
            } else if (NewValue.equals("Neutral")) {
                SetDefenseCreature.setItems(Neutral);
            }
            SetDefenseCreature.getSelectionModel().selectedItemProperty().addListener((v,Oldvalue,Newvalue) -> {
                        for (int i = 0; i < creatures.length; i++) {
                            if (Objects.equals(Newvalue, creatures[i].Name)) {
                                Defensing.set(i);
                                break;
                            }
                        }
                });
        });

        Calculate.setOnAction(event -> {
            ResultArea.setText(HoMM3.kills(creatures[Attacking.get()], creatures[Defensing.get()], heroeAttack, heroeDefense, Integer.parseInt(Amount.getText())));
        });

    }
}
