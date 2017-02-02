CREATE OR REPLACE TABLE developer (
    id INTEGER NOT NULL PRIMARY KEY,
    login VARCHAR(50),
    repos_url VARCHAR(80),
    repo_count INTEGER
);

CREATE OR REPLACE TABLE repo (
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(80),
    language VARCHAR(50),
    developer VARCHAR(50)
);

/*
 * // DROP TABLE developer IF EXISTS;

*/