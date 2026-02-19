package entities;


import jakarta.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  //means only a single table will be created
//// and a discriminator col to determine which child
//@DiscriminatorColumn(name = "ProductType")  // to have custom name for the DType  (discrinimator colmn)


//@Inheritance(strategy = InheritanceType.JOINED)//  it will create table for each entity

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product {  //abstract since we don't have products in real life , we have its specific types.
    // abstract and entity since we want polymorphic behaviour and get the child class records using parent reference.

    @Id
    protected int id;

    public int getId() {
        return id;
    }
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
