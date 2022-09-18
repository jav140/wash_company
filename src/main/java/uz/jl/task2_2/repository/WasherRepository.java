package uz.jl.task2_2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.jl.task2_2.domains.Washer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Transactional
public interface WasherRepository extends JpaRepository<Washer,Long> {

    @Query(value = "select b from Washer b where b.order.id=:orderId")
    Set<Washer> findByOrderId(Long orderId);

    @Query(value = "select w from Washer w inner join WashCompany c where w.name like '%:name%' and c.id=:id")
    Page<Washer>findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable, @Param("id") Long id);

    @Query(value = "select w from Washer w where w.order.date between :dateFrom and :dateTo and w.order.id" +
            " in(select t.id from OrderTable t join WashCompany w where w.id=:companyId)")
    List<Washer> getValidOrders(@Param("companyId") Long washCompanyId,
                                @Param("dateFrom") LocalDateTime dateFrom,
                                @Param("dateTo") LocalDateTime dateTo);
}
