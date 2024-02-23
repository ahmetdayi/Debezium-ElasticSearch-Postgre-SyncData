package elastickafkadebezium.product.controller;

import elastickafkadebezium.product.response.ElasticProductResponse;
import elastickafkadebezium.product.service.ElasticProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/elasticProduct")
@RestController
@RequiredArgsConstructor
public class ElasticProductController {

    private final ElasticProductService elasticProductService;

    @GetMapping("/findAll")
    public ResponseEntity<List<ElasticProductResponse>> findAll(){
        return new ResponseEntity<>(elasticProductService.findAll(), HttpStatus.OK);
    }
}
