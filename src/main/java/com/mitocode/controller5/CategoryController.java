package com.mitocode.controller5;

import  com.mitocode.dto.CategoryDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model1.Category;
import com.mitocode.service3.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService service;

    @Autowired
    private ModelMapper mapper;

//    @GetMapping
//    public List<Category> readAll() throws Exception{
//        return service.readAll();
//    }

    /*  Se está utilizando el MODELDTO para que esta en un futuro tenga independencia del MODEL ordinario
    *   Se recibe una Lista de CATEGORYDTO
    *   Se crea una instancia de Lista CategoryDTO el SERVICE recibe un CATEGORY es ahí donde se consigue los get y set de la misma
    *   Despues se crea una funcion donde se unirán estas dos, luego esta al momento de realizar la funcion devolvera una lista
    *   y se guardará en un collect
    * */
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> readAll() throws Exception{
        //Utilizando la libreria MODELMAPPER
        List<CategoryDTO>lst = service.readAll().stream().map(cat -> mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());

       /*List<CategoryDTO> lst = service.readAll().stream().map( cat -> {
           CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setIdCategoryDTO(cat.getIdCategory());
            categoryDTO.setNameCategoryDTO(cat.getName());
            categoryDTO.setDescriptionCategoryDTO(cat.getDescription());
            categoryDTO.setEnabledCategoryDTO(cat.isEnabled());
            return categoryDTO;                             //se retornará el CATEGORYDTO transformado
        }).collect(Collectors.toList());                    //por ultimo se recolecta con una lista     */
        return new ResponseEntity<>(lst, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> readById(@PathVariable ("id") Integer id) throws Exception{
        CategoryDTO obj = mapper.map(service.readById(id), CategoryDTO.class);

        if (obj == null){
            throw  new ModelNotFoundException("ID NO ENCONTRADO" + id);
        }
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    //La anotacion @VALID sirve para que las restricciones que esten asociadas a las clases se respeten "activa la anotacion"
    /*Se está enviando un objeto de tipo CATEGORYDTO
    Luego se instancia un CATEGORY para almacenarlo dentro de un OBJ y almacenandolo en el dto y mandando un CATEGORY
    despues retornará un mapper llamando al OBJ y almacenandolo en un CATEGORYDTO*/
    @PostMapping
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO dto) throws Exception {
        Category obj = service.save(mapper.map(dto,Category.class));//El mapper transforma de DTO a un CATEGORY ya que el SERVICE espera un CATE RGORY y no un CATEGORYDTO
        return new ResponseEntity<>(mapper.map(obj, CategoryDTO.class), HttpStatus.CREATED);
    }

    /*Se está enviando un objeto de tipo CATEGORYDTO
    Luego se instancia un CATEGORY para almacenarlo dentro de un OBJ y almacenandolo en el dto y mandando un CATEGORY
    despues retornará un mapper llamando al OBJ y almacenandolo en un CATEGORYDTO*/
    @PutMapping
    public ResponseEntity<CategoryDTO>update(@Valid @RequestBody CategoryDTO dto) throws Exception{
        Category obj = service.update(mapper.map(dto, Category.class));
        return new ResponseEntity<>(mapper.map(obj, CategoryDTO.class),HttpStatus.OK);
    }


//    @PutMapping("/{id}")
//    public Category update(@PathVariable("id") Integer id, @RequestBody Category category) throws Exception {
//        category.setIdCategory(id);
//        return service.save(category);
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
