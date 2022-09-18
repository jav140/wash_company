package uz.jl.task2_2.domains;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String carModel;

    @Column(nullable = false)
    private String carNumber;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private int clientNumber;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isActive = true;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isCancelled = false;

    @Builder.Default
    private LocalDateTime date = LocalDateTime.now();


}
