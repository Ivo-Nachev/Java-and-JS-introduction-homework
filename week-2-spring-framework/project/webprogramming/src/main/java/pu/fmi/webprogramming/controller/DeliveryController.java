package pu.fmi.webprogramming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pu.fmi.webprogramming.exception.DeliveryCustomException;
import pu.fmi.webprogramming.model.Customer;
import pu.fmi.webprogramming.model.Delivery;
import pu.fmi.webprogramming.service.DeliveryServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DeliveryController {

    private final DeliveryServiceInterface deliveryService;
    private List<Customer> customers = new ArrayList<>();

    public DeliveryController(DeliveryServiceInterface deliveryService) {
        this.deliveryService = deliveryService;
        Customer customer1 = new Customer(1L, "ivan", "ivanov", "ivan.ivanov", "00", "Plovdiv");
        Customer customer2 = new Customer(2L, "george", "ivanov", "ge.ivanov", "01", "Sofia");
        customers.add(customer1);
        customers.add(customer2);
    }

    @RequestMapping(value = "/createDelivery", method = RequestMethod.GET)
    public String getCreatedDeliveryPage (Model model) {
        model.addAttribute("customers", customers);
        return "create-delivery.html";
    }

    @PostMapping ("/createDelivery")
    public String createDelivery (@RequestParam Long customerId, Model model) {
       Customer customerFound = customers.stream()
               .filter(customer -> customer.getId().equals(customerId))
               .findFirst().orElse(null);

        if (customerFound == null) {
            throw new DeliveryCustomException("Customer with id: " + customerId + "not found");
        }

        Delivery delivery = deliveryService.createDelivery(customerFound);
        model.addAttribute("delivery", delivery);

        return "delivery-created.html";
    }

    @GetMapping("/getDeliveries")
    public ModelAndView getDeliveries () {
        ModelAndView modelAndView = new ModelAndView("deliveries");
        List<Delivery> deliveries = deliveryService.getAllDeliveries();
        modelAndView.addObject("deliveries" ,deliveries);
        return modelAndView;
    }
}
