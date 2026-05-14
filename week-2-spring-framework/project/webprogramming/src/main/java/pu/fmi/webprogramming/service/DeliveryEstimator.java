package pu.fmi.webprogramming.service;

import org.springframework.stereotype.Component;
import pu.fmi.webprogramming.model.Delivery;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static pu.fmi.webprogramming.model.enums.DeliveryStatusEnum.ASSIGNED;

@Component
public class DeliveryEstimator {

  public LocalDateTime estimateArrivalTime(Delivery delivery) {

    boolean isSameCity = delivery.getWarehouse().getCity().equals(delivery.getCustomer().getCity());
    boolean courierAvailability = delivery.getDeliveryStatus().equals(ASSIGNED);

    LocalDateTime deliveryDay = delivery.getCreatedAt();

    int days = isSameCity ? 1:3;

    if (!courierAvailability) days += 2;

    return deliveryDay.plusDays(days);

    // В Delivery е добавено ново поле LocalDateTime estimatedArrivalAt
    // Този метод се използва в createDelivery на DeliveryService

    // TODO: Довършване на имплементацията за изчисляване на очаквана дата на доставка
    // * Провери дали градовете на склада и клиента съвпадат:
    //   → При съвпадащи градове:
    //     - очаквана дата на доставка трябва да е 1 ден след датата на създаване на доставка
    //   → При различни градове между склада и този на клиента:
    //     - очаквана дата на доставка трябва да е 3 дни след датата на създаване на доставка
    //
    // * Провери и наличността на куриер:
    //   → Ако няма назначен куриер:
    //     - Добави още 2 дни закъснение към вече изчислената дата
    //
    // ВАЖНО:
    // * Всички предоставени Unit тестове (DeliveryServiceTest) трябва да минават успешно
    // * Не променяйте тестовете
    // * Не променяйте сигнатурата на метода
  }
}
