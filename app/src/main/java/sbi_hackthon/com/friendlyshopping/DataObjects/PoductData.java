package sbi_hackthon.com.friendlyshopping.DataObjects;

import android.support.annotation.NonNull;

/**
 * Created by ramesh p on 11/06/2017.
 */

public class PoductData implements Comparable<PoductData> {

    private String image, name, description;
    Long id;

    public PoductData() {

    }

    public PoductData(Long id, String name, String description, String image) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int compareTo(@NonNull PoductData o) {
        return (this.getId()+"").compareTo(o.getId()+"");
    }
}
