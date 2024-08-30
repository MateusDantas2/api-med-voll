package br.com.med.voll.domain.entity;

import br.com.med.voll.dto.patient.PatientRegisterDTO;
import br.com.med.voll.dto.patient.PatientUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mateus Dantas
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
@Entity(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String name;
    private String email;
    @Column(name = "telefone")
    private String phone;
    private String cpf;
    @Embedded
    private Address address;
    private Boolean status;

    public Patient(PatientRegisterDTO dados) {
        this.status = true;
        this.name = dados.nome();
        this.email = dados.email();
        this.phone = dados.telefone();
        this.cpf = dados.cpf();
        this.address = new Address(dados.endereco());
    }

    public void atualizarInformacoes(PatientUpdateDTO dados) {
        if (dados.nome() != null) {
            this.name = dados.nome();
        }
        if (dados.telefone() != null) {
            this.phone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.address.updateInformation(dados.endereco());
        }
    }

    public void delete() {
        this.status = false;
    }
}
