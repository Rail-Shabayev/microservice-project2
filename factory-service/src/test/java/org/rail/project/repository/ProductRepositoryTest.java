package org.rail.project.repository;

import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rail.project.model.Manufacturer;
import org.rail.project.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductRepositoryTest extends ContainerStart {
    @Autowired
    private ProductRepository productRepository;

    Product product = Instancio.of(Product.class)
            .set(Select.field(Manufacturer::getPhoneNumber), "+1 (415) 555‑0132")
            .set(Select.field(Manufacturer::getEmail), "sobaka@mail.ru")
            .create();

    @Test
    public void shouldSaveProduct() {
        Product savedProduct = productRepository.save(product);
        assertThat(savedProduct).usingRecursiveComparison()
                .ignoringFields("id", "manufacturer.id")
                .isEqualTo(product);
    }

    @Test
    public void shouldFindAll() {
        productRepository.save(product);
        List<Product> savedProducts = productRepository.findAll();
        assertThat(savedProducts).isNotEmpty();
    }

    @Test
    public void shouldFindById() {
        productRepository.save(product);
        Optional<Product> actualProduct = productRepository.findById(1L);
        assertThat(actualProduct.get().getName()).isEqualTo(product.getName());
    }
}