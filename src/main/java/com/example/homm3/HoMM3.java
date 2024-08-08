package com.example.homm3;

import java.util.ArrayList;
import java.util.Objects;

public class HoMM3 {

    protected int Attack, Defense;

    public HoMM3(int Attack, int Defense) {
        this.Attack = Attack;
        this.Defense = Defense;

    }


    public static String kills(Creatures creaturesHitStack, Creatures creatures, Heroe heroeAttack, Heroe heroeDefense, int amount) {
        int AtDef = 0;
        float change = 1;
        int OldHeroeAttackAttack = heroeAttack.Attack, OldHeroeAttackDefense = heroeAttack.Defense, OldHeroeAttackFixAttack = heroeAttack.FixAtt, OldHeroeAttackFixDefense = heroeAttack.FixDef, OldHeroeAttackLvl = heroeAttack.lvl, OldHeroeDefenseAttack = heroeDefense.Attack, OldHeroeDefenseDefense = heroeDefense.Defense, OldHeroeDefenseFixAttack = heroeDefense.FixAtt, OldHeroeDefenseFixDefense = heroeDefense.FixDef, OldHeroeDefenseLvl = heroeDefense.lvl;
        int OldCreaturesHitStackAttack = creaturesHitStack.Attack, OldCreaturesHitStackDefense = creaturesHitStack.Defense, OldCreaturesAttack = creatures.Attack, OldCreaturesDefense = creatures.Defense;
        float OldHeroeAttackOffense = heroeAttack.offense, OldHeroeAttackArmorer = heroeAttack.armorer, OldHeroeAttackArchery = heroeAttack.archery, OldHeroeDefenseOffense = heroeDefense.offense, OldHeroeDefenseArmorer = heroeDefense.armorer, OldHeroeDefenseArchery = heroeDefense.archery;
        int summAttack, summDef, FinalMinDamage, FinalMaxDamage, MinKills, MaxKills;
        ArrayList<Integer> kills = new ArrayList<>();
        ArrayList<Integer> damage = new ArrayList<>();
        String result;


        // Проверка на нейтральных существ (IsNeutral у героя)
        if (heroeAttack.IsNeutral) {
            heroeAttack.Attack = 0;
            heroeAttack.Defense = 0;
            heroeAttack.offense = 0;
            heroeAttack.armorer = 0;
            heroeAttack.archery = 0;
            heroeAttack.FixAtt = 0;
            heroeAttack.FixDef = 0;
            heroeAttack.lvl = 0;
        }

        if (heroeDefense.IsNeutral) {
            heroeDefense.Attack = 0;
            heroeDefense.Defense = 0;
            heroeDefense.offense = 0;
            heroeDefense.armorer = 0;
            heroeDefense.archery = 0;
            heroeDefense.FixAtt = 0;
            heroeDefense.FixDef = 0;
            heroeDefense.lvl = 0;
        }

        // Проверка на родную землю у существ (NativeLand у героя)

        if (heroeAttack.NativeLand){
            creaturesHitStack.Attack += 1;
            creaturesHitStack.Defense += 1;
        }

        if (heroeDefense.NativeLand){
            creatures.Attack += 1;
            creatures.Defense += 1;
        }

        // Расчет мин и макс урона

        int minDamage = amount * creaturesHitStack.minDamage * creaturesHitStack.attacks;
        int maxDamage = amount * creaturesHitStack.maxDamage * creaturesHitStack.attacks;

        // Изменение статов существ засчет навыков существ и героев

        if (heroeAttack.SpecCreatureFix) {
            creaturesHitStack.Attack = creaturesHitStack.Attack + heroeAttack.FixAtt;
            creaturesHitStack.Defense = creaturesHitStack.Defense + heroeAttack.FixDef;
        } else if (heroeAttack.SpecCreatureNotFix) {
            creaturesHitStack.Attack = (int) (Math.ceil(creaturesHitStack.Attack * (1 + ((double) heroeAttack.lvl / creaturesHitStack.lvl) * 0.05)));
            creaturesHitStack.Defense = (int) (Math.ceil(creaturesHitStack.Defense * (1 + ((double) heroeAttack.lvl / creaturesHitStack.lvl) * 0.05)));
        }

        if (heroeDefense.SpecCreatureFix) {
            creatures.Attack = creatures.Attack + heroeDefense.FixAtt;
            creatures.Defense = creatures.Defense + heroeDefense.FixDef;
        } else if (heroeDefense.SpecCreatureNotFix) {
            creatures.Attack = (int) (Math.ceil(creatures.Attack * (1 + ((double) heroeDefense.lvl / creatures.lvl) * 0.05)));
            creatures.Defense = (int) (Math.ceil(creatures.Defense * (1 + ((double) heroeDefense.lvl / creatures.lvl) * 0.05)));
        }


        summAttack = creaturesHitStack.Attack + heroeAttack.Attack;
        summDef = creatures.Defense + heroeDefense.Defense;

        if (creaturesHitStack.ignoreDefense > 0) {
            summDef = (int) ((summDef - 1) * (1 - creaturesHitStack.ignoreDefense));
        }
        if (creatures.ignoreAttack > 0) {
            summAttack = (int)((summAttack - 1) * (1 - creatures.ignoreAttack));
        }

        // Сравнение итоговых показателей Атаки и Защиты и расчет изменения урона

        if (summAttack > summDef) {
            AtDef = summAttack - summDef;
            change = 1 + ((float) AtDef * 5) / 100;
            if (change > 4) {
                change = 4;
            }
        } else if (summAttack < summDef) {
            AtDef = summDef - summAttack;
            change = 1 - ((float) AtDef * 2.5f) / 100;
            if (change < 0.3) {
                change = 0.3f;
            }
        }

        // Расчет урона дальнобойных отрядов
        if (creaturesHitStack instanceof ArcheryCreatures) {

            if (heroeAttack.archery != 0) {
                heroeAttack.archery += (float)heroeAttack.ExtraArchery/100;
                if (heroeAttack.SpecArchery) {

                    FinalMinDamage = (int) ((minDamage * change + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)))/2);
                    if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                        FinalMinDamage = (int) ((minDamage * change + minDamage * (creaturesHitStack.hate - 1) + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)))/2);
                    }
                    if (change < 1) {
                        FinalMinDamage = (int) ((minDamage * change + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)))/2);
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int) ((minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)))/2);
                        }
                    }

                    if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMinDamage = (int) (((minDamage * change + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int) (((minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                            }
                            if (change < 1) {
                                FinalMinDamage = (int) (((minDamage * change + (minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMinDamage = (int) (((minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                                }
                            }
                        } else {
                            FinalMinDamage = (int) (((minDamage * change + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer))/2);
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int) (((minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer))/2);
                            }
                            if (change < 1) {
                                FinalMinDamage = (int) (((minDamage * change + (minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)))) * (1 - heroeDefense.armorer))/2);
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMinDamage = (int) (((minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer))/2);
                                }
                            }
                        }
                    }

                    damage.add(FinalMinDamage);

                    MinKills = (int) (FinalMinDamage / creatures.health) + (int) (amount * creaturesHitStack.chanceKill);
                    kills.add(MinKills);

                    FinalMaxDamage = (int) ((maxDamage * change + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)))/2);
                    if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                        FinalMaxDamage = (int) ((maxDamage * change + maxDamage * (creaturesHitStack.hate - 1) + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)))/2);
                    }
                    if (change < 1) {
                        FinalMaxDamage = (int) ((maxDamage * change + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)))/2);
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)))/2);
                        }
                    }

                    if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMaxDamage = (int) (((maxDamage * change + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int) (((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                            }
                            if (change < 1) {
                                FinalMaxDamage = (int) (((maxDamage * change + (maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMaxDamage = (int) (((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                                }
                            }
                        } else {
                            FinalMaxDamage = (int) (((maxDamage * change + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer))/2);
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int) (((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer))/2);
                            }
                            if (change < 1) {
                                FinalMaxDamage = (int) (((maxDamage * change + (maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)))) * (1 - heroeDefense.armorer))/2);
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMaxDamage = (int) (((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer))/2);
                                }
                            }
                        }
                    }

                    damage.add(FinalMaxDamage);

                    MaxKills = (int) ((int) (FinalMaxDamage / creatures.health) + (int) (amount * creaturesHitStack.chanceKill));
                    kills.add(MaxKills);


                    FinalMinDamage = (int) (minDamage * change + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)));
                    if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                        FinalMinDamage = (int) (minDamage * change + minDamage * (creaturesHitStack.hate - 1) + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)));
                    }
                    if (change < 1) {
                        FinalMinDamage = (int) (minDamage * change + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int) (minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)));
                        }
                    }

                    if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMinDamage = (int) ((minDamage * change + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int) ((minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                            }
                            if (change < 1) {
                                FinalMinDamage = (int) ((minDamage * change + (minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMinDamage = (int) ((minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                                }
                            }
                        } else {
                            FinalMinDamage = (int) ((minDamage * change + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int) ((minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer));
                            }
                            if (change < 1) {
                                FinalMinDamage = (int) ((minDamage * change + (minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)))) * (1 - heroeDefense.armorer));
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMinDamage = (int) ((minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer));
                                }
                            }
                        }
                    }

                    damage.add(FinalMinDamage);

                    MinKills = (int) (FinalMinDamage / creatures.health) + (int) (amount * creaturesHitStack.chanceKill);
                    kills.add(MinKills);

                    FinalMaxDamage = (int) (maxDamage * change + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)));
                    if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                        FinalMaxDamage = (int) (maxDamage * change + maxDamage * (creaturesHitStack.hate - 1) + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)));
                    }
                    if (change < 1) {
                        FinalMaxDamage = (int) (maxDamage * change + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int) (maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)));
                        }
                    }

                    if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMaxDamage = (int) ((maxDamage * change + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                            }
                            if (change < 1) {
                                FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                                }
                            }
                        } else {
                            FinalMaxDamage = (int) ((maxDamage * change + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer));
                            }
                            if (change < 1) {
                                FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl)))) * (1 - heroeDefense.armorer));
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (heroeAttack.archery * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer));
                                }
                            }
                        }
                    }

                    damage.add(FinalMaxDamage);

                    MaxKills = (int) ((int) (FinalMaxDamage / creatures.health) + (int) (amount * creaturesHitStack.chanceKill));
                    kills.add(MaxKills);

                } else {
                    FinalMinDamage = (int) ((minDamage * change + minDamage * (heroeAttack.archery))/2);
                    if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                        FinalMinDamage = (int) ((minDamage * change + minDamage * (creaturesHitStack.hate - 1) + minDamage * (heroeAttack.archery))/2);
                    }
                    if (change < 1) {
                        FinalMinDamage = (int) ((minDamage * change + minDamage * (heroeAttack.archery))/2);
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int) ((minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery))/2);
                        }
                    }

                    if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMinDamage = (int) (((minDamage * change + minDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int) (((minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                            }
                            if (change < 1) {
                                FinalMinDamage = (int) (((minDamage * change + (minDamage * (heroeAttack.archery))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMinDamage = (int) (((minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                                }
                            }
                        } else {
                            FinalMinDamage = (int) (((minDamage * change + minDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer))/2);
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int) (((minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer))/2);
                            }
                            if (change < 1) {
                                FinalMinDamage = (int) (((minDamage * change + (minDamage * (heroeAttack.archery))) * (1 - heroeDefense.armorer))/2);
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMinDamage = (int) (((minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer))/2);
                                }
                            }
                        }
                    }

                    damage.add(FinalMinDamage);

                    MinKills = (int) (FinalMinDamage / creatures.health) + (int) (amount * creaturesHitStack.chanceKill);
                    kills.add(MinKills);

                    FinalMaxDamage = (int) ((maxDamage * change + maxDamage * (heroeAttack.archery))/2);
                    if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                        FinalMaxDamage = (int) ((maxDamage * change + maxDamage * (creaturesHitStack.hate - 1) + maxDamage * (heroeAttack.archery))/2);
                    }
                    if (change < 1) {
                        FinalMaxDamage = (int) ((maxDamage * change + maxDamage * (heroeAttack.archery))/2);
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (heroeAttack.archery))/2);
                        }
                    }

                    if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMaxDamage = (int) (((maxDamage * change + maxDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int) (((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                            }
                            if (change < 1) {
                                FinalMaxDamage = (int) (((maxDamage * change + (maxDamage * (heroeAttack.archery))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMaxDamage = (int) (((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                                }
                            }
                        } else {
                            FinalMaxDamage = (int) (((maxDamage * change + maxDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer))/2);
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int) (((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer))/2);
                            }
                            if (change < 1) {
                                FinalMaxDamage = (int) (((maxDamage * change + (maxDamage * (heroeAttack.archery))) * (1 - heroeDefense.armorer))/2);
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMaxDamage = (int) (((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer))/2);
                                }
                            }
                        }
                    }

                    damage.add(FinalMaxDamage);

                    MaxKills = (int) ((int) (FinalMaxDamage / creatures.health) + (int) (amount * creaturesHitStack.chanceKill));
                    kills.add(MaxKills);


                    FinalMinDamage = (int) (minDamage * change + minDamage * (heroeAttack.archery));
                    if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                        FinalMinDamage = (int) (minDamage * change + minDamage * (creaturesHitStack.hate - 1) + minDamage * (heroeAttack.archery));
                    }
                    if (change < 1) {
                        FinalMinDamage = (int) (minDamage * change + minDamage * (heroeAttack.archery));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int) (minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery));
                        }
                    }

                    if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMinDamage = (int) ((minDamage * change + minDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int) ((minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                            }
                            if (change < 1) {
                                FinalMinDamage = (int) ((minDamage * change + (minDamage * (heroeAttack.archery))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMinDamage = (int) ((minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                                }
                            }
                        } else {
                            FinalMinDamage = (int) ((minDamage * change + minDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int) ((minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer));
                            }
                            if (change < 1) {
                                FinalMinDamage = (int) ((minDamage * change + (minDamage * (heroeAttack.archery))) * (1 - heroeDefense.armorer));
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMinDamage = (int) ((minDamage * change + (minDamage * (creaturesHitStack.hate - 1)) + minDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer));
                                }
                            }
                        }
                    }

                    damage.add(FinalMinDamage);

                    MinKills = (int) (FinalMinDamage / creatures.health) + (int) (amount * creaturesHitStack.chanceKill);
                    kills.add(MinKills);

                    FinalMaxDamage = (int) (maxDamage * change + maxDamage * (heroeAttack.archery));
                    if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                        FinalMaxDamage = (int) (maxDamage * change + maxDamage * (creaturesHitStack.hate - 1) + maxDamage * (heroeAttack.archery));
                    }
                    if (change < 1) {
                        FinalMaxDamage = (int) (maxDamage * change + maxDamage * (heroeAttack.archery));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int) (maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (2 - change) * (heroeAttack.archery));
                        }
                    }

                    if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMaxDamage = (int) ((maxDamage * change + maxDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                            }
                            if (change < 1) {
                                FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (heroeAttack.archery))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                                }
                            }
                        } else {
                            FinalMaxDamage = (int) ((maxDamage * change + maxDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer));
                            }
                            if (change < 1) {
                                FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (heroeAttack.archery))) * (1 - heroeDefense.armorer));
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)) + maxDamage * (heroeAttack.archery)) * (1 - heroeDefense.armorer));
                                }
                            }
                        }
                    }

                    damage.add(FinalMaxDamage);

                    MaxKills = (int) ((int) (FinalMaxDamage / creatures.health) + (int) (amount * creaturesHitStack.chanceKill));
                    kills.add(MaxKills);
                }
                heroeAttack.archery -= (float)heroeAttack.ExtraArchery/100;
            } else {
                    FinalMinDamage = (int) ((minDamage * change)/2);
                    if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                        FinalMinDamage = (int) ((minDamage * change + minDamage * (creaturesHitStack.hate - 1))/2);
                    }
                    if (change < 1) {
                        FinalMinDamage = (int) ((minDamage * change)/2);
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int) ((minDamage * change + (minDamage * (creaturesHitStack.hate - 1)))/2);
                        }
                    }

                    if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMinDamage = (int) (((minDamage * change) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int) (((minDamage * change + (minDamage * (creaturesHitStack.hate - 1))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                            }
                            if (change < 1) {
                                FinalMinDamage = (int) (((minDamage * change)) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl))/2);
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMinDamage = (int) (((minDamage * change + (minDamage * (creaturesHitStack.hate - 1))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                                }
                            }
                        } else {
                            FinalMinDamage = (int) (((minDamage * change) * (1 - heroeDefense.armorer))/2);
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int) (((minDamage * change + (minDamage * (creaturesHitStack.hate - 1))) * (1 - heroeDefense.armorer))/2);
                            }
                            if (change < 1) {
                                FinalMinDamage = (int) (((minDamage * change)) * (1 - heroeDefense.armorer)/2);
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMinDamage = (int) (((minDamage * change + (minDamage * (creaturesHitStack.hate - 1))) * (1 - heroeDefense.armorer))/2);
                                }
                            }
                        }
                    }

                    damage.add(FinalMinDamage);

                    MinKills = (int) (FinalMinDamage / creatures.health) + (int) (amount * creaturesHitStack.chanceKill);
                    kills.add(MinKills);

                    FinalMaxDamage = (int) ((maxDamage * change)/2);
                    if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                        FinalMaxDamage = (int) ((maxDamage * change + maxDamage * (creaturesHitStack.hate - 1))/2);
                    }
                    if (change < 1) {
                        FinalMaxDamage = (int) ((maxDamage * change)/2);
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)))/2);
                        }
                    }

                    if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMaxDamage = (int) (((maxDamage * change) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int) (((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                            }
                            if (change < 1) {
                                FinalMaxDamage = (int) (((maxDamage * change)) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl))/2);
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMaxDamage = (int) (((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)))/2);
                                }
                            }
                        } else {
                            FinalMaxDamage = (int) (((maxDamage * change) * (1 - heroeDefense.armorer))/2);
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int) (((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1))) * (1 - heroeDefense.armorer))/2);
                            }
                            if (change < 1) {
                                FinalMaxDamage = (int) (((maxDamage * change) * (1 - heroeDefense.armorer))/2);
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMaxDamage = (int) (((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1))) * (1 - heroeDefense.armorer))/2);
                                }
                            }
                        }
                    }

                    damage.add(FinalMaxDamage);

                    MaxKills = (int) ((int) (FinalMaxDamage / creatures.health) + (int) (amount * creaturesHitStack.chanceKill));
                    kills.add(MaxKills);


                    FinalMinDamage = (int) (minDamage * change + minDamage * (heroeAttack.archery));
                    if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                        FinalMinDamage = (int) (minDamage * change + minDamage * (creaturesHitStack.hate - 1));
                    }
                    if (change < 1) {
                        FinalMinDamage = (int) (minDamage * change);
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int) (minDamage * change + (minDamage * (creaturesHitStack.hate - 1)));
                        }
                    }

                    if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMinDamage = (int) ((minDamage * change) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int) ((minDamage * change + (minDamage * (creaturesHitStack.hate - 1))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                            }
                            if (change < 1) {
                                FinalMinDamage = (int) ((minDamage * change) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMinDamage = (int) ((minDamage * change + (minDamage * (creaturesHitStack.hate - 1))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                                }
                            }
                        } else {
                            FinalMinDamage = (int) ((minDamage * change) * (1 - heroeDefense.armorer));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int) ((minDamage * change + (minDamage * (creaturesHitStack.hate - 1))) * (1 - heroeDefense.armorer));
                            }
                            if (change < 1) {
                                FinalMinDamage = (int) ((minDamage * change) * (1 - heroeDefense.armorer));
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMinDamage = (int) ((minDamage * change + (minDamage * (creaturesHitStack.hate - 1))) * (1 - heroeDefense.armorer));
                                }
                            }
                        }
                    }

                    damage.add(FinalMinDamage);

                    MinKills = (int) (FinalMinDamage / creatures.health) + (int) (amount * creaturesHitStack.chanceKill);
                    kills.add(MinKills);

                    FinalMaxDamage = (int) (maxDamage * change);
                    if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                        FinalMaxDamage = (int) (maxDamage * change + maxDamage * (creaturesHitStack.hate - 1));
                    }
                    if (change < 1) {
                        FinalMaxDamage = (int) (maxDamage * change);
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int) (maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1)));
                        }
                    }

                    if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMaxDamage = (int) ((maxDamage * change) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                            }
                            if (change < 1) {
                                FinalMaxDamage = (int) ((maxDamage * change) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1))) * (1 - heroeDefense.armorer * (1 + 0.05 * heroeDefense.lvl)));
                                }
                            }
                        } else {
                            FinalMaxDamage = (int) ((maxDamage * change) * (1 - heroeDefense.armorer));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1))) * (1 - heroeDefense.armorer));
                            }
                            if (change < 1) {
                                FinalMaxDamage = (int) ((maxDamage * change) * (1 - heroeDefense.armorer));
                                if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                    FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (creaturesHitStack.hate - 1))) * (1 - heroeDefense.armorer));
                                }
                            }
                        }
                    }

                    damage.add(FinalMaxDamage);

                    MaxKills = (int) ((int) (FinalMaxDamage / creatures.health) + (int) (amount * creaturesHitStack.chanceKill));
                    kills.add(MaxKills);
                }

            // Урон не может быть меньше 1
            for (int i = 0; i < damage.size(); i++) {
                if (damage.get(i)<1){
                    damage.set(i,1);
                }
            }

            // Вывод итогового урона и кол-ва убийств
            result = "Со сломанной стрелы: ";
            //System.out.println("Со сломанной стрелы: ");
            if (damage.get(0)!=damage.get(1)) {
               // System.out.println("Урон: " + damage.get(0) + " - " + damage.get(1));
                result += "\nУрон: " + damage.get(0) + " - " + damage.get(1);
            }
            else {
               // System.out.println("Урон: " + damage.get(0));
                result += "\nУрон: " + damage.get(0);
            }
            if (kills.get(0) != kills.get(1)) {
               // System.out.println("Будет убито: "+kills.get(0) + " - " + kills.get(1) + " существ");
                result += "\nБудет убито: "+kills.get(0) + " - " + kills.get(1) + " существ";
            } else {
              //  System.out.println("Будет убито: "+kills.get(0) + " существ");
                result += "\nБудет убито: "+kills.get(0) + " существ";
            }

            //System.out.println("\nС прямой стрелы: ");
            result += "\n\nС прямой стрелы: ";
            if (damage.get(2)!=damage.get(3)) {
                //System.out.println("Урон: " + damage.get(2) + " - " + damage.get(3));
                result += "\nУрон: " + damage.get(2) + " - " + damage.get(3);
            }
            else {
                //System.out.println("Урон: " + damage.get(2));
                result += "\nУрон: " + damage.get(2);
            }
            if (kills.get(2) != kills.get(3)) {
                //System.out.println("Будет убито: "+kills.get(2) + " - " + kills.get(3) + " существ");
                result += "\nБудет убито: "+kills.get(2) + " - " + kills.get(3) + " существ";
            } else {
                //System.out.println("Будет убито: "+kills.get(2) + " существ");
                result += "\nБудет убито: "+kills.get(2) + " существ";
            }
            if (creaturesHitStack.chanceKill != 0) {
                result += "\n\nДоп убийство с шансом " + (int)Math.ceil(((amount * creaturesHitStack.chanceKill)%1)*100) + "%";
            }
        }


        // Расчет урона юнитов ближнего боя
        else {
            if (creaturesHitStack instanceof OffenseCreatures) {
                if (heroeAttack.offense != 0) {
                    if (heroeAttack.SpecOffense) {
                        FinalMinDamage = (int) ((int)(minDamage * change) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int)((int)(minDamage * change) + (int)(minDamage*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((int)(minDamage * change) + (int)((minDamage) * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int)((int)(minDamage * change) + (int)((minDamage)*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)((minDamage) * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int)(((int)(minDamage * change) + (int)((minDamage)*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1 - heroeDefense.armorer));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)((minDamage) * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int)(((int)(minDamage * change) + (int)((minDamage)*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMinDamage);

                        MinKills = (int)(FinalMinDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill);
                        kills.add(MinKills);

                        FinalMaxDamage = (int) ((int)(maxDamage * change) + (int)(maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int)((int)(maxDamage * change) + (int)(maxDamage*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((int)(maxDamage * change) + (int)((maxDamage) * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int)((int)(maxDamage * change) + (int)((maxDamage)*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)((maxDamage) * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int)(((int)(maxDamage * change) + (int)((maxDamage)*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1 - heroeDefense.armorer));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)((maxDamage) * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int)(((int)(maxDamage * change) + (int)((maxDamage)*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMaxDamage);

                        MaxKills = (int) ((int)(FinalMaxDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill));
                        kills.add(MaxKills);

                    } else {
                        FinalMinDamage = (int) ((int)(minDamage * change) + (int)(minDamage * (heroeAttack.offense)));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int)((int)(minDamage * change) + (int)(minDamage*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense)));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((int)(minDamage) + (int)((minDamage) * (heroeAttack.offense)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int)((int)(minDamage) + (int)((minDamage)*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense)));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)((minDamage) * (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int)(((int)(minDamage * change) + (int)((minDamage)*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage * (heroeAttack.offense))) * (1 - heroeDefense.armorer));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)((minDamage) * (heroeAttack.offense))) * (1  - heroeDefense.armorer));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int)(((int)(minDamage * change) + (int)((minDamage)*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMinDamage);

                        MinKills = (int)(FinalMinDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill);
                        kills.add(MinKills);

                        FinalMaxDamage = (int) ((int)(maxDamage * change) + (int)(maxDamage * (heroeAttack.offense)));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int)((int)(maxDamage * change) + (int)(maxDamage*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense)));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((int)(maxDamage * change) + (int)((maxDamage) * (heroeAttack.offense)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int)((int)(maxDamage * change) + (int)((maxDamage)*(creaturesHitStack.hate - 1)) + (int)(maxDamage*change * (heroeAttack.offense)));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage*(creaturesHitStack.hate - 1)) + (int)(maxDamage* (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)((maxDamage) * (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int)(((int)(maxDamage * change) + (int)((maxDamage)*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage * (heroeAttack.offense))) * (1 - heroeDefense.armorer));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)((maxDamage) * (heroeAttack.offense))) * (1  - heroeDefense.armorer));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int)(((int)(maxDamage * change) + (int)((maxDamage)*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMaxDamage);

                        MaxKills = (int) ((int)(FinalMaxDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill));
                        kills.add(MaxKills);

                    }
                } else {
                        FinalMinDamage = (int) (minDamage * change);
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int)((int)(minDamage * change) + (int)(minDamage*(creaturesHitStack.hate - 1)));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) (minDamage * change);
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int)((int)(minDamage * change) + (int)((int)(minDamage)*(creaturesHitStack.hate - 1)));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMinDamage = (int) ((int)(minDamage * change) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage*(creaturesHitStack.hate - 1))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((minDamage * change) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int)(((int)(minDamage * change) + (int)((int)(minDamage)*(creaturesHitStack.hate - 1))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMinDamage = (int) ((int)(minDamage * change) * (1 - heroeDefense.armorer));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage*(creaturesHitStack.hate - 1))) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((int)(minDamage) * (1  - heroeDefense.armorer));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int)(((int)(minDamage * change) + (int)((int)(minDamage)*(creaturesHitStack.hate - 1))) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMinDamage);

                        MinKills = (int)(FinalMinDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill);
                        kills.add(MinKills);

                        FinalMaxDamage = (int) (maxDamage * change);
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int)((int)(maxDamage * change) + (int)(maxDamage*(creaturesHitStack.hate - 1)));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) (maxDamage * change);
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int)((int)(maxDamage * change) + (int)((int)(maxDamage)*(creaturesHitStack.hate - 1)));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMaxDamage = (int) ((int)(maxDamage * change) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage*(creaturesHitStack.hate - 1))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((int)(maxDamage * change) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int)(((int)(maxDamage * change) + (int)((int)(maxDamage)*(creaturesHitStack.hate - 1))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMaxDamage = (int) ((int)(maxDamage * change) * (1 - heroeDefense.armorer));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage*(creaturesHitStack.hate - 1))) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((int)(maxDamage * change) * (1  - heroeDefense.armorer));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int)(((int)(maxDamage * change) + (int)((int)(maxDamage)*(creaturesHitStack.hate - 1))) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMaxDamage);

                        MaxKills = (int) ((int)(FinalMaxDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill));
                        kills.add(MaxKills);

                    }
            }

            // Расчет урона в случае атаки Психическими Элементалями (-50% урона по сущ-вам с иммунитетом к магии разума)

            else if (creaturesHitStack instanceof PsychicElemental) {
                if (heroeAttack.offense != 0) {
                    if (heroeAttack.SpecOffense) {
                        FinalMinDamage = (int) ((int)(minDamage * change) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                        if (creatures.PsychicImmune) {
                            FinalMinDamage = (int)((int)(minDamage * change) - (int)(minDamage /2) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((int)(minDamage * change) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                            if (creatures.PsychicImmune) {
                                FinalMinDamage = (int)((int)(minDamage * change) - (int)((minDamage)/2) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMinDamage = (int) ((minDamage * change + minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creatures.PsychicImmune) {
                            FinalMinDamage = (int) ((minDamage * change - (double) minDamage /2 + minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((minDamage * change + (minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creatures.PsychicImmune) {
                                FinalMinDamage = (int)((minDamage * change - ((double) minDamage /2) + minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMinDamage = (int) ((minDamage * change + minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer));
                        if (creatures.PsychicImmune) {
                            FinalMinDamage = (int) ((minDamage * change - (double) minDamage /2 + minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((minDamage * change + (minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer));
                            if (creatures.PsychicImmune) {
                                FinalMinDamage = (int)((minDamage * change - ((double) minDamage /2) + minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMinDamage);

                        MinKills = (int)(FinalMinDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill);
                        kills.add(MinKills);

                        FinalMaxDamage = (int) (maxDamage * change + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)));
                        if (creatures.PsychicImmune) {
                            FinalMaxDamage = (int)(maxDamage * change - (double) maxDamage /2 + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) (maxDamage * change + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)));
                            if (creatures.PsychicImmune) {
                                FinalMaxDamage = (int)(maxDamage * change - ((double) maxDamage /2) + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMaxDamage = (int) ((maxDamage * change + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creatures.PsychicImmune) {
                            FinalMaxDamage = (int) ((maxDamage * change - (double) maxDamage /2 + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creatures.PsychicImmune) {
                                FinalMaxDamage = (int)((maxDamage * change - ((double) maxDamage /2) + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMaxDamage = (int) ((maxDamage * change + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer));
                        if (creatures.PsychicImmune) {
                            FinalMaxDamage = (int) ((maxDamage * change - (double) maxDamage /2 + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer));
                            if (creatures.PsychicImmune) {
                                FinalMaxDamage = (int)((maxDamage * change - ((double) maxDamage /2) + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMaxDamage);

                        MaxKills = (int) ((int)(FinalMaxDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill));
                        kills.add(MaxKills);

                    } else {
                        FinalMinDamage = (int) (minDamage * change + minDamage * (heroeAttack.offense));
                        if (creatures.PsychicImmune) {
                            FinalMinDamage = (int)(minDamage * change - (float) minDamage /2 + minDamage * (heroeAttack.offense));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) (minDamage * change + minDamage * (heroeAttack.offense));
                            if (creatures.PsychicImmune) {
                                FinalMinDamage = (int)(minDamage * change - ((float) minDamage /2) + minDamage * (heroeAttack.offense));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMinDamage = (int) ((minDamage * change + minDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creatures.PsychicImmune) {
                            FinalMinDamage = (int) ((minDamage * change - (double) minDamage /2 + minDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((minDamage * change + (minDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creatures.PsychicImmune) {
                                FinalMinDamage = (int)((minDamage * change - (minDamage*(1-creaturesHitStack.hate)) + minDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMinDamage = (int) ((minDamage * change + minDamage * (heroeAttack.offense)) * (1 - heroeDefense.armorer));
                        if (creatures.PsychicImmune) {
                            FinalMinDamage = (int) ((minDamage * change - (float) minDamage /2 + minDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((minDamage * change + (minDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer));
                            if (creatures.PsychicImmune) {
                                FinalMinDamage = (int)((minDamage * change - ((float) minDamage /2) + minDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMinDamage);

                        MinKills = (int)(FinalMinDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill);
                        kills.add(MinKills);

                        FinalMaxDamage = (int) (maxDamage * change + maxDamage * (heroeAttack.offense));
                        if (creatures.PsychicImmune) {
                            FinalMaxDamage = (int)(maxDamage * change - (float) maxDamage /2 + maxDamage * (heroeAttack.offense));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) (maxDamage * change + maxDamage * (heroeAttack.offense));
                            if (creatures.PsychicImmune) {
                                FinalMaxDamage = (int)(maxDamage * change - ((float) maxDamage /2) + maxDamage * (heroeAttack.offense));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMaxDamage = (int) ((maxDamage * change + maxDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creatures.PsychicImmune) {
                            FinalMaxDamage = (int) ((maxDamage * change - (double) maxDamage /2 + maxDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creatures.PsychicImmune) {
                                FinalMaxDamage = (int)((maxDamage * change - ((double) maxDamage /2) + maxDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMaxDamage = (int) ((maxDamage * change + maxDamage * (heroeAttack.offense)) * (1 - heroeDefense.armorer));
                        if (creatures.PsychicImmune) {
                            FinalMaxDamage = (int) ((maxDamage * change - (float) maxDamage /2 + maxDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer));
                            if (creatures.PsychicImmune) {
                                FinalMaxDamage = (int)((maxDamage * change - ((float) maxDamage /2) + maxDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMaxDamage);

                        MaxKills = (int) ((int)(FinalMaxDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill));
                        kills.add(MaxKills);

                    }
                } else {
                        FinalMinDamage = (int) (minDamage * change);
                        if (creatures.PsychicImmune) {
                            FinalMinDamage = (int)(minDamage * change - (float) minDamage /2);
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) (minDamage * change);
                            if (creatures.PsychicImmune) {
                                FinalMinDamage = (int)(minDamage * change - ((float) minDamage /2));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMinDamage = (int) ((minDamage * change) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creatures.PsychicImmune) {
                            FinalMinDamage = (int) ((minDamage * change - ((double) minDamage /2)) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((minDamage * change) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creatures.PsychicImmune) {
                                FinalMinDamage = (int)((minDamage * change - (double) minDamage /2) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMinDamage = (int) ((minDamage * change) * (1 - heroeDefense.armorer));
                        if (creatures.PsychicImmune) {
                            FinalMinDamage = (int) ((minDamage * change - (float) minDamage /2) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((minDamage * change) * (1  - heroeDefense.armorer));
                            if (creatures.PsychicImmune) {
                                FinalMinDamage = (int)((minDamage * change - ((float) minDamage /2)) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMinDamage);

                        MinKills = (int)(FinalMinDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill);
                        kills.add(MinKills);

                        FinalMaxDamage = (int) (maxDamage * change);
                        if (creatures.PsychicImmune) {
                            FinalMaxDamage = (int)(maxDamage * change - (float) maxDamage /2);
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) (maxDamage * change);
                            if (creatures.PsychicImmune) {
                                FinalMaxDamage = (int)(maxDamage * change - ((float) maxDamage /2));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMaxDamage = (int) ((maxDamage * change) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creatures.PsychicImmune) {
                            FinalMaxDamage = (int) ((maxDamage * change - (double) maxDamage /2) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((maxDamage * change) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creatures.PsychicImmune) {
                                FinalMaxDamage = (int)((maxDamage * change - ((double) maxDamage /2)) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMaxDamage = (int) ((maxDamage * change) * (1 - heroeDefense.armorer));
                        if (creatures.PsychicImmune) {
                            FinalMaxDamage = (int) ((maxDamage * change - (float) maxDamage /2) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((maxDamage * change) * (1  - heroeDefense.armorer));
                            if (creatures.PsychicImmune) {
                                FinalMaxDamage = (int)((maxDamage * change - ((float) maxDamage /2)) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMaxDamage);

                        MaxKills = (int) ((int)(FinalMaxDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill));
                        kills.add(MaxKills);

                    }
            }

            // Расчет урона в случае атаки Элементалями Магии (-50% урона по сущ-вам с иммунитетом к магии)
            else if (creaturesHitStack instanceof MagicElemental) {
                if (heroeAttack.offense != 0) {
                    if (heroeAttack.SpecOffense) {
                        FinalMinDamage = (int) ((int)(minDamage * change) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                        if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                            FinalMinDamage = (int)((int)(minDamage * change) - (int)(minDamage /2) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((int)(minDamage * change) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                            if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                                FinalMinDamage = (int)((int)(minDamage * change) - (int)((minDamage)/2) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMinDamage = (int) ((minDamage * change + minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                            FinalMinDamage = (int) ((minDamage * change - (double) minDamage /2 + minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((minDamage * change + (minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                                FinalMinDamage = (int)((minDamage * change - ((double) minDamage /2) + minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMinDamage = (int) ((minDamage * change + minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer));
                        if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                            FinalMinDamage = (int) ((minDamage * change - (double) minDamage /2 + minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((minDamage * change + (minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer));
                            if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                                FinalMinDamage = (int)((minDamage * change - ((double) minDamage /2) + minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMinDamage);

                        MinKills = (int)(FinalMinDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill);
                        kills.add(MinKills);

                        FinalMaxDamage = (int) (maxDamage * change + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)));
                        if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                            FinalMaxDamage = (int)(maxDamage * change - (double) maxDamage /2 + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) (maxDamage * change + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)));
                            if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                                FinalMaxDamage = (int)(maxDamage * change - ((double) maxDamage /2) + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMaxDamage = (int) ((maxDamage * change + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                            FinalMaxDamage = (int) ((maxDamage * change - (double) maxDamage /2 + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                                FinalMaxDamage = (int)((maxDamage * change - ((double) maxDamage /2) + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMaxDamage = (int) ((maxDamage * change + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1 - heroeDefense.armorer));
                        if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                            FinalMaxDamage = (int) ((maxDamage * change - (double) maxDamage /2 + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer));
                            if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                                FinalMaxDamage = (int)((maxDamage * change - ((double) maxDamage /2) + maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMaxDamage);

                        MaxKills = (int) ((int)(FinalMaxDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill));
                        kills.add(MaxKills);

                    } else {
                        FinalMinDamage = (int) (minDamage * change + minDamage * (heroeAttack.offense));
                        if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                            FinalMinDamage = (int)(minDamage * change - (float) minDamage /2 + minDamage * (heroeAttack.offense));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) (minDamage * change + minDamage * (heroeAttack.offense));
                            if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                                FinalMinDamage = (int)(minDamage * change - ((float) minDamage /2) + minDamage * (heroeAttack.offense));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMinDamage = (int) ((minDamage * change + minDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                            FinalMinDamage = (int) ((minDamage * change - (double) minDamage /2 + minDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((minDamage * change + (minDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                                FinalMinDamage = (int)((minDamage * change - (minDamage*(1-creaturesHitStack.hate)) + minDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMinDamage = (int) ((minDamage * change + minDamage * (heroeAttack.offense)) * (1 - heroeDefense.armorer));
                        if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                            FinalMinDamage = (int) ((minDamage * change - (float) minDamage /2 + minDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((minDamage * change + (minDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer));
                            if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                                FinalMinDamage = (int)((minDamage * change - ((float) minDamage /2) + minDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMinDamage);

                        MinKills = (int)(FinalMinDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill);
                        kills.add(MinKills);

                        FinalMaxDamage = (int) (maxDamage * change + maxDamage * (heroeAttack.offense));
                        if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                            FinalMaxDamage = (int)(maxDamage * change - (float) maxDamage /2 + maxDamage * (heroeAttack.offense));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) (maxDamage * change + maxDamage * (heroeAttack.offense));
                            if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                                FinalMaxDamage = (int)(maxDamage * change - ((float) maxDamage /2) + maxDamage * (heroeAttack.offense));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMaxDamage = (int) ((maxDamage * change + maxDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                            FinalMaxDamage = (int) ((maxDamage * change - (double) maxDamage /2 + maxDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                                FinalMaxDamage = (int)((maxDamage * change - ((double) maxDamage /2) + maxDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMaxDamage = (int) ((maxDamage * change + maxDamage * (heroeAttack.offense)) * (1 - heroeDefense.armorer));
                        if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                            FinalMaxDamage = (int) ((maxDamage * change - (float) maxDamage /2 + maxDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((maxDamage * change + (maxDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer));
                            if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                                FinalMaxDamage = (int)((maxDamage * change - ((float) maxDamage /2) + maxDamage * (heroeAttack.offense)) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMaxDamage);

                        MaxKills = (int) ((int)(FinalMaxDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill));
                        kills.add(MaxKills);

                    }
                } else {
                        FinalMinDamage = (int) (minDamage * change);
                        if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                            FinalMinDamage = (int)(minDamage * change - (float) minDamage /2);
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) (minDamage * change);
                            if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                                FinalMinDamage = (int)(minDamage * change - ((float) minDamage /2));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMinDamage = (int) ((minDamage * change) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                            FinalMinDamage = (int) ((minDamage * change - ((double) minDamage /2)) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((minDamage * change) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                                FinalMinDamage = (int)((minDamage * change - (double) minDamage /2) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMinDamage = (int) ((minDamage * change) * (1 - heroeDefense.armorer));
                        if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                            FinalMinDamage = (int) ((minDamage * change - (float) minDamage /2) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((minDamage * change) * (1  - heroeDefense.armorer));
                            if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                                FinalMinDamage = (int)((minDamage * change - ((float) minDamage /2)) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMinDamage);

                        MinKills = (int)(FinalMinDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill);
                        kills.add(MinKills);

                        FinalMaxDamage = (int) (maxDamage * change);
                        if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                            FinalMaxDamage = (int)(maxDamage * change - (float) maxDamage /2);
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) (maxDamage * change);
                            if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                                FinalMaxDamage = (int)(maxDamage * change - ((float) maxDamage /2));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMaxDamage = (int) ((maxDamage * change) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                            FinalMaxDamage = (int) ((maxDamage * change - (double) maxDamage /2) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((maxDamage * change) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                                FinalMaxDamage = (int)((maxDamage * change - ((double) maxDamage /2)) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMaxDamage = (int) ((maxDamage * change) * (1 - heroeDefense.armorer));
                        if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                            FinalMaxDamage = (int) ((maxDamage * change - (float) maxDamage /2) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((maxDamage * change) * (1  - heroeDefense.armorer));
                            if (creatures.Name.equals("BlackDragon") || creatures.Name.equals("MagicElemental")) {
                                FinalMaxDamage = (int)((maxDamage * change - ((float) maxDamage /2)) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMaxDamage);

                        MaxKills = (int) ((int)(FinalMaxDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill));
                        kills.add(MaxKills);

                    }
            }

            // Расчет урона в случае атаки Ассидами со способностью Кровожадность

            else if (creaturesHitStack instanceof Assids) {
                if (heroeAttack.offense != 0) {
                    if (heroeAttack.SpecOffense) {
                        FinalMinDamage = (int) ((int)(minDamage * change) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int)((int)(minDamage * change) + (int)(minDamage*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((int)(minDamage * change) + (int)((minDamage) * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int)((int)(minDamage * change) + (int)((minDamage)*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)((minDamage) * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int)(((int)(minDamage * change) + (int)((minDamage)*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1 - heroeDefense.armorer));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)((minDamage) * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int)(((int)(minDamage * change) + (int)((minDamage)*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMinDamage);

                        MinKills = (int)(FinalMinDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill);
                        kills.add(MinKills);

                        FinalMaxDamage = (int) ((int)(maxDamage * change) + (int)(maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int)((int)(maxDamage * change) + (int)(maxDamage*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((int)(maxDamage * change) + (int)((maxDamage) * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int)((int)(maxDamage * change) + (int)((maxDamage)*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl))));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)((maxDamage) * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int)(((int)(maxDamage * change) + (int)((maxDamage)*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1 - heroeDefense.armorer));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)((maxDamage) * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int)(((int)(maxDamage * change) + (int)((maxDamage)*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense * (1 + 0.05 * heroeAttack.lvl)))) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMaxDamage);

                        MaxKills = (int) ((int)(FinalMaxDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill));
                        kills.add(MaxKills);

                    } else {
                        FinalMinDamage = (int) ((int)(minDamage * change) + (int)(minDamage * (heroeAttack.offense)));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int)((int)(minDamage * change) + (int)(minDamage*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense)));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((int)(minDamage) + (int)((minDamage) * (heroeAttack.offense)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int)((int)(minDamage) + (int)((minDamage)*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense)));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)((minDamage) * (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int)(((int)(minDamage * change) + (int)((minDamage)*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage * (heroeAttack.offense))) * (1 - heroeDefense.armorer));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)((minDamage) * (heroeAttack.offense))) * (1  - heroeDefense.armorer));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int)(((int)(minDamage * change) + (int)((minDamage)*(creaturesHitStack.hate - 1)) + (int)(minDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMinDamage);

                        MinKills = (int)(FinalMinDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill);
                        kills.add(MinKills);

                        FinalMaxDamage = (int) ((int)(maxDamage * change) + (int)(maxDamage * (heroeAttack.offense)));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int)((int)(maxDamage * change) + (int)(maxDamage*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense)));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((int)(maxDamage * change) + (int)((maxDamage) * (heroeAttack.offense)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int)((int)(maxDamage * change) + (int)((maxDamage)*(creaturesHitStack.hate - 1)) + (int)(maxDamage*change * (heroeAttack.offense)));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage*(creaturesHitStack.hate - 1)) + (int)(maxDamage* (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)((maxDamage) * (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int)(((int)(maxDamage * change) + (int)((maxDamage)*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage * (heroeAttack.offense))) * (1 - heroeDefense.armorer));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)((maxDamage) * (heroeAttack.offense))) * (1  - heroeDefense.armorer));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int)(((int)(maxDamage * change) + (int)((maxDamage)*(creaturesHitStack.hate - 1)) + (int)(maxDamage * (heroeAttack.offense))) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMaxDamage);

                        MaxKills = (int) ((int)(FinalMaxDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill));
                        kills.add(MaxKills);

                    }
                } else {
                        FinalMinDamage = (int) (minDamage * change);
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int)((int)(minDamage * change) + (int)(minDamage*(creaturesHitStack.hate - 1)));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) (minDamage * change);
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int)((int)(minDamage * change) + (int)((int)(minDamage)*(creaturesHitStack.hate - 1)));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMinDamage = (int) ((int)(minDamage * change) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage*(creaturesHitStack.hate - 1))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((minDamage * change) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int)(((int)(minDamage * change) + (int)((int)(minDamage)*(creaturesHitStack.hate - 1))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMinDamage = (int) ((int)(minDamage * change) * (1 - heroeDefense.armorer));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMinDamage = (int) (((int)(minDamage * change) + (int)(minDamage*(creaturesHitStack.hate - 1))) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMinDamage = (int) ((int)(minDamage) * (1  - heroeDefense.armorer));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMinDamage = (int)(((int)(minDamage * change) + (int)((int)(minDamage)*(creaturesHitStack.hate - 1))) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMinDamage);

                        MinKills = (int)(FinalMinDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill);
                        kills.add(MinKills);

                        FinalMaxDamage = (int) (maxDamage * change);
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int)((int)(maxDamage * change) + (int)(maxDamage*(creaturesHitStack.hate - 1)));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) (maxDamage * change);
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int)((int)(maxDamage * change) + (int)((int)(maxDamage)*(creaturesHitStack.hate - 1)));
                    }
                        }

                        if (heroeDefense.armorer != 0) {
                        if (heroeDefense.SpecDefense) {
                            FinalMaxDamage = (int) ((int)(maxDamage * change) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage*(creaturesHitStack.hate - 1))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((int)(maxDamage * change) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int)(((int)(maxDamage * change) + (int)((int)(maxDamage)*(creaturesHitStack.hate - 1))) * (1  - heroeDefense.armorer*(1 + 0.05 * heroeDefense.lvl)));
                    }
                        }
                        }
                        else {
                            FinalMaxDamage = (int) ((int)(maxDamage * change) * (1 - heroeDefense.armorer));
                        if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                            FinalMaxDamage = (int) (((int)(maxDamage * change) + (int)(maxDamage*(creaturesHitStack.hate - 1))) * (1  - heroeDefense.armorer));
                    }
                        if (change < 1) {
                            FinalMaxDamage = (int) ((int)(maxDamage * change) * (1  - heroeDefense.armorer));
                            if (creaturesHitStack.Hate1.equals(creatures.Name) || creaturesHitStack.Hate2.equals(creatures.Name)) {
                                FinalMaxDamage = (int)(((int)(maxDamage * change) + (int)((int)(maxDamage)*(creaturesHitStack.hate - 1))) * (1  - heroeDefense.armorer));
                    }
                        }
                        }
                    }

                        damage.add(FinalMaxDamage);

                        MaxKills = (int) ((int)(FinalMaxDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill));
                        kills.add(MaxKills);

                    }


                    if (kills.getFirst() > 0) {
                            FinalMinDamage *= 2;
                            damage.set(0,FinalMinDamage);

                            MinKills = (int)(FinalMinDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill);
                            kills.set(0,MinKills);
                        }
                    if (kills.getLast() > 0) {
                            FinalMaxDamage *= 2;
                            damage.set(1,FinalMaxDamage);

                            MaxKills = (int)(FinalMaxDamage/creatures.health) + (int)(amount * creaturesHitStack.chanceKill);
                            kills.set(1,MaxKills);
                        }

                }



            // Урон не может быть меньше 1
            for (int i = 0; i < damage.size(); i++) {
                if (damage.get(i)<1){
                    damage.set(i,1);
                }
            }


            // Вывод итогового урона и кол-ва убийств
            if (!Objects.equals(damage.getFirst(), damage.getLast())) {
                //System.out.println("Урон: " + damage.get(0) + " - " + damage.get(1));
                result = "Урон: " + damage.get(0) + " - " + damage.get(1);
            }
            else {
                //System.out.println("Урон: " + damage.get(0));
                result = "Урон: " + damage.get(0);
            }
            if (kills.getFirst() != kills.getLast()) {
                //System.out.println("Будет убито: " + kills.getFirst() + " - " + kills.getLast() + " существ");
                result += "\nБудет убито: " + kills.getFirst() + " - " + kills.getLast() + " существ";
            }
            else {
                //System.out.println("Будет убито: " + kills.getFirst() + " существ");
                result += "\nБудет убито: " + kills.getFirst() + " существ";
            }
            if (creaturesHitStack.chanceKill != 0) {
                result += "\n\nДоп убийство с шансом " + (int)Math.ceil(((amount * creaturesHitStack.chanceKill)%1)*100) + "%";
            }
            if (creaturesHitStack.Name.equals("Thunderbird")) {
                result += "\n\n + "+creaturesHitStack.extraDamage*amount+" урона с вероятностью 20%";
            }
            if (creaturesHitStack.Name.equals("DreadNight")) {
                result += "\n\nДвойной урон с вероятностью 20%";
            }
            if (creaturesHitStack instanceof Assids) {
                result += "\n\nУрон рассчитан при ударе по существам с максимальным здоровьем";
            }
        }

        if (heroeAttack.NativeLand){
            creaturesHitStack.Attack -= 1;
            creaturesHitStack.Defense -= 1;
        }

        if (heroeDefense.NativeLand){
            creatures.Attack -= 1;
            creatures.Defense -= 1;
        }

        if (heroeAttack.IsNeutral) {
            heroeAttack.Attack = OldHeroeAttackAttack;
            heroeAttack.Defense = OldHeroeAttackDefense;
            heroeAttack.offense = OldHeroeAttackOffense;
            heroeAttack.armorer = OldHeroeAttackArmorer;
            heroeAttack.archery = OldHeroeAttackArchery;
            heroeAttack.FixAtt = OldHeroeAttackFixAttack;
            heroeAttack.FixDef = OldHeroeAttackFixDefense;
            heroeAttack.lvl = OldHeroeAttackLvl;
        }

        if (heroeDefense.IsNeutral) {
            heroeDefense.Attack = OldHeroeDefenseAttack;
            heroeDefense.Defense = OldHeroeDefenseDefense;
            heroeDefense.offense = OldHeroeDefenseOffense;
            heroeDefense.armorer = OldHeroeDefenseArmorer;
            heroeDefense.archery = OldHeroeDefenseArchery;
            heroeDefense.FixAtt = OldHeroeDefenseFixAttack;
            heroeDefense.FixDef = OldHeroeDefenseFixAttack;
            heroeDefense.lvl = OldHeroeDefenseLvl;
        }

        creaturesHitStack.Attack = OldCreaturesHitStackAttack;
        creaturesHitStack.Defense = OldCreaturesHitStackDefense;
        creatures.Attack = OldCreaturesAttack;
        creatures.Defense = OldCreaturesDefense;

        return result;
    }
}
