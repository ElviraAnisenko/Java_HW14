import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.AlreadyExistsException;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.ShopRepository;

public class ShopRepositoryTest {
    Product product1 = new Product(1001, "Мяч", 100);
    Product product2 = new Product(1002, "Книга", 200);
    Product product3 = new Product(1003, "Кукла", 500);


    @Test
    public void shouldAddIDNotExistInRepository() {
        ShopRepository repository = new ShopRepository();
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);
        Product[] expected = {product1, product2, product3};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddIDExistInRepository() {
        ShopRepository repository = new ShopRepository();
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);
        Assertions.assertThrows(AlreadyExistsException.class, () ->
                repository.add(product1));

    }


    @Test
    public void shouldDeleteProductWithExistingID() {
        ShopRepository repository = new ShopRepository();
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);
        repository.remove(1002);
        Product[] expected = {product1, product3};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldDeleteProductFromAnEmptyRepository() {
        ShopRepository repository = new ShopRepository();
        Assertions.assertThrows(NotFoundException.class, () ->
                repository.remove(1001));

    }

    @Test
    public void shouldDeleteProductWithNotExistingID() {
        ShopRepository repository = new ShopRepository();
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);
        Assertions.assertThrows(NotFoundException.class, () ->
                repository.remove(1004));

    }

}
