package sorting.searching.filtering.pagination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sorting.searching.filtering.pagination.model.Customers;
import sorting.searching.filtering.pagination.service.CustomerService;

@RestController
public class CustomersController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers/{pageNo}")
    public Page<Customers> test(@PathVariable(name = "pageNo") int pageNo,
            @RequestParam(name = "sortField", defaultValue = "id") String sortField,
            @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
            @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "address", defaultValue = "") String address,
            @RequestParam(name = "first_name", defaultValue = "") String firstName,
            @RequestParam(name = "last_name", defaultValue = "") String lastName)
            throws Exception {
        Page<Customers> pagedListingRequest = customerService.pagination(pageNo, pageSize, sortField,
                sortDir, firstName, lastName, address, search);
        return pagedListingRequest;
    }
}
