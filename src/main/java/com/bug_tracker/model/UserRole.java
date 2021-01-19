package com.bug_tracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users_roles")
@Data
public class UserRole {

    @Id
    @SequenceGenerator(name = "userSequence", sequenceName = "users_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
    private long id;
    
    @Column(name="role")
    private String roleName;

    @Override
    public String toString() {
        return "UserRole [id=" + id + ", role=" + roleName + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserRole other = (UserRole) obj;
        if (id != other.id)
            return false;
        if (roleName == null) {
            if (other.roleName != null)
                return false;
        } else if (!roleName.equals(other.roleName))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
        return result;
    }
    
    
}
