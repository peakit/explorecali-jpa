package com.hmllc.explorecalijpa.model;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="tourLite", types = {Tour.class})
public interface TourLite {
    
    String getTitle();
    
    Integer getPrice();
    
    String getDuration();

}
