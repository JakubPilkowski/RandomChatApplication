package com.example.randomchatapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Hobby implements Parcelable {

    private String value;
    private boolean checked;

    public Hobby(String value){
        this.value = value;
        checked = false;
    }

    protected Hobby(Parcel in) {
        value = in.readString();
        checked = in.readByte() != 0;
    }

    public static final Creator<Hobby> CREATOR = new Creator<Hobby>() {
        @Override
        public Hobby createFromParcel(Parcel in) {
            return new Hobby(in);
        }

        @Override
        public Hobby[] newArray(int size) {
            return new Hobby[size];
        }
    };

    public String getValue() {
        return value;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(value);
        dest.writeByte((byte) (checked ? 1 : 0));
    }
}
