package elastickafkadebezium.product.controller;

import elastickafkadebezium.product.response.CategoryResponse;
import elastickafkadebezium.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/findAll")
    public ResponseEntity<List<CategoryResponse>> findAll(){
        return new ResponseEntity<>(categoryService.findAll(),HttpStatus.OK);
    }
}
