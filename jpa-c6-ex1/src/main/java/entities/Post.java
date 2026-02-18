package entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String content;

// for unidirectional
//    @OneToMany
//    @JoinColumn(name="post_id")   // only case where JoinColumn on the opposite side of owner
//    private List<Comment> comments;

    //in bidirectional , want cascade to work both sides , then use in both sides, since internal cascade logic
    //for cascade is different for both.
    //for bidirectional
    @OneToMany (mappedBy = "post",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    // by default fetch type for collection is Lazy ,and naturally we don't want to fetch all when fetching the Post

    private List<Comment> comments;   //here is a collection ( List, map ,set, anything collection can be used.


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
