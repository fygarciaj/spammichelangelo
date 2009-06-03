
-- ------------------------------------------------------------
-- Tabela de Especialidade Médica
-- ------------------------------------------------------------

CREATE SEQUENCE especialidademedica_emdcod_seq;

CREATE TABLE especialidademedica (
  emdcod INTEGER  NOT NULL DEFAULT NEXTVAL('especialidademedica_emdcod_seq'),
  emddes VARCHAR(50) NULL
);

ALTER TABLE especialidademedica
   ADD CONSTRAINT emdA PRIMARY KEY(emdcod);

CREATE INDEX emdB ON especialidademedica (emddes);

-- ------------------------------------------------------------
-- Tabela de Convênio
-- ------------------------------------------------------------

CREATE SEQUENCE convenio_cvncod_seq;

CREATE TABLE convenio (
  cvncod INTEGER  NOT NULL DEFAULT NEXTVAL('convenio_cvncod_seq'),
  cvnanscod INTEGER  NULL,
  cvndes VARCHAR(50) NOT NULL,
  cvncpj VARCHAR(18) NOT NULL,
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

CREATE INDEX cvnC ON convenio (cvncpj);
-- ------------------------------------------------------------
-- Tabela de Usuário
-- ------------------------------------------------------------

CREATE SEQUENCE usuario_usrcod_seq;

CREATE TABLE usuario (
  usrcod INTEGER  NOT NULL DEFAULT NEXTVAL('usuario_usrcod_seq'),
  usrnom VARCHAR(60) NULL,
  usrsex CHAR(1) NULL,
  usrcpf VARCHAR(15) NULL,
  usrrg NUMERIC(15) NULL,
  usrorgexp VARCHAR(15) NULL,
  usrend VARCHAR(60) NULL,
  usrcep NUMERIC(8) NULL,
  usrcde VARCHAR(25) NULL,
  usruf VARCHAR(2) NULL,
  usrddd NUMERIC(3) NULL,
  usrtel NUMERIC(8) NULL,
  usreml VARCHAR(30) NULL,
  usracs VARCHAR(25) NULL,
  usrsen VARCHAR(32) NULL,
  usrpfl NUMERIC(1) NOT NULL,
  usrdatnsc DATE NULL

);

ALTER TABLE usuario
   ADD CONSTRAINT usrA PRIMARY KEY(usrcod);

CREATE INDEX usrB ON usuario (usrnom);
CREATE INDEX usrC ON usuario (usracs);
-- ------------------------------------------------------------
-- Tabela de Parâmetro
-- ------------------------------------------------------------

CREATE SEQUENCE parametro_pmtcod_seq;

CREATE TABLE parametro (
  pmtcod INTEGER  NOT NULL DEFAULT NEXTVAL('parametro_pmtcod_seq'),
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

CREATE SEQUENCE codigodoenca_cidcod_seq;

CREATE TABLE codigodoenca (
  cidcod INTEGER  NOT NULL DEFAULT NEXTVAL('codigodoenca_cidcod_seq'),
  cidctgini VARCHAR(3) NULL,
  cidctgfim VARCHAR(3) NULL,
  ciddes VARCHAR(250) NULL, 
  ciddesabr VARCHAR(100) NULL
);

ALTER TABLE codigodoenca
   ADD CONSTRAINT cidA PRIMARY KEY(cidcod);  

CREATE INDEX cidB ON codigodoenca (ciddes);
CREATE INDEX cidC ON codigodoenca (ciddesabr);

-- ------------------------------------------------------------
-- Tabela de Log de Auditoria
-- ------------------------------------------------------------

CREATE SEQUENCE auditoria_adtcod_seq;

CREATE TABLE auditoria (
  adtcod NUMERIC(12) NOT NULL DEFAULT NEXTVAL('auditoria_adtcod_seq'),
  adtdatref TIMESTAMP NULL,
  adtact VARCHAR(15) NULL,
  adttip VARCHAR(40) NULL,
  usrcod INTEGER  NOT NULL
);

ALTER TABLE auditoria
   ADD CONSTRAINT adtA PRIMARY KEY(adtcod);
   
ALTER TABLE auditoria
   ADD CONSTRAINT FK_auditoria_USUARIO FOREIGN KEY (usrcod)
                          REFERENCES USUARIO (usrcod);

-- ------------------------------------------------------------
-- Tabela de Médico
-- ------------------------------------------------------------

CREATE SEQUENCE medico_mdccod_seq;

CREATE TABLE medico (
  mdccod INTEGER  NOT NULL DEFAULT NEXTVAL('medico_mdccod_seq'),
  usrcod INTEGER  NOT NULL,
  mdccrm INTEGER  NULL,
  mdccrmuf VARCHAR(2) NULL,
  mdcdiaatd VARCHAR(14) NULL,
  mdchorini NUMERIC(4) NULL,
  mdchorfim NUMERIC(4) NULL,
  mdccnsatd NUMERIC(4) NULL
);

ALTER TABLE medico
   ADD CONSTRAINT mdcA PRIMARY KEY(mdccod);

ALTER TABLE medico
   ADD CONSTRAINT FK_medico_USUARIO FOREIGN KEY (usrcod)
                          REFERENCES USUARIO (usrcod);

-- ------------------------------------------------------------
-- Tabela de Compromisso
-- ------------------------------------------------------------

CREATE SEQUENCE compromisso_cpmcod_seq;

CREATE TABLE compromisso  (
  cpmcod INTEGER  NOT NULL DEFAULT NEXTVAL('compromisso_cpmcod_seq'),
  mdccod INTEGER  NOT NULL,
  cpmdes VARCHAR(50) NULL,
  cpmtip VARCHAR(14) NULL,
  cpmdta DATE NULL,
  cpmhorini NUMERIC(4) NULL,
  cpmhorfim NUMERIC(4) NULL  
);

ALTER TABLE compromisso
   ADD CONSTRAINT cpmA PRIMARY KEY(cpmcod);  

CREATE INDEX cpmB ON compromisso (cpmdes);

ALTER TABLE compromisso
   ADD CONSTRAINT FK_compromisso_MEDICO FOREIGN KEY (mdccod)
                          REFERENCES MEDICO (mdccod); 

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

CREATE SEQUENCE paciente_pctcod_seq;

CREATE TABLE paciente (
  pctidfseg NUMERIC(14) NOT NULL DEFAULT NEXTVAL('paciente_pctcod_seq'),
  usrcod INTEGER NOT NULL,
  cvncod INTEGER NULL,
  pctplnrde VARCHAR(25) NULL,
  pctacm VARCHAR(20) NULL,
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

CREATE SEQUENCE agendamento_agdcod_seq;

CREATE TABLE agendamento (
  agdcod INTEGER NOT NULL DEFAULT NEXTVAL('agendamento_agdcod_seq'),
  pctidfseg NUMERIC(14) NOT NULL,
  emdcod INTEGER  NOT NULL,
  mdccod INTEGER  NOT NULL,
  agdtip INTEGER  NULL,
  agddat DATE NULL,
  agdhor INTEGER  NULL,
  agdobs VARCHAR(200) NULL,
  agdsta INTEGER NOT NULL
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

CREATE SEQUENCE historicoprontuario_htccod_seq;

CREATE TABLE historicoprontuario (
  htccod INTEGER  NOT NULL DEFAULT NEXTVAL('historicoprontuario_htccod_seq'),
  cidcod INTEGER  NULL,
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


