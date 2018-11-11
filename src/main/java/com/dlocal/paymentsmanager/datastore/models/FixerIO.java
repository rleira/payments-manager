package com.dlocal.paymentsmanager.datastore.models;

import org.springframework.data.annotation.Id;

import java.util.HashMap;

public class FixerIO {

    @Id
    public Integer timestamp;

    public String base;

    public String date;

    public HashMap<String, Double> rates;

    public FixerIO() {}

    @Override
    public String toString() {
        return String.format(
                "FixerIO[timestamp='%s', base='%s, base='%s, date='%s, rates='%s']",
                timestamp,
                base,
                date,
                rates
        );
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HashMap<String, Double> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, Double> rates) {
        this.rates = rates;
    }
}
