package uz.jl.task2_2.domains;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Changes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer orderId;

    private String username;

    private String previousCarModel;

    private String currentCarModel;

    private String previousCarNumber;

    private String currentCarNumber;

    private String previousServiceName;

    private String currentServiceName;

    private String previousServicePrice;

    private String currentServicePrice;

    private String previousPrice;

    private String currentPrice;

    private Boolean isCancelled;

    private String cancelledReason;


}
