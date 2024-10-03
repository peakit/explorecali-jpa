package com.hmllc.explorecalijpa.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Column(length = 2000)
    private String description;

    @Column(length = 2000)
    private String blurb;

    @Column
    private Integer price;

    @Column
    private String duration;

    @Column(length = 2000)
    private String bullets;

    @Column
    private String keywords;

    @ManyToOne
    @JoinColumn(name = "tour_package_code", referencedColumnName = "code")
    private TourPackage tourPackage;

    @Column
    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficulty;

    @Column
    private Region region;

    public Integer getId() {
	return id;
    }

    public String getTitle() {
	return title;
    }

    public String getDescription() {
	return description;
    }

    public String getBlurb() {
	return blurb;
    }

    public void setBlurb(String blurb) {
	this.blurb = blurb;
    }

    public Integer getPrice() {
	return price;
    }

    public void setPrice(Integer price) {
	this.price = price;
    }

    public String getDuration() {
	return duration;
    }

    public void setDuration(String duration) {
	this.duration = duration;
    }

    public String getBullets() {
	return bullets;
    }

    public void setBullets(String bullets) {
	this.bullets = bullets;
    }

    public String getKeywords() {
	return keywords;
    }

    public void setKeywords(String keywords) {
	this.keywords = keywords;
    }

    public TourPackage getTourPackage() {
	return tourPackage;
    }

    public DifficultyLevel getDifficulty() {
	return difficulty;
    }

    public Region getRegion() {
	return region;
    }

    public Tour() {
	super();
    }

    public Tour(String title, String description, String blurb, Integer price, String duration, String bullets,
	    String keywords, TourPackage tourPackage, DifficultyLevel difficulty, Region region) {
	super();
	this.title = title;
	this.description = description;
	this.blurb = blurb;
	this.price = price;
	this.duration = duration;
	this.bullets = bullets;
	this.keywords = keywords;
	this.tourPackage = tourPackage;
	this.difficulty = difficulty;
	this.region = region;
    }

    @Override
    public int hashCode() {
	return Objects.hash(blurb, bullets, description, difficulty, duration, id, keywords, price, region, title,
		tourPackage);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Tour other = (Tour) obj;
	return Objects.equals(blurb, other.blurb) && Objects.equals(bullets, other.bullets)
		&& Objects.equals(description, other.description) && difficulty == other.difficulty
		&& Objects.equals(duration, other.duration) && Objects.equals(id, other.id)
		&& Objects.equals(keywords, other.keywords) && Objects.equals(price, other.price)
		&& region == other.region && Objects.equals(title, other.title)
		&& Objects.equals(tourPackage, other.tourPackage);
    }

}
