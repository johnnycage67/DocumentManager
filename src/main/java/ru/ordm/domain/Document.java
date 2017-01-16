package ru.ordm.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.ordm.utils.JsonDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "doc_hdrs")
public class Document {

    @Id
   // @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ndoc")
    private String ndoc;

    @Column(name = "c1")
    private String c1;

    @Column(name = "c2")
    private String c2;

    @Column(name = "c3")
    private String c3;

    @Column(name = "dat")
    private LocalDate localDate;

    //Тип документа
    @Column(name = "tp")
    private String tp;

    @ManyToOne
    @JoinColumn(name = "owner")
   // @Column(name = "owner")
    private SysUser owner;



    public Document(){
    }

    public Document(Long id, String ndoc, String c1, String c2, String c3, LocalDate localDate, String tp, SysUser owner) {
        this.id = id;
        this.ndoc = ndoc;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.localDate = localDate;
        this.tp = tp;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public SysUser getOwner() {
        return owner;
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getTp() {
        return tp;
    }

    public String getNdoc() {
        return ndoc;
    }

    public String getC1() {
        return c1;
    }

    public String getC2() {
        return c2;
    }

    public String getC3() {
        return c3;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public void setNdoc(String ndoc) {
        this.ndoc = ndoc;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOwner(SysUser info) {
        this.owner = info;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }



}
