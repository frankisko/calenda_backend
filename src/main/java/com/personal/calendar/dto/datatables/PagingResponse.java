package com.personal.calendar.dto.datatables;

import java.util.List;

public class PagingResponse<T> {

    private int draw = 0;

    private int recordsTotal;

    private int recordsFiltered;

    private List<T> data;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {


        return recordsTotal;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PagingResponse{");
        sb.append("draw=").append(draw);
        sb.append(", recordsTotal=").append(recordsTotal);
        sb.append(", recordsFiltered=").append(recordsFiltered);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
