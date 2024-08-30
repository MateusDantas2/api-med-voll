package br.com.med.voll.domain.entity;

import br.com.med.voll.dto.doctor.DoctorRegisterDTO;
import br.com.med.voll.dto.doctor.DoctorUpdateDTO;
import br.com.med.voll.enumaration.Specialty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mateus Dantas
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicos")
@Entity(name = "medico")
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String name;
    private String email;
    @Column(name = "telefone")
    private String phone;
    private String crm;
    @Column(name = "especialidade")
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;
    private Boolean status;

    public Doctor(DoctorRegisterDTO registerDTO) {
        this.name = registerDTO.nome();
        this.email = registerDTO.email();
        this.phone = registerDTO.telefone();
        this.crm = registerDTO.crm();
        this.specialty = registerDTO.especialidade();
        this.address = new Address(registerDTO.endereco());
        this.status = true;
    }

    public void updateInformation(@Valid DoctorUpdateDTO dataDTO) {
        if (dataDTO.nome() != null && !dataDTO.nome().isEmpty()) {
            this.name = dataDTO.nome();
        }
        if (dataDTO.telefone() != null && !dataDTO.telefone().isEmpty()) {
            this.phone = dataDTO.telefone();
        }
        if (dataDTO.endereco() != null) {
            this.address.updateInformation(dataDTO.endereco());
        }
    }

    public void delete() {
        this.status = false;
    }
}
