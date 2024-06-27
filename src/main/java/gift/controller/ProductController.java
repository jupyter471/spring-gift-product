package gift.controller;

import gift.ProductService;
import gift.dto.ProductRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService = new ProductService();

    /**
     * 상품 등록 API
     * @param requestDto 이름과 가격 이미지 URL
     */
    @PostMapping("")
    public void save(@RequestBody ProductRequestDto requestDto){
        productService.addProduct(requestDto);
    }
}