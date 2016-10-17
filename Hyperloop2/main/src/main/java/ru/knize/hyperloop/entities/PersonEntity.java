package ru.knize.hyperloop.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by knize on 03.09.16.
 */
@Entity
@Table(name = "Person", schema = "Hyperloop", catalog = "")
public class PersonEntity {
    private int id;
    private String name;
    private Date birthdate;
    private Collection<TicketEntity> tickets;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int personId) {
        this.id = personId;
    }

    @Basic

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity that = (PersonEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (birthdate != null ? !birthdate.equals(that.birthdate) : that.birthdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "person")
    public Collection<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<TicketEntity> ticketsByPersonId) {
        this.tickets = ticketsByPersonId;
    }
}
