package com.mosesm.zsstestint.Models;

import org.json.JSONObject;

public class Card {
    private String id;
    private String expiry;

    public String getId(){
        return this.id;
    }
    public String getExpiry(){
        return this.expiry;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setExpiry(String expiry){
        this.expiry = expiry;
    }

}
