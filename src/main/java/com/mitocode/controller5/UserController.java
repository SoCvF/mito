package com.mitocode.controller5;

import com.mitocode.dto.UserDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model1.User;
import com.mitocode.service3.IUserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService service;

    @Autowired
    @Qualifier("userMapper")
    private ModelMapper mapper;

//    @GetMapping
//    public List<User> readAll() throws Exception{
//        return service.readAll();
//    }

    /*  Se está utilizando el MODELDTO para que esta en un futuro tenga independencia del MODEL ordinario
    *   Se recibe una Lista de UserDTO
    *   Se crea una instancia de Lista UserDTO el SERVICE recibe un User es ahí donde se consigue los get y set de la misma
    *   Despues se crea una funcion donde se unirán estas dos, luego esta al momento de realizar la funcion devolvera una lista
    *   y se guardará en un collect
    * */
    @GetMapping
    public ResponseEntity<List<UserDTO>> readAll() throws Exception{
        //Utilizando la libreria MODELMAPPER
        List<UserDTO>lst = service.readAll().stream().map(use -> mapper.map(use, UserDTO.class)).collect(Collectors.toList());

       /*List<UserDTO> lst = service.readAll().stream().map( use -> {
           UserDTO UserDTO = new UserDTO();
            UserDTO.setIdUserDTO(use.getIdUser());
            UserDTO.setNameUserDTO(use.getName());
            UserDTO.setDescriptionUserDTO(use.getDescription());
            UserDTO.setEnabledUserDTO(use.isEnabled());
            return UserDTO;                             //se retornará el UserDTO transformado
        }).collect(Collectors.toList());                    //por ultimo se recolecta con una lista     */
        return new ResponseEntity<>(lst, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> readById(@PathVariable ("id") Integer id) throws Exception{
        UserDTO obj = mapper.map(service.readById(id), UserDTO.class);

        if (obj == null){
            throw  new ModelNotFoundException("ID NO ENCONTRADO" + id);
        }
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    //La anotacion @VALID sirve para que las restricciones que esten asociadas a las clases se respeten "activa la anotacion"
    /*Se está enviando un objeto de tipo UserDTO
    Luego se instancia un User para almacenarlo dentro de un OBJ y almacenandolo en el dto y mandando un User
    despues retornará un mapper llamando al OBJ y almacenandolo en un UserDTO*/
    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO dto) throws Exception {
            User obj = service.save(mapper.map(dto,User.class));//El mapper transforma de DTO a un User ya que el SERVICE espera un useE RGORY y no un UserDTO
        return new ResponseEntity<>(mapper.map(obj, UserDTO.class), HttpStatus.CREATED);
    }

    /*Se está enviando un objeto de tipo UserDTO
    Luego se instancia un User para almacenarlo dentro de un OBJ y almacenandolo en el dto y mandando un User
    despues retornará un mapper llamando al OBJ y almacenandolo en un UserDTO*/
    @PutMapping
    public ResponseEntity<UserDTO>update(@Valid @RequestBody UserDTO dto) throws Exception{
        User obj = service.update(mapper.map(dto, User.class));
        return new ResponseEntity<>(mapper.map(obj, UserDTO.class),HttpStatus.OK);
    }


//    @PutMapping("/{id}")
//    public User update(@PathVariable("id") Integer id, @RequestBody User User) throws Exception {
//        User.setIdUser(id);
//        return service.save(User);
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
