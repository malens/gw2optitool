package com.malens.opt.Utilities;

/**
 * Created by malens on 2017-09-04.
 */
public class Stats {
    public double Power;
    public double Precision;
    public double Ferocity;
    public double Toughness;
    public double Vitality;
    public double Concentration;
    public double Expertise;
    public double HealingPower;
    public double ConditionDamage;

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

    public Stats(double power, double precision, double ferocity, double toughness, double vitality, double concentration, double expertise, double healingPower, double conditionDamage) {
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

    public double getPower() {
        return Power;
    }

    public void setPower(double power) {
        Power = power;
    }

    public double getPrecision() {
        return Precision;
    }

    public void setPrecision(double precision) {
        Precision = precision;
    }

    public double getFerocity() {
        return Ferocity;
    }

    public void setFerocity(double ferocity) {
        Ferocity = ferocity;
    }

    public double getToughness() {
        return Toughness;
    }

    public void setToughness(double toughness) {
        Toughness = toughness;
    }

    public double getVitality() {
        return Vitality;
    }

    public void setVitality(double vitality) {
        Vitality = vitality;
    }

    public double getConcentration() {
        return Concentration;
    }

    public void setConcentration(double concentration) {
        Concentration = concentration;
    }

    public double getExpertise() {
        return Expertise;
    }

    public void setExpertise(double expertise) {
        Expertise = expertise;
    }

    public double getHealingPower() {
        return HealingPower;
    }

    public void setHealingPower(double healingPower) {
        HealingPower = healingPower;
    }

    public double getConditionDamage() {
        return ConditionDamage;
    }

    public void setConditionDamage(double conditionDamage) {
        ConditionDamage = conditionDamage;
    }
}
