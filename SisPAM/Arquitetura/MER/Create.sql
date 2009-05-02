-- ------------------------------------------------------------
-- Tabela de Especialidade Médica
-- ------------------------------------------------------------

CREATE SEQUENCE emd_seq;

CREATE TABLE especialidademedica (
  emdcod INTEGER  NOT NULL DEFAULT NEXTVAL('emd_seq'),
  emddes VARCHAR(50) NULL
);

ALTER TABLE especialidademedica
   ADD CONSTRAINT emdA PRIMARY KEY(emdcod);

CREATE INDEX emdB ON especialidademedica (emddes);

-- ------------------------------------------------------------
-- Tabela de Convênio
-- ------------------------------------------------------------

CREATE SEQUENCE cvn_seq;

CREATE TABLE convenio (
  cvncod INTEGER  NOT NULL DEFAULT NEXTVAL('cvn_seq'),
  cvnanscod INTEGER  NULL,
  cvndes VARCHAR(50) NULL,
  cvncpj NUMERIC(14) NULL,
  cvnend VARCHAR(50) NULL,
  cvncde VARCHAR(30) NULL,
  cvnetd VARCHAR(20) NULL,
  cvncep NUMERIC(8) NULL,
  cvnste VARCHAR(50) NULL,
  cvneml VARCHAR(50) NULL,
  cvnddd NUMERIC(3) NULL,
  cvntel NUMERIC(8) NULL
);

ALTER TABLE convenio
   ADD CONSTRAINT cvnA PRIMARY KEY(cvncod);  

CREATE INDEX cvnB ON convenio (cvndes);

-- ------------------------------------------------------------
-- Tabela de Usuário
-- ------------------------------------------------------------

CREATE SEQUENCE usr_seq;

CREATE TABLE usuario (
  usrcod INTEGER  NOT NULL DEFAULT NEXTVAL('usr_seq'),
  usrnom VARCHAR(40) NULL,
  usrsex CHAR(1) NULL,
  usrcpf VARCHAR(15) NULL,
  usrrg NUMERIC(10) NULL,
  usrorgexp VARCHAR(5) NULL,
  usrend VARCHAR(50) NULL,
  usrcep NUMERIC(8) NULL,
  usrcde VARCHAR(30) NULL,
  usruf VARCHAR(2) NULL,
  usrddd NUMERIC(3) NULL,
  usrtel NUMERIC(8) NULL,
  usreml VARCHAR(50) NULL,
  usrsen VARCHAR(20) NULL,
  usrpfl NUMERIC(1) NOT NULL
);

ALTER TABLE usuario
   ADD CONSTRAINT usrA PRIMARY KEY(usrcod);

CREATE INDEX usrB ON usuario (usrnom);

-- ------------------------------------------------------------
-- Tabela de Parâmetro
-- ------------------------------------------------------------

CREATE SEQUENCE pmt_seq;

CREATE TABLE parametro (
  pmtcod INTEGER  NOT NULL DEFAULT NEXTVAL('pmt_seq'),
  pmtdes VARCHAR(30) NOT NULL,
  pmtval VARCHAR(100) NOT NULL,
  pmttip NUMERIC(1) NULL
);


ALTER TABLE parametro
   ADD CONSTRAINT pmtA PRIMARY KEY(pmtcod);  

CREATE INDEX pmtB ON parametro (pmtdes);

-- ------------------------------------------------------------
-- Tabela de Codigos Internacionais de Doenças
-- ------------------------------------------------------------

CREATE SEQUENCE cid_seq;

CREATE TABLE codigodoenca (
  cidcod INTEGER  NOT NULL DEFAULT NEXTVAL('cid_seq'),
  ciddes VARCHAR(50) NULL 
);

ALTER TABLE codigodoenca
   ADD CONSTRAINT cidA PRIMARY KEY(cidcod);  

CREATE INDEX cidB ON codigodoenca (ciddes);

-- ------------------------------------------------------------
-- Tabela de Log de Auditoria
-- ------------------------------------------------------------

CREATE SEQUENCE adt_seq;

CREATE TABLE auditoria (
  adtcod NUMERIC(12) NOT NULL DEFAULT NEXTVAL('adt_seq'),
  adtdatref TIMESTAMP NULL,
  adtip VARCHAR(15) NULL
);

ALTER TABLE auditoria
   ADD CONSTRAINT adtA PRIMARY KEY(adtcod);

-- ------------------------------------------------------------
-- Tabela de Médico
-- ------------------------------------------------------------

CREATE SEQUENCE mdc_seq;

CREATE TABLE medico (
  mdccod INTEGER  NOT NULL DEFAULT NEXTVAL('mdc_seq'),
  usrcod INTEGER  NOT NULL,
  mdccrm INTEGER  NULL,
  mdccrmuf VARCHAR(2) NULL
);

ALTER TABLE medico
   ADD CONSTRAINT mdcA PRIMARY KEY(mdccod);

ALTER TABLE medico
   ADD CONSTRAINT FK_medico_USUARIO FOREIGN KEY (usrcod)
                          REFERENCES USUARIO (usrcod);

-- ------------------------------------------------------------
-- Tabela de Agenda Médica
-- ------------------------------------------------------------

CREATE SEQUENCE agm_seq;

CREATE TABLE agendamedica (
  agmcod INTEGER  NOT NULL DEFAULT NEXTVAL('agm_seq'),
  mdccod INTEGER  NOT NULL,
  agmdtaatd VARCHAR(14) NULL,
  agmhorini NUMERIC(4) NULL,
  agmhorfim NUMERIC(4) NULL,
  agmcnsatd NUMERIC(4) NULL
);

ALTER TABLE agendamedica
   ADD CONSTRAINT agmA PRIMARY KEY(agmcod);  

