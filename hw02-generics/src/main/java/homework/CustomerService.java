package homework;


import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    TreeMap<Customer, String> customerService = new TreeMap<>(Comparator.comparingLong(Customer::getScores).reversed());

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        TreeMap<Customer, String> copy = new TreeMap<>(Comparator.comparingLong(Customer::getScores).reversed());
        copy.put(new Customer(customerService.lastEntry().getKey().getId(),
                        customerService.lastEntry().getKey().getName(),
                        customerService.lastEntry().getKey().getScores()),
                customerService.lastEntry().getValue());
        return copy.firstEntry();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        TreeMap<Customer, String> copy = new TreeMap<>(Comparator.comparingLong(Customer::getScores).reversed());
        try {
            copy.put(new Customer(customerService.lowerEntry(customer).getKey().getId(),
                            customerService.lowerEntry(customer).getKey().getName(),
                            customerService.lowerEntry(customer).getKey().getScores()),
                    customerService.lowerEntry(customer).getValue());
            return copy.firstEntry();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public void add(Customer customer, String data) {
        customerService.put(customer, data);
    }
}
