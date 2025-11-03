package com.sgh.sgh_marketing.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "patient")
public class Patient {

    @Id
    private String id;
    private int age;
    private String gender;
    private String first_visit_date;
    private String diagnosis;
    private String city_or_village;
    private int pincode;
    private double latitude;
    private double longitude;

    public Patient() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getFirst_visit_date() { return first_visit_date; }
    public void setFirst_visit_date(String first_visit_date) { this.first_visit_date = first_visit_date; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getCity_or_village() { return city_or_village; }
    public void setCity_or_village(String city_or_village) { this.city_or_village = city_or_village; }

    public int getPincode() { return pincode; }
    public void setPincode(int pincode) { this.pincode = pincode; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
}
