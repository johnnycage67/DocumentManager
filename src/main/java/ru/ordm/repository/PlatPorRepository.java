package ru.ordm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.ordm.domain.PlatPor;


public interface PlatPorRepository extends JpaRepository<PlatPor,Long> {
	
	@Query(value="select t.*,skl.sprop(s) as sprop from v$pl_por_oki_oplat t where t.id=?1",nativeQuery=true)
	PlatPor findByCustomQuery(Long id);
	
}
