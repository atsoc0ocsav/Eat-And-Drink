/*==============================================================*/
/* DBMS name:      Sybase SQL Anywhere 12                       */
/* Created on:     27/12/2013 12:13:21                          */
/*==============================================================*/


if exists(select 1 from sys.sysforeignkey where role='FK_COMENTAR_COMENTARI_ESTABELE') then
    alter table ComentarioAoEstabelecimento
       delete foreign key FK_COMENTAR_COMENTARI_ESTABELE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_COMENTAR_COMENTARI_UTILIZAD') then
    alter table ComentarioAoEstabelecimento
       delete foreign key FK_COMENTAR_COMENTARI_UTILIZAD
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_COMENTAR_COMENTARI_PRATO') then
    alter table ComentarioAoPrato
       delete foreign key FK_COMENTAR_COMENTARI_PRATO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_COMENTAR_COMENTARI_UTILIZAD') then
    alter table ComentarioAoPrato
       delete foreign key FK_COMENTAR_COMENTARI_UTILIZAD
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ESTABELE_LOCALDOES_ZONA') then
    alter table Estabelecimento
       delete foreign key FK_ESTABELE_LOCALDOES_ZONA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ESTABELE_SUGESTAO_UTILIZAD') then
    alter table Estabelecimento
       delete foreign key FK_ESTABELE_SUGESTAO_UTILIZAD
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ESTABELE_TIPODOEST_TIPODEES') then
    alter table Estabelecimento
       delete foreign key FK_ESTABELE_TIPODOEST_TIPODEES
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_FOTOGRAF_FOTOGRAFI_ESTABELE') then
    alter table Fotografia
       delete foreign key FK_FOTOGRAF_FOTOGRAFI_ESTABELE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_FOTOGRAF_FOTOGRAFI_PRATO') then
    alter table Fotografia
       delete foreign key FK_FOTOGRAF_FOTOGRAFI_PRATO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_FOTO2_FOTOGRAFI_UTILIZAD') then
    alter table Fotografia
       delete foreign key FK_FOTO2_FOTOGRAFI_UTILIZAD
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_HORARIOE_HORARIOES_DIASEMAN') then
    alter table HorarioEstabelecimento
       delete foreign key FK_HORARIOE_HORARIOES_DIASEMAN
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_HORARIOE_HORARIOES_ESTABELE') then
    alter table HorarioEstabelecimento
       delete foreign key FK_HORARIOE_HORARIOES_ESTABELE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_PRATO_TIPODOPRA_TIPODEPR') then
    alter table Prato
       delete foreign key FK_PRATO_TIPODOPRA_TIPODEPR
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_UTIL1_FOTOGRAFI_FOTOGRAF') then
    alter table Utilizador
       delete foreign key FK_UTIL1_FOTOGRAFI_FOTOGRAF
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ZONA_ZONADACID_CIDADE') then
    alter table Zona
       delete foreign key FK_ZONA_ZONADACID_CIDADE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_EVENTOOF_EVENTOOFE_ESTABELE') then
    alter table eventoOferecido
       delete foreign key FK_EVENTOOF_EVENTOOFE_ESTABELE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_EVENTOOF_EVENTOOFE_TIPODEEV') then
    alter table eventoOferecido
       delete foreign key FK_EVENTOOF_EVENTOOFE_TIPODEEV
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_FOLLOW_FOLLOW_UTILIZAD') then
    alter table follow
       delete foreign key FK_FOLLOW_FOLLOW_UTILIZAD
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_FOLLOW_FOLLOW2_UTILIZAD') then
    alter table follow
       delete foreign key FK_FOLLOW_FOLLOW2_UTILIZAD
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_MENUDOES_MENUDOEST_ESTABELE') then
    alter table menuDoEstabelecimento
       delete foreign key FK_MENUDOES_MENUDOEST_ESTABELE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_MENUDOES_MENUDOEST_PRATO') then
    alter table menuDoEstabelecimento
       delete foreign key FK_MENUDOES_MENUDOEST_PRATO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_PRATOREC_PRATORECO_PRATO') then
    alter table pratoRecomendado
       delete foreign key FK_PRATOREC_PRATORECO_PRATO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_PRATOREC_PRATORECO_UTILIZAD') then
    alter table pratoRecomendado
       delete foreign key FK_PRATOREC_PRATORECO_UTILIZAD
