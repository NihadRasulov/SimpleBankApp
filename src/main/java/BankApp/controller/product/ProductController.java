package BankApp.controller.product;

import BankApp.dto.product.ProductRequestDto;
import BankApp.dto.product.ProductResponseDto;
import BankApp.model.product.Product;
import BankApp.service.product.ProductService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/createProduct")
    public ProductResponseDto create(@RequestPart ProductRequestDto productRequestDto,
                                     @RequestPart List<MultipartFile> file) throws IOException {
        return productService.createProduct(productRequestDto,file);
    }

    @GetMapping("/getAllProduct")
    public List<Product> getAll() {
        return productService.getAllProduct();
    }

    @GetMapping("/getById/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/updateProduct")
    public ProductResponseDto update(@RequestPart Long id, @RequestPart ProductRequestDto productRequestDto,@RequestPart List<MultipartFile> files) throws IOException {
        return productService.updateProduct(id, productRequestDto,files);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
