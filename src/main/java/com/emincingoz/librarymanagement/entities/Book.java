package com.emincingoz.librarymanagement.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
@ApiModel(value = "My Model Book", description = "description")
public class Book {

    @Id
    @ApiModelProperty(value = "id")
    private Long id;
    @Column(name = "isbn")
    private String ISBN;
    @ApiModelProperty(value = "title")
    private String title;
    private String subject;
    private String publisher;
    private String language;
    @Column(name = "number_of_pages")
    private Long numberOfPages;
}
