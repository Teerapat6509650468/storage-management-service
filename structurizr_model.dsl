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
                    
                }
            
                service3Controller = container "Storage Controller (REST)" {
                    tags "Service 3" "REST Controller"
                    technology "Spring Boot"
                    service3Api -> this "Exposes REST endpoints"
                }
            
                service3ServiceLayer = container "Storage Service Layer" {
                    tags "Service 3" "Service Layer"
                    technology "Spring Boot"
                    service3Controller -> this "Calls business logic"
                }
            
                service3Repo = container "Storage Repository (JPA/JDBC)" {
                    tags "Service 3" "Repository Layer"
                    technology "Spring Data JPA / JDBC"
                    service3ServiceLayer -> this "Reads/Writes data"
                }
            
            }
                container "Storage Management Database" {
                    tags "Service 3" "Database"
                    service3Repo -> this "Reads from and writes to"
                }
            kafka = group "Kafka" {
                kafkacontainer = container "Kafka Container" {
                    tag "Kafka"                    
                }
            }
            service1Api -> kafkacontainer "send message: product(s) check-in warehouse"
            service2Api -> kafkacontainer "send message: product(s) retrieve warehouse"
            kafkacontainer -> service3Api "recieve message: check-in/retrive from warehouse"
            service3Api -> kafkacontainer "send message: ACCEPT or REJECT check-in/retrive request(s)"
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

        theme default
        
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
        }
    }
}
