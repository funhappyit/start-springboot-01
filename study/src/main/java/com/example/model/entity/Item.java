package com.example.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.model.enumclass.ItemStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude= {"orderDetailList","partner"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private ItemStatus status; // 등록 / 해지 / 검수증(등록대기중)
	
	private String name;
	
	private String title;
	
	private String content;
	
	private BigDecimal price;
	
	private String brandName;
	
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
	

	@CreatedDate
	private LocalDateTime createdAt;
	
	@CreatedBy
	private String createdBy;
	
	@LastModifiedDate
	private LocalDateTime updatedAt;
	
	@LastModifiedBy
	private String updatedBy;
	
	//private Long partnerId;
	
	//Item 1:N OrderDetail
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	private List<OrderDetail> orderDetailList;
	
	//Item N:1 Partner
	@ManyToOne
	private Partner partner;
	
	
	
	
	/*
		1:N
		LAZY = 지연로딩, EAGER = 즉시로딩
		LAZY => 1:N, N:1
		LAZY = SELECT * FROM item where id = ?
		EAGER => 연관되는 모든 테이블을 조인후 1:1
		item_id = order_detail.item_id
		user_id = order_detail.user_id
		where item.id = ?
		@OneToMany(fetch=FetchType.LAZY, mappedBy = "item")
		private List<OrderDetail> orderDetailList;
	*/
	
	
}
