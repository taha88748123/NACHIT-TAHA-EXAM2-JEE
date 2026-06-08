package ma.nachit.taha.credit_app.web;

import lombok.RequiredArgsConstructor;
import ma.nachit.taha.credit_app.dtos.RemboursementDTO;
import ma.nachit.taha.credit_app.services.RemboursementService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/remboursements")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class RemboursementController {

    private final RemboursementService remboursementService;

    @GetMapping
    public List<RemboursementDTO> getAll() {
        return remboursementService.getAllRemboursements();
    }

    @GetMapping("/{id}")
    public RemboursementDTO getById(@PathVariable Long id) {
        return remboursementService.getRemboursementById(id);
    }

    @GetMapping("/credit/{creditId}")
    public List<RemboursementDTO> getByCredit(@PathVariable Long creditId) {
        return remboursementService.getRemboursementsByCredit(creditId);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE')")
    public RemboursementDTO create(@RequestBody RemboursementDTO remboursementDTO) {
        return remboursementService.saveRemboursement(remboursementDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        remboursementService.deleteRemboursement(id);
    }
}
