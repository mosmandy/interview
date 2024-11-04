package com.mosesm.zsstestint.Models;

import org.json.JSONObject;

import java.util.Map;

public class PaymentDetails {
    private String narration;
    private String reference;
    private Card card;

    private AdditionalData additionalData;

    //private Map<String, Object> additionalData;

    public String getNarration(){
        return this.narration;
    }
    public String getReference(){
        return this.reference;
    }
    public Card getCard(){
        return this.card;
    }
    public AdditionalData getAdditionalData(){
        return this.additionalData;
    }

    public void setNarration(String narration){
        this.narration = narration;
    }
    public void setReference(String reference){
        this.reference = reference;
    }
    public void setCard(Card card){
        this.card = card;
    }
    public void setAdditionalData(AdditionalData additionalData){
        this.additionalData = additionalData;
    }
}
