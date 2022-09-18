package uz.jl.task2_2.domains;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WashCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private byte avatar;

    @Column(nullable = false)
    private String location;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "washCompany_user",
            joinColumns = @JoinColumn(name = "washCompany_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<AuthUser> users;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "washCompany_order",
            joinColumns = @JoinColumn(name = "washCompany_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id")
    )
    private List<OrderTable> orders;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "washCompany_washer",
            joinColumns = @JoinColumn(name = "washCompany_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "washer_id", referencedColumnName = "id")
    )
    private List<Washer> washers;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "washCompany_service",
            joinColumns = @JoinColumn(name = "washCompany_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id")
    )
    private List<Service> services;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "washCompany_journal",
            joinColumns = @JoinColumn(name = "washCompany_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "journal_id", referencedColumnName = "id")
    )
    private List<Journal> journals;


}
