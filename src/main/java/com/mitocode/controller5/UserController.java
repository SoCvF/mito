package com.mitocode.controller5;

import com.mitocode.dto.ClientDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model1.Client;
import com.mitocode.service3.IClientService;
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
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private IClientService service;

    @Autowired
    @Qualifier("clientMapper")
    private ModelMapper mapper;

//    @GetMapping
//    public List<Client> readAll() throws Exception{
//        return service.readAll();
//    }

    /*  Se está utilizando el MODELDTO para que esta en un futuro tenga independencia del MODEL ordinario
    *   Se recibe una Lista de ClientDTO
    *   Se crea una instancia de Lista ClientDTO el SERVICE recibe un Client es ahí donde se consigue los get y set de la misma
    *   Despues se crea una funcion donde se unirán estas dos, luego esta al momento de realizar la funcion devolvera una lista
    *   y se guardará en un collect
    * */
    @GetMapping
    public ResponseEntity<List<ClientDTO>> readAll() throws Exception{
        //Utilizando la libreria MODELMAPPER
        List<ClientDTO>lst = service.readAll().stream().map(cat -> mapper.map(cat, ClientDTO.class)).collect(Collectors.toList());

       /*List<ClientDTO> lst = service.readAll().stream().map( cat -> {
           ClientDTO ClientDTO = new ClientDTO();
            ClientDTO.setIdClientDTO(cat.getIdClient());
            ClientDTO.setNameClientDTO(cat.getName());
            ClientDTO.setDescriptionClientDTO(cat.getDescription());
            ClientDTO.setEnabledClientDTO(cat.isEnabled());
            return ClientDTO;                             //se retornará el ClientDTO transformado
        }).collect(Collectors.toList());                    //por ultimo se recolecta con una lista     */
        return new ResponseEntity<>(lst, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> readById(@PathVariable ("id") Integer id) throws Exception{
        ClientDTO obj = mapper.map(service.readById(id), ClientDTO.class);

        if (obj == null){
            throw  new ModelNotFoundException("ID NO ENCONTRADO" + id);
        }
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    //La anotacion @VALID sirve para que las restricciones que esten asociadas a las clases se respeten "activa la anotacion"
    /*Se está enviando un objeto de tipo ClientDTO
    Luego se instancia un Client para almacenarlo dentro de un OBJ y almacenandolo en el dto y mandando un Client
    despues retornará un mapper llamando al OBJ y almacenandolo en un ClientDTO*/
    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO dto) throws Exception {
        Client obj = service.save(mapper.map(dto,Client.class));//El mapper transforma de DTO a un Client ya que el SERVICE espera un CATE RGORY y no un ClientDTO
        return new ResponseEntity<>(mapper.map(obj, ClientDTO.class), HttpStatus.CREATED);
    }

    /*Se está enviando un objeto de tipo ClientDTO
    Luego se instancia un Client para almacenarlo dentro de un OBJ y almacenandolo en el dto y mandando un Client
    despues retornará un mapper llamando al OBJ y almacenandolo en un ClientDTO*/
    @PutMapping
    public ResponseEntity<ClientDTO>update(@Valid @RequestBody ClientDTO dto) throws Exception{
        Client obj = service.update(mapper.map(dto, Client.class));
        return new ResponseEntity<>(mapper.map(obj, ClientDTO.class),HttpStatus.OK);
    }


//    @PutMapping("/{id}")
//    public Client update(@PathVariable("id") Integer id, @RequestBody Client Client) throws Exception {
//        Client.setIdClient(id);
//        return service.save(Client);
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
