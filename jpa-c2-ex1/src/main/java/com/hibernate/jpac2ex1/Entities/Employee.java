package com.hibernate.jpac2ex1.Entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity   //(name=" ")  this name is set's the name for the persistence
@Table(name ="employee")   //to set the name of modelling table
public class Employee {

    @Id       //any entity need to have the ID (primary key),  simple or composed primary key.
    private int id;


    @Column()       // it used to specify the properties for the column
    private String name;


    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

// Entity annotations instructs spring to model the class to the table in the DB.
