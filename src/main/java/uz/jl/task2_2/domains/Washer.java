package uz.jl.task2_2.domains;

import lombok.*;

import javax.persistence.*;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Washer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int telephoneNumber;

    @Column(nullable = false)
    private int stake;

    @Column(nullable = true)
    private Byte image;

    @Column(nullable = false)
    private Boolean isActive;

    @ManyToOne
    private OrderTable order;


}
