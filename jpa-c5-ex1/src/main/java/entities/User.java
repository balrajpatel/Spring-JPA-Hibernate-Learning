package entities;

import jakarta.persistence.*;

@Entity
  // in this entity user is the composition of two tables (user, user_details), no one_one relationship
@SecondaryTable(
        name="user_details",
        pkJoinColumns=@PrimaryKeyJoinColumn(name="id")    // tells join both the table using id PK column
)   // maps one entity ( user) to multiple tables (user, user_details)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;


    @Column(table="user_details")      // telling where is that attribute.
    // Store this column in user_details table instead of main table.
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // from  hibernate POV no relationship.
    // from DB POV they are one to one related and by foreign Key.
}
