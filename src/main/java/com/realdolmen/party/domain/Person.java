package com.realdolmen.party.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Levifer on 15/09/2014.
 */
@Entity
public class Person {

    @GeneratedValue
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    @Temporal(TemporalType.DATE)
    private Date dayOfBirth;
    private char gender;


    public Person() {
    }

    public Person(String firstName, String lastName, String userName, String password, Date dayOfBirth, char gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.dayOfBirth = dayOfBirth;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
