package ma.nachit.taha.credit_app.services;

import lombok.RequiredArgsConstructor;
import ma.nachit.taha.credit_app.dtos.CreditDTO;
import ma.nachit.taha.credit_app.entities.Client;
import ma.nachit.taha.credit_app.entities.Credit;
import ma.nachit.taha.credit_app.enums.StatutCredit;
import ma.nachit.taha.credit_app.exceptions.ResourceNotFoundException;
import ma.nachit.taha.credit_app.mappers.CreditMapper;
import ma.nachit.taha.credit_app.repositories.ClientRepository;
import ma.nachit.taha.credit_app.repositories.CreditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final ClientRepository clientRepository;
    private final CreditMapper creditMapper;

    @Override
    public List<CreditDTO> getAllCredits() {
        return creditRepository.findAll().stream().map(creditMapper::toDTO).toList();
    }

    @Override
    public CreditDTO getCreditById(Long id) {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit non trouve avec id " + id));
        return creditMapper.toDTO(credit);
    }

    @Override
    public List<CreditDTO> getCreditsByClient(Long clientId) {
        return creditRepository.findByClientId(clientId).stream().map(creditMapper::toDTO).toList();
    }

    @Override
    public CreditDTO saveCredit(CreditDTO creditDTO) {
        Credit credit = creditMapper.toEntity(creditDTO);
        if (creditDTO.getClientId() != null) {
            Client client = clientRepository.findById(creditDTO.getClientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Client non trouve avec id " + creditDTO.getClientId()));
            credit.setClient(client);
        }
        if (credit.getDateDemande() == null) {
            credit.setDateDemande(LocalDate.now());
        }
        if (credit.getStatut() == null) {
            credit.setStatut(StatutCredit.EN_COURS);
        }
        return creditMapper.toDTO(creditRepository.save(credit));
    }

    @Override
    public CreditDTO updateStatut(Long id, StatutCredit statut) {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit non trouve avec id " + id));
        credit.setStatut(statut);
        if (statut == StatutCredit.ACCEPTE) {
            credit.setDateAcceptation(LocalDate.now());
        }
        return creditMapper.toDTO(creditRepository.save(credit));
    }

    @Override
    public void deleteCredit(Long id) {
        creditRepository.deleteById(id);
    }
}
