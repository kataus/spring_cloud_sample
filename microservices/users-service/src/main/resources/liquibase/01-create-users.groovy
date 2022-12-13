package liquibase

databaseChangeLog {
    changeSet(id: '0_1_1_Users_Drop_exists', author: 'vkutsenko') {
        sql(stripComments: true, splitStatements: false, endDelimiter: ';') {
            "DROP TABLE IF EXISTS users "
        }
    }

    changeSet(id: '0_1_2_Users_Sequence', author: 'vkutsenko') {
        sql(stripComments: true, splitStatements: false, endDelimiter: ';') {
            "CREATE SEQUENCE IF NOT EXISTS users_id_seq"
        }
    }

    changeSet(id: '0_1_3_Users_Table', author: 'vkutsenko') {
        sql(stripComments: true, splitStatements: false, endDelimiter: ';') {
            "create table users" +
                    "( id             bigint    default nextval('users_id_seq'::regclass) not null\n" +
                    "        constraint users_pkey\n" +
                    "            primary key, \n" +
                    "    name      varchar(255)" +
                    ");"
        }
    }

}