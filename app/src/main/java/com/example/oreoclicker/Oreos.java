package com.example.oreoclicker;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Oreos implements Parcelable {
    private String name;
    private int price;
    private int oreosPerSecond;
    private int image;
    private int count;

    public Oreos(String name, int price, int oreosPerSecond, int image, int count) {
        this.name = name;
        this.price = price;
        this.oreosPerSecond = oreosPerSecond;
        this.image = image;
        this.count = count;
    }

    protected Oreos(Parcel in) {
        name = in.readString();
        price = in.readInt();
        oreosPerSecond = in.readInt();
        image = in.readInt();
        count = in.readInt();
    }

    public static final Creator<Oreos> CREATOR = new Creator<Oreos>() {
        @Override
        public Oreos createFromParcel(Parcel in) {
            return new Oreos(in);
        }

        @Override
        public Oreos[] newArray(int size) {
            return new Oreos[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public int getCount() {
        return count;
    }

    public int getOreosPerSecond() {
        return oreosPerSecond;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(price);
        parcel.writeInt(oreosPerSecond);
        parcel.writeInt(image);
        parcel.writeInt(count);
    }
}
