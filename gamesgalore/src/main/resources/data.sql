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
  