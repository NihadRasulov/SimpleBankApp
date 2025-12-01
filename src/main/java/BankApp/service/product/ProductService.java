package BankApp.service.product;

import BankApp.dto.PhotoResponseDto;
import BankApp.dto.product.ProductRequestDto;
import BankApp.dto.product.ProductResponseDto;
import BankApp.model.product.Product;
import BankApp.repository.product.ProductRepository;
import BankApp.service.image.ImageService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductService {

    @Autowired
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    ProductRepository repository;

    @Autowired
    ImageService imageService;

    public ProductResponseDto createProduct(ProductRequestDto dto, List<MultipartFile> files) throws IOException {

        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());

        List<PhotoResponseDto> uploadedPhotos = imageService.uploadPhotos(files, "products");

        List<String> imageUrls = uploadedPhotos.stream()
                .map(PhotoResponseDto::url)
                .toList();
        product.setImageUrls(imageUrls);

        repository.save(product);

        return modelMapper.map(product,ProductResponseDto.class);
    }

    public List<Product> getAllProduct() {
        return repository.findAll();
    }

    public ProductResponseDto getProductById(Long id) {
        Optional<Product> product = repository.findById(id);
        return modelMapper.map(product, ProductResponseDto.class);
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto dto, List<MultipartFile> files) throws IOException {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());

        List<PhotoResponseDto> uploadedPhotos = imageService.uploadPhotos(files, "products");

        List<String> imageUrls = new ArrayList<>(
                uploadedPhotos.stream()
                        .map(PhotoResponseDto::url)
                        .toList()
        );
        product.setImageUrls(imageUrls);

        repository.save(product);
        return modelMapper.map(product,ProductResponseDto.class);
    }


    public void deleteProduct(Long id) {
        Optional<Product> product = repository.findById(id);
        repository.delete(product.get());
    }
}
