package com.example.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controller.ifs.CrudInterface;
import com.example.model.entity.Item;
import com.example.model.network.Header;
import com.example.model.network.request.ItemApiRequest;
import com.example.model.network.response.ItemApiResponse;
import com.example.repository.ItemRepository;
import com.example.repository.PartnerRepository;

@Service
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse>{

	@Autowired
	private PartnerRepository partnerRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	@Override
	public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
		ItemApiRequest body = request.getData();
		
		Item item = Item.builder()
				.status(body.getStatus())
				.name(body.getName())
				.title(body.getTitle())
				.content(body.getContent())
				.price(body.getPrice())
				.brandName(body.getBrandName())
				.registeredAt(LocalDateTime.now())
				.partner(partnerRepository.getOne(body.getPartnerId()))
				.build();
		
		Item newItem = itemRepository.save(item);
		
		return response(newItem);
	}

	@Override
	public Header<ItemApiResponse> read(Long id) {
		return itemRepository.findById(id)
							.map(item -> response(item))
							.orElseGet(()->Header.ERROR("데이터 없음"));
	}

	@Override
	public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Header delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private Header<ItemApiResponse> response(Item item){
		ItemApiResponse body = ItemApiResponse.builder()
				.id(item.getId())
				.status(item.getStatus())
				.name(item.getName())
				.title(item.getTitle())
				.content(item.getContent())
				.price(item.getPrice())
				.brandName(item.getBrandName())
				.registeredAt(item.getRegisteredAt())
				.unregisteredAt(item.getUnregisteredAt())
				.partnerId(item.getPartner().getId())
				.build();
				
		return Header.OK(body);
		
		
	}
	
	
	
	
}