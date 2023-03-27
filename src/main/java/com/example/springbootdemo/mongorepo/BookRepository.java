package com.example.springbootdemo.mongorepo;

import com.example.springbootdemo.documents.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book,String> {
}
