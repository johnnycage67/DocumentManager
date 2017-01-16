package ru.ordm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_users")
public class SysUser {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "fio")
    private String fio;

    public SysUser() {
    }

    public SysUser(String name, String fio) {
        this.name = name;
        this.fio = fio;
    }

    public String getName() {
        return name;
    }

    public String getFio() {
        return fio;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }


}