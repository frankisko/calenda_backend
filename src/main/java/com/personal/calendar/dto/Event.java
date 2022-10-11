package com.personal.calendar.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.ToString;

@JsonInclude(Include.NON_NULL)
@Data
@ToString
public class Event {
    private Long idEvent;

    private String name;

    private String description;

    private Date date;

    private String status;

    private Type type;
}