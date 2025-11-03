package staff.demo.staff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import staff.demo.staff.entity.StaffEntity;

public interface StaffRepository extends JpaRepository<StaffEntity, Long> {

}
