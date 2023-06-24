package com.example.demo.controllers;

import com.example.demo.entity.ProductDetailEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.models.ResponseObject;
import com.example.demo.repository.ProductDetailRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.util.ExcelGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(path = "/api/products")
public class ProductControllers {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDetailRepository productDetailRepository;
    @Autowired
    ProductService productService;

    @GetMapping("/test")
    @ResponseBody
    public String homePage() {
        return "index";
    }
    @GetMapping("")
    public @ResponseBody
    ResponseEntity<ResponseObject> getAllProducts(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "12") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Get all products successfully [Hello World]!!", productService.getAllProducts(pageable)));
    }

//    @GetMapping("/{id}")
//    ResponseEntity<ResponseObject> getProductById(@PathVariable int id) {
//        ProductEntity product = productRepository.findById(id).orElse(null);
//        if (product != null) {
//            ProductDetailEntity productDetail = productDetailRepository.findById(id).orElse(null);
//            product.setProductDetail(productDetail);
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("OK", "Get product successfully", product)
//            );
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("Failure", "Cannot find product with id = " + id, "")
//            );
//        }
////        return productRepository.findById(id);
//    }

//    @PutMapping("/{id}")
//    ResponseEntity<ResponseObject> updateProductById(@PathVariable int id, @RequestBody ProductEntity productEntity) {
//        System.out.println(productEntity);
//        System.out.println(productEntity.getProductDetail());
//        ProductEntity product = productRepository.findById(id).orElse(null);
//        if (product != null) {
//            product.setProductLine(productEntity.getProductLine());
//            product.setProductName(productEntity.getProductName());
//            product.setBuyPrice(productEntity.getBuyPrice());
//            product.setStatus(productEntity.getStatus());
//            product.setWarrantyPeriod(productEntity.getWarrantyPeriod());
//            product.setCreateAt(productEntity.getCreateAt());
//
//            ProductDetailEntity productDetail = product.getProductDetail();
//            ProductDetailEntity detailEntity = productEntity.getProductDetail();
//            productDetail.setWeight(detailEntity.getWeight());
//            productDetail.setBrakes(detailEntity.getBrakes());
//            productDetail.setFame(detailEntity.getFame());
//            productDetail.setHandlebar(detailEntity.getHandlebar());
//            productDetail.setPedals(detailEntity.getPedals());
//            productDetail.setRims(detailEntity.getRims());
//            productDetail.setSaddle(detailEntity.getSaddle());
//            productDetail.setShock(detailEntity.getShock());
//            productDetail.setTires(detailEntity.getTires());
//            productDetail.setSize(detailEntity.getSize());
//            productRepository.save(product);
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("OK", "Update product successfully", product)
//            );
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("OK", "Cannot find product", "")
//        );
//    }

//    @DeleteMapping("/{id}")
//    ResponseEntity<ResponseObject> deleteProductById(@PathVariable int id) {
//        ProductEntity product = productRepository.findById(id).orElse(null);
//        if (product != null) {
//            productDetailRepository.deleteById(id);
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("OK", "Delete product " + id + " successfully", "")
//            );
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                new ResponseObject("OK", "Cannot find product id =  " + id, "")
//        );
//    }

//    @PostMapping("")
//    ResponseEntity<ResponseObject> createProduct(@RequestBody ProductEntity product) {
//        ProductEntity productEntity = new ProductEntity(product);
//        ProductDetailEntity productDetail = new ProductDetailEntity(product.getProductDetail());
//        productEntity.setProductDetail(productDetail);
//        productRepository.save(productEntity);
//        productDetailRepository.save(productDetail);
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("OK", "Create product successfully", "")
//        );
//    }
    @GetMapping("/export-to-excel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=student" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<ProductEntity> listOfStudents = productService.findAll();
        ExcelGenerator generator = new ExcelGenerator(listOfStudents);
        generator.generateExcelFile(response);
    }

}
