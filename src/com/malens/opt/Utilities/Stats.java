package com.malens.opt.Utilities;

/**
 * Created by malens on 2017-09-04.
 */
public class Stats {
    public int Power;
    public int Precision;
    public int Ferocity;
    public int Toughness;
    public int Vitality;
    public int Concentration;
    public int Expertise;
    public int HealingPower;
    public int ConditionDamage;

    public Stats add (Stats o){
        this.Power += o.Power;
        this.Precision += o.Precision;
        this.Ferocity += o.Ferocity;
        this.Toughness += o.Toughness;
        this.Vitality += o.Vitality;
        this.Concentration += o.Concentration;
        this.Expertise += o.Expertise;
        this.HealingPower += o.HealingPower;
        this.ConditionDamage += o.ConditionDamage;
        return this;
    }

    public Stats(int power, int precision, int ferocity, int toughness, int vitality, int concentration, int expertise, int healingPower, int conditionDamage) {
        Power = power;
        Precision = precision;
        Ferocity = ferocity;
        Toughness = toughness;
        Vitality = vitality;
        Concentration = concentration;
        Expertise = expertise;
        HealingPower = healingPower;
        ConditionDamage = conditionDamage;
    }

    public Stats() {
        this.Power = 0;
        this.Precision = 0;
        this.Ferocity = 0;
        this.Toughness = 0;
        this.Vitality = 0;
        this.Concentration = 0;
        this.Expertise = 0;
        this.HealingPower = 0;
        this.ConditionDamage = 0;
    }

    public int getPower() {
        return Power;
    }

    public void setPower(int power) {
        Power = power;
    }

    public int getPrecision() {
        return Precision;
    }

    public void setPrecision(int precision) {
        Precision = precision;
    }

    public int getFerocity() {
        return Ferocity;
    }

    public void setFerocity(int ferocity) {
        Ferocity = ferocity;
    }

    public int getToughness() {
        return Toughness;
    }

    public void setToughness(int toughness) {
        Toughness = toughness;
    }

    public int getVitality() {
        return Vitality;
    }

    public void setVitality(int vitality) {
        Vitality = vitality;
    }

    public int getConcentration() {
        return Concentration;
    }

    public void setConcentration(int concentration) {
        Concentration = concentration;
    }

    public int getExpertise() {
        return Expertise;
    }

    public void setExpertise(int expertise) {
        Expertise = expertise;
    }

    public int getHealingPower() {
        return HealingPower;
    }

    public void setHealingPower(int healingPower) {
        HealingPower = healingPower;
    }

    public int getConditionDamage() {
        return ConditionDamage;
    }

    public void setConditionDamage(int conditionDamage) {
        ConditionDamage = conditionDamage;
    }
}
