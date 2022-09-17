package cn.edu.hust.graphqlstudy.controller;

import cn.edu.hust.graphqlstudy.bean.Author;
import cn.edu.hust.graphqlstudy.bean.Book;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

/**
 * @author luwenyang
 * @date 2022/9/15 下午 09:33
 */
@Controller
public class BookController {

    @QueryMapping
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.getAuthorId());
    }
}
