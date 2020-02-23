package com.bug_tracker.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @SequenceGenerator(name = "userSequence", sequenceName = "users_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role")
    private UserRole role;

    @Column(name = "created_at")
    private LocalDate createdAt;
    
    @Column(name="password")
    private String password;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDate.now();
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", role="
                + role + ", createdAt=" + createdAt + "]";
    }
    
    @JsonIgnore
    public String getPassword() {
        return password;
    }
    
    @JsonProperty
    public void setPassword(String password) {
        this.password=password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

}
