package com.bob.springboot.repository;

import com.bob.springboot.bean.Book;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookRepository extends ElasticsearchRepository<Book, Integer>{
  public List<Book> findByBookNameLike(String bookName);
}
