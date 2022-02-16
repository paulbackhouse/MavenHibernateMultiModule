package org.comtravo.travel.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class BaseService {

    protected <TSource, TDestination> TDestination MapTo(TSource source, Class<TDestination> dest) {
        ModelMapper modelMapper = new ModelMapper();
        var result = modelMapper.map(source, dest);
        return result;
    }

    protected <TSource, TDestination> List<TDestination> MapToList(List<TSource> source, Class<TDestination> dest) {
        ModelMapper modelMapper = new ModelMapper();
        var results = source
                        .stream()
                        .map(item ->  modelMapper.map(item, dest))
                        .collect(Collectors.toList());
        
       return results;
    }

    
}
