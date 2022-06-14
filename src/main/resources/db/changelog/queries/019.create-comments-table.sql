create table comments (
    id bigserial primary key,
    "message" text not null,
    users_id bigint references users(id),
    nodes_id bigint references nodes(id)
);