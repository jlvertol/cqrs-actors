create table EVENT (
   ordering int not null auto_increment,
   persistence_id uuid not null,
   write_timestamp bigint not null,
   event_payload varchar
)