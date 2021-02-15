INSERT INTO users(seq,email,passwd) VALUES (null,'test00@gmail.com','$$2GYy.');
INSERT INTO users(seq,email,passwd) VALUES (null,'test01@gmail.com','$2GYy');
INSERT INTO users(seq,email,passwd) VALUES (null,'test02@gmail.com','$2aG');

INSERT INTO posts(seq,user_seq,contents,like_count,comment_count,create_at) VALUES (null,1,'test01 first post',1,1,'2019-03-01 13:10:00');
INSERT INTO posts(seq,user_seq,contents,like_count,comment_count,create_at) VALUES (null,1,'test01 second post',0,0,'2019-03-12 09:45:00');
INSERT INTO posts(seq,user_seq,contents,like_count,comment_count,create_at) VALUES (null,1,'test01 third post',0,0,'2019-03-20 19:05:00');
INSERT INTO posts(seq,user_seq,contents,like_count,comment_count,create_at) VALUES (null,2,'test02 post',0,0,'2019-03-20 15:13:20');
