package com.personal.calendar.controllers.event.requests;

import com.personal.calendar.dto.datatables.DatatablesRequest;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EventPagingRequest extends DatatablesRequest {

    private Long idEvent;

}
