package com.app.entity.onetoM;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book implements Serializable{
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String title;
	    private String author;
	    @Column(unique = true)
	    private String isbn;

	    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY,
	            cascade = CascadeType.ALL)
	    private Set<Page> pages;

}
