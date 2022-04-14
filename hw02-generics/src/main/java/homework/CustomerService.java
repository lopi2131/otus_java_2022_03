package homework;


import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    TreeMap<Customer, String> customerService = new TreeMap<>(Comparator.comparingLong(Customer::getScores).reversed());

    public Map.Entry<Customer, String> getSmallest() {
        TreeMap<Customer, String> copy = new TreeMap<>(Comparator.comparingLong(Customer::getScores).reversed());
        Customer customer = customerService.lastEntry().getKey();
        String data = customerService.lastEntry().getValue();
        copy.put(new Customer(customer.getId(), customer.getName(), customer.getScores()), data);
        return copy.firstEntry();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        TreeMap<Customer, String> copy = new TreeMap<>(Comparator.comparingLong(Customer::getScores).reversed());
        try {
            Customer customerCopy = customerService.lowerEntry(customer).getKey();
            String data = customerService.lowerEntry(customer).getValue();
            copy.put(new Customer(customerCopy.getId(), customerCopy.getName(), customerCopy.getScores()), data);
            return copy.firstEntry();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public void add(Customer customer, String data) {
        customerService.put(customer, data);
    }
}