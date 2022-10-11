package com.personal.calendar.dto.datatables;

import java.util.List;

public class DatatablesRequest {
    
    private Integer start;

    private Integer length;

    private DatatablesSearch search;

    private List<DatatablesOrder> order;

    private Integer draw;

    private List<DatatablesColumn> columns;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public DatatablesSearch getSearch() {
        return search;
    }

    public void setSearch(DatatablesSearch search) {
        this.search = search;
    }

    public List<DatatablesOrder> getOrder() {
        return order;
    }

    public void setOrder(List<DatatablesOrder> order) {
        this.order = order;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public List<DatatablesColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DatatablesColumn> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DatatablesRequest{");
        sb.append("start=").append(start);
        sb.append(", length=").append(length);
        sb.append(", search=").append(search);
        sb.append(", order=").append(order);
        sb.append(", draw=").append(draw);
        sb.append(", columns=").append(columns);
        sb.append('}');
        return sb.toString();
    }
}
