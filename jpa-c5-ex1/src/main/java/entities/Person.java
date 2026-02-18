package entities;

import jakarta.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)   // means persist operation on person,will cascade(happen to) on Passport also
    // cascade adds cascade objects in the context and then commit() mirrors it in DB
    // fetchTYpe= Lazy means only when the passport type is needed 1st time only then it is fetched
    //if not used, won't be fetched
    // fetchType = Eager means passport fetched when person details fetched

    // optional  means person might not have an passport

    @JoinColumn(name = "passport")   //for giving specific name of foreign key
    // no mappedBY means He is the owner of the relationship and controls the foreign key.
    //  JoinColumn will always be on the owner of the relationship, since it names the foreign key
    //and foreign key is  always inside the owner of the relationship
    private Passport passport;

    /*
    Think of it like:
    Owning side = Database controller
    Inverse side = Mirror only
    mappedBy = "I am the mirror."
     */
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

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passport=" + passport +
                '}';
    }

    /*
    What Happens If You Remove mappedBy?
    `If both sides are like this:
    @OneToOne
    private Person person;
    Hibernate thinks:
    Two separate relationships exist.

    And it creates:
    One foreign key in PERSON
    One foreign key in PASSPORT
    Which is WRONG.
    So mappedBy prevents duplicate relationship creation.
    🎯 Why Is Ownership Important?
    Because only the owning side:
    Updates the foreign key
    Controls relationship persistence
    Generates join column`
     */
}
