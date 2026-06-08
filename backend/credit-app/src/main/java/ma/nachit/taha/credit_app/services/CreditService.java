package ma.nachit.taha.credit_app.services;

import ma.nachit.taha.credit_app.dtos.CreditDTO;
import ma.nachit.taha.credit_app.enums.StatutCredit;

import java.util.List;

public interface CreditService {
    List<CreditDTO> getAllCredits();
    CreditDTO getCreditById(Long id);
    List<CreditDTO> getCreditsByClient(Long clientId);
    CreditDTO saveCredit(CreditDTO creditDTO);
    CreditDTO updateStatut(Long id, StatutCredit statut);
    void deleteCredit(Long id);
}
