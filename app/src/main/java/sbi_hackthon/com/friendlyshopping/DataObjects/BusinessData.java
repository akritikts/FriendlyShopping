package sbi_hackthon.com.friendlyshopping.DataObjects;

import android.support.annotation.NonNull;

/**
 * Created by ramesh p on 11/06/2017.
 */

public class BusinessData implements Comparable<BusinessData> {

    private String image, name, description, location;
    Long id;

    public BusinessData() {

    }

    public BusinessData(Long id, String name, String description, String location, String image) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.id = id;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int compareTo(@NonNull BusinessData o) {
        return (this.getId()+"").compareTo(o.getId()+"");
    }
}
