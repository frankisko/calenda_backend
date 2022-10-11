package com.personal.calendar.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.personal.calendar.dao.TypeMapper;
import com.personal.calendar.dto.Type;

@Service
public class TypeService {
    private final TypeMapper typeMapper;

    public TypeService(TypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

    public List<Type> getTypes() {
        return typeMapper.getTypes();
    }

    public Type getType(Long idType) {
        return typeMapper.getType(idType);
    }

    public void insertType(Type type) {
        typeMapper.insertType(type);
    }

    public void updateType(Type type) {
        typeMapper.updateType(type);
    }

    public void deleteType(Long idType) {
        typeMapper.deleteType(idType);
    }
}
