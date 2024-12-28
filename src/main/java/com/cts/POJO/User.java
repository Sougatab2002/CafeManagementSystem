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
import lombok.*;


@NamedQuery(name="User.findByEmailId",query="select u from User u where u.email=:email")



@Data
@Entity
//@DynamicUpdate is used when we have entities with a large number of columns and you expect that only a few columns will be modified frequently.
@DynamicUpdate
@DynamicInsert
@Table(name="user")
public class User {

		
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name= "Id")
		private Integer id;
		
		@Column(name= "name")
		private String name;
		
		@Column(name= "contactNumber")
		private String contactNumber;
		
		@Column(name= "email")
		private String email;
		
		@Column(name= "password")
		private String password;
		
		@Column(name= "status")
		private String status;
		
		@Column(name= "role")
		private String role;
		
		
		
}
