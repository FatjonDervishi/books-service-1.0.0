package al.fatjon.bookservice.service;

import al.fatjon.bookservice.model.Author;
import al.fatjon.bookservice.model.Category;
import al.fatjon.bookservice.model.CategoryList;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class HttpService {

    RestTemplate restTemplate = new RestTemplate();

    public Author getAuthorById(Integer id) {
        Author author = null;
        try{
            author = restTemplate.getForObject("http://localhost:9002/public/library/author-service/" + id, Author.class);
        } catch (Exception e) {
            System.out.println(e);
        }
     return author;
    }

    public List<Category> getCategoriesByBook(int id) {
        ResponseEntity<List<Category>> categoriesResponse =
                restTemplate.exchange("http://localhost:9003/public/library/category-service/book/" + id,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Category>>() {
                        });
        return categoriesResponse.getBody();
    }

    public void createCategoryRelation(Integer bookId, Integer categoryId) {
        restTemplate.postForEntity("http://localhost:9003/public/library/category-service/book/"
                                           + bookId
                                           + "?categoryId="
                                           +categoryId, Void.class, Void.class);
    }
}
