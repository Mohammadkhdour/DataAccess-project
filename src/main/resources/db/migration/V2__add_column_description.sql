ALTER TABLE Book add description VARCHAR(255);

INSERT INTO book (title, isbn, language, author, description) VALUES
('the men', 1212, 'arabic', 'mohammad khdour',"A book about men's experiences and challenges in modern society");