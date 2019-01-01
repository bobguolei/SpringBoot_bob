package com.bob.springboot;

import com.bob.springboot.bean.Article;
import com.bob.springboot.bean.Book;
import com.bob.springboot.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Index.Builder;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot03ElasticApplicationTests {

	@Autowired
	JestClient jestClient;

	@Autowired
  BookRepository bookRepository;

	@Test
  public void test01() {
//    Book book = new Book();
//    book.setId(1);
//    book.setBookName("Hello");
//    book.setAuthor("Bob");
//    bookRepository.index(book);
    List<Book> list = bookRepository.findByBookNameLike("Hel");
    for (Book book : list) {
      System.out.println(book);
    }
  }

	@Test
	public void contextLoads() {
		Article article = new Article();
		article.setId(1);
		article.setAuthor("Bob");
		article.setTitle("好消息");
		article.setContent("Hello world!");

		Index index = new Builder(article).index("bob").type("news").build();

		try {
			jestClient.execute(index);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void search() {
    String json = "{\n    \"query\" : {\n        \"match\" : {\n            \"content\" : \"hello\"\n        }\n    }\n}";
    Search search = new Search.Builder(json).addIndex("bob").addType("news").build();

    try {
      SearchResult execute = jestClient.execute(search);
      System.out.println(execute.getJsonString());
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}

