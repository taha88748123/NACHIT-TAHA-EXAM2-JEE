package ma.nachit.taha.credit_app.services;

import lombok.RequiredArgsConstructor;
import ma.nachit.taha.credit_app.dtos.ClientDTO;
import ma.nachit.taha.credit_app.entities.Client;
import ma.nachit.taha.credit_app.exceptions.ResourceNotFoundException;
import ma.nachit.taha.credit_app.mappers.ClientMapper;
import ma.nachit.taha.credit_app.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream().map(clientMapper::toDTO).toList();
    }

    @Override
    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client non trouve avec id " + id));
        return clientMapper.toDTO(client);
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client non trouve avec id " + id));
        client.setNom(clientDTO.getNom());
        client.setEmail(clientDTO.getEmail());
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
