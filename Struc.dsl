workspace {

    model {
        softwareSystem = softwaresystem "Storage Management Service" {
            service1 = group "Goods Check-In" {
                service1Api = container "Goods Check-In Service" {
                    tags "Service 1" "Service API"
                }
            }
            container "Goods Check-In Database" {
                tags "Service 1" "Database"
                service1Api -> this "Reads from and writes to"
            }

            service2 = group "Goods Retrieval" {
                service2Api = container "Goods Retrieval Service" {
                    tags "Service 2" "Service API"
                }
            }
            container "Retrieval Database" {
                tags "Service 2" "Database"
                service2Api -> this "Reads from and writes to"
            }

            service3 = group "Storage Management" {
                service3Api = container "Storage Management Service" {
                    tags "Service 3" "Service API"

                    service3Controller = component "Storage Controller (REST)" "Exposes REST endpoints for check-in/retrieval." "Spring Boot REST Controller"
                    service3ServiceLayer = component "Storage Service Layer" "Handles business logic for storage management." "Spring Boot Service"
                    service3Repository = component "Storage Repository (JPA/JDBC)" "Reads and writes to the database using JPA or JDBC." "Spring Data JPA / JDBC"

                    service3Controller -> service3ServiceLayer "Calls"
                    service3ServiceLayer -> service3Repository "Reads from and writes to"
                }
            }

            container "Storage Management Database" {
                tags "Service 3" "Database"
                service3Repository -> this "Reads from and writes to"
            }

            kafka = group "Kafka" {
                kafkacontainer = container "Kafka Container" {
                    tag "Kafka"
                }
            }

            service1Api -> kafkacontainer "send message: product(s) check-in warehouse"
            service2Api -> kafkacontainer "send message: product(s) retrieve warehouse"
            kafkacontainer -> service3Api "receive message: check-in/retrieve from warehouse"
            service3Api -> kafkacontainer "send message: ACCEPT or REJECT check-in/retrieve request(s)"
            kafkacontainer -> service1Api "return request: check-in SUCCESS/FAILURE"
            kafkacontainer -> service2Api "return request: retrieve SUCCESS/FAILURE"
        }
    }

    views {
        systemContext softwareSystem {
            include *
            autolayout lr
        }

        container softwareSystem {
            include *
            autolayout lr
        }

        component service3Api "StorageManagementComponents" {
            include *
            autoLayout lr
            description "Component diagram showing internal structure of the Storage Management Service."
        }

        styles {
            element "Database" {
                shape cylinder
                background #00a3ee
                icon "cylinder"
            }
            element "Service API" {
                shape hexagon
                background #6DB33F
            }
            element "REST Controller" {
                shape component
                background #facc15
            }
            element "Service Layer" {
                shape component
                background #4ade80
            }
            element "Repository Layer" {
                shape component
                background #60a5fa
            }
        }

        theme default
    }
}
