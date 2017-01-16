package ru.ordm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ordm.domain.Document;

import java.util.List;


public interface DocumentRepository extends JpaRepository<Document,Long> {

    List<Document> findByOwnerNameAndTpOrderByLocalDateDesc(String userName,String typeDocument);

    List<Document> findByTpOrderByLocalDateDesc(String typeDocument);


    //  @Query("select j from Document j ")
 //   List<Document> findByCustomQuery(String word);

/*
    select DISTINCT name, id from skl.doc_types, SKL.doc_user_actions
    where id=doc and act in ('VIEW', 'OWN') and usr=user
    order by name
*/
}
