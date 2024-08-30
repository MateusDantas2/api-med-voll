ALTER TABLE medicos ADD status TINYINT;
UPDATE medicos set status = 1;
ALTER TABLE medicos MODIFY COLUMN status TINYINT NOT NULL;