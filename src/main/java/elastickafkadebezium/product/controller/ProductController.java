package elastickafkadebezium.product.controller;

import elastickafkadebezium.product.request.CreateProductRequest;
import elastickafkadebezium.product.request.UpdateProductRequest;
import elastickafkadebezium.product.request.UpdateProduct_CategoryList_Request;
import elastickafkadebezium.product.response.ProductResponse;
import elastickafkadebezium.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody CreateProductRequest request){
        productService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody UpdateProductRequest request){
        productService.update(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/updateCategoryList")
    public ResponseEntity<Void> updateCategoryList(@RequestBody UpdateProduct_CategoryList_Request request){
        productService.updateCategoryList(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<ProductResponse>> findAll(){
        return new ResponseEntity<>(productService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable String id){
        return new ResponseEntity<>(productService.getById(id),HttpStatus.OK);
    }



}
