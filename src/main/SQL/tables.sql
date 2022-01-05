drop database avafieldbase;
create database avafieldbase;
GRANT ALL PRIVILEGES ON avafieldbase.* TO 'root'@'localhost';
use avafieldbase;

create table utente
(
    nome            varchar(30) not null,
    cognome         varchar(30) not null,
    email           varchar(40) null,
    username        varchar(30) not null,
    password        varchar(40) not null,
    isAdmin         tinyint(1)  null,
    telefono        varchar(15) not null,
    autovalutazione int         not null,
    idUtente        int         not null
        primary key
);

create table struttura
(
    idStruttura      int         not null
        primary key,
    nome             varchar(20) not null,
    indirizzo        varchar(40) not null,
    telefono         varchar(15) not null,
    descrizione      varchar(50) not null,
    capienza         int         not null,
    categoria        varchar(20) not null,
    numeroSpogliatoi int         not null,
    parcheggio       tinyint(1)  null
);

create table evento
(
    idEvento           int         not null
        primary key,
    nome               varchar(30) not null,
    numeroPartecipanti int         not null,
    dataEvento         date        not null,
    orario             time        not null,
    str_fk             int         null,
    constraint str_evento_fk
        foreign key (str_fk) references struttura (idStruttura)
            on update cascade on delete cascade
);

create table recensione
(
    idRecensione int          not null
        primary key,
    titolo       varchar(15)  not null,
    testo        varchar(150) not null,
    numeroStelle int          not null,
    ute_fk       int          null,
    str_fk       int          null,
    constraint recensione_struttura_fk
        foreign key (str_fk) references struttura (idStruttura)
            on update cascade on delete cascade,
    constraint recensione_utente_fk
        foreign key (ute_fk) references utente (idUtente)
            on update cascade on delete cascade
);

create table evento_utente
(
    eve_fk   int not null,
    ute_fk   int not null,
    primary key (eve_fk, ute_fk)
);



