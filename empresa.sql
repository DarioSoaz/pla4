create database empresa;
use empresa;

create table if not exists proveedor (
    IDProveedor int auto_increment primary key,
    NIF varchar(40) default '' not null,
    Nombre varchar(50) default '' not null,
    Direccion varchar(200) default '' not null
) ENGINE=INNODB;

create table if not exists producto (
    IDProducto int auto_increment primary key,
    Nombre varchar(50) default '' not null,
    PrecioUd float (8,2),
    IDProveedor int
) ENGINE=INNODB;

create table if not exists pedido (
    IDPedido int auto_increment primary key,
    IDCliente int,
    IDProducto int
) ENGINE=INNODB;

create table if not exists cliente (
    IDCliente int auto_increment primary key,
    Nombre varchar(50) not null,
    Apellidos varchar(120) not null,
	Direccion varchar(200) not null,	
    Telefono varchar(12) not null
) ENGINE=INNODB;

ALTER TABLE producto
  ADD FOREIGN KEY fk1(IDProveedor) REFERENCES proveedor(IDProveedor) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE pedido
  ADD FOREIGN KEY fk2(IDCliente) REFERENCES cliente(IDCliente) ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE pedido
  ADD FOREIGN KEY fk3(IDProducto) REFERENCES producto(IDProducto) ON DELETE RESTRICT ON UPDATE CASCADE;