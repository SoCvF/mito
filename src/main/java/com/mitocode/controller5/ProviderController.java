package com.mitocode.controller5;

import com.mitocode.dto.ProviderDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model1.Provider;
import com.mitocode.service3.IProviderService;
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
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private IProviderService service;

    @Autowired
    @Qualifier("providerMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProviderDTO>> readAll() throws Exception{

        List<ProviderDTO>lst = service.readAll().stream().map(prov -> mapper.map(prov, ProviderDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(lst, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> readById(@PathVariable ("id") Integer id) throws Exception{
        ProviderDTO obj = mapper.map(service.readById(id), ProviderDTO.class);

        if (obj == null){
            throw  new ModelNotFoundException("ID NO ENCONTRADO" + id);
        }
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProviderDTO> create(@Valid @RequestBody ProviderDTO dto) throws Exception {
        Provider obj = service.save(mapper.map(dto,Provider.class));//El mapper transforma de DTO a un Provider ya que el SERVICE espera un provE RGORY y no un ProviderDTO
        return new ResponseEntity<>(mapper.map(obj, ProviderDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProviderDTO>update(@Valid @RequestBody ProviderDTO dto) throws Exception{
        Provider obj = service.update(mapper.map(dto, Provider.class));
        return new ResponseEntity<>(mapper.map(obj, ProviderDTO.class),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")  Integer id) throws Exception {
         service.delete(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }

}
