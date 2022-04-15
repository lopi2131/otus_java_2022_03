package homework;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    private final TreeMap<Customer, String> customerService = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        return copy(customerService.firstEntry().getKey(), customerService.firstEntry().getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        try {
            return copy(customerService.higherEntry(customer).getKey(), customerService.higherEntry(customer).getValue());
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Map.Entry<Customer, String> copy(Customer customer, String data) {
        return Map.entry(new Customer(customer.getId(), customer.getName(), customer.getScores()), data);
    }

    public void add(Customer customer, String data) {
        customerService.put(customer, data);
    }
}