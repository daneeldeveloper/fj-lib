
CREATE TABLE db_jvfs_file (
	file_name VARCHAR(1024) NOT NULL,
	parent_path VARCHAR(2048) NOT NULL,
	file_props VARCHAR(1024),
	creation_time TIMESTAMP NOT NULL,
	update_time TIMESTAMP NOT NULL,
	file_size NUMBER(10),
	file_content BLOB
);