package com.example.homm3;

public class Heroe extends HoMM3 {
    float  offense, archery, armorer;
    int FixAtt, FixDef, lvl,ExtraArchery;
    boolean SpecCreatureFix, SpecCreatureNotFix, SpecOffense, SpecArchery, SpecDefense, IsNeutral, NativeLand;
    public Heroe (int attack, int defense, float offense, float armorer, float archery, int ExtraArchery, boolean SpecOffense, boolean SpecDefense, boolean SpecArchery, boolean SpecCreatureFix, int FixAtt, int FixDef, boolean SpecCreatureNotFix, int lvl, boolean IsNeutral, boolean NativeLand) {
        super(attack,defense);
        this.archery = archery;
        this.offense = offense;
        this.armorer = armorer;
        this.ExtraArchery = ExtraArchery;
        this.SpecOffense = SpecOffense;
        this.SpecDefense = SpecDefense;
        this.SpecArchery = SpecArchery;
        this.SpecCreatureFix = SpecCreatureFix;
        this.SpecCreatureNotFix = SpecCreatureNotFix;
        this.IsNeutral = IsNeutral;
        this.FixDef = FixDef;
        this.FixAtt = FixAtt;
        this.lvl = lvl;
        this.NativeLand = NativeLand;
    }



}
