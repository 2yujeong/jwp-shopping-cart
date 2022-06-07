package woowacourse.shoppingcart.dao;

import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;
import woowacourse.shoppingcart.domain.Cart;
import woowacourse.shoppingcart.domain.Product;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.*;

@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Sql("/cartInitSchema.sql")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class CartItemDaoTest {

    private final CartItemDao cartItemDao;
    private final Product product1;
    private final Product product2;

    public CartItemDaoTest(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        cartItemDao = new CartItemDao(jdbcTemplate, dataSource);

        product1 = new Product(1L, "banana", 1_000, "woowa1.com");
        product2 = new Product(2L, "apple", 2_000, "woowa2.com");
    }

    @DisplayName("카트에 아이템을 담으면, 담긴 카트 아이디를 반환한다.")
    @Test
    void save() {
        // given
        Long customerId = 2L;

        // when
        final Long cartId = cartItemDao.save(customerId, product1.getId(), 10);

        // then
        assertThat(cartId).isEqualTo(3L);
    }

    @DisplayName("Customer Id를 넣으면, 해당 Customer의 장바구니 아이템들을 가져온다.")
    @Test
    void findCartItemsByCustomerId() {
        // given
        Long customerId = 1L;

        // when
        List<Cart> cartItems = cartItemDao.findByCustomerId(customerId);

        // then
        assertThat(cartItems).extracting("id", "quantity")
                        .containsExactly(tuple(1L, 10), tuple(2L, 20));

        List<Product> products = cartItems.stream()
                .map(Cart::getProduct)
                .collect(Collectors.toList());
        assertThat(products).extracting("id", "name")
                .containsExactly(tuple(1L, "banana"), tuple(2L, "apple"));
    }

    @DisplayName("Customer Id와 Product Id를 이용하여 장바구니 데이터 존재 여부를 확인한다.")
    @Test
    void existByCustomerIdAndProductId() {
        // when
        boolean result = cartItemDao.existByCustomerIdAndProductId(1L, 2L);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("Customer Id와 Product Id를 이용하여 장바구니 아이템을 삭제한다.")
    @Test
    void deleteCartItem() {
        // when
        cartItemDao.deleteByCustomerIdAndProductId(1L, 2L);

        // then
        assertThat(cartItemDao.existByCustomerIdAndProductId(1L, 2L)).isFalse();
    }
}
