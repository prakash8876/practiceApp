package com.javatechie.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import com.javatechie.reactive.dto.ProductDto;
import com.javatechie.reactive.repository.ProductRepository;
import com.javatechie.reactive.utils.AppUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
	
	private final ProductRepository repo;
	
	@Autowired
	public ProductService(ProductRepository repo) {
		this.repo = repo;
	}
	
	public Flux<ProductDto> getProductsDto() {
		return repo.findAll().map(AppUtils::entityToDto);
	}
	
	public Mono<ProductDto> getProduct(String id) {
		return repo.findById(id).map(AppUtils::entityToDto);
	}
	
	public Flux<ProductDto> getProductInRange(Double min, Double max) {
		return repo.findByPriceBetween(Range.closed(min, max));
	}
	
	public Mono<ProductDto> saveProduct(Mono<ProductDto> productDto) {
		return productDto.map(AppUtils::dtoToEntity)
						.flatMap(repo::insert)
						.map(AppUtils::entityToDto);
	}
	
	public Mono<ProductDto> updateProduct(Mono<ProductDto> productDto, String id) {
		return repo.findById(id)
			.flatMap(p -> productDto.map(AppUtils::dtoToEntity)
			.doOnNext(e -> e.setId(id)))
			.flatMap(repo::save)
			.map(AppUtils::entityToDto);
	}
	
	public Mono<Void> deleteProduct(String id) {
		return repo.deleteById(id);
	}
}
