package com.deliverytech.delivery.service;

import com.deliverytech.delivery.entity.Client;
import com.deliverytech.delivery.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client create(Client client) {
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado: " + client.getEmail());
        }

        client.setIsActive(true);
        return clientRepository.save(client);
    }

    public Client findById(Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
    }

    public List<Client> findActiveClients() {
        return clientRepository.findByIsActive(true);
    }

    public Client update(Integer id, Client dadosAtualizados) {
        Client existingClient = findById(id);

        existingClient.setName(dadosAtualizados.getName());
        existingClient.setPhoneNumber(dadosAtualizados.getPhoneNumber());

        return clientRepository.save(existingClient);
    }

    /**
     * "Deleta" um cliente (deleção lógica).
     */
    public void inactivate(Integer id) {
        Client existingClient = findById(id);
        existingClient.setIsActive(false);
        clientRepository.save(existingClient);
    }
}