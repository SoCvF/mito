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

//    @GetMapping
//    public List<Product> readAll() throws Exception{
//        return service.readAll();
//    }

    /*  Se está utilizando el MODELDTO para que esta en un futuro tenga independencia del MODEL ordinario
    *   Se recibe una Lista de ProductDTO
    *   Se crea una instancia de Lista ProductDTO el SERVICE recibe un Product es ahí donde se consigue los get y set de la misma
    *   Despues se crea una funcion donde se unirán estas dos, luego esta al momento de realizar la funcion devolvera una lista
    *   y se guardará en un collect
    * */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> readAll() throws Exception{
        //Utilizando la libreria MODELMAPPER
        List<ProductDTO>lst = service.readAll().stream().map(prod -> mapper.map(prod, ProductDTO.class)).collect(Collectors.toList());

       /*List<ProductDTO> lst = service.readAll().stream().map( prod -> {
           ProductDTO ProductDTO = new ProductDTO();
            ProductDTO.setIdProductDTO(prod.getIdProduct());
            ProductDTO.setNameProductDTO(prod.getName());
            ProductDTO.setDescriptionProductDTO(prod.getDescription());
            ProductDTO.setEnabledProductDTO(prod.isEnabled());
            return ProductDTO;                             //se retornará el ProductDTO transformado
        }).collect(Collectors.toList());                    //por ultimo se recolecta con una lista     */
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

    //La anotacion @VALID sirve para que las restricciones que esten asociadas a las clases se respeten "activa la anotacion"
    /*Se está enviando un objeto de tipo ProductDTO
    Luego se instancia un Product para almacenarlo dentro de un OBJ y almacenandolo en el dto y mandando un Product
    despues retornará un mapper llamando al OBJ y almacenandolo en un ProductDTO*/
    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) throws Exception {
        Product obj = service.save(mapper.map(dto,Product.class));//El mapper transforma de DTO a un Product ya que el SERVICE espera un prodE RGORY y no un ProductDTO
        return new ResponseEntity<>(mapper.map(obj, ProductDTO.class), HttpStatus.CREATED);
    }

    /*Se está enviando un objeto de tipo ProductDTO
    Luego se instancia un Product para almacenarlo dentro de un OBJ y almacenandolo en el dto y mandando un Product
    despues retornará un mapper llamando al OBJ y almacenandolo en un ProductDTO*/
    @PutMapping
    public ResponseEntity<ProductDTO>update(@Valid @RequestBody ProductDTO dto) throws Exception{
        Product obj = service.update(mapper.map(dto, Product.class));
        return new ResponseEntity<>(mapper.map(obj, ProductDTO.class),HttpStatus.OK);
    }


//    @PutMapping("/{id}")
//    public Product update(@PathVariable("id") Integer id, @RequestBody Product Product) throws Exception {
//        Product.setIdProduct(id);
//        return service.save(Product);
//    }

    /*
    * Como este no envia un objeto no es necesario usar el metodo mapper ya que solamente se ingresará por
    * el ID
    * */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")  Integer id) throws Exception {
         service.delete(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }

}
