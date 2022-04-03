package al.fatjon.bookservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class BookPojo {
    private String name;
    private String description;
    private String image;
    private Author author;
    private List<Category> categories;
}
