package elastickafkadebezium.product.controller;

import elastickafkadebezium.product.request.CreateProductRequest;
import elastickafkadebezium.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateProductRequest request){
        productService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
