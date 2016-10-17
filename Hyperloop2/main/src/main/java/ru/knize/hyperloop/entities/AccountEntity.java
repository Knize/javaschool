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
    private String username;
    private String password;
    private String email;
    private Byte verified;
    private Collection<TicketEntity> tickets;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int accountId) {
        this.id = accountId;
    }

    @Basic

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
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
        if (!username.equals(that.username)) return false;
        if (!password.equals(that.password)) return false;
        if (!email.equals(that.email)) return false;
        if (!verified.equals(that.verified)) return false;
        return tickets.equals(that.tickets);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + verified.hashCode();
        result = 31 * result + tickets.hashCode();
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
