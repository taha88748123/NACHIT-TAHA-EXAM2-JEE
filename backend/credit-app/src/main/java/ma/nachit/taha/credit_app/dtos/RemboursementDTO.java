package ma.nachit.taha.credit_app.dtos;

import lombok.Data;
import ma.nachit.taha.credit_app.enums.TypeRemboursement;

import java.time.LocalDate;

@Data
public class RemboursementDTO {
    private Long id;
    private LocalDate date;
    private Double montant;
    private TypeRemboursement type;
    private Long creditId;
}
