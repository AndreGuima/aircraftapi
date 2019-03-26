CREATE table aircraft(
  id bigint auto_increment not null,
  name varchar(255) not null,
  serial_number varchar(20) not null,
  un varchar(20),
  capacity int not null,
  weight decimal (10,1) not null,
  manufacture_date DATETIME not null,

  primary key (id)
);