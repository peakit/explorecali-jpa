package com.hmllc.explorecalijpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "tour_rating", uniqueConstraints = {@UniqueConstraint(columnNames = {"tour_id", "customer_id"})})
@Data
public class TourRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tour_id", referencedColumnName = "id")
    private Tour tour;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @Column(nullable = false)
    private Integer score;

    @Column(length = 300)
    private String comments;

    public TourRating() {
	super();
    }

    public TourRating(Tour tour, Integer customerId, Integer score, String comments) {
	this.tour = tour;
	this.customerId = customerId;
	this.score = score;
	this.comments = comments;
    }

}
