package bank.controller.admin;


import bank.core.service.creditCard.AddCreditCardService;
import bank.core.service.creditCard.FindAllCreditCardService;
import bank.core.service.creditCard.GetCreditCardByIdService;
import bank.core.service.creditCard.UpdateCreditCardService;
import bank.core.service.credit.dto.creditCard.add.AddCreditCardRequest;
import bank.core.service.credit.dto.creditCard.add.AddCreditCardResponse;
import bank.core.service.credit.dto.creditCard.find.FindAllCreditCardResponse;
import bank.core.service.credit.dto.creditCard.find.GetByIdCreditCardResponse;
import bank.core.service.credit.dto.creditCard.update.UpdateCreditCardRequest;
import bank.core.service.credit.dto.creditCard.update.UpdateCreditCardResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/creditCards")
@AllArgsConstructor
public class CreditCardController {

    private FindAllCreditCardService findAllCreditCardService;
    private GetCreditCardByIdService getCreditCardByIdService;
    private AddCreditCardService addCreditCardService;
    private UpdateCreditCardService updateCreditCardService;

    @GetMapping()
    public FindAllCreditCardResponse findAllCreditCard() {
        log.debug("Find All Credit Card received");
        return findAllCreditCardService.findAll();
    }

    @GetMapping("/{id}")
    public GetByIdCreditCardResponse findById(@PathVariable("id") Integer id) {
        log.debug("Find By Id Request Received, id: {}", id);
        return getCreditCardByIdService.getCreditCardById(id);
    }

    @PostMapping()
    public AddCreditCardResponse create(@RequestBody @Valid AddCreditCardRequest request) {
        log.debug("Received Add Credit Card request: {}", request);
        return addCreditCardService.add(request);
    }

    @PutMapping()
    public UpdateCreditCardResponse updateCreditCard(@RequestBody @Valid UpdateCreditCardRequest request) {
        log.debug("Received update Credit Card request: {}", request);
        return updateCreditCardService.update(request);
    }

}
