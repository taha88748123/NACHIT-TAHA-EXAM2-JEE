package ma.nachit.taha.credit_app.services;

import ma.nachit.taha.credit_app.dtos.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClients();
    ClientDTO getClientById(Long id);
    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO updateClient(Long id, ClientDTO clientDTO);
    void deleteClient(Long id);
}
