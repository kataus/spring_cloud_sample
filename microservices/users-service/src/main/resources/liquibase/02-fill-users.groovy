package liquibase

databaseChangeLog {
    changeSet(id: '0_2_1_Users_Drop_exists', author: 'vkutsenko') {
        sql(stripComments: true, splitStatements: false, endDelimiter: ';') {
            "insert into users (name) values ( 'boss'); " +
                    "insert into users (name) values ('manager1'); " +
                    "insert into users (name) values ( 'manager2'); " +
                    "insert into users (name) values ( 'support1'); " +
                    "insert into users (name) values ( 'support2'); " +
                    "insert into users (name) values ( 'support3'); "
        }
    }


}