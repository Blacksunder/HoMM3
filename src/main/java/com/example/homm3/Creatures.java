package com.example.homm3;

public class Creatures extends HoMM3 {

    int health, lvl;
    int attacks;
    int maxDamage, minDamage;
    float ignoreDefense, ignoreAttack, chanceKill, hate;
    int extraDamage;
    String Name, Hate1, Hate2;

    public Creatures(String Name, int Attack, int Defense, int health, int minDamage, int maxDamage, int attacks, float ignoreDefense, float ignoreAttack, int lvl, float chanceKill, int extraDamage, String Hate1, String Hate2, float hate) {
        super(Attack, Defense);
        this.health = health;
        this.attacks = attacks;
        this.maxDamage = maxDamage;
        this.minDamage = minDamage;
        this.ignoreDefense = ignoreDefense;
        this.ignoreAttack = ignoreAttack;
        this.lvl = lvl;
        this.chanceKill = chanceKill;
        this.extraDamage = extraDamage;
        this.hate = hate;
        this.Name = Name;
        this.Hate1 = Hate1;
        this.Hate2 = Hate2;
    }
}
