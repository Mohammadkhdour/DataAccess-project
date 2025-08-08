CREATE TABLE IF NOT EXISTS book (
    title VARCHAR(32),
    isbn INT PRIMARY KEY,
    language VARCHAR(32),
    author VARCHAR(32)
);

INSERT INTO book (title, isbn, language, author) VALUES
('History', 1001, 'English', 'kareem'),
('Leo Messi', 1002, 'Spanish', 'walled'),
('Barcalona', 1003, 'all language', 'the History'),
('Les Misérables', 1004, 'French', 'Victor Hugo'),
('The Prophet', 1005, 'Arabic', 'Kahlil Gibran');