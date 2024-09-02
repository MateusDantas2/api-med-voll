create table consultas(
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_medico BIGINT NOT NULL,
    id_paciente BIGINT NOT NULL,
    data DATETIME NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_consultas_id_medico FOREIGN KEY (id_medico) REFERENCES medicos(id),
    CONSTRAINT fk_consultas_id_paciente FOREIGN KEY (id_paciente) REFERENCES pacientes(id)
);