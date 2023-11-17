package me.tamsil.multidatasource;

import me.tamsil.multidatasource.entity.product.Product;
import me.tamsil.multidatasource.entity.user.User;
import me.tamsil.multidatasource.repository.product.ProductRepository;
import me.tamsil.multidatasource.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@EnableTransactionManagement
@Rollback(value = false)
public class JpaMultiDatasourceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional("userTransactionManager")
    public void whenCreatingUser_thenCreated() {
        User user = new User();
        user.setName("Tamsil");
        user.setEmail("tamsil2@naver.com");
        user.setAge(30);
        User savedUser = userRepository.save(user);

        assertThat(savedUser).isNotNull();
    }

    @Test
    @Transactional("userTransactionManager")
    public void whenCreatingUserWithSameEmail_thenRollback() {
        User user1 = new User();
        user1.setName("tamsil2");
        user1.setEmail("tamsil2@naver.com");
        user1.setAge(30);
        user1 = userRepository.save(user1);
        assertThat(user1).isNotNull();

        User user2 = new User();
        user2.setName("kobeshow");
        user2.setEmail("tamsil2@naver.com");
        user2.setAge(20);
        try {
            user2 = userRepository.save(user2);
        } catch (DataIntegrityViolationException e) {
        }

        assertThat(userRepository.findById(user2.getId())).isNotNull();
    }

    @Test
    @Transactional("productTransactionManager")
    public void whenCreatingProduct_thenCreated() {
        Product product = new Product();
        product.setName("Book");
        product.setId(2);
        product.setPrice(20);
        product = productRepository.save(product);

        assertThat(productRepository.findById(product.getId())).isNotNull();
    }
}
