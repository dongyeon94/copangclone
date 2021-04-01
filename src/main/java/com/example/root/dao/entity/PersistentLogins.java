package com.example.root.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "persistent_logins")
@Entity
@Getter @Setter
public class PersistentLogins {
    @Id
    private String          series;
    private String          username;
    private String          token;
    private LocalDateTime   lastUsed;

}
