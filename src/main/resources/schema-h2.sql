DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
  seq           bigint NOT NULL AUTO_INCREMENT,
  email         varchar(50) NOT NULL,
  passwd        varchar(80) NOT NULL,
  login_count   int NOT NULL DEFAULT 0,
  last_login_at datetime DEFAULT NULL,
  create_at     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (seq),
  CONSTRAINT unq_user_email UNIQUE (email)
);

CREATE TABLE posts (
  seq           bigint NOT NULL AUTO_INCREMENT,
  user_seq      bigint NOT NULL,
  contents      varchar(500) NOT NULL,
  like_count    int NOT NULL DEFAULT 0,
  comment_count int NOT NULL DEFAULT 0,
  create_at     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (seq),
  CONSTRAINT fk_post_to_user FOREIGN KEY (user_seq) REFERENCES users (seq) ON DELETE RESTRICT ON UPDATE RESTRICT
);
