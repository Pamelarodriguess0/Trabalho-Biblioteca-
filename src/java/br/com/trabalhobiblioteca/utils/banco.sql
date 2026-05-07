 create table livros (
	id serial primary key,
	nomeLivro varchar (150) not null,
	isbn varchar(20) not null, 
	autor varchar (100),
	dataPublicacao date,
	valorLivro decimal (10,2) not null);

--	insert into livros (nomeLivro, isbn, autor, dataPublicacao, valorLivro)
--	values ('Jantar Secreto', '14569874523014', 'Raphael Montes', '2016-11-14', '55.00' ),
--	       ('A Ultima Carta', '87562149875563', 'Rebecca Yarros', '2025-04-09', '80.00'),
--	       ('O Principe Cruel', '4587962587021', 'Holly Black', '2018-07-15', '43.12'); 
