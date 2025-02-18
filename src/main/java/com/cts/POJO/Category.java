package com.cts.POJO;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;


//@NamedQuery(name="Category.getAllCategory", query="select c from Category c where c.id in (select p.category from Product p where p.status='true')")
@NamedQuery(name = "Category.getAllCategory" , query = "select c from Category c")
@Data
@Entity
//@DynamicUpdate is used when we have entities with a large number of columns and you expect that only a few columns will be modified frequently.
@DynamicUpdate
@DynamicInsert
@Table(name="category")
public class Category {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;

}
