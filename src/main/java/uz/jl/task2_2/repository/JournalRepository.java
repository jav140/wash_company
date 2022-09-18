package uz.jl.task2_2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.jl.task2_2.domains.Journal;

@Transactional
public interface JournalRepository extends JpaRepository<Journal,Long> {


    @Query(value = "select w from Journal w inner join WashCompany c where c.id=:id")
    Page<Journal> findByCompanyId(@Param("id") Long washCompanyId, Pageable pageable);
}
