package gift.domain.product;

import gift.dto.ProductRequestDto;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Primary
public class ProductJdbcRepository implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO product(name, price, img_url) VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImgUrl());
    }

    @Override
    public ArrayList<Product> findAll() {
        String sql = "SELECT * FROM product";
        return (ArrayList<Product>) jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int price = rs.getInt("price");
            String imgUrl = rs.getString("img_url");
            return new Product(id, name, price, imgUrl);
        });
    }

    @Override
    public Product findById(Long id) {
        String sql = "SELECT * FROM product where id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            String name = rs.getString("name");
            int price = rs.getInt("price");
            String imgUrl = rs.getString("img_url");
            return new Product(id, name, price, imgUrl);
        });
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM product where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public int update(Long id, ProductRequestDto requestDto) {
        String sql = "UPDATE product set name = ?, price = ?, image_url = ? where id = ?";
        return jdbcTemplate.update(sql, requestDto.getName(), requestDto.getPrice(),
                requestDto.getImgUrl(), id);
    }
}