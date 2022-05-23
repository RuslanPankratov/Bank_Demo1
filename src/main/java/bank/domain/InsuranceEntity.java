package bank.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.math.BigDecimal;

@Entity(name = "insurance")
@Table(name = "insurance")
@Data
@AllArgsConstructor
@Transactional
@NoArgsConstructor
public class InsuranceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insurance")
    private int idInsurance;
    @Column(name = "sum_insured")
    private BigDecimal sumInsured;
    @Column(name = "insurance_paid")
    private BigDecimal insurancePaid;
    @Column(name = "id_user")
    private Integer idUser;
//    @Column(name = "id_bank_account")
//    private int idBankAccount;

}
