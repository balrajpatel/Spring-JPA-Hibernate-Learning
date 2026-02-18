package entities;


import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    public int getId() {
        return id;
    }


    // for unidirectional
    // Many side table should get the foreignKey , logically bhi think
    //ManyToOne  or OneToMany
//    @ManyToOne  // always read from class to attribute ( many comments in a post)
//    // Comment is the owner of the relationship,
//    @JoinColumn(name="post")
//    private Post post;

    //for BIdirectional
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name ="post")
    private Post post;


    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
//    public Post getPost() {
//        return post;
//    }
//
//    public void setPost(Post post) {
//        this.post = post;
//    }
}
