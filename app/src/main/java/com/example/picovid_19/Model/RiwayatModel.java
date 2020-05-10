package com.example.picovid_19.Model;

import com.google.gson.annotations.SerializedName;

public class RiwayatModel {
    //APi dengan milhat ringkasan seluruh negara terkena covid
    @SerializedName("combinedKey")
    private String combinedKey;
    @SerializedName("lastUpdate")
    private String lastUpdate;
    @SerializedName("confirmed")
    private String confirmed;
    @SerializedName("deaths")
    private String deaths;
    @SerializedName("recovered")
    private String recovered;

    public RiwayatModel(String combinedKey, String lastUpdate, String confirmed, String deaths, String recovered) {
        this.combinedKey = combinedKey;
        this.lastUpdate = lastUpdate;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public String getCountryRegion() {return combinedKey; }
    public void setCountryRegion(String countryRegion) {
        this.combinedKey = combinedKey;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getConfirmed() {
        return confirmed;
    }
    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getDeaths() {
        return deaths;
    }
    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRecovered() {
        return recovered;
    }
    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }
}
