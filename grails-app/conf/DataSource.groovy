dataSource {
    pooled = true
    driverClassName = "org.postgresql.Driver"
    username = "postgres"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
			password = "postgres"
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:postgresql://localhost/fakesmtp_development"
        }
    }
    test {
        dataSource {
			password = "postgres"
            dbCreate = "update"
            url = "jdbc:postgresql://localhost/fakesmtp_test"
        }
    }
    production {
        dataSource {
			password = "postgres"
            dbCreate = "update"
            url = "jdbc:postgresql://localhost/fakesmtp_production"
        }
    }
}
