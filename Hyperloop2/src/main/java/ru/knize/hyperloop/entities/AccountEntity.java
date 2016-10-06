package ru.knize.hyperloop.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by knize on 03.09.16.
 */
@Entity
@Table(name = "Account", schema = "Hyperloop", catalog = "")
public class AccountEntity {
    private int id;
    private String name;
    private String email;
    private Byte verified;
    private Collection<TicketEntity> tickets;

    @Id

    public int getId() {
        return id;
    }

    public void setId(int accountId) {
        this.id = accountId;
    }

    @Basic

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic

    public Byte getVerified() {
        return verified;
    }

    public void setVerified(Byte verified) {
        this.verified = verified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountEntity that = (AccountEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (verified != null ? !verified.equals(that.verified) : that.verified != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (verified != null ? verified.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "account")
    public Collection<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<TicketEntity> ticketsByAccountId) {
        this.tickets = ticketsByAccountId;
    }
}
