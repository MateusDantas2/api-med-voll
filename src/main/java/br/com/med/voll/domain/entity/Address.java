package br.com.med.voll.domain.entity;

import br.com.med.voll.dto.AddressDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mateus Dantas
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    @Column(name = "logradouro")
    private String street;
    @Column(name = "bairro")
    private String neighborhood;
    @Column(name = "cep")
    private String zipCode;
    @Column(name = "numero")
    private String number;
    @Column(name = "complemento")
    private String complement;
    @Column(name = "cidade")
    private String city;
    private String uf;

    public Address(AddressDTO addressDTO) {
        this.street = addressDTO.logradouro();
        this.neighborhood = addressDTO.bairro();
        this.zipCode = addressDTO.cep();
        this.number = addressDTO.numero();
        this.complement = addressDTO.complemento();
        this.city = addressDTO.cidade();
        this.uf = addressDTO.uf();
    }
}
