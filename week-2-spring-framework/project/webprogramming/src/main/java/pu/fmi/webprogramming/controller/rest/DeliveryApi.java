package pu.fmi.webprogramming.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pu.fmi.webprogramming.controller.DeliveryController;
import pu.fmi.webprogramming.exception.ErrorDTO;
import pu.fmi.webprogramming.model.Delivery;
import pu.fmi.webprogramming.service.DeliveryServiceInterface;

import java.util.List;

@Controller
@RequestMapping("/api/deliveries")
public class DeliveryApi {
    private final DeliveryServiceInterface deliveryServiceInterface;

    public DeliveryApi(DeliveryServiceInterface deliveryServiceInterface) {
        this.deliveryServiceInterface = deliveryServiceInterface;
    }

    public List<Delivery> getAllDeliveries () {
        List<Delivery> deliveries = deliveryServiceInterface.getAllDeliveries();
        return deliveries;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Delivery getDeliveryById (@PathVariable Long deliveryId) {
        Delivery deliveryFound = deliveryServiceInterface.getAllDeliveries().stream().findFirst().orElse(null);

        if (deliveryFound == null) {
            ErrorDTO errorDTO = new ErrorDTO(404, "Delivery with id" + deliveryId + "is not found!", "");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }

        return deliveryFound;
    }
}
