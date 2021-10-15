package nl.simpliphi.indexingservice.customer;

import io.github.alikelleci.easysourcing.messages.snapshots.annotations.HandleSnapshot;
import nl.simpliphi.shopdomain.customer.Customer;
import nl.simpliphi.shopprojections.customer.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerProjector {

    private final CustomerRepository customerRepository;

    public CustomerProjector(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @HandleSnapshot
    public void handle(Customer snapshot) {
        //project it to elasticsearch
        customerRepository.save(CustomerDto.builder()
                .id(snapshot.getId())
                .firstName(snapshot.getFirstName())
                .lastName(snapshot.getLastName())
                .address(snapshot.getAddress())
                .build());
    }

}
