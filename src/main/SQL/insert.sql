use avafieldbase;

/* Utente */
insert into avafieldbase.utente (nome, cognome, email, username, password, isAdmin, telefono, autovalutazione, idUtente) values ('Mario', 'Rossi', 'mariorossi99@gmail.com', 'MarioRossi99', SHA1('PasswordMario99'), 0, '3334445556', 3, 1);
insert into avafieldbase.utente (nome, cognome, email, username, password, isAdmin, telefono, autovalutazione, idUtente) values ('Luigi', 'Bianchi', 'luigibianchi98@gmail.com', 'LuigiBianchi98', SHA1('PasswordLuigi98'), 0, '3336677778', 2, 2);
insert into avafieldbase.utente (nome, cognome, email, username, password, isAdmin, telefono, autovalutazione, idUtente) values ('Marco', 'Verdi', 'marcoverdi00@gmail.com', 'MarcoVerdi00', SHA1('PasswordMarco00'), 0, '3331234561', 4, 3);
insert into avafieldbase.utente (nome, cognome, email, username, password, isAdmin, telefono, autovalutazione, idUtente) values ('Francesco', 'Neri', 'francesconeri01@gmail.com', 'FrancescoNeri01', SHA1('PasswordFrancesco01'), 0, '3225476890', 1, 4);
insert into avafieldbase.utente (nome, cognome, email, username, password, isAdmin, telefono, autovalutazione, idUtente) values ('Mina', 'Admin', 'minaadmin80@gmail.com', 'MinaAdmin', SHA1('admin'), 1, '3456345789', 0, 5);

/* Struttura */
insert into avafieldbase.struttura (idStruttura, nome, indirizzo, telefono, descrizione, capienza, categoria, numeroSpogliatoi, parcheggio) values (6, 'VolleyFun', 'Via Roma, 12', '3214567635', 'Ospitiamo le migliori partite di pallavolo!', 140, 'Pallavolo', 3, 1);
insert into avafieldbase.struttura (idStruttura, nome, indirizzo, telefono, descrizione, capienza, categoria, numeroSpogliatoi, parcheggio) values (7, 'SoccerEnjoy','Via Venezia, 23', '3425674567', 'Le partite a calcio più emozionanti!', 240, 'Calcio', 2, 1);
insert into avafieldbase.struttura (idStruttura, nome, indirizzo, telefono, descrizione, capienza, categoria, numeroSpogliatoi, parcheggio) values (8, 'JustBasketball','Via Napoli, 34', '3987654312', 'Prenota la tua prossima partita di basket!', 170, 'Basket', 4, 1);
insert into avafieldbase.struttura (idStruttura, nome, indirizzo, telefono, descrizione, capienza, categoria, numeroSpogliatoi, parcheggio) values (9, 'TennisHere','Via Padova, 42', '3345647568', 'Assisti a fantastiche partite di tennis!', 100, 'Tennis', 2, 1);
insert into avafieldbase.struttura (idStruttura, nome, indirizzo, telefono, descrizione, capienza, categoria, numeroSpogliatoi, parcheggio) values (10, 'PaddlePad','Via Torino, 56', '3331425678', 'Le partite di paddle più spettacolari!', 90, 'Paddle', 3, 1);

insert into avafieldbase.struttura (idStruttura, nome, indirizzo, telefono, descrizione, capienza, categoria, numeroSpogliatoi, parcheggio) values (20, 'VolleyFun', 'Via Roma, 12', '3214567635', 'Ospitiamo le migliori partite di pallavolo!', 140, 'Pallavolo', 3, 1);
insert into avafieldbase.struttura (idStruttura, nome, indirizzo, telefono, descrizione, capienza, categoria, numeroSpogliatoi, parcheggio) values (21, 'SoccerEnjoy','Via Venezia, 23', '3425674567', 'Le partite a calcio più emozionanti!', 240, 'Calcio', 2, 1);
insert into avafieldbase.struttura (idStruttura, nome, indirizzo, telefono, descrizione, capienza, categoria, numeroSpogliatoi, parcheggio) values (22, 'JustBasketball','Via Napoli, 34', '3987654312', 'Prenota la tua prossima partita di basket!', 170, 'Basket', 4, 1);
insert into avafieldbase.struttura (idStruttura, nome, indirizzo, telefono, descrizione, capienza, categoria, numeroSpogliatoi, parcheggio) values (23, 'TennisHere','Via Padova, 42', '3345647568', 'Assisti a fantastiche partite di tennis!', 100, 'Tennis', 2, 1);

/* Evento */
insert into avafieldbase.evento (idEvento, nome, numeroPartecipanti, dataEvento, orario, str_fk) values (11, 'Partita di pallavolo', 12, '2022-2-11','2022-2-11 13:20:00', 6);
insert into avafieldbase.evento (idEvento, nome, numeroPartecipanti, dataEvento, orario, str_fk) values (12, 'Partita di calcio', 22, '2022-3-12','2022-3-12 14:30:00', 7);
insert into avafieldbase.evento (idEvento, nome, numeroPartecipanti, dataEvento, orario, str_fk) values (13, 'Partita di basket', 10, '2022-4-5','2022-4-5 15:00:00', 8);
insert into avafieldbase.evento (idEvento, nome, numeroPartecipanti, dataEvento, orario, str_fk) values (14, 'Partita di tennis', 14, '2022-4-7','2022-4-7 16:30:00', 9);
insert into avafieldbase.evento (idEvento, nome, numeroPartecipanti, dataEvento, orario, str_fk) values (15, 'Partita di paddle', 14, '2022-5-3','2022-5-3 17:30:00', 10);

/* Recensione */
insert into avafieldbase.recensione (idRecensione, titolo, testo, numeroStelle, ute_fk, str_fk) values (16, 'Ottimo', 'Davvero ottima esperienza, ritornerò', 4, 1, 6);
insert into avafieldbase.recensione (idRecensione, titolo, testo, numeroStelle, ute_fk, str_fk) values (17, 'Pessima', 'Personale poco educato, non è stata una bella esperienza',1 , 2, 7);
insert into avafieldbase.recensione (idRecensione, titolo, testo, numeroStelle, ute_fk, str_fk) values (18, 'Ritornerò', 'Mi sono divertito, personale gentile e disponibile',3 , 3, 8);
insert into avafieldbase.recensione (idRecensione, titolo, testo, numeroStelle, ute_fk, str_fk) values (19, 'Mi piace', 'Una delle partite più divertenti a cui abbia assistito',5 , 4, 9);
insert into avafieldbase.recensione (idRecensione, titolo, testo, numeroStelle, ute_fk, str_fk) values (20, 'Deludente','Purtroppo non è stata una bella esperienza. Sconsiglio la prenotazione a questa struttura', 1, 5, 10);