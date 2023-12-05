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

//    @GetMapping
//    public List<Provider> readAll() throws Exception{
//        return service.readAll();
//    }

    /*  Se está utilizando el MODELDTO para que esta en un futuro tenga independencia del MODEL ordinario
    *   Se recibe una Lista de ProviderDTO
    *   Se crea una instancia de Lista ProviderDTO el SERVICE recibe un Provider es ahí donde se consigue los get y set de la misma
    *   Despues se crea una funcion donde se unirán estas dos, luego esta al momento de realizar la funcion devolvera una lista
    *   y se guardará en un collect
    * */
    @GetMapping
    public ResponseEntity<List<ProviderDTO>> readAll() throws Exception{
        //Utilizando la libreria MODELMAPPER
        List<ProviderDTO>lst = service.readAll().stream().map(prov -> mapper.map(prov, ProviderDTO.class)).collect(Collectors.toList());

       /*List<ProviderDTO> lst = service.readAll().stream().map( prov -> {
           ProviderDTO ProviderDTO = new ProviderDTO();
            ProviderDTO.setIdProviderDTO(prov.getIdProvider());
            ProviderDTO.setNameProviderDTO(prov.getName());
            ProviderDTO.setDescriptionProviderDTO(prov.getDescription());
            ProviderDTO.setEnabledProviderDTO(prov.isEnabled());
            return ProviderDTO;                             //se retornará el ProviderDTO transformado
        }).collect(Collectors.toList());                    //por ultimo se recolecta con una lista     */
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

    //La anotacion @VALID sirve para que las restricciones que esten asociadas a las clases se respeten "activa la anotacion"
    /*Se está enviando un objeto de tipo ProviderDTO
    Luego se instancia un Provider para almacenarlo dentro de un OBJ y almacenandolo en el dto y mandando un Provider
    despues retornará un mapper llamando al OBJ y almacenandolo en un ProviderDTO*/
    @PostMapping
    public ResponseEntity<ProviderDTO> create(@Valid @RequestBody ProviderDTO dto) throws Exception {
        Provider obj = service.save(mapper.map(dto,Provider.class));//El mapper transforma de DTO a un Provider ya que el SERVICE espera un provE RGORY y no un ProviderDTO
        return new ResponseEntity<>(mapper.map(obj, ProviderDTO.class), HttpStatus.CREATED);
    }

    /*Se está enviando un objeto de tipo ProviderDTO
    Luego se instancia un Provider para almacenarlo dentro de un OBJ y almacenandolo en el dto y mandando un Provider
    despues retornará un mapper llamando al OBJ y almacenandolo en un ProviderDTO*/
    @PutMapping
    public ResponseEntity<ProviderDTO>update(@Valid @RequestBody ProviderDTO dto) throws Exception{
        Provider obj = service.update(mapper.map(dto, Provider.class));
        return new ResponseEntity<>(mapper.map(obj, ProviderDTO.class),HttpStatus.OK);
    }


//    @PutMapping("/{id}")
//    public Provider update(@PathVariable("id") Integer id, @RequestBody Provider Provider) throws Exception {
//        Provider.setIdProvider(id);
//        return service.save(Provider);
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
