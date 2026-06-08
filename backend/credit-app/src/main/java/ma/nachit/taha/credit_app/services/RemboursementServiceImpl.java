package ma.nachit.taha.credit_app.services;

import lombok.RequiredArgsConstructor;
import ma.nachit.taha.credit_app.dtos.RemboursementDTO;
import ma.nachit.taha.credit_app.entities.Credit;
import ma.nachit.taha.credit_app.entities.Remboursement;
import ma.nachit.taha.credit_app.exceptions.ResourceNotFoundException;
import ma.nachit.taha.credit_app.mappers.RemboursementMapper;
import ma.nachit.taha.credit_app.repositories.CreditRepository;
import ma.nachit.taha.credit_app.repositories.RemboursementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RemboursementServiceImpl implements RemboursementService {

    private final RemboursementRepository remboursementRepository;
    private final CreditRepository creditRepository;
    private final RemboursementMapper remboursementMapper;

    @Override
    public List<RemboursementDTO> getAllRemboursements() {
        return remboursementRepository.findAll().stream().map(remboursementMapper::toDTO).toList();
    }

    @Override
    public RemboursementDTO getRemboursementById(Long id) {
        Remboursement remboursement = remboursementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Remboursement non trouve avec id " + id));
        return remboursementMapper.toDTO(remboursement);
    }

    @Override
    public List<RemboursementDTO> getRemboursementsByCredit(Long creditId) {
        return remboursementRepository.findByCreditId(creditId).stream().map(remboursementMapper::toDTO).toList();
    }

    @Override
    public RemboursementDTO saveRemboursement(RemboursementDTO remboursementDTO) {
        Remboursement remboursement = remboursementMapper.toEntity(remboursementDTO);
        if (remboursementDTO.getCreditId() != null) {
            Credit credit = creditRepository.findById(remboursementDTO.getCreditId())
                    .orElseThrow(() -> new ResourceNotFoundException("Credit non trouve avec id " + remboursementDTO.getCreditId()));
            remboursement.setCredit(credit);
        }
        return remboursementMapper.toDTO(remboursementRepository.save(remboursement));
    }

    @Override
    public void deleteRemboursement(Long id) {
        remboursementRepository.deleteById(id);
    }
}
