CREATE OR REPLACE TABLE Developer (
    developer_id INTEGER NOT NULL PRIMARY KEY,
    login VARCHAR(50),
    repos_url VARCHAR(80),
    repo_count INTEGER
);

CREATE OR REPLACE TABLE Repo (
    repo_id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(80),
    language VARCHAR(50),
    developer VARCHAR(50)
);
