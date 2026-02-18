package com.hibernate.jpac3ex1.Entities;


import jakarta.persistence.*;

@Entity
@Table(name ="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;


    @Column()
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
