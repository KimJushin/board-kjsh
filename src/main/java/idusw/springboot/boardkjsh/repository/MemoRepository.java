package idusw.springboot.boardkjsh.repository;

import idusw.springboot.boardkjsh.entity.MemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<MemoEntity, Long> {

}
