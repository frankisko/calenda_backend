package com.personal.calendar.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.personal.calendar.dto.Type;

@Mapper
public interface TypeMapper {

    List<Type> getTypes();

	List<Type> pagedList(Map request);

	Integer countType(Map request);

    Type getType(Long idType);

    void insertType(Type type);

    void updateType(Type type);

    void deleteType(Long idType);

}
