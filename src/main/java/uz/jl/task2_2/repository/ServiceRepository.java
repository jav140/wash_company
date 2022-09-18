package uz.jl.task2_2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.jl.task2_2.domains.Service;

import java.util.Set;

@Transactional
public interface ServiceRepository extends JpaRepository<Service,Long> {
    @Query(value = "select b from Service b where b.order.id=:orderId")
    Set<Service> findByOrderId(Long orderId);

    @Query(value = "select s from Service s inner join WashCompany w where w.id=:washCompanyId and s.name like '%:searchName%'")
    Page<Service> findByNameContainingIgnoreCase(@Param("searchName") String searchName,
                                                  Pageable pageable,
                                                 @Param("washCompanyId") Long washCompanyId);
}
