package com.hmllc.explorecalijpa.model;

import java.util.List;

public enum Region {

    Central_Coast("Central Coast"), Southern_California("Southern California"),
    Norther_California("Northern California"), Varies("Varies");

    private String label;

    private Region(String label) {
	this.label = label;
    }

    /**
     * Finds a Region matching the label specified
     * 
     * @param label
     * @return
     */
    public static Region findByLabel(String label) {
	return List.of(Region.values()).stream().filter(r -> r.getLabel().equalsIgnoreCase(label)).findFirst()
		.orElse(null);
    }

    public String getLabel() {
	return label;
    }

}
