package ru.knize.hyperloop.entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by knize on 28.08.16.
 */
@Entity
@Table(name = "Person", schema = "Hyperloop", catalog = "")
public class PersonEntity {
    private int personId;
    private Integer idRange;
    private Integer idNumber;
    private String name;
    private Date burthdate;

    @Id
    @Column(name = "Person_ID")
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "ID_Range")
    public Integer getIdRange() {
        return idRange;
    }

    public void setIdRange(Integer idRange) {
        this.idRange = idRange;
    }

    @Basic
    @Column(name = "ID_Number")
    public Integer getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Burthdate")
    public Date getBurthdate() {
        return burthdate;
    }

    public void setBurthdate(Date burthdate) {
        this.burthdate = burthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity that = (PersonEntity) o;

        if (personId != that.personId) return false;
        if (idRange != null ? !idRange.equals(that.idRange) : that.idRange != null) return false;
        if (idNumber != null ? !idNumber.equals(that.idNumber) : that.idNumber != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (burthdate != null ? !burthdate.equals(that.burthdate) : that.burthdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personId;
        result = 31 * result + (idRange != null ? idRange.hashCode() : 0);
        result = 31 * result + (idNumber != null ? idNumber.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (burthdate != null ? burthdate.hashCode() : 0);
        return result;
    }
}