package ma.nachit.taha.credit_app.web;

import lombok.RequiredArgsConstructor;
import ma.nachit.taha.credit_app.dtos.ClientDTO;
import ma.nachit.taha.credit_app.services.ClientService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<ClientDTO> getAll() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ClientDTO getById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE')")
    public ClientDTO create(@RequestBody ClientDTO clientDTO) {
        return clientService.saveClient(clientDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE')")
    public ClientDTO update(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return clientService.updateClient(id, clientDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
