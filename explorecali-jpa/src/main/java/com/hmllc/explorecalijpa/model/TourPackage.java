package com.hmllc.explorecalijpa.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "tour_package")
@Entity
public class TourPackage {

    @Id
    private String code;

    @Column
    private String name;

    public TourPackage() {
	super();
    }

    public TourPackage(String code, String name) {
	super();
	this.code = code;
	this.name = name;
    }

    public String getCode() {
	return code;
    }

    public String getName() {
	return name;
    }

    @Override
    public String toString() {
	return "TourPackage [code=" + code + ", name=" + name + "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(code, name);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	TourPackage other = (TourPackage) obj;
	return Objects.equals(code, other.code) && Objects.equals(name, other.name);
    }

}
