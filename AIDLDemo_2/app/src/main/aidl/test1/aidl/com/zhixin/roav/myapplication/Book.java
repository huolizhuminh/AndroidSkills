package test1.aidl.com.zhixin.roav.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author minhui.zhu
 *         Created by minhui.zhu on 2018/3/24.
 *         Copyright © 2017年 Oceanwing. All rights reserved.
 */

public class Book implements Parcelable {
    public int name;
    public String ID;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.name);
        dest.writeString(this.ID);
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.name = in.readInt();
        this.ID = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
