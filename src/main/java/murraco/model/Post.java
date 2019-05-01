package murraco.model;

import javax.persistence.*;

@Entity
public class Post {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPosterId() {
        return posterId;
    }

    public void setPosterId(Integer posterId) {
        this.posterId = posterId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( nullable = false)
    private Integer posterId;

    private String description;

    @Column( nullable = false)
    private float price;

}
