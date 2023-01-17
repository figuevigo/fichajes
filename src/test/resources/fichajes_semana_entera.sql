--fichaje (id bigint not null, business_id bigint, date timestamp, employee_id varchar(255), record_type integer, service_id varchar(255), type integer, primary key (id))
DELETE fichaje;

-- Lunes (Jornada normal - 8H - Entrada 8 - Con descanso)
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (1, 1, {ts '2023-01-09 08:00:00.000'}, '222222222', 0, 'ALBASANZ', 0);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (2, 1, {ts '2023-01-09 11:00:00.000'}, '222222222', 0, 'ALBASANZ', 1);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (3, 1, {ts '2023-01-09 11:30:00.000'}, '222222222', 1, 'ALBASANZ', 1);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (4, 1, {ts '2023-01-09 14:00:00.000'}, '222222222', 1, 'ALBASANZ', 0);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (5, 1, {ts '2023-01-09 15:00:00.000'}, '222222222', 0, 'ALBASANZ', 0);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (6, 1, {ts '2023-01-09 17:30:00.000'}, '222222222', 1, 'ALBASANZ', 0);

-- Martes (Jornada incompleta - Se olvida fichaje salida mañana)
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (7, 1, {ts '2023-01-10 08:00:00.000'}, '222222222', 0, 'ALBASANZ', 0);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (8, 1, {ts '2023-01-10 11:00:00.000'}, '222222222', 0, 'ALBASANZ', 1);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (9, 1, {ts '2023-01-10 11:30:00.000'}, '222222222', 1, 'ALBASANZ', 1);
--INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (10, 1, {ts '2023-01-10 14:00:00.000'}, '222222222', 1, 'ALBASANZ', 0);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (11, 1, {ts '2023-01-10 15:00:00.000'}, '222222222', 0, 'ALBASANZ', 0);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (12, 1, {ts '2023-01-10 17:30:00.000'}, '222222222', 1, 'ALBASANZ', 0);

-- Miercoles (Desastre de día - Empieza antes de las 08:00 - Sin descanso - Se pasa de la jornada máxima)
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (13, 1, {ts '2023-01-11 07:30:00.000'}, '222222222', 0, 'ALBASANZ', 0);
--INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (14, 1, {ts '2023-01-11 11:00:00.000'}, '222222222', 0, 'ALBASANZ', 1);
--INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (15, 1, {ts '2023-01-11 11:30:00.000'}, '222222222', 1, 'ALBASANZ', 1);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (16, 1, {ts '2023-01-11 14:00:00.000'}, '222222222', 1, 'ALBASANZ', 0);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (17, 1, {ts '2023-01-11 15:00:00.000'}, '222222222', 0, 'ALBASANZ', 0);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (18, 1, {ts '2023-01-11 19:00:00.000'}, '222222222', 1, 'ALBASANZ', 0);

-- Jueves (Incompleta ficha salida de descanso pero no entrada)
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (19, 1, {ts '2023-01-12 08:00:00.000'}, '222222222', 0, 'ALBASANZ', 0);
--INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (20, 1, {ts '2023-01-12 11:00:00.000'}, '222222222', 0, 'ALBASANZ', 1);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (21, 1, {ts '2023-01-12 11:30:00.000'}, '222222222', 1, 'ALBASANZ', 1);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (22, 1, {ts '2023-01-12 14:00:00.000'}, '222222222', 1, 'ALBASANZ', 0);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (23, 1, {ts '2023-01-12 15:00:00.000'}, '222222222', 0, 'ALBASANZ', 0);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (24, 1, {ts '2023-01-12 17:30:00.000'}, '222222222', 1, 'ALBASANZ', 0);

-- Viernes (Completa pero empieza antes de las 7)
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (25, 1, {ts '2023-01-13 06:30:00.000'}, '222222222', 0, 'ALBASANZ', 0);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (26, 1, {ts '2023-01-13 11:00:00.000'}, '222222222', 0, 'ALBASANZ', 1);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (27, 1, {ts '2023-01-13 11:30:00.000'}, '222222222', 1, 'ALBASANZ', 1);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (28, 1, {ts '2023-01-13 14:00:00.000'}, '222222222', 1, 'ALBASANZ', 0);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (29, 1, {ts '2023-01-13 15:00:00.000'}, '222222222', 0, 'ALBASANZ', 0);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (30, 1, {ts '2023-01-13 16:00:00.000'}, '222222222', 1, 'ALBASANZ', 0);