ALTER TABLE agendamedica
   ADD CONSTRAINT FK_agendamedica_MEDICO FOREIGN KEY (mdccod)
                          REFERENCES MEDICO (mdccod);    

-- ------------------------------------------------------------
-- Tabela de Compromisso
-- ------------------------------------------------------------

CREATE SEQUENCE cpm_seq;

CREATE TABLE compromisso  (
  cpmcod INTEGER  NOT NULL DEFAULT NEXTVAL('cpm_seq'),
  agmcod INTEGER  NOT NULL,
  cpmdes VARCHAR(50) NULL,
  cpmtip VARCHAR(14) NULL,
  cpmdta TIMESTAMP NULL,
  cpmhorini NUMERIC(4) NULL,
  cpmhorter NUMERIC(4) NULL  
);

ALTER TABLE compromisso
   ADD CONSTRAINT cpmA PRIMARY KEY(cpmcod);  

CREATE INDEX cpmB ON compromisso (cpmdes);

ALTER TABLE compromisso
   ADD CONSTRAINT FK_compromisso_AGENDAMEDICA FOREIGN KEY (agmcod)
                          REFERENCES AGENDAMEDICA (agmcod); 

-- ------------------------------------------------------------
-- Tabela de Relacionamento medico x especialidade
-- ------------------------------------------------------------


CREATE TABLE medico_especialidade  (
  mdccod INTEGER  NOT NULL,
  emdcod INTEGER  NOT NULL
);

ALTER TABLE medico_especialidade
   ADD CONSTRAINT rmeA PRIMARY KEY(mdccod, emdcod);  

ALTER TABLE medico_especialidade
   ADD CONSTRAINT FK_medico_especialidade_MEDICO FOREIGN KEY (mdccod)
                          REFERENCES MEDICO (mdccod); 
ALTER TABLE medico_especialidade
   ADD CONSTRAINT FK_medico_especialidade_ESPECIALIDADEMEDICA FOREIGN KEY (emdcod)
                          REFERENCES ESPECIALIDADEMEDICA (emdcod); 


-- ------------------------------------------------------------
-- Tabela de Paciente
-- ------------------------------------------------------------

CREATE SEQUENCE pct_seq;

CREATE TABLE paciente (
  pctidfseg NUMERIC(14) NOT NULL DEFAULT NEXTVAL('pct_seq'),
  usrcod INTEGER NOT NULL,
  cvncod INTEGER NOT NULL,
  pctplnrde VARCHAR(25) NULL,
  pctacm VARCHAR(30) NULL,
  pctplnvld TIMESTAMP NULL
);

ALTER TABLE paciente
   ADD CONSTRAINT pctA PRIMARY KEY(pctidfseg);
  
CREATE INDEX pctB ON paciente (pctplnrde);

ALTER TABLE paciente
   ADD CONSTRAINT FK_paciente_USUARIO FOREIGN KEY (usrcod)
                          REFERENCES USUARIO (usrcod);
ALTER TABLE paciente
   ADD CONSTRAINT FK_paciente_CONVENIO FOREIGN KEY (cvncod)
                          REFERENCES CONVENIO (cvncod);

-- ------------------------------------------------------------
-- Tabela de Agendamento
-- ------------------------------------------------------------

CREATE SEQUENCE agd_seq;

CREATE TABLE agendamento (
  agdcod INTEGER NOT NULL DEFAULT NEXTVAL('agd_seq'),
  pctidfseg NUMERIC(14) NOT NULL,
  emdcod INTEGER  NOT NULL,
  mdccod INTEGER  NOT NULL,
  agdtip INTEGER  NULL,
  agddat TIMESTAMP NULL,
  agdhor INTEGER  NULL,
  agdobs VARCHAR(200) NULL
);

ALTER TABLE agendamento
   ADD CONSTRAINT agdA PRIMARY KEY(agdcod);  

ALTER TABLE agendamento
   ADD CONSTRAINT FK_agendamento_MEDICO FOREIGN KEY (mdccod)
                          REFERENCES MEDICO (mdccod);    
ALTER TABLE agendamento
   ADD CONSTRAINT FK_agendamento_PACIENTE FOREIGN KEY (pctidfseg)
                          REFERENCES PACIENTE (pctidfseg);
ALTER TABLE agendamento
   ADD CONSTRAINT FK_agendamento_ESPECIALIDADEMEDICA FOREIGN KEY (emdcod)
                          REFERENCES ESPECIALIDADEMEDICA (emdcod);

-- ------------------------------------------------------------
-- Tabela de Historico de Prontuário
-- ------------------------------------------------------------

CREATE SEQUENCE htc_seq;

CREATE TABLE historicoprontuario (
  htccod INTEGER  NOT NULL DEFAULT NEXTVAL('htc_seq'),
  cidcod INTEGER  NOT NULL,
  pctidfseg NUMERIC(14) NOT NULL,
  htcstm VARCHAR(400) NULL,
  htcldo VARCHAR(400) NULL,
  htcobs VARCHAR(400) NULL,
  htcprc VARCHAR(400) NULL
);

ALTER TABLE historicoprontuario
   ADD CONSTRAINT htcA PRIMARY KEY(htccod);  

CREATE INDEX htcB ON historicoprontuario (htcstm, htcldo);

ALTER TABLE historicoprontuario
   ADD CONSTRAINT FK_historicoprontuario_CODIGODOENCA FOREIGN KEY (cidcod)
                          REFERENCES CODIGODOENCA (cidcod); 
ALTER TABLE historicoprontuario
   ADD CONSTRAINT FK_historicoprontuario_PACIENTE FOREIGN KEY (pctidfseg)
                          REFERENCES PACIENTE (pctidfseg);



