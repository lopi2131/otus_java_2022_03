package homework;


import java.util.ArrayDeque;

public class CustomerReverseOrder {
    ArrayDeque<Customer> customerReverseOrder = new ArrayDeque<>();

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"

    public void add(Customer customer) {
        customerReverseOrder.add(customer);
    }

    public Customer take() {
        return customerReverseOrder.pollLast(); // это "заглушка, чтобы скомилировать"
    }
}
