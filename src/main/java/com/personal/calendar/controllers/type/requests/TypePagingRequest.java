package com.personal.calendar.controllers.type.requests;

import com.personal.calendar.dto.datatables.DatatablesRequest;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TypePagingRequest extends DatatablesRequest {

    private Long idType;

}
