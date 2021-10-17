package com.javatechie.reactive.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.reactive.dto.ProductDto;
import com.javatechie.reactive.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping
	public Flux<ProductDto> products() { return service.getProductsDto(); }

	@GetMapping("/{id}")
	public Mono<ProductDto> product(@PathVariable String id) { return service.getProduct(id); }
	
	@GetMapping("/range")
	public Flux<ProductDto> getBetweenRange(@RequestParam("min") Double min, @RequestParam("max") Double max) {
		return service.getProductInRange(min, max);
	}
	
	@PostMapping
	public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDto) {
		return service.saveProduct(productDto);	
	}
	
	@PutMapping("/{id}")
	public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDto, @PathVariable String id) {
		return service.updateProduct(productDto, id);
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> deleteProduct(@PathVariable String id) { return service.deleteProduct(id); }
	
	
	
}