end if;

drop table if exists Cidade;

drop table if exists ComentarioAoEstabelecimento;

drop table if exists ComentarioAoPrato;

drop table if exists DiaSemana;

drop table if exists Estabelecimento;

drop table if exists Fotografia;

drop table if exists HorarioEstabelecimento;

drop table if exists Prato;

drop table if exists TipoDeEstabelecimento;

drop table if exists TipoDeEvento;

drop table if exists TipoDePrato;

drop table if exists Utilizador;

drop table if exists Zona;

drop table if exists eventoOferecido;

drop table if exists follow;

drop table if exists menuDoEstabelecimento;

drop table if exists pratoRecomendado;

/*==============================================================*/
/* Table: Cidade                                                */
/*==============================================================*/
create table Cidade 
(
   cidade               varchar(100)                   not null,
   constraint PK_CIDADE primary key (cidade)
);

/*==============================================================*/
/* Table: ComentarioAoEstabelecimento                           */
/*==============================================================*/
create table ComentarioAoEstabelecimento 
(
   idEstabelecimento    integer                        not null,
   email                varchar(80)                    not null,
   comentario           text                           null,
   nota                 integer                        null,
   estado               integer                        not null,
   constraint PK_COMENTARIOAOESTABELECIMENTO primary key (idEstabelecimento, email)
);

/*==============================================================*/
/* Table: ComentarioAoPrato                                     */
/*==============================================================*/
create table ComentarioAoPrato 
(
   email                varchar(80)                    not null,
   idPrato              integer                        not null,
   comentario           text                           null,
   nota                 integer                        null,
   constraint PK_COMENTARIOAOPRATO primary key (email, idPrato)
);

/*==============================================================*/
/* Table: DiaSemana                                             */
/*==============================================================*/
create table DiaSemana 
(
   diaDaSemana          integer                        not null,
   constraint PK_DIASEMANA primary key (diaDaSemana)
);

/*==============================================================*/
/* Table: Estabelecimento                                       */
/*==============================================================*/
create table Estabelecimento 
(
   informacoesHorario   varchar(300)                   null,
   formaDeLaChegar      text                           null,
   coordenadasGps       varchar(100)                   null,
   morada               varchar(500)                   not null,
   idEstabelecimento    integer                        not null,
   email                varchar(80)                    not null,
   idZona               integer                        null,
   tipoDoEstabelecimento varchar(50)                    null,
   designacao           varchar(300)                   not null,
   rating               decimal(5,2)                   null,
   constraint PK_ESTABELECIMENTO primary key (idEstabelecimento)
);

/*==============================================================*/
/* Table: Fotografia                                            */
/*==============================================================*/
create table Fotografia 
(
   idFotografia         integer                        not null,
   idEstabelecimento    integer                        null,
   emailutilizador      varchar(80)                    null,
   idPrato              integer                        null,
   localizacao          varchar(300)                   null,
   constraint PK_FOTOGRAFIA primary key (idFotografia)
);

/*==============================================================*/
/* Table: HorarioEstabelecimento                                */
/*==============================================================*/
create table HorarioEstabelecimento 
(
   idEstabelecimento    integer                        not null,
   diaDaSemana          integer                        not null,
   horaAbertura         time                           not null,
   horaFecho            time                           not null,
   constraint PK_HORARIOESTABELECIMENTO primary key (idEstabelecimento, diaDaSemana)
);

