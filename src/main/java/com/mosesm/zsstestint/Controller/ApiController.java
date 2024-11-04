package com.mosesm.zsstestint.Controller;


import com.mosesm.zsstestint.Models.Book;
import com.mosesm.zsstestint.Models.Category;
import com.mosesm.zsstestint.Models.PaymentDetails;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mosesm.zsstestint.Repo.BookRepo;
import com.mosesm.zsstestint.Repo.CategoryRepo;
import org.springframework.web.client.RestClient;

import java.util.Date;
import java.util.List;


@RestController
public class ApiController {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @GetMapping(value="/")
    public String getPage(){
        return "Welcome";
    }
    @GetMapping(value="books")
    public List<Book> getBooks(){
        return bookRepo.findAll();
    }
    @GetMapping(value="categories")
    public List<Category> getCategory(){
        return categoryRepo.findAll();
    }
    @GetMapping(value="/booksincategory/{cat}")
    public List<Book> getBooksInCategory(@PathVariable int cat){
        return bookRepo.findByCategory(cat);
    }
    @PostMapping(value="/addbook")
    public String addBook(@RequestBody Book book){
        bookRepo.save(book);
        return "Book was successfully added";
    }
    @PostMapping(value="/addcategory")
    public String addCategory(@RequestBody Category category){
        categoryRepo.save(category);
        return "Category was successfully added";
    }
    @PutMapping(value="/updatebook/{id}")
    public String updateBook(@PathVariable long id, @RequestBody Book book){
        Book updatedBook = bookRepo.findById(id).get();
        updatedBook.setCategory(book.getCategory());
        updatedBook.setDescript(book.getDescript());
        updatedBook.setTitle(book.getTitle());
        bookRepo.save(updatedBook);
        return "Book has successfully been update";
    }
    @PutMapping(value="/updatecategory/{id}")
    public String updateCategory(@PathVariable long id, @RequestBody Category category){
        Category updatedCategory = categoryRepo.findById(id).get();
        updatedCategory.setTitle(category.getTitle());
        categoryRepo.save(updatedCategory);
        return "Category updated";
    }
    @PostMapping(value="/purchasebook/{id}")
    public String purchaseBook(@PathVariable long id, @RequestBody PaymentDetails paymentData){
        Book desiredBook = bookRepo.findById(id).get();
        JSONObject addData = new JSONObject().put("sampleKey", paymentData.getAdditionalData().getSampleKey());
        JSONObject cardDetails = new JSONObject();
        cardDetails.put("id", paymentData.getCard().getId());
        cardDetails.put("expiry", paymentData.getCard().getExpiry());
        JSONObject paymentDetails = new JSONObject();
        paymentDetails.put("type", "PURCHASE");
        paymentDetails.put("extendedType", "NONE");
        paymentDetails.put("amount", desiredBook.getPrice());
        paymentDetails.put("created", "2024-09-19T11:42:56.472+02:00");
        paymentDetails.put("card", cardDetails);
        paymentDetails.put("reference", paymentData.getReference());
        paymentDetails.put("narration", paymentData.getNarration());
        paymentDetails.put("additionalData", addData);
        RestClient restClient = RestClient.create();
        //String result = restClient.get().uri("http://localhost:8080/api/transaction").retrieve().body(String.class);
        ResponseEntity<Void> response = restClient.post().uri("http://localhost:8000/api/transaction").contentType(MediaType.APPLICATION_JSON).body(paymentDetails.toString()).retrieve().toBodilessEntity();
        System.out.println(response);
        return response.toString();
    }
    @PostMapping(value="/api/transaction")
    public String payBook(@RequestBody PaymentDetails paymentDetails){
        System.out.println(paymentDetails);
        return "Book has been purchases successfully";
    }

    @DeleteMapping(value="/deletebook/{id}")
    public String deleteBook(@PathVariable long id){
        Book deleteBook = bookRepo.findById(id).get();
        bookRepo.delete(deleteBook);
        return "Book has been deleted successfully";
    }
}

