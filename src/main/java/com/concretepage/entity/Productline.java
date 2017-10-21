package com.concretepage.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the productlines database table.
 * 
 */
@Entity
@Table(name="productlines")
@NamedQuery(name="Productline.findAll", query="SELECT p FROM Productline p")
public class Productline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String productLine;

	@Lob
	private String htmlDescription;

	@Lob
	private byte[] image;

	private String textDescription;

	public Productline() {
	}

	public String getProductLine() {
		return this.productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public String getHtmlDescription() {
		return this.htmlDescription;
	}

	public void setHtmlDescription(String htmlDescription) {
		this.htmlDescription = htmlDescription;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getTextDescription() {
		return this.textDescription;
	}

	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}

}