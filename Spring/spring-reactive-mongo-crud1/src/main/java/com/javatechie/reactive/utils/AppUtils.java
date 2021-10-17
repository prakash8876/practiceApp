package com.javatechie.reactive.utils;

import org.springframework.beans.BeanUtils;

import com.javatechie.reactive.dto.ProductDto;
import com.javatechie.reactive.entity.Product;

public class AppUtils {
	
	public static ProductDto entityToDto(Product product) {
		ProductDto productDto = new ProductDto();
//		use BeanUtil.copyProperties() only if both source and destination
//		object have same elemnets
		BeanUtils.copyProperties(product, productDto);
		return productDto;
	}
	
	public static Product dtoToEntity(ProductDto productDto) {
		Product product = new Product();
//		use BeanUtil.copyProperties() only if both source and destination
//		object have same elemnets
		BeanUtils.copyProperties(productDto, product);
		return product;
	}
}
