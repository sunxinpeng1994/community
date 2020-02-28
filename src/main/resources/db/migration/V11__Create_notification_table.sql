create table notification
(
	id bigint auto_increment,
	notifier bigint not null,
	receiver bigint not null,
	outerId bigint not null,
	type int not null,
	gmt_create bigint not null,
	status int default 0,
	constraint notification_pk
		primary key (id)
);

comment on column notification.outerId is 'question id or comment id';

comment on column notification.type is 'reply or like';

comment on column notification.status is '0 unread; 1 read';

