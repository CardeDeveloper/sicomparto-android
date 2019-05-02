package com.iteso.proyecto_pdm.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemAsociacion implements Parcelable {
    private int image;
    private String title;
    private String phone;
    private String description;
    private int code;

    public ItemAsociacion() {
        setImage(0);
        setTitle("");
        setPhone("");
        setDescription("");
    }

    @Override
    public String toString() {
        return "ItemProduct{" +
                "\nimage=" + image +
                ", \ntitle='" + title + '\'' +
                ", \nphone='" + phone + '\'' +
                ", \ndescription='" + description + '\'' +
                '}';
    }

    public ItemAsociacion(int image, String title, String phone, String description, int code) {
        setImage(image);
        setTitle(title);
        setPhone(phone);
        setDescription(description);
        setCode(code);
    }

    public ItemAsociacion(Parcel in){
        image = in.readInt();
        title = in.readString();
        phone = in.readString();
        description = in.readString();
        code = in.readInt();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(title);
        dest.writeString(phone);
        dest.writeString(description);
        dest.writeInt(code);
    }
    public static final Parcelable.Creator<ItemAsociacion> CREATOR = new
            Parcelable.Creator<ItemAsociacion>() {
                @Override
                public ItemAsociacion createFromParcel(Parcel source) {
                    // using parcelable constructor
                    return new ItemAsociacion (source);
                }
                @Override
                public ItemAsociacion [] newArray(int size) {
                    return new ItemAsociacion [size];
                }
            };

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
