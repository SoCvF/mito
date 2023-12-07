package com.mitocode.controller5;

import com.mitocode.dto.ProductDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model1.Product;
import com.mitocode.service3.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService service;

    @Autowired
    @Qualifier("productMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> readAll() throws Exception{
        List<ProductDTO>lst = service.readAll().stream().map(prod -> mapper.map(prod, ProductDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(lst, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> readById(@PathVariable ("id") Integer id) throws Exception{
        ProductDTO obj = mapper.map(service.readById(id), ProductDTO.class);

        if (obj == null){
            throw  new ModelNotFoundException("ID NO ENCONTRADO" + id);
        }
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) throws Exception {
        Product obj = service.save(mapper.map(dto,Product.class));//El mapper transforma de DTO a un Product ya que el SERVICE espera un prodE RGORY y no un ProductDTO
        return new ResponseEntity<>(mapper.map(obj, ProductDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductDTO>update(@Valid @RequestBody ProductDTO dto) throws Exception{
        Product obj = service.update(mapper.map(dto, Product.class));
        return new ResponseEntity<>(mapper.map(obj, ProductDTO.class),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")  Integer id) throws Exception {
         service.delete(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }

}
