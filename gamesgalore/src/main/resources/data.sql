INSERT INTO users (user_email, user_first_name, user_last_name) VALUES
  ('samueldorilas@outlook.com', 'Samuel', 'Dorilas'),
  ('christophercoleman@outlook.com', 'Christopher', 'Coleman'),
  ('kylereimer@outlook.com', 'Kyle', 'Reimer');
  
INSERT INTO roles (role_name) VALUES
  ('ADMIN'), 
  ('USER');
  
INSERT INTO accounts (account_password, account_username, account_role_id, account_user_id) VALUES
('Pass123', 'samueldorilas', 1, 1),
('Pass123', 'christophercoleman', 1, 2),
('Pass123', 'kylereimer', 1, 3);
  