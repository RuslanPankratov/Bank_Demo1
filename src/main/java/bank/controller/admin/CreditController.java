package bank.controller.admin;


import bank.core.service.credit.AddCreditService;
import bank.core.service.credit.FindAllCreditService;
import bank.core.service.credit.GetCreditByIdService;
import bank.core.service.credit.UpdateCreditService;
import bank.dto.credit.add.AddCreditRequest;
import bank.dto.credit.add.AddCreditResponse;
import bank.dto.credit.find.FindAllCreditResponse;
import bank.dto.credit.find.GetByIdCreditResponse;
import bank.dto.credit.update.UpdateCreditRequest;
import bank.dto.credit.update.UpdateCreditResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
public class CreditController {

    private final FindAllCreditService findAllCreditService;
    private final GetCreditByIdService getCreditByIdService;
    private final AddCreditService addCreditService;
    private final UpdateCreditService updateCreditService;

    /*

    Better to use plural words - users/credits/accounts/transactions/cards etc
     - Find all credits
    /credits - could be moved to @RequestMapping("/credits") instead of duplication in every endpoint
     */
    @GetMapping("/credit")
    public FindAllCreditResponse findAllCredits() {
        log.debug("Find all credit received");
        return findAllCreditService.findAll();
    }

    /*
    REST best practices
    credits = list
    credits/1 = credit with id 1 from list
     */
    @GetMapping("/credit/{id}")
    public GetByIdCreditResponse findById(@PathVariable("id") Integer id) {
        log.debug("Find by id request received, id: {}", id);
        return getCreditByIdService.getCreditById(id);
    }

    @PostMapping("/credit")
    public AddCreditResponse addCredit(@RequestBody AddCreditRequest request) {
        log.debug("Add Credit Request: {}", request);
        return addCreditService.add(request);
    }

    @PutMapping("/credit")
    public UpdateCreditResponse updateCredit(@RequestBody UpdateCreditRequest request) {
        log.debug("Update Credit Request: {}", request);
        return updateCreditService.update(request);
    }

}
