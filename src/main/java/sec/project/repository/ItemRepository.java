package sec.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sec.project.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
