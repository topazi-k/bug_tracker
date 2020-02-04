package com.bug_tracker.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "projects")
@Data
public class Project {

    @Id
    @SequenceGenerator(name = "projectSequence", sequenceName = "projects_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projectSequence")
    private long id;

    @Column(name = "name")
    private String projectName;

    @Column(name = "descript")
    private String descripton;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "projects_users", joinColumns = @JoinColumn(name = "project_id"), 
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> projectMembers;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private List<Ticket> tickets;

    @Override
    public String toString() {
        return "Project [id=" + id + ", projectName=" + projectName + ", descripton=" + descripton + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Project other = (Project) obj;
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
