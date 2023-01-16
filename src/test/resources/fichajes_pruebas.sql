--fichaje (id bigint not null, business_id bigint, date timestamp, employee_id varchar(255), record_type integer, service_id varchar(255), type integer, primary key (id))

-- Lunes
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (1, 1, {ts '2023-01-09 08:00:00.000'}, '222222222', 0, 'ALBASANZ', 0);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (2, 1, {ts '2023-01-09 13:30:00.000'}, '222222222', 1, 'ALBASANZ', 0);

-- Jueves
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (3, 1, {ts '2023-01-12 08:30:00.000'}, '222222222', 0, 'ALBASANZ', 0);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (4, 1, {ts '2023-01-12 14:00:00.000'}, '222222222', 1, 'ALBASANZ', 0);

-- Jueves - Otro empleado --misma semana
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (5, 1, {ts '2023-01-12 08:30:00.000'}, '333333333', 0, 'ALBASANZ', 0);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (6, 1, {ts '2023-01-12 14:00:00.000'}, '333333333', 1, 'ALBASANZ', 0);

-- Lunes -- Semana siguiente
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (7, 1, {ts '2023-01-16 08:00:00.000'}, '222222222', 0, 'ALBASANZ', 0);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (8, 1, {ts '2023-01-16 13:30:00.000'}, '222222222', 1, 'ALBASANZ', 0);

-- Lunes -- Semana anterior
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (9, 1, {ts '2023-01-02 08:00:00.000'}, '222222222', 0, 'ALBASANZ', 0);
INSERT INTO fichaje (id, business_id, date, employee_id, record_type, service_id, type) values (10, 1, {ts '2023-01-02 13:30:00.000'}, '222222222', 1, 'ALBASANZ', 0);