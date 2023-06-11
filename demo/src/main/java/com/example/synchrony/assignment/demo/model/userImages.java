package com.example.synchrony.assignment.demo.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;



@Entity
@Table(name="USER_IMAGES")

public class userImages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
	@SequenceGenerator(name="id_generator", sequenceName = "image_Seq", allocationSize=50)
	private int imageId;
	@Column
	private String imageName;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)	@JsonBackReference
	@ManyToOne
	private userProfile user;
	@Column
	private Date imageUploaded;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column
	private String deletehash;
	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column
	private String id;
	@Column
	private String link;

	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public userProfile getUser() {
		return user;
	}
	public void setUser(userProfile user) {
		this.user = user;
	}
	public Date getImageUploaded() {
		return imageUploaded;
	}
	public void setImageUploaded(Date imageUploaded) {
		this.imageUploaded = imageUploaded;
	}
	public userImages() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDeletehash() {
		return deletehash;
	}
	public void setDeletehash(String deletehash) {
		this.deletehash = deletehash;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public String toString() {
		return "userImages [imageId=" + imageId + ", imageName=" + imageName + ", user=" + user + ", imageUploaded="
				+ imageUploaded + ", deletehash=" + deletehash + ", id=" + id + ", link=" + link + "]";
	}
	

}
