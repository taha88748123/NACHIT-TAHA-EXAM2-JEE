package ma.nachit.taha.credit_app.mappers;

import ma.nachit.taha.credit_app.dtos.RemboursementDTO;
import ma.nachit.taha.credit_app.entities.Remboursement;
import org.springframework.stereotype.Component;

@Component
public class RemboursementMapper {

    public RemboursementDTO toDTO(Remboursement remboursement) {
        RemboursementDTO dto = new RemboursementDTO();
        dto.setId(remboursement.getId());
        dto.setDate(remboursement.getDate());
        dto.setMontant(remboursement.getMontant());
        dto.setType(remboursement.getType());
        if (remboursement.getCredit() != null) {
            dto.setCreditId(remboursement.getCredit().getId());
        }
        return dto;
    }

    public Remboursement toEntity(RemboursementDTO dto) {
        Remboursement remboursement = new Remboursement();
        remboursement.setId(dto.getId());
        remboursement.setDate(dto.getDate());
        remboursement.setMontant(dto.getMontant());
        remboursement.setType(dto.getType());
        return remboursement;
    }
}
