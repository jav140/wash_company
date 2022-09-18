package uz.jl.task2_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.jl.task2_2.domains.OrderTable;
import uz.jl.task2_2.domains.WashCompany;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface WashCompanyRepository extends JpaRepository<WashCompany, Long> {


//    @Query(value = "select b from Board b inner join b.authUsers u where b.id=:id and b.isDeleted=false and  u.id=:userId")

    @Query(value = "select b from WashCompany b inner join b.users u where u.id=:id")
    WashCompany findByAuthUser(Long id);

    @Query(value = "select b from WashCompany b inner join b.orders o where o.id=:orderId")
    List<WashCompany> findByOrderId(Long orderId);

    @Query(value = "select b from OrderTable b where b.id in (select t.id from OrderTable t join WashCompany u where u.id =:washId)" +
            "and b.isActive =:isActive and b.date >=:dateFrom and b.date <=:dateTo")
    List<OrderTable> findOrdersById(@Param("washId") Long washCompanyId,
                                    @Param("isActive") boolean isActive,
                                    @Param("dateFrom") LocalDateTime dateFrom,
                                    @Param("dateTo") LocalDateTime dateTo);


    @Query(value = "select b from OrderTable b where b.id in " +
            "(select t.id from OrderTable t join WashCompany " +
            "u where u.id=:washCompanyId) and b.date between :dateFrom and :dateTo")
    List<OrderTable> getValidOrders(@Param("washCompanyId") Long washCompanyId,
                                    @Param("dateFrom") LocalDateTime dateFrom,
                                    @Param("dateTo") LocalDateTime dateTo);


    @Query(value = "select b.id from WashCompany b where b.id in " +
            "(select t.id from WashCompany t join AuthUser u where u.id=:id)")
    List<Long> findAllWashCompaniesByAuthUserId(Long id);
}
