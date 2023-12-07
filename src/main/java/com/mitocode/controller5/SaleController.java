package com.mitocode.controller5;

import com.mitocode.dto.SaleDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model1.Sale;
import com.mitocode.service3.ISaleService;
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
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private ISaleService service;

    @Autowired
    @Qualifier("saleMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> readAll() throws Exception{

        List<SaleDTO>lst = service.readAll().stream().map(sal -> mapper.map(sal, SaleDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(lst, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> readById(@PathVariable ("id") Integer id) throws Exception{
        SaleDTO obj = mapper.map(service.readById(id), SaleDTO.class);

        if (obj == null){
            throw  new ModelNotFoundException("ID NO ENCONTRADO" + id);
        }
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SaleDTO> create(@Valid @RequestBody SaleDTO dto) throws Exception {
        Sale obj = service.save(mapper.map(dto,Sale.class));
        return new ResponseEntity<>(mapper.map(obj, SaleDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SaleDTO>update(@Valid @RequestBody SaleDTO dto) throws Exception{
        Sale obj = service.update(mapper.map(dto, Sale.class));
        return new ResponseEntity<>(mapper.map(obj, SaleDTO.class),HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")  Integer id) throws Exception {
         service.delete(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }

}
