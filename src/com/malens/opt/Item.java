package com.malens.opt;

/**
 * Created by malens on 2017-08-29.
 */
public class Item {

        private final Integer Power;
        private final Integer Precision;
        private final Integer Ferocity;
        private final Integer Concentration;
        private final Integer Toughness;
        private final Integer CondiDmg;
        private final Integer Expertise;
        private final String Piece;

        public Item(int power, int precision, int ferocity, int toughness, int concentration, int condiDmg, int expertise, String piece) {
            Power = power;
            Precision = precision;
            Toughness = toughness;
            Concentration = concentration;
            Ferocity = ferocity;
            CondiDmg = condiDmg;
            Expertise = expertise;
            Piece = piece;
        }

        public String getPiece() {
            return Piece;
        }

        public Integer getPower() {
            return Power;
        }

        public Integer getPrecision() {
            return Precision;
        }

        public Integer getToughness() {
            return Toughness;
        }

        public Integer getConcentration() {
            return Concentration;
        }

        public Integer getFerocity() {
            return Ferocity;
        }

        public Integer getCondiDmg() {
            return CondiDmg;
        }

        public Integer getExpertise() {
            return Expertise;
        }

        public int TotalStats () {
            return (this.Power + this.Precision + this.Concentration + this.CondiDmg + this.Expertise + this.Ferocity);
        }





}
