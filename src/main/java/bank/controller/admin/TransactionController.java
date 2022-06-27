package bank.controller.admin;


import bank.core.service.transaction.AddTransactionService;
import bank.dto.transaction.add.AddTransactionRequest;
import bank.dto.transaction.add.AddTransactionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
public class TransactionController {

    private final AddTransactionService addTransactionService;

    @PutMapping("/transactions")
    public AddTransactionResponse transaction(@RequestBody @Valid AddTransactionRequest request) {
        log.debug("Received Add Transaction request: {}", request);
        return addTransactionService.save(request);
    }

}
