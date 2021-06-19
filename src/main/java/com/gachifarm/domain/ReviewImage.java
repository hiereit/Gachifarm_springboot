package com.gachifarm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
public class ReviewImage implements Serializable {
	@Id
	@SequenceGenerator(name = "REVIEWIMAGE_SEQ_GENERATOR", sequenceName = "REVIEWIMAGE_SEQUENCE", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "REVIEWIMAGE_SEQ_GENERATOR")
	@Column(name="img_id")
	private int imgId;
	private String imgName;
	private String imgPath;
	@Column(name="review_id")
	private int reviewId;
}
