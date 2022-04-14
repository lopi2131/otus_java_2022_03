package homework;


import java.util.ArrayDeque;

public class CustomerReverseOrder {
    private final ArrayDeque<Customer> customerReverseOrder = new ArrayDeque<>();

    public void add(Customer customer) {
        customerReverseOrder.add(customer);
    }

    public Customer take() {
        return customerReverseOrder.pollLast();
    }
}
