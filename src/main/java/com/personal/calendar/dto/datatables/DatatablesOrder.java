package com.personal.calendar.dto.datatables;

public class DatatablesOrder {

    private Integer column;

    private String dir;

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DatatablesOrder{");
        sb.append("column=").append(column);
        sb.append(", dir='").append(dir).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
