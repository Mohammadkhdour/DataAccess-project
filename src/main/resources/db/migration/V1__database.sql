CREATE TABLE IF NOT EXISTS book (
    title VARCHAR(32),
    isbn INT PRIMARY KEY,
    language VARCHAR(32),
    author VARCHAR(32)
);

INSERT INTO book (title, isbn, language, author) VALUES
('The Alchemist', 100001, 'English', 'Paulo Coelho'),
('One Hundred Years of Solitude', 100002, 'Spanish', 'Gabriel García Márquez'),
('Crime and Punishment', 100003, 'Russian', 'Fyodor Dostoevsky'),
('Les Misérables', 100004, 'French', 'Victor Hugo'),
('The Prophet', 100005, 'Arabic', 'Kahlil Gibran');