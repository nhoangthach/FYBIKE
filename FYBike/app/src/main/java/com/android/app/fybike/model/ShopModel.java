package com.android.app.fybike.model;



public class ShopModel {

    private String nameShop;
    private String addressShop;
    private int photoOfShop;
    private int type;
    private int rating;
    private double longitue;
    private double latitue;

    public ShopModel (){

    }

    public ShopModel (String _nameShop, String _addressShop, int _photoOfShop){
        nameShop = _nameShop;
        addressShop = _addressShop;
        photoOfShop = _photoOfShop;
    }

    public double getLatitue() {
        return latitue;
    }

    public void setLatitue(double latitue) {
        this.latitue = latitue;
    }

    public double getLongitue() {
        return longitue;
    }

    public void setLongitue(double longitue) {
        this.longitue = longitue;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

    public String getAddressShop() {
        return addressShop;
    }

    public void setAddressShop(String addressShop) {
        this.addressShop = addressShop;
    }

    public int getPhotoOfShop() {
        return photoOfShop;
    }

    public void setPhotoOfShop(int photoOfShop) {
        this.photoOfShop = photoOfShop;
    }
}
