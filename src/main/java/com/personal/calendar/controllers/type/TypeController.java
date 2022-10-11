package com.personal.calendar.controllers.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.personal.calendar.controllers.type.requests.TypePagingRequest;
import com.personal.calendar.dao.TypeMapper;
import com.personal.calendar.dto.datatables.DatatablesRequest;
import com.personal.calendar.dto.datatables.PagingResponse;
import com.personal.calendar.dto.Type;
import com.personal.calendar.services.TypeService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class TypeController {

	private final TypeService typeService;

	private final TypeMapper typeMapper;

	@Autowired
	public TypeController(TypeService typeService, TypeMapper typeMapper) {
		this.typeService = typeService;
		this.typeMapper = typeMapper;
	}

	@GetMapping("/types")
	public List<Type> getTypes() {
		return typeService.getTypes();
	}

	@PostMapping(value="/types/paged")
	public PagingResponse<Type> pagedList(@RequestBody DatatablesRequest request) {
		PagingResponse<Type> pagingResponse = new PagingResponse<>();

		//TypePagingRequest pagingRequest = (TypePagingRequest) request;

		DatatablesRequest pagingRequest = request;

	    Map<String, Object> params = new HashMap<>();

        params.put("order", request.getOrder());

        params.put("search", null);
        if (pagingRequest.getSearch() != null && pagingRequest.getSearch().getValue() != null && !pagingRequest.getSearch().getValue().equalsIgnoreCase("")) {
            params.put("search", pagingRequest.getSearch().getValue());
        }

        params.put("start", pagingRequest.getStart());
        params.put("length", pagingRequest.getLength());

        CompletableFuture<List<Type>> list = CompletableFuture.supplyAsync(() -> typeMapper.pagedList(params));
        CompletableFuture<Integer> count = CompletableFuture.supplyAsync(() -> typeMapper.countType(params));

        try {
            CompletableFuture.allOf(list, count).get();

            pagingResponse.setData(list.get());
            pagingResponse.setRecordsTotal(count.get());
            pagingResponse.setRecordsFiltered(list.get().size());
            pagingResponse.setDraw(request.getDraw());

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

		return pagingResponse;
	}

	@GetMapping("/types/{idType}")
	public Type getType(@PathVariable Long idType) {
		return typeService.getType(idType);
	}

	@PostMapping("/types")
	public void insertType(@RequestBody Type type) {
		type.setStatus("A");
		typeService.insertType(type);
	}

	@PutMapping("/types/{idType}")
	public void updateType(@RequestBody Type type, @PathVariable Long idType) {
		type.setIdType(idType);
		typeService.updateType(type);
	}

	@DeleteMapping("/types/{idType}")
	public void deleteType(@PathVariable Long idType) {
		typeService.deleteType(idType);
	}
}