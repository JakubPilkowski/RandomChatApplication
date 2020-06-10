package com.example.randomchatapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.randomchatapplication.interfaces.HobbyInterface;

public class ListenerContainer implements Parcelable {
    private HobbyInterface hobbyInterface;

    public ListenerContainer(HobbyInterface hobbyInterface) {
        this.hobbyInterface = hobbyInterface;
    }

    protected ListenerContainer(Parcel in) {
    }

    public static final Creator<ListenerContainer> CREATOR = new Creator<ListenerContainer>() {
        @Override
        public ListenerContainer createFromParcel(Parcel in) {
            return new ListenerContainer(in);
        }

        @Override
        public ListenerContainer[] newArray(int size) {
            return new ListenerContainer[size];
        }
    };

    public HobbyInterface getHobbyInterface() {
        return hobbyInterface;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInterfaceToken();
    }
}
