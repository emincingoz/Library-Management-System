package com.emincingoz.librarymanagement.domain.models;

import com.emincingoz.librarymanagement.domain.enums.UserRolesEnum;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "lms_authority")
public class Authority implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 50)
    @Id
    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private UserRolesEnum name;
}
