package liquibase

databaseChangeLog {
    include(file: '01-create-users.groovy', relativeToChangelogFile: true)
    include(file: '02-fill-users.groovy', relativeToChangelogFile: true)

}
