package model;

import android.support.annotation.NonNull;

public class ProductClass  {
    public String Name;
    public int ID;
    public String Code;
    public String Price;
    public String Image;
    public int GroupID;
    public String NameKey;
    public String NameView;

    public String getNameView() {
        return NameView;
    }

    public void setNameView(String nameView) {
        NameView = nameView;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getGroupID() {
        return GroupID;
    }

    public void setGroupID(int groupID) {
        GroupID = groupID;
    }

    public String getNameKey() {
        return NameKey;
    }

    public void setNameKey(String nameKey) {
        NameKey = nameKey;
    }
}
