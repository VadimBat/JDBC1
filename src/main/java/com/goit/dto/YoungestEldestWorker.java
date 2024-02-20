package com.goit.dto;

import java.sql.Date;

public class YoungestEldestWorker {
    private final String name;
    private final Date birthday;

    public YoungestEldestWorker(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }
}
