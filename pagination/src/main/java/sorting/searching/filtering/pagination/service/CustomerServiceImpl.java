package sorting.searching.filtering.pagination.service;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import sorting.searching.filtering.pagination.model.Addresses;
import sorting.searching.filtering.pagination.model.Customers;
import sorting.searching.filtering.pagination.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Customers> pagination(Integer pageNo, Integer pageSize, String sortField, String sortDirection,
            String firstName, String lastName, String address, String search) {

        Specification<Customers> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<Customers, Addresses> customerAddressJoin = root.join("addresses", JoinType.LEFT);

            // example for partial matching
            if (address != null && !address.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(customerAddressJoin.get("fullAddress")),
                        "%" + address.toLowerCase() + "%"));
            }

            // example for equal matching
            if (firstName != null && !firstName.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("firstName"), firstName));
            }

            if (lastName != null && !lastName.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),
                        "%" + lastName.toLowerCase() + "%"));
            }

            if (search != null && !search.isEmpty()) {
                // example for partial searching with a string into multiple columns
                String searchLower = "%" + search.toLowerCase() + "%";
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), searchLower),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), searchLower), criteriaBuilder
                                .like(criteriaBuilder.lower(customerAddressJoin.get("fullAddress")), searchLower)));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        int adjustedPageNo = Math.max(pageNo - 1, 0);
        int adjustedPageSize = Math.max(pageSize, 1);

        Sort sort = Sort.by(sortField);
        if ("desc".equals(sortDirection)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        Pageable pageable = PageRequest.of(adjustedPageNo, adjustedPageSize, sort);
        return customerRepository.findAll(spec, pageable);
    }
}
