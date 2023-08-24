package sorting.searching.filtering.pagination.service;

import org.springframework.data.domain.Page;

import sorting.searching.filtering.pagination.model.Customers;

public interface CustomerService {
    Page<Customers> pagination(Integer page, Integer pageSize, String sortField, String sortDirection,
            String brand, String model, String address, String search);
}
