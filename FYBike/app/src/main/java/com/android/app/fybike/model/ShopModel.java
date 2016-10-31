package com.android.app.fybike.model;



public class ShopModel {

    private String nameShop;
    private String addressShop;
    private int photoOfShop;

    public ShopModel (String _nameShop, String _addressShop, int _photoOfShop){
        nameShop = _nameShop;
        addressShop = _addressShop;
        photoOfShop = _photoOfShop;
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
