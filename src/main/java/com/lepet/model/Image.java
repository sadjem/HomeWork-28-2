package com.lepet.model;

import javax.persistence.*;

@Entity
@Table(name = "Image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private int id;

    @Column
    private String imageInBase64;
    @Expose
    private String aboutImage;

    public Image() {
    }

    public Image(String imageInBase64, String aboutImage) {
        this.imageInBase64 = imageInBase64;
        this.aboutImage = aboutImage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageInBase64(String imageInBase64) {
        this.imageInBase64 = imageInBase64;
    }

    public void setAboutImage(String aboutImage) {
        this.aboutImage = aboutImage;
    }

    public int getId() {
        return id;
    }

    public String getImageInBase64() {
        return imageInBase64;
    }

    public String getAboutImage() {
        return aboutImage;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson.toJson(this);
    }
}
