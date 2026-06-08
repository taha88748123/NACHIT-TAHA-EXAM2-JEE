package ma.nachit.taha.credit_app.web;

import lombok.RequiredArgsConstructor;
import ma.nachit.taha.credit_app.dtos.CreditDTO;
import ma.nachit.taha.credit_app.enums.StatutCredit;
import ma.nachit.taha.credit_app.services.CreditService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    @GetMapping
    public List<CreditDTO> getAll() {
        return creditService.getAllCredits();
    }

    @GetMapping("/{id}")
    public CreditDTO getById(@PathVariable Long id) {
        return creditService.getCreditById(id);
    }

    @GetMapping("/client/{clientId}")
    public List<CreditDTO> getByClient(@PathVariable Long clientId) {
        return creditService.getCreditsByClient(clientId);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE')")
    public CreditDTO create(@RequestBody CreditDTO creditDTO) {
        return creditService.saveCredit(creditDTO);
    }

    @PutMapping("/{id}/statut")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE')")
    public CreditDTO updateStatut(@PathVariable Long id, @RequestParam StatutCredit statut) {
        return creditService.updateStatut(id, statut);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        creditService.deleteCredit(id);
    }
}
