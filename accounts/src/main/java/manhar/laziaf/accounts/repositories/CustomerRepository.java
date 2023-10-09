package manhar.laziaf.accounts.repositories;

import manhar.laziaf.accounts.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
