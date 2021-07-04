package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Product;
import com.app.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1")
@Api(tags = { "Product Controller" })
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping("/addProduct")
	@Operation(summary = "add Product", description = "you must pass object of Product", responses = {
			@ApiResponse(responseCode = "201", description = "Product CREATED") })
	public ResponseEntity<Product> addProduct(@RequestBody(required = true) Product product) {
		return new ResponseEntity<Product>(service.saveProduct(product), HttpStatus.CREATED);
	}

	@PostMapping("/addProducts")
	@Operation(summary = "add Products", description = "you must pass objects of Product", responses = {
			@ApiResponse(responseCode = "201", description = "Products CREATED") })
	public ResponseEntity<List<Product>> addProducts(@RequestBody List<Product> products) {
		return new ResponseEntity<List<Product>>(service.saveProducts(products), HttpStatus.CREATED);
	}

	@GetMapping("/products")
	@Operation(summary = "find All Products", description = "you get all products", responses = {
			@ApiResponse(responseCode = "200", description = "Everything looks ok") })
	public ResponseEntity<List<Product>> findAllProducts() {
		return new ResponseEntity<List<Product>>(service.getProducts(), HttpStatus.OK);
	}

	@GetMapping("/productById/{id}")
	@Operation(summary = "find Product By id", description = "you must pass the id of the Product", responses = {
			@ApiResponse(responseCode = "200", description = "Everything looks ok") })
	public ResponseEntity<Product> findProductById(@PathVariable int id) {
		return new ResponseEntity<Product>(service.getProductById(id), HttpStatus.OK);
	}

	@GetMapping("/product/{name}")
	@Operation(summary = "find Product By name", description = "you must pass the name of the Product", responses = {
			@ApiResponse(responseCode = "200", description = "Everything looks ok") })
	public ResponseEntity<Product> findProductByName(@PathVariable String name) {
		return new ResponseEntity<Product>(service.getProductByName(name), HttpStatus.OK);
	}

	@PutMapping("/update")
	@Operation(summary = "update Product", description = "you must pass instance of Product", responses = {
			@ApiResponse(responseCode = "200", description = "Everything looks ok") })
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		return new ResponseEntity<Product>(service.updateProduct(product), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@Operation(summary = "delete Product By id", description = "you must pass the id of the Product", responses = {
			@ApiResponse(responseCode = "200", description = "Everything looks ok"),
			@ApiResponse(responseCode = "400", description = "BAD REQUEST") })
	public ResponseEntity<String> deleteProduct(@PathVariable int id) {
		try {
			return new ResponseEntity<String>(service.deleteProduct(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("no Product was fount with this id: " + id, HttpStatus.BAD_REQUEST);
		}
	}

}
