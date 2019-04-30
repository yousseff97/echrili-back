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

    public Integer getPosterID() {
        return poster_id;
    }

    public void setPosterID(Integer poster_id) {
        this.poster_id = poster_id;
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
    private Integer poster_id;

    private String description;

    @Column( nullable = false)
    private float price;



}
