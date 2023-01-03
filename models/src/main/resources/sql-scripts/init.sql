INSERT INTO public.credentials(username, "password") VALUES ('test', 'asdf1234');
INSERT INTO public.details(name, surname, email, gender) VALUES ('Test', 'TestSurname', 'test@hotmail.com', 'Male');
INSERT INTO public.users("detailsId", "credId") VALUES (1,1);