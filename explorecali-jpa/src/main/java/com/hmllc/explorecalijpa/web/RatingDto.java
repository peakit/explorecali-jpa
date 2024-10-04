package com.hmllc.explorecalijpa.web;

import com.hmllc.explorecalijpa.model.TourRating;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RatingDto {

    @NotNull
    private Integer customerId;

    @Min(value = 1)
    @Max(value = 5)
    private Integer score;

    @Size(max = 300)
    private String comments;

    public RatingDto(Integer score, Integer customerId, String comments) {
	this.score = score;
	this.customerId = customerId;
	this.comments = comments;
    }

    public RatingDto(TourRating rating) {
	this.score = rating.getScore();
	this.customerId = rating.getCustomerId();
	this.comments = rating.getComments();
    }

}
