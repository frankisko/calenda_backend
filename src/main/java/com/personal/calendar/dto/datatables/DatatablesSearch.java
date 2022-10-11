package com.personal.calendar.dto.datatables;

public class DatatablesSearch {

    private String value;

    private Boolean regex;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getRegex() {
        return regex;
    }

    public void setRegex(Boolean regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DatatablesSearch{");
        sb.append("value='").append(value).append('\'');
        sb.append(", regex=").append(regex);
        sb.append('}');
        return sb.toString();
    }
}
