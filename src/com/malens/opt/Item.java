package com.malens.opt;

import com.malens.opt.Utilities.Stats;

/**
 * Created by malens on 2017-08-29.
 */
public class Item {
        private final Stats stats;
        private final String Piece;

        public Item(Stats stats, String piece) {
            this.stats = stats;
            Piece = piece;
        }

        public String getPiece() {
            return Piece;
        }

        public Stats getStats() {
            return this.stats;
        }

        public double TotalStats () {
            return (this.stats.Power + this.stats.Precision + this.stats.Concentration + this.stats.ConditionDamage +
                    this.stats.Expertise + this.stats.Ferocity);
        }





}
