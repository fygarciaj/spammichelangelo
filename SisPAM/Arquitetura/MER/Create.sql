-- ------------------------------------------------------------
-- Tabela de Especialidade Médica
-- ------------------------------------------------------------

CREATE SEQUENCE emd_seq;

CREATE TABLE emd (
  emdcod INTEGER  NOT NULL DEFAULT NEXTVAL('emd_seq'),
  emddes VARCHAR(50) NULL
);

ALTER TABLE emd
   ADD CONSTRAINT emdA PRIMARY KEY(emdcod);

CREATE INDEX emdB ON emd (emddes);

-- ------------------------------------------------------------
-- Tabela de Convênio
-- ------------------------------------------------------------

CREATE SEQUENCE cvn_seq;

CREATE TABLE cvn (
  cvncod INTEGER  NOT NULL DEFAULT NEXTVAL('cvn_seq'),
  cvnanscod INTEGER  NULL,
  cvndes VARCHAR(50) NULL,
  cvncpj NUMERIC(14) NULL,
  cvnend VARCHAR(50) NULL,
  cvnste VARCHAR(50) NULL,
  cvneml VARCHAR(50) NULL,
  cvnddd NUMERIC(3) NULL,
  cvntel NUMERIC(8) NULL
);

ALTER TABLE cvn
   ADD CONSTRAINT cvnA PRIMARY KEY(cvncod);  

CREATE INDEX cvnB ON cvn (cvndes);

-- ------------------------------------------------------------
-- Tabela de Usuário
-- ------------------------------------------------------------

CREATE SEQUENCE usr_seq;

