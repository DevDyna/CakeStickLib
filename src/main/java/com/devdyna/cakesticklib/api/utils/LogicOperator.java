package com.devdyna.cakesticklib.api.utils;


//TODO maybe i will rework LogicOperator#execute due it doesn't still useful
/**
 * simple enum that define Logic operators
 */
public enum LogicOperator {
    NOT() {
        @Override
        public Boolean execute(Boolean... v) {
            return !v[0];
        }
    },
    AND() {
        @Override
        public Boolean execute(Boolean... v) {
            return v[0] && v[1];
        }
    },
    OR() {
        @Override
        public Boolean execute(Boolean... v) {
            return v[0] || v[1];
        }
    };

    public abstract Boolean execute(Boolean... v);

}