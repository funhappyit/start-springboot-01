package com.example.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data //== getter, setter
@AllArgsConstructor //== 생성자
@NoArgsConstructor
@Entity //==table
@ToString(exclude = {"orderGroup"})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String account;
	
	private String password;
	
	private String status;
	
	private String email;
	
	private String phoneNumber;
	
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
	
	private LocalDateTime createdAt;
	
	private String createdBy;
	
	private LocalDateTime updatedAt;
	
	private String updatedBy;
	
	//User 1: N  OrderGroup
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<OrderGroup> orderGroupList;
	
}