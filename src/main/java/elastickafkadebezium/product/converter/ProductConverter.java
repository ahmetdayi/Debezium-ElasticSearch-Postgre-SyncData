package elastickafkadebezium.product.converter;

import elastickafkadebezium.product.model.Product;
import elastickafkadebezium.product.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final CategoryConverter categoryConverter;
    public List<ProductResponse> convert(List<Product> fromlist){
        return fromlist
                .stream()
                .map(from -> new ProductResponse(
                        from.getId(),
                        from.getName(),
                        from.getUnitStock(),
                        categoryConverter.convertResponse(from.getCategoryList())))
                .toList();
    }
    public ProductResponse convert(Product from){
        return new ProductResponse(
                        from.getId(),
                        from.getName(),
                        from.getUnitStock(),
                        categoryConverter.convertResponse(from.getCategoryList()));

    }
}
