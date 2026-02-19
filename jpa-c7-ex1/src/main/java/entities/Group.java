package entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="ugroups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "users_ugroups",
            joinColumns = @JoinColumn(name="ugroup_id") , // this tells name of col of join_table which is the foreign key of current entity.
            inverseJoinColumns = @JoinColumn(name= "user_id")   // same for the inverse side of  it.
    )
    private List<User> users;
    // in it owner of relationship of don't matter, since no entity has foreign key, join_table have both the foreign key,
    //here is @JoinTable so we can say it is kind of owner of table (in true sense not a owner)


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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
