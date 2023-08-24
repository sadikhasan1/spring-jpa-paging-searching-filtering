package sorting.searching.filtering.pagination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sorting.searching.filtering.pagination.model.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Long>, JpaSpecificationExecutor<Customers> {
}