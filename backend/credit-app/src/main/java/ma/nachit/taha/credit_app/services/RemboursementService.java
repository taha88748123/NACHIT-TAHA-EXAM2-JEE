package ma.nachit.taha.credit_app.services;

import ma.nachit.taha.credit_app.dtos.RemboursementDTO;

import java.util.List;

public interface RemboursementService {
    List<RemboursementDTO> getAllRemboursements();
    RemboursementDTO getRemboursementById(Long id);
    List<RemboursementDTO> getRemboursementsByCredit(Long creditId);
    RemboursementDTO saveRemboursement(RemboursementDTO remboursementDTO);
    void deleteRemboursement(Long id);
}
