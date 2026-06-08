package ma.nachit.taha.credit_app.mappers;

import ma.nachit.taha.credit_app.dtos.CreditDTO;
import ma.nachit.taha.credit_app.entities.*;
import org.springframework.stereotype.Component;

@Component
public class CreditMapper {

    public CreditDTO toDTO(Credit credit) {
        CreditDTO dto = new CreditDTO();
        dto.setId(credit.getId());
        dto.setDateDemande(credit.getDateDemande());
        dto.setStatut(credit.getStatut());
        dto.setDateAcceptation(credit.getDateAcceptation());
        dto.setMontant(credit.getMontant());
        dto.setDureeRemboursement(credit.getDureeRemboursement());
        dto.setTauxInteret(credit.getTauxInteret());
        if (credit.getClient() != null) {
            dto.setClientId(credit.getClient().getId());
        }
        if (credit instanceof CreditPersonnel cp) {
            dto.setType("PERSONNEL");
            dto.setMotif(cp.getMotif());
        } else if (credit instanceof CreditImmobilier ci) {
            dto.setType("IMMOBILIER");
            dto.setTypeBien(ci.getTypeBien());
        } else if (credit instanceof CreditProfessionnel cpr) {
            dto.setType("PROFESSIONNEL");
            dto.setMotif(cpr.getMotif());
            dto.setRaisonSociale(cpr.getRaisonSociale());
        }
        return dto;
    }

    public Credit toEntity(CreditDTO dto) {
        Credit credit;
        String type = dto.getType() == null ? "" : dto.getType().toUpperCase();
        if (type.equals("IMMOBILIER")) {
            CreditImmobilier ci = new CreditImmobilier();
            ci.setTypeBien(dto.getTypeBien());
            credit = ci;
        } else if (type.equals("PROFESSIONNEL")) {
            CreditProfessionnel cpr = new CreditProfessionnel();
            cpr.setMotif(dto.getMotif());
            cpr.setRaisonSociale(dto.getRaisonSociale());
            credit = cpr;
        } else {
            CreditPersonnel cp = new CreditPersonnel();
            cp.setMotif(dto.getMotif());
            credit = cp;
        }
        credit.setId(dto.getId());
        credit.setDateDemande(dto.getDateDemande());
        credit.setStatut(dto.getStatut());
        credit.setDateAcceptation(dto.getDateAcceptation());
        credit.setMontant(dto.getMontant());
        credit.setDureeRemboursement(dto.getDureeRemboursement());
        credit.setTauxInteret(dto.getTauxInteret());
        return credit;
    }
}
