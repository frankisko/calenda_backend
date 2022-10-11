package com.personal.calendar.dto.datatables;

public class DatatablesColumn {

    private String data;

    private String name;

    private Boolean searchable;

    private Boolean orderable;

    private DatatablesSearch search;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSearchable() {
        return searchable;
    }

    public void setSearchable(Boolean searchable) {
        this.searchable = searchable;
    }

    public Boolean getOrderable() {
        return orderable;
    }

    public void setOrderable(Boolean orderable) {
        this.orderable = orderable;
    }

    public DatatablesSearch getSearch() {
        return search;
    }

    public void setSearch(DatatablesSearch search) {
        this.search = search;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DatatablesColumn{");
        sb.append("data=").append(data);
        sb.append(", name='").append(name).append('\'');
        sb.append(", searchable=").append(searchable);
        sb.append(", orderable=").append(orderable);
        sb.append(", search=").append(search);
        sb.append('}');
        return sb.toString();
    }
}