CREATE TABLE usr (
  usrcod INTEGER  NOT NULL DEFAULT NEXTVAL('usr_seq'),
  usrnom VARCHAR(40) NULL,
  usrsex BIT NULL,
  usrcpf NUMERIC(11) NULL,
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

ALTER TABLE usr
   ADD CONSTRAINT usrA PRIMARY KEY(usrcod);

CREATE INDEX usrB ON usr (usrnom);

-- ------------------------------------------------------------
-- Tabela de Parâmetro
-- ------------------------------------------------------------

CREATE SEQUENCE pmt_seq;

CREATE TABLE pmt (
  pmtcod INTEGER  NOT NULL DEFAULT NEXTVAL('pmt_seq'),
  pmtdes VARCHAR(30) NOT NULL,
  pmtval VARCHAR(100) NOT NULL,
  pmttip NUMERIC(1) NULL
);


ALTER TABLE pmt
   ADD CONSTRAINT pmtA PRIMARY KEY(pmtcod);  

CREATE INDEX pmtB ON pmt (pmtdes);

-- ------------------------------------------------------------
-- Tabela de Codigos Internacionais de Doenças
-- ------------------------------------------------------------

CREATE SEQUENCE cid_seq;

CREATE TABLE cid (
  cidcod INTEGER  NOT NULL DEFAULT NEXTVAL('cid_seq'),
  ciddes VARCHAR(50) NULL 
);

ALTER TABLE cid
   ADD CONSTRAINT cidA PRIMARY KEY(cidcod);  

CREATE INDEX cidB ON cid (ciddes);

-- ------------------------------------------------------------
-- Tabela de Log de Auditoria
-- ------------------------------------------------------------

CREATE SEQUENCE adt_seq;

CREATE TABLE adt (
  adtcod NUMERIC(12) NOT NULL DEFAULT NEXTVAL('adt_seq'),
  adtdatref DATE NULL,
  adtip VARCHAR(15) NULL
);

ALTER TABLE adt
   ADD CONSTRAINT adtA PRIMARY KEY(adtcod);

-- ------------------------------------------------------------
-- Tabela de Médico
-- ------------------------------------------------------------

CREATE SEQUENCE mdc_seq;

CREATE TABLE mdc (
  mdccod INTEGER  NOT NULL DEFAULT NEXTVAL('mdc_seq'),
  usrcod INTEGER  NOT NULL,
  mdccrm INTEGER  NULL,
  mdccrmuf VARCHAR(2) NULL
);

ALTER TABLE mdc
   ADD CONSTRAINT mdcA PRIMARY KEY(mdccod);

ALTER TABLE mdc
   ADD CONSTRAINT FK_mdc_USR FOREIGN KEY (usrcod)
                          REFERENCES USR (usrcod);

-- ------------------------------------------------------------
-- Tabela de Agenda Médica
-- ------------------------------------------------------------

CREATE SEQUENCE agm_seq;

CREATE TABLE agm (
  agmcod INTEGER  NOT NULL DEFAULT NEXTVAL('agm_seq'),
  mdccod INTEGER  NOT NULL,
  agmdtaatd VARCHAR(14) NULL,
  agmhorini NUMERIC(4) NULL,
  agmhorfim NUMERIC(4) NULL,
  agmcnsatd NUMERIC(4) NULL
);

ALTER TABLE agm
   ADD CONSTRAINT agmA PRIMARY KEY(agmcod);  

ALTER TABLE agm
   ADD CONSTRAINT FK_agm_MDC FOREIGN KEY (mdccod)
                          REFERENCES MDC (mdccod);    

-- ------------------------------------------------------------
-- Tabela de Compromisso
-- ------------------------------------------------------------

CREATE SEQUENCE cpm_seq;

CREATE TABLE cpm (
  cpmcod INTEGER  NOT NULL DEFAULT NEXTVAL('cpm_seq'),
  agmcod INTEGER  NOT NULL,
  cpmdes VARCHAR(50) NULL,
  cpmtip VARCHAR(14) NULL,
  cpmdta DATE NULL,
  cpmhorini NUMERIC(4) NULL,
  cpmhorter NUMERIC(4) NULL  
);

ALTER TABLE cpm
   ADD CONSTRAINT cpmA PRIMARY KEY(cpmcod);  

CREATE INDEX cpmB ON cpm (cpmdes);

ALTER TABLE cpm
   ADD CONSTRAINT FK_cpm_AGM FOREIGN KEY (agmcod)
                          REFERENCES AGM (agmcod); 

-- ------------------------------------------------------------
-- Tabela de Relacionamento medico x especialidade
-- ------------------------------------------------------------


CREATE TABLE rme (
  mdccod INTEGER  NOT NULL,
  emdcod INTEGER  NOT NULL
);

ALTER TABLE rme
   ADD CONSTRAINT rmeA PRIMARY KEY(mdccod, emdcod);  

ALTER TABLE rme
   ADD CONSTRAINT FK_rme_MDC FOREIGN KEY (mdccod)
                          REFERENCES MDC (mdccod); 
ALTER TABLE rme
   ADD CONSTRAINT FK_rme_EMD FOREIGN KEY (emdcod)
                          REFERENCES EMD (emdcod); 


-- ------------------------------------------------------------
-- Tabela de Paciente
-- ------------------------------------------------------------

CREATE SEQUENCE pct_seq;

CREATE TABLE pct (
  pctidfseg NUMERIC(14) NOT NULL DEFAULT NEXTVAL('pct_seq'),
  usrcod INTEGER NOT NULL,
  cvncod INTEGER NOT NULL,
  pctplnrde VARCHAR(25) NULL,
  pctacm VARCHAR(30) NULL,
  pctplnvld DATE NULL
);

ALTER TABLE pct
   ADD CONSTRAINT pctA PRIMARY KEY(pctidfseg);
  
CREATE INDEX pctB ON pct (pctplnrde);

ALTER TABLE pct
   ADD CONSTRAINT FK_pct_USR FOREIGN KEY (usrcod)
                          REFERENCES USR (usrcod);
ALTER TABLE pct
   ADD CONSTRAINT FK_pct_CVN FOREIGN KEY (cvncod)
                          REFERENCES CVN (cvncod);

-- ------------------------------------------------------------
-- Tabela de Agendamento
-- ------------------------------------------------------------

CREATE SEQUENCE agd_seq;

CREATE TABLE agd (
  agdcod INTEGER NOT NULL DEFAULT NEXTVAL('agd_seq'),
  pctidfseg NUMERIC(14) NOT NULL,
  emdcod INTEGER  NOT NULL,
  mdccod INTEGER  NOT NULL,
  agdtip INTEGER  NULL,
  agddat DATE NULL,
  agdhor INTEGER  NULL,
  agdobs VARCHAR(200) NULL
);

ALTER TABLE agd
   ADD CONSTRAINT agdA PRIMARY KEY(agdcod);  

ALTER TABLE agd
   ADD CONSTRAINT FK_agd_MDC FOREIGN KEY (mdccod)
                          REFERENCES MDC (mdccod);    
ALTER TABLE agd
   ADD CONSTRAINT FK_agd_PCT FOREIGN KEY (pctidfseg)
                          REFERENCES PCT (pctidfseg);
ALTER TABLE agd
   ADD CONSTRAINT FK_agd_EMD FOREIGN KEY (emdcod)
                          REFERENCES EMD (emdcod);

-- ------------------------------------------------------------
-- Tabela de Historico de Prontuário
-- ------------------------------------------------------------

CREATE SEQUENCE htc_seq;

CREATE TABLE htc (
  htccod INTEGER  NOT NULL DEFAULT NEXTVAL('htc_seq'),
  cidcod INTEGER  NOT NULL,
  pctidfseg NUMERIC(14) NOT NULL,
  htcstm VARCHAR(400) NULL,
  htcldo VARCHAR(400) NULL,
  htcobs VARCHAR(400) NULL,
  htcprc VARCHAR(400) NULL
);

ALTER TABLE htc
   ADD CONSTRAINT htcA PRIMARY KEY(htccod);  

CREATE INDEX htcB ON htc (htcstm, htcldo);

ALTER TABLE htc
   ADD CONSTRAINT FK_htc_CID FOREIGN KEY (cidcod)
                          REFERENCES CID (cidcod); 
ALTER TABLE htc
   ADD CONSTRAINT FK_htc_PCT FOREIGN KEY (pctidfseg)
                          REFERENCES PCT (pctidfseg);



