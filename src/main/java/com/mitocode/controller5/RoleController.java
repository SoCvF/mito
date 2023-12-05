package com.mitocode.controller5;

import com.mitocode.dto.RoleDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model1.Role;
import com.mitocode.service3.IRolService;
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
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRolService service;

    @Autowired
    @Qualifier("rolMapper")
    private ModelMapper mapper;

//    @GetMapping
//    public List<Role> readAll() throws Exception{
//        return service.readAll();
//    }

    /*  Se está utilizando el MODELDTO para que esta en un futuro tenga independencia del MODEL ordinario
    *   Se recibe una Lista de RoleDTO
    *   Se crea una instancia de Lista RoleDTO el SERVICE recibe un Role es ahí donde se consigue los get y set de la misma
    *   Despues se crea una funcion donde se unirán estas dos, luego esta al momento de realizar la funcion devolvera una lista
    *   y se guardará en un collect
    * */
    @GetMapping
    public ResponseEntity<List<RoleDTO>> readAll() throws Exception{
        //Utilizando la libreria MODELMAPPER
        List<RoleDTO>lst = service.readAll().stream().map(rol -> mapper.map(rol, RoleDTO.class)).collect(Collectors.toList());

       /*List<RoleDTO> lst = service.readAll().stream().map( rol -> {
           RoleDTO RoleDTO = new RoleDTO();
            RoleDTO.setIdRoleDTO(rol.getIdRole());
            RoleDTO.setNameRoleDTO(rol.getName());
            RoleDTO.setDescriptionRoleDTO(rol.getDescription());
            RoleDTO.setEnabledRoleDTO(rol.isEnabled());
            return RoleDTO;                             //se retornará el RoleDTO transformado
        }).collect(Collectors.toList());                    //por ultimo se recolecta con una lista     */
        return new ResponseEntity<>(lst, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> readById(@PathVariable ("id") Integer id) throws Exception{
        RoleDTO obj = mapper.map(service.readById(id), RoleDTO.class);

        if (obj == null){
            throw  new ModelNotFoundException("ID NO ENCONTRADO" + id);
        }
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    //La anotacion @VALID sirve para que las restricciones que esten asociadas a las clases se respeten "activa la anotacion"
    /*Se está enviando un objeto de tipo RoleDTO
    Luego se instancia un Role para almacenarlo dentro de un OBJ y almacenandolo en el dto y mandando un Role
    despues retornará un mapper llamando al OBJ y almacenandolo en un RoleDTO*/
    @PostMapping
    public ResponseEntity<RoleDTO> create(@Valid @RequestBody RoleDTO dto) throws Exception {
        Role obj = service.save(mapper.map(dto,Role.class));//El mapper transforma de DTO a un Role ya que el SERVICE espera un rolE RGORY y no un RoleDTO
        return new ResponseEntity<>(mapper.map(obj, RoleDTO.class), HttpStatus.CREATED);
    }

    /*Se está enviando un objeto de tipo RoleDTO
    Luego se instancia un Role para almacenarlo dentro de un OBJ y almacenandolo en el dto y mandando un Role
    despues retornará un mapper llamando al OBJ y almacenandolo en un RoleDTO*/
    @PutMapping
    public ResponseEntity<RoleDTO>update(@Valid @RequestBody RoleDTO dto) throws Exception{
        Role obj = service.update(mapper.map(dto, Role.class));
        return new ResponseEntity<>(mapper.map(obj, RoleDTO.class),HttpStatus.OK);
    }


//    @PutMapping("/{id}")
//    public Role update(@PathVariable("id") Integer id, @RequestBody Role Role) throws Exception {
//        Role.setIdRole(id);
//        return service.save(Role);
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
