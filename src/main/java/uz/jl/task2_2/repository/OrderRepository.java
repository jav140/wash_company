package uz.jl.task2_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import uz.jl.task2_2.domains.OrderTable;

@Transactional
public interface OrderRepository extends JpaRepository<OrderTable, Long> {
}
