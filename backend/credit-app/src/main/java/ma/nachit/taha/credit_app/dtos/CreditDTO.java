package ma.nachit.taha.credit_app.dtos;

import lombok.Data;
import ma.nachit.taha.credit_app.enums.StatutCredit;
import ma.nachit.taha.credit_app.enums.TypeBienImmobilier;

import java.time.LocalDate;

@Data
public class CreditDTO {
    private Long id;
    private String type;
    private LocalDate dateDemande;
    private StatutCredit statut;
    private LocalDate dateAcceptation;
    private Double montant;
    private Integer dureeRemboursement;
    private Double tauxInteret;
    private Long clientId;
    private String motif;
    private TypeBienImmobilier typeBien;
    private String raisonSociale;
}
