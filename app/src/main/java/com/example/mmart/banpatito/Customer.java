package com.example.mmart.banpatito;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by MMART on 9/8/2017.
 */
public class Customer implements Parcelable {

    private String name;
    private int operations;
    private boolean flag = true;
    private int position;
    private String id;
    private ArrayList<String> visits;

    public Customer(String name, int operations) {
        this.name = name;
        this.operations = operations;
        this.visits = new ArrayList<String>();
    }

    public Customer() {

    }

    public Customer(Parcel in) {
        this.name = in.readString();
        this.operations = in.readInt();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOperations() {
        return operations;
    }

    public void setOperations(int operations) {
        this.operations = operations;
    }

    public boolean checkFlag() { return flag; }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ArrayList<String> getVisits() {
        return visits;
    }

    public void setVisits(ArrayList<String> visits) {
        this.visits = visits;
    }

    public void addVisit(String visit) {
        this.visits.add(visit);
    }

    public String printVisits() {
        String print = "";
        if (this.visits != null) {
            for (String visit : this.visits) {
                if (print.length() > 0) {
                    print += ", " + visit;
                } else {
                    print += visit;
                }
            }
        }
        return print;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(operations);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public Customer createFromParcel(Parcel in){
            return new Customer(in);
        }
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };
}
