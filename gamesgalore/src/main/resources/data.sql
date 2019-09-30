INSERT INTO users (user_email, user_first_name, user_last_name) VALUES
  ('samueldorilas@outlook.com', 'Samuel', 'Dorilas'),
  ('christophercoleman@outlook.com', 'Christopher', 'Coleman'),
  ('kylereimer@outlook.com', 'Kyle', 'Reimer');
  
INSERT INTO roles (role_name) VALUES
  ('ADMIN'), 
  ('USER');
  
INSERT INTO accounts VALUES
(default, true, true, '$2a$10$3gSlF.jlu4skLXQ/2SCKX.Jr6GJht/7RHA.DDLJHnILZzDM4ZOUyq', 'samueldorilas', true, true, 1, 1),
(default, true, true, '$2a$10$3gSlF.jlu4skLXQ/2SCKX.Jr6GJht/7RHA.DDLJHnILZzDM4ZOUyq', 'christophercoleman', true, true, 1, 2),
(default, true, true,'$2a$10$3gSlF.jlu4skLXQ/2SCKX.Jr6GJht/7RHA.DDLJHnILZzDM4ZOUyq', 'kylereimer', true, true, 1, 3);

INSERT INTO genres VALUES
(default, 'Horrorx'),
(default, 'Actionx');

INSERT INTO platforms VALUES
(default, 'PS4x'),
(default, 'XBOXx');

INSERT INTO games VALUES
(default, 'God of War'),
(default, 'Prince of Persia');

INSERT INTO accounts_genres VALUES
(1, 1),
(2, 2);

INSERT INTO accounts_platforms VALUES
(1, 1),
(2, 2);
  