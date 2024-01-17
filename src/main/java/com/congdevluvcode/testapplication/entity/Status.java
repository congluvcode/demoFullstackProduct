package com.congdevluvcode.testapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="status_name")
    private String status_name;

    @OneToMany(mappedBy = "status")
    private List<Product> products;

    public Status(String status_name) {
        this.status_name = status_name;
    }

    @Override
    public String toString() {
        return status_name;
    }
}
