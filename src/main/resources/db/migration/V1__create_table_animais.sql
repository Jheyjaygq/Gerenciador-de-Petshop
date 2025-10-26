
CREATE TABLE IF NOT EXISTS animais (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    especie TEXT NOT NULL,
    raca TEXT,
    idade INTEGER NOT NULL,
    data_cadastro DATE,
    nome TEXT
);