/*==============================================================*/
/* Table: Prato                                                 */
/*==============================================================*/
create table Prato 
(
   descricao            text                           not null,
   preco                decimal(5,2)                   null,
   idPrato              integer                        not null,
   tipoDePrato          integer                        null,
   rating               decimal(5,2)                   null,
   constraint PK_PRATO primary key (idPrato)
);

/*==============================================================*/
/* Table: TipoDeEstabelecimento                                 */
/*==============================================================*/
create table TipoDeEstabelecimento 
(
   tipoDoEstabelecimento varchar(50)                    not null,
   constraint PK_TIPODEESTABELECIMENTO primary key (tipoDoEstabelecimento)
);

/*==============================================================*/
/* Table: TipoDeEvento                                          */
/*==============================================================*/
create table TipoDeEvento 
(
   tipoDoEvento         integer                        not null,
   descricao            text                           not null,
   constraint PK_TIPODEEVENTO primary key (tipoDoEvento)
);

/*==============================================================*/
/* Table: TipoDePrato                                           */
/*==============================================================*/
create table TipoDePrato 
(
   tipoDePrato          integer                        not null,
   descricao            varchar(300)                   not null,
   constraint PK_TIPODEPRATO primary key (tipoDePrato)
);

/*==============================================================*/
/* Table: Utilizador                                            */
/*==============================================================*/
create table Utilizador 
(
   email                varchar(80)                    not null,
   idFotografia         integer                        not null,
   nome                 varchar(300)                   not null,
   escola               varchar(80)                    null,
   senha                varchar(15)                    not null,
   constraint PK_UTILIZADOR primary key (email)
);

/*==============================================================*/
/* Table: Zona                                                  */
/*==============================================================*/
create table Zona 
(
   idZona               integer                        not null,
   cidade               varchar(100)                   not null,
   designacao           varchar(200)                   not null,
   constraint PK_ZONA primary key (idZona)
);

/*==============================================================*/
/* Table: eventoOferecido                                       */
/*==============================================================*/
create table eventoOferecido 
(
   idEstabelecimento    integer                        not null,
   tipoDoEvento         integer                        not null,
   constraint PK_EVENTOOFERECIDO primary key (idEstabelecimento, tipoDoEvento)
);

/*==============================================================*/
/* Table: follow                                                */
/*==============================================================*/
create table follow 
(
   emailsguidor         varchar(80)                    not null,
   emailsgu             varchar(80)                    not null,
   constraint PK_FOLLOW primary key (emailsguidor, emailsgu)
);

/*==============================================================*/
/* Table: menuDoEstabelecimento                                 */
/*==============================================================*/
create table menuDoEstabelecimento 
(
   idEstabelecimento    integer                        not null,
   idPrato              integer                        not null,
   constraint PK_MENUDOESTABELECIMENTO primary key (idEstabelecimento, idPrato)
);

/*==============================================================*/
/* Table: pratoRecomendado                                      */
/*==============================================================*/
create table pratoRecomendado 
(
   email                varchar(80)                    not null,
   idPrato              integer                        not null,
   constraint PK_PRATORECOMENDADO primary key (email, idPrato)
);

alter table ComentarioAoEstabelecimento
   add constraint FK_COMENTAR_COMENTARI_ESTABELE foreign key (idEstabelecimento)
      references Estabelecimento (idEstabelecimento)
      on update cascade
      on delete cascade;

alter table ComentarioAoEstabelecimento
   add constraint FK_COMENTAR_COMENTARI_UTILIZAD foreign key (email)
      references Utilizador (email)
      on update cascade
      on delete cascade;

alter table ComentarioAoPrato
   add constraint FK_COMENTAR_COMENTARI_PRATO foreign key (idPrato)
      references Prato (idPrato)
      on update cascade
      on delete cascade;

alter table ComentarioAoPrato
   add constraint FK_COMENTAR_COMENTARI_UTILIZAD foreign key (email)
      references Utilizador (email)
      on update cascade
      on delete cascade;

alter table Estabelecimento
   add constraint FK_ESTABELE_LOCALDOES_ZONA foreign key (idZona)
      references Zona (idZona)
      on update cascade
      on delete restrict;

alter table Estabelecimento
   add constraint FK_ESTABELE_SUGESTAO_UTILIZAD foreign key (email)
      references Utilizador (email)
      on update cascade
      on delete cascade;

alter table Estabelecimento
   add constraint FK_ESTABELE_TIPODOEST_TIPODEES foreign key (tipoDoEstabelecimento)
      references TipoDeEstabelecimento (tipoDoEstabelecimento)
      on update cascade
      on delete set null;

alter table Fotografia
   add constraint FK_FOTOGRAF_FOTOGRAFI_ESTABELE foreign key (idEstabelecimento)
      references Estabelecimento (idEstabelecimento)
      on update cascade
      on delete cascade;

alter table Fotografia
   add constraint FK_FOTOGRAF_FOTOGRAFI_PRATO foreign key (idPrato)
      references Prato (idPrato)
      on update cascade
      on delete restrict;

alter table Fotografia
   add constraint FK_FOTO2_FOTOGRAFI_UTILIZAD foreign key (emailutilizador)
      references Utilizador (email)
      on update cascade
      on delete cascade;

alter table HorarioEstabelecimento
   add constraint FK_HORARIOE_HORARIOES_DIASEMAN foreign key (diaDaSemana)
      references DiaSemana (diaDaSemana)
      on update cascade
      on delete cascade;

alter table HorarioEstabelecimento
   add constraint FK_HORARIOE_HORARIOES_ESTABELE foreign key (idEstabelecimento)
      references Estabelecimento (idEstabelecimento)
      on update cascade
      on delete cascade;

alter table Prato
   add constraint FK_PRATO_TIPODOPRA_TIPODEPR foreign key (tipoDePrato)
      references TipoDePrato (tipoDePrato)
      on update cascade
      on delete set null;

alter table Utilizador
   add constraint FK_UTIL1_FOTOGRAFI_FOTOGRAF foreign key (idFotografia)
      references Fotografia (idFotografia)
      on update cascade
      on delete restrict;

alter table Zona
   add constraint FK_ZONA_ZONADACID_CIDADE foreign key (cidade)
      references Cidade (cidade)
      on update cascade
      on delete restrict;

alter table eventoOferecido
   add constraint FK_EVENTOOF_EVENTOOFE_ESTABELE foreign key (idEstabelecimento)
      references Estabelecimento (idEstabelecimento)
      on update cascade
      on delete cascade;

alter table eventoOferecido
   add constraint FK_EVENTOOF_EVENTOOFE_TIPODEEV foreign key (tipoDoEvento)
      references TipoDeEvento (tipoDoEvento)
      on update cascade
      on delete cascade;

alter table follow
   add constraint FK_FOLLOW_FOLLOW_UTILIZAD foreign key (emailsguidor)
      references Utilizador (email)
      on update cascade
      on delete cascade;

alter table follow
   add constraint FK_FOLLOW_FOLLOW2_UTILIZAD foreign key (emailsgu)
      references Utilizador (email)
      on update cascade
      on delete cascade;

alter table menuDoEstabelecimento
   add constraint FK_MENUDOES_MENUDOEST_ESTABELE foreign key (idEstabelecimento)
      references Estabelecimento (idEstabelecimento)
      on update cascade
      on delete cascade;

alter table menuDoEstabelecimento
   add constraint FK_MENUDOES_MENUDOEST_PRATO foreign key (idPrato)
      references Prato (idPrato)
      on update cascade
      on delete cascade;

alter table pratoRecomendado
   add constraint FK_PRATOREC_PRATORECO_PRATO foreign key (idPrato)
      references Prato (idPrato)
      on update cascade
      on delete cascade;

alter table pratoRecomendado
   add constraint FK_PRATOREC_PRATORECO_UTILIZAD foreign key (email)
      references Utilizador (email)
      on update cascade
      on delete cascade;

