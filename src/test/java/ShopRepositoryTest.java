import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    Product item1 = new Product(1, "хлеб", 40);
    Product item2 = new Product(2, "булка", 30);
    Product item3 = new Product(3, "картошка", 20);

    ShopRepository repo = new ShopRepository();

    @BeforeEach
    public void setup() {
        repo.add(item1);
        repo.add(item2);
    }

    @Test
    public void shouldAddItem3() {
        repo.add(item3);
        Product[] expected = {item1, item2, item3};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldNotAddItem3OneMoreTime() {
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(item2);
        });
    }

    @Test
    public void shouldRemoveById() {
        repo.removeById(2);
        Product[] expected = {item1};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGenerateException() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(5);
        });
    }
}
