workspace {
    model {
        ss1 = softwaresystem "Storage Management Service" {
            service1 = group "Service 1" {
                service1Api = container "Service 1 API" {
                    tags "Service 1" "Service API"
                }
                container "Service 1 Database" {
                    tags "Service 1" "Database"
                    service1Api -> this "Reads from and writes to"
                }
            }
            service2 = group "Service 2" {
                service2Api = container "Service 2 API" {
                    tags "Service 2" "Service API"
                }
                container "Service 2 Database" {
                    tags "Service 2" "Database"
                    service2Api -> this "Reads from and writes to"
                }
            }
            service3 = group "Service 3" {
                service3Api = container "Service 3 API" {
                    tags "Service 3" "Service API"
                }
                container "Service 3 Database" {
                    tags "Service 3" "Database"
                    service3Api -> this "Reads from and writes to"
                }
            }
        }
        service1Api -> service2Api
        service3Api -> service2Api
    }
    views {
                styles {
                element "Database" {
                shape cylinder
                background #f5da81
                color #000000
                icon "cylinder"
            }
        }
    }
}
