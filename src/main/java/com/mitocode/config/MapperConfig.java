package com.mitocode.config;

import com.mitocode.dto.ProductDTO;
import com.mitocode.model1.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("productMapper")
    public ModelMapper modelMapper(){
       ModelMapper mapper = new ModelMapper();
        TypeMap<ProductDTO, Product> typeMap = mapper.createTypeMap(ProductDTO.class,Product.class);
   //     typeMap.addMapping(ProductDTO::getIdCategoria, (dest, v) -> dest.getCategory().setIdCategory((Integer)v));
        typeMap.addMapping(ProductDTO::getIdCategory, (dest, v) -> dest.getCategory().setIdCategory((Integer)v));
       return mapper;
    }

    @Bean("categoryMapper")
     public ModelMapper categoryMapper(){
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }

    @Bean("clientMapper")
    public ModelMapper clientMapper(){
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }

    @Bean("providerMapper")
    public ModelMapper providerMapper() {
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }
    @Bean("userMapper")
    public ModelMapper userMapper(){
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }
    @Bean("saleMapper")
    public ModelMapper saleMapper(){
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }
    @Bean("rolMapper")
    public ModelMapper rolMapper(){
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }

}